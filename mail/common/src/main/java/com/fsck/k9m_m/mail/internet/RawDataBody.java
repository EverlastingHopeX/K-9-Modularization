package com.fsck.k9m_m.mail.internet;


import com.fsck.k9m_m.mail.Body;


/**
 * See {@link MimeUtility#decodeBody(Body)}
 */
public interface RawDataBody extends Body {
    String getEncoding();
}
