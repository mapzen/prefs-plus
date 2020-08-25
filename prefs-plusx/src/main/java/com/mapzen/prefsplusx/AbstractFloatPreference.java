package com.mapzen.prefsplusx;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceManager;

/**
 * Wrapper for {@link android.preference.EditTextPreference} that exposes additional methods.
 */
public abstract class AbstractFloatPreference extends EditTextPreference {
    public AbstractFloatPreference(Context context) {
        super(context);
    }

    public AbstractFloatPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractFloatPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        super.onAttachedToHierarchy(preferenceManager);
    }

    @Override
    protected boolean persistFloat(float value) {
        return super.persistFloat(value);
    }

    @Override
    protected float getPersistedFloat(float defaultReturnValue) {
        return super.getPersistedFloat(defaultReturnValue);
    }

    /*@Override
    protected boolean persistString(String value){
        throw new RuntimeException("Only persist the type we are using here");
    }*/
}
