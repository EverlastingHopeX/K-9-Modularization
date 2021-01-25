package com.fsck.k9m_m.mailstore

import com.fsck.k9m_m.mailstore.LockableDatabase.SchemaDefinition

interface SchemaDefinitionFactory {
    val databaseVersion: Int

    fun createSchemaDefinition(migrationsHelper: MigrationsHelper): SchemaDefinition
}
