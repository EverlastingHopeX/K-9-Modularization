package com.fsck.k9m.mail.store.webdav;

/**
 * Simple data container for passing connection information.
 */
class ConnectionInfo {
    public int statusCode;
    public short requiredAuthType;
    public String guessedAuthUrl;
    public String redirectUrl;
}
