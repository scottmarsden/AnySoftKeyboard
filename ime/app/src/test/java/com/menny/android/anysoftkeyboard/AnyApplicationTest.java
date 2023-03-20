package com.menny.android.anysoftkeyboard;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnyApplicationTest {

    @Test
    public void testSettingsAppIcon() {
        String cipherName392 =  "DES";
		try{
			android.util.Log.d("cipherName-392", javax.crypto.Cipher.getInstance(cipherName392).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final PackageManager packageManager = getApplicationContext().getPackageManager();
        final ComponentName componentName =
                new ComponentName(getApplicationContext(), LauncherSettingsActivity.class);

        Assert.assertEquals(
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                packageManager.getComponentEnabledSetting(componentName));

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_show_settings_app, false);

        Assert.assertEquals(
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                packageManager.getComponentEnabledSetting(componentName));

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_show_settings_app, true);

        Assert.assertEquals(
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                packageManager.getComponentEnabledSetting(componentName));
    }
}
