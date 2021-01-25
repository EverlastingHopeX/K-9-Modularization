package com.fsck.k9m_m.mail

class AuthenticationFailedException @JvmOverloads constructor(
        message: String,
        throwable: Throwable? = null,
        val messageFromServer: String? = null
) : MessagingException(message, throwable) {
    val isMessageFromServerAvailable = messageFromServer != null
}
