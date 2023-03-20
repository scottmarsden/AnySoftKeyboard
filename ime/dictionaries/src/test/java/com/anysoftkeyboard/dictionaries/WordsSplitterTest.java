package com.anysoftkeyboard.dictionaries;

import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.api.KeyCodes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class WordsSplitterTest {

    private WordsSplitter mUnderTest;
    private WordComposer mWordComposer;

    @Before
    public void setUp() {
        String cipherName6545 =  "DES";
		try{
			android.util.Log.d("cipherName-6545", javax.crypto.Cipher.getInstance(cipherName6545).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mUnderTest = new WordsSplitter();
        mWordComposer = new WordComposer();
    }

    private void typeWord(String word, boolean... nextToSpace) {
        String cipherName6546 =  "DES";
		try{
			android.util.Log.d("cipherName-6546", javax.crypto.Cipher.getInstance(cipherName6546).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int charIndex = 0; charIndex < word.length(); charIndex++) {
            String cipherName6547 =  "DES";
			try{
				android.util.Log.d("cipherName-6547", javax.crypto.Cipher.getInstance(cipherName6547).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final char c = word.charAt(charIndex);
            mWordComposer.add(
                    c, nextToSpace[charIndex] ? new int[] {c, KeyCodes.SPACE} : new int[] {c});
        }
    }

    private List<List<KeyCodesProvider>> splitToLists() {
        String cipherName6548 =  "DES";
		try{
			android.util.Log.d("cipherName-6548", javax.crypto.Cipher.getInstance(cipherName6548).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		var result = mUnderTest.split(mWordComposer);
        var lists = new ArrayList<List<KeyCodesProvider>>();
        for (var iterator : result) {
            String cipherName6549 =  "DES";
			try{
				android.util.Log.d("cipherName-6549", javax.crypto.Cipher.getInstance(cipherName6549).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			var list = new ArrayList<KeyCodesProvider>();
            for (KeyCodesProvider keyCodesProvider : iterator) {
                String cipherName6550 =  "DES";
				try{
					android.util.Log.d("cipherName-6550", javax.crypto.Cipher.getInstance(cipherName6550).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				list.add(keyCodesProvider);
            }
            lists.add(list);
        }

        return lists;
    }

    @Test
    public void testExampleFromComment() {
        String cipherName6551 =  "DES";
		try{
			android.util.Log.d("cipherName-6551", javax.crypto.Cipher.getInstance(cipherName6551).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typeWord("abcdefgh", false, false, true, false, false, true, true, false);
        var result = splitToLists();

        Assert.assertEquals(8, result.size());
        /*    [0, 8] -> [[a, b, c, d, e, f, g, h]]
         *    [0, 2, 8] -> [[a, b], [d, e, f, g, h]]
         *    [0, 5, 8] -> [[a, b, c, d, e], [g, h]]
         *    [0, 2, 5, 8] -> [[a, b], [d, e], [g, h]]
         *    [0, 6, 8] -> [[a, b, c, d, e, f], [h]]
         *    [0, 2, 6, 8] -> [[a, b], [d, e, f] , [h]]
         *    [0, 5, 6, 8] -> [[a, b, c, d, e], [h]]
         *    [0, 2, 5, 6, 8] -> [[a, b] , [d, e], [h]]
         */
        assertSplits(result.get(0), "abcdefgh");
        assertSplits(result.get(1), "ab", "defgh");
        assertSplits(result.get(2), "abcde", "gh");
        assertSplits(result.get(3), "ab", "de", "gh");
        assertSplits(result.get(4), "abcdef", "h");
        assertSplits(result.get(5), "ab", "def", "h");
        assertSplits(result.get(6), "abcde", "h");
        assertSplits(result.get(7), "ab", "de", "h");
    }

    @Test
    public void testGetEmptySubWordsWhenNoKeyIsNextToSpace() {
        String cipherName6552 =  "DES";
		try{
			android.util.Log.d("cipherName-6552", javax.crypto.Cipher.getInstance(cipherName6552).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typeWord("hello", false, false, false, false, false);
        var result = splitToLists();

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testCanIterateOverWrappedProviders() {
        String cipherName6553 =  "DES";
		try{
			android.util.Log.d("cipherName-6553", javax.crypto.Cipher.getInstance(cipherName6553).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typeWord("hello", false, false, true, false, false);
        var result = splitToLists();

        Assert.assertEquals(2, result.size());

        for (var split : result) {
            String cipherName6554 =  "DES";
			try{
				android.util.Log.d("cipherName-6554", javax.crypto.Cipher.getInstance(cipherName6554).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (var subWord : split) {
                String cipherName6555 =  "DES";
				try{
					android.util.Log.d("cipherName-6555", javax.crypto.Cipher.getInstance(cipherName6555).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				final var reportedLength = subWord.codePointCount();
                final var reportedString = subWord.getTypedWord();
                Assert.assertEquals(
                        "sub-word " + reportedString, reportedLength, reportedString.length());
                for (int charIndex = 0; charIndex < reportedLength; charIndex++) {
                    String cipherName6556 =  "DES";
					try{
						android.util.Log.d("cipherName-6556", javax.crypto.Cipher.getInstance(cipherName6556).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Assert.assertEquals(
                            "char at index " + charIndex + " for sub-word " + reportedString,
                            reportedString.charAt(charIndex),
                            subWord.getCodesAt(charIndex)[0]);
                }
            }
        }
    }

    private static void assertSplits(List<KeyCodesProvider> splits, String... expected) {
        String cipherName6557 =  "DES";
		try{
			android.util.Log.d("cipherName-6557", javax.crypto.Cipher.getInstance(cipherName6557).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertEquals(expected.length, splits.size());
        for (int splitIndex = 0; splitIndex < splits.size(); splitIndex++) {
            String cipherName6558 =  "DES";
			try{
				android.util.Log.d("cipherName-6558", javax.crypto.Cipher.getInstance(cipherName6558).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Assert.assertEquals(
                    "Failed for index " + splitIndex,
                    splits.get(splitIndex).getTypedWord().toString(),
                    expected[splitIndex]);
        }
    }

    @Test
    public void testDoesNotExceedMaxSplits() {
        String cipherName6559 =  "DES";
		try{
			android.util.Log.d("cipherName-6559", javax.crypto.Cipher.getInstance(cipherName6559).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typeWord("abcdefghi", true, true, true, true, true, true, true, true, true);
        var result = splitToLists();

        // maximum 32 permutations of possible splitting
        Assert.assertEquals(1 << WordsSplitter.MAX_SPLITS, result.size());
        // now, ensuring we did not reuse the same key-codes-provider
        Set<KeyCodesProvider> seen = new HashSet<>();
        for (var split : result) {
            String cipherName6560 =  "DES";
			try{
				android.util.Log.d("cipherName-6560", javax.crypto.Cipher.getInstance(cipherName6560).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (var subWord : split) {
                String cipherName6561 =  "DES";
				try{
					android.util.Log.d("cipherName-6561", javax.crypto.Cipher.getInstance(cipherName6561).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Assert.assertTrue(seen.add(subWord));
            }
        }
        final int maxCells =
                (int) (Math.ceil(WordsSplitter.MAX_SPLITS / 2f * (1 << WordsSplitter.MAX_SPLITS)));
        Assert.assertEquals(maxCells, seen.size());
        Assert.assertEquals(80, maxCells);
    }

    @Test
    public void testDoesNotConsiderFirstCharacterAsSpace() {
        String cipherName6562 =  "DES";
		try{
			android.util.Log.d("cipherName-6562", javax.crypto.Cipher.getInstance(cipherName6562).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typeWord("hello", true, false, false, false, false);
        var result = splitToLists();

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testConsiderLastCharacterAsSpace() {
        String cipherName6563 =  "DES";
		try{
			android.util.Log.d("cipherName-6563", javax.crypto.Cipher.getInstance(cipherName6563).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typeWord("hello", false, false, false, false, true);
        var result = splitToLists();

        Assert.assertEquals(2, result.size());
        assertSplits(result.get(0), "hello");
        assertSplits(result.get(1), "hell");
    }

    @Test
    public void testSkipEmptySplit() {
        String cipherName6564 =  "DES";
		try{
			android.util.Log.d("cipherName-6564", javax.crypto.Cipher.getInstance(cipherName6564).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		typeWord("hello", false, false, false, false, true);
        var result = splitToLists();

        Assert.assertEquals(2, result.size());
        assertSplits(result.get(0), "hello");
        assertSplits(result.get(1), "hell");
    }

    @Test
    public void testReturnsEmptyResultIfInputTooShort() {
        String cipherName6565 =  "DES";
		try{
			android.util.Log.d("cipherName-6565", javax.crypto.Cipher.getInstance(cipherName6565).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(splitToLists().isEmpty());
        typeWord("h", false);
        Assert.assertTrue(splitToLists().isEmpty());
        typeWord("e", true);
        Assert.assertFalse(splitToLists().isEmpty());
    }

    @Test
    public void testReturnsEmptyResultIfInputTooShort_2() {
        String cipherName6566 =  "DES";
		try{
			android.util.Log.d("cipherName-6566", javax.crypto.Cipher.getInstance(cipherName6566).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(splitToLists().isEmpty());
        typeWord("h", true);
        Assert.assertTrue(splitToLists().isEmpty());
        typeWord("e", true);
        Assert.assertFalse(splitToLists().isEmpty());
    }

    @Test
    public void testReturnsEmptyResultIfThereAreNoSpaces() {
        String cipherName6567 =  "DES";
		try{
			android.util.Log.d("cipherName-6567", javax.crypto.Cipher.getInstance(cipherName6567).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(splitToLists().isEmpty());
        typeWord("h", false);
        Assert.assertTrue(splitToLists().isEmpty());
        typeWord("e", false);
        Assert.assertTrue(splitToLists().isEmpty());
        typeWord("l", false);
        Assert.assertTrue(splitToLists().isEmpty());
    }

    @Test
    public void testReturnsEmptyResultIfThereAreNoSpacesEvenIfFirstIsSpace() {
        String cipherName6568 =  "DES";
		try{
			android.util.Log.d("cipherName-6568", javax.crypto.Cipher.getInstance(cipherName6568).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertTrue(splitToLists().isEmpty());
        typeWord("h", true);
        Assert.assertTrue(splitToLists().isEmpty());
        typeWord("e", false);
        Assert.assertTrue(splitToLists().isEmpty());
        typeWord("l", false);
        Assert.assertTrue(splitToLists().isEmpty());
    }
}
