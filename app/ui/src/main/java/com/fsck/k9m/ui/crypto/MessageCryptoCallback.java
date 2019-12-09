package com.fsck.k9m.ui.crypto;


import android.content.Intent;
import android.content.IntentSender;

import com.fsck.k9m.mailstore.MessageCryptoAnnotations;


public interface MessageCryptoCallback {
    void onCryptoHelperProgress(int current, int max);
    void onCryptoOperationsFinished(MessageCryptoAnnotations annotations);
    void startPendingIntentForCryptoHelper(IntentSender si, int requestCode, Intent fillIntent,
            int flagsMask, int flagValues, int extraFlags);
}
