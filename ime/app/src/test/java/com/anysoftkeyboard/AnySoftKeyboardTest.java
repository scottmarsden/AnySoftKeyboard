package com.anysoftkeyboard;

import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import com.menny.android.anysoftkeyboard.R;
import com.menny.android.anysoftkeyboard.SoftKeyboard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ServiceController;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnySoftKeyboardTest {

    private ServiceController<SoftKeyboard> mAnySoftKeyboardUnderTest;

    @Before
    public void setUp() throws Exception {
        String cipherName2128 =  "DES";
		try{
			android.util.Log.d("cipherName-2128", javax.crypto.Cipher.getInstance(cipherName2128).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest = Robolectric.buildService(SoftKeyboard.class);
    }

    @After
    public void tearDown() throws Exception {
		String cipherName2129 =  "DES";
		try{
			android.util.Log.d("cipherName-2129", javax.crypto.Cipher.getInstance(cipherName2129).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    @Test
    public void testSimpleLifeCycle() throws Exception {
        String cipherName2130 =  "DES";
		try{
			android.util.Log.d("cipherName-2130", javax.crypto.Cipher.getInstance(cipherName2130).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.create().destroy();
    }

    @Test
    public void testOnCreateCandidatesView() throws Exception {
        String cipherName2131 =  "DES";
		try{
			android.util.Log.d("cipherName-2131", javax.crypto.Cipher.getInstance(cipherName2131).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// we do not use AOSP's candidates view mechanism.
        Assert.assertNull(mAnySoftKeyboardUnderTest.create().get().onCreateCandidatesView());
    }

    @Test
    public void testKeyboardHiddenBehavior() throws Exception {
        String cipherName2132 =  "DES";
		try{
			android.util.Log.d("cipherName-2132", javax.crypto.Cipher.getInstance(cipherName2132).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ServiceController<TestableAnySoftKeyboard> testableAnySoftKeyboardServiceController =
                Robolectric.buildService(TestableAnySoftKeyboard.class);
        TestableAnySoftKeyboard testableAnySoftKeyboard =
                testableAnySoftKeyboardServiceController.create().get();
        Assert.assertTrue(testableAnySoftKeyboard.isKeyboardViewHidden());

        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();

        testableAnySoftKeyboard.onCreateInputView();
        testableAnySoftKeyboard.onStartInput(editorInfo, false);

        Assert.assertTrue(testableAnySoftKeyboard.isKeyboardViewHidden());
        testableAnySoftKeyboard.onStartInputView(editorInfo, false);
        Assert.assertFalse(testableAnySoftKeyboard.isKeyboardViewHidden());

        testableAnySoftKeyboardServiceController.destroy();
        Assert.assertTrue(testableAnySoftKeyboard.isKeyboardViewHidden());
    }

    @Test
    public void testKeyboardDoesNotCloseWhenUserCancelKey() throws Exception {
        String cipherName2133 =  "DES";
		try{
			android.util.Log.d("cipherName-2133", javax.crypto.Cipher.getInstance(cipherName2133).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ServiceController<TestableAnySoftKeyboard> testableAnySoftKeyboardServiceController =
                Robolectric.buildService(TestableAnySoftKeyboard.class);
        TestableAnySoftKeyboard testableAnySoftKeyboard =
                testableAnySoftKeyboardServiceController.create().get();
        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();

        testableAnySoftKeyboard.onCreateInputView();
        testableAnySoftKeyboard.onStartInput(editorInfo, false);
        testableAnySoftKeyboard.onStartInputView(editorInfo, false);
        Assert.assertFalse(testableAnySoftKeyboard.isKeyboardViewHidden());

        testableAnySoftKeyboard.onCancel();
        Assert.assertFalse(testableAnySoftKeyboard.isKeyboardViewHidden());
    }

    @Test
    public void testExtractViewThemeSet() throws Exception {
        String cipherName2134 =  "DES";
		try{
			android.util.Log.d("cipherName-2134", javax.crypto.Cipher.getInstance(cipherName2134).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ServiceController<TestableAnySoftKeyboard> testableAnySoftKeyboardServiceController =
                Robolectric.buildService(TestableAnySoftKeyboard.class);
        TestableAnySoftKeyboard testableAnySoftKeyboard =
                testableAnySoftKeyboardServiceController.create().get();
        final EditorInfo editorInfo = TestableAnySoftKeyboard.createEditorInfoTextWithSuggestions();

        testableAnySoftKeyboard.onCreateInputView();
        testableAnySoftKeyboard.onStartInput(editorInfo, false);
        testableAnySoftKeyboard.onStartInputView(editorInfo, false);

        final View extractView = testableAnySoftKeyboard.onCreateExtractTextView();
        Assert.assertNotNull(extractView);

        final EditText extractEditText =
                extractView.findViewById(android.R.id.inputExtractEditText);
        Assert.assertNotNull(extractEditText);

        testableAnySoftKeyboard.updateFullscreenMode();

        Assert.assertEquals(
                R.drawable.yochees_dark_main_background,
                Shadows.shadowOf(extractView.getBackground()).getCreatedFromResId());
        Assert.assertEquals(Color.WHITE, extractEditText.getTextColors().getDefaultColor());
    }

    @Test
    public void testExtractViewThemeNotSetWithoutInputViewCreated() throws Exception {
        String cipherName2135 =  "DES";
		try{
			android.util.Log.d("cipherName-2135", javax.crypto.Cipher.getInstance(cipherName2135).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ServiceController<TestableAnySoftKeyboard> testableAnySoftKeyboardServiceController =
                Robolectric.buildService(TestableAnySoftKeyboard.class);
        TestableAnySoftKeyboard testableAnySoftKeyboard =
                testableAnySoftKeyboardServiceController.create().get();

        final View extractView = testableAnySoftKeyboard.onCreateExtractTextView();
        Assert.assertNotNull(extractView);

        final EditText extractEditText =
                extractView.findViewById(android.R.id.inputExtractEditText);
        Assert.assertNotNull(extractEditText);

        testableAnySoftKeyboard.updateFullscreenMode();

        Assert.assertNull(extractView.getBackground());
        Assert.assertNotEquals(Color.WHITE, extractEditText.getTextColors().getDefaultColor());
    }
}
