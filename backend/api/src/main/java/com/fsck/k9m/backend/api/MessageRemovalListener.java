package com.fsck.k9m.backend.api;

import com.fsck.k9m.mail.Message;

public interface MessageRemovalListener {
    void messageRemoved(Message message);
}
