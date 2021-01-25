package com.fsck.k9m_m.view

import org.koin.dsl.module.applicationContext

val viewModule = applicationContext {
    bean { WebViewConfigProvider(get()) }
}
