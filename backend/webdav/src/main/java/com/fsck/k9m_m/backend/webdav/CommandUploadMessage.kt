package com.fsck.k9m_m.backend.webdav


import com.fsck.k9m_m.mail.Folder
import com.fsck.k9m_m.mail.Message
import com.fsck.k9m_m.mail.store.webdav.WebDavStore


internal class CommandUploadMessage(private val webDavStore: WebDavStore) {

    fun uploadMessage(folderServerId: String, message: Message): String? {
        val folder = webDavStore.getFolder(folderServerId)
        try {
            folder.open(Folder.OPEN_MODE_RW)

            folder.appendMessages(listOf(message))

            return null
        } finally {
            folder.close()
        }
    }
}
