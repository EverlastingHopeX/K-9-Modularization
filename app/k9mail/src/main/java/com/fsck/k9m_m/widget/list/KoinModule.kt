package com.fsck.k9m_m.widget.list

import org.koin.dsl.module.applicationContext

val messageListWidgetModule = applicationContext {
    bean { MessageListWidgetUpdateListener(get()) }
}
