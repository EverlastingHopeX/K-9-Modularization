package com.fsck.k9m_m.storage

import com.fsck.k9m_m.mailstore.LockableDatabase
import com.fsck.k9m_m.mailstore.MigrationsHelper
import com.fsck.k9m_m.mailstore.SchemaDefinitionFactory

class K9SchemaDefinitionFactory : SchemaDefinitionFactory {
    override val databaseVersion = StoreSchemaDefinition.DB_VERSION

    override fun createSchemaDefinition(migrationsHelper: MigrationsHelper): LockableDatabase.SchemaDefinition {
        return StoreSchemaDefinition(migrationsHelper)
    }
}
