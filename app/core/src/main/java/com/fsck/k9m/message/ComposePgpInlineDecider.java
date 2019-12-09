package com.fsck.k9m.message;


import java.util.List;

import com.fsck.k9m.crypto.MessageCryptoStructureDetector;
import com.fsck.k9m.mail.Message;
import com.fsck.k9m.mail.Part;


public class ComposePgpInlineDecider {
    public boolean shouldReplyInline(Message localMessage) {
        // TODO more criteria for this? maybe check the User-Agent header?
        return messageHasPgpInlineParts(localMessage);
    }

    private boolean messageHasPgpInlineParts(Message localMessage) {
        List<Part> inlineParts = MessageCryptoStructureDetector.findPgpInlineParts(localMessage);
        return !inlineParts.isEmpty();
    }
}
