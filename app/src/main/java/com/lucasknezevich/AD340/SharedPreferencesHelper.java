package com.lucasknezevich.AD340;

import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper (SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public boolean saveEntry(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);

        return editor.commit();
    }

    public String getEntry(String key) {
        return sharedPreferences.getString(key, "");
    }

}
