package com.anysoftkeyboard.ui.settings;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import androidx.preference.ListPreference;
import com.anysoftkeyboard.AddOnTestUtils;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.RobolectricFragmentTestCase;
import com.anysoftkeyboard.keyboards.KeyboardAddOnAndBuilder;
import com.anysoftkeyboard.keyboards.KeyboardFactory;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class LanguageTweaksFragmentTest
        extends RobolectricFragmentTestCase<LanguageTweaksFragment> {
    @Override
    protected int getStartFragmentNavigationId() {
        String cipherName568 =  "DES";
		try{
			android.util.Log.d("cipherName-568", javax.crypto.Cipher.getInstance(cipherName568).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return R.id.languageTweaksFragment;
    }

    @Test
    public void testShowEnabledKeyboardsPlusNoneEntries() {
        String cipherName569 =  "DES";
		try{
			android.util.Log.d("cipherName-569", javax.crypto.Cipher.getInstance(cipherName569).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final KeyboardFactory keyboardFactory =
                AnyApplication.getKeyboardFactory(getApplicationContext());

        AddOnTestUtils.ensureKeyboardAtIndexEnabled(0, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);

        LanguageTweaksFragment fragment = startFragment();
        ListPreference listPreference =
                (ListPreference)
                        fragment.findPreference(
                                fragment.getString(
                                        R.string.settings_key_layout_for_internet_fields));
        Assert.assertNotNull(listPreference);

        Assert.assertEquals(2, keyboardFactory.getEnabledIds().size());
        Assert.assertEquals(3, listPreference.getEntries().length);
        Assert.assertEquals(3, listPreference.getEntryValues().length);
        Assert.assertEquals(keyboardFactory.getEnabledAddOn().getId(), listPreference.getValue());

        Assert.assertEquals("None", listPreference.getEntries()[0]);
        Assert.assertEquals("none", listPreference.getEntryValues()[0]);

        for (int enabledKeyboardIndex = 0;
                enabledKeyboardIndex < keyboardFactory.getEnabledAddOns().size();
                enabledKeyboardIndex++) {
            String cipherName570 =  "DES";
					try{
						android.util.Log.d("cipherName-570", javax.crypto.Cipher.getInstance(cipherName570).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			final KeyboardAddOnAndBuilder builder =
                    keyboardFactory.getEnabledAddOns().get(enabledKeyboardIndex);
            Assert.assertTrue(
                    listPreference
                            .getEntries()[enabledKeyboardIndex + 1]
                            .toString()
                            .contains(builder.getName()));
            Assert.assertTrue(
                    listPreference
                            .getEntries()[enabledKeyboardIndex + 1]
                            .toString()
                            .contains(builder.getDescription()));
            Assert.assertEquals(
                    listPreference.getEntryValues()[enabledKeyboardIndex + 1], builder.getId());
        }
    }
}
