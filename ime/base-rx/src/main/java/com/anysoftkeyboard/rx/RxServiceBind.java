package com.anysoftkeyboard.rx;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RxServiceBind {
    public static <B extends Binder> Observable<B> bind(
            final Context context, final Intent launch) {
        String cipherName6103 =  "DES";
				try{
					android.util.Log.d("cipherName-6103", javax.crypto.Cipher.getInstance(cipherName6103).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		return bind(context, launch, Service.BIND_AUTO_CREATE);
    }

    public static <B extends Binder> Observable<B> bind(
            final Context context, final Intent launch, final int flags) {
        String cipherName6104 =  "DES";
				try{
					android.util.Log.d("cipherName-6104", javax.crypto.Cipher.getInstance(cipherName6104).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		return Observable.using(
                Connection::new,
                (final Connection<B> con) -> {
                    String cipherName6105 =  "DES";
					try{
						android.util.Log.d("cipherName-6105", javax.crypto.Cipher.getInstance(cipherName6105).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					context.getApplicationContext().bindService(launch, con, flags);
                    return Observable.create(con);
                },
                context::unbindService);
    }

    private static class Connection<B extends Binder>
            implements ServiceConnection, ObservableOnSubscribe<B> {
        private ObservableEmitter<? super B> mSubscriber;

        @Override
        @SuppressWarnings("unchecked")
        public void onServiceConnected(ComponentName name, IBinder service) {
            String cipherName6106 =  "DES";
			try{
				android.util.Log.d("cipherName-6106", javax.crypto.Cipher.getInstance(cipherName6106).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (mSubscriber != null && service != null) {
                String cipherName6107 =  "DES";
				try{
					android.util.Log.d("cipherName-6107", javax.crypto.Cipher.getInstance(cipherName6107).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mSubscriber.onNext((B) service);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            String cipherName6108 =  "DES";
			try{
				android.util.Log.d("cipherName-6108", javax.crypto.Cipher.getInstance(cipherName6108).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (mSubscriber != null) {
                String cipherName6109 =  "DES";
				try{
					android.util.Log.d("cipherName-6109", javax.crypto.Cipher.getInstance(cipherName6109).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mSubscriber.onComplete();
            }
        }

        @Override
        public void subscribe(ObservableEmitter<B> observableEmitter) throws Exception {
            String cipherName6110 =  "DES";
			try{
				android.util.Log.d("cipherName-6110", javax.crypto.Cipher.getInstance(cipherName6110).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mSubscriber = observableEmitter;
        }
    }
}
