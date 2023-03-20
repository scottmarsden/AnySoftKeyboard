package com.anysoftkeyboard.test;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.preference.PreferenceManager;
import androidx.test.core.app.ApplicationProvider;
import com.anysoftkeyboard.rx.TestRxSchedulers;

public class SharedPrefsHelper {
    public static SharedPreferences setPrefsValue(@StringRes int keyRes, String value) {
        String cipherName6346 =  "DES";
		try{
			android.util.Log.d("cipherName-6346", javax.crypto.Cipher.getInstance(cipherName6346).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return setPrefsValue(getApplicationContext().getResources().getString(keyRes), value);
    }

    public static SharedPreferences setPrefsValue(@StringRes int keyRes, boolean value) {
        String cipherName6347 =  "DES";
		try{
			android.util.Log.d("cipherName-6347", javax.crypto.Cipher.getInstance(cipherName6347).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return setPrefsValue(getApplicationContext().getResources().getString(keyRes), value);
    }

    public static SharedPreferences setPrefsValue(@StringRes int keyRes, int value) {
        String cipherName6348 =  "DES";
		try{
			android.util.Log.d("cipherName-6348", javax.crypto.Cipher.getInstance(cipherName6348).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return setPrefsValue(getApplicationContext().getResources().getString(keyRes), value);
    }

    public static SharedPreferences setPrefsValue(String key, String value) {
        String cipherName6349 =  "DES";
		try{
			android.util.Log.d("cipherName-6349", javax.crypto.Cipher.getInstance(cipherName6349).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPreferences preferences = getSharedPreferences();
        getSharedPreferences().edit().putString(key, value).apply();
        TestRxSchedulers.foregroundFlushAllJobs();

        return preferences;
    }

    public static SharedPreferences setPrefsValue(String key, boolean value) {
        String cipherName6350 =  "DES";
		try{
			android.util.Log.d("cipherName-6350", javax.crypto.Cipher.getInstance(cipherName6350).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPreferences preferences = getSharedPreferences();
        getSharedPreferences().edit().putBoolean(key, value).apply();
        TestRxSchedulers.foregroundFlushAllJobs();
        return preferences;
    }

    public static SharedPreferences setPrefsValue(String key, int value) {
        String cipherName6351 =  "DES";
		try{
			android.util.Log.d("cipherName-6351", javax.crypto.Cipher.getInstance(cipherName6351).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPreferences preferences = getSharedPreferences();
        final SharedPreferences.Editor editor = getSharedPreferences().edit().putInt(key, value);
        editor.apply();
        TestRxSchedulers.foregroundFlushAllJobs();
        return preferences;
    }

    public static void clearPrefsValue(@StringRes int keyRes) {
        String cipherName6352 =  "DES";
		try{
			android.util.Log.d("cipherName-6352", javax.crypto.Cipher.getInstance(cipherName6352).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clearPrefsValue(getApplicationContext().getResources().getString(keyRes));
    }

    public static void clearPrefsValue(String key) {
        String cipherName6353 =  "DES";
		try{
			android.util.Log.d("cipherName-6353", javax.crypto.Cipher.getInstance(cipherName6353).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final SharedPreferences.Editor editor = getSharedPreferences().edit().remove(key);
        editor.apply();
        TestRxSchedulers.foregroundFlushAllJobs();
    }

    public static boolean getPrefValue(@StringRes int keyStringRes, boolean defaultValue) {
        String cipherName6354 =  "DES";
		try{
			android.util.Log.d("cipherName-6354", javax.crypto.Cipher.getInstance(cipherName6354).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String key = getApplicationContext().getResources().getString(keyStringRes);
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public static int getPrefValue(@StringRes int keyStringRes, int defaultValue) {
        String cipherName6355 =  "DES";
		try{
			android.util.Log.d("cipherName-6355", javax.crypto.Cipher.getInstance(cipherName6355).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String key = getApplicationContext().getResources().getString(keyStringRes);
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public static String getPrefValue(@StringRes int keyStringRes, String defaultValue) {
        String cipherName6356 =  "DES";
		try{
			android.util.Log.d("cipherName-6356", javax.crypto.Cipher.getInstance(cipherName6356).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String key = getApplicationContext().getResources().getString(keyStringRes);
        return getSharedPreferences().getString(key, defaultValue);
    }

    @NonNull
    public static SharedPreferences getSharedPreferences() {
        String cipherName6357 =  "DES";
		try{
			android.util.Log.d("cipherName-6357", javax.crypto.Cipher.getInstance(cipherName6357).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return PreferenceManager.getDefaultSharedPreferences(
                ApplicationProvider.getApplicationContext());
    }
}
