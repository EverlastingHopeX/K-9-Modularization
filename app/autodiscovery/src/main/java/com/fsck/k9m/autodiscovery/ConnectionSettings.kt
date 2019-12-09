package com.fsck.k9m.autodiscovery

import com.fsck.k9m.mail.ServerSettings

data class ConnectionSettings(val incoming: ServerSettings, val outgoing: ServerSettings)
