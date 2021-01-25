package com.fsck.k9m_m.ui.helper

import com.fsck.k9m_m.K9
import com.fsck.k9m_m.message.html.HtmlSettings
import com.fsck.k9m_m.ui.Theme
import com.fsck.k9m_m.ui.ThemeManager

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
