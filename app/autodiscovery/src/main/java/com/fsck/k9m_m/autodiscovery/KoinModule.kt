package com.fsck.k9m_m.autodiscovery

import com.fsck.k9m_m.autodiscovery.providersxml.ProvidersXmlDiscovery
import com.fsck.k9m_m.autodiscovery.providersxml.ProvidersXmlProvider
import org.koin.dsl.module.applicationContext

val autodiscoveryModule = applicationContext {
    factory { ProvidersXmlProvider(get()) }
    factory { ProvidersXmlDiscovery(get(), get()) }
}
