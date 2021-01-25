package com.fsck.k9m_m.mail.store.imap;

import java.io.IOException;

interface UntaggedHandler {
    void handleAsyncUntaggedResponse(ImapResponse response) throws IOException;
}
