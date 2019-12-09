package com.fsck.k9m.autodiscovery

interface ConnectionSettingsDiscovery {
    fun discover(email: String): ConnectionSettings?
}
