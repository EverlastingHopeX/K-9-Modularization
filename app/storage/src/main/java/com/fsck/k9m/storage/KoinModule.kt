package com.fsck.k9m.storage

import com.fsck.k9m.mailstore.SchemaDefinitionFactory
import org.koin.dsl.module.applicationContext

val storageModule = applicationContext {
    bean { K9SchemaDefinitionFactory() as SchemaDefinitionFactory }
}
