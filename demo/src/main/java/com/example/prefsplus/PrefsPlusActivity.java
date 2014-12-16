package com.example.prefsplus;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class PrefsPlusActivity extends Activity {
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_lost, container, false);
        }
    }
}
