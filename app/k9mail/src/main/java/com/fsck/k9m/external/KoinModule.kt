package com.fsck.k9m.external

import org.koin.dsl.module.applicationContext

val externalModule = applicationContext {
    bean { BroadcastSenderListener(get()) }
}
