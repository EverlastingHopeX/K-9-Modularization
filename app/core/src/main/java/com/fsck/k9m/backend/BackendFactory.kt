package com.fsck.k9m.backend

import com.fsck.k9m.Account
import com.fsck.k9m.backend.api.Backend
import com.fsck.k9m.mail.ServerSettings

interface BackendFactory {
    fun createBackend(account: Account): Backend

    fun decodeStoreUri(storeUri: String): ServerSettings
    fun createStoreUri(serverSettings: ServerSettings): String

    val transportUriPrefix: String
    fun decodeTransportUri(transportUri: String): ServerSettings
    fun createTransportUri(serverSettings: ServerSettings): String
}
