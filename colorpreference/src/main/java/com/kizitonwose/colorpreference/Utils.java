package com.kizitonwose.colorpreference;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Kizito Nwose
 */

public class Utils {
    @NonNull
    public static AppCompatActivity resolveContext(Context context) {
        if (context instanceof Activity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextWrapper) {
            return resolveContext(((ContextWrapper) context).getBaseContext());
        }
        // How should this ever happen ?
        throw new RuntimeException("Cannot get context");
    }
}
