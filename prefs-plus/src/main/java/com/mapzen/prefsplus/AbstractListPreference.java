package com.mapzen.prefsplus;

import android.content.Context;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

/**
 * Wrapper for {@link android.preference.ListPreference} that exposes additional methods.
 */
@SuppressWarnings("deprecation")
public abstract class AbstractListPreference extends ListPreference {
    public AbstractListPreference(Context context) {
        super(context);
    }

    public AbstractListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        super.onAttachedToHierarchy(preferenceManager);
    }

    @Override
    protected boolean persistInt(int value) {
        return super.persistInt(value);
    }

    @Override
    protected int getPersistedInt(int defaultReturnValue) {
        return super.getPersistedInt(defaultReturnValue);
    }
}
