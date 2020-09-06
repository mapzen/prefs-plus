package com.mapzen.prefsplusx;

import android.os.Bundle;

import androidx.annotation.XmlRes;
import androidx.preference.PreferenceFragmentCompat;

// Takes the resource file in the constructor
// Does nothing special,you can use this one whenever you don't want to declare a new fragment just for an xml preference screen
public class DefaultPreferenceFragment extends PreferenceFragmentCompat {
    private final @XmlRes int resourceFile;

    public DefaultPreferenceFragment(@XmlRes int resourceFile) {
        this.resourceFile=resourceFile;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(resourceFile, rootKey);
    }

}
