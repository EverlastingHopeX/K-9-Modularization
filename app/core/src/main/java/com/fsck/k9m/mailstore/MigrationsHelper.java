package com.fsck.k9m.mailstore;


import java.util.List;

import android.content.Context;

import com.fsck.k9m.Account;
import com.fsck.k9m.Preferences;
import com.fsck.k9m.mail.Flag;
import com.fsck.k9m.preferences.Storage;


/**
 * Helper to allow accessing classes and methods that aren't visible or accessible to the 'migrations' package
 */
public interface MigrationsHelper {
    LocalStore getLocalStore();
    Storage getStorage();
    Preferences getPreferences();
    Account getAccount();
    Context getContext();
    String serializeFlags(List<Flag> flags);
}
