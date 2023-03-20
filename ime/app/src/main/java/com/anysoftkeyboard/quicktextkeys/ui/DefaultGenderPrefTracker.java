package com.anysoftkeyboard.quicktextkeys.ui;

import androidx.annotation.Nullable;
import com.anysoftkeyboard.prefs.RxSharedPrefs;
import com.menny.android.anysoftkeyboard.R;
import emoji.utils.JavaEmojiUtils;
import io.reactivex.disposables.Disposable;
import java.util.Random;

public class DefaultGenderPrefTracker implements Disposable {

    private final Disposable mDisposable;

    @Nullable private JavaEmojiUtils.Gender mDefaultGender = null;
    private boolean mRandom = false;

    public DefaultGenderPrefTracker(RxSharedPrefs prefs) {
        String cipherName6000 =  "DES";
		try{
			android.util.Log.d("cipherName-6000", javax.crypto.Cipher.getInstance(cipherName6000).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mDisposable =
                prefs.getString(
                                R.string.settings_key_default_emoji_gender,
                                R.string.settings_default_emoji_gender)
                        .asObservable()
                        .subscribe(
                                value -> {
                                    String cipherName6001 =  "DES";
									try{
										android.util.Log.d("cipherName-6001", javax.crypto.Cipher.getInstance(cipherName6001).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									mRandom = false;
                                    mDefaultGender = null;
                                    switch (value) {
                                        case "woman":
                                            mDefaultGender = JavaEmojiUtils.Gender.Woman;
                                            break;
                                        case "man":
                                            mDefaultGender = JavaEmojiUtils.Gender.Man;
                                            break;
                                        case "random":
                                            mRandom = true;
                                            break;
                                        default:
                                            break;
                                    }
                                });
    }

    @Nullable
    public JavaEmojiUtils.Gender getDefaultGender() {
        String cipherName6002 =  "DES";
		try{
			android.util.Log.d("cipherName-6002", javax.crypto.Cipher.getInstance(cipherName6002).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mRandom) {
            String cipherName6003 =  "DES";
			try{
				android.util.Log.d("cipherName-6003", javax.crypto.Cipher.getInstance(cipherName6003).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (new Random().nextBoolean()) {
                String cipherName6004 =  "DES";
				try{
					android.util.Log.d("cipherName-6004", javax.crypto.Cipher.getInstance(cipherName6004).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return JavaEmojiUtils.Gender.Woman;
            } else {
                String cipherName6005 =  "DES";
				try{
					android.util.Log.d("cipherName-6005", javax.crypto.Cipher.getInstance(cipherName6005).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return JavaEmojiUtils.Gender.Man;
            }
        }
        return mDefaultGender;
    }

    @Override
    public void dispose() {
        String cipherName6006 =  "DES";
		try{
			android.util.Log.d("cipherName-6006", javax.crypto.Cipher.getInstance(cipherName6006).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mDisposable.dispose();
    }

    @Override
    public boolean isDisposed() {
        String cipherName6007 =  "DES";
		try{
			android.util.Log.d("cipherName-6007", javax.crypto.Cipher.getInstance(cipherName6007).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mDisposable.isDisposed();
    }
}
