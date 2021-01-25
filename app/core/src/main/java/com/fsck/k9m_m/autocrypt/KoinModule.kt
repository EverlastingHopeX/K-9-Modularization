package com.fsck.k9m_m.autocrypt

import org.koin.dsl.module.applicationContext

val autocryptModule = applicationContext {
    bean { AutocryptTransferMessageCreator(get()) }
    bean { AutocryptDraftStateHeaderParser() }
}