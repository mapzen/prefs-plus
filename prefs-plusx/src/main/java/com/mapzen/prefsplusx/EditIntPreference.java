package com.mapzen.prefsplusx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;

import java.util.Objects;

import static android.text.InputType.TYPE_CLASS_NUMBER;

/**
 * {@link androidx.preference.EditTextPreference} that saves int values to
 * {@link android.content.SharedPreferences}.
 */
public class EditIntPreference extends EditTextPreference {
    public static final String TAG = EditIntPreference.class.getSimpleName();
    private int minimumValue=Integer.MIN_VALUE;
    private int maximumValue=Integer.MAX_VALUE;
    // True if a custom range (aka min / max value) has been set
    private boolean hasCustomAllowedRange=false;

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
            final boolean minValueSet=a.hasValue(R.styleable.EditIntPreference_minIntValue);
            final boolean maxValueSet=a.hasValue(R.styleable.EditIntPreference_maxIntValue);
            //if(minValueSet!=maxValueSet)
            //    throw new RuntimeException("Make Sure to declare both a minimum and maximum value when using a custom Range");
            if(minValueSet && maxValueSet){
                minimumValue=a.getInteger(R.styleable.EditIntPreference_minIntValue,Integer.MIN_VALUE);
                maximumValue=a.getInteger(R.styleable.EditIntPreference_maxIntValue,Integer.MAX_VALUE);
                hasCustomAllowedRange=true;
                Log.d(TAG,"has custom allowed range "+getAllowedRangeReadable());
            }
            a.recycle();
        }
        super.setOnBindEditTextListener(new OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setRawInputType(TYPE_CLASS_NUMBER);
            }
        });
    }

    private String getAllowedRangeReadable(){
        return "["+minimumValue+",...,"+maximumValue+"]";
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected String getPersistedString(String defaultReturnValue) {
        if(getSharedPreferences().contains(getKey())) {
            /*try{
                getSharedPreferences().getInt(getKey(),0);
            }catch (ClassCastException e){
                e.printStackTrace();
                getSharedPreferences().edit().remove(getKey()).commit();
            }*/
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
        } catch (NumberFormatException e) {
            Log.e(TAG, "Unable to parse preference value: " + value);
            setSummary("Invalid value");
            return false;
        }
        if(hasCustomAllowedRange){
            if(intValue<minimumValue || intValue>maximumValue){
                final String allowedRange=getAllowedRangeReadable();
                Log.e(TAG, "Value is not in range: "+allowedRange+" " + value);
                setSummary("Invalid value. Select "+allowedRange);
                return false;
            }
        }
        setSummary(value);
        return persistInt(intValue);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Integer.decode(Objects.requireNonNull(a.getString(index))).toString();
    }

}
