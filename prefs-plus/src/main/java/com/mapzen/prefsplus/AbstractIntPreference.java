package com.mapzen.prefsplus;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

/**
 * Wrapper for {@link android.preference.EditTextPreference} that exposes additional methods.
 */
@SuppressWarnings("deprecation")
public abstract class AbstractIntPreference extends EditTextPreference {
    public AbstractIntPreference(Context context) {
        super(context);
    }

    public AbstractIntPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractIntPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
