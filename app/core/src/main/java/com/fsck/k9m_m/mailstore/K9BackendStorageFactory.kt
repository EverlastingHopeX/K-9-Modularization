package com.fsck.k9m_m.mailstore

import com.fsck.k9m_m.Account
import com.fsck.k9m_m.Preferences

class K9BackendStorageFactory(
        private val preferences: Preferences,
        private val folderRepositoryManager: FolderRepositoryManager,
        private val localStoreProvider: LocalStoreProvider
) {
    fun createBackendStorage(account: Account): K9BackendStorage {
        val folderRepository = folderRepositoryManager.getFolderRepository(account)
        val localStore = localStoreProvider.getInstance(account)
        val specialFolderUpdater = SpecialFolderUpdater(preferences, folderRepository, account)
        return K9BackendStorage(preferences, account, localStore, specialFolderUpdater)
    }
}
