package com.fsck.k9m_m.resources

import android.content.Context
import com.fsck.k9m_m.autocrypt.AutocryptStringProvider
import com.fsck.k9m_m.R

class K9AutocryptStringProvider(private val context: Context) : AutocryptStringProvider {
    override fun transferMessageSubject(): String = context.getString(R.string.ac_transfer_msg_subject)
    override fun transferMessageBody(): String = context.getString(R.string.ac_transfer_msg_body)
}
