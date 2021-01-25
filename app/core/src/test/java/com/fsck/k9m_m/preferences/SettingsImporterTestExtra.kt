@file:JvmName("MessagingControllerTestExtra")
package com.fsck.k9m_m.preferences

import com.fsck.k9m_m.Account
import com.fsck.k9m_m.backend.BackendFactory
import com.fsck.k9m_m.backend.BackendManager
import com.fsck.k9m_m.backend.api.Backend
import com.fsck.k9m_m.backend.imap.ImapStoreUriCreator
import com.fsck.k9m_m.backend.imap.ImapStoreUriDecoder
import com.fsck.k9m_m.mail.ServerSettings
import com.fsck.k9m_m.mail.transport.smtp.SmtpTransportUriCreator
import com.fsck.k9m_m.mail.transport.smtp.SmtpTransportUriDecoder
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.loadKoinModules

fun setUpBackendManager() {
    val backendFactory = object : BackendFactory {
        override val transportUriPrefix = "smtp"

        override fun createBackend(account: Account): Backend {
            throw UnsupportedOperationException("not implemented")
        }

        override fun decodeStoreUri(storeUri: String): ServerSettings {
            return ImapStoreUriDecoder.decode(storeUri)
        }

        override fun createStoreUri(serverSettings: ServerSettings): String {
            return ImapStoreUriCreator.create(serverSettings)
        }

        override fun decodeTransportUri(transportUri: String): ServerSettings {
            return SmtpTransportUriDecoder.decodeSmtpUri(transportUri)
        }

        override fun createTransportUri(serverSettings: ServerSettings): String {
            return SmtpTransportUriCreator.createSmtpUri(serverSettings)
        }
    }

    loadKoinModules(applicationContext {
        bean { BackendManager(mapOf("imap" to backendFactory)) }
    })
}
