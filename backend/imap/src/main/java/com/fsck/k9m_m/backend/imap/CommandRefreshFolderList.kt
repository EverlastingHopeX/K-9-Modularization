package com.fsck.k9m_m.backend.imap


import com.fsck.k9m_m.backend.api.BackendStorage
import com.fsck.k9m_m.backend.api.FolderInfo
import com.fsck.k9m_m.mail.store.imap.ImapStore


internal class CommandRefreshFolderList(
        private val backendStorage: BackendStorage,
        private val imapStore: ImapStore
) {
    fun refreshFolderList() {
        val foldersOnServer = imapStore.personalNamespaces
        val oldFolderServerIds = backendStorage.getFolderServerIds()

        val foldersToCreate = mutableListOf<FolderInfo>()
        for (folder in foldersOnServer) {
            if (folder.serverId !in oldFolderServerIds) {
                foldersToCreate.add(FolderInfo(folder.serverId, folder.name, folder.type))
            } else {
                backendStorage.changeFolder(folder.serverId, folder.name, folder.type)
            }
        }
        backendStorage.createFolders(foldersToCreate)

        val newFolderServerIds = foldersOnServer.map { it.serverId }
        val removedFolderServerIds = oldFolderServerIds - newFolderServerIds
        backendStorage.deleteFolders(removedFolderServerIds)
    }
}
