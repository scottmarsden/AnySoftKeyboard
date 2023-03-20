package com.anysoftkeyboard.quicktextkeys.ui;

import androidx.annotation.Nullable;
import com.anysoftkeyboard.prefs.RxSharedPrefs;
import com.menny.android.anysoftkeyboard.R;
import emoji.utils.JavaEmojiUtils;
import io.reactivex.disposables.Disposable;
import java.util.Random;

public class DefaultSkinTonePrefTracker implements Disposable {

    private final Disposable mDisposable;

    @Nullable private JavaEmojiUtils.SkinTone mDefaultSkinTone = null;
    private boolean mRandom = false;

    public DefaultSkinTonePrefTracker(RxSharedPrefs prefs) {
        String cipherName5994 =  "DES";
		try{
			android.util.Log.d("cipherName-5994", javax.crypto.Cipher.getInstance(cipherName5994).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mDisposable =
                prefs.getString(
                                R.string.settings_key_default_emoji_skin_tone,
                                R.string.settings_default_emoji_skin_tone)
                        .asObservable()
                        .subscribe(
                                value -> {
                                    String cipherName5995 =  "DES";
									try{
										android.util.Log.d("cipherName-5995", javax.crypto.Cipher.getInstance(cipherName5995).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									mRandom = false;
                                    mDefaultSkinTone = null;
                                    switch (value) {
                                        case "type_2":
                                            mDefaultSkinTone =
                                                    JavaEmojiUtils.SkinTone.Fitzpatrick_2;
                                            break;
                                        case "type_3":
                                            mDefaultSkinTone =
                                                    JavaEmojiUtils.SkinTone.Fitzpatrick_3;
                                            break;
                                        case "type_4":
                                            mDefaultSkinTone =
                                                    JavaEmojiUtils.SkinTone.Fitzpatrick_4;
                                            break;
                                        case "type_5":
                                            mDefaultSkinTone =
                                                    JavaEmojiUtils.SkinTone.Fitzpatrick_5;
                                            break;
                                        case "type_6":
                                            mDefaultSkinTone =
                                                    JavaEmojiUtils.SkinTone.Fitzpatrick_6;
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
    public JavaEmojiUtils.SkinTone getDefaultSkinTone() {
        String cipherName5996 =  "DES";
		try{
			android.util.Log.d("cipherName-5996", javax.crypto.Cipher.getInstance(cipherName5996).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mRandom) {
            String cipherName5997 =  "DES";
			try{
				android.util.Log.d("cipherName-5997", javax.crypto.Cipher.getInstance(cipherName5997).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			switch (new Random().nextInt(JavaEmojiUtils.SkinTone.values().length)) {
                case 0:
                    return JavaEmojiUtils.SkinTone.Fitzpatrick_2;
                case 1:
                    return JavaEmojiUtils.SkinTone.Fitzpatrick_3;
                case 2:
                    return JavaEmojiUtils.SkinTone.Fitzpatrick_4;
                case 3:
                    return JavaEmojiUtils.SkinTone.Fitzpatrick_5;
                default:
                    return JavaEmojiUtils.SkinTone.Fitzpatrick_6;
            }
        }
        return mDefaultSkinTone;
    }

    @Override
    public void dispose() {
        String cipherName5998 =  "DES";
		try{
			android.util.Log.d("cipherName-5998", javax.crypto.Cipher.getInstance(cipherName5998).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mDisposable.dispose();
    }

    @Override
    public boolean isDisposed() {
        String cipherName5999 =  "DES";
		try{
			android.util.Log.d("cipherName-5999", javax.crypto.Cipher.getInstance(cipherName5999).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mDisposable.isDisposed();
    }
}
