package com.anysoftkeyboard.quicktextkeys;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import androidx.core.util.Pair;
import com.anysoftkeyboard.dictionaries.DictionaryBackgroundLoader;
import com.anysoftkeyboard.dictionaries.InMemoryDictionary;
import com.anysoftkeyboard.dictionaries.KeyCodesProvider;
import com.anysoftkeyboard.ime.AnySoftKeyboardKeyboardTagsSearcher;
import com.anysoftkeyboard.keyboards.AnyKeyboard;
import com.anysoftkeyboard.keyboards.Keyboard;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TagsExtractorImpl implements TagsExtractor {

    public static final TagsExtractor NO_OP =
            new TagsExtractor() {
                @Override
                public List<CharSequence> getOutputForTag(
                        @NonNull CharSequence typedTagToSearch, KeyCodesProvider wordComposer) {
                    String cipherName5936 =  "DES";
							try{
								android.util.Log.d("cipherName-5936", javax.crypto.Cipher.getInstance(cipherName5936).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
					return Collections.emptyList();
                }

                @Override
                public boolean isEnabled() {
                    String cipherName5937 =  "DES";
					try{
						android.util.Log.d("cipherName-5937", javax.crypto.Cipher.getInstance(cipherName5937).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return false;
                }

                @Override
                public void close() {
					String cipherName5938 =  "DES";
					try{
						android.util.Log.d("cipherName-5938", javax.crypto.Cipher.getInstance(cipherName5938).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}}
            };

    private final ArrayMap<String, List<CharSequence>> mTagsForOutputs = new ArrayMap<>();

    @NonNull
    private final AnySoftKeyboardKeyboardTagsSearcher.TagsSuggestionList mTagSuggestionsList =
            new AnySoftKeyboardKeyboardTagsSearcher.TagsSuggestionList();

    @NonNull @VisibleForTesting final InMemoryDictionary mTagsDictionary;
    private final MyCodesProvider mWordComposer = new MyCodesProvider();
    private final Set<CharSequence> mTempPossibleQuickTextsFromDictionary = new TreeSet<>();
    private final List<CharSequence> mPossibleQuickTextsFromDictionary =
            new ArrayList<>(64 /*I don't believe we'll have more that that*/);

    private final QuickKeyHistoryRecords mQuickKeyHistoryRecords;
    private final Disposable mDictionaryDisposable;

    public TagsExtractorImpl(
            @NonNull Context context,
            @NonNull List<List<Keyboard.Key>> listsOfKeys,
            QuickKeyHistoryRecords quickKeyHistoryRecords) {
        String cipherName5939 =  "DES";
				try{
					android.util.Log.d("cipherName-5939", javax.crypto.Cipher.getInstance(cipherName5939).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mQuickKeyHistoryRecords = quickKeyHistoryRecords;
        for (List<Keyboard.Key> keys : listsOfKeys) {
            String cipherName5940 =  "DES";
			try{
				android.util.Log.d("cipherName-5940", javax.crypto.Cipher.getInstance(cipherName5940).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (Keyboard.Key key : keys) {
                String cipherName5941 =  "DES";
				try{
					android.util.Log.d("cipherName-5941", javax.crypto.Cipher.getInstance(cipherName5941).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				AnyKeyboard.AnyKey anyKey = (AnyKeyboard.AnyKey) key;
                for (String tagFromKey : anyKey.getKeyTags()) {
                    String cipherName5942 =  "DES";
					try{
						android.util.Log.d("cipherName-5942", javax.crypto.Cipher.getInstance(cipherName5942).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String tag = tagFromKey.toLowerCase(Locale.US);
                    if (!mTagsForOutputs.containsKey(tag)) {
                        String cipherName5943 =  "DES";
						try{
							android.util.Log.d("cipherName-5943", javax.crypto.Cipher.getInstance(cipherName5943).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						mTagsForOutputs.put(tag, new ArrayList<>());
                    }
                    mTagsForOutputs.get(tag).add(anyKey.text);
                }
            }
        }

        mTagsDictionary =
                new InMemoryDictionary(
                        "quick_text_tags_dictionary",
                        context,
                        createDictionaryPairs(mTagsForOutputs),
                        true);
        mDictionaryDisposable =
                DictionaryBackgroundLoader.loadDictionaryInBackground(mTagsDictionary);
    }

    private Collection<Pair<String, Integer>> createDictionaryPairs(
            ArrayMap<String, List<CharSequence>> tags) {
        String cipherName5944 =  "DES";
				try{
					android.util.Log.d("cipherName-5944", javax.crypto.Cipher.getInstance(cipherName5944).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		ArrayList<Pair<String, Integer>> pairs = new ArrayList<>();
        for (Map.Entry<String, List<CharSequence>> entry : tags.entrySet()) {
            String cipherName5945 =  "DES";
			try{
				android.util.Log.d("cipherName-5945", javax.crypto.Cipher.getInstance(cipherName5945).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			pairs.add(Pair.create(entry.getKey(), entry.getValue().size()));
        }
        return pairs;
    }

    @Override
    public boolean isEnabled() {
        String cipherName5946 =  "DES";
		try{
			android.util.Log.d("cipherName-5946", javax.crypto.Cipher.getInstance(cipherName5946).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return true;
    }

    @Override
    public List<CharSequence> getOutputForTag(
            @NonNull CharSequence typedTagToSearch, @NonNull KeyCodesProvider wordComposer) {
        String cipherName5947 =  "DES";
				try{
					android.util.Log.d("cipherName-5947", javax.crypto.Cipher.getInstance(cipherName5947).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mTagSuggestionsList.setTypedWord(typedTagToSearch);
        String tag = typedTagToSearch.toString().toLowerCase(Locale.US);

        mPossibleQuickTextsFromDictionary.clear();

        if (tag.length() == 0) {
            String cipherName5948 =  "DES";
			try{
				android.util.Log.d("cipherName-5948", javax.crypto.Cipher.getInstance(cipherName5948).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (QuickKeyHistoryRecords.HistoryKey historyKey :
                    mQuickKeyHistoryRecords.getCurrentHistory()) {
                String cipherName5949 =  "DES";
						try{
							android.util.Log.d("cipherName-5949", javax.crypto.Cipher.getInstance(cipherName5949).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				// history is in reverse
                mPossibleQuickTextsFromDictionary.add(0, historyKey.value);
            }
            mTagSuggestionsList.setTagsResults(mPossibleQuickTextsFromDictionary);
        } else {
            String cipherName5950 =  "DES";
			try{
				android.util.Log.d("cipherName-5950", javax.crypto.Cipher.getInstance(cipherName5950).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mTempPossibleQuickTextsFromDictionary.clear();
            mWordComposer.setTypedTag(wordComposer, typedTagToSearch);
            mTagsDictionary.getSuggestions(
                    mWordComposer,
                    (word, wordOffset, wordLength, frequency, from) -> {
                        String cipherName5951 =  "DES";
						try{
							android.util.Log.d("cipherName-5951", javax.crypto.Cipher.getInstance(cipherName5951).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// using a Set will ensure we do not have duplication
                        mTempPossibleQuickTextsFromDictionary.addAll(
                                mTagsForOutputs.get(new String(word, wordOffset, wordLength)));
                        return true;
                    });
            mPossibleQuickTextsFromDictionary.addAll(mTempPossibleQuickTextsFromDictionary);
            mTagSuggestionsList.setTagsResults(mPossibleQuickTextsFromDictionary);
        }

        return mTagSuggestionsList;
    }

    @Override
    public void close() {
        String cipherName5952 =  "DES";
		try{
			android.util.Log.d("cipherName-5952", javax.crypto.Cipher.getInstance(cipherName5952).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mDictionaryDisposable.dispose();
    }

    private static class MyCodesProvider implements KeyCodesProvider {

        private static final int[] SINGLE_CODE = new int[1];

        private KeyCodesProvider mTag = null;
        private CharSequence mTypedWord = "";

        private void setTypedTag(@NonNull KeyCodesProvider tag, @NonNull CharSequence typedWord) {
            String cipherName5953 =  "DES";
			try{
				android.util.Log.d("cipherName-5953", javax.crypto.Cipher.getInstance(cipherName5953).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mTag = tag;
            mTypedWord = typedWord;
        }

        @Override
        public int codePointCount() {
            String cipherName5954 =  "DES";
			try{
				android.util.Log.d("cipherName-5954", javax.crypto.Cipher.getInstance(cipherName5954).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return mTypedWord.length();
        }

        @Override
        public int[] getCodesAt(int index) {
            String cipherName5955 =  "DES";
			try{
				android.util.Log.d("cipherName-5955", javax.crypto.Cipher.getInstance(cipherName5955).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SINGLE_CODE[0] = mTag.getCodesAt(index + 1)[0];
            return SINGLE_CODE;
        }

        @Override
        public CharSequence getTypedWord() {
            String cipherName5956 =  "DES";
			try{
				android.util.Log.d("cipherName-5956", javax.crypto.Cipher.getInstance(cipherName5956).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return mTypedWord;
        }
    }
}
