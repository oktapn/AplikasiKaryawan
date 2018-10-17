package com.example.okta.aplikasikaryawan.widget;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Okta on 10/17/2018.
 */

public class SharePreferencesManager {
    private static final String MyPREFERENCES = "MyPrefs";
    private static final String USER = "userKey";
    private static final String PASS = "passKey";

    public static void setLoginPreference(Context context, String user, String pass) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USER, user);
        editor.putString(PASS, pass);
        editor.commit();
    }

    public static String getUser(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getString(USER, "");
    }

    public static void logout(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static String getPass(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getString(PASS, "");
    }

}
