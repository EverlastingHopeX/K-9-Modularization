package com.fsck.k9m_m.helper

object EmailHelper {
    @JvmStatic
    fun getLocalPartFromEmailAddress(email: String): String? {
        val index = email.lastIndexOf('@')
        return if (index == -1 || index == email.lastIndex) null else email.substring(0, index)
    }

    @JvmStatic
    fun getDomainFromEmailAddress(email: String): String? {
        val index = email.lastIndexOf('@')
        return if (index == -1 || index == email.lastIndex) null else email.substring(index + 1)
    }
}
