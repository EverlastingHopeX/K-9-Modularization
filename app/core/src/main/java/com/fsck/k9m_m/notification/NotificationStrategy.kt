package com.fsck.k9m_m.notification

import com.fsck.k9m_m.Account
import com.fsck.k9m_m.mailstore.LocalFolder
import com.fsck.k9m_m.mail.Message

interface NotificationStrategy {

    fun shouldNotifyForMessage(account: Account,
                               localFolder: LocalFolder,
                               message: Message,
                               isOldMessage:Boolean):Boolean
}