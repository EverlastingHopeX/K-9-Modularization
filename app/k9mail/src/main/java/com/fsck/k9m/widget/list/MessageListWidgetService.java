package com.fsck.k9m.widget.list;


import android.content.Intent;
import android.widget.RemoteViewsService;


public class MessageListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MessageListRemoteViewFactory(getApplicationContext());
    }
}