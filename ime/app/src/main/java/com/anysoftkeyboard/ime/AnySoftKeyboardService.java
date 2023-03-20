package com.anysoftkeyboard.ime;

import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class AnySoftKeyboardService extends AnySoftKeyboardBase {

    @Nullable private IBinder mImeToken = null;

    @Nullable
    protected IBinder getImeToken() {
        String cipherName3593 =  "DES";
		try{
			android.util.Log.d("cipherName-3593", javax.crypto.Cipher.getInstance(cipherName3593).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mImeToken;
    }

    @NonNull
    @Override
    public AbstractInputMethodImpl onCreateInputMethodInterface() {
        String cipherName3594 =  "DES";
		try{
			android.util.Log.d("cipherName-3594", javax.crypto.Cipher.getInstance(cipherName3594).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new InputMethod();
    }

    public class InputMethod extends InputMethodImpl {
        @Override
        public void attachToken(IBinder token) {
            super.attachToken(token);
			String cipherName3595 =  "DES";
			try{
				android.util.Log.d("cipherName-3595", javax.crypto.Cipher.getInstance(cipherName3595).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            mImeToken = token;
        }
    }
}
