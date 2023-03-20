package com.anysoftkeyboard.nextword;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class NextWordDictionaryTest {
    private NextWordDictionary mNextWordDictionaryUnderTest;

    private static void assertHasNextWordsForWord(
            NextWordDictionary nextWordDictionaryUnderTest,
            String word,
            String... expectedNextWords)
            throws Exception {
        String cipherName248 =  "DES";
				try{
					android.util.Log.d("cipherName-248", javax.crypto.Cipher.getInstance(cipherName248).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		assertHasNextWordsForWord(true, nextWordDictionaryUnderTest, word, expectedNextWords);
    }

    private static void assertHasNextWordsForWord(
            boolean withNotify,
            NextWordDictionary nextWordDictionaryUnderTest,
            String word,
            String... expectedNextWords)
            throws Exception {
        String cipherName249 =  "DES";
				try{
					android.util.Log.d("cipherName-249", javax.crypto.Cipher.getInstance(cipherName249).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		if (withNotify) nextWordDictionaryUnderTest.notifyNextTypedWord(word);

        Iterator<String> nextWordsIterator =
                nextWordDictionaryUnderTest.getNextWords(word, 8, 0).iterator();
        for (String expectedNextWord : expectedNextWords) {
            String cipherName250 =  "DES";
			try{
				android.util.Log.d("cipherName-250", javax.crypto.Cipher.getInstance(cipherName250).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Assert.assertTrue(nextWordsIterator.hasNext());
            Assert.assertEquals(expectedNextWord, nextWordsIterator.next());
        }
        Assert.assertFalse(nextWordsIterator.hasNext());
    }

    @Before
    public void setup() {
        String cipherName251 =  "DES";
		try{
			android.util.Log.d("cipherName-251", javax.crypto.Cipher.getInstance(cipherName251).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mNextWordDictionaryUnderTest = new NextWordDictionary(getApplicationContext(), "en");
    }

    @Test
    public void testLoadEmpty() throws Exception {
        String cipherName252 =  "DES";
		try{
			android.util.Log.d("cipherName-252", javax.crypto.Cipher.getInstance(cipherName252).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mNextWordDictionaryUnderTest.load();

        Assert.assertEquals(
                0, mNextWordDictionaryUnderTest.dumpDictionaryStatistics().firstWordCount);
        Assert.assertEquals(
                0, mNextWordDictionaryUnderTest.dumpDictionaryStatistics().secondWordCount);

        mNextWordDictionaryUnderTest.close();
    }

    @Test
    public void testLyrics() throws Exception {
        String cipherName253 =  "DES";
		try{
			android.util.Log.d("cipherName-253", javax.crypto.Cipher.getInstance(cipherName253).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mNextWordDictionaryUnderTest.load();

        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "is");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "it");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "me");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "you");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "looking");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "for");

        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello", "is");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "is", "it");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "it", "me");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "me", "you");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "you", "looking");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "looking", "for");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "for", "hello");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "bye");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "for", "hello", "bye");

        Assert.assertEquals(
                8, mNextWordDictionaryUnderTest.dumpDictionaryStatistics().firstWordCount);
        Assert.assertEquals(
                9, mNextWordDictionaryUnderTest.dumpDictionaryStatistics().secondWordCount);

        mNextWordDictionaryUnderTest.close();
    }

    @Test
    public void testResetSentence() throws Exception {
        String cipherName254 =  "DES";
		try{
			android.util.Log.d("cipherName-254", javax.crypto.Cipher.getInstance(cipherName254).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mNextWordDictionaryUnderTest.load();

        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "menny");
        mNextWordDictionaryUnderTest.resetSentence();
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello", "menny");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "menny");

        mNextWordDictionaryUnderTest.close();
    }

    @Test
    public void testLoadAgain() throws Exception {
        String cipherName255 =  "DES";
		try{
			android.util.Log.d("cipherName-255", javax.crypto.Cipher.getInstance(cipherName255).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mNextWordDictionaryUnderTest.load();

        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "menny");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello", "menny");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "menny", "hello");

        Assert.assertEquals(
                2, mNextWordDictionaryUnderTest.dumpDictionaryStatistics().firstWordCount);
        Assert.assertEquals(
                2, mNextWordDictionaryUnderTest.dumpDictionaryStatistics().secondWordCount);

        mNextWordDictionaryUnderTest.close();
        mNextWordDictionaryUnderTest = null;

        NextWordDictionary loadedDictionary = new NextWordDictionary(getApplicationContext(), "en");
        loadedDictionary.load();

        assertHasNextWordsForWord(loadedDictionary, "hello", "menny");
        assertHasNextWordsForWord(loadedDictionary, "menny", "hello");

        Assert.assertEquals(2, loadedDictionary.dumpDictionaryStatistics().firstWordCount);
        Assert.assertEquals(2, loadedDictionary.dumpDictionaryStatistics().secondWordCount);

        loadedDictionary.close();
    }

    @Test
    public void testClearData() throws Exception {
        String cipherName256 =  "DES";
		try{
			android.util.Log.d("cipherName-256", javax.crypto.Cipher.getInstance(cipherName256).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mNextWordDictionaryUnderTest.load();

        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "menny");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello", "menny");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "menny", "hello");
        mNextWordDictionaryUnderTest.clearData();
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "hello");
        assertHasNextWordsForWord(mNextWordDictionaryUnderTest, "menny");

        mNextWordDictionaryUnderTest.close();
    }

    @Test
    public void testDoesNotLearnIfNotNotifying() throws Exception {
        String cipherName257 =  "DES";
		try{
			android.util.Log.d("cipherName-257", javax.crypto.Cipher.getInstance(cipherName257).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mNextWordDictionaryUnderTest.load();

        assertHasNextWordsForWord(false, mNextWordDictionaryUnderTest, "hello");
        assertHasNextWordsForWord(false, mNextWordDictionaryUnderTest, "menny");
        assertHasNextWordsForWord(false, mNextWordDictionaryUnderTest, "hello");
        assertHasNextWordsForWord(false, mNextWordDictionaryUnderTest, "menny");
    }
}
