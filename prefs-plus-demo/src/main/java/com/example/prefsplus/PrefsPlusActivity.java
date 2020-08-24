package com.example.prefsplus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrefsPlusActivity extends AppCompatActivity {
    private PrefsPlusFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentManager fragmentManager = getFragmentManager();
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
        switch (item.getItemId()) {
            case R.id.settings:
                onSettingsOptionSelected();
                break;
        }

        return true;
    }

    private void onSettingsOptionSelected() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public static class PrefsPlusFragment extends Fragment {
        public static final String TAG = PrefsPlusFragment.class.getSimpleName();

        private TextView integerTextView;
        private TextView floatTextView;
        private TextView integerListTextView;
        private TextView textTextView;

        private int intValue;
        private float floatValue;
        private int intListValue;
        private String textValue;

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
            prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            res = getResources();
            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            intValue = prefs.getInt(res.getString(R.string.integer_key),
                    res.getInteger(R.integer.integer_default_value));
            floatValue = prefs.getFloat(res.getString(R.string.float_key),
                    res.getInteger(R.integer.integer_default_value));
            intListValue = prefs.getInt(res.getString(R.string.integer_list_key),
                    res.getInteger(R.integer.integer_default_value));
            textValue = prefs.getString(res.getString(R.string.text_key),
                    res.getString(R.string.text_default_value));

            integerTextView.setText(Integer.toString(intValue));
            floatTextView.setText(Float.toString(floatValue));
            integerListTextView.setText(Integer.toString(intListValue));
            textTextView.setText(textValue);
        }
    }
}
