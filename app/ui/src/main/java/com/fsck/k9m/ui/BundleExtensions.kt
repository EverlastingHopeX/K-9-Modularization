package com.fsck.k9m.ui

import android.os.Bundle

fun <T : Enum<T>> Bundle.putEnum(key: String, value: T) {
    putString(key, value.name)
}

inline fun <reified T : Enum<T>> Bundle.getEnum(key: String, defaultValue: T): T {
    val value = getString(key) ?: return defaultValue
    return enumValueOf(value)
}
