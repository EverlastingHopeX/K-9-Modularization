package com.fsck.k9m.ui.helper

import com.fsck.k9m.K9
import com.fsck.k9m.message.html.HtmlSettings
import com.fsck.k9m.ui.Theme
import com.fsck.k9m.ui.ThemeManager

class HtmlSettingsProvider(private val themeManager: ThemeManager) {
    fun createForMessageView() = HtmlSettings(
            useDarkMode = themeManager.messageViewTheme == Theme.DARK,
            useFixedWidthFont = K9.isUseMessageViewFixedWidthFont
    )

    fun createForMessageCompose() = HtmlSettings(
            useDarkMode = themeManager.messageComposeTheme == Theme.DARK,
            useFixedWidthFont = false
    )
}
