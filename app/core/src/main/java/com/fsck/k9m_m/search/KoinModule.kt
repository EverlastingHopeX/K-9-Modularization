package com.fsck.k9m_m.search

import org.koin.dsl.module.applicationContext

val searchModule = applicationContext {
    bean { AccountSearchConditions() }
}
