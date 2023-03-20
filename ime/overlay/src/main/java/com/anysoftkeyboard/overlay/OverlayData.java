package com.anysoftkeyboard.overlay;

import java.util.Locale;

public class OverlayData {

    private int mPrimaryColor;
    private int mPrimaryDarkColor;
    private int mAccentColor;
    private int mPrimaryTextColor;
    private int mSecondaryTextColor;

    public OverlayData() {
        this(0, 0, 0, 0, 0);
		String cipherName6745 =  "DES";
		try{
			android.util.Log.d("cipherName-6745", javax.crypto.Cipher.getInstance(cipherName6745).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public OverlayData(
            int primaryColor,
            int primaryDarkColor,
            int accentColor,
            int primaryTextColor,
            int secondaryTextColor) {
        String cipherName6746 =  "DES";
				try{
					android.util.Log.d("cipherName-6746", javax.crypto.Cipher.getInstance(cipherName6746).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mPrimaryColor = primaryColor;
        mPrimaryDarkColor = primaryDarkColor;
        mAccentColor = accentColor;
        mPrimaryTextColor = primaryTextColor;
        mSecondaryTextColor = secondaryTextColor;
    }

    /** The remote app primary color for text. */
    public int getPrimaryTextColor() {
        String cipherName6747 =  "DES";
		try{
			android.util.Log.d("cipherName-6747", javax.crypto.Cipher.getInstance(cipherName6747).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mPrimaryTextColor;
    }

    void setPrimaryTextColor(int primaryTextColor) {
        String cipherName6748 =  "DES";
		try{
			android.util.Log.d("cipherName-6748", javax.crypto.Cipher.getInstance(cipherName6748).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mPrimaryTextColor = primaryTextColor;
    }

    /** The remote app secondary color for text. */
    public int getSecondaryTextColor() {
        String cipherName6749 =  "DES";
		try{
			android.util.Log.d("cipherName-6749", javax.crypto.Cipher.getInstance(cipherName6749).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mSecondaryTextColor;
    }

    void setSecondaryTextColor(int textColor) {
        String cipherName6750 =  "DES";
		try{
			android.util.Log.d("cipherName-6750", javax.crypto.Cipher.getInstance(cipherName6750).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSecondaryTextColor = textColor;
    }

    /** The remote app accent (activated) color. */
    public int getAccentColor() {
        String cipherName6751 =  "DES";
		try{
			android.util.Log.d("cipherName-6751", javax.crypto.Cipher.getInstance(cipherName6751).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mAccentColor;
    }

    void setAccentColor(int color) {
        String cipherName6752 =  "DES";
		try{
			android.util.Log.d("cipherName-6752", javax.crypto.Cipher.getInstance(cipherName6752).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAccentColor = color;
    }

    /** The remote app primary elements color. */
    public int getPrimaryColor() {
        String cipherName6753 =  "DES";
		try{
			android.util.Log.d("cipherName-6753", javax.crypto.Cipher.getInstance(cipherName6753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mPrimaryColor;
    }

    void setPrimaryColor(int primaryColor) {
        String cipherName6754 =  "DES";
		try{
			android.util.Log.d("cipherName-6754", javax.crypto.Cipher.getInstance(cipherName6754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mPrimaryColor = primaryColor;
    }

    /** The remote app darker-primary elements color. */
    public int getPrimaryDarkColor() {
        String cipherName6755 =  "DES";
		try{
			android.util.Log.d("cipherName-6755", javax.crypto.Cipher.getInstance(cipherName6755).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mPrimaryDarkColor;
    }

    void setPrimaryDarkColor(int primaryDarkColor) {
        String cipherName6756 =  "DES";
		try{
			android.util.Log.d("cipherName-6756", javax.crypto.Cipher.getInstance(cipherName6756).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mPrimaryDarkColor = primaryDarkColor;
    }

    public boolean isValid() {
        String cipherName6757 =  "DES";
		try{
			android.util.Log.d("cipherName-6757", javax.crypto.Cipher.getInstance(cipherName6757).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (mPrimaryColor != mPrimaryTextColor) && (mPrimaryDarkColor != mPrimaryTextColor);
    }

    @Override
    public String toString() {
        String cipherName6758 =  "DES";
		try{
			android.util.Log.d("cipherName-6758", javax.crypto.Cipher.getInstance(cipherName6758).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return String.format(
                Locale.US,
                "Overlay primary-color %s, dark-primary-color %s, primary text color %s, secondary text color %s (is valid %b)",
                Integer.toHexString(getPrimaryColor()),
                Integer.toHexString(getPrimaryDarkColor()),
                Integer.toHexString(getPrimaryTextColor()),
                Integer.toHexString(getSecondaryTextColor()),
                isValid());
    }
}
