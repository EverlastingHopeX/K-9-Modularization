package com.fsck.k9m_m.fragment

import org.koin.dsl.module.applicationContext

val fragmentModule = applicationContext {
    bean { SortTypeToastProvider() }
}
