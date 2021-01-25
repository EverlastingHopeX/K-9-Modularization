package com.fsck.k9m_m.controller

import android.content.Context
import com.fsck.k9m_m.Account
import com.fsck.k9m_m.AccountStats
import com.fsck.k9m_m.Preferences
import com.fsck.k9m_m.mail.MessagingException
import com.fsck.k9m_m.mailstore.LocalStoreProvider
import com.fsck.k9m_m.search.AccountSearchConditions
import com.fsck.k9m_m.search.LocalSearch
import com.fsck.k9m_m.search.SearchAccount

interface AccountStatsCollector {
    @Throws(MessagingException::class)
    fun getStats(account: Account): AccountStats?

    fun getSearchAccountStats(searchAccount: SearchAccount): AccountStats
}

internal class DefaultAccountStatsCollector(
        private val context: Context,
        private val accountSearchConditions: AccountSearchConditions,
        private val localStoreProvider: LocalStoreProvider
) : AccountStatsCollector {
    private val preferences = Preferences.getPreferences(context)


    override fun getStats(account: Account): AccountStats? {
        if (!account.isAvailable(context)) {
            return null
        }

        val localStore = localStoreProvider.getInstance(account)

        val search = LocalSearch()
        accountSearchConditions.excludeSpecialFolders(account, search)
        accountSearchConditions.limitToDisplayableFolders(account, search)

        return localStore.getAccountStats(search)
    }

    override fun getSearchAccountStats(searchAccount: SearchAccount): AccountStats {
        val search = searchAccount.relatedSearch
        val accounts = getAccountsFromLocalSearch(search)

        val aggregatedAccountStats = AccountStats()
        for (account in accounts) {
            val accountStats = localStoreProvider.getInstance(account).getAccountStats(search)
            aggregatedAccountStats.unreadMessageCount += accountStats.unreadMessageCount
            aggregatedAccountStats.flaggedMessageCount += accountStats.flaggedMessageCount
        }

        return aggregatedAccountStats
    }

    private fun getAccountsFromLocalSearch(search: LocalSearch): List<Account> {
        return if (search.searchAllAccounts()) {
            preferences.accounts
        } else {
            preferences.accounts.filter { it.uuid in search.accountUuids }
        }
    }
}
