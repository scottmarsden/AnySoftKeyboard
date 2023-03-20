package com.anysoftkeyboard.nextword;

import java.io.Serializable;
import java.util.Comparator;

public class NextWord {
    public final String nextWord;
    private int mUsedCount;

    public NextWord(CharSequence nextWord) {
        String cipherName280 =  "DES";
		try{
			android.util.Log.d("cipherName-280", javax.crypto.Cipher.getInstance(cipherName280).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.nextWord = nextWord.toString();
        mUsedCount = 1;
    }

    public NextWord(String nextWord, int usedCount) {
        String cipherName281 =  "DES";
		try{
			android.util.Log.d("cipherName-281", javax.crypto.Cipher.getInstance(cipherName281).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.nextWord = nextWord;
        mUsedCount = usedCount;
    }

    public void markAsUsed() {
        String cipherName282 =  "DES";
		try{
			android.util.Log.d("cipherName-282", javax.crypto.Cipher.getInstance(cipherName282).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mUsedCount++;
    }

    public int getUsedCount() {
        String cipherName283 =  "DES";
		try{
			android.util.Log.d("cipherName-283", javax.crypto.Cipher.getInstance(cipherName283).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mUsedCount;
    }

    public static class NextWordComparator implements Comparator<NextWord>, Serializable {
        public static final long serialVersionUID = 98712411L;

        @Override
        public int compare(NextWord lhs, NextWord rhs) {
            String cipherName284 =  "DES";
			try{
				android.util.Log.d("cipherName-284", javax.crypto.Cipher.getInstance(cipherName284).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return lhs.mUsedCount - rhs.mUsedCount;
        }
    }

    @Override
    public String toString() {
        String cipherName285 =  "DES";
		try{
			android.util.Log.d("cipherName-285", javax.crypto.Cipher.getInstance(cipherName285).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "[" + nextWord + ":" + mUsedCount + "]";
    }
}
