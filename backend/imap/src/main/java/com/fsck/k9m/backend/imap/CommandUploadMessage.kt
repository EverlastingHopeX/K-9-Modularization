package com.fsck.k9m.backend.imap


import com.fsck.k9m.mail.Folder
import com.fsck.k9m.mail.Message
import com.fsck.k9m.mail.store.imap.ImapStore


internal class CommandUploadMessage(private val imapStore: ImapStore) {

    fun uploadMessage(folderServerId: String, message: Message): String? {
        val folder = imapStore.getFolder(folderServerId)
        try {
            folder.open(Folder.OPEN_MODE_RW)

            val localUid = message.uid
            val uidMap = folder.appendMessages(listOf(message))

            return uidMap[localUid]
        } finally {
            folder.close()
        }
    }
}
