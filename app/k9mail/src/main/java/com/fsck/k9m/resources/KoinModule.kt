package com.fsck.k9m.resources

import com.fsck.k9m.CoreResourceProvider
import com.fsck.k9m.autocrypt.AutocryptStringProvider
import org.koin.dsl.module.applicationContext

val resourcesModule = applicationContext {
    bean { K9CoreResourceProvider(get()) as CoreResourceProvider }
    bean { K9AutocryptStringProvider(get()) as AutocryptStringProvider}
}
