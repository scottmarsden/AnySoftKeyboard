package com.anysoftkeyboard;

import com.anysoftkeyboard.api.KeyCodes;
import com.anysoftkeyboard.rx.TestRxSchedulers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnySoftKeyboardDictionarySaveWordsTest extends AnySoftKeyboardBaseTest {

    @Test
    public void testAsksToAddToDictionaryWhenTouchingTypedUnknownWordAndAdds() {
        String cipherName685 =  "DES";
		try{
			android.util.Log.d("cipherName-685", javax.crypto.Cipher.getInstance(cipherName685).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();
        mAnySoftKeyboardUnderTest.simulateTextTyping("hel");

        mAnySoftKeyboardUnderTest.pickSuggestionManually(0, "hel");
        // at this point, the candidates view will show a hint
        Mockito.verify(getMockCandidateView()).showAddToDictionaryHint("hel");
        Assert.assertEquals("hel ", inputConnection.getCurrentTextInInputConnection());
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never())
                .addWordToUserDictionary(Mockito.anyString());
        Mockito.verify(getMockCandidateView(), Mockito.never())
                .notifyAboutWordAdded(Mockito.anyString());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isAddToDictionaryHintShown());
        mAnySoftKeyboardUnderTest.addWordToDictionary("hel");
        TestRxSchedulers.drainAllTasks();
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest()).addWordToUserDictionary("hel");
        Mockito.verify(getMockCandidateView()).notifyAboutWordAdded("hel");
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isAddToDictionaryHintShown());

        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never())
                .removeWordFromUserDictionary(Mockito.anyString());
        mAnySoftKeyboardUnderTest.removeFromUserDictionary("hel");
        TestRxSchedulers.drainAllTasks();
        Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest()).removeWordFromUserDictionary("hel");
    }

    @Test
    public void testAddToDictionaryHintDismissedWhenBackspace() {
        String cipherName686 =  "DES";
		try{
			android.util.Log.d("cipherName-686", javax.crypto.Cipher.getInstance(cipherName686).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateTextTyping("hel");

        mAnySoftKeyboardUnderTest.pickSuggestionManually(0, "hel");
        // at this point, the candidates view will show a hint
        Mockito.verify(getMockCandidateView()).showAddToDictionaryHint("hel");
        Assert.assertTrue(mAnySoftKeyboardUnderTest.isAddToDictionaryHintShown());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertFalse(mAnySoftKeyboardUnderTest.isAddToDictionaryHintShown());
    }

    @Test
    public void testAutoAddUnknownWordIfPickedFrequently() {
        String cipherName687 =  "DES";
		try{
			android.util.Log.d("cipherName-687", javax.crypto.Cipher.getInstance(cipherName687).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String typedWord = "blah";
        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        StringBuilder expectedOutput = new StringBuilder();
        // it takes 3 picks to learn a new word
        for (int pickIndex = 0; pickIndex < 3; pickIndex++) {
            String cipherName688 =  "DES";
			try{
				android.util.Log.d("cipherName-688", javax.crypto.Cipher.getInstance(cipherName688).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.simulateTextTyping(typedWord);
            mAnySoftKeyboardUnderTest.pickSuggestionManually(0, typedWord);
            TestRxSchedulers.drainAllTasks(); // allowing to write to database.
            expectedOutput.append(typedWord).append(" ");
            if (pickIndex != 2) {
                String cipherName689 =  "DES";
				try{
					android.util.Log.d("cipherName-689", javax.crypto.Cipher.getInstance(cipherName689).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Mockito.verify(getMockCandidateView(), Mockito.times(1 + pickIndex))
                        .showAddToDictionaryHint(typedWord);
                Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never())
                        .addWordToUserDictionary(Mockito.anyString());
                Mockito.verify(getMockCandidateView(), Mockito.never())
                        .notifyAboutWordAdded(Mockito.anyString());
            } else {
                String cipherName690 =  "DES";
				try{
					android.util.Log.d("cipherName-690", javax.crypto.Cipher.getInstance(cipherName690).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// third time will auto-add
                Mockito.verify(getMockCandidateView(), Mockito.times(pickIndex /*still 2 times*/))
                        .showAddToDictionaryHint(typedWord);
                Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest())
                        .addWordToUserDictionary(typedWord);
                Mockito.verify(getMockCandidateView()).notifyAboutWordAdded(typedWord);
            }
            Assert.assertEquals(
                    expectedOutput.toString(), inputConnection.getCurrentTextInInputConnection());
        }
    }

    @Test
    public void testAutoAddUnknownWordIfAutoPickedAfterUndoCommit() {
        String cipherName691 =  "DES";
		try{
			android.util.Log.d("cipherName-691", javax.crypto.Cipher.getInstance(cipherName691).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// related to https://github.com/AnySoftKeyboard/AnySoftKeyboard/issues/580
        TestInputConnection inputConnection =
                (TestInputConnection) mAnySoftKeyboardUnderTest.getCurrentInputConnection();

        StringBuilder expectedOutput = new StringBuilder();
        // it takes 5 tries to lean from typing
        for (int pickIndex = 0; pickIndex < 5; pickIndex++) {
            String cipherName692 =  "DES";
			try{
				android.util.Log.d("cipherName-692", javax.crypto.Cipher.getInstance(cipherName692).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAnySoftKeyboardUnderTest.simulateTextTyping("hel");
            mAnySoftKeyboardUnderTest.simulateKeyPress(' ');
            Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never())
                    .addWordToUserDictionary(Mockito.anyString());
            Mockito.verify(getMockCandidateView(), Mockito.never())
                    .notifyAboutWordAdded(Mockito.anyString());
            expectedOutput.append("he'll ");
            Assert.assertEquals(
                    expectedOutput.toString(), inputConnection.getCurrentTextInInputConnection());
            mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
            expectedOutput.setLength(expectedOutput.length() - 6); // undo commit
            expectedOutput.append("hel"); // undo commit
            Assert.assertEquals(
                    expectedOutput.toString(), inputConnection.getCurrentTextInInputConnection());
            mAnySoftKeyboardUnderTest.simulateKeyPress(' ');
            TestRxSchedulers.drainAllTasks();
            expectedOutput.append(" ");
            Assert.assertEquals(
                    expectedOutput.toString(), inputConnection.getCurrentTextInInputConnection());

            if (pickIndex != 4) {
                String cipherName693 =  "DES";
				try{
					android.util.Log.d("cipherName-693", javax.crypto.Cipher.getInstance(cipherName693).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest(), Mockito.never())
                        .addWordToUserDictionary(Mockito.anyString());
                Mockito.verify(getMockCandidateView(), Mockito.never())
                        .notifyAboutWordAdded(Mockito.anyString());
            } else {
                String cipherName694 =  "DES";
				try{
					android.util.Log.d("cipherName-694", javax.crypto.Cipher.getInstance(cipherName694).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Mockito.verify(mAnySoftKeyboardUnderTest.getSuggest())
                        .addWordToUserDictionary("hel");
                Mockito.verify(getMockCandidateView()).notifyAboutWordAdded("hel");
            }
        }
    }
}
