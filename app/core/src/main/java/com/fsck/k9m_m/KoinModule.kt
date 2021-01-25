package com.fsck.k9m_m

import android.content.Context
import com.fsck.k9m_m.helper.Contacts
import com.fsck.k9m_m.mail.power.PowerManager
import com.fsck.k9m_m.mail.ssl.DefaultTrustedSocketFactory
import com.fsck.k9m_m.mail.ssl.LocalKeyStore
import com.fsck.k9m_m.mail.ssl.TrustManagerFactory
import com.fsck.k9m_m.mail.ssl.TrustedSocketFactory
import com.fsck.k9m_m.mailstore.LocalStoreProvider
import com.fsck.k9m_m.mailstore.StorageManager
import com.fsck.k9m_m.power.TracingPowerManager
import org.koin.dsl.module.applicationContext

val mainModule = applicationContext {
    bean { Preferences.getPreferences(get()) }
    bean { get<Context>().resources }
    bean { StorageManager.getInstance(get()) }
    bean { LocalStoreProvider() }
    bean { TracingPowerManager.getPowerManager(get()) as PowerManager }
    bean { Contacts.getInstance(get()) }
    bean { LocalKeyStore.createInstance(get()) }
    bean { TrustManagerFactory.createInstance(get()) }
    bean { LocalKeyStoreManager(get()) }
    bean { DefaultTrustedSocketFactory(get(), get()) as TrustedSocketFactory }
    bean { Clock.INSTANCE }
}
