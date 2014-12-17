package com.mapzen.prefsplus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.robolectric.Robolectric.application;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class EditTextPlusPreferenceTest {
    private EditTextPlusPreference editTextPlusPreference;

    @Before
    public void setUp() throws Exception {
        editTextPlusPreference = new EditTextPlusPreference(application);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertThat(editTextPlusPreference).isNotNull();
    }

    @Test
    public void persistString_shouldSetValueAsSummary() throws Exception {
        editTextPlusPreference.persistString("test");
        assertThat(editTextPlusPreference).hasSummary("test");
    }
}
