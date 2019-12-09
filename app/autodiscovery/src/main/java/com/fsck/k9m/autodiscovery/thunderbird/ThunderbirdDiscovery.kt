package com.fsck.k9m.autodiscovery.thunderbird

import com.fsck.k9m.autodiscovery.ConnectionSettings
import com.fsck.k9m.autodiscovery.ConnectionSettingsDiscovery

class ThunderbirdDiscovery(
        private val fetcher: ThunderbirdAutoconfigFetcher,
        private val parser: ThunderbirdAutoconfigParser
): ConnectionSettingsDiscovery {

    override fun discover(email: String): ConnectionSettings? {
        val autoconfigInputStream = fetcher.fetchAutoconfigFile(email) ?: return null

        return autoconfigInputStream.use {
            parser.parseSettings(it, email)
        }
    }

    override fun toString(): String = "Thunderbird autoconfig"
}
