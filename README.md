Prefs+
======

An extension for Android preferences that creates additional preference types including `EditIntPreference`, `EditFloatPreference`, and `IntListPreference`.

Values entered by the user are validated, parsed, and stored as the correct primitive type in `SharedPreferences`.

The current value is displayed in the summary (subtitle) of each preference view.

This project also provides an extended version of `EditTextPreference` called `EditTextPlusPreference` that displays the current value in the summary for string preferences too.

## Usage

### preferences.xml
```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">

    <com.mapzen.prefsplus.EditIntPreference
        android:key="@string/integer_key"
        android:title="Integer"
        android:dialogTitle="Enter an integer value"
        android:defaultValue="42" />

    <com.mapzen.prefsplus.EditFloatPreference
        android:key="@string/float_key"
        android:title="Float"
        android:dialogTitle="Enter a float value"
        android:defaultValue="3.14159" />

    <com.mapzen.prefsplus.IntListPreference
        android:key="@string/integer_list_key"
        android:title="Integer List"
        android:dialogTitle="Select from the following list"
        android:entries="@array/int_list_entries"
        android:entryValues="@array/int_list_values"
        android:defaultValue="1" />

    <com.mapzen.prefsplus.EditTextPlusPreference
        android:key="@string/text_key"
        android:title="Text"
        android:dialogTitle="Enter a string"
        android:defaultValue="default" />

</PreferenceScreen>
```

### PreferenceFragment

![PreferenceFragment](https://github.com/mapzen/prefs-plus/blob/master/screenshots/prefs.png)

### EditIntPreference

![EditIntPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/int.png)

### EditFloatPreference

![EditFloatPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/float.png)

### IntListPreference

![IntListPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/int-list.png)

### EditTextPlusPreference

![EditTextPlusPreference](https://github.com/mapzen/prefs-plus/blob/master/screenshots/text.png)

## Install

### Download Jar

Download the [latest JAR][1].

### Maven

Include dependency using Maven.

```xml
<dependency>
  <groupId>com.mapzen.android</groupId>
  <artifactId>prefs-plus</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle

Include dependency using Gradle.

```groovy
compile 'com.mapzen.android:prefs-plus:1.0.0'
```

[1]: http://search.maven.org/remotecontent?filepath=com/mapzen/android/prefs-plus/1.0.0/prefs-plus-1.0.0.jar
