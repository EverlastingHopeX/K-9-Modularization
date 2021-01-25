package com.fsck.k9m_m.mailstore

data class OutboxState(
        val sendState: SendState,
        val numberOfSendAttempts: Int,
        val sendError: String?,
        val sendErrorTimestamp: Long
)
