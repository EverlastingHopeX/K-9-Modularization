package com.fsck.k9m_m.external.remotecontrol;
/**
 *
 * @author Daniel I. Applebaum
 * The interface to implement in order to accept the arrays containing the UUIDs and descriptions of
 * the accounts configured in K-9 Mail.  Should be passed to fetchAccounts(Context, K9AccountReceptor)
 */
public interface K9AccountReceptor {
    void accounts(String[] uuids, String[] descriptions);
}
