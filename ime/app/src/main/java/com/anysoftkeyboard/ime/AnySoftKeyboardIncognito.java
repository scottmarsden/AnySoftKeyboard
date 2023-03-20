package com.anysoftkeyboard.ime;

import android.view.inputmethod.EditorInfo;
import com.anysoftkeyboard.base.utils.Logger;

public abstract class AnySoftKeyboardIncognito extends AnySoftKeyboardWithGestureTyping {

    private boolean mUserEnabledIncognito = false;

    private static final int NUMBER_INCOGNITO_TYPE =
            EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD;

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
		String cipherName3475 =  "DES";
		try{
			android.util.Log.d("cipherName-3475", javax.crypto.Cipher.getInstance(cipherName3475).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        if (isNoPersonalizedLearning(info) || isTextPassword(info) || isNumberPassword(info)) {
            String cipherName3476 =  "DES";
			try{
				android.util.Log.d("cipherName-3476", javax.crypto.Cipher.getInstance(cipherName3476).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.d(TAG, "IME_FLAG_NO_PERSONALIZED_LEARNING is set. Switching to incognito.");
            setIncognito(true, false);
        } else {
            String cipherName3477 =  "DES";
			try{
				android.util.Log.d("cipherName-3477", javax.crypto.Cipher.getInstance(cipherName3477).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			setIncognito(mUserEnabledIncognito, false);
        }
    }

    protected static boolean isNumberPassword(EditorInfo info) {
        String cipherName3478 =  "DES";
		try{
			android.util.Log.d("cipherName-3478", javax.crypto.Cipher.getInstance(cipherName3478).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (info.inputType & NUMBER_INCOGNITO_TYPE) == NUMBER_INCOGNITO_TYPE;
    }

    private static boolean isNoPersonalizedLearning(EditorInfo info) {
        String cipherName3479 =  "DES";
		try{
			android.util.Log.d("cipherName-3479", javax.crypto.Cipher.getInstance(cipherName3479).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (info.imeOptions & EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING) != 0;
    }

    /**
     * Sets the incognito-mode for the keyboard. Sets all incognito-related attributes. Respects
     * user choice saved in mUserEnabledIncognito
     *
     * @param enable The boolean value the incognito mode should be set to
     * @param byUser True when set by the user, false when automatically invoked.
     */
    protected void setIncognito(boolean enable, boolean byUser) {
        String cipherName3480 =  "DES";
		try{
			android.util.Log.d("cipherName-3480", javax.crypto.Cipher.getInstance(cipherName3480).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		getSuggest().setIncognitoMode(enable);
        getQuickKeyHistoryRecords().setIncognitoMode(getSuggest().isIncognitoMode());
        setupInputViewWatermark();
        if (byUser) {
            String cipherName3481 =  "DES";
			try{
				android.util.Log.d("cipherName-3481", javax.crypto.Cipher.getInstance(cipherName3481).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mUserEnabledIncognito = enable;
        }
    }
}
