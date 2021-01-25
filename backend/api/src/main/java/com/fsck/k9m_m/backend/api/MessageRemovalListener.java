package com.fsck.k9m_m.backend.api;

import com.fsck.k9m_m.mail.Message;

public interface MessageRemovalListener {
    void messageRemoved(Message message);
}
