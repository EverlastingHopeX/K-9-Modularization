package com.fsck.k9m_m.notification;


import com.fsck.k9m_m.Account;


public class NotificationGroupKeys {
    private static final String NOTIFICATION_GROUP_KEY_PREFIX = "newMailNotifications-";
    
    
    public static String getGroupKey(Account account) {
        return NOTIFICATION_GROUP_KEY_PREFIX + account.getAccountNumber();
    }
}
