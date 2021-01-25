package com.fsck.k9m_m.mail.store;


import com.fsck.k9m_m.mail.NetworkType;

public interface StoreConfig {
    boolean isSubscribedFoldersOnly();
    boolean useCompression(NetworkType type);

    String getInboxFolder();
    String getOutboxFolder();
    String getDraftsFolder();

    int getMaximumAutoDownloadMessageSize();

    boolean isAllowRemoteSearch();
    boolean isRemoteSearchFullText();

    boolean isPushPollOnConnect();

    int getDisplayCount();

    int getIdleRefreshMinutes();
}
