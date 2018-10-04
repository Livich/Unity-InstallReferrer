package com.livich.unity_installreferrer;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class InstallReferrer {
    private static final String PREF_FILE = "com.livich.unity_installreferrer";
    public static final String KEY_REFERRER = "referrer";
    public static InstallReferrer self = new InstallReferrer();
    public static String referrer = null;

    public void getInstallReferrer(Context context, boolean isTest, StringObjectCallback callback){
        referrer = load(context, KEY_REFERRER, "");
        if(referrer == null || referrer.equals("")){
            if(!isTest) {
                callback.onResult("");
                return;
            }else{
                callback.onResult("https://play.google.com/store/apps/details?id=com.mypackage&referrer=utm_source%3Dmobisoc%26utm_content%3D{transaction_id}%26utm_campaign%3D1");
                return;
            }
        }
        callback.onResult(referrer);
    }

    public static void save(Context ctx, String key, String value){
        SharedPreferences sPref = ctx.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(key, value);
        ed.commit();
    }

    public static String load(Context ctx, String key, String def){
        SharedPreferences sPref = ctx.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sPref.getString(key, def);
    }
}
