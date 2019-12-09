package com.fsck.k9m.mail.power

interface PowerManager {
    fun newWakeLock(tag: String): WakeLock
}
