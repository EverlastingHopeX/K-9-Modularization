package com.fsck.k9m_m.message;


import java.util.List;

import com.fsck.k9m_m.crypto.MessageCryptoStructureDetector;
import com.fsck.k9m_m.mail.Message;
import com.fsck.k9m_m.mail.Part;


public class ComposePgpEnableByDefaultDecider {
    public boolean shouldEncryptByDefault(Message localMessage) {
        return messageIsEncrypted(localMessage);
    }

    private boolean messageIsEncrypted(Message localMessage) {
        List<Part> encryptedParts = MessageCryptoStructureDetector.findMultipartEncryptedParts(localMessage);
        return !encryptedParts.isEmpty();
    }
}
