package com.mapzen.prefsplus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class EditFloatPreferenceTest {
    private EditFloatPreference editFloatPreference;

    @Before
    public void setUp() throws Exception {
        editFloatPreference = new EditFloatPreference(Robolectric.application);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertThat(editFloatPreference).isNotNull();
    }
}
