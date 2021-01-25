package com.fsck.k9m_m.mailstore

import com.fsck.k9m_m.CoreResourceProvider
import com.fsck.k9m_m.message.extractors.AttachmentInfoExtractor
import com.fsck.k9m_m.message.html.HtmlProcessorFactory
import com.fsck.k9m_m.message.html.HtmlSettings

class MessageViewInfoExtractorFactory(
        private val attachmentInfoExtractor: AttachmentInfoExtractor,
        private val htmlProcessorFactory: HtmlProcessorFactory,
        private val resourceProvider: CoreResourceProvider
) {
    fun create(settings: HtmlSettings): MessageViewInfoExtractor {
        val htmlProcessor = htmlProcessorFactory.create(settings)
        return MessageViewInfoExtractor(attachmentInfoExtractor, htmlProcessor, resourceProvider)
    }
}
