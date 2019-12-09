package com.fsck.k9m.autodiscovery.providersxml

import android.content.Context
import android.content.res.XmlResourceParser
import com.fsck.k9m.autodiscovery.R

class ProvidersXmlProvider(private val context: Context) {
    fun getXml(): XmlResourceParser {
        return context.resources.getXml(R.xml.providers)
    }
}
