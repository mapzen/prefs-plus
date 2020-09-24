package com.mapzen.prefsplusx;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;

/**
 * {@link androidx.preference.EditTextPreference} that saves float values to
 * {@link android.content.SharedPreferences}.
 */
public class EditFloatPreference extends EditTextPreference {
    public static final String TAG = EditFloatPreference.class.getSimpleName();
    private float minimumValue=Float.MIN_VALUE;
    private float maximumValue=Float.MAX_VALUE;
    private boolean hasCustomMinMaxValue=false;

    public EditFloatPreference(Context context) {
        super(context);
        init(null,0);
    }

    public EditFloatPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public EditFloatPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs,defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        if(attrs!=null){
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs, R.styleable.EditIntPreference, defStyle, defStyle);
            if(a.hasValue(R.styleable.EditFloatPreference_minFloatValue) && a.hasValue(R.styleable.EditFloatPreference_maxFloatValue)){
                Log.d(TAG,"has value lol");
            }else{
                Log.d(TAG,"has no value lol");
            }
            minimumValue=a.getFloat(R.styleable.EditFloatPreference_minFloatValue,Float.MIN_VALUE);
            maximumValue=a.getFloat(R.styleable.EditFloatPreference_maxFloatValue,Float.MAX_VALUE);
            Log.d(TAG,"Min & max"+minimumValue+" "+maximumValue);
            a.recycle();
        }
        super.setOnBindEditTextListener(new OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setRawInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL);
            }
        });
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        if(getSharedPreferences().contains(getKey())) {
            float floatValue = getPersistedFloat(0f);
            return String.valueOf(floatValue);
        } else {
            return defaultReturnValue;
        }
    }

    @Override
    protected boolean persistString(String value) {
        float floatValue;
        try {
            floatValue = Float.parseFloat(value);
            if(floatValue<minimumValue || floatValue>maximumValue){
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
        if(minimumValue!= Float.MIN_VALUE && maximumValue !=Float.MAX_VALUE){
            final String allowedRange="["+minimumValue+","+maximumValue+"]";
            setSummary(Float.toString(floatValue)+" "+allowedRange);
        }else{
            setSummary(Float.toString(floatValue));
        }
        return persistFloat(floatValue);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Float.valueOf(a.getString(index)).toString();
    }

    /*@Override
    public boolean shouldDisableDependents(){
        return super.shouldDisableDependents();
    }*/
}
