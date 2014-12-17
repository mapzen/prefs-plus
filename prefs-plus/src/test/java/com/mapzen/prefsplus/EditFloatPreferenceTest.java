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

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.robolectric.Robolectric.application;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class EditFloatPreferenceTest {
    private EditFloatPreference editFloatPreference;
    private SharedPreferences prefs;

    @Before
    public void setUp() throws Exception {
        editFloatPreference = new EditFloatPreference(Robolectric.application);
        editFloatPreference.setKey("key");
        editFloatPreference.setPersistent(true);
        prefs = PreferenceManager.getDefaultSharedPreferences(application);
        editFloatPreference.onAttachedToHierarchy(new TestPreferenceManager(prefs));
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertThat(editFloatPreference).isNotNull();
    }

    @Test
    public void singleArgumentConstructor_shouldHaveInputTypeDecimalNumber() throws Exception {
        editFloatPreference = new EditFloatPreference(application);
        assertThat(editFloatPreference.getEditText())
                .hasInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Test
    public void twoArgumentConstructor_shouldHaveInputTypeDecimalNumber() throws Exception {
        editFloatPreference = new EditFloatPreference(application, null);
        assertThat(editFloatPreference.getEditText())
                .hasInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Test
    public void threeArgumentConstructor_shouldHaveInputTypeDecimalNumber() throws Exception {
        editFloatPreference = new EditFloatPreference(application, null, 0);
        assertThat(editFloatPreference.getEditText())
                .hasInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Test
    public void getPersistedString_shouldReturnDefaultValue() throws Exception {
        assertThat(editFloatPreference.getPersistedString("0.0")).isEqualTo("0.0");
    }

    @Test
    public void getPersistedString_shouldReturnPersistedFloatAsString() throws Exception {
        prefs.edit().putFloat("key", 1.0f).commit();
        editFloatPreference.persistFloat(1.0f);
        assertThat(editFloatPreference.getPersistedString("0.0")).isEqualTo("1.0");
    }

    @Test
    public void persistString_shouldPersistValueAsInt() throws Exception {
        editFloatPreference.persistString("1.0");
        assertThat(editFloatPreference.getPersistedFloat(0.0f)).isEqualTo(1.0f);
    }

    @Test
    public void persistString_shouldLogErrorForInvalidValue() throws Exception {
        editFloatPreference.persistString("NAN");
        assertThat(ShadowLog.getLogs().get(0).msg).contains("Unable to parse preference value");
    }

    @Test
    public void persistString_shouldDisplaySummaryAlertForInvalidValue() throws Exception {
        editFloatPreference.persistString("NAN");
        assertThat(editFloatPreference).hasSummary("Invalid value");
    }

    @Test
    public void persistString_shouldSetValueAsSummary() throws Exception {
        editFloatPreference.persistString("1.0");
        assertThat(editFloatPreference).hasSummary("1.0");
    }

    @Test
    public void persistString_shouldConvertIntToFloat() throws Exception {
        editFloatPreference.persistString("1");
        assertThat(editFloatPreference).hasSummary("1.0");
    }

    @Test
    public void onGetDefaultValue_shouldFormatFloat() throws Exception {
        TestTypedArray typedArray = new TestTypedArray();
        typedArray.putString(0, "1");
        assertThat(editFloatPreference.onGetDefaultValue(typedArray, 0)).isEqualTo("1.0");
    }
}
