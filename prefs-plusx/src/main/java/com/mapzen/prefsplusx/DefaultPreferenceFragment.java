package com.mapzen.prefsplusx;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import static android.content.Context.MODE_PRIVATE;

public class DefaultPreferenceFragment extends PreferenceFragmentCompat {
    private final String sharedPreferencesName;
    private final PreferenceScreen preferenceScreen;

    DefaultPreferenceFragment(PreferenceScreen preferenceScreen, String sharedPreferencesName) {
        this.preferenceScreen = preferenceScreen;
        this.sharedPreferencesName=sharedPreferencesName;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        PreferenceManager prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName(sharedPreferencesName);
        prefMgr.setSharedPreferencesMode(MODE_PRIVATE);
        setPreferenceScreen(preferenceScreen);
    }

}
