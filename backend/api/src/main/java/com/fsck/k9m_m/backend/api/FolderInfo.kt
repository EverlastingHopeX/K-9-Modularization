package com.fsck.k9m_m.backend.api

import com.fsck.k9m_m.mail.Folder

data class FolderInfo(val serverId: String, val name: String, val type: Folder.FolderType)
