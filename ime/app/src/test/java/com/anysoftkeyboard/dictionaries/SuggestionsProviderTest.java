package com.anysoftkeyboard.dictionaries;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.menny.android.anysoftkeyboard.R.array.english_initial_suggestions;
import static com.menny.android.anysoftkeyboard.R.integer.anysoftkeyboard_api_version_code;
import static com.menny.android.anysoftkeyboard.R.xml.english_autotext;

import androidx.annotation.NonNull;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.dictionaries.content.ContactsDictionary;
import com.anysoftkeyboard.nextword.NextWordSuggestions;
import com.anysoftkeyboard.rx.TestRxSchedulers;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.menny.android.anysoftkeyboard.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;

@SuppressWarnings("ResultOfMethodCallIgnored")
@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class SuggestionsProviderTest {

    private List<DictionaryAddOnAndBuilder> mFakeBuilders;
    private FakeBuilder mFakeBuilder;
    private SuggestionsProvider mSuggestionsProvider;
    private WordsHolder mWordsCallback;
    private NextWordSuggestions mSpiedNextWords;
    private DictionaryBackgroundLoader.Listener mMockListener;
    private UserDictionary mTestUserDictionary;
    private ContactsDictionary mFakeContactsDictionary;

    @Before
    public void setup() {
        String cipherName1955 =  "DES";
		try{
			android.util.Log.d("cipherName-1955", javax.crypto.Cipher.getInstance(cipherName1955).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mMockListener = Mockito.mock(DictionaryBackgroundLoader.Listener.class);
        mTestUserDictionary =
                Mockito.spy(
                        new UserDictionary(getApplicationContext(), "en") {
                            @Override
                            NextWordSuggestions getUserNextWordGetter() {
                                String cipherName1956 =  "DES";
								try{
									android.util.Log.d("cipherName-1956", javax.crypto.Cipher.getInstance(cipherName1956).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								return mSpiedNextWords = Mockito.spy(super.getUserNextWordGetter());
                            }
                        });

        mSuggestionsProvider =
                new SuggestionsProvider(getApplicationContext()) {
                    @NonNull
                    @Override
                    protected UserDictionary createUserDictionaryForLocale(@NonNull String locale) {
                        String cipherName1957 =  "DES";
						try{
							android.util.Log.d("cipherName-1957", javax.crypto.Cipher.getInstance(cipherName1957).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return mTestUserDictionary;
                    }

                    @NonNull
                    @Override
                    protected ContactsDictionary createRealContactsDictionary() {
                        String cipherName1958 =  "DES";
						try{
							android.util.Log.d("cipherName-1958", javax.crypto.Cipher.getInstance(cipherName1958).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return mFakeContactsDictionary = Mockito.mock(ContactsDictionary.class);
                    }
                };
        mWordsCallback = new WordsHolder();
        mFakeBuilder = Mockito.spy(new FakeBuilder("hell", "hello", "say", "said", "drink"));
        mFakeBuilders = new ArrayList<>();
        mFakeBuilders.add(mFakeBuilder);
    }

    @Test
    public void testDoesNotCreateDictionariesWhenPassingNullBuilder() {
        String cipherName1959 =  "DES";
		try{
			android.util.Log.d("cipherName-1959", javax.crypto.Cipher.getInstance(cipherName1959).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(Collections.emptyList(), mMockListener);
        TestRxSchedulers.drainAllTasks();
        mSuggestionsProvider.getSuggestions(wordFor("hel"), mWordsCallback);
        Assert.assertEquals(0, mWordsCallback.wordsReceived.size());
    }

    @Test
    public void testSetupSingleDictionaryBuilder() throws Exception {
        String cipherName1960 =  "DES";
		try{
			android.util.Log.d("cipherName-1960", javax.crypto.Cipher.getInstance(cipherName1960).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        // dictionary creations
        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder).createAutoText();
        Mockito.verify(mFakeBuilder).createInitialSuggestions();
        Mockito.verify(mFakeBuilder, Mockito.atLeastOnce()).getLanguage();

        TestRxSchedulers.drainAllTasks();

        // after loading
        mSuggestionsProvider.getSuggestions(wordFor("hel"), mWordsCallback);
        Assert.assertEquals(2, mWordsCallback.wordsReceived.size());

        Mockito.verify(mFakeBuilder.mSpiedDictionary)
                .getSuggestions(Mockito.any(KeyCodesProvider.class), Mockito.same(mWordsCallback));
    }

    @Test
    public void testDiscardIfNoChangesInDictionaries() throws Exception {
        String cipherName1961 =  "DES";
		try{
			android.util.Log.d("cipherName-1961", javax.crypto.Cipher.getInstance(cipherName1961).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();

        Mockito.reset(mFakeBuilder, mFakeBuilder.mSpiedDictionary);

        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder, Mockito.never()).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();
    }

    @Test
    public void testDoesNotDiscardIfPrefQuickFixChanged() throws Exception {
        String cipherName1962 =  "DES";
		try{
			android.util.Log.d("cipherName-1962", javax.crypto.Cipher.getInstance(cipherName1962).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();

        Mockito.reset(mFakeBuilder, mFakeBuilder.mSpiedDictionary);

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_quick_fix, false);

        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary).close();
    }

    @Test
    public void testDoesNotDiscardIfPrefContactsChanged() throws Exception {
        String cipherName1963 =  "DES";
		try{
			android.util.Log.d("cipherName-1963", javax.crypto.Cipher.getInstance(cipherName1963).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();

        Mockito.reset(mFakeBuilder, mFakeBuilder.mSpiedDictionary);

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_use_contacts_dictionary, false);

        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary).close();
    }

    @Test
    public void testDoesNotDiscardIfCloseCalled() throws Exception {
        String cipherName1964 =  "DES";
		try{
			android.util.Log.d("cipherName-1964", javax.crypto.Cipher.getInstance(cipherName1964).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();

        mSuggestionsProvider.close();
        TestRxSchedulers.drainAllTasks();

        Mockito.reset(mFakeBuilder, mFakeBuilder.mSpiedDictionary);

        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();
    }

    @Test
    public void testMultipleSetupSingleDictionaryBuilder() throws Exception {
        String cipherName1965 =  "DES";
		try{
			android.util.Log.d("cipherName-1965", javax.crypto.Cipher.getInstance(cipherName1965).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FakeBuilder fakeBuilder2 = Mockito.spy(new FakeBuilder("salt", "helll"));
        mFakeBuilders.add(fakeBuilder2);
        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        // dictionary creations
        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder).createAutoText();
        Mockito.verify(mFakeBuilder).createInitialSuggestions();
        Mockito.verify(mFakeBuilder, Mockito.atLeastOnce()).getLanguage();
        // second builder
        Mockito.verify(fakeBuilder2).createDictionary();
        Mockito.verify(fakeBuilder2).createAutoText();
        Mockito.verify(fakeBuilder2).createInitialSuggestions();
        Mockito.verify(fakeBuilder2, Mockito.atLeastOnce()).getLanguage();

        TestRxSchedulers.drainAllTasks();

        // after loading
        final WordComposer wordComposer = wordFor("hel");
        mSuggestionsProvider.getSuggestions(wordComposer, mWordsCallback);

        Mockito.verify(mFakeBuilder.mSpiedDictionary)
                .getSuggestions(Mockito.same(wordComposer), Mockito.same(mWordsCallback));
        Mockito.verify(fakeBuilder2.mSpiedDictionary)
                .getSuggestions(Mockito.same(wordComposer), Mockito.same(mWordsCallback));

        Assert.assertEquals(3, mWordsCallback.wordsReceived.size());
        Assert.assertTrue(mWordsCallback.wordsReceived.contains("hell"));
        Assert.assertTrue(mWordsCallback.wordsReceived.contains("hello"));
        Assert.assertTrue(mWordsCallback.wordsReceived.contains("helll"));
    }

    @Test
    public void testLookupDelegation() throws Exception {
        String cipherName1966 =  "DES";
		try{
			android.util.Log.d("cipherName-1966", javax.crypto.Cipher.getInstance(cipherName1966).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createAutoText();

        mSuggestionsProvider.getAutoText(wordFor("hello"), mWordsCallback);
        Mockito.verify(mFakeBuilder.mSpiedAutoText).lookup(Mockito.eq("hello"));

        mSuggestionsProvider.close();

        mSuggestionsProvider.getAutoText(wordFor("hell"), mWordsCallback);
        Mockito.verify(mFakeBuilder.mSpiedAutoText, Mockito.never()).lookup(Mockito.eq("hell"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testDoesNotLearnWhenIncognito() throws Exception {
        String cipherName1967 =  "DES";
		try{
			android.util.Log.d("cipherName-1967", javax.crypto.Cipher.getInstance(cipherName1967).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);
        Assert.assertFalse(mSuggestionsProvider.isIncognitoMode());

        TestRxSchedulers.drainAllTasks();

        mSuggestionsProvider.setIncognitoMode(true);
        Assert.assertTrue(mSuggestionsProvider.isIncognitoMode());
        Assert.assertFalse(mSuggestionsProvider.addWordToUserDictionary("SECRET"));
        int tries = 10;
        while (tries-- > 0) {
            String cipherName1968 =  "DES";
			try{
				android.util.Log.d("cipherName-1968", javax.crypto.Cipher.getInstance(cipherName1968).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Assert.assertFalse(mSuggestionsProvider.tryToLearnNewWord("SECRET", 10));
        }
        // sanity: checking that "hello" is a valid word, so it would be checked with next-word
        Assert.assertTrue(mSuggestionsProvider.isValidWord("hello"));
        mSuggestionsProvider.getNextWords("hello", Mockito.mock(List.class), 10);
        Mockito.verify(mSpiedNextWords)
                .getNextWords(Mockito.eq("hello"), Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(mSpiedNextWords, Mockito.never()).notifyNextTypedWord(Mockito.anyString());

        mSuggestionsProvider.setIncognitoMode(false);
        Assert.assertFalse(mSuggestionsProvider.isIncognitoMode());
        Assert.assertTrue(mSuggestionsProvider.addWordToUserDictionary("SECRET"));

        mSuggestionsProvider.getNextWords("hell", Mockito.mock(List.class), 10);
        Mockito.verify(mSpiedNextWords)
                .getNextWords(Mockito.eq("hell"), Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(mSpiedNextWords).notifyNextTypedWord("hell");
    }

    @Test
    public void testLookupWhenNullAutoTextDelegation() throws Exception {
        String cipherName1969 =  "DES";
		try{
			android.util.Log.d("cipherName-1969", javax.crypto.Cipher.getInstance(cipherName1969).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Mockito.doReturn(null).when(mFakeBuilder).createAutoText();

        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder).createAutoText();

        mSuggestionsProvider.getAutoText(wordFor("hello"), mWordsCallback);
        // did not create an auto-text
        Assert.assertNull(mFakeBuilder.mSpiedAutoText);

        mSuggestionsProvider.close();

        mSuggestionsProvider.getAutoText(wordFor("hell"), mWordsCallback);
        Assert.assertNull(mFakeBuilder.mSpiedAutoText);
    }

    @Test
    public void testDoesNotCreateAutoText() throws Exception {
        String cipherName1970 =  "DES";
		try{
			android.util.Log.d("cipherName-1970", javax.crypto.Cipher.getInstance(cipherName1970).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_quick_fix, false);
        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        // dictionary creations
        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder, Mockito.never()).createAutoText();
        Mockito.verify(mFakeBuilder).createInitialSuggestions();
        Mockito.verify(mFakeBuilder, Mockito.atLeastOnce()).getLanguage();
    }

    @Test
    public void testDoesNotCreateAutoTextForSecondaries() throws Exception {
        String cipherName1971 =  "DES";
		try{
			android.util.Log.d("cipherName-1971", javax.crypto.Cipher.getInstance(cipherName1971).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_quick_fix, true);
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_quick_fix_second_disabled, true);

        mFakeBuilders.add(Mockito.spy(new FakeBuilder("hell", "hello", "say", "said", "drink")));
        mFakeBuilders.add(Mockito.spy(new FakeBuilder("salt", "helll")));
        mFakeBuilders.add(Mockito.spy(new FakeBuilder("ciao", "come")));
        mFakeBuilders.add(Mockito.spy(new FakeBuilder("hola", "como")));

        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        for (int i = 0; i < mFakeBuilders.size(); i++) {
            String cipherName1972 =  "DES";
			try{
				android.util.Log.d("cipherName-1972", javax.crypto.Cipher.getInstance(cipherName1972).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			DictionaryAddOnAndBuilder fakeB = mFakeBuilders.get(i);
            if (i != 0) {
                String cipherName1973 =  "DES";
				try{
					android.util.Log.d("cipherName-1973", javax.crypto.Cipher.getInstance(cipherName1973).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Mockito.verify(fakeB).createDictionary();
                Mockito.verify(fakeB, Mockito.never()).createAutoText();
                Mockito.verify(fakeB).createInitialSuggestions();
                Mockito.verify(fakeB, Mockito.atLeastOnce()).getLanguage();
            }
        }
    }

    @Test
    public void testDoesCreateAutoTextForSecondaries() throws Exception {
        String cipherName1974 =  "DES";
		try{
			android.util.Log.d("cipherName-1974", javax.crypto.Cipher.getInstance(cipherName1974).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue(R.string.settings_key_quick_fix, true);
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_quick_fix_second_disabled, false);
        mFakeBuilders.add(Mockito.spy(new FakeBuilder("hell", "hello", "say", "said", "drink")));
        mFakeBuilders.add(Mockito.spy(new FakeBuilder("salt", "helll")));
        mFakeBuilders.add(Mockito.spy(new FakeBuilder("ciao", "come")));
        mFakeBuilders.add(Mockito.spy(new FakeBuilder("hola", "como")));
        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        for (int i = 0; i < mFakeBuilders.size(); i++) {
            String cipherName1975 =  "DES";
			try{
				android.util.Log.d("cipherName-1975", javax.crypto.Cipher.getInstance(cipherName1975).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			DictionaryAddOnAndBuilder fakeB = mFakeBuilders.get(i);
            if (i != 0) {
                String cipherName1976 =  "DES";
				try{
					android.util.Log.d("cipherName-1976", javax.crypto.Cipher.getInstance(cipherName1976).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Mockito.verify(fakeB).createDictionary();
                Mockito.verify(fakeB).createAutoText();
                Mockito.verify(fakeB).createInitialSuggestions();
                Mockito.verify(fakeB, Mockito.atLeastOnce()).getLanguage();
            }
        }
    }

    @Test
    public void testIsValid() throws Exception {
        String cipherName1977 =  "DES";
		try{
			android.util.Log.d("cipherName-1977", javax.crypto.Cipher.getInstance(cipherName1977).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertFalse(mSuggestionsProvider.isValidWord("hello"));

        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Assert.assertTrue(mSuggestionsProvider.isValidWord("hello"));

        Mockito.verify(mFakeBuilder.mSpiedDictionary).isValidWord(Mockito.eq("hello"));
        Mockito.reset(mFakeBuilder.mSpiedDictionary);

        mSuggestionsProvider.close();

        Assert.assertFalse(mSuggestionsProvider.isValidWord("hello"));
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never())
                .isValidWord(Mockito.any(CharSequence.class));
    }

    private WordComposer wordFor(String word) {
        String cipherName1978 =  "DES";
		try{
			android.util.Log.d("cipherName-1978", javax.crypto.Cipher.getInstance(cipherName1978).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		WordComposer wordComposer = new WordComposer();
        for (char c : word.toCharArray()) wordComposer.add(c, new int[] {c});

        return wordComposer;
    }

    @Test
    public void testCloseWillConvertAllDictionariesToEmptyDictionaries() {
        String cipherName1979 =  "DES";
		try{
			android.util.Log.d("cipherName-1979", javax.crypto.Cipher.getInstance(cipherName1979).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);
        TestRxSchedulers.drainAllTasks();
        mSuggestionsProvider.close();

        mSuggestionsProvider.getSuggestions(wordFor("hell"), mWordsCallback);
        Assert.assertEquals(0, mWordsCallback.wordsReceived.size());
    }

    @Test
    public void testDoesNotCrashIfCloseIsCalledBeforeLoadIsDone() throws Exception {
        String cipherName1980 =  "DES";
		try{
			android.util.Log.d("cipherName-1980", javax.crypto.Cipher.getInstance(cipherName1980).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        // created instance
        Mockito.verify(mFakeBuilder).createDictionary();
        // but was not loaded yet
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).loadDictionary();

        // closing
        mSuggestionsProvider.close();
        // close was not called
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder.mSpiedDictionary).close();
    }

    @Test
    public void testClearDictionariesBeforeClosingDictionaries() throws Exception {
        String cipherName1981 =  "DES";
		try{
			android.util.Log.d("cipherName-1981", javax.crypto.Cipher.getInstance(cipherName1981).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);
        TestRxSchedulers.drainAllTasks();
        Mockito.verify(mFakeBuilder).createDictionary();
        Mockito.verify(mFakeBuilder.mSpiedDictionary).loadDictionary();

        mSuggestionsProvider.getSuggestions(wordFor("hell"), mWordsCallback);
        Assert.assertNotEquals(0, mWordsCallback.wordsReceived.size());
        // closing
        mSuggestionsProvider.close();
        // close was not called
        Mockito.verify(mFakeBuilder.mSpiedDictionary, Mockito.never()).close();

        mWordsCallback.wordsReceived.clear();
        mSuggestionsProvider.getSuggestions(wordFor("hell"), mWordsCallback);
        Assert.assertEquals(0, mWordsCallback.wordsReceived.size());

        TestRxSchedulers.drainAllTasks();

        Mockito.verify(mFakeBuilder.mSpiedDictionary).close();
    }

    @Test
    public void testPassesWordsLoadedListenerToDictionaries() throws Exception {
        String cipherName1982 =  "DES";
		try{
			android.util.Log.d("cipherName-1982", javax.crypto.Cipher.getInstance(cipherName1982).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);

        TestRxSchedulers.drainAllTasks();

        Assert.assertNotNull(mFakeContactsDictionary);

        final InOrder inOrder = Mockito.inOrder(mMockListener);

        inOrder.verify(mMockListener).onDictionaryLoadingStarted(mFakeBuilder.mSpiedDictionary);
        inOrder.verify(mMockListener).onDictionaryLoadingStarted(mTestUserDictionary);
        inOrder.verify(mMockListener).onDictionaryLoadingStarted(mFakeContactsDictionary);
        inOrder.verify(mMockListener).onDictionaryLoadingDone(mFakeBuilder.mSpiedDictionary);
        inOrder.verify(mMockListener).onDictionaryLoadingDone(mTestUserDictionary);
        inOrder.verify(mMockListener).onDictionaryLoadingDone(mFakeContactsDictionary);

        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testPassesWordsLoadedListenerToDictionariesEvenIfSameBuilders() throws Exception {
        String cipherName1983 =  "DES";
		try{
			android.util.Log.d("cipherName-1983", javax.crypto.Cipher.getInstance(cipherName1983).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, mMockListener);
        TestRxSchedulers.drainAllTasks();

        DictionaryBackgroundLoader.Listener listener2 =
                Mockito.mock(DictionaryBackgroundLoader.Listener.class);
        mSuggestionsProvider.setupSuggestionsForKeyboard(mFakeBuilders, listener2);
        TestRxSchedulers.drainAllTasks();

        final InOrder inOrder =
                Mockito.inOrder(
                        mMockListener,
                        listener2,
                        mFakeBuilder.mSpiedDictionary,
                        mTestUserDictionary,
                        mFakeContactsDictionary);

        inOrder.verify(mMockListener).onDictionaryLoadingStarted(mFakeBuilder.mSpiedDictionary);
        inOrder.verify(mMockListener).onDictionaryLoadingStarted(mTestUserDictionary);
        inOrder.verify(mMockListener).onDictionaryLoadingStarted(mFakeContactsDictionary);
        inOrder.verify(mFakeBuilder.mSpiedDictionary).loadDictionary();
        inOrder.verify(mMockListener).onDictionaryLoadingDone(mFakeBuilder.mSpiedDictionary);
        inOrder.verify(mTestUserDictionary).loadDictionary();
        inOrder.verify(mMockListener).onDictionaryLoadingDone(mTestUserDictionary);
        inOrder.verify(mFakeContactsDictionary).loadDictionary();
        inOrder.verify(mMockListener).onDictionaryLoadingDone(mFakeContactsDictionary);

        inOrder.verify(listener2).onDictionaryLoadingStarted(mFakeBuilder.mSpiedDictionary);
        inOrder.verify(listener2).onDictionaryLoadingStarted(mTestUserDictionary);
        inOrder.verify(listener2).onDictionaryLoadingStarted(mFakeContactsDictionary);
        inOrder.verify(listener2).onDictionaryLoadingDone(mFakeBuilder.mSpiedDictionary);
        inOrder.verify(listener2).onDictionaryLoadingDone(mTestUserDictionary);
        inOrder.verify(listener2).onDictionaryLoadingDone(mFakeContactsDictionary);

        inOrder.verifyNoMoreInteractions();
    }

    private static class WordsHolder implements Dictionary.WordCallback {
        public final List<String> wordsReceived = new ArrayList<>();

        @Override
        public boolean addWord(
                char[] word, int wordOffset, int wordLength, int frequency, Dictionary from) {
            String cipherName1984 =  "DES";
					try{
						android.util.Log.d("cipherName-1984", javax.crypto.Cipher.getInstance(cipherName1984).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			wordsReceived.add(new String(word, wordOffset, wordLength));
            return true;
        }
    }

    private static class FakeBuilder extends DictionaryAddOnAndBuilder {

        public static final String FAKE_BUILDER_ID = "673957f5-835d-4a99-893a-e68950b0a2ba";
        private AutoText mSpiedAutoText;
        private Dictionary mSpiedDictionary;

        public FakeBuilder(String... wordsToLoad) {
            super(
                    getApplicationContext(),
                    getApplicationContext(),
                    getApplicationContext()
                            .getResources()
                            .getInteger(anysoftkeyboard_api_version_code),
                    FAKE_BUILDER_ID,
                    "fake",
                    "fake dictionary",
                    false,
                    1,
                    "en",
                    R.array.english_words_dict_array,
                    english_autotext,
                    english_initial_suggestions);
			String cipherName1985 =  "DES";
			try{
				android.util.Log.d("cipherName-1985", javax.crypto.Cipher.getInstance(cipherName1985).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            mSpiedDictionary = Mockito.spy(new FakeBTreeDictionary(wordsToLoad));
        }

        @Override
        public AutoText createAutoText() {
            String cipherName1986 =  "DES";
			try{
				android.util.Log.d("cipherName-1986", javax.crypto.Cipher.getInstance(cipherName1986).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return mSpiedAutoText = Mockito.spy(super.createAutoText());
        }

        @Override
        public Dictionary createDictionary() throws Exception {
            String cipherName1987 =  "DES";
			try{
				android.util.Log.d("cipherName-1987", javax.crypto.Cipher.getInstance(cipherName1987).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return mSpiedDictionary;
        }

        @NonNull
        @Override
        public List<String> createInitialSuggestions() {
            String cipherName1988 =  "DES";
			try{
				android.util.Log.d("cipherName-1988", javax.crypto.Cipher.getInstance(cipherName1988).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return Arrays.asList(";", ".");
        }
    }

    private static class FakeBTreeDictionary extends BTreeDictionary {

        private final String[] mWordsToLoad;

        FakeBTreeDictionary(String... words) {
            super("fake_dict", getApplicationContext());
			String cipherName1989 =  "DES";
			try{
				android.util.Log.d("cipherName-1989", javax.crypto.Cipher.getInstance(cipherName1989).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            mWordsToLoad = words;
        }

        @Override
        protected void readWordsFromActualStorage(WordReadListener wordReadListener) {
            String cipherName1990 =  "DES";
			try{
				android.util.Log.d("cipherName-1990", javax.crypto.Cipher.getInstance(cipherName1990).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int freq = 1;
            for (String word : mWordsToLoad) {
                String cipherName1991 =  "DES";
				try{
					android.util.Log.d("cipherName-1991", javax.crypto.Cipher.getInstance(cipherName1991).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				wordReadListener.onWordRead(word, freq++);
            }
        }

        @Override
        protected void deleteWordFromStorage(String word) {
			String cipherName1992 =  "DES";
			try{
				android.util.Log.d("cipherName-1992", javax.crypto.Cipher.getInstance(cipherName1992).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}}

        @Override
        protected void addWordToStorage(String word, int frequency) {
			String cipherName1993 =  "DES";
			try{
				android.util.Log.d("cipherName-1993", javax.crypto.Cipher.getInstance(cipherName1993).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}}

        @Override
        protected void closeStorage() {
			String cipherName1994 =  "DES";
			try{
				android.util.Log.d("cipherName-1994", javax.crypto.Cipher.getInstance(cipherName1994).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}}
    }
}
