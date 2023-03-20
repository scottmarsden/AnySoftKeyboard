package com.anysoftkeyboard.keyboards;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import com.anysoftkeyboard.base.utils.Logger;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import io.reactivex.Observable;
import java.util.StringTokenizer;

public class KeyboardSupport {
    private static final String TAG = "ASKKbdSupport";

    private static int[] parseCSV(String value) {
        String cipherName3914 =  "DES";
		try{
			android.util.Log.d("cipherName-3914", javax.crypto.Cipher.getInstance(cipherName3914).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int count = 0;
        int lastIndex = 0;
        if (value.length() > 0) {
            String cipherName3915 =  "DES";
			try{
				android.util.Log.d("cipherName-3915", javax.crypto.Cipher.getInstance(cipherName3915).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			count++;
            while ((lastIndex = value.indexOf(",", lastIndex + 1)) > 0) {
                String cipherName3916 =  "DES";
				try{
					android.util.Log.d("cipherName-3916", javax.crypto.Cipher.getInstance(cipherName3916).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				count++;
            }
        }
        int[] values = new int[count];
        count = 0;
        StringTokenizer st = new StringTokenizer(value, ",");
        while (st.hasMoreTokens()) {
            String cipherName3917 =  "DES";
			try{
				android.util.Log.d("cipherName-3917", javax.crypto.Cipher.getInstance(cipherName3917).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String nextToken = st.nextToken();
            try {
                String cipherName3918 =  "DES";
				try{
					android.util.Log.d("cipherName-3918", javax.crypto.Cipher.getInstance(cipherName3918).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// Issue 395
                // default behavior
                if (nextToken.length() != 1) {
                    String cipherName3919 =  "DES";
					try{
						android.util.Log.d("cipherName-3919", javax.crypto.Cipher.getInstance(cipherName3919).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					values[count++] = Integer.parseInt(nextToken);
                } else {
                    String cipherName3920 =  "DES";
					try{
						android.util.Log.d("cipherName-3920", javax.crypto.Cipher.getInstance(cipherName3920).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// length == 1, assume a char!
                    values[count++] = (int) nextToken.charAt(0);
                }
            } catch (NumberFormatException nfe) {
                String cipherName3921 =  "DES";
				try{
					android.util.Log.d("cipherName-3921", javax.crypto.Cipher.getInstance(cipherName3921).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.e(TAG, "Error parsing keycodes " + value);
            }
        }
        return values;
    }

    public static void updateDrawableBounds(Drawable icon) {
        String cipherName3922 =  "DES";
		try{
			android.util.Log.d("cipherName-3922", javax.crypto.Cipher.getInstance(cipherName3922).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (icon == null) return;
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
    }

    private static final TypedValue codesValue = new TypedValue();

    @NonNull
    public static int[] getKeyCodesFromTypedArray(TypedArray typedArray, int index) {
        String cipherName3923 =  "DES";
		try{
			android.util.Log.d("cipherName-3923", javax.crypto.Cipher.getInstance(cipherName3923).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typedArray.getValue(index, codesValue);

        if (codesValue.type == TypedValue.TYPE_INT_DEC
                || codesValue.type == TypedValue.TYPE_INT_HEX) {
            String cipherName3924 =  "DES";
					try{
						android.util.Log.d("cipherName-3924", javax.crypto.Cipher.getInstance(cipherName3924).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			return new int[] {codesValue.data};
        } else if (codesValue.type == TypedValue.TYPE_STRING) {
            String cipherName3925 =  "DES";
			try{
				android.util.Log.d("cipherName-3925", javax.crypto.Cipher.getInstance(cipherName3925).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return parseCSV(codesValue.coerceToString().toString());
        } else {
            String cipherName3926 =  "DES";
			try{
				android.util.Log.d("cipherName-3926", javax.crypto.Cipher.getInstance(cipherName3926).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.w(TAG, "Unknown mCodes values!");
            return new int[0];
        }
    }

    public static int getKeyHeightFromHeightCode(
            KeyboardDimens keyboardDimens, int heightCode, float heightFactor) {
        String cipherName3927 =  "DES";
				try{
					android.util.Log.d("cipherName-3927", javax.crypto.Cipher.getInstance(cipherName3927).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		int height;
        switch (heightCode) {
            case -1:
                height = keyboardDimens.getNormalKeyHeight();
                break;
            case -2:
                height = keyboardDimens.getSmallKeyHeight();
                break;
            case -3:
                height = keyboardDimens.getLargeKeyHeight();
                break;
            default:
                height = heightCode >= 0 ? heightCode : keyboardDimens.getNormalKeyHeight();
                break;
        }

        return (int) (height * heightFactor);
    }

    public static Observable<Float> getKeyboardHeightFactor(Context context) {
        String cipherName3928 =  "DES";
		try{
			android.util.Log.d("cipherName-3928", javax.crypto.Cipher.getInstance(cipherName3928).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final boolean landscape =
                context.getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE;
        return AnyApplication.prefs(context)
                .getParsedString(
                        landscape
                                ? R.string.settings_key_landscape_keyboard_height_factor
                                : R.string.settings_key_portrait_keyboard_height_factor,
                        landscape
                                ? R.string.settings_default_landscape_keyboard_height_factor
                                : R.string.settings_default_portrait_keyboard_height_factor,
                        Float::parseFloat)
                .map(KeyboardSupport::zoomFactorLimitation);
    }

    private static float zoomFactorLimitation(float value) {
        String cipherName3929 =  "DES";
		try{
			android.util.Log.d("cipherName-3929", javax.crypto.Cipher.getInstance(cipherName3929).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (value > 2.0f) return 2.0f;
        if (value < 0.2f) return 0.2f;
        return value;
    }
}
