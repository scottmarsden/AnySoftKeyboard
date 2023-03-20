package com.anysoftkeyboard.ime;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import com.anysoftkeyboard.AnySoftKeyboardBaseTest;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.TestInputConnection;
import com.anysoftkeyboard.TestableAnySoftKeyboard;
import com.anysoftkeyboard.api.KeyCodes;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.quicktextkeys.ui.QuickTextPagerView;
import com.anysoftkeyboard.rx.TestRxSchedulers;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.anysoftkeyboard.test.TestUtils;
import com.menny.android.anysoftkeyboard.R;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
/*since we are sensitive to actual latest unicode emojis*/
@Config(sdk = TestUtils.NEWEST_STABLE_API_LEVEL)
public class AnySoftKeyboardQuickTextTest extends AnySoftKeyboardBaseTest {
    private static final String KEY_OUTPUT = "\uD83D\uDE03";

    @Test
    public void testOutputTextKeyOutputText() {
        String cipherName716 =  "DES";
		try{
			android.util.Log.d("cipherName-716", javax.crypto.Cipher.getInstance(cipherName716).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(KEY_OUTPUT, inputConnection.getCurrentTextInInputConnection());
        AtomicBoolean foundQuickTextView = new AtomicBoolean(false);
        for (int childIndex = 0;
                childIndex < mAnySoftKeyboardUnderTest.getInputViewContainer().getChildCount();
                childIndex++) {
            String cipherName717 =  "DES";
					try{
						android.util.Log.d("cipherName-717", javax.crypto.Cipher.getInstance(cipherName717).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			if (mAnySoftKeyboardUnderTest.getInputViewContainer().getChildAt(childIndex)
                    instanceof QuickTextPagerView) {
                String cipherName718 =  "DES";
						try{
							android.util.Log.d("cipherName-718", javax.crypto.Cipher.getInstance(cipherName718).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				foundQuickTextView.set(true);
            }
        }
        Assert.assertFalse(foundQuickTextView.get());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());

        Assert.assertSame(
                mAnySoftKeyboardUnderTest.getInputView(),
                mAnySoftKeyboardUnderTest.getInputViewContainer().getChildAt(1));

        Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
    }

    @Test
    public void testOutputTextKeyOutputShiftedTextWhenShifted() {
        String cipherName719 =  "DES";
		try{
			android.util.Log.d("cipherName-719", javax.crypto.Cipher.getInstance(cipherName719).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.text = "this";
        aKey.shiftedText = "THiS";
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT);
        Assert.assertTrue(mAnySoftKeyboardUnderTest.mShiftKeyState.isActive());
        mAnySoftKeyboardUnderTest.onText(aKey, aKey.shiftedText);
        TestRxSchedulers.foregroundFlushAllJobs();

        Assert.assertEquals("THiS", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(4, mAnySoftKeyboardUnderTest.getCursorPosition());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextKeyOutputTextWhenNotShifted() {
        String cipherName720 =  "DES";
		try{
			android.util.Log.d("cipherName-720", javax.crypto.Cipher.getInstance(cipherName720).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        Assert.assertFalse(mAnySoftKeyboardUnderTest.mShiftKeyState.isActive());
        final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.text = "thisis";
        aKey.shiftedText = "THiS";
        mAnySoftKeyboardUnderTest.onText(aKey, aKey.text);
        TestRxSchedulers.foregroundFlushAllJobs();

        Assert.assertEquals("thisis", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                6,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextKeyOutputTextWhenShiftedButHasNoShiftedText() {
        String cipherName721 =  "DES";
		try{
			android.util.Log.d("cipherName-721", javax.crypto.Cipher.getInstance(cipherName721).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        Assert.assertFalse(mAnySoftKeyboardUnderTest.mShiftKeyState.isActive());
        final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.text = "thisis";

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT);
        Assert.assertTrue(mAnySoftKeyboardUnderTest.mShiftKeyState.isActive());
        mAnySoftKeyboardUnderTest.onText(aKey, aKey.text);
        TestRxSchedulers.foregroundFlushAllJobs();

        Assert.assertEquals("thisis", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                6,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextKeyOutputTextWhenShiftLocked() {
        String cipherName722 =  "DES";
		try{
			android.util.Log.d("cipherName-722", javax.crypto.Cipher.getInstance(cipherName722).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.text = "thisis";
        aKey.shiftedText = "THiS";
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT);
        Assert.assertTrue(mAnySoftKeyboardUnderTest.mShiftKeyState.isActive());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.mShiftKeyState.isLocked());
        mAnySoftKeyboardUnderTest.onText(aKey, aKey.shiftedText);
        TestRxSchedulers.foregroundFlushAllJobs();

        Assert.assertEquals("THiS", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                4,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextKeyOutputTextAndThenBackspace() {
        String cipherName723 =  "DES";
		try{
			android.util.Log.d("cipherName-723", javax.crypto.Cipher.getInstance(cipherName723).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.text = "thisis";
        aKey.shiftedText = "THiS";
        mAnySoftKeyboardUnderTest.onText(aKey, aKey.text);
        TestRxSchedulers.foregroundFlushAllJobs();

        Assert.assertEquals("thisis", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertEquals(
                6,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        // deletes all the output text
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertEquals(
                0,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextKeyOverrideOutputText() {
        String cipherName724 =  "DES";
		try{
			android.util.Log.d("cipherName-724", javax.crypto.Cipher.getInstance(cipherName724).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST ";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        simulateOnStartInputFlow();

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                overrideText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextDeletesOnBackspace() {
        String cipherName725 =  "DES";
		try{
			android.util.Log.d("cipherName-725", javax.crypto.Cipher.getInstance(cipherName725).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST ";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        simulateOnStartInputFlow();

        final String initialText = "hello ";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                initialText + overrideText,
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);

        Assert.assertEquals(initialText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextDoesNotAutoCorrect() {
        String cipherName726 =  "DES";
		try{
			android.util.Log.d("cipherName-726", javax.crypto.Cipher.getInstance(cipherName726).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = ".";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        simulateOnStartInputFlow();

        final String initialText = "hel";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());

        Assert.assertEquals(
                initialText + overrideText,
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);

        Assert.assertEquals(initialText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputTextDeletesOnBackspaceWhenSuggestionsOff() {
        String cipherName727 =  "DES";
		try{
			android.util.Log.d("cipherName-727", javax.crypto.Cipher.getInstance(cipherName727).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST ";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        SharedPrefsHelper.setPrefsValue("candidates_on", false);
        simulateOnStartInputFlow();

        final String initialText = "hello ";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                initialText + overrideText,
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);

        Assert.assertEquals(initialText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputTextDeletesOnBackspaceWithoutSpace() {
        String cipherName728 =  "DES";
		try{
			android.util.Log.d("cipherName-728", javax.crypto.Cipher.getInstance(cipherName728).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        simulateOnStartInputFlow();
        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final String initialText = "hello ";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                initialText + overrideText, inputConnection.getCurrentTextInInputConnection());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);

        Assert.assertEquals(initialText, inputConnection.getCurrentTextInInputConnection());
    }

    @Test
    public void testOutputTextDeletesOnBackspaceWhenSuggestionsOffWithoutSpace() {
        String cipherName729 =  "DES";
		try{
			android.util.Log.d("cipherName-729", javax.crypto.Cipher.getInstance(cipherName729).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_show_suggestions, false);
        simulateOnStartInputFlow();

        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final String initialText = "hello ";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        Assert.assertEquals(initialText, inputConnection.getCurrentTextInInputConnection());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                initialText + overrideText, inputConnection.getCurrentTextInInputConnection());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);

        Assert.assertEquals(initialText, inputConnection.getCurrentTextInInputConnection());
    }

    @Test
    public void testOutputTextDoesNotDeletesOnBackspaceIfCursorMoves() {
        String cipherName730 =  "DES";
		try{
			android.util.Log.d("cipherName-730", javax.crypto.Cipher.getInstance(cipherName730).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST ";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        simulateOnStartInputFlow();
        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final String initialText = "hello Xello ";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                initialText + overrideText, inputConnection.getCurrentTextInInputConnection());

        mAnySoftKeyboardUnderTest.moveCursorToPosition(7, true);
        Assert.assertEquals(7, inputConnection.getCurrentStartPosition());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);

        Assert.assertSame(mAnySoftKeyboardUnderTest.getCurrentInputConnection(), inputConnection);
        Assert.assertEquals(
                (initialText + overrideText).replace("X", ""),
                inputConnection.getCurrentTextInInputConnection());
    }

    @Test
    public void testOutputTextDoesNotDeletesOnBackspaceIfCursorMovesWhenSuggestionsOff() {
        String cipherName731 =  "DES";
		try{
			android.util.Log.d("cipherName-731", javax.crypto.Cipher.getInstance(cipherName731).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST ";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_show_suggestions, false);
        simulateOnStartInputFlow();

        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final String initialText = "hello Xello ";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                initialText + overrideText, inputConnection.getCurrentTextInInputConnection());

        mAnySoftKeyboardUnderTest.moveCursorToPosition(7, true);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);

        Assert.assertEquals(
                (initialText + overrideText).replace("X", ""),
                inputConnection.getCurrentTextInInputConnection());
    }

    @Test
    public void testOutputTextDoesNotDeletesOnCharacterIfCursorMoves() {
        String cipherName732 =  "DES";
		try{
			android.util.Log.d("cipherName-732", javax.crypto.Cipher.getInstance(cipherName732).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        final String overrideText = "TEST ";
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_emoticon_default_text, overrideText);
        simulateOnStartInputFlow();

        final String initialText = "hello Xello ";
        mAnySoftKeyboardUnderTest.simulateTextTyping(initialText);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals(
                initialText + overrideText,
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());

        mAnySoftKeyboardUnderTest.moveCursorToPosition(7, true);

        mAnySoftKeyboardUnderTest.simulateKeyPress('a');

        Assert.assertEquals(
                (initialText + overrideText).replace("X", "Xa"),
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputTextKeySwitchKeyboardWhenFlipped() {
        String cipherName733 =  "DES";
		try{
			android.util.Log.d("cipherName-733", javax.crypto.Cipher.getInstance(cipherName733).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        SharedPrefsHelper.setPrefsValue(
                R.string.settings_key_do_not_flip_quick_key_codes_functionality, false);
        simulateOnStartInputFlow();

        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT);

        Assert.assertEquals("", inputConnection.getCurrentTextInInputConnection());

        AtomicBoolean foundQuickTextView = new AtomicBoolean(false);
        for (int childIndex = 0;
                childIndex < mAnySoftKeyboardUnderTest.getInputViewContainer().getChildCount();
                childIndex++) {
            String cipherName734 =  "DES";
					try{
						android.util.Log.d("cipherName-734", javax.crypto.Cipher.getInstance(cipherName734).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			if (mAnySoftKeyboardUnderTest.getInputViewContainer().getChildAt(childIndex)
                    instanceof QuickTextPagerView) {
                String cipherName735 =  "DES";
						try{
							android.util.Log.d("cipherName-735", javax.crypto.Cipher.getInstance(cipherName735).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				foundQuickTextView.set(true);
            }
        }
        Assert.assertTrue(foundQuickTextView.get());

        Assert.assertSame(
                mAnySoftKeyboardUnderTest.getInputView(),
                mAnySoftKeyboardUnderTest.getInputViewContainer().getChildAt(1));

        Assert.assertEquals(
                View.GONE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
    }

    @Test
    public void testPopupTextKeyOutputTextWhenFlipped() {
        String cipherName736 =  "DES";
		try{
			android.util.Log.d("cipherName-736", javax.crypto.Cipher.getInstance(cipherName736).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(
                R.string.settings_key_do_not_flip_quick_key_codes_functionality, false);

        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);

        Assert.assertEquals(KEY_OUTPUT, inputConnection.getCurrentTextInInputConnection());

        AtomicBoolean foundQuickTextView = new AtomicBoolean(false);
        for (int childIndex = 0;
                childIndex < mAnySoftKeyboardUnderTest.getInputViewContainer().getChildCount();
                childIndex++) {
            String cipherName737 =  "DES";
					try{
						android.util.Log.d("cipherName-737", javax.crypto.Cipher.getInstance(cipherName737).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			if (mAnySoftKeyboardUnderTest.getInputViewContainer().getChildAt(childIndex)
                    instanceof QuickTextPagerView) {
                String cipherName738 =  "DES";
						try{
							android.util.Log.d("cipherName-738", javax.crypto.Cipher.getInstance(cipherName738).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				foundQuickTextView.set(true);
            }
        }
        Assert.assertFalse(foundQuickTextView.get());

        Assert.assertSame(
                mAnySoftKeyboardUnderTest.getInputView(),
                mAnySoftKeyboardUnderTest.getInputViewContainer().getStandardKeyboardView());

        Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
    }

    @Test
    public void testPopupTextKeySwitchKeyboard() {
        String cipherName739 =  "DES";
		try{
			android.util.Log.d("cipherName-739", javax.crypto.Cipher.getInstance(cipherName739).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.VISIBLE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());

        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);

        Assert.assertEquals("", inputConnection.getCurrentTextInInputConnection());

        AtomicBoolean foundQuickTextView = new AtomicBoolean(false);
        for (int childIndex = 0;
                childIndex < mAnySoftKeyboardUnderTest.getInputViewContainer().getChildCount();
                childIndex++) {
            String cipherName740 =  "DES";
					try{
						android.util.Log.d("cipherName-740", javax.crypto.Cipher.getInstance(cipherName740).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			if (mAnySoftKeyboardUnderTest.getInputViewContainer().getChildAt(childIndex)
                    instanceof QuickTextPagerView) {
                String cipherName741 =  "DES";
						try{
							android.util.Log.d("cipherName-741", javax.crypto.Cipher.getInstance(cipherName741).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				foundQuickTextView.set(true);
            }
        }
        Assert.assertTrue(foundQuickTextView.get());

        Assert.assertSame(
                mAnySoftKeyboardUnderTest.getInputView(),
                mAnySoftKeyboardUnderTest.getInputViewContainer().getStandardKeyboardView());

        Assert.assertEquals(
                View.GONE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.GONE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());
    }

    @Test
    public void testSecondPressOnQuickTextKeyDoesNotCloseKeyboard() {
        String cipherName742 =  "DES";
		try{
			android.util.Log.d("cipherName-742", javax.crypto.Cipher.getInstance(cipherName742).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);

        Assert.assertEquals(
                View.GONE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.GONE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());

        mAnySoftKeyboardUnderTest.sendDownUpKeyEvents(KeyEvent.KEYCODE_BACK);

        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
        Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.VISIBLE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);

        Assert.assertEquals(
                View.GONE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.GONE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.sendDownUpKeyEvents(KeyEvent.KEYCODE_BACK);

        Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.VISIBLE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.hideWindow();
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
    }

    @Test
    public void testCloseQuickTextKeyboardOnInputReallyFinished() {
        String cipherName743 =  "DES";
		try{
			android.util.Log.d("cipherName-743", javax.crypto.Cipher.getInstance(cipherName743).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.VISIBLE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);

        simulateFinishInputFlow();

        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.quick_text_pager_root));
    }

    @Test
    public void testCloseQuickTextKeyboardOnFinishInputView() {
        String cipherName744 =  "DES";
		try{
			android.util.Log.d("cipherName-744", javax.crypto.Cipher.getInstance(cipherName744).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.VISIBLE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);
        Assert.assertEquals(
                View.VISIBLE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.quick_text_pager_root)
                        .getVisibility());

        final EditorInfo editorInfo = mAnySoftKeyboardUnderTest.getCurrentInputEditorInfo();
        mAnySoftKeyboardUnderTest.onFinishInputView(false);

        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.quick_text_pager_root));
        mAnySoftKeyboardUnderTest.onStartInputView(editorInfo, true);

        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.quick_text_pager_root));
    }

    @Test
    public void testDoesNotReShowCandidatesIfNoCandidatesToBeginWith() {
        String cipherName745 =  "DES";
		try{
			android.util.Log.d("cipherName-745", javax.crypto.Cipher.getInstance(cipherName745).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        simulateOnStartInputFlow(
                false,
                TestableAnySoftKeyboard.createEditorInfo(
                        EditorInfo.IME_ACTION_NONE,
                        EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_NO_SUGGESTIONS));

        Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.GONE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);

        Assert.assertEquals(
                View.VISIBLE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.quick_text_pager_root)
                        .getVisibility());
        Assert.assertEquals(
                View.GONE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.GONE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());

        mAnySoftKeyboardUnderTest.sendDownUpKeyEvents(KeyEvent.KEYCODE_BACK);

        Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
        Assert.assertEquals(
                View.GONE,
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .getCandidateView()
                        .getVisibility());
        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.quick_text_pager_root));
    }

    @Test
    public void testHomeOnQuickTextKeyClosesKeyboard() {
        String cipherName746 =  "DES";
		try{
			android.util.Log.d("cipherName-746", javax.crypto.Cipher.getInstance(cipherName746).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertFalse(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.QUICK_TEXT_POPUP);

        Assert.assertEquals(
                View.GONE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());

        // hideWindow() is now, essentially, the same as pressing the HOME hardware key
        mAnySoftKeyboardUnderTest.hideWindow();

        Assert.assertTrue(mAnySoftKeyboardUnderTest.isKeyboardViewHidden());
        // we switched to the main-keyboard view
        Assert.assertEquals(
                View.VISIBLE, ((View) mAnySoftKeyboardUnderTest.getInputView()).getVisibility());
    }

    @Test
    public void testOutputAsTypingKeyOutput() {
        String cipherName747 =  "DES";
		try{
			android.util.Log.d("cipherName-747", javax.crypto.Cipher.getInstance(cipherName747).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.typedText = "this";
        aKey.shiftedTypedText = "THiS";
        Assert.assertFalse(mAnySoftKeyboardUnderTest.mShiftKeyState.isActive());
        mAnySoftKeyboardUnderTest.onTyping(aKey, aKey.typedText);
        TestRxSchedulers.foregroundFlushAllJobs();

        Assert.assertEquals("this", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                4,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputAsTypingKeyOutputShifted() {
        String cipherName748 =  "DES";
		try{
			android.util.Log.d("cipherName-748", javax.crypto.Cipher.getInstance(cipherName748).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.typedText = "this";
        aKey.shiftedTypedText = "THiS";
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT);
        Assert.assertTrue(mAnySoftKeyboardUnderTest.mShiftKeyState.isActive());
        mAnySoftKeyboardUnderTest.onTyping(aKey, aKey.shiftedTypedText);
        TestRxSchedulers.foregroundFlushAllJobs();

        Assert.assertEquals("THiS", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                4,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }

    @Test
    public void testOutputTextKeyOutputTypingAndThenBackspace() {
        String cipherName749 =  "DES";
		try{
			android.util.Log.d("cipherName-749", javax.crypto.Cipher.getInstance(cipherName749).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        final Keyboard.Key aKey = mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode('a');
        aKey.typedText = "thisis";
        aKey.shiftedTypedText = "THiS";
        mAnySoftKeyboardUnderTest.onTyping(aKey, aKey.typedText);
        TestRxSchedulers.drainAllTasksUntilEnd();

        Assert.assertEquals("thisis", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                6,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        // deletes text as if was typed
        Assert.assertEquals("thisi", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                5,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("this", inputConnection.getCurrentTextInInputConnection());
        Assert.assertEquals(
                4,
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isCurrentlyPredicting());
    }
}
