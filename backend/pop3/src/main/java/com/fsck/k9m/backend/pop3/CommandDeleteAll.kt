package com.fsck.k9m.backend.pop3


import com.fsck.k9m.mail.Flag
import com.fsck.k9m.mail.Folder
import com.fsck.k9m.mail.MessagingException
import com.fsck.k9m.mail.store.pop3.Pop3Store


internal class CommandDeleteAll(private val pop3Store: Pop3Store) {

    @Throws(MessagingException::class)
    fun deleteAll(folderServerId: String) {
        val remoteFolder = pop3Store.getFolder(folderServerId)
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
