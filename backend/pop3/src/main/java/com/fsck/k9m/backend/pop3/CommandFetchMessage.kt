package com.fsck.k9m.backend.pop3


import com.fsck.k9m.mail.FetchProfile
import com.fsck.k9m.mail.Message
import com.fsck.k9m.mail.store.pop3.Pop3Store


internal class CommandFetchMessage(private val pop3Store: Pop3Store) {

    fun fetchMessage(folderServerId: String, messageServerId: String, fetchProfile: FetchProfile): Message {
        val folder = pop3Store.getFolder(folderServerId)
        try {
            val message = folder.getMessage(messageServerId)

            folder.fetch(listOf(message), fetchProfile, null)

            return message
        } finally {
            folder.close()
        }
    }
}
