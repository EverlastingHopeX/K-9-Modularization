package com.fsck.k9m.backend.imap


import com.fsck.k9m.mail.Flag
import com.fsck.k9m.mail.Folder
import com.fsck.k9m.mail.MessagingException
import com.fsck.k9m.mail.store.imap.ImapStore


internal class CommandDeleteAll(private val imapStore: ImapStore) {

    @Throws(MessagingException::class)
    fun deleteAll(folderServerId: String) {
        val remoteFolder = imapStore.getFolder(folderServerId)
        if (!remoteFolder.exists()) {
            return
        }

        try {
            remoteFolder.open(Folder.OPEN_MODE_RW)
            remoteFolder.setFlags(setOf(Flag.DELETED), true)
        } finally {
            remoteFolder.close()
        }
    }
}
