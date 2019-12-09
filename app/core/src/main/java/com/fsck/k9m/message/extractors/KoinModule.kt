package com.fsck.k9m.message.extractors

import org.koin.dsl.module.applicationContext

val extractorModule = applicationContext {
    bean { AttachmentInfoExtractor(get()) }
}
