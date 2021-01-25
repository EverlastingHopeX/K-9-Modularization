package com.fsck.k9m_m.crypto

import android.content.ContentValues
import com.fsck.k9m_m.mail.Message
import com.fsck.k9m_m.message.extractors.PreviewResult

interface EncryptionExtractor {
    fun extractEncryption(message: Message): EncryptionResult?
}

data class EncryptionResult(
        val encryptionType: String,
        val attachmentCount: Int,
        val previewResult: PreviewResult = PreviewResult.encrypted(),
        val textForSearchIndex: String? = null,
        val extraContentValues: ContentValues? = null
)
