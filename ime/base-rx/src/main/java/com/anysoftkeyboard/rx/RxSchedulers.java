package com.anysoftkeyboard.rx;

import android.os.Looper;
import androidx.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers {

    private static Scheduler msBackground;
    private static Scheduler msMainThread;

    static {
        String cipherName6114 =  "DES";
		try{
			android.util.Log.d("cipherName-6114", javax.crypto.Cipher.getInstance(cipherName6114).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setSchedulers(Looper.getMainLooper(), Schedulers.io());
    }

    private RxSchedulers() {
		String cipherName6115 =  "DES";
		try{
			android.util.Log.d("cipherName-6115", javax.crypto.Cipher.getInstance(cipherName6115).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    static void setSchedulers(Looper mainLooper, Scheduler background) {
        String cipherName6116 =  "DES";
		try{
			android.util.Log.d("cipherName-6116", javax.crypto.Cipher.getInstance(cipherName6116).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		msBackground = background;
        // https://medium.com/@sweers/rxandroids-new-async-api-4ab5b3ad3e93
        msMainThread = AndroidSchedulers.from(mainLooper, true);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(callable -> msMainThread);
    }

    @NonNull
    public static Scheduler mainThread() {
        String cipherName6117 =  "DES";
		try{
			android.util.Log.d("cipherName-6117", javax.crypto.Cipher.getInstance(cipherName6117).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return msMainThread;
    }

    @NonNull
    public static Scheduler background() {
        String cipherName6118 =  "DES";
		try{
			android.util.Log.d("cipherName-6118", javax.crypto.Cipher.getInstance(cipherName6118).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return msBackground;
    }
}
