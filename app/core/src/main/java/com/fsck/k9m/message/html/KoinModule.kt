package com.fsck.k9m.message.html

import org.koin.dsl.module.applicationContext

val htmlModule = applicationContext {
    bean { HtmlProcessorFactory(get(), get()) }
    bean { HtmlSanitizer() }
    bean { DisplayHtmlFactory() }
}
