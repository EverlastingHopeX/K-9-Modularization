package com.fsck.k9m.message.quote

import org.koin.dsl.module.applicationContext

val quoteModule = applicationContext {
    factory { QuoteHelper(get()) }
    factory { TextQuoteCreator(get(), get()) }
}
