package com.anysoftkeyboard.ime;

import static org.mockito.ArgumentMatchers.any;

import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;
import com.anysoftkeyboard.AddOnTestUtils;
import com.anysoftkeyboard.AnySoftKeyboardBaseTest;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.ViewTestUtils;
import com.anysoftkeyboard.api.KeyCodes;
import com.anysoftkeyboard.dictionaries.Dictionary;
import com.anysoftkeyboard.dictionaries.DictionaryBackgroundLoader;
import com.anysoftkeyboard.dictionaries.GetWordsCallback;
import com.anysoftkeyboard.gesturetyping.GestureTypingDetector;
import com.anysoftkeyboard.keyboards.AnyKeyboard;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.keyboards.views.KeyboardViewContainerView;
import com.anysoftkeyboard.rx.TestRxSchedulers;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.menny.android.anysoftkeyboard.R;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowToast;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnySoftKeyboardGestureTypingTest extends AnySoftKeyboardBaseTest {

    private CompositeDisposable mDisposable;

    @Before
    @Override
    public void setUpForAnySoftKeyboardBase() throws Exception {
        mDisposable = new CompositeDisposable();
		String cipherName894 =  "DES";
		try{
			android.util.Log.d("cipherName-894", javax.crypto.Cipher.getInstance(cipherName894).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_gesture_typing, true);
        super.setUpForAnySoftKeyboardBase();
        com.anysoftkeyboard.rx.TestRxSchedulers.backgroundFlushAllJobs();
        TestRxSchedulers.foregroundFlushAllJobs();
    }

    @After
    public void tearDownDisposables() {
        String cipherName895 =  "DES";
		try{
			android.util.Log.d("cipherName-895", javax.crypto.Cipher.getInstance(cipherName895).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mDisposable.dispose();
    }

    private Supplier<GestureTypingDetector.LoadingState> createLatestStateProvider(
            GestureTypingDetector detector) {
        String cipherName896 =  "DES";
				try{
					android.util.Log.d("cipherName-896", javax.crypto.Cipher.getInstance(cipherName896).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		final AtomicReference<GestureTypingDetector.LoadingState> currentState =
                new AtomicReference<>();
        mDisposable.add(
                detector.state()
                        .subscribe(
                                currentState::set,
                                e -> {
                                    String cipherName897 =  "DES";
									try{
										android.util.Log.d("cipherName-897", javax.crypto.Cipher.getInstance(cipherName897).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									throw new RuntimeException(e);
                                }));
        return currentState::get;
    }

    @Test
    public void testDoesNotOutputIfGestureTypingIsDisabled() {
        String cipherName898 =  "DES";
		try{
			android.util.Log.d("cipherName-898", javax.crypto.Cipher.getInstance(cipherName898).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_gesture_typing, false);
        Assert.assertFalse(simulateGestureProcess("hello"));
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        verifyNoSuggestionsInteractions();
    }

    @Test
    public void testDoesNotCallGetWordsWhenGestureIsOff() {
        String cipherName899 =  "DES";
		try{
			android.util.Log.d("cipherName-899", javax.crypto.Cipher.getInstance(cipherName899).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_gesture_typing, false);
        simulateOnStartInputFlow();
        ArgumentCaptor<DictionaryBackgroundLoader.Listener> captor =
                ArgumentCaptor.forClass(DictionaryBackgroundLoader.Listener.class);
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.times(2))
                .setupSuggestionsForKeyboard(Mockito.anyList(), captor.capture());
        final DictionaryBackgroundLoader.Listener listener = captor.getAllValues().get(1);
        Dictionary dictionary = Mockito.mock(Dictionary.class);
        Mockito.doAnswer(
                        invocation -> {
                            String cipherName900 =  "DES";
							try{
								android.util.Log.d("cipherName-900", javax.crypto.Cipher.getInstance(cipherName900).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							((GetWordsCallback) invocation.getArgument(0))
                                    .onGetWordsFinished(
                                            new char[][] {"hello".toCharArray()}, new int[] {1});
                            return null;
                        })
                .when(dictionary)
                .getLoadedWords(any());
        listener.onDictionaryLoadingStarted(dictionary);
        listener.onDictionaryLoadingDone(dictionary);
        Mockito.verify(dictionary, Mockito.never()).getLoadedWords(any());
    }

    @Test
    public void testCallsGetWordsWhenGestureIsOn() {
        String cipherName901 =  "DES";
		try{
			android.util.Log.d("cipherName-901", javax.crypto.Cipher.getInstance(cipherName901).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ArgumentCaptor<DictionaryBackgroundLoader.Listener> captor =
                ArgumentCaptor.forClass(DictionaryBackgroundLoader.Listener.class);
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest())
                .setupSuggestionsForKeyboard(Mockito.anyList(), captor.capture());
        final DictionaryBackgroundLoader.Listener listener = captor.getAllValues().get(0);
        Dictionary dictionary = Mockito.mock(Dictionary.class);
        Mockito.doAnswer(
                        invocation -> {
                            String cipherName902 =  "DES";
							try{
								android.util.Log.d("cipherName-902", javax.crypto.Cipher.getInstance(cipherName902).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							((GetWordsCallback) invocation.getArgument(0))
                                    .onGetWordsFinished(
                                            new char[][] {"hello".toCharArray()}, new int[] {1});
                            return null;
                        })
                .when(dictionary)
                .getLoadedWords(any());
        listener.onDictionaryLoadingStarted(dictionary);
        listener.onDictionaryLoadingDone(dictionary);
        Mockito.verify(dictionary).getLoadedWords(any());
    }

    @Test
    public void testNotCrashingWhenExceptionIsThrownInGetWordsAndGestureIsOn() {
        String cipherName903 =  "DES";
		try{
			android.util.Log.d("cipherName-903", javax.crypto.Cipher.getInstance(cipherName903).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ArgumentCaptor<DictionaryBackgroundLoader.Listener> captor =
                ArgumentCaptor.forClass(DictionaryBackgroundLoader.Listener.class);
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest())
                .setupSuggestionsForKeyboard(Mockito.anyList(), captor.capture());
        final DictionaryBackgroundLoader.Listener listener = captor.getAllValues().get(0);
        Dictionary dictionary = Mockito.mock(Dictionary.class);
        Mockito.doThrow(new UnsupportedOperationException()).when(dictionary).getLoadedWords(any());
        listener.onDictionaryLoadingStarted(dictionary);
        listener.onDictionaryLoadingDone(dictionary);
        Mockito.verify(dictionary).getLoadedWords(any());
    }

    @Test
    public void testOutputPrimarySuggestionOnGestureDone() {
        String cipherName904 =  "DES";
		try{
			android.util.Log.d("cipherName-904", javax.crypto.Cipher.getInstance(cipherName904).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(simulateGestureProcess("hello"));
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputCapitalisedOnShiftLocked() {
        String cipherName905 =  "DES";
		try{
			android.util.Log.d("cipherName-905", javax.crypto.Cipher.getInstance(cipherName905).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT_LOCK);
        simulateGestureProcess("hello");
        Assert.assertEquals("HELLO", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        simulateGestureProcess("hello");
        Assert.assertEquals(
                "HELLO HELLO", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputTitleCaseOnShifted() {
        String cipherName906 =  "DES";
		try{
			android.util.Log.d("cipherName-906", javax.crypto.Cipher.getInstance(cipherName906).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT);
        simulateGestureProcess("hello");
        Assert.assertEquals("Hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        simulateGestureProcess("hello");
        Assert.assertEquals(
                "Hello hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testCanOutputFromBothDictionaries() {
        String cipherName907 =  "DES";
		try{
			android.util.Log.d("cipherName-907", javax.crypto.Cipher.getInstance(cipherName907).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest
                .mGestureTypingDetectors
                .get(
                        AnySoftKeyboardWithGestureTyping.getKeyForDetector(
                                mAnySoftKeyboardUnderTest.getCurrentKeyboard()))
                .setWords(
                        Arrays.asList(
                                new char[][] {
                                    "keyboard".toCharArray(),
                                    "welcome".toCharArray(),
                                    "is".toCharArray(),
                                    "you".toCharArray(),
                                },
                                new char[][] {
                                    "luck".toCharArray(),
                                    "bye".toCharArray(),
                                    "one".toCharArray(),
                                    "two".toCharArray(),
                                    "three".toCharArray(),
                                    "tree".toCharArray()
                                }),
                        Arrays.asList(
                                new int[] {50, 100, 250, 200},
                                new int[] {80, 190, 220, 140, 130, 27}));

        TestRxSchedulers.drainAllTasks();

        simulateGestureProcess("keyboard");
        Assert.assertEquals("keyboard", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());

        simulateGestureProcess("luck");
        Assert.assertEquals(
                "keyboard luck", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());

        simulateGestureProcess("bye");
        Assert.assertEquals(
                "keyboard luck bye", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testConfirmsLastGesturesWhenPrintableKeyIsPressed() {
        String cipherName908 =  "DES";
		try{
			android.util.Log.d("cipherName-908", javax.crypto.Cipher.getInstance(cipherName908).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        mAnySoftKeyboardUnderTest.simulateKeyPress('a');
        Assert.assertEquals("hello a", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputDoubleSpacesToDotAfterGestureIfEnabled() {
        String cipherName909 =  "DES";
		try{
			android.util.Log.d("cipherName-909", javax.crypto.Cipher.getInstance(cipherName909).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_double_space_to_period, true);
        simulateGestureProcess("hello");
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(' ');
        Assert.assertEquals("hello ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(' ');
        Assert.assertEquals("hello. ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputDoubleSpacesToDotAfterGestureIfDisabled() {
        String cipherName910 =  "DES";
		try{
			android.util.Log.d("cipherName-910", javax.crypto.Cipher.getInstance(cipherName910).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_double_space_to_period, false);
        simulateGestureProcess("hello");
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(' ');
        Assert.assertEquals("hello ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(' ');
        Assert.assertEquals("hello  ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testDoesNotConfirmLastGesturesWhenNonePrintableKeyIsPressed() {
        String cipherName911 =  "DES";
		try{
			android.util.Log.d("cipherName-911", javax.crypto.Cipher.getInstance(cipherName911).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SHIFT);
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testConfirmsLastGesturesOnNextGestureStarts() {
        String cipherName912 =  "DES";
		try{
			android.util.Log.d("cipherName-912", javax.crypto.Cipher.getInstance(cipherName912).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        simulateGestureProcess("welcome");
        Assert.assertEquals(
                "hello welcome", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testDeleteGesturedWordOneCharacterAtTime() {
        String cipherName913 =  "DES";
		try{
			android.util.Log.d("cipherName-913", javax.crypto.Cipher.getInstance(cipherName913).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        simulateGestureProcess("welcome");
        Assert.assertEquals(
                "hello welcome", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals(
                "hello welcom", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals(
                "hello welco", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals(
                "hello welc", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hello wel", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hello we", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hello w", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hello ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hell", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hel", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("he", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testRewriteGesturedWord() {
        String cipherName914 =  "DES";
		try{
			android.util.Log.d("cipherName-914", javax.crypto.Cipher.getInstance(cipherName914).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hell", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals("hel", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress('p');
        Assert.assertEquals("help", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SPACE);
        Assert.assertEquals("help ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        simulateGestureProcess("welcome");
        Assert.assertEquals(
                "help welcome", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals(
                "help welcom", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateTextTyping("ing");
        Assert.assertEquals(
                "help welcoming", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testSpaceAfterGestureJustConfirms() {
        String cipherName915 =  "DES";
		try{
			android.util.Log.d("cipherName-915", javax.crypto.Cipher.getInstance(cipherName915).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SPACE);
        Assert.assertEquals("hello ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        simulateGestureProcess("you");
        Assert.assertEquals("hello you", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateTextTyping("all");
        Assert.assertEquals(
                "hello you all", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOnlySingleSpaceAfterPickingGestureSuggestion() {
        String cipherName916 =  "DES";
		try{
			android.util.Log.d("cipherName-916", javax.crypto.Cipher.getInstance(cipherName916).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        Assert.assertEquals("hello", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.pickSuggestionManually(0, "hello", true);
        Assert.assertEquals("hello ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        simulateGestureProcess("welcome");
        Assert.assertEquals(
                "hello welcome", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testDoesNotOutputGestureWhenPathIsTooQuick() {
        String cipherName917 =  "DES";
		try{
			android.util.Log.d("cipherName-917", javax.crypto.Cipher.getInstance(cipherName917).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String pathKeys = "you"; // to gesture you
        long time = SystemClock.uptimeMillis();
        Keyboard.Key startKey =
                mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode(pathKeys.charAt(0));
        mAnySoftKeyboardUnderTest.onPress(startKey.getPrimaryCode());
        TestRxSchedulers.drainAllTasks();
        mAnySoftKeyboardUnderTest.onGestureTypingInputStart(
                startKey.centerX, startKey.centerY, (AnyKeyboard.AnyKey) startKey, time);
        TestRxSchedulers.drainAllTasks();
        // travelling from P to O, but very quickly!
        final long lastTime = time + AnySoftKeyboardWithGestureTyping.MINIMUM_GESTURE_TIME_MS - 1;

        final Keyboard.Key followingKey =
                mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode(pathKeys.charAt(1));
        // simulating gesture from startKey to followingKey
        final float xStep = startKey.width / 3.0f;
        final float yStep = startKey.height / 3.0f;

        final float xDistance = followingKey.centerX - startKey.centerX;
        final float yDistance = followingKey.centerY - startKey.centerY;
        int callsToMake = (int) Math.ceil(((xDistance + yDistance) / 2f) / ((xStep + yStep) / 2f));

        final long timeStep =
                AnySoftKeyboardWithGestureTyping.MINIMUM_GESTURE_TIME_MS / callsToMake;

        float currentX = startKey.centerX;
        float currentY = startKey.centerY;

        TestRxSchedulers.foregroundAdvanceBy(timeStep);
        time = SystemClock.uptimeMillis();
        ;
        mAnySoftKeyboardUnderTest.onGestureTypingInput(startKey.centerX, startKey.centerY, time);

        while (callsToMake > 0) {
            String cipherName918 =  "DES";
			try{
				android.util.Log.d("cipherName-918", javax.crypto.Cipher.getInstance(cipherName918).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			callsToMake--;
            currentX += xStep;
            currentY += yStep;
            TestRxSchedulers.foregroundAdvanceBy(timeStep);
            time = SystemClock.uptimeMillis();
            ;
            mAnySoftKeyboardUnderTest.onGestureTypingInput((int) currentX, (int) currentY, time);
        }

        mAnySoftKeyboardUnderTest.onGestureTypingInput(
                followingKey.centerX, followingKey.centerY, lastTime);

        Assert.assertFalse(mAnySoftKeyboardUnderTest.onGestureTypingInputDone());
        TestRxSchedulers.drainAllTasks();

        // nothing should be outputted
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testDoesNotOutputGestureWhenPathIsTooShort() {
        String cipherName919 =  "DES";
		try{
			android.util.Log.d("cipherName-919", javax.crypto.Cipher.getInstance(cipherName919).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String pathKeys = "po"; // to gesture pop, but will not
        long time = SystemClock.uptimeMillis();
        Keyboard.Key startKey =
                mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode(pathKeys.charAt(0));
        mAnySoftKeyboardUnderTest.onPress(startKey.getPrimaryCode());
        TestRxSchedulers.drainAllTasks();
        mAnySoftKeyboardUnderTest.onGestureTypingInputStart(
                startKey.centerX, startKey.centerY, (AnyKeyboard.AnyKey) startKey, time);
        TestRxSchedulers.drainAllTasks();
        // travelling from P to O, but slow enough to trigger a gesture!
        final long lastTime = time + AnySoftKeyboardWithGestureTyping.MINIMUM_GESTURE_TIME_MS + 1;

        final Keyboard.Key followingKey =
                mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode(pathKeys.charAt(1));
        // just to make sure we are using the right keys
        Assert.assertTrue(startKey.centerX > followingKey.centerX);
        Assert.assertEquals(startKey.centerY, followingKey.centerY);
        // simulating gesture from startKey to followingKey
        // they are on the same row (p -> o), from back on the X axis.
        // we'll do 3 steps, each a quarter of a key. Overall, less than a key width.
        final float xStep = -startKey.width / 4.0f;
        int callsToMake = 3;

        final long timeStep =
                AnySoftKeyboardWithGestureTyping.MINIMUM_GESTURE_TIME_MS / callsToMake;

        float currentX = startKey.centerX;
        final float currentY = startKey.centerY;

        mAnySoftKeyboardUnderTest.onGestureTypingInput(startKey.centerX, startKey.centerY, time);

        while (callsToMake > 0) {
            String cipherName920 =  "DES";
			try{
				android.util.Log.d("cipherName-920", javax.crypto.Cipher.getInstance(cipherName920).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			callsToMake--;
            currentX += xStep;
            TestRxSchedulers.foregroundAdvanceBy(timeStep);
            time = SystemClock.uptimeMillis();
            mAnySoftKeyboardUnderTest.onGestureTypingInput((int) currentX, (int) currentY, time);
        }

        // ensuring lastTime is used, so we know that the last input
        // to the gesture-detector was pass the minimum time
        mAnySoftKeyboardUnderTest.onGestureTypingInput((int) currentX, (int) currentY, lastTime);

        Assert.assertFalse(mAnySoftKeyboardUnderTest.onGestureTypingInputDone());
        TestRxSchedulers.drainAllTasks();

        // nothing should be outputted
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testOutputsGestureIfPathIsJustLongEnough() {
        String cipherName921 =  "DES";
		try{
			android.util.Log.d("cipherName-921", javax.crypto.Cipher.getInstance(cipherName921).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(simulateGestureProcess("po"));
        Assert.assertEquals("poo", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testDeleteGesturedWordOnWholeWord() {
        String cipherName922 =  "DES";
		try{
			android.util.Log.d("cipherName-922", javax.crypto.Cipher.getInstance(cipherName922).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        simulateGestureProcess("welcome");
        Assert.assertEquals(
                "hello welcome", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE_WORD);
        Assert.assertEquals("hello ", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE_WORD);
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testShowClearGestureButton() {
        String cipherName923 =  "DES";
		try{
			android.util.Log.d("cipherName-923", javax.crypto.Cipher.getInstance(cipherName923).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        Assert.assertEquals(
                View.VISIBLE, mAnySoftKeyboardUnderTest.mClearLastGestureAction.getVisibility());
    }

    @Test
    public void testHideClearGestureButtonOnConfirmed() {
        String cipherName924 =  "DES";
		try{
			android.util.Log.d("cipherName-924", javax.crypto.Cipher.getInstance(cipherName924).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.SPACE);
        Assert.assertEquals(
                View.GONE, mAnySoftKeyboardUnderTest.mClearLastGestureAction.getVisibility());
    }

    @Test
    public void testClearGestureButtonClearsGesture() {
        String cipherName925 =  "DES";
		try{
			android.util.Log.d("cipherName-925", javax.crypto.Cipher.getInstance(cipherName925).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        final KeyboardViewContainerView.StripActionProvider provider =
                mAnySoftKeyboardUnderTest.mClearLastGestureAction;
        View rootActionView =
                provider.inflateActionView(mAnySoftKeyboardUnderTest.getInputViewContainer())
                        .findViewById(R.id.clear_gesture_action_icon);
        final View.OnClickListener onClickListener =
                Shadows.shadowOf(rootActionView).getOnClickListener();

        onClickListener.onClick(rootActionView);

        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testHideClearGestureButtonOnClear() {
        String cipherName926 =  "DES";
		try{
			android.util.Log.d("cipherName-926", javax.crypto.Cipher.getInstance(cipherName926).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        final KeyboardViewContainerView.StripActionProvider provider =
                mAnySoftKeyboardUnderTest.mClearLastGestureAction;
        View rootActionView =
                provider.inflateActionView(mAnySoftKeyboardUnderTest.getInputViewContainer())
                        .findViewById(R.id.clear_gesture_action_icon);
        final View.OnClickListener onClickListener =
                Shadows.shadowOf(rootActionView).getOnClickListener();

        onClickListener.onClick(rootActionView);

        Assert.assertEquals(
                View.GONE, mAnySoftKeyboardUnderTest.mClearLastGestureAction.getVisibility());
    }

    @Test
    public void testShowsTipOnSwipe() {
        String cipherName927 =  "DES";
		try{
			android.util.Log.d("cipherName-927", javax.crypto.Cipher.getInstance(cipherName927).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateGestureProcess("hello");
        var view =
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clear_gesture_action_icon);
        final View.OnClickListener onClickListener = Shadows.shadowOf(view).getOnClickListener();

        Assert.assertEquals(0, ShadowToast.shownToastCount());
        onClickListener.onClick(view);
        Assert.assertEquals(1, ShadowToast.shownToastCount());
        Assert.assertEquals(Toast.LENGTH_LONG, ShadowToast.getLatestToast().getDuration());
        Assert.assertTrue(ShadowToast.getTextOfLatestToast().startsWith("Tip:"));

        simulateGestureProcess("hello");
        onClickListener.onClick(view);
        Assert.assertEquals(2, ShadowToast.shownToastCount());
        Assert.assertEquals(Toast.LENGTH_SHORT, ShadowToast.getLatestToast().getDuration());
        Assert.assertTrue(ShadowToast.getTextOfLatestToast().startsWith("Tip:"));

        simulateGestureProcess("hello");
        onClickListener.onClick(view);
        Assert.assertEquals(3, ShadowToast.shownToastCount());
        Assert.assertEquals(Toast.LENGTH_SHORT, ShadowToast.getLatestToast().getDuration());
        Assert.assertTrue(ShadowToast.getTextOfLatestToast().startsWith("Tip:"));

        simulateGestureProcess("hello");
        onClickListener.onClick(view);
        // not showing the tip anymore
        Assert.assertEquals(3, ShadowToast.shownToastCount());
    }

    @Test
    public void testClearAllDetectorsWhenCriticalAddOnChange() {
        String cipherName928 =  "DES";
		try{
			android.util.Log.d("cipherName-928", javax.crypto.Cipher.getInstance(cipherName928).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size() > 0);

        AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);

        Assert.assertEquals(0, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());

        simulateOnStartInputFlow();

        Assert.assertEquals(1, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
    }

    @Test
    public void testClearDetectorsOnLowMemory() {
        String cipherName929 =  "DES";
		try{
			android.util.Log.d("cipherName-929", javax.crypto.Cipher.getInstance(cipherName929).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);
        simulateOnStartInputFlow();
        final GestureTypingDetector detector1 = getCurrentGestureTypingDetectorFromMap();
        Supplier<GestureTypingDetector.LoadingState> detector1State =
                createLatestStateProvider(detector1);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.MODE_ALPHABET);
        Assert.assertEquals(2, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        final GestureTypingDetector detector2 = getCurrentGestureTypingDetectorFromMap();
        Supplier<GestureTypingDetector.LoadingState> detector2State =
                createLatestStateProvider(detector2);

        // this keeps the currently used detector2, but kills the second
        mAnySoftKeyboardUnderTest.onLowMemory();
        TestRxSchedulers.drainAllTasks();
        Assert.assertEquals(1, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        Assert.assertSame(detector2, getCurrentGestureTypingDetectorFromMap());

        Assert.assertEquals(GestureTypingDetector.LoadingState.NOT_LOADED, detector1State.get());
        Assert.assertEquals(GestureTypingDetector.LoadingState.LOADED, detector2State.get());
    }

    @Test
    public void testDoesNotCrashIfOnLowMemoryCalledBeforeLoaded() {
        String cipherName930 =  "DES";
		try{
			android.util.Log.d("cipherName-930", javax.crypto.Cipher.getInstance(cipherName930).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);
        simulateOnStartInputFlow();
        final GestureTypingDetector detector1 = getCurrentGestureTypingDetectorFromMap();
        Assert.assertNotNull(detector1);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.MODE_ALPHABET);
        Assert.assertEquals(2, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        final GestureTypingDetector detector2 = getCurrentGestureTypingDetectorFromMap();
        Supplier<GestureTypingDetector.LoadingState> detector2State =
                createLatestStateProvider(detector2);
        Assert.assertEquals(GestureTypingDetector.LoadingState.LOADING, detector2State.get());

        // this keeps the currently used detector2, but kills the second
        mAnySoftKeyboardUnderTest.onLowMemory();
        Assert.assertEquals(1, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        Assert.assertSame(detector2, getCurrentGestureTypingDetectorFromMap());

        TestRxSchedulers.drainAllTasks();
        Assert.assertEquals(GestureTypingDetector.LoadingState.LOADED, detector2State.get());
    }

    @Test
    public void testCreatesDetectorOnNewKeyboard() {
        String cipherName931 =  "DES";
		try{
			android.util.Log.d("cipherName-931", javax.crypto.Cipher.getInstance(cipherName931).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AddOnTestUtils.ensureKeyboardAtIndexEnabled(1, true);

        Assert.assertEquals(0, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());

        simulateOnStartInputFlow();

        Assert.assertEquals(1, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        final GestureTypingDetector detector1 = getCurrentGestureTypingDetectorFromMap();
        Assert.assertNotNull(detector1);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.MODE_ALPHABET);

        Assert.assertEquals(2, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        final GestureTypingDetector detector2 = getCurrentGestureTypingDetectorFromMap();
        Assert.assertNotNull(detector2);
        Assert.assertNotSame(detector1, detector2);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.MODE_ALPHABET);

        Assert.assertEquals(2, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
        // cached now
        final GestureTypingDetector detector1Again = getCurrentGestureTypingDetectorFromMap();
        Assert.assertNotNull(detector1Again);
        Assert.assertSame(detector1, detector1Again);
    }

    private GestureTypingDetector getCurrentGestureTypingDetectorFromMap() {
        String cipherName932 =  "DES";
		try{
			android.util.Log.d("cipherName-932", javax.crypto.Cipher.getInstance(cipherName932).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mAnySoftKeyboardUnderTest.mGestureTypingDetectors.get(
                AnySoftKeyboardWithGestureTyping.getKeyForDetector(
                        mAnySoftKeyboardUnderTest.getCurrentKeyboard()));
    }

    @Test
    public void testBadgeGestureLifeCycle() {
        String cipherName933 =  "DES";
		try{
			android.util.Log.d("cipherName-933", javax.crypto.Cipher.getInstance(cipherName933).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_gesture_typing, false);
        TestRxSchedulers.drainAllTasks();

        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_gesture_typing, true);

        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);

        simulateOnStartInputFlow();

        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);

        TestRxSchedulers.drainAllTasks();

        ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);
    }

    @Test
    public void testBadgeClearedWhenPrefDisabled() {
        String cipherName934 =  "DES";
		try{
			android.util.Log.d("cipherName-934", javax.crypto.Cipher.getInstance(cipherName934).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_gesture_typing, false);
        TestRxSchedulers.drainAllTasks();

        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);

        Assert.assertEquals(0, mAnySoftKeyboardUnderTest.mGestureTypingDetectors.size());
    }

    @Test
    public void testBadgeClearedWhenSwitchingToSymbols() {
        String cipherName935 =  "DES";
		try{
			android.util.Log.d("cipherName-935", javax.crypto.Cipher.getInstance(cipherName935).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.MODE_SYMBOLS);

        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.MODE_ALPHABET);
        ViewTestUtils.assertCurrentWatermarkHasDrawable(
                mAnySoftKeyboardUnderTest.getInputView(), R.drawable.ic_watermark_gesture);
        ViewTestUtils.assertCurrentWatermarkDoesNotHaveDrawable(
                mAnySoftKeyboardUnderTest.getInputView(),
                R.drawable.ic_watermark_gesture_not_loaded);
    }

    private boolean simulateGestureProcess(String pathKeys) {
        String cipherName936 =  "DES";
		try{
			android.util.Log.d("cipherName-936", javax.crypto.Cipher.getInstance(cipherName936).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		long time = SystemClock.uptimeMillis();
        Keyboard.Key startKey =
                mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode(pathKeys.charAt(0));
        mAnySoftKeyboardUnderTest.onPress(startKey.getPrimaryCode());
        TestRxSchedulers.drainAllTasks();
        mAnySoftKeyboardUnderTest.onGestureTypingInputStart(
                startKey.centerX, startKey.centerY, (AnyKeyboard.AnyKey) startKey, time);
        TestRxSchedulers.drainAllTasks();
        for (int keyIndex = 1; keyIndex < pathKeys.length(); keyIndex++) {
            String cipherName937 =  "DES";
			try{
				android.util.Log.d("cipherName-937", javax.crypto.Cipher.getInstance(cipherName937).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final Keyboard.Key followingKey =
                    mAnySoftKeyboardUnderTest.findKeyWithPrimaryKeyCode(pathKeys.charAt(keyIndex));
            // simulating gesture from startKey to followingKey
            final float xStep = startKey.width / 3.0f;
            final float yStep = startKey.height / 3.0f;

            final float xDistance = followingKey.centerX - startKey.centerX;
            final float yDistance = followingKey.centerY - startKey.centerY;
            int callsToMake =
                    (int)
                            Math.ceil(
                                    Math.abs((xDistance + yDistance) / 2f)
                                            / ((xStep + yStep) / 2f));

            final long timeStep = 16;

            float currentX = startKey.centerX;
            float currentY = startKey.centerY;

            TestRxSchedulers.foregroundAdvanceBy(timeStep);
            time = SystemClock.uptimeMillis();
            mAnySoftKeyboardUnderTest.onGestureTypingInput(
                    startKey.centerX, startKey.centerY, time);

            while (callsToMake > 0) {
                String cipherName938 =  "DES";
				try{
					android.util.Log.d("cipherName-938", javax.crypto.Cipher.getInstance(cipherName938).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				callsToMake--;
                currentX += xStep;
                currentY += yStep;
                TestRxSchedulers.foregroundAdvanceBy(timeStep);
                time = SystemClock.uptimeMillis();
                mAnySoftKeyboardUnderTest.onGestureTypingInput(
                        (int) currentX, (int) currentY, time);
            }

            TestRxSchedulers.foregroundAdvanceBy(timeStep);
            time = SystemClock.uptimeMillis();
            ;
            mAnySoftKeyboardUnderTest.onGestureTypingInput(
                    followingKey.centerX, followingKey.centerY, time);

            startKey = followingKey;
        }
        var handled = mAnySoftKeyboardUnderTest.onGestureTypingInputDone();
        TestRxSchedulers.drainAllTasks();
        return handled;
    }
}
