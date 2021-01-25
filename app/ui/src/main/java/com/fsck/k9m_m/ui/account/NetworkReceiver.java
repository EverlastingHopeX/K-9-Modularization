package com.fsck.k9m_m.ui.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

import com.fsck.k9m_m.ui.settings.account.DialogActivity;

public class NetworkReceiver extends BroadcastReceiver {

    private static boolean isFirst=true;
    private static boolean isOpen=false;

    private static String SWITCH_STATE_CHANGE="com.fsck.k9m_m.SWITCH_STATE_CHANGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("wifitest",intent.getAction());
        if(SWITCH_STATE_CHANGE.equals(intent.getAction())){
            isOpen=intent.getBooleanExtra("state",false);
            Log.d("wifitest","isOpen:"+isOpen);
        }
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            Parcelable parcelableExtra = intent
                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                boolean isConnected = state == NetworkInfo.State.CONNECTED;
                if(!isOpen) {
                    if (isConnected) {
                        Log.d("wifitest", "isFirst: " + isFirst);
                        if (isFirst) {
                            Log.d("wifitest", "Connected");
                            Intent i = new Intent(context, DialogActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //                        i.putExtra(EXTRA_ACCOUNT_UUID,);
                            context.startActivity(i);
                            isFirst = false;
                        } else {
                            isFirst = true;
                        }
                    } else {
                        Log.d("wifitest", "not connected");
                    }
                }
            }
        }

    }

}
