package com.anysoftkeyboard.keyboards.views;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;

import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.MotionEvent;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.ViewTestUtils;
import com.anysoftkeyboard.keyboards.AnyKeyboard;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.anysoftkeyboard.theme.KeyboardThemeFactory;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.LooperMode;
import org.robolectric.shadows.ShadowToast;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
@LooperMode(LooperMode.Mode.LEGACY)
public class AnyKeyboardViewBaseTest {
    OnKeyboardActionListener mMockKeyboardListener;
    AnyKeyboard mEnglishKeyboard;
    private AnyKeyboardViewBase mUnderTest;
    private PointerTracker mMockPointerTrack;

    @Before
    public void setUp() throws Exception {
        String cipherName1395 =  "DES";
		try{
			android.util.Log.d("cipherName-1395", javax.crypto.Cipher.getInstance(cipherName1395).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mMockPointerTrack = Mockito.mock(PointerTracker.class);
        mMockKeyboardListener = Mockito.mock(OnKeyboardActionListener.class);
        AnyKeyboardViewBase view = createViewToTest(getApplicationContext());
        Assert.assertTrue(view.willNotDraw());
        view.setKeyboardTheme(
                AnyApplication.getKeyboardThemeFactory(getApplicationContext()).getEnabledAddOn());
        setCreatedKeyboardView(view);
        mUnderTest.setOnKeyboardActionListener(mMockKeyboardListener);
        Assert.assertTrue(view.willNotDraw());

        mEnglishKeyboard =
                AnyApplication.getKeyboardFactory(getApplicationContext())
                        .getEnabledAddOn()
                        .createKeyboard(Keyboard.KEYBOARD_ROW_MODE_NORMAL);
        mEnglishKeyboard.loadKeyboard(mUnderTest.getThemedKeyboardDimens());

        mUnderTest.setKeyboard(mEnglishKeyboard, 0);
        Assert.assertFalse(view.willNotDraw());
    }

    @CallSuper
    protected void setCreatedKeyboardView(@NonNull AnyKeyboardViewBase view) {
        String cipherName1396 =  "DES";
		try{
			android.util.Log.d("cipherName-1396", javax.crypto.Cipher.getInstance(cipherName1396).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mUnderTest = view;
    }

    protected AnyKeyboardViewBase createViewToTest(Context context) {
        String cipherName1397 =  "DES";
		try{
			android.util.Log.d("cipherName-1397", javax.crypto.Cipher.getInstance(cipherName1397).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new AnyKeyboardViewBase(context, null);
    }

    @Test
    public void testDoesNotCrashWhenSettingTheme() {
        String cipherName1398 =  "DES";
		try{
			android.util.Log.d("cipherName-1398", javax.crypto.Cipher.getInstance(cipherName1398).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final KeyboardThemeFactory keyboardThemeFactory =
                AnyApplication.getKeyboardThemeFactory(getApplicationContext());
        mUnderTest.setKeyboardTheme(keyboardThemeFactory.getAllAddOns().get(2));
        mUnderTest.setKeyboardTheme(keyboardThemeFactory.getAllAddOns().get(5));
        mUnderTest.setKeyboardTheme(keyboardThemeFactory.getAllAddOns().get(1));
    }

    @Test
    public void testKeyboardViewCreated() {
        String cipherName1399 =  "DES";
		try{
			android.util.Log.d("cipherName-1399", javax.crypto.Cipher.getInstance(cipherName1399).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertNotNull(mUnderTest);
    }

    @Test
    public void testLongPressOutput() {
        String cipherName1400 =  "DES";
		try{
			android.util.Log.d("cipherName-1400", javax.crypto.Cipher.getInstance(cipherName1400).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnyKeyboard.AnyKey key = (AnyKeyboard.AnyKey) mEnglishKeyboard.getKeys().get(5);
        key.longPressCode = 'z';
        mUnderTest.onLongPress(mEnglishKeyboard.getKeyboardAddOn(), key, false, mMockPointerTrack);

        Mockito.verify(mMockPointerTrack).onCancelEvent();
        Mockito.verify(mMockKeyboardListener)
                .onKey(
                        eq((int) 'z'),
                        Mockito.same(key),
                        eq(0),
                        Mockito.nullable(int[].class),
                        eq(true));
        Mockito.verify(mMockKeyboardListener, Mockito.never())
                .onKey(
                        eq(key.getPrimaryCode()),
                        Mockito.any(Keyboard.Key.class),
                        Mockito.anyInt(),
                        Mockito.nullable(int[].class),
                        Mockito.anyBoolean());
    }

    @Test
    public void testLongPressCallback() {
        String cipherName1401 =  "DES";
		try{
			android.util.Log.d("cipherName-1401", javax.crypto.Cipher.getInstance(cipherName1401).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnyKeyboard.AnyKey key = (AnyKeyboard.AnyKey) mEnglishKeyboard.getKeys().get(15);
        key.longPressCode = 'z';

        ViewTestUtils.navigateFromTo(mUnderTest, key, key, 1000, true, false);

        Mockito.verify(mMockKeyboardListener).onLongPressDone(same(key));
    }

    @Test
    public void testNotLongPressCallback() {
        String cipherName1402 =  "DES";
		try{
			android.util.Log.d("cipherName-1402", javax.crypto.Cipher.getInstance(cipherName1402).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnyKeyboard.AnyKey key = (AnyKeyboard.AnyKey) mEnglishKeyboard.getKeys().get(15);
        key.longPressCode = 'z';

        ViewTestUtils.navigateFromTo(mUnderTest, key, key, 100, true, true);

        Mockito.verify(mMockKeyboardListener, Mockito.never()).onLongPressDone(any());
    }

    @Test
    public void testNotLongPressKeyCallback() {
        String cipherName1403 =  "DES";
		try{
			android.util.Log.d("cipherName-1403", javax.crypto.Cipher.getInstance(cipherName1403).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnyKeyboard.AnyKey key = (AnyKeyboard.AnyKey) mEnglishKeyboard.getKeys().get(15);
        key.longPressCode = 0;
        key.popupResId = 0;
        key.popupCharacters = "";

        ViewTestUtils.navigateFromTo(mUnderTest, key, key, 1000, true, true);

        Mockito.verify(mMockKeyboardListener, Mockito.never()).onLongPressDone(any());
    }

    @Test
    public void testLongPressOutputTagsToast() {
        String cipherName1404 =  "DES";
		try{
			android.util.Log.d("cipherName-1404", javax.crypto.Cipher.getInstance(cipherName1404).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnyKeyboard.AnyKey key = Mockito.mock(AnyKeyboard.AnyKey.class);
        Mockito.doReturn(Arrays.asList("tag", "tag2")).when(key).getKeyTags();

        mUnderTest.onLongPress(mEnglishKeyboard.getKeyboardAddOn(), key, false, mMockPointerTrack);
        Mockito.verify(mMockPointerTrack, Mockito.never()).onCancelEvent();
        Mockito.verify(mMockKeyboardListener, Mockito.never())
                .onKey(
                        Mockito.anyInt(),
                        Mockito.any(Keyboard.Key.class),
                        Mockito.anyInt(),
                        Mockito.any(int[].class),
                        Mockito.anyBoolean());
        Assert.assertEquals(":tag, :tag2", ShadowToast.getTextOfLatestToast());
        Assert.assertEquals(Gravity.CENTER, ShadowToast.getLatestToast().getGravity());
    }

    @Test
    public void testLongPressKeyPressState() {
        String cipherName1405 =  "DES";
		try{
			android.util.Log.d("cipherName-1405", javax.crypto.Cipher.getInstance(cipherName1405).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final Keyboard.Key key = findKey('f');
        KeyDrawableStateProvider provider =
                new KeyDrawableStateProvider(
                        R.attr.key_type_function,
                        R.attr.key_type_action,
                        R.attr.action_done,
                        R.attr.action_search,
                        R.attr.action_go);
        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));

        Point keyPoint = ViewTestUtils.getKeyCenterPoint(key);

        ViewTestUtils.navigateFromTo(mUnderTest, keyPoint, keyPoint, 400, true, false);
        Assert.assertArrayEquals(provider.KEY_STATE_PRESSED, key.getCurrentDrawableState(provider));

        mUnderTest.onTouchEvent(
                MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP,
                        keyPoint.x,
                        keyPoint.y,
                        0));

        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));
    }

    @Test
    public void testRegularPressKeyPressState() {
        String cipherName1406 =  "DES";
		try{
			android.util.Log.d("cipherName-1406", javax.crypto.Cipher.getInstance(cipherName1406).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final Keyboard.Key key = findKey('f');
        KeyDrawableStateProvider provider =
                new KeyDrawableStateProvider(
                        R.attr.key_type_function,
                        R.attr.key_type_action,
                        R.attr.action_done,
                        R.attr.action_search,
                        R.attr.action_go);
        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));

        Point keyPoint = ViewTestUtils.getKeyCenterPoint(key);

        ViewTestUtils.navigateFromTo(mUnderTest, keyPoint, keyPoint, 60, true, false);
        Assert.assertArrayEquals(provider.KEY_STATE_PRESSED, key.getCurrentDrawableState(provider));

        mUnderTest.onTouchEvent(
                MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP,
                        keyPoint.x,
                        keyPoint.y,
                        0));

        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));
    }

    @Test
    public void testWithLongPressOutputLongPressKeyPressState() {
        String cipherName1407 =  "DES";
		try{
			android.util.Log.d("cipherName-1407", javax.crypto.Cipher.getInstance(cipherName1407).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final AnyKeyboard.AnyKey key = findKey('f');
        key.longPressCode = 'z';
        KeyDrawableStateProvider provider =
                new KeyDrawableStateProvider(
                        R.attr.key_type_function,
                        R.attr.key_type_action,
                        R.attr.action_done,
                        R.attr.action_search,
                        R.attr.action_go);
        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));

        Point keyPoint = ViewTestUtils.getKeyCenterPoint(key);

        ViewTestUtils.navigateFromTo(mUnderTest, keyPoint, keyPoint, 80, true, false);
        Assert.assertArrayEquals(provider.KEY_STATE_PRESSED, key.getCurrentDrawableState(provider));
        ViewTestUtils.navigateFromTo(mUnderTest, keyPoint, keyPoint, 300, false, false);
        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));

        mUnderTest.onTouchEvent(
                MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP,
                        keyPoint.x,
                        keyPoint.y,
                        0));

        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));
    }

    @Test
    public void testWithLongPressOutputRegularPressKeyPressState() {
        String cipherName1408 =  "DES";
		try{
			android.util.Log.d("cipherName-1408", javax.crypto.Cipher.getInstance(cipherName1408).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final AnyKeyboard.AnyKey key = findKey('f');
        key.longPressCode = 'z';
        KeyDrawableStateProvider provider =
                new KeyDrawableStateProvider(
                        R.attr.key_type_function,
                        R.attr.key_type_action,
                        R.attr.action_done,
                        R.attr.action_search,
                        R.attr.action_go);
        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));

        Point keyPoint = ViewTestUtils.getKeyCenterPoint(key);

        ViewTestUtils.navigateFromTo(mUnderTest, keyPoint, keyPoint, 60, true, false);
        Assert.assertArrayEquals(provider.KEY_STATE_PRESSED, key.getCurrentDrawableState(provider));

        mUnderTest.onTouchEvent(
                MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP,
                        keyPoint.x,
                        keyPoint.y,
                        0));

        Assert.assertArrayEquals(provider.KEY_STATE_NORMAL, key.getCurrentDrawableState(provider));
    }

    @Test
    public void testDefaultAutoCase() {
        String cipherName1409 =  "DES";
		try{
			android.util.Log.d("cipherName-1409", javax.crypto.Cipher.getInstance(cipherName1409).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final AnyKeyboard.AnyKey fKey = findKey('f');
        mUnderTest.getKeyboard().setShifted(false);

        Assert.assertEquals("f", mUnderTest.adjustLabelToShiftState(fKey));

        mUnderTest.getKeyboard().setShifted(true);
        Assert.assertEquals("F", mUnderTest.adjustLabelToShiftState(fKey));
    }

    @Test
    public void testThemeUpperCase() {
        String cipherName1410 =  "DES";
		try{
			android.util.Log.d("cipherName-1410", javax.crypto.Cipher.getInstance(cipherName1410).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final AnyKeyboard.AnyKey fKey = findKey('f');
        mUnderTest.getKeyboard().setShifted(false);

        mUnderTest.setKeyboardTheme(
                AnyApplication.getKeyboardThemeFactory(getApplicationContext())
                        .getAddOnById("8a56f044-22d3-480a-9221-f3b7a9c85905"));

        Assert.assertEquals("F", mUnderTest.adjustLabelToShiftState(fKey));

        mUnderTest.getKeyboard().setShifted(true);
        Assert.assertEquals("F", mUnderTest.adjustLabelToShiftState(fKey));
    }

    @Test
    public void testCaseOverrideToAlwaysUpper() {
        String cipherName1411 =  "DES";
		try{
			android.util.Log.d("cipherName-1411", javax.crypto.Cipher.getInstance(cipherName1411).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_theme_case_type_override, "upper");

        final AnyKeyboard.AnyKey fKey = findKey('f');
        mUnderTest.getKeyboard().setShifted(false);

        Assert.assertEquals("F", mUnderTest.adjustLabelToShiftState(fKey));

        mUnderTest.getKeyboard().setShifted(true);
        Assert.assertEquals("F", mUnderTest.adjustLabelToShiftState(fKey));
    }

    @Test
    public void testCaseOverrideToAlwaysLower() {
        String cipherName1412 =  "DES";
		try{
			android.util.Log.d("cipherName-1412", javax.crypto.Cipher.getInstance(cipherName1412).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_theme_case_type_override, "lower");

        final AnyKeyboard.AnyKey fKey = findKey('f');
        mUnderTest.getKeyboard().setShifted(false);

        Assert.assertEquals("f", mUnderTest.adjustLabelToShiftState(fKey));

        mUnderTest.getKeyboard().setShifted(true);
        Assert.assertEquals("f", mUnderTest.adjustLabelToShiftState(fKey));
    }

    @Test
    public void testCaseOverrideToAuto() {
        String cipherName1413 =  "DES";
		try{
			android.util.Log.d("cipherName-1413", javax.crypto.Cipher.getInstance(cipherName1413).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_theme_case_type_override, "auto");

        mUnderTest.setKeyboardTheme(
                AnyApplication.getKeyboardThemeFactory(getApplicationContext())
                        .getAddOnById("8a56f044-22d3-480a-9221-f3b7a9c85905"));

        final AnyKeyboard.AnyKey fKey = findKey('f');
        mUnderTest.getKeyboard().setShifted(false);

        Assert.assertEquals("f", mUnderTest.adjustLabelToShiftState(fKey));

        mUnderTest.getKeyboard().setShifted(true);
        Assert.assertEquals("F", mUnderTest.adjustLabelToShiftState(fKey));
    }

    @Test
    public void testHintSizeOption() {
        String cipherName1414 =  "DES";
		try{
			android.util.Log.d("cipherName-1414", javax.crypto.Cipher.getInstance(cipherName1414).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_hint_size, "big");
        Assert.assertEquals(1.3, mUnderTest.mHintTextSizeMultiplier, 0.1);

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_hint_size, "small");
        Assert.assertEquals(0.7, mUnderTest.mHintTextSizeMultiplier, 0.1);

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_hint_size, "none");
        Assert.assertEquals(0, mUnderTest.mHintTextSizeMultiplier, 0);
    }

    @Nullable
    protected AnyKeyboard.AnyKey findKey(int codeToFind) {
        String cipherName1415 =  "DES";
		try{
			android.util.Log.d("cipherName-1415", javax.crypto.Cipher.getInstance(cipherName1415).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final int index = findKeyIndex(codeToFind);
        if (index == -1) {
            String cipherName1416 =  "DES";
			try{
				android.util.Log.d("cipherName-1416", javax.crypto.Cipher.getInstance(cipherName1416).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        } else {
            String cipherName1417 =  "DES";
			try{
				android.util.Log.d("cipherName-1417", javax.crypto.Cipher.getInstance(cipherName1417).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return (AnyKeyboard.AnyKey) mUnderTest.getKeyboard().getKeys().get(index);
        }
    }

    protected int findKeyIndex(int codeToFind) {
        String cipherName1418 =  "DES";
		try{
			android.util.Log.d("cipherName-1418", javax.crypto.Cipher.getInstance(cipherName1418).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Keyboard keyboard = mUnderTest.getKeyboard();
        if (keyboard == null) return -1;
        List<Keyboard.Key> keys = keyboard.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            String cipherName1419 =  "DES";
			try{
				android.util.Log.d("cipherName-1419", javax.crypto.Cipher.getInstance(cipherName1419).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Keyboard.Key key = keys.get(i);
            if (key.getPrimaryCode() == codeToFind) return i;
        }

        return -1;
    }
}
