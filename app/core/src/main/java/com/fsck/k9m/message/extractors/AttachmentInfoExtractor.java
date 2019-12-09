package com.fsck.k9m.message.extractors;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import timber.log.Timber;
import androidx.annotation.WorkerThread;

import com.fsck.k9m.mail.Body;
import com.fsck.k9m.mail.MessagingException;
import com.fsck.k9m.mail.Part;
import com.fsck.k9m.mail.internet.MimeHeader;
import com.fsck.k9m.mail.internet.MimeUtility;
import com.fsck.k9m.mailstore.AttachmentViewInfo;
import com.fsck.k9m.mailstore.DeferredFileBody;
import com.fsck.k9m.mailstore.LocalMessage;
import com.fsck.k9m.mailstore.LocalPart;
import com.fsck.k9m.provider.AttachmentProvider;
import com.fsck.k9m.provider.DecryptedFileProvider;


public class AttachmentInfoExtractor {
    private final Context context;


    AttachmentInfoExtractor(Context context) {
        this.context = context;
    }

    @WorkerThread
    public List<AttachmentViewInfo> extractAttachmentInfoForView(List<Part> attachmentParts)
            throws MessagingException {

        List<AttachmentViewInfo> attachments = new ArrayList<>();
        for (Part part : attachmentParts) {
            AttachmentViewInfo attachmentViewInfo = extractAttachmentInfo(part);
            attachments.add(attachmentViewInfo);
        }

        return attachments;
    }

    @WorkerThread
    public AttachmentViewInfo extractAttachmentInfo(Part part) throws MessagingException {
        Uri uri;
        long size;
        boolean isContentAvailable;

        if (part instanceof LocalPart) {
            LocalPart localPart = (LocalPart) part;
            String accountUuid = localPart.getAccountUuid();
            long messagePartId = localPart.getPartId();
            size = localPart.getSize();
            isContentAvailable = part.getBody() != null;
            uri = AttachmentProvider.getAttachmentUri(accountUuid, messagePartId);
        } else if (part instanceof LocalMessage) {
            LocalMessage localMessage = (LocalMessage) part;
            String accountUuid = localMessage.getAccount().getUuid();
            long messagePartId = localMessage.getMessagePartId();
            size = localMessage.getSize();
            isContentAvailable = part.getBody() != null;
            uri = AttachmentProvider.getAttachmentUri(accountUuid, messagePartId);
        } else {
            Body body = part.getBody();
            if (body instanceof DeferredFileBody) {
                DeferredFileBody decryptedTempFileBody = (DeferredFileBody) body;
                size = decryptedTempFileBody.getSize();
                uri = getDecryptedFileProviderUri(decryptedTempFileBody, part.getMimeType());
                isContentAvailable = true;
            } else {
                throw new IllegalArgumentException("Unsupported part type provided");
            }
        }

        return extractAttachmentInfo(part, uri, size, isContentAvailable);
    }

    @Nullable
    @VisibleForTesting
    protected Uri getDecryptedFileProviderUri(DeferredFileBody decryptedTempFileBody, String mimeType) {
        Uri uri;
        try {
            File file = decryptedTempFileBody.getFile();
            uri = DecryptedFileProvider.getUriForProvidedFile(
                    context, file, decryptedTempFileBody.getEncoding(), mimeType);
        } catch (IOException e) {
            Timber.e(e, "Decrypted temp file (no longer?) exists!");
            uri = null;
        }
        return uri;
    }

    public AttachmentViewInfo extractAttachmentInfoForDatabase(Part part) throws MessagingException {
        boolean isContentAvailable = part.getBody() != null;
        return extractAttachmentInfo(part, Uri.EMPTY, AttachmentViewInfo.UNKNOWN_SIZE, isContentAvailable);
    }

    @WorkerThread
    private AttachmentViewInfo extractAttachmentInfo(Part part, Uri uri, long size, boolean isContentAvailable) {
        boolean inlineAttachment = false;

        String mimeType = part.getMimeType();
        String contentTypeHeader = part.getContentType();
        String contentDisposition = part.getDisposition();

        String name = MimeUtility.getHeaderParameter(contentDisposition, "filename");
        if (name == null) {
            name = MimeUtility.getHeaderParameter(contentTypeHeader, "name");
        }

        if (name == null) {
            String extension = null;
            if (mimeType != null) {
                extension = MimeUtility.getExtensionByMimeType(mimeType);
            }
            name = "noname" + ((extension != null) ? "." + extension : "");
        }

        // Inline parts with a Content-Id header and a MIME type of image/* are probably components of an HTML message,
        // not attachments.
        if (contentDisposition != null &&
                MimeUtility.getHeaderParameter(contentDisposition, null).matches("^(?i:inline)") &&
                part.getHeader(MimeHeader.HEADER_CONTENT_ID).length > 0 &&
                mimeType != null && mimeType.toLowerCase(Locale.ROOT).startsWith("image/")) {
            inlineAttachment = true;
        }

        long attachmentSize = extractAttachmentSize(contentDisposition, size);

        return new AttachmentViewInfo(mimeType, name, attachmentSize, uri, inlineAttachment, part, isContentAvailable);
    }

    @WorkerThread
    private long extractAttachmentSize(String contentDisposition, long size) {
        if (size != AttachmentViewInfo.UNKNOWN_SIZE) {
            return size;
        }

        long result = AttachmentViewInfo.UNKNOWN_SIZE;
        String sizeParam = MimeUtility.getHeaderParameter(contentDisposition, "size");
        if (sizeParam != null) {
            try {
                result = Integer.parseInt(sizeParam);
            } catch (NumberFormatException e) { /* ignore */ }
        }

        return result;
    }
}
