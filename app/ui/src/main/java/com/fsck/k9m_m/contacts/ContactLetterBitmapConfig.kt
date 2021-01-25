package com.fsck.k9m_m.contacts

import android.content.Context
import android.util.TypedValue
import android.view.ContextThemeWrapper
import com.fsck.k9m_m.K9
import com.fsck.k9m_m.ui.R
import com.fsck.k9m_m.ui.Theme
import com.fsck.k9m_m.ui.ThemeManager

class ContactLetterBitmapConfig(context: Context, themeManager: ThemeManager) {
    val hasDefaultBackgroundColor: Boolean = !K9.isColorizeMissingContactPictures
    val useDarkTheme = themeManager.appTheme == Theme.DARK
    val defaultBackgroundColor: Int

    init {
        defaultBackgroundColor = if (hasDefaultBackgroundColor) {
            val outValue = TypedValue()
            val themedContext = ContextThemeWrapper(context, themeManager.appThemeResourceId)
            themedContext.theme.resolveAttribute(R.attr.contactPictureFallbackDefaultBackgroundColor, outValue, true)
            outValue.data
        } else {
            0
        }
    }
}
