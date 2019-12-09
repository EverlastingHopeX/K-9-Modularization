package com.fsck.k9m.mailstore

import com.fsck.k9m.mailstore.LockableDatabase.SchemaDefinition

interface SchemaDefinitionFactory {
    val databaseVersion: Int

    fun createSchemaDefinition(migrationsHelper: MigrationsHelper): SchemaDefinition
}
