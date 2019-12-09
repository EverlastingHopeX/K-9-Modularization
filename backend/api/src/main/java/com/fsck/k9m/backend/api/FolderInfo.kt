package com.fsck.k9m.backend.api

import com.fsck.k9m.mail.Folder

data class FolderInfo(val serverId: String, val name: String, val type: Folder.FolderType)
