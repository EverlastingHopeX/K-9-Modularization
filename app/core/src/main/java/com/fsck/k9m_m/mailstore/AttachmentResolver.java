package com.fsck.k9m_m.mailstore;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;

import com.fsck.k9m_m.DI;
import timber.log.Timber;

import com.fsck.k9m_m.mail.Body;
import com.fsck.k9m_m.mail.MessagingException;
import com.fsck.k9m_m.mail.Multipart;
import com.fsck.k9m_m.mail.Part;
import com.fsck.k9m_m.message.extractors.AttachmentInfoExtractor;


/**
 * This class is used to encapsulate a message part, providing an interface to
 * get relevant info for a given Content-ID URI.
 *
 * The point of this class is to keep the Content-ID loading code agnostic of
 * the underlying part structure.
 */
public class AttachmentResolver {
    Map<String,Uri> contentIdToAttachmentUriMap;


    private AttachmentResolver(Map<String, Uri> contentIdToAttachmentUriMap) {
        this.contentIdToAttachmentUriMap = contentIdToAttachmentUriMap;
    }

    @Nullable
    public Uri getAttachmentUriForContentId(String cid) {
        return contentIdToAttachmentUriMap.get(cid);
    }

    @WorkerThread
    public static AttachmentResolver createFromPart(Part part) {
        AttachmentInfoExtractor attachmentInfoExtractor = DI.get(AttachmentInfoExtractor.class);
        Map<String, Uri> contentIdToAttachmentUriMap = buildCidToAttachmentUriMap(attachmentInfoExtractor, part);
        return new AttachmentResolver(contentIdToAttachmentUriMap);
    }

    @VisibleForTesting
    static Map<String,Uri> buildCidToAttachmentUriMap(AttachmentInfoExtractor attachmentInfoExtractor,
            Part rootPart) {
        HashMap<String,Uri> result = new HashMap<>();

        Stack<Part> partsToCheck = new Stack<>();
        partsToCheck.push(rootPart);

        while (!partsToCheck.isEmpty()) {
            Part part = partsToCheck.pop();

            Body body = part.getBody();
            if (body instanceof Multipart) {
                Multipart multipart = (Multipart) body;
                for (Part bodyPart : multipart.getBodyParts()) {
                    partsToCheck.push(bodyPart);
                }
            } else {
                try {
                    String contentId = part.getContentId();
                    if (contentId != null) {
                        AttachmentViewInfo attachmentInfo = attachmentInfoExtractor.extractAttachmentInfo(part);
                        result.put(contentId, attachmentInfo.internalUri);
                    }
                } catch (MessagingException e) {
                    Timber.e(e, "Error extracting attachment info");
                }
            }
        }

        return Collections.unmodifiableMap(result);
    }

}
