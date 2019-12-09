package com.fsck.k9m.widget.list

import android.content.Context
import com.fsck.k9m.Account
import com.fsck.k9m.controller.SimpleMessagingListener
import com.fsck.k9m.core.BuildConfig
import com.fsck.k9m.mail.Message
import timber.log.Timber

class MessageListWidgetUpdateListener(private val context: Context) : SimpleMessagingListener() {

    private fun updateMailListWidget() {
        try {
            MessageListWidgetProvider.triggerMessageListWidgetUpdate(context)
        } catch (e: RuntimeException) {
            if (BuildConfig.DEBUG) {
                throw e
            } else {
                Timber.e(e, "Error while updating message list widget")
            }
        }
    }

    override fun synchronizeMailboxRemovedMessage(account: Account, folderServerId: String, messageServerId: String) {
        updateMailListWidget()
    }

    override fun messageDeleted(account: Account, folderServerId: String, messageServerId: String) {
        updateMailListWidget()
    }

    override fun synchronizeMailboxNewMessage(account: Account, folderServerId: String, message: Message) {
        updateMailListWidget()
    }

    override fun folderStatusChanged(account: Account, folderServerId: String, unreadMessageCount: Int) {
        updateMailListWidget()
    }
}
