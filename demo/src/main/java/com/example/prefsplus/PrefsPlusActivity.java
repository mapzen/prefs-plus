package com.example.prefsplus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PrefsPlusActivity extends AppCompatActivity {
    private PrefsPlusFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = new PrefsPlusFragment();
        fragmentManager.beginTransaction()
                .add(android.R.id.content, fragment, PrefsPlusFragment.TAG)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            onSettingsOptionSelected();
        }

        return true;
    }

    private void onSettingsOptionSelected() {
        // Either the new or old preference class
        //startActivity(new Intent(this, SettingsActivity.class));
        startActivity(new Intent(this, SettingsActivityX.class));
    }

    public static class PrefsPlusFragment extends Fragment {
        public static final String TAG = PrefsPlusFragment.class.getSimpleName();

        private TextView integerTextView;
        private TextView floatTextView;
        private TextView integerListTextView;
        private TextView textTextView;
        private TextView colorTextView;

        private SharedPreferences prefs;
        private Resources res;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_prefs_plus, container, false);
            integerTextView = (TextView) view.findViewById(R.id.integer_value);
            floatTextView = (TextView) view.findViewById(R.id.float_value);
            integerListTextView = (TextView) view.findViewById(R.id.integer_list_value);
            textTextView = (TextView) view.findViewById(R.id.text_value);
            colorTextView=(TextView) view.findViewById(R.id.tv_color_value);
            prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            res = getResources();
            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            int intValue = prefs.getInt(res.getString(R.string.integer_key),
                    res.getInteger(R.integer.integer_default_value));
            float floatValue = prefs.getFloat(res.getString(R.string.float_key),
                    res.getInteger(R.integer.integer_default_value));
            int intListValue = prefs.getInt(res.getString(R.string.integer_list_key),
                    res.getInteger(R.integer.integer_default_value));
            String textValue = prefs.getString(res.getString(R.string.text_key),
                    res.getString(R.string.text_default_value));
            int colorValue = prefs.getInt(res.getString(R.string.color_key), Color.RED);

            integerTextView.setText(Integer.toString(intValue));
            floatTextView.setText(Float.toString(floatValue));
            integerListTextView.setText(Integer.toString(intListValue));
            textTextView.setText(textValue);
            colorTextView.setBackgroundColor(colorValue);
        }
    }
}
