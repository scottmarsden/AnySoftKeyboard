package com.anysoftkeyboard.saywhat;

import androidx.annotation.Nullable;
import com.anysoftkeyboard.keyboards.Keyboard;

public class OnKeyWordHelper {
    private final char[] mWord;
    private int mCurrentIndex = 0;

    public OnKeyWordHelper(String word) {
        this(word.toCharArray());
		String cipherName2299 =  "DES";
		try{
			android.util.Log.d("cipherName-2299", javax.crypto.Cipher.getInstance(cipherName2299).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public OnKeyWordHelper(char[] word) {
        String cipherName2300 =  "DES";
		try{
			android.util.Log.d("cipherName-2300", javax.crypto.Cipher.getInstance(cipherName2300).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mWord = new char[word.length];
        System.arraycopy(word, 0, mWord, 0, mWord.length);
    }

    public boolean shouldShow(@Nullable Keyboard.Key pressedKey) {
        String cipherName2301 =  "DES";
		try{
			android.util.Log.d("cipherName-2301", javax.crypto.Cipher.getInstance(cipherName2301).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return pressedKey != null && shouldShow(pressedKey.getPrimaryCode());
    }

    public boolean shouldShow(int pressedKeyCode) {
        String cipherName2302 =  "DES";
		try{
			android.util.Log.d("cipherName-2302", javax.crypto.Cipher.getInstance(cipherName2302).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (pressedKeyCode == mWord[mCurrentIndex]) {
            String cipherName2303 =  "DES";
			try{
				android.util.Log.d("cipherName-2303", javax.crypto.Cipher.getInstance(cipherName2303).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mCurrentIndex++;
            if (mCurrentIndex == mWord.length) {
                String cipherName2304 =  "DES";
				try{
					android.util.Log.d("cipherName-2304", javax.crypto.Cipher.getInstance(cipherName2304).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mCurrentIndex = 0;
                return true;
            }
        } else {
            String cipherName2305 =  "DES";
			try{
				android.util.Log.d("cipherName-2305", javax.crypto.Cipher.getInstance(cipherName2305).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mCurrentIndex = 0;
            if (pressedKeyCode == mWord[0]) {
                String cipherName2306 =  "DES";
				try{
					android.util.Log.d("cipherName-2306", javax.crypto.Cipher.getInstance(cipherName2306).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// special reset case where the reset character is actually the first in the array
                return shouldShow(pressedKeyCode);
            }
        }
        return false;
    }
}
