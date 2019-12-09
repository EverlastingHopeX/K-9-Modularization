package com.fsck.k9m.autocrypt

interface AutocryptStringProvider {
    fun transferMessageSubject(): String
    fun transferMessageBody(): String
}
