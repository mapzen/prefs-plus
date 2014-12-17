package com.mapzen.prefsplus;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.NumberListPreference;
import android.util.AttributeSet;
import android.util.Log;

/**
 * {@link android.preference.ListPreference} that saves integer values to
 * {@link android.content.SharedPreferences}.
 */
public class IntListPreference extends NumberListPreference {
    public static final String TAG = IntListPreference.class.getSimpleName();

    public IntListPreference(Context context) {
        super(context);
    }

    public IntListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        if(getSharedPreferences().contains(getKey())) {
            int intValue = getPersistedInt(0);
            return String.valueOf(intValue);
        } else {
            return defaultReturnValue;
        }
    }

    @Override
    protected boolean persistString(String value) {
        int intValue;
        try {
            intValue = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Unable to parse preference value: " + value);
            return true;
        }

        setSummary(value);
        return persistInt(intValue);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        final String s = Integer.decode(a.getString(index)).toString();
        setSummary(s);
        return s;
    }
}
