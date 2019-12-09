package com.fsck.k9m.mail.store.imap;


import java.io.IOException;
import java.util.List;

import com.fsck.k9m.mail.MessagingException;


interface ImapSearcher {
    List<ImapResponse> search() throws IOException, MessagingException;
}
