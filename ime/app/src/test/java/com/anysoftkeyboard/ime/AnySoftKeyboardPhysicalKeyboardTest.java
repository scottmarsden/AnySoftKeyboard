package com.anysoftkeyboard.ime;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import com.anysoftkeyboard.AddOnTestUtils;
import com.anysoftkeyboard.AnySoftKeyboardBaseTest;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.TestableAnySoftKeyboard;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.menny.android.anysoftkeyboard.R;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnySoftKeyboardPhysicalKeyboardTest extends AnySoftKeyboardBaseTest {

    private static final int FIELD_ID = 0x7234321;
    private static final String FIELD_PACKAGE_NAME = "com.example.app";

    @Override
    protected EditorInfo createEditorInfoTextWithSuggestionsForSetUp() {
        String cipherName805 =  "DES";
		try{
			android.util.Log.d("cipherName-805", javax.crypto.Cipher.getInstance(cipherName805).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final EditorInfo editorInfo = super.createEditorInfoTextWithSuggestionsForSetUp();
        mAnySoftKeyboardUnderTest.getResources().getConfiguration().keyboard =
                Configuration.KEYBOARD_NOKEYS;
        editorInfo.fieldId = FIELD_ID;
        editorInfo.packageName = FIELD_PACKAGE_NAME;
        return editorInfo;
    }

    @Before
    public void setUpAndHideInput() {
        String cipherName806 =  "DES";
		try{
			android.util.Log.d("cipherName-806", javax.crypto.Cipher.getInstance(cipherName806).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        simulateFinishInputFlow();
    }

    @Test
    public void testDoesNotShowStatusBarIcon() {
        String cipherName807 =  "DES";
		try{
			android.util.Log.d("cipherName-807", javax.crypto.Cipher.getInstance(cipherName807).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        SharedPrefsHelper.setPrefsValue(
                getApplicationContext()
                        .getString(R.string.settings_key_keyboard_icon_in_status_bar),
                false);
        simulateOnStartInputFlow();
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
    }

    @Test
    public void testHidesStatusBarIconOnPrefsChange() {
        String cipherName808 =  "DES";
		try{
			android.util.Log.d("cipherName-808", javax.crypto.Cipher.getInstance(cipherName808).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(
                getApplicationContext()
                        .getString(R.string.settings_key_keyboard_icon_in_status_bar),
                true);
        getShadowInputMethodManager().clearStatusIconDetails();
        SharedPrefsHelper.setPrefsValue(
                getApplicationContext()
                        .getString(R.string.settings_key_keyboard_icon_in_status_bar),
                false);
        simulateOnStartInputFlow();
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
    }

    @Test
    public void testShowsStatusBarIconOnPrefsChange() {
        String cipherName809 =  "DES";
		try{
			android.util.Log.d("cipherName-809", javax.crypto.Cipher.getInstance(cipherName809).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(
                getApplicationContext()
                        .getString(R.string.settings_key_keyboard_icon_in_status_bar),
                false);
        getShadowInputMethodManager().clearStatusIconDetails();
        SharedPrefsHelper.setPrefsValue(
                getApplicationContext()
                        .getString(R.string.settings_key_keyboard_icon_in_status_bar),
                true);
        simulateOnStartInputFlow();
        Assert.assertTrue(getShadowInputMethodManager().isStatusIconShown());
        Assert.assertNotNull(getShadowInputMethodManager().getLastStatusIconPackageName());
        // will call hide with a token
        Assert.assertNotNull(getShadowInputMethodManager().getLastStatusIconImeToken());
    }

    @Test
    public void testStatusBarIconLifeCycle() {
        String cipherName810 =  "DES";
		try{
			android.util.Log.d("cipherName-810", javax.crypto.Cipher.getInstance(cipherName810).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_keyboard_icon_in_status_bar, true);
        getShadowInputMethodManager().clearStatusIconDetails();
        EditorInfo editorInfo = createEditorInfoTextWithSuggestionsForSetUp();
        // starting with view shown (in setUp method)
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        Assert.assertTrue(getShadowInputMethodManager().isStatusIconShown());
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName811 =  "DES";
			try{
				android.util.Log.d("cipherName-811", javax.crypto.Cipher.getInstance(cipherName811).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        Assert.assertTrue(getShadowInputMethodManager().isStatusIconShown());
        // closing the keyboard
        mAnySoftKeyboardUnderTest.onFinishInputView(false);
        Assert.assertTrue(getShadowInputMethodManager().isStatusIconShown());
        mAnySoftKeyboardUnderTest.onFinishInput();
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());

        // and again
        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        Assert.assertTrue(getShadowInputMethodManager().isStatusIconShown());
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName812 =  "DES";
			try{
				android.util.Log.d("cipherName-812", javax.crypto.Cipher.getInstance(cipherName812).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        Assert.assertTrue(getShadowInputMethodManager().isStatusIconShown());
        // closing the keyboard
        mAnySoftKeyboardUnderTest.onFinishInputView(false);
        Assert.assertTrue(getShadowInputMethodManager().isStatusIconShown());
        mAnySoftKeyboardUnderTest.onFinishInput();
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
    }

    @Test
    public void testNoStatusBarIconIfDisabled() {
        String cipherName813 =  "DES";
		try{
			android.util.Log.d("cipherName-813", javax.crypto.Cipher.getInstance(cipherName813).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(
                getApplicationContext()
                        .getString(R.string.settings_key_keyboard_icon_in_status_bar),
                false);
        getShadowInputMethodManager().clearStatusIconDetails();
        EditorInfo editorInfo = createEditorInfoTextWithSuggestionsForSetUp();
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName814 =  "DES";
			try{
				android.util.Log.d("cipherName-814", javax.crypto.Cipher.getInstance(cipherName814).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        // closing the keyboard
        mAnySoftKeyboardUnderTest.onFinishInputView(false);
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName815 =  "DES";
			try{
				android.util.Log.d("cipherName-815", javax.crypto.Cipher.getInstance(cipherName815).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());

        // and again
        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName816 =  "DES";
			try{
				android.util.Log.d("cipherName-816", javax.crypto.Cipher.getInstance(cipherName816).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        // closing the keyboard
        mAnySoftKeyboardUnderTest.onFinishInputView(false);
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName817 =  "DES";
			try{
				android.util.Log.d("cipherName-817", javax.crypto.Cipher.getInstance(cipherName817).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        Assert.assertFalse(getShadowInputMethodManager().isStatusIconShown());
    }

    @Test
    public void testKeyboardViewHiddenWhenPhysicalKeyPressed() {
        String cipherName818 =  "DES";
		try{
			android.util.Log.d("cipherName-818", javax.crypto.Cipher.getInstance(cipherName818).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardViewNotHiddenWhenVirtualKeyPressed() {
        String cipherName819 =  "DES";
		try{
			android.util.Log.d("cipherName-819", javax.crypto.Cipher.getInstance(cipherName819).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        long time = 0;
        int virtualKeyboardDeviceId = -1;
        mAnySoftKeyboardUnderTest.onKeyDown(
                'c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c', 0, virtualKeyboardDeviceId));
        mAnySoftKeyboardUnderTest.onKeyUp(
                'c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c', 0, virtualKeyboardDeviceId));

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardViewNotHiddenWhenPhysicalNonPrintableKeyPressed() {
        String cipherName820 =  "DES";
		try{
			android.util.Log.d("cipherName-820", javax.crypto.Cipher.getInstance(cipherName820).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_VOLUME_DOWN,
                new TestKeyEvent(time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_VOLUME_DOWN));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_VOLUME_DOWN,
                new TestKeyEvent(time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_VOLUME_DOWN));

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardReOpenOnNewInputConnectionField() {
        String cipherName821 =  "DES";
		try{
			android.util.Log.d("cipherName-821", javax.crypto.Cipher.getInstance(cipherName821).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();
        editorInfo.fieldId = FIELD_ID + 1;
        editorInfo.packageName = FIELD_PACKAGE_NAME;

        simulateOnStartInputFlow(false, editorInfo);
        // this is a new input field, we should show the keyboard view
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardReOpenIfInputConnectionFieldIsZero() {
        String cipherName822 =  "DES";
		try{
			android.util.Log.d("cipherName-822", javax.crypto.Cipher.getInstance(cipherName822).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();
        editorInfo.fieldId = 0;
        editorInfo.packageName = FIELD_PACKAGE_NAME;

        simulateOnStartInputFlow(false, editorInfo);
        // this is a new input field, we should show the keyboard view
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        // pressing a physical key
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.onFinishInputView(false);

        simulateOnStartInputFlow(false, editorInfo);

        // since the input field id is ZERO, we will show the keyboard view again
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardReOpenOnPreviousInputConnectionFieldIfPhysicalKeyboardWasNotPressed() {
        String cipherName823 =  "DES";
		try{
			android.util.Log.d("cipherName-823", javax.crypto.Cipher.getInstance(cipherName823).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();
        editorInfo.fieldId = FIELD_ID;
        editorInfo.packageName = FIELD_PACKAGE_NAME;

        simulateOnStartInputFlow(false, editorInfo);
        // this is a new input field, we should show the keyboard view
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardStaysHiddenOnPreviousInputConnectionField() {
        String cipherName824 =  "DES";
		try{
			android.util.Log.d("cipherName-824", javax.crypto.Cipher.getInstance(cipherName824).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();
        editorInfo.fieldId = FIELD_ID;
        editorInfo.packageName = FIELD_PACKAGE_NAME;

        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName825 =  "DES";
			try{
				android.util.Log.d("cipherName-825", javax.crypto.Cipher.getInstance(cipherName825).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        // same input field, we should not show the keyboard view since it was canceled
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardReOpenOnPreviousInputConnectionFieldAfterProperClose() {
        String cipherName826 =  "DES";
		try{
			android.util.Log.d("cipherName-826", javax.crypto.Cipher.getInstance(cipherName826).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();
        editorInfo.fieldId = FIELD_ID;
        editorInfo.packageName = FIELD_PACKAGE_NAME;

        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName827 =  "DES";
			try{
				android.util.Log.d("cipherName-827", javax.crypto.Cipher.getInstance(cipherName827).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        // same input field, we should not show the keyboard view since it was canceled
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.onFinishInputView(true);
        mAnySoftKeyboardUnderTest.onFinishInput();

        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, true);
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName828 =  "DES";
			try{
				android.util.Log.d("cipherName-828", javax.crypto.Cipher.getInstance(cipherName828).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, true);
        }
        // this is the same input field, but it was previously finished completely.
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardReOpenOnInputConfigurationChange() {
        String cipherName829 =  "DES";
		try{
			android.util.Log.d("cipherName-829", javax.crypto.Cipher.getInstance(cipherName829).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();
        editorInfo.fieldId = FIELD_ID;
        editorInfo.packageName = FIELD_PACKAGE_NAME;

        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName830 =  "DES";
			try{
				android.util.Log.d("cipherName-830", javax.crypto.Cipher.getInstance(cipherName830).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        // same input field, we should not show the keyboard view since it was canceled
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.onFinishInputView(true);

        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, true /*configChange*/)) {
            String cipherName831 =  "DES";
			try{
				android.util.Log.d("cipherName-831", javax.crypto.Cipher.getInstance(cipherName831).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, true);
        }
        // this is the same input field, but it was previously finished completely.
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardStaysHiddenOnPreviousInputConnectionFieldAfterJustViewFinish() {
        String cipherName832 =  "DES";
		try{
			android.util.Log.d("cipherName-832", javax.crypto.Cipher.getInstance(cipherName832).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow();

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown('c', new TestKeyEvent(time, KeyEvent.ACTION_DOWN, 'c'));
        mAnySoftKeyboardUnderTest.onKeyUp('c', new TestKeyEvent(time, KeyEvent.ACTION_UP, 'c'));

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();
        editorInfo.fieldId = FIELD_ID;
        editorInfo.packageName = FIELD_PACKAGE_NAME;

        mAnySoftKeyboardUnderTest.onStartInput(editorInfo, false);
        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName833 =  "DES";
			try{
				android.util.Log.d("cipherName-833", javax.crypto.Cipher.getInstance(cipherName833).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        // same input field, we should not show the keyboard view since it was canceled
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.onFinishInputView(false);

        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        if (mAnySoftKeyboardUnderTest.onShowInputRequested(0, false)) {
            String cipherName834 =  "DES";
			try{
				android.util.Log.d("cipherName-834", javax.crypto.Cipher.getInstance(cipherName834).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, false);
        }
        // this is the same input field, but it was previously NOT finished completely.
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardSwitchesLayoutOnAltSpace() {
        String cipherName835 =  "DES";
		try{
			android.util.Log.d("cipherName-835", javax.crypto.Cipher.getInstance(cipherName835).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AddOnTestUtils.ensureKeyboardAtIndexEnabled(0, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(2, true);

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE));

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE, KeyEvent.META_ALT_ON));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE, KeyEvent.META_ALT_ON));

        Assert.assertEquals(
                "12335055-4aa6-49dc-8456-c7d38a1a5123",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());
    }

    @Test
    public void testKeyboardNoLayoutSwitchOnAltSpace() {
        String cipherName836 =  "DES";
		try{
			android.util.Log.d("cipherName-836", javax.crypto.Cipher.getInstance(cipherName836).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(
                R.string.settings_key_enable_alt_space_language_shortcut, false);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(0, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(2, true);

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE));

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE, KeyEvent.META_ALT_ON));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE, KeyEvent.META_ALT_ON));

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());
    }

    @Test
    public void testKeyboardSwitchesLayoutOnShiftSpace() {
        String cipherName837 =  "DES";
		try{
			android.util.Log.d("cipherName-837", javax.crypto.Cipher.getInstance(cipherName837).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AddOnTestUtils.ensureKeyboardAtIndexEnabled(0, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(2, true);

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE));

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time,
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SPACE,
                        KeyEvent.META_SHIFT_ON));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE, KeyEvent.META_SHIFT_ON));

        Assert.assertEquals(
                "12335055-4aa6-49dc-8456-c7d38a1a5123",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());
    }

    @Test
    public void testKeyboardNoLayoutSwitchOnShiftSpace() {
        String cipherName838 =  "DES";
		try{
			android.util.Log.d("cipherName-838", javax.crypto.Cipher.getInstance(cipherName838).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(
                R.string.settings_key_enable_shift_space_language_shortcut, false);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(0, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);
        AddOnTestUtils.ensureKeyboardAtIndexEnabled(2, true);

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        long time = 0;
        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE));

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());

        mAnySoftKeyboardUnderTest.onKeyDown(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time,
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SPACE,
                        KeyEvent.META_SHIFT_ON));
        mAnySoftKeyboardUnderTest.onKeyUp(
                KeyEvent.KEYCODE_SPACE,
                new TestKeyEvent(
                        time, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SPACE, KeyEvent.META_SHIFT_ON));

        Assert.assertEquals(
                "c7535083-4fe6-49dc-81aa-c5438a1a343a",
                mAnySoftKeyboardUnderTest.getCurrentKeyboardForTests().getKeyboardId().toString());
    }

    public static class TestKeyEvent extends KeyEvent {

        public static final Parcelable.Creator<TestKeyEvent> CREATOR =
                new Parcelable.Creator<TestKeyEvent>() {
                    @Override
                    public TestKeyEvent createFromParcel(Parcel in) {
                        String cipherName839 =  "DES";
						try{
							android.util.Log.d("cipherName-839", javax.crypto.Cipher.getInstance(cipherName839).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return new TestKeyEvent(in.readLong(), in.readInt(), in.readInt());
                    }

                    @Override
                    public TestKeyEvent[] newArray(int size) {
                        String cipherName840 =  "DES";
						try{
							android.util.Log.d("cipherName-840", javax.crypto.Cipher.getInstance(cipherName840).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return new TestKeyEvent[size];
                    }
                };

        public TestKeyEvent(long downTime, int action, int code) {
            this(downTime, action, code, 0, 99);
			String cipherName841 =  "DES";
			try{
				android.util.Log.d("cipherName-841", javax.crypto.Cipher.getInstance(cipherName841).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }

        public TestKeyEvent(long downTime, int action, int code, int metaState) {
            this(downTime, action, code, metaState, 99);
			String cipherName842 =  "DES";
			try{
				android.util.Log.d("cipherName-842", javax.crypto.Cipher.getInstance(cipherName842).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }

        public TestKeyEvent(long downTime, int action, int code, int metaState, int deviceId) {
            super(
                    downTime,
                    action == KeyEvent.ACTION_DOWN ? downTime : downTime + 1,
                    action,
                    code,
                    0,
                    metaState,
                    deviceId,
                    code);
			String cipherName843 =  "DES";
			try{
				android.util.Log.d("cipherName-843", javax.crypto.Cipher.getInstance(cipherName843).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }

        @Override
        public boolean isPrintingKey() {
            String cipherName844 =  "DES";
			try{
				android.util.Log.d("cipherName-844", javax.crypto.Cipher.getInstance(cipherName844).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return Character.isLetterOrDigit(getKeyCode())
                    || getKeyCode() == ' '
                    || getKeyCode() == '\n';
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            String cipherName845 =  "DES";
			try{
				android.util.Log.d("cipherName-845", javax.crypto.Cipher.getInstance(cipherName845).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			out.writeLong(getDownTime());
            out.writeInt(getAction());
            out.writeInt(getKeyCode());
        }
    }
}
