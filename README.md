Prefs+
======

Extension for Android preferences that creates additional preference types including `EditIntPreference`, `EditFloatPreference`, and `IntListPreference`.

Values entered by the user are validated, parsed, and stored as the correct primitive type in `SharedPreferences`.

Displays the current value in the summary (subtitle) of each preference view.

## Example

### preferences.xml
```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">

    <com.mapzen.prefsplus.EditIntPreference
        android:key="integer_key"
        android:title="Integer"
        android:defaultValue="0" />

    <com.mapzen.prefsplus.EditFloatPreference
        android:key="float_key"
        android:title="Float"
        android:defaultValue="0" />

    <com.mapzen.prefsplus.IntListPreference
        android:key="integery_list_key"
        android:title="Integer List"
        android:entries="@array/int_list_entries"
        android:entryValues="@array/int_list_values"
        android:defaultValue="0" />

</PreferenceScreen>
```

### EditIntPreference

![EditIntPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/screenshot_0.png)

### EditFloatPreference

![EditFloatPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/screenshot_1.png)

### IntListPreference

![IntListPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/screenshot_2.png)

### PreferenceFragment

![IntListPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/screenshot_3.png)
