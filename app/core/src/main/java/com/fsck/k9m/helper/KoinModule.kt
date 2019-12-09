package com.fsck.k9m.helper

import org.koin.dsl.module.applicationContext

val helperModule = applicationContext {
    bean { ClipboardManager(get()) }
}
