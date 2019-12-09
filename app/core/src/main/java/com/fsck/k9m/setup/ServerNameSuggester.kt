package com.fsck.k9m.setup

import com.fsck.k9m.preferences.Protocols


class ServerNameSuggester {
    fun suggestServerName(serverType: String, domainPart: String): String = when (serverType) {
        Protocols.IMAP -> "imap.$domainPart"
        Protocols.SMTP -> "smtp.$domainPart"
        Protocols.WEBDAV -> "exchange.$domainPart"
        Protocols.POP3 -> "pop3.$domainPart"
        else -> throw AssertionError("Missed case: $serverType")
    }
}
