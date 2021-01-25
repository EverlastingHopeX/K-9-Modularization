package com.fsck.k9m_m.autodiscovery

interface ConnectionSettingsDiscovery {
    fun discover(email: String): ConnectionSettings?
}
