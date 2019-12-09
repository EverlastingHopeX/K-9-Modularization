package com.fsck.k9m.notification

import com.fsck.k9m.Account
import com.fsck.k9m.mailstore.LocalFolder
import com.fsck.k9m.mail.Message

interface NotificationStrategy {

    fun shouldNotifyForMessage(account: Account,
                               localFolder: LocalFolder,
                               message: Message,
                               isOldMessage:Boolean):Boolean
}