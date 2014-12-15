package android.preference;

import android.content.Context;
import android.util.AttributeSet;

public class NumberListPreference extends ListPreference {
    public NumberListPreference(Context context) {
        super(context);
    }

    public NumberListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        super.onAttachedToHierarchy(preferenceManager);
    }

    @Override
    public boolean persistInt(int value) {
        return super.persistInt(value);
    }

    @Override
    public int getPersistedInt(int defaultReturnValue) {
        return super.getPersistedInt(defaultReturnValue);
    }

    @Override
    public boolean persistFloat(float value) {
        return super.persistFloat(value);
    }

    @Override
    public float getPersistedFloat(float defaultReturnValue) {
        return super.getPersistedFloat(defaultReturnValue);
    }
}
