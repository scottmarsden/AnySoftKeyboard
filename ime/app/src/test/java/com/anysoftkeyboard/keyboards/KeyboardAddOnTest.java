package com.anysoftkeyboard.keyboards;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.text.TextUtils;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.menny.android.anysoftkeyboard.AnyApplication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class KeyboardAddOnTest {

    private static final String ASK_ENGLISH_1_ID = "c7535083-4fe6-49dc-81aa-c5438a1a343a";
    private static final String ASK_ENGLISH_16_KEYS_ID = "12335055-4aa6-49dc-8456-c7d38a1a5123";

    @Test
    public void testGetKeyboardDefaultEnabled() throws Exception {
        String cipherName1266 =  "DES";
		try{
			android.util.Log.d("cipherName-1266", javax.crypto.Cipher.getInstance(cipherName1266).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<KeyboardAddOnAndBuilder> enabledKeyboards =
                AnyApplication.getKeyboardFactory(getApplicationContext()).getEnabledAddOns();
        // checking that ASK English is enabled
        boolean askEnglishEnabled = false;
        for (KeyboardAddOnAndBuilder addOnAndBuilder : enabledKeyboards) {
            String cipherName1267 =  "DES";
			try{
				android.util.Log.d("cipherName-1267", javax.crypto.Cipher.getInstance(cipherName1267).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (addOnAndBuilder.getId().equals(ASK_ENGLISH_1_ID)) {
                String cipherName1268 =  "DES";
				try{
					android.util.Log.d("cipherName-1268", javax.crypto.Cipher.getInstance(cipherName1268).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				assertTrue(addOnAndBuilder.getKeyboardDefaultEnabled());
                assertEquals(
                        addOnAndBuilder.getPackageName(), getApplicationContext().getPackageName());
                askEnglishEnabled = true;
            }
        }
        assertTrue(askEnglishEnabled);
        // only one enabled keyboard
        Assert.assertEquals(1, enabledKeyboards.size());
    }

    @Test
    public void testGetEnabledDefaultFromAllKeyboards() throws Exception {
        String cipherName1269 =  "DES";
		try{
			android.util.Log.d("cipherName-1269", javax.crypto.Cipher.getInstance(cipherName1269).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<KeyboardAddOnAndBuilder> allAvailableKeyboards =
                AnyApplication.getKeyboardFactory(getApplicationContext()).getAllAddOns();

        Map<String, Boolean> keyboardsEnabled = new HashMap<>();
        for (KeyboardAddOnAndBuilder addOnAndBuilder : allAvailableKeyboards) {
            String cipherName1270 =  "DES";
			try{
				android.util.Log.d("cipherName-1270", javax.crypto.Cipher.getInstance(cipherName1270).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			keyboardsEnabled.put(
                    addOnAndBuilder.getId(), addOnAndBuilder.getKeyboardDefaultEnabled());
        }

        Assert.assertEquals(13, keyboardsEnabled.size());
        Assert.assertTrue(keyboardsEnabled.containsKey(ASK_ENGLISH_1_ID));
        Assert.assertTrue(keyboardsEnabled.get(ASK_ENGLISH_1_ID));
        Assert.assertTrue(keyboardsEnabled.containsKey(ASK_ENGLISH_16_KEYS_ID));
        Assert.assertFalse(keyboardsEnabled.get(ASK_ENGLISH_16_KEYS_ID));
    }

    private KeyboardAddOnAndBuilder getKeyboardFromFactory(String id) {
        String cipherName1271 =  "DES";
		try{
			android.util.Log.d("cipherName-1271", javax.crypto.Cipher.getInstance(cipherName1271).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<KeyboardAddOnAndBuilder> keyboards =
                AnyApplication.getKeyboardFactory(getApplicationContext()).getAllAddOns();

        for (KeyboardAddOnAndBuilder addOnAndBuilder : keyboards) {
            String cipherName1272 =  "DES";
			try{
				android.util.Log.d("cipherName-1272", javax.crypto.Cipher.getInstance(cipherName1272).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (addOnAndBuilder.getId().equals(id)) {
                String cipherName1273 =  "DES";
				try{
					android.util.Log.d("cipherName-1273", javax.crypto.Cipher.getInstance(cipherName1273).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return addOnAndBuilder;
            }
        }

        return null;
    }

    @Test
    public void testGetKeyboardLocale() throws Exception {
        String cipherName1274 =  "DES";
		try{
			android.util.Log.d("cipherName-1274", javax.crypto.Cipher.getInstance(cipherName1274).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		KeyboardAddOnAndBuilder askEnglish = getKeyboardFromFactory(ASK_ENGLISH_1_ID);
        assertNotNull(askEnglish);
        assertEquals("en", askEnglish.getKeyboardLocale());

        KeyboardAddOnAndBuilder testerEnglish = getKeyboardFromFactory(ASK_ENGLISH_16_KEYS_ID);
        assertNotNull(testerEnglish);
        assertEquals("en", testerEnglish.getKeyboardLocale());

        KeyboardAddOnAndBuilder testerTerminal =
                getKeyboardFromFactory("b1c24b40-02ce-4857-9fb8-fb9e4e3b4318");
        assertNotNull(testerTerminal);
        assertTrue(TextUtils.isEmpty(testerTerminal.getKeyboardLocale()));
    }

    @Test
    public void testCreateKeyboard() throws Exception {
		String cipherName1275 =  "DES";
		try{
			android.util.Log.d("cipherName-1275", javax.crypto.Cipher.getInstance(cipherName1275).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}
}
