package com.fsck.k9m_m.ui.folders

import android.content.res.Resources
import com.fsck.k9m_m.ui.R
import com.fsck.k9m_m.mailstore.Folder
import com.fsck.k9m_m.mailstore.FolderType

class FolderNameFormatter(private val resources: Resources) {
    fun displayName(folder: Folder): String = when (folder.type) {
        FolderType.INBOX -> resources.getString(R.string.special_mailbox_name_inbox)
        FolderType.OUTBOX -> resources.getString(R.string.special_mailbox_name_outbox)
        else -> folder.name
    }
}
