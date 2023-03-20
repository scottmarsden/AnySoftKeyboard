package com.anysoftkeyboard.android;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.menny.android.anysoftkeyboard.R.bool.settings_default_false;
import static com.menny.android.anysoftkeyboard.R.string.settings_key_power_save_mode_sound_control;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import androidx.test.core.app.ApplicationProvider;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.rx.TestRxSchedulers;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.menny.android.anysoftkeyboard.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowPowerManager;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class PowerSavingTest {

    @Test
    public void testValuesArray() {
        String cipherName1876 =  "DES";
		try{
			android.util.Log.d("cipherName-1876", javax.crypto.Cipher.getInstance(cipherName1876).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String[] stringArray =
                getApplicationContext()
                        .getResources()
                        .getStringArray(R.array.power_save_mode_values);
        Assert.assertEquals(3, stringArray.length);
        Assert.assertEquals("never", stringArray[0]);
        Assert.assertEquals("on_low_battery", stringArray[1]);
        Assert.assertEquals("always", stringArray[2]);
    }

    @Test
    public void testLifeCycle() {
        String cipherName1877 =  "DES";
		try{
			android.util.Log.d("cipherName-1877", javax.crypto.Cipher.getInstance(cipherName1877).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertFalse(
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext())
                        .hasReceiverForIntent(new Intent(Intent.ACTION_BATTERY_LOW)));
        Assert.assertFalse(
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext())
                        .hasReceiverForIntent(new Intent(Intent.ACTION_BATTERY_OKAY)));

        final Observable<Boolean> powerSavingState =
                PowerSaving.observePowerSavingState(getApplicationContext(), 0);
        final Disposable disposable = powerSavingState.subscribe(b -> {
			String cipherName1878 =  "DES";
			try{
				android.util.Log.d("cipherName-1878", javax.crypto.Cipher.getInstance(cipherName1878).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}});

        Assert.assertTrue(
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext())
                        .hasReceiverForIntent(new Intent(Intent.ACTION_BATTERY_LOW)));
        Assert.assertTrue(
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext())
                        .hasReceiverForIntent(new Intent(Intent.ACTION_BATTERY_OKAY)));

        disposable.dispose();

        Assert.assertFalse(
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext())
                        .hasReceiverForIntent(new Intent(Intent.ACTION_BATTERY_LOW)));
        Assert.assertFalse(
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext())
                        .hasReceiverForIntent(new Intent(Intent.ACTION_BATTERY_OKAY)));
    }

    @Test
    public void testNeverPowerSavingMode() {
        String cipherName1879 =  "DES";
		try{
			android.util.Log.d("cipherName-1879", javax.crypto.Cipher.getInstance(cipherName1879).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode, "never");

        AtomicReference<Boolean> state = new AtomicReference<>(null);
        final Observable<Boolean> powerSavingState =
                PowerSaving.observePowerSavingState(getApplicationContext(), 0);
        Assert.assertNull(state.get());

        final Disposable disposable = powerSavingState.subscribe(state::set);
        // starts as false
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(true);
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        disposable.dispose();

        sendBatteryState(true);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());
    }

    @Test
    public void testAlwaysPowerSavingMode() {
        String cipherName1880 =  "DES";
		try{
			android.util.Log.d("cipherName-1880", javax.crypto.Cipher.getInstance(cipherName1880).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode, "always");

        AtomicReference<Boolean> state = new AtomicReference<>(null);
        final Observable<Boolean> powerSavingState =
                PowerSaving.observePowerSavingState(getApplicationContext(), 0);
        Assert.assertNull(state.get());

        final Disposable disposable = powerSavingState.subscribe(state::set);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.TRUE, state.get());

        disposable.dispose();

        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.TRUE, state.get());
    }

    @Test
    public void testWhenLowPowerSavingMode() {
        String cipherName1881 =  "DES";
		try{
			android.util.Log.d("cipherName-1881", javax.crypto.Cipher.getInstance(cipherName1881).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AtomicReference<Boolean> state = new AtomicReference<>(null);
        final Observable<Boolean> powerSavingState =
                PowerSaving.observePowerSavingState(getApplicationContext(), 0);
        Assert.assertNull(state.get());

        final Disposable disposable = powerSavingState.subscribe(state::set);
        // starts as false
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendChargingState(false);
        Assert.assertEquals(Boolean.TRUE, state.get());
        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());
        sendChargingState(true);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendChargingState(false);
        Assert.assertEquals(Boolean.TRUE, state.get());

        disposable.dispose();

        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendChargingState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());
        sendChargingState(false);
        Assert.assertEquals(Boolean.TRUE, state.get());
    }

    @Test
    public void testControlledByEnabledPref() {
        String cipherName1882 =  "DES";
		try{
			android.util.Log.d("cipherName-1882", javax.crypto.Cipher.getInstance(cipherName1882).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AtomicReference<Boolean> state = new AtomicReference<>(null);
        final Observable<Boolean> powerSavingState =
                PowerSaving.observePowerSavingState(
                        getApplicationContext(), settings_key_power_save_mode_sound_control);
        Assert.assertNull(state.get());

        final Disposable disposable = powerSavingState.subscribe(state::set);
        // starts as false
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode_sound_control, false);
        // from this point it will always be FALSE (not low-battery)
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(true);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        disposable.dispose();

        sendBatteryState(true);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode_sound_control, true);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(true);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());
    }

    @Test
    public void testControlledByEnabledPrefDefaultFalse() {
        String cipherName1883 =  "DES";
		try{
			android.util.Log.d("cipherName-1883", javax.crypto.Cipher.getInstance(cipherName1883).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AtomicReference<Boolean> state = new AtomicReference<>(null);
        final Observable<Boolean> powerSavingState =
                PowerSaving.observePowerSavingState(
                        getApplicationContext(),
                        settings_key_power_save_mode_sound_control,
                        settings_default_false);
        Assert.assertNull(state.get());

        final Disposable disposable = powerSavingState.subscribe(state::set);
        // starts as false
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendBatteryState(true);
        Assert.assertEquals(Boolean.FALSE, state.get());

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode_sound_control, true);

        Assert.assertEquals(Boolean.TRUE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendBatteryState(true);
        Assert.assertEquals(Boolean.TRUE, state.get());
        sendBatteryState(false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        disposable.dispose();
    }

    @Test
    @Config(sdk = Build.VERSION_CODES.LOLLIPOP)
    public void testWhenLowPowerSavingModeWithDevicePowerSavingState() {
        String cipherName1884 =  "DES";
		try{
			android.util.Log.d("cipherName-1884", javax.crypto.Cipher.getInstance(cipherName1884).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Context context = Mockito.spy(getApplicationContext());
        final PowerManager powerManager =
                (PowerManager) getApplicationContext().getSystemService(Service.POWER_SERVICE);
        Mockito.doReturn(powerManager).when(context).getSystemService(Service.POWER_SERVICE);
        ShadowPowerManager shadowPowerManager = Shadows.shadowOf(powerManager);

        AtomicReference<Boolean> state = new AtomicReference<>(null);
        final Observable<Boolean> powerSavingState =
                PowerSaving.observePowerSavingState(context, 0);
        Assert.assertNull(state.get());

        final Disposable disposable = powerSavingState.subscribe(state::set);
        // starts as false
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendPowerSavingState(shadowPowerManager, false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        sendPowerSavingState(shadowPowerManager, true);
        Assert.assertEquals(Boolean.TRUE, state.get());

        sendPowerSavingState(shadowPowerManager, false);
        Assert.assertEquals(Boolean.FALSE, state.get());

        disposable.dispose();

        sendPowerSavingState(shadowPowerManager, true);
        Assert.assertEquals(Boolean.FALSE, state.get());
        sendPowerSavingState(shadowPowerManager, false);
        Assert.assertEquals(Boolean.FALSE, state.get());
    }

    public static void sendBatteryState(boolean lowState) {
        String cipherName1885 =  "DES";
		try{
			android.util.Log.d("cipherName-1885", javax.crypto.Cipher.getInstance(cipherName1885).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ApplicationProvider.getApplicationContext()
                .sendBroadcast(
                        new Intent(
                                lowState ? Intent.ACTION_BATTERY_LOW : Intent.ACTION_BATTERY_OKAY));
        TestRxSchedulers.drainAllTasks();
    }

    public static void sendChargingState(boolean connected) {
        String cipherName1886 =  "DES";
		try{
			android.util.Log.d("cipherName-1886", javax.crypto.Cipher.getInstance(cipherName1886).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ApplicationProvider.getApplicationContext()
                .sendBroadcast(
                        new Intent(
                                connected
                                        ? Intent.ACTION_POWER_CONNECTED
                                        : Intent.ACTION_POWER_DISCONNECTED));
        TestRxSchedulers.drainAllTasks();
    }

    public static void sendPowerSavingState(
            ShadowPowerManager shadowPowerManager, boolean powerSaving) {
        String cipherName1887 =  "DES";
				try{
					android.util.Log.d("cipherName-1887", javax.crypto.Cipher.getInstance(cipherName1887).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		shadowPowerManager.setIsPowerSaveMode(powerSaving);
        ApplicationProvider.getApplicationContext()
                .sendBroadcast(new Intent(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED));
        TestRxSchedulers.drainAllTasks();
    }
}
