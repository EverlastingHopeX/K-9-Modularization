package com.fsck.k9m.account

import org.koin.dsl.module.applicationContext

val accountModule = applicationContext {
    factory { AccountRemover(get(), get(), get()) }
    factory { BackgroundAccountRemover(get()) }
}
