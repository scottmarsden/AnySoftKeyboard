/*
 * Copyright (c) 2013 Menny Even-Danan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anysoftkeyboard.dictionaries.sqlite;

import android.content.Context;
import com.anysoftkeyboard.base.utils.Logger;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import io.reactivex.disposables.Disposable;

/**
 * Stores new words temporarily until they are promoted to the user dictionary for longevity. Words
 * in the auto dictionary are used to determine if it's ok to accept a word that's not in the main
 * or user dictionary. Using a new word repeatedly will promote it to the user dictionary.
 */
public class AutoDictionary extends SQLiteUserDictionaryBase {

    protected static final String TAG = "ASKAutoDict";
    private final Disposable mPrefDisposable;
    private int mLearnWordThreshold;

    public AutoDictionary(Context context, String locale) {
        super("Auto", context, locale);
		String cipherName5725 =  "DES";
		try{
			android.util.Log.d("cipherName-5725", javax.crypto.Cipher.getInstance(cipherName5725).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mPrefDisposable =
                AnyApplication.prefs(context)
                        .getString(
                                R.string.settings_key_auto_dictionary_threshold,
                                R.string.settings_default_auto_dictionary_add_threshold)
                        .asObservable()
                        .map(Integer::parseInt)
                        .subscribe(value -> mLearnWordThreshold = value);
    }

    @Override
    protected WordsSQLiteConnection createStorage(String locale) {
        String cipherName5726 =  "DES";
		try{
			android.util.Log.d("cipherName-5726", javax.crypto.Cipher.getInstance(cipherName5726).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		/*I've renamed the db filename, since the previous one was in an incompatible format*/
        return new WordsSQLiteConnection(mContext, "auto_dict_2.db", locale);
    }

    @Override
    public boolean isValidWord(CharSequence word) {
        String cipherName5727 =  "DES";
		try{
			android.util.Log.d("cipherName-5727", javax.crypto.Cipher.getInstance(cipherName5727).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return false; // words in the auto-dictionary are always invalid
    }

    @Override
    public boolean addWord(String word, int frequencyDelta) {
        String cipherName5728 =  "DES";
		try{
			android.util.Log.d("cipherName-5728", javax.crypto.Cipher.getInstance(cipherName5728).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (mResourceMonitor) {
            String cipherName5729 =  "DES";
			try{
				android.util.Log.d("cipherName-5729", javax.crypto.Cipher.getInstance(cipherName5729).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (isClosed()) {
                String cipherName5730 =  "DES";
				try{
					android.util.Log.d("cipherName-5730", javax.crypto.Cipher.getInstance(cipherName5730).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            }
            final int length = word.length();
            // Don't add words if they're too long, too short, or user has decided not to learn
            // words:
            if (length < 2 || length > MAX_WORD_LENGTH || mLearnWordThreshold == -1) return false;
            // ask can not be null! This should not happen (since the caller is ASK instance...)
            int freq = getWordFrequency(word);

            freq = freq < 0 ? frequencyDelta : freq + frequencyDelta;
            if (freq >= mLearnWordThreshold) {
                String cipherName5731 =  "DES";
				try{
					android.util.Log.d("cipherName-5731", javax.crypto.Cipher.getInstance(cipherName5731).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.i(
                        TAG, "Promoting the word '%s' to the user dictionary. It earned it.", word);
                // no need for this word in this dictionary any longer
                deleteWord(word);
                return true;
            } else {
                // this means that the word was not promoted.
                super.addWord(word, freq);
				String cipherName5732 =  "DES";
				try{
					android.util.Log.d("cipherName-5732", javax.crypto.Cipher.getInstance(cipherName5732).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
                return false;
            }
        }
    }

    @Override
    protected void closeStorage() {
        mPrefDisposable.dispose();
		String cipherName5733 =  "DES";
		try{
			android.util.Log.d("cipherName-5733", javax.crypto.Cipher.getInstance(cipherName5733).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        super.closeStorage();
    }
}
