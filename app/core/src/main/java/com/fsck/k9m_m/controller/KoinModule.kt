package com.fsck.k9m_m.controller

import org.koin.dsl.module.applicationContext

val controllerModule = applicationContext {
    bean { MessagingController(get(), get(), get(), get(), get(), get(), get(), get(), get("controllerExtensions")) }
    bean { DefaultAccountStatsCollector(get(), get(), get()) as AccountStatsCollector }
}
