package com.fsck.k9m.mailstore

import com.fsck.k9m.Account

class FolderRepositoryManager(
        private val localStoreProvider: LocalStoreProvider,
        private val specialFolderSelectionStrategy: SpecialFolderSelectionStrategy
) {
    fun getFolderRepository(account: Account) = FolderRepository(localStoreProvider, specialFolderSelectionStrategy, account)
}
