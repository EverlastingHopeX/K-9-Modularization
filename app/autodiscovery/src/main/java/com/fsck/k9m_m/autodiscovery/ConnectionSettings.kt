package com.fsck.k9m_m.autodiscovery

import com.fsck.k9m_m.mail.ServerSettings

data class ConnectionSettings(val incoming: ServerSettings, val outgoing: ServerSettings)
