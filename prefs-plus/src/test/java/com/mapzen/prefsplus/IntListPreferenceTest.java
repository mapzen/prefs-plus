package com.mapzen.prefsplus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import android.content.SharedPreferences;
import android.content.res.TestTypedArray;
import android.preference.PreferenceManager;
import android.preference.TestPreferenceManager;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.robolectric.Robolectric.application;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class IntListPreferenceTest {
    private IntListPreference intListPreference;
    private SharedPreferences prefs;

    @Before
    public void setUp() throws Exception {
        intListPreference = new IntListPreference(Robolectric.application);
        intListPreference.setKey("key");
        intListPreference.setPersistent(true);
        prefs = PreferenceManager.getDefaultSharedPreferences(application);
        intListPreference.onAttachedToHierarchy(new TestPreferenceManager(prefs));
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertThat(intListPreference).isNotNull();
    }

    @Test
    public void getPersistedString_shouldReturnDefaultValue() throws Exception {
        assertThat(intListPreference.getPersistedString("0")).isEqualTo("0");
    }

    @Test
    public void getPersistedString_shouldReturnPersistedIntAsString() throws Exception {
        prefs.edit().putInt("key", 1).commit();
        intListPreference.persistInt(1);
        assertThat(intListPreference.getPersistedString("0")).isEqualTo("1");
    }

    @Test
    public void persistString_shouldPersistValueAsInt() throws Exception {
        intListPreference.persistString("1");
        assertThat(intListPreference.getPersistedInt(0)).isEqualTo(1);
    }

    @Test
    public void persistString_shouldLogErrorForInvalidValue() throws Exception {
        intListPreference.persistString("NAN");
        assertThat(ShadowLog.getLogs().get(0).msg).contains("Unable to parse preference value");
    }

    @Test
    public void persistString_shouldDisplaySummaryAlertForInvalidValue() throws Exception {
        intListPreference.persistString("NAN");
        assertThat(intListPreference).hasSummary("Error! Invalid value");
    }

    @Test
    public void persistString_shouldSetValueAsSummary() throws Exception {
        intListPreference.persistString("1");
        assertThat(intListPreference).hasSummary("1");
    }

    @Test
    public void onGetDefaultValue_shouldConvertHexValue() throws Exception {
        TestTypedArray typedArray = new TestTypedArray();
        typedArray.putString(0, "0x00000001");
        assertThat(intListPreference.onGetDefaultValue(typedArray, 0)).isEqualTo("1");
    }

    @Test
    public void onGetDefaultValue_shouldSetValueAsSummary() throws Exception {
        TestTypedArray typedArray = new TestTypedArray();
        typedArray.putString(0, "1");
        intListPreference.onGetDefaultValue(typedArray, 0);
        assertThat(intListPreference).hasSummary("1");
    }
}
