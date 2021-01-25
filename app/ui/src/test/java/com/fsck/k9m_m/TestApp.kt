package com.fsck.k9m_m

import android.app.Application
import com.fsck.k9m_m.preferences.InMemoryStoragePersister
import com.fsck.k9m_m.preferences.StoragePersister
import com.fsck.k9m_m.storage.storageModule
import com.nhaarman.mockito_kotlin.mock
import org.koin.dsl.module.applicationContext

class TestApp : Application() {
    override fun onCreate() {
        Core.earlyInit(this)

        super.onCreate()
        DI.start(this, Core.coreModules + storageModule + testModule)

        K9.init(this)
        Core.init(this)
    }
}

val testModule = applicationContext {
    bean { AppConfig(emptyList()) }
    bean { mock<CoreResourceProvider>() }
    bean { InMemoryStoragePersister() as StoragePersister }
}
