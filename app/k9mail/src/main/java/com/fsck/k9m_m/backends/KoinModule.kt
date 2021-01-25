package com.fsck.k9m_m.backends

import com.fsck.k9m_m.backend.BackendManager
import org.koin.dsl.module.applicationContext

val backendsModule = applicationContext {
    bean {
        BackendManager(
                mapOf(
                        "imap" to get<ImapBackendFactory>(),
                        "pop3" to get<Pop3BackendFactory>(),
                        "webdav" to get<WebDavBackendFactory>()
                ))
    }
    bean { ImapBackendFactory(get(), get(), get(), get()) }
    bean { Pop3BackendFactory(get(), get()) }
    bean { WebDavBackendFactory(get(), get()) }
}
