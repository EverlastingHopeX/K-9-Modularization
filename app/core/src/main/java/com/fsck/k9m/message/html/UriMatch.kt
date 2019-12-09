package com.fsck.k9m.message.html

data class UriMatch(
        val startIndex: Int,
        val endIndex: Int,
        val uri: CharSequence
)
