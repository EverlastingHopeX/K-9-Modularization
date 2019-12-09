package com.fsck.k9m.crypto.openpgp;


import com.fsck.k9m.mail.Body;
import com.fsck.k9m.mail.BodyPart;
import com.fsck.k9m.mail.Message;
import com.fsck.k9m.mail.MessagingException;
import com.fsck.k9m.mail.internet.MimeBodyPart;
import com.fsck.k9m.mail.internet.MimeHeader;
import com.fsck.k9m.mail.internet.MimeMessage;
import com.fsck.k9m.mail.internet.MimeMultipart;
import com.fsck.k9m.mail.internet.TextBody;
import com.fsck.k9m.mailstore.BinaryMemoryBody;


public class MessageCreationHelper {
    public static BodyPart createPart(String mimeType) throws MessagingException {
        BinaryMemoryBody body = new BinaryMemoryBody(new byte[0], "utf-8");
        return new MimeBodyPart(body, mimeType);
    }

    public static Message createTextMessage(String mimeType, String text) {
        TextBody body = new TextBody(text);
        return createMessage(mimeType, body);
    }

    public static Message createMultipartMessage(String mimeType, BodyPart... parts) {
        MimeMultipart body = createMultipartBody(mimeType, parts);
        return createMessage(mimeType, body);
    }

    public static Message createMessage(String mimeType) {
        return createMessage(mimeType, null);
    }

    private static Message createMessage(String mimeType, Body body) {
        MimeMessage message = new MimeMessage();
        message.setBody(body);
        message.setHeader(MimeHeader.HEADER_CONTENT_TYPE, mimeType);

        return message;
    }

    private static MimeMultipart createMultipartBody(String mimeType, BodyPart[] parts) {
        MimeMultipart multipart = new MimeMultipart(mimeType, "boundary");
        for (BodyPart part : parts) {
            multipart.addBodyPart(part);
        }
        return multipart;
    }
}
