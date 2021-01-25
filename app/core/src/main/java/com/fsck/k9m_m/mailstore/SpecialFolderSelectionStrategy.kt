package com.fsck.k9m_m.mailstore

/**
 * Implements the automatic special folder selection strategy.
 */
class SpecialFolderSelectionStrategy {
    fun selectSpecialFolder(folders: List<Folder>, type: FolderType): Folder? {
        return folders.firstOrNull { folder -> folder.type == type }
    }
}
