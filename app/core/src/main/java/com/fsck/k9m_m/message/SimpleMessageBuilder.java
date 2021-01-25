package com.fsck.k9m_m.message;


import android.content.Intent;
import androidx.annotation.VisibleForTesting;

import com.fsck.k9m_m.CoreResourceProvider;
import com.fsck.k9m_m.DI;
import com.fsck.k9m_m.mail.BoundaryGenerator;
import com.fsck.k9m_m.mail.MessagingException;
import com.fsck.k9m_m.mail.internet.MessageIdGenerator;
import com.fsck.k9m_m.mail.internet.MimeMessage;


public class SimpleMessageBuilder extends MessageBuilder {

    public static SimpleMessageBuilder newInstance() {
        MessageIdGenerator messageIdGenerator = MessageIdGenerator.getInstance();
        BoundaryGenerator boundaryGenerator = BoundaryGenerator.getInstance();
        CoreResourceProvider resourceProvider = DI.get(CoreResourceProvider.class);
        return new SimpleMessageBuilder(messageIdGenerator, boundaryGenerator, resourceProvider);
    }

    @VisibleForTesting
    SimpleMessageBuilder(MessageIdGenerator messageIdGenerator, BoundaryGenerator boundaryGenerator,
            CoreResourceProvider resourceProvider) {
        super(messageIdGenerator, boundaryGenerator, resourceProvider);
    }

    @Override
    protected void buildMessageInternal() {
        try {
            MimeMessage message = build();
            queueMessageBuildSuccess(message);
        } catch (MessagingException me) {
            queueMessageBuildException(me);
        }
    }

    @Override
    protected void buildMessageOnActivityResult(int requestCode, Intent data) {
        throw new UnsupportedOperationException();
    }
}
