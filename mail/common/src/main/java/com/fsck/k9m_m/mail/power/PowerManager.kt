package com.fsck.k9m_m.mail.power

interface PowerManager {
    fun newWakeLock(tag: String): WakeLock
}
