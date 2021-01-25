package com.fsck.k9m_m.storage

import com.fsck.k9m_m.mailstore.SchemaDefinitionFactory
import org.koin.dsl.module.applicationContext

val storageModule = applicationContext {
    bean { K9SchemaDefinitionFactory() as SchemaDefinitionFactory }
}
