package com.fsck.k9m_m.widget.unread

import com.fsck.k9m_m.Account
import com.fsck.k9m_m.controller.SimpleMessagingListener
import com.fsck.k9m_m.mail.Message
import timber.log.Timber

class UnreadWidgetUpdateListener(private val unreadWidgetUpdater: UnreadWidgetUpdater) : SimpleMessagingListener() {

    private fun updateUnreadWidget() {
        try {
            unreadWidgetUpdater.updateAll()
        } catch (e: Exception) {
            Timber.e(e, "Error while updating unread widget(s)")
        }
    }

    override fun synchronizeMailboxRemovedMessage(account: Account, folderServerId: String, messageServerId: String) {
        updateUnreadWidget()
    }

    override fun messageDeleted(account: Account, folderServerId: String, messageServerId: String) {
        updateUnreadWidget()
    }

    override fun synchronizeMailboxNewMessage(account: Account, folderServerId: String, message: Message) {
        updateUnreadWidget()
    }

    override fun folderStatusChanged(account: Account, folderServerId: String, unreadMessageCount: Int) {
        updateUnreadWidget()
    }
}
