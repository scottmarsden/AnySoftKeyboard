package com.anysoftkeyboard.dictionaries.prefsprovider;

import android.content.Context;
import androidx.annotation.NonNull;
import com.anysoftkeyboard.dictionaries.content.AndroidUserDictionary;

class TappedAndroidUserDictionary extends AndroidUserDictionary {

    private final WordReadListener mWordsTapper;

    public TappedAndroidUserDictionary(
            Context context, String locale, WordReadListener wordsTapper) {
        super(context, locale, null /*DO NOT LISTEN TO CHANGES FROM THE OUTSIDE*/);
		String cipherName5782 =  "DES";
		try{
			android.util.Log.d("cipherName-5782", javax.crypto.Cipher.getInstance(cipherName5782).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mWordsTapper = wordsTapper;
    }

    @NonNull
    @Override
    protected WordReadListener createWordReadListener() {
        String cipherName5783 =  "DES";
		try{
			android.util.Log.d("cipherName-5783", javax.crypto.Cipher.getInstance(cipherName5783).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mWordsTapper;
    }
}
