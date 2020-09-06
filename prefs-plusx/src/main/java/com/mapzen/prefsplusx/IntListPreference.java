package com.mapzen.prefsplusx;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.ListPreference;

import java.util.Arrays;

/**
 * {@link androidx.preference.ListPreference} that saves integer values to
 * {@link android.content.SharedPreferences}.
 * Do not forget to set the entry values in your xml file, example:
 * android:entries="@array/int_list_entries"
 * By default, each entry of this array maps to the integer value it can be indexed with -
 * For example, entry 0 maps to '0', entry 1 maps to '1' and so on
 * However, you can also specify the entry values manually - for example, element 0 should map to '100' or similar
 * In this case use
 * android:entryValues="@array/int_list_values"
 * In the old variant from mapzen, you'd have to always declare both the entries and entryValue array
 */

public class IntListPreference extends ListPreference {
    public static final String TAG = IntListPreference.class.getSimpleName();

    public IntListPreference(Context context) {
        super(context);
        setDefaultEntryValuesIfNull();
    }

    public IntListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDefaultEntryValuesIfNull();
    }

    // If there are no entry values set populate it with a default array that holds
    // as many entries as the entry array and maps element 0 -> 0, element 1 -> 1 and more
    private void setDefaultEntryValuesIfNull(){
        if(getEntries()==null){
            // You probably forgot to set the entry values
            // example,in your xml,do :  android:entries="@array/int_list_entries"
            // mapzen's version would also crash in this case
            throw new RuntimeException("List preference has no entries");
        }
        /*if(getEntryValues()!=null){
            Log.d(TAG,"Has entry values "+ Arrays.toString(getEntryValues()));
        }else{
            Log.d(TAG,"Has no entry values");
        }*/
        if(getEntryValues()==null){
            //Log.d(TAG,"Setting default entry values");
            final int size=getEntries().length;
            CharSequence[] defaultEntryValues =new CharSequence[size];
            for(int i=0;i<size;i++){
                defaultEntryValues[i]=Integer.toString(i);
            }
            setEntryValues(defaultEntryValues);
        }
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
        } catch (NumberFormatException e) {
            Log.e(TAG, "Unable to parse preference value: " + value);
            setSummary("Invalid value");
            return false;
        }

        final int valueIndex = Arrays.asList(getEntryValues()).indexOf(value);
        setSummary(getEntries()[valueIndex]);
        return persistInt(intValue);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Integer.decode(a.getString(index)).toString();
    }
}
