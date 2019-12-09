package com.fsck.k9m.message.html

class DisplayHtmlFactory {
    fun create(settings: HtmlSettings): DisplayHtml {
        return DisplayHtml(settings)
    }
}
