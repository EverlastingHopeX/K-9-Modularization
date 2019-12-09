package com.fsck.k9m.mail.transport;


import java.util.Collections;

import com.fsck.k9m.mail.K9MailLib;
import com.fsck.k9m.mail.Message;
import com.fsck.k9m.mail.MessagingException;
import com.fsck.k9m.mail.Transport;
import com.fsck.k9m.mail.ssl.TrustManagerFactory;
import com.fsck.k9m.mail.store.StoreConfig;
import com.fsck.k9m.mail.store.webdav.WebDavStore;
import com.fsck.k9m.mail.store.webdav.WebDavStoreSettings;
import timber.log.Timber;

public class WebDavTransport extends Transport {
    private WebDavStore store;

    public WebDavTransport(TrustManagerFactory trustManagerFactory, WebDavStoreSettings serverSettings, StoreConfig storeConfig) {
        store = new WebDavStore(trustManagerFactory, serverSettings, storeConfig);

        if (K9MailLib.isDebug())
            Timber.d(">>> New WebDavTransport creation complete");
    }

    @Override
    public void open() throws MessagingException {
        if (K9MailLib.isDebug())
            Timber.d( ">>> open called on WebDavTransport ");

        store.getHttpClient();
    }

    @Override
    public void close() {
    }

    @Override
    public void sendMessage(Message message) throws MessagingException {
        store.sendMessages(Collections.singletonList(message));
    }

    public void checkSettings() throws MessagingException {
        store.checkSettings();
    }
}
