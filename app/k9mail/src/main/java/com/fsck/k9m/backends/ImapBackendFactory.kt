package com.fsck.k9m.backends

import android.content.Context
import android.net.ConnectivityManager
import com.fsck.k9m.Account
import com.fsck.k9m.backend.BackendFactory
import com.fsck.k9m.backend.api.Backend
import com.fsck.k9m.backend.imap.ImapBackend
import com.fsck.k9m.backend.imap.ImapStoreUriCreator
import com.fsck.k9m.backend.imap.ImapStoreUriDecoder
import com.fsck.k9m.mail.ServerSettings
import com.fsck.k9m.mail.oauth.OAuth2TokenProvider
import com.fsck.k9m.mail.power.PowerManager
import com.fsck.k9m.mail.ssl.TrustedSocketFactory
import com.fsck.k9m.mail.store.imap.ImapStore
import com.fsck.k9m.mail.transport.smtp.SmtpTransport
import com.fsck.k9m.mail.transport.smtp.SmtpTransportUriCreator
import com.fsck.k9m.mail.transport.smtp.SmtpTransportUriDecoder
import com.fsck.k9m.mailstore.K9BackendStorageFactory

class ImapBackendFactory(
        private val context: Context,
        private val powerManager: PowerManager,
        private val backendStorageFactory: K9BackendStorageFactory,
        private val trustedSocketFactory: TrustedSocketFactory
) : BackendFactory {
    override val transportUriPrefix = "smtp"

    override fun createBackend(account: Account): Backend {
        val accountName = account.displayName
        val backendStorage = backendStorageFactory.createBackendStorage(account)
        val imapStore = createImapStore(account)
        val smtpTransport = createSmtpTransport(account)
        return ImapBackend(accountName, backendStorage, imapStore, powerManager, smtpTransport)
    }

    private fun createImapStore(account: Account): ImapStore {
        val oAuth2TokenProvider: OAuth2TokenProvider? = null
        val serverSettings = ImapStoreUriDecoder.decode(account.storeUri)
        return ImapStore(
                serverSettings,
                account,
                trustedSocketFactory,
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager,
                oAuth2TokenProvider
        )
    }

    private fun createSmtpTransport(account: Account): SmtpTransport {
        val serverSettings = decodeTransportUri(account.transportUri)
        val oauth2TokenProvider: OAuth2TokenProvider? = null
        return SmtpTransport(serverSettings, trustedSocketFactory, oauth2TokenProvider)
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
