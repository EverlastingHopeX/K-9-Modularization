package com.fsck.k9m_m.ui.account

import androidx.lifecycle.LiveData
import com.fsck.k9m_m.Account
import com.fsck.k9m_m.AccountsChangeListener
import com.fsck.k9m_m.Preferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AccountsLiveData(val preferences: Preferences) : LiveData<List<Account>>(), AccountsChangeListener {

    private fun loadAccountsAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val accounts = async {
                loadAccounts()
            }

            value = accounts.await()
        }
    }

    override fun onAccountsChanged() {
        loadAccountsAsync()
    }

    private fun loadAccounts(): List<Account> {
        return preferences.accounts
    }

    override fun onActive() {
        super.onActive()
        preferences.addOnAccountsChangeListener(this)
        loadAccountsAsync()
    }

    override fun onInactive() {
        super.onInactive()
        preferences.removeOnAccountsChangeListener(this)
    }
}
