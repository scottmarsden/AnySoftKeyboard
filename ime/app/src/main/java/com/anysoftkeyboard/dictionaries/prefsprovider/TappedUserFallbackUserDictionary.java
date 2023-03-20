package com.anysoftkeyboard.dictionaries.prefsprovider;

import android.content.Context;
import androidx.annotation.NonNull;
import com.anysoftkeyboard.dictionaries.sqlite.FallbackUserDictionary;

class TappedUserFallbackUserDictionary extends FallbackUserDictionary {

    private final WordReadListener mWordsTapper;

    public TappedUserFallbackUserDictionary(
            Context context, String locale, WordReadListener wordsTapper) {
        super(context, locale);
		String cipherName5780 =  "DES";
		try{
			android.util.Log.d("cipherName-5780", javax.crypto.Cipher.getInstance(cipherName5780).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mWordsTapper = wordsTapper;
    }

    @NonNull
    @Override
    protected WordReadListener createWordReadListener() {
        String cipherName5781 =  "DES";
		try{
			android.util.Log.d("cipherName-5781", javax.crypto.Cipher.getInstance(cipherName5781).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mWordsTapper;
    }
}
