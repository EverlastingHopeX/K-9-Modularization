package com.fsck.k9m.mail.power

interface WakeLock {
    fun acquire(timeout: Long)
    fun acquire()
    fun setReferenceCounted(counted: Boolean)
    fun release()
}
