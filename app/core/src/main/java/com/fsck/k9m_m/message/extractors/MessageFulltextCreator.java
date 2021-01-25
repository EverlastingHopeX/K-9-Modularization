package com.fsck.k9m_m.message.extractors;


import androidx.annotation.NonNull;

import com.fsck.k9m_m.message.html.HtmlConverter;
import com.fsck.k9m_m.mail.Message;
import com.fsck.k9m_m.mail.Part;
import com.fsck.k9m_m.mail.internet.MessageExtractor;
import com.fsck.k9m_m.mail.internet.MimeUtility;


public class MessageFulltextCreator {
    private static final int MAX_CHARACTERS_CHECKED_FOR_FTS = 200*1024;


    private final TextPartFinder textPartFinder;


    MessageFulltextCreator(TextPartFinder textPartFinder) {
        this.textPartFinder = textPartFinder;
    }

    public static MessageFulltextCreator newInstance() {
        TextPartFinder textPartFinder = new TextPartFinder();
        return new MessageFulltextCreator(textPartFinder);
    }

    public String createFulltext(@NonNull Message message) {
        Part textPart = textPartFinder.findFirstTextPart(message);
        if (textPart == null || hasEmptyBody(textPart)) {
            return null;
        }

        String text = MessageExtractor.getTextFromPart(textPart, MAX_CHARACTERS_CHECKED_FOR_FTS);
        String mimeType = textPart.getMimeType();
        if (!MimeUtility.isSameMimeType(mimeType, "text/html")) {
            return text;
        }

        return HtmlConverter.htmlToText(text);
    }

    private boolean hasEmptyBody(Part textPart) {
        return textPart.getBody() == null;
    }

}
