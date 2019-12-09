package com.fsck.k9m.autocrypt

import org.koin.dsl.module.applicationContext

val autocryptModule = applicationContext {
    bean { AutocryptTransferMessageCreator(get()) }
    bean { AutocryptDraftStateHeaderParser() }
}