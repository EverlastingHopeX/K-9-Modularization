package com.fsck.k9m.ui.settings.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.fsck.k9m.Account;
import com.fsck.k9m.activity.K9Activity;
import com.fsck.k9m.ui.R;

import com.fsck.k9m.Preferences;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;

public class DialogActivity extends K9Activity {
    private static final String EXTRA_ACCOUNT_UUID = "accountUuid";
    private static final String EXTRA_CONFIG_ENCRYPT="config_encrypt";

    private SplitInstallManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager=SplitInstallManagerFactory.create(this);

        setContentView(R.layout.activity_dialog);
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You've connnected to a public wifi, do you want to enable encryption?").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                        String moduleName="dynamicfeature";
                        if(manager.getInstalledModules().contains(moduleName)){
//                            Toast.makeText("module installed", Toast.LENGTH_LONG).show();
                        }
                        else{
//                            Toast.makeText(this,"module not installed",Toast.LENGTH_LONG).show();

                            SplitInstallRequest request=SplitInstallRequest.newBuilder()
                                    .addModule(moduleName)
                                    .build();
                            manager.startInstall(request)
                                    .addOnCompleteListener(aVoid->toastAndLog("module installed"))
                                    .addOnFailureListener(aVoid->toastAndLog("error loading"));
                        }
                        //go to end-to-end encryption
                        Intent i=new Intent(DialogActivity.this,AccountSettingsActivity.class);
                        Account mAccount=Preferences.getPreferences(DialogActivity.this).getDefaultAccount();
                        i.putExtra(EXTRA_ACCOUNT_UUID,mAccount.getUuid());
                        i.putExtra(EXTRA_CONFIG_ENCRYPT,"enable");
                        startActivity(i);
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
//        PreferenceScreen preferenceScreen = null;
//        preferenceScreen.getPreferenceManager().setOnNavigateToScreenListener(new PreferenceManager.OnNavigateToScreenListener() {
//            @Override
//            public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
//                Log.d("dialog", "onNavigateToScreen: ");
//            }
//        });
    }

    private void toastAndLog(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Log.d("dynamic", text);
    }

}
