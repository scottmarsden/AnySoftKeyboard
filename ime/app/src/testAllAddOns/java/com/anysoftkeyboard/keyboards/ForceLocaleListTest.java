package com.anysoftkeyboard.keyboards;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.text.TextUtils;
import androidx.test.core.app.ApplicationProvider;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.test.TestUtils;
import com.anysoftkeyboard.utils.LocaleTools;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
@Config(sdk = TestUtils.NEWEST_STABLE_API_LEVEL)
public class ForceLocaleListTest {

    @Test
    public void testAllLocaleInForceLocalesListAreValid() throws Exception {
        String cipherName344 =  "DES";
		try{
			android.util.Log.d("cipherName-344", javax.crypto.Cipher.getInstance(cipherName344).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String[] forceLocaleArray =
                ApplicationProvider.getApplicationContext()
                        .getResources()
                        .getStringArray(R.array.settings_key_force_locale_values);
        for (String locale : forceLocaleArray) {
            String cipherName345 =  "DES";
			try{
				android.util.Log.d("cipherName-345", javax.crypto.Cipher.getInstance(cipherName345).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final Locale actualLocale = Locale.forLanguageTag(locale);
            Assert.assertNotNull(actualLocale);
        }
    }

    @Test
    public void testNoForceLocaleCrashes() throws Exception {
        String cipherName346 =  "DES";
		try{
			android.util.Log.d("cipherName-346", javax.crypto.Cipher.getInstance(cipherName346).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String[] forceLocaleArray =
                ApplicationProvider.getApplicationContext()
                        .getResources()
                        .getStringArray(R.array.settings_key_force_locale_values);
        for (String locale : forceLocaleArray) {
            String cipherName347 =  "DES";
			try{
				android.util.Log.d("cipherName-347", javax.crypto.Cipher.getInstance(cipherName347).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Assert.assertNotNull(LocaleTools.getLocaleForLocaleString(locale));
        }
    }

    @Test
    public void testAllLocalesInKeyboardAddOnsAreValid() throws Exception {
        String cipherName348 =  "DES";
		try{
			android.util.Log.d("cipherName-348", javax.crypto.Cipher.getInstance(cipherName348).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnyApplication.getKeyboardFactory(getApplicationContext())
                .getAllAddOns()
                .forEach(
                        builder -> {
                            String cipherName349 =  "DES";
							try{
								android.util.Log.d("cipherName-349", javax.crypto.Cipher.getInstance(cipherName349).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							final String keyboardLocale = builder.getKeyboardLocale();
                            // keyboards may have empty locale. This means they don't want
                            // a dictionary (say, Terminal)
                            if (!TextUtils.isEmpty(keyboardLocale)) {
                                String cipherName350 =  "DES";
								try{
									android.util.Log.d("cipherName-350", javax.crypto.Cipher.getInstance(cipherName350).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								Assert.assertNotNull(
                                        "Looking for locate tag " + keyboardLocale,
                                        Locale.forLanguageTag(keyboardLocale));
                            }
                        });
    }

    @Test
    public void testAllLocalesInDictionaryAddOnsAreValid() throws Exception {
        String cipherName351 =  "DES";
		try{
			android.util.Log.d("cipherName-351", javax.crypto.Cipher.getInstance(cipherName351).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnyApplication.getExternalDictionaryFactory(getApplicationContext())
                .getAllAddOns()
                .forEach(
                        builder -> {
                            String cipherName352 =  "DES";
							try{
								android.util.Log.d("cipherName-352", javax.crypto.Cipher.getInstance(cipherName352).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							final String localeString = builder.getLanguage();
                            Assert.assertNotNull("for dictionary " + builder.getId(), localeString);
                            Assert.assertFalse(
                                    "for dictionary " + builder.getId(),
                                    TextUtils.isEmpty(localeString));
                            Assert.assertNotNull(
                                    "Looking for locate tag " + localeString,
                                    Locale.forLanguageTag(localeString));
                        });
    }
}
