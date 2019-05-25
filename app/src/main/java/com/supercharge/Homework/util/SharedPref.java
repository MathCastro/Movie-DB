package com.supercharge.Homework.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import com.supercharge.Homework.R;

import static android.content.Context.MODE_PRIVATE;

public class SharedPref {

    static public void putString(String search, AppCompatActivity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(activity.getResources().getString(R.string.shared_preferences_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("search", search);
        editor.commit();
    }

    static public String getString(AppCompatActivity activity) {
        SharedPreferences mPrefs = activity.getSharedPreferences(activity.getResources().getString(R.string.shared_preferences_file), MODE_PRIVATE); //add key
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        return mPrefs.getString("search", "");
    }

    static public void clearSharedPref(AppCompatActivity activity) {
        SharedPreferences mPrefs = activity.getSharedPreferences(activity.getResources().getString(R.string.shared_preferences_file), MODE_PRIVATE); //add key
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }
}
