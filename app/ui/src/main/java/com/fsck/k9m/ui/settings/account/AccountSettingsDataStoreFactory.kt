package com.fsck.k9m.ui.settings.account

import com.fsck.k9m.Account
import com.fsck.k9m.Preferences
import com.fsck.k9m.job.K9JobManager
import java.util.concurrent.ExecutorService

class AccountSettingsDataStoreFactory(
        private val preferences: Preferences,
        private val jobManager: K9JobManager,
        private val executorService: ExecutorService
) {
    fun create(account: Account): AccountSettingsDataStore {
        return AccountSettingsDataStore(preferences, executorService, account, jobManager)
    }
}
