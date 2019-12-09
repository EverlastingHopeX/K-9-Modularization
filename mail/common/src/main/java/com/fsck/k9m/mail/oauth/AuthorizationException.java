package com.fsck.k9m.mail.oauth;

public class AuthorizationException extends Exception {
    public AuthorizationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AuthorizationException(String detailMessage) {
        super(detailMessage);
    }
}
