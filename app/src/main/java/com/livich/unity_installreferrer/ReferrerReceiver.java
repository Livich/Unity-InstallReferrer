package com.livich.unity_installreferrer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ReferrerReceiver extends BroadcastReceiver {
    private static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
    private static final String EXTRA_REFERRER = "referrer";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Log.e("ReferrerReceiver", "Intent is null");
            return;
        }
        if (!ACTION_INSTALL_REFERRER.equals(intent.getAction())) {
            Log.e("ReferrerReceiver", "Wrong action! Expected: " + ACTION_INSTALL_REFERRER + " but was: " + intent.getAction());
            return;
        }
        Bundle extras = intent.getExtras();
        if (intent.getExtras() == null || !intent.hasExtra(EXTRA_REFERRER)) {
            Log.e("ReferrerReceiver", "No data in intent");
            return;
        }

        InstallReferrer.referrer = intent.getStringExtra(EXTRA_REFERRER);
        InstallReferrer.save(context, InstallReferrer.KEY_REFERRER, InstallReferrer.referrer);
    }
}
