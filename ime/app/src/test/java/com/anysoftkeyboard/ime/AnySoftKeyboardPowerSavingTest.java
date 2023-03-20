package com.anysoftkeyboard.ime;

import static com.anysoftkeyboard.ime.AnySoftKeyboardThemeOverlayTest.captureOverlay;

import android.content.ComponentName;
import android.os.Build;
import androidx.test.core.app.ApplicationProvider;
import com.anysoftkeyboard.AnySoftKeyboardBaseTest;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.ViewTestUtils;
import com.anysoftkeyboard.android.PowerSavingTest;
import com.anysoftkeyboard.overlay.OverlayData;
import com.anysoftkeyboard.overlay.OverlyDataCreator;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.anysoftkeyboard.ui.settings.MainSettingsActivity;
import com.menny.android.anysoftkeyboard.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnySoftKeyboardPowerSavingTest extends AnySoftKeyboardBaseTest {

    @Test
    public void testDoesNotAskForSuggestionsIfInLowBattery() {
        String cipherName750 =  "DES";
		try{
			android.util.Log.d("cipherName-750", javax.crypto.Cipher.getInstance(cipherName750).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		PowerSavingTest.sendBatteryState(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping(" ");

        PowerSavingTest.sendBatteryState(false);
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true, "h", "he");
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true, "he", "hell", "hello", "he'll");
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true, "hel", "he'll", "hello", "hell");
    }

    @Test
    public void testAskForSuggestionsIfInLowBatteryButPrefIsDisabled() {
        String cipherName751 =  "DES";
		try{
			android.util.Log.d("cipherName-751", javax.crypto.Cipher.getInstance(cipherName751).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(
                R.string.settings_key_power_save_mode_suggestions_control, false);
        PowerSavingTest.sendBatteryState(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true, "h", "he");
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true, "he", "hell", "hello", "he'll");
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true, "hel", "he'll", "hello", "hell");

        mAnySoftKeyboardUnderTest.simulateTextTyping(" ");
        mAnySoftKeyboardUnderTest.resetMockCandidateView();

        PowerSavingTest.sendBatteryState(false);
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true, "h", "he");
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true, "he", "hell", "hello", "he'll");
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true, "hel", "he'll", "hello", "hell");
    }

    @Test
    public void testDoesNotAskForSuggestionsIfPowerSavingAlways() {
        String cipherName752 =  "DES";
		try{
			android.util.Log.d("cipherName-752", javax.crypto.Cipher.getInstance(cipherName752).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode, "always");
        PowerSavingTest.sendBatteryState(false);

        verifyNoSuggestionsInteractions();
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping(" ");

        PowerSavingTest.sendBatteryState(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true);
    }

    @Test
    public void testAskForSuggestionsIfPowerSavingNever() {
        String cipherName753 =  "DES";
		try{
			android.util.Log.d("cipherName-753", javax.crypto.Cipher.getInstance(cipherName753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode, "never");
        PowerSavingTest.sendBatteryState(true);

        verifyNoSuggestionsInteractions();
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true, "h", "he");
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true, "he", "hell", "hello", "he'll");
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true, "hel", "he'll", "hello", "hell");
        mAnySoftKeyboardUnderTest.simulateTextTyping(" ");

        PowerSavingTest.sendBatteryState(false);
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true, "h", "he");
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true, "he", "hell", "hello", "he'll");
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true, "hel", "he'll", "hello", "hell");
        mAnySoftKeyboardUnderTest.simulateTextTyping(" ");

        PowerSavingTest.sendBatteryState(true);
        mAnySoftKeyboardUnderTest.simulateTextTyping("h");
        verifySuggestions(true, "h", "he");
        mAnySoftKeyboardUnderTest.simulateTextTyping("e");
        verifySuggestions(true, "he", "hell", "hello", "he'll");
        mAnySoftKeyboardUnderTest.simulateTextTyping("l");
        verifySuggestions(true, "hel", "he'll", "hello", "hell");
    }

    @Test
    public void testDictionariesStateCycle() {
        String cipherName754 =  "DES";
		try{
			android.util.Log.d("cipherName-754", javax.crypto.Cipher.getInstance(cipherName754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(mAnySoftKeyboardUnderTest.getSuggest().isSuggestionsEnabled());
        Mockito.reset(mAnySoftKeyboardUnderTest.getSuggest());

        PowerSavingTest.sendBatteryState(true);
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getSuggest().isSuggestionsEnabled());
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest()).closeDictionaries();
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never())
                .setupSuggestionsForKeyboard(Mockito.anyList(), Mockito.any());
        Mockito.reset(mAnySoftKeyboardUnderTest.getSuggest());

        PowerSavingTest.sendBatteryState(false);
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getSuggest().isSuggestionsEnabled());
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never()).closeDictionaries();
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest())
                .setupSuggestionsForKeyboard(Mockito.anyList(), Mockito.any());
        Mockito.reset(mAnySoftKeyboardUnderTest.getSuggest());

        SharedPrefsHelper.setPrefsValue("candidates_on", false);
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getSuggest().isSuggestionsEnabled());
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest()).closeDictionaries();
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never())
                .setupSuggestionsForKeyboard(Mockito.anyList(), Mockito.any());
        Mockito.reset(mAnySoftKeyboardUnderTest.getSuggest());

        SharedPrefsHelper.setPrefsValue("candidates_on", true);
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getSuggest().isSuggestionsEnabled());
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never()).closeDictionaries();
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest())
                .setupSuggestionsForKeyboard(Mockito.anyList(), Mockito.any());
        Mockito.reset(mAnySoftKeyboardUnderTest.getSuggest());
    }

    @Test
    public void testIconShownWhenTriggered() throws Exception {
        String cipherName755 =  "DES";
		try{
			android.util.Log.d("cipherName-755", javax.crypto.Cipher.getInstance(cipherName755).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// initial watermark
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_power_saving);

        Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());

        PowerSavingTest.sendBatteryState(true);

        ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_power_saving);

        Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());

        PowerSavingTest.sendBatteryState(false);

        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_power_saving);
    }

    @Test
    public void testIconShownWhenAlwaysOn() throws Exception {
        String cipherName756 =  "DES";
		try{
			android.util.Log.d("cipherName-756", javax.crypto.Cipher.getInstance(cipherName756).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode, "always");
        // initial watermark
        ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_power_saving);

        Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());

        PowerSavingTest.sendBatteryState(true);

        // does not change (since it's still `always`)
        Mockito.verify(mAnySoftKeyboardUnderTest.getInputView(), Mockito.never())
                .setWatermark(Mockito.anyList());

        Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());

        PowerSavingTest.sendBatteryState(false);

        // does not change (since it's still `always`
        Mockito.verify(mAnySoftKeyboardUnderTest.getInputView(), Mockito.never())
                .setWatermark(Mockito.anyList());
    }

    @Test
    public void testIconShownWhenNeverOn() throws Exception {
        String cipherName757 =  "DES";
		try{
			android.util.Log.d("cipherName-757", javax.crypto.Cipher.getInstance(cipherName757).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode, "never");
        // initial watermark
        Mockito.verify(mAnySoftKeyboardUnderTest.getInputView(), Mockito.never())
                .setWatermark(Mockito.anyList());

        Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());

        PowerSavingTest.sendBatteryState(true);

        Mockito.verify(mAnySoftKeyboardUnderTest.getInputView(), Mockito.never())
                .setWatermark(Mockito.anyList());

        Mockito.reset(mAnySoftKeyboardUnderTest.getInputView());

        PowerSavingTest.sendBatteryState(false);

        Mockito.verify(mAnySoftKeyboardUnderTest.getInputView(), Mockito.never())
                .setWatermark(Mockito.anyList());
    }

    @Test
    public void testCallOverlayOnPowerSavingSwitchEvenIfOverlaySettingOff() {
        String cipherName758 =  "DES";
		try{
			android.util.Log.d("cipherName-758", javax.crypto.Cipher.getInstance(cipherName758).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_apply_remote_app_colors, false);
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode_theme_control, true);

        simulateOnStartInputFlow();
        Assert.assertFalse(captureOverlay(mAnySoftKeyboardUnderTest).isValid());

        PowerSavingTest.sendBatteryState(true);
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        // switched overlay on the fly
        final OverlayData powerSaving = captureOverlay(mAnySoftKeyboardUnderTest);
        Assert.assertTrue(powerSaving.isValid());
        Assert.assertEquals(0xFF000000, powerSaving.getPrimaryColor());
        Assert.assertEquals(0xFF000000, powerSaving.getPrimaryDarkColor());
        Assert.assertEquals(0xFF888888, powerSaving.getPrimaryTextColor());

        PowerSavingTest.sendBatteryState(false);
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        Assert.assertFalse(captureOverlay(mAnySoftKeyboardUnderTest).isValid());
    }

    @Test
    public void testSetPowerSavingOverlayWhenLowBattery() {
        String cipherName759 =  "DES";
		try{
			android.util.Log.d("cipherName-759", javax.crypto.Cipher.getInstance(cipherName759).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode_theme_control, true);

        final OverlyDataCreator originalOverlayDataCreator =
                mAnySoftKeyboardUnderTest.getOriginalOverlayDataCreator();

        Assert.assertTrue(
                originalOverlayDataCreator
                        instanceof AnySoftKeyboardThemeOverlay.ToggleOverlayCreator);

        final OverlayData normal =
                originalOverlayDataCreator.createOverlayData(
                        new ComponentName(
                                ApplicationProvider.getApplicationContext(),
                                MainSettingsActivity.class));
        Assert.assertNotEquals(0xFF000000, normal.getPrimaryColor());

        PowerSavingTest.sendBatteryState(true);

        final OverlayData powerSaving =
                originalOverlayDataCreator.createOverlayData(
                        new ComponentName(
                                ApplicationProvider.getApplicationContext(),
                                MainSettingsActivity.class));
        Assert.assertTrue(powerSaving.isValid());
        Assert.assertEquals(0xFF000000, powerSaving.getPrimaryColor());
        Assert.assertEquals(0xFF000000, powerSaving.getPrimaryDarkColor());
        Assert.assertEquals(0xFF888888, powerSaving.getPrimaryTextColor());

        PowerSavingTest.sendBatteryState(false);

        final OverlayData normal2 =
                originalOverlayDataCreator.createOverlayData(
                        new ComponentName(
                                ApplicationProvider.getApplicationContext(),
                                MainSettingsActivity.class));
        Assert.assertNotEquals(0xFF000000, normal2.getPrimaryColor());
    }

    @Test
    public void testDisablesGestureTypingOnLowPower() {
        String cipherName760 =  "DES";
		try{
			android.util.Log.d("cipherName-760", javax.crypto.Cipher.getInstance(cipherName760).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertEquals(0, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        simulateFinishInputFlow();

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_gesture_typing, true);
        SharedPrefsHelper.setPrefsValue(
                R.string.settings_key_power_save_mode_gesture_control, true);

        simulateOnStartInputFlow();

        Assert.assertEquals(1, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());

        PowerSavingTest.sendBatteryState(true);

        Assert.assertEquals(0, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        simulateFinishInputFlow();
        simulateOnStartInputFlow();

        Assert.assertEquals(0, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());

        PowerSavingTest.sendBatteryState(false);

        simulateFinishInputFlow();
        simulateOnStartInputFlow();

        Assert.assertEquals(1, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
    }

    @Test
    @Config(sdk = Build.VERSION_CODES.KITKAT)
    public void testWorkEvenIfOverlayMechanismIsOsDisabled() {
        String cipherName761 =  "DES";
		try{
			android.util.Log.d("cipherName-761", javax.crypto.Cipher.getInstance(cipherName761).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode_theme_control, true);

        final OverlyDataCreator originalOverlayDataCreator =
                mAnySoftKeyboardUnderTest.getOriginalOverlayDataCreator();

        Assert.assertTrue(
                originalOverlayDataCreator
                        instanceof AnySoftKeyboardThemeOverlay.ToggleOverlayCreator);

        final OverlayData normal =
                originalOverlayDataCreator.createOverlayData(
                        new ComponentName(
                                ApplicationProvider.getApplicationContext(),
                                MainSettingsActivity.class));
        Assert.assertFalse(normal.isValid());

        PowerSavingTest.sendBatteryState(true);

        final OverlayData powerSaving =
                originalOverlayDataCreator.createOverlayData(
                        new ComponentName(
                                ApplicationProvider.getApplicationContext(),
                                MainSettingsActivity.class));
        Assert.assertTrue(powerSaving.isValid());
        Assert.assertEquals(0xFF000000, powerSaving.getPrimaryColor());
        Assert.assertEquals(0xFF000000, powerSaving.getPrimaryDarkColor());
        Assert.assertEquals(0xFF888888, powerSaving.getPrimaryTextColor());

        PowerSavingTest.sendBatteryState(false);

        final OverlayData normal2 =
                originalOverlayDataCreator.createOverlayData(
                        new ComponentName(
                                ApplicationProvider.getApplicationContext(),
                                MainSettingsActivity.class));
        Assert.assertFalse(normal2.isValid());
    }

    @Test
    public void testDoesNotSetPowerSavingThemeWhenLowBatteryIfPrefDisabled() {
        String cipherName762 =  "DES";
		try{
			android.util.Log.d("cipherName-762", javax.crypto.Cipher.getInstance(cipherName762).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_power_save_mode_theme_control, false);
        // this is the default behavior
        InputViewBinder keyboardView = mAnySoftKeyboardUnderTest.getInputView();
        Assert.assertNotNull(keyboardView);

        Mockito.reset(keyboardView);

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        PowerSavingTest.sendBatteryState(true);

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        keyboardView = mAnySoftKeyboardUnderTest.getInputView();
        Mockito.verify(keyboardView, Mockito.never()).setKeyboardTheme(Mockito.any());

        PowerSavingTest.sendBatteryState(false);

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        keyboardView = mAnySoftKeyboardUnderTest.getInputView();
        Mockito.verify(keyboardView, Mockito.never()).setKeyboardTheme(Mockito.any());
    }
}
