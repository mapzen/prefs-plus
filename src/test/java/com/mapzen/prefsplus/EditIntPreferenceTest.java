package com.mapzen.prefsplus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.preference.TestPreferenceManager;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.robolectric.Robolectric.application;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class EditIntPreferenceTest {
    private EditIntPreference editIntPreference;
    private SharedPreferences prefs;

    @Before
    public void setUp() throws Exception {
        editIntPreference = new EditIntPreference(application);
        editIntPreference.setKey("key");
        editIntPreference.setPersistent(true);
        prefs = PreferenceManager.getDefaultSharedPreferences(application);
        editIntPreference.onAttachedToHierarchy(new TestPreferenceManager(prefs));
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertThat(editIntPreference).isNotNull();
    }

    @Test
    public void singleArgumentConstructor_shouldHaveInputTypeNumber() throws Exception {
        editIntPreference = new EditIntPreference(application);
        assertThat(editIntPreference.getEditText()).hasInputType(TYPE_CLASS_NUMBER);
    }

    @Test
    public void twoArgumentConstructor_shouldHaveInputTypeNumber() throws Exception {
        editIntPreference = new EditIntPreference(application, null);
        assertThat(editIntPreference.getEditText()).hasInputType(TYPE_CLASS_NUMBER);
    }

    @Test
    public void threeArgumentConstructor_shouldHaveInputTypeNumber() throws Exception {
        editIntPreference = new EditIntPreference(application, null, 0);
        assertThat(editIntPreference.getEditText()).hasInputType(TYPE_CLASS_NUMBER);
    }

    @Test
    public void getPersistedString_shouldReturnDefaultValue() throws Exception {
        assertThat(editIntPreference.getPersistedString("0")).isEqualTo("0");
    }

    @Test
    public void getPersistedString_shouldReturnPersistedIntAsString() throws Exception {
        prefs.edit().putInt("key", 1).commit();
        editIntPreference.persistInt(1);
        assertThat(editIntPreference.getPersistedString("0")).isEqualTo("1");
    }

    @Test
    public void persistString_shouldPersistValueAsInt() throws Exception {
        editIntPreference.persistString("1");
        assertThat(editIntPreference.getPersistedInt(0)).isEqualTo(1);
    }

    @Test
    public void persistString_shouldLogErrorForInvalidValue() throws Exception {
        editIntPreference.persistString("NAN");
        assertThat(ShadowLog.getLogs().get(0).msg).contains("Unable to parse preference value");
    }
}
