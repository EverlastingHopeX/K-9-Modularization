package com.fsck.k9m_m.mail.ssl;

import com.fsck.k9m_m.mail.MessagingException;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface TrustedSocketFactory {
    Socket createSocket(Socket socket, String host, int port, String clientCertificateAlias)
            throws NoSuchAlgorithmException, KeyManagementException, MessagingException, IOException;
}
