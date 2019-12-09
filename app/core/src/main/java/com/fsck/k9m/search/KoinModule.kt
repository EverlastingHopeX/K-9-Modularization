package com.fsck.k9m.search

import org.koin.dsl.module.applicationContext

val searchModule = applicationContext {
    bean { AccountSearchConditions() }
}
