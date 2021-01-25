package com.fsck.k9m_m.mailstore;


public interface LocalPart {
    String getAccountUuid();
    long getPartId();
    long getSize();
    LocalMessage getMessage();
}
