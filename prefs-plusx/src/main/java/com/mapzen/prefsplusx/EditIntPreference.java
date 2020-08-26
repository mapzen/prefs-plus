package com.mapzen.prefsplusx;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;

import static android.text.InputType.TYPE_CLASS_NUMBER;

/**
 * {@link androidx.preference.EditTextPreference} that saves int values to
 * {@link android.content.SharedPreferences}.
 */
public class EditIntPreference extends EditTextPreference {
    public static final String TAG = EditIntPreference.class.getSimpleName();
    private int minimumValue=Integer.MIN_VALUE;
    private int maximumValue=Integer.MAX_VALUE;

    public EditIntPreference(Context context) {
        super(context);
        init(null,0);
    }

    public EditIntPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public EditIntPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs,defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        if(attrs!=null){
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs, R.styleable.EditIntPreference, defStyle, defStyle);
            minimumValue=a.getInteger(R.styleable.EditIntPreference_minIntValue,Integer.MIN_VALUE);
            maximumValue=a.getInteger(R.styleable.EditIntPreference_maxIntValue,Integer.MAX_VALUE);
            Log.d(TAG,"Min & max"+minimumValue+" "+maximumValue);
            a.recycle();
        }
        super.setOnBindEditTextListener(new OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setRawInputType(TYPE_CLASS_NUMBER);
            }
        });
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
            intValue = Integer.parseInt(value);
            if(intValue<minimumValue || intValue>maximumValue){
                final String allowedRange="["+minimumValue+","+maximumValue+"]";
                Log.e(TAG, "Value is not in range: "+allowedRange+" " + value);
                setSummary("Invalid value.Select "+allowedRange);
                return false;
            }
        } catch (NumberFormatException e) {
            Log.e(TAG, "Unable to parse preference value: " + value);
            setSummary("Invalid value");
            return false;
        }
        setSummary(value);
        return persistInt(intValue);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Integer.decode(a.getString(index)).toString();
    }
}
