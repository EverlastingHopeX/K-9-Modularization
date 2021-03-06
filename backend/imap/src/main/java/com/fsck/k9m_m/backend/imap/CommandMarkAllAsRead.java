package com.fsck.k9m_m.backend.imap;


import java.util.Collections;

import com.fsck.k9m_m.mail.Flag;
import com.fsck.k9m_m.mail.Folder;
import com.fsck.k9m_m.mail.MessagingException;
import com.fsck.k9m_m.mail.store.imap.ImapFolder;
import com.fsck.k9m_m.mail.store.imap.ImapStore;
import org.jetbrains.annotations.NotNull;


class CommandMarkAllAsRead {
    private final ImapStore imapStore;


    CommandMarkAllAsRead(ImapStore imapStore) {
        this.imapStore = imapStore;
    }

    void markAllAsRead(@NotNull String folderServerId) throws MessagingException {
        ImapFolder remoteFolder = imapStore.getFolder(folderServerId);
        if (!remoteFolder.exists()) {
            return;
        }

        try {
            remoteFolder.open(Folder.OPEN_MODE_RW);
            if (remoteFolder.getMode() != Folder.OPEN_MODE_RW) {
                return;
            }

            remoteFolder.setFlags(Collections.singleton(Flag.SEEN), true);
        } finally {
            remoteFolder.close();
        }
    }
}
