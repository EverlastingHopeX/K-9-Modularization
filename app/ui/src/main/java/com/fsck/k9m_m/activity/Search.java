package com.fsck.k9m_m.activity;


public class Search extends MessageList {
    @Override
    public void onStart() {
        searchStatusManager.setActive(true);
        super.onStart();
    }

    @Override
    public void onStop() {
        searchStatusManager.setActive(false);
        super.onStop();
    }

    @Override
    protected boolean isDrawerEnabled() {
        return false;
    }
}
