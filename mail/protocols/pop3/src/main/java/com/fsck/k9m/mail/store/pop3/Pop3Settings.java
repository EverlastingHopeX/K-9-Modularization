package com.fsck.k9m.mail.store.pop3;


import com.fsck.k9m.mail.AuthType;
import com.fsck.k9m.mail.ConnectionSecurity;


interface Pop3Settings {
    String getHost();

    int getPort();

    ConnectionSecurity getConnectionSecurity();

    AuthType getAuthType();

    String getUsername();

    String getPassword();

    String getClientCertificateAlias();
}
