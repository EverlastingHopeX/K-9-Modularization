package com.fsck.k9m.widget.unread

import android.content.Context
import android.content.Intent
import com.fsck.k9m.Account
import com.fsck.k9m.Preferences
import com.fsck.k9m.R
import com.fsck.k9m.activity.MessageList
import com.fsck.k9m.controller.MessagingController
import com.fsck.k9m.search.LocalSearch
import com.fsck.k9m.search.SearchAccount

class UnreadWidgetDataProvider(
        private val context: Context,
        private val preferences: Preferences,
        private val messagingController: MessagingController
) {
    fun loadUnreadWidgetData(configuration: UnreadWidgetConfiguration): UnreadWidgetData? = with(configuration) {
        if (SearchAccount.UNIFIED_INBOX == accountUuid) {
            loadSearchAccountData(configuration)
        } else if (folderServerId != null) {
            loadFolderData(configuration)
        } else {
            loadAccountData(configuration)
        }
    }

    private fun loadSearchAccountData(configuration: UnreadWidgetConfiguration): UnreadWidgetData {
        val searchAccount = getSearchAccount(configuration.accountUuid)
        val title = searchAccount.description

        val stats = messagingController.getSearchAccountStatsSynchronous(searchAccount, null)
        val unreadCount = stats.unreadMessageCount

        val clickIntent = MessageList.intentDisplaySearch(context, searchAccount.relatedSearch, false, true, true)

        return UnreadWidgetData(configuration, title, unreadCount, clickIntent)
    }

    private fun getSearchAccount(accountUuid: String): SearchAccount = when (accountUuid) {
        SearchAccount.UNIFIED_INBOX -> SearchAccount.createUnifiedInboxAccount()
        else -> throw AssertionError("SearchAccount expected")
    }

    private fun loadAccountData(configuration: UnreadWidgetConfiguration): UnreadWidgetData? {
        val account = preferences.getAccount(configuration.accountUuid) ?: return null

        val title = account.description

        val stats = messagingController.getAccountStats(account)
        val unreadCount = stats.unreadMessageCount

        val clickIntent = getClickIntentForAccount(account)

        return UnreadWidgetData(configuration, title, unreadCount, clickIntent)
    }

    private fun getClickIntentForAccount(account: Account): Intent {
        val folderServerId = account.autoExpandFolder ?: account.inboxFolder
        return getClickIntentForFolder(account, folderServerId)
    }

    private fun loadFolderData(configuration: UnreadWidgetConfiguration): UnreadWidgetData? {
        val accountUuid = configuration.accountUuid
        val account = preferences.getAccount(accountUuid) ?: return null
        val folderServerId = configuration.folderServerId ?: return null

        val accountName = account.description
        //FIXME: Use folder display name instead of folderServerId for title
        val title = context.getString(R.string.unread_widget_title, accountName, folderServerId)

        val unreadCount = messagingController.getFolderUnreadMessageCount(account, folderServerId)

        val clickIntent = getClickIntentForFolder(account, folderServerId)

        return UnreadWidgetData(configuration, title, unreadCount, clickIntent)
    }

    private fun getClickIntentForFolder(account: Account, folderServerId: String): Intent {
        val search = LocalSearch(folderServerId)
        search.addAllowedFolder(folderServerId)
        search.addAccountUuid(account.uuid)

        val clickIntent = MessageList.intentDisplaySearch(context, search, false, true, true)
        clickIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        return clickIntent
    }
}
