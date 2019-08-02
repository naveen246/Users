package com.example.users.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREF_NAME = "com.example.users.PREF_NAME";

    private static PreferencesManager INSTANCE;
    private final SharedPreferences sharedPrefs;

    private PreferencesManager(Context context) {
        sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesManager(context);
        }
        return INSTANCE;
    }

    public void setLongValue(String key, long value) {
        sharedPrefs.edit()
                .putLong(key, value)
                .apply();
    }

    public long getLongValue(String key) {
        return sharedPrefs.getLong(key, 0);
    }

    public void remove(String key) {
        sharedPrefs.edit()
                .remove(key)
                .apply();
    }

    public boolean clear() {
        return sharedPrefs.edit()
                .clear()
                .commit();
    }
}
