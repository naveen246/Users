package com.example.users.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREF_NAME = "com.example.app.PREF_NAME";
    private static final String KEY_VALUE = "com.example.app.KEY_VALUE";

    private static PreferencesManager INSTANCE;
    private final SharedPreferences preferences;

    private PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesManager(context);
        }
        return INSTANCE;
    }

    public void setValue(long value) {
        preferences.edit()
                .putLong(KEY_VALUE, value)
                .commit();
    }

    public long getValue() {
        return preferences.getLong(KEY_VALUE, 0);
    }

    public void remove(String key) {
        preferences.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return preferences.edit()
                .clear()
                .commit();
    }
}
