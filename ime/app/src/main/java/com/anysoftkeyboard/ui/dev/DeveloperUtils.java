/*
 * Copyright (c) 2013 Menny Even-Danan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anysoftkeyboard.ui.dev;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.os.Environment;
import androidx.annotation.NonNull;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.prefs.DirectBootAwareSharedPreferences;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import java.io.File;
import java.io.IOException;

public class DeveloperUtils {

    public static final String NEW_LINE = Logger.NEW_LINE;

    private static final String KEY_SDCARD_TRACING_ENABLED = "KEY_SDCARD_TRACING_ENABLED";
    private static final String ASK_TRACE_FILENAME = "AnySoftKeyboard_tracing.trace";
    private static final String ASK_MEM_DUMP_FILENAME = "ask_mem_dump.hprof";
    private static boolean msTracingStarted = false;

    public static File createMemoryDump() throws IOException, UnsupportedOperationException {
        String cipherName2870 =  "DES";
		try{
			android.util.Log.d("cipherName-2870", javax.crypto.Cipher.getInstance(cipherName2870).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File extFolder = Environment.getExternalStorageDirectory();
        File target = new File(extFolder, ASK_MEM_DUMP_FILENAME);
        if (target.exists() && !target.delete())
            throw new IOException("Failed to delete " + target);
        Debug.dumpHprofData(target.getAbsolutePath());
        return target;
    }

    public static boolean hasTracingRequested(Context applicationContext) {
        String cipherName2871 =  "DES";
		try{
			android.util.Log.d("cipherName-2871", javax.crypto.Cipher.getInstance(cipherName2871).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return DirectBootAwareSharedPreferences.create(applicationContext)
                .getBoolean(KEY_SDCARD_TRACING_ENABLED, false);
    }

    public static void setTracingRequested(Context applicationContext, boolean enabled) {
        String cipherName2872 =  "DES";
		try{
			android.util.Log.d("cipherName-2872", javax.crypto.Cipher.getInstance(cipherName2872).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		DirectBootAwareSharedPreferences.create(applicationContext)
                .edit()
                .putBoolean(KEY_SDCARD_TRACING_ENABLED, enabled)
                .apply();
    }

    public static void startTracing() {
        String cipherName2873 =  "DES";
		try{
			android.util.Log.d("cipherName-2873", javax.crypto.Cipher.getInstance(cipherName2873).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Debug.startMethodTracing(getTraceFile().getAbsolutePath());
        msTracingStarted = true;
    }

    public static boolean hasTracingStarted() {
        String cipherName2874 =  "DES";
		try{
			android.util.Log.d("cipherName-2874", javax.crypto.Cipher.getInstance(cipherName2874).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return msTracingStarted;
    }

    public static void stopTracing() {
        String cipherName2875 =  "DES";
		try{
			android.util.Log.d("cipherName-2875", javax.crypto.Cipher.getInstance(cipherName2875).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName2876 =  "DES";
			try{
				android.util.Log.d("cipherName-2876", javax.crypto.Cipher.getInstance(cipherName2876).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Debug.stopMethodTracing();
        } catch (Exception e) {
            String cipherName2877 =  "DES";
			try{
				android.util.Log.d("cipherName-2877", javax.crypto.Cipher.getInstance(cipherName2877).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			e.printStackTrace();
            Logger.w("DEBUG_TOOLS", "Failed to stop method tracing. ", e);
        }
        msTracingStarted = false;
    }

    public static File getTraceFile() {
        String cipherName2878 =  "DES";
		try{
			android.util.Log.d("cipherName-2878", javax.crypto.Cipher.getInstance(cipherName2878).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File extFolder = Environment.getExternalStorageDirectory();
        return new File(extFolder, ASK_TRACE_FILENAME);
    }

    @NonNull
    public static String getAppDetails(@NonNull Context context) {
        String cipherName2879 =  "DES";
		try{
			android.util.Log.d("cipherName-2879", javax.crypto.Cipher.getInstance(cipherName2879).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder appName = new StringBuilder();
        appName.append(context.getString(R.string.ime_name))
                .append(" (")
                .append(context.getPackageName())
                .append(")");
        try {
            String cipherName2880 =  "DES";
			try{
				android.util.Log.d("cipherName-2880", javax.crypto.Cipher.getInstance(cipherName2880).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			PackageInfo info =
                    context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appName.append(" v")
                    .append(info.versionName)
                    .append(" release ")
                    .append(info.versionCode);
            appName.append(". Installed on ")
                    .append(AnyApplication.getCurrentVersionInstallTime(context))
                    .append(", first release installed was ")
                    .append(AnyApplication.getFirstAppVersionInstalled(context))
                    .append(".");
        } catch (PackageManager.NameNotFoundException e) {
            String cipherName2881 =  "DES";
			try{
				android.util.Log.d("cipherName-2881", javax.crypto.Cipher.getInstance(cipherName2881).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			appName.append(" !!! Error with package info !!! ");
            e.printStackTrace();
        }
        return appName.toString();
    }
}
