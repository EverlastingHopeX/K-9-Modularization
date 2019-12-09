package com.fsck.k9m.storage

import android.app.Application
import com.fsck.k9m.AppConfig
import com.fsck.k9m.Core
import com.fsck.k9m.CoreResourceProvider
import com.fsck.k9m.DI
import com.fsck.k9m.K9
import com.fsck.k9m.crypto.EncryptionExtractor
import com.fsck.k9m.preferences.K9StoragePersister
import com.fsck.k9m.preferences.StoragePersister
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
    bean { mock<EncryptionExtractor>() }
    bean { K9StoragePersister(get()) as StoragePersister }
}
