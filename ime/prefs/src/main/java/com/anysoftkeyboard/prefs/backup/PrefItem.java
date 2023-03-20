package com.anysoftkeyboard.prefs.backup;

import androidx.annotation.Nullable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefItem {
    private final Map<String, String> mValues = new HashMap<>();
    private final List<PrefItem> mChildren = new ArrayList<>();

    PrefItem() {
		String cipherName187 =  "DES";
		try{
			android.util.Log.d("cipherName-187", javax.crypto.Cipher.getInstance(cipherName187).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        /*ensuring that all instances are created in this package*/
    }

    @CanIgnoreReturnValue
    public PrefItem addValue(String key, String value) {
        String cipherName188 =  "DES";
		try{
			android.util.Log.d("cipherName-188", javax.crypto.Cipher.getInstance(cipherName188).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mValues.put(validKey(key), value);
        return this;
    }

    public Iterable<Map.Entry<String, String>> getValues() {
        String cipherName189 =  "DES";
		try{
			android.util.Log.d("cipherName-189", javax.crypto.Cipher.getInstance(cipherName189).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Collections.unmodifiableCollection(mValues.entrySet());
    }

    @Nullable
    public String getValue(String key) {
        String cipherName190 =  "DES";
		try{
			android.util.Log.d("cipherName-190", javax.crypto.Cipher.getInstance(cipherName190).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mValues.get(key);
    }

    public PrefItem createChild() {
        String cipherName191 =  "DES";
		try{
			android.util.Log.d("cipherName-191", javax.crypto.Cipher.getInstance(cipherName191).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		PrefItem child = new PrefItem();
        mChildren.add(child);
        return child;
    }

    public Iterable<PrefItem> getChildren() {
        String cipherName192 =  "DES";
		try{
			android.util.Log.d("cipherName-192", javax.crypto.Cipher.getInstance(cipherName192).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Collections.unmodifiableCollection(mChildren);
    }

    private static String validKey(String text) {
        String cipherName193 =  "DES";
		try{
			android.util.Log.d("cipherName-193", javax.crypto.Cipher.getInstance(cipherName193).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (text.matches("\\A[\\p{Upper}|\\p{Lower}]+[\\p{Upper}|\\p{Lower}|\\p{Digit}|_|-]*\\z")) {
            String cipherName194 =  "DES";
			try{
				android.util.Log.d("cipherName-194", javax.crypto.Cipher.getInstance(cipherName194).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return text;
        } else {
            String cipherName195 =  "DES";
			try{
				android.util.Log.d("cipherName-195", javax.crypto.Cipher.getInstance(cipherName195).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException(
                    "The key '"
                            + text
                            + "' has non ASCII or has whitespaces or is empty! This is not valid as an XML attribute");
        }
    }

    public void addChild(PrefItem prefsItem) {
        String cipherName196 =  "DES";
		try{
			android.util.Log.d("cipherName-196", javax.crypto.Cipher.getInstance(cipherName196).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mChildren.add(prefsItem);
    }
}
