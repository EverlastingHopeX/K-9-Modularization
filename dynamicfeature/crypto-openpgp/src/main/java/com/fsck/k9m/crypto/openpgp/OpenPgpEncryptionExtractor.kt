package com.fsck.k9m.crypto.openpgp

import com.fsck.k9m.crypto.EncryptionExtractor
import com.fsck.k9m.crypto.EncryptionResult
import com.fsck.k9m.mail.Message
import com.fsck.k9m.message.extractors.TextPartFinder

class OpenPgpEncryptionExtractor internal constructor(
        private val encryptionDetector: EncryptionDetector
) : EncryptionExtractor {

    override fun extractEncryption(message: Message): EncryptionResult? {
        return if (encryptionDetector.isEncrypted(message)) {
            EncryptionResult(ENCRYPTION_TYPE, 0)
        } else {
            null
        }
    }


    companion object {
        const val ENCRYPTION_TYPE = "openpgp"

        @JvmStatic
        fun newInstance(): OpenPgpEncryptionExtractor {
            val textPartFinder = TextPartFinder()
            val encryptionDetector = EncryptionDetector(textPartFinder)
            return OpenPgpEncryptionExtractor(encryptionDetector)
        }
    }
}
