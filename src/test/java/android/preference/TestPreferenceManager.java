package android.preference;

import android.content.SharedPreferences;

public class TestPreferenceManager extends PreferenceManager {
    private SharedPreferences prefs;

    public TestPreferenceManager(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return prefs;
    }

    public SharedPreferences.Editor getEditor() {
        return prefs.edit();
    }
}
