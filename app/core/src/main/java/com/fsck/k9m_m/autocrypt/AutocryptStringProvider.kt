package com.fsck.k9m_m.autocrypt

interface AutocryptStringProvider {
    fun transferMessageSubject(): String
    fun transferMessageBody(): String
}
