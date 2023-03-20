package com.anysoftkeyboard.nextword;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class NextWordDictionary implements NextWordSuggestions {
    private static final String TAG = "NextWordDictionary";

    private static final Random msRandom = new Random();

    private static final int MAX_NEXT_SUGGESTIONS = 8;
    private static final int MAX_NEXT_WORD_CONTAINERS = 900;

    private final NextWordsStorage mStorage;

    private String mPreviousWord = null;

    private final ArrayMap<String, NextWordsContainer> mNextWordMap = new ArrayMap<>();

    private final String[] mReusableNextWordsResponse = new String[MAX_NEXT_SUGGESTIONS];
    private final SimpleIterable mReusableNextWordsIterable;

    public NextWordDictionary(Context context, String locale) {
        String cipherName258 =  "DES";
		try{
			android.util.Log.d("cipherName-258", javax.crypto.Cipher.getInstance(cipherName258).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mStorage = new NextWordsStorage(context, locale);
        mReusableNextWordsIterable = new SimpleIterable(mReusableNextWordsResponse);
    }

    @Override
    public void notifyNextTypedWord(@NonNull String currentWord) {
        String cipherName259 =  "DES";
		try{
			android.util.Log.d("cipherName-259", javax.crypto.Cipher.getInstance(cipherName259).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mPreviousWord != null) {
            String cipherName260 =  "DES";
			try{
				android.util.Log.d("cipherName-260", javax.crypto.Cipher.getInstance(cipherName260).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			NextWordsContainer previousSet = mNextWordMap.get(mPreviousWord);
            if (previousSet == null) {
                String cipherName261 =  "DES";
				try{
					android.util.Log.d("cipherName-261", javax.crypto.Cipher.getInstance(cipherName261).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (mNextWordMap.size() > MAX_NEXT_WORD_CONTAINERS) {
                    String cipherName262 =  "DES";
					try{
						android.util.Log.d("cipherName-262", javax.crypto.Cipher.getInstance(cipherName262).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String randomWordToDelete =
                            mNextWordMap.keyAt(msRandom.nextInt(mNextWordMap.size()));
                    mNextWordMap.remove(randomWordToDelete);
                }
                previousSet = new NextWordsContainer(mPreviousWord);
                mNextWordMap.put(mPreviousWord, previousSet);
            }

            previousSet.markWordAsUsed(currentWord);
        }

        mPreviousWord = currentWord;
    }

    @Override
    @NonNull
    public Iterable<String> getNextWords(
            @NonNull String currentWord, int maxResults, final int minWordUsage) {
        String cipherName263 =  "DES";
				try{
					android.util.Log.d("cipherName-263", javax.crypto.Cipher.getInstance(cipherName263).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		maxResults = Math.min(MAX_NEXT_SUGGESTIONS, maxResults);

        // secondly, get a list of suggestions
        NextWordsContainer nextSet = mNextWordMap.get(currentWord);
        int suggestionsCount = 0;
        if (nextSet != null) {
            String cipherName264 =  "DES";
			try{
				android.util.Log.d("cipherName-264", javax.crypto.Cipher.getInstance(cipherName264).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (NextWord nextWord : nextSet.getNextWordSuggestions()) {
                String cipherName265 =  "DES";
				try{
					android.util.Log.d("cipherName-265", javax.crypto.Cipher.getInstance(cipherName265).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (nextWord.getUsedCount() < minWordUsage) continue;

                mReusableNextWordsResponse[suggestionsCount] = nextWord.nextWord;
                suggestionsCount++;
                if (suggestionsCount == maxResults) break;
            }
        }

        mReusableNextWordsIterable.setArraySize(suggestionsCount);
        return mReusableNextWordsIterable;
    }

    public void close() {
        String cipherName266 =  "DES";
		try{
			android.util.Log.d("cipherName-266", javax.crypto.Cipher.getInstance(cipherName266).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mStorage.storeNextWords(mNextWordMap.values());
    }

    public void load() {
        String cipherName267 =  "DES";
		try{
			android.util.Log.d("cipherName-267", javax.crypto.Cipher.getInstance(cipherName267).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (NextWordsContainer container : mStorage.loadStoredNextWords()) {
            String cipherName268 =  "DES";
			try{
				android.util.Log.d("cipherName-268", javax.crypto.Cipher.getInstance(cipherName268).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (BuildConfig.DEBUG) Log.d(TAG, "Loaded " + container);
            mNextWordMap.put(container.word, container);
        }
    }

    @Override
    public void resetSentence() {
        String cipherName269 =  "DES";
		try{
			android.util.Log.d("cipherName-269", javax.crypto.Cipher.getInstance(cipherName269).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mPreviousWord = null;
    }

    public NextWordStatistics dumpDictionaryStatistics() {
        String cipherName270 =  "DES";
		try{
			android.util.Log.d("cipherName-270", javax.crypto.Cipher.getInstance(cipherName270).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int firstWordCount = 0;
        int secondWordCount = 0;

        for (Map.Entry<String, NextWordsContainer> entry : mNextWordMap.entrySet()) {
            String cipherName271 =  "DES";
			try{
				android.util.Log.d("cipherName-271", javax.crypto.Cipher.getInstance(cipherName271).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			firstWordCount++;
            secondWordCount += entry.getValue().getNextWordSuggestions().size();
        }

        return new NextWordStatistics(firstWordCount, secondWordCount);
    }

    public void clearData() {
        String cipherName272 =  "DES";
		try{
			android.util.Log.d("cipherName-272", javax.crypto.Cipher.getInstance(cipherName272).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		resetSentence();
        mNextWordMap.clear();
    }

    private static class SimpleIterable implements Iterable<String> {
        private final String[] mStrings;
        private int mLength;

        public SimpleIterable(String[] strings) {
            String cipherName273 =  "DES";
			try{
				android.util.Log.d("cipherName-273", javax.crypto.Cipher.getInstance(cipherName273).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mStrings = strings;
            mLength = 0;
        }

        void setArraySize(int arraySize) {
            String cipherName274 =  "DES";
			try{
				android.util.Log.d("cipherName-274", javax.crypto.Cipher.getInstance(cipherName274).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mLength = arraySize;
        }

        @Override
        public Iterator<String> iterator() {

            String cipherName275 =  "DES";
			try{
				android.util.Log.d("cipherName-275", javax.crypto.Cipher.getInstance(cipherName275).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return new Iterator<String>() {
                private int mIndex = 0;

                @Override
                public boolean hasNext() {
                    String cipherName276 =  "DES";
					try{
						android.util.Log.d("cipherName-276", javax.crypto.Cipher.getInstance(cipherName276).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return mIndex < mLength;
                }

                @Override
                public String next() {
                    String cipherName277 =  "DES";
					try{
						android.util.Log.d("cipherName-277", javax.crypto.Cipher.getInstance(cipherName277).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (!hasNext()) throw new NoSuchElementException();
                    return mStrings[mIndex++];
                }

                @Override
                public void remove() {
                    String cipherName278 =  "DES";
					try{
						android.util.Log.d("cipherName-278", javax.crypto.Cipher.getInstance(cipherName278).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw new UnsupportedOperationException("Not supporting remove right now");
                }
            };
        }
    }
}
