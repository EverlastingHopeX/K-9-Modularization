package com.fsck.k9m_m.mailstore;


import java.util.Stack;

import com.fsck.k9m_m.mail.Body;
import com.fsck.k9m_m.mail.BodyPart;
import com.fsck.k9m_m.mail.MessagingException;
import com.fsck.k9m_m.mail.Multipart;
import com.fsck.k9m_m.mail.Part;
import com.fsck.k9m_m.mail.internet.MimeBodyPart;


public class MessageHelper {

    public static boolean isCompletePartAvailable(Part part) {
        Stack<Part> partsToCheck = new Stack<>();
        partsToCheck.push(part);

        while (!partsToCheck.isEmpty()) {
            Part currentPart = partsToCheck.pop();
            Body body = currentPart.getBody();

            boolean isBodyMissing = body == null;
            if (isBodyMissing) {
                return false;
            }

            if (body instanceof Multipart) {
                Multipart multipart = (Multipart) body;
                for (BodyPart bodyPart : multipart.getBodyParts()) {
                    partsToCheck.push(bodyPart);
                }
            }
        }

        return true;
    }

    public static MimeBodyPart createEmptyPart() {
        try {
            return new MimeBodyPart(null);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
