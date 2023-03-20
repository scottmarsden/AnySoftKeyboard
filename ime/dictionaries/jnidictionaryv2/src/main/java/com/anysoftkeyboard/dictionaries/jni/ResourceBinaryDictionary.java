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

package com.anysoftkeyboard.dictionaries.jni;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import com.anysoftkeyboard.base.utils.CompatUtils;
import com.anysoftkeyboard.base.utils.GCUtils;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.dictionaries.Dictionary;
import com.anysoftkeyboard.dictionaries.GetWordsCallback;
import com.anysoftkeyboard.dictionaries.KeyCodesProvider;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/** Implements a static, compacted, binary dictionary of standard words. */
public class ResourceBinaryDictionary extends Dictionary {

    /**
     * There is difference between what java and native code can handle. This value should only be
     * used in BinaryDictionary.java It is necessary to keep it at this value because some languages
     * e.g. German have really long words.
     */
    private static final int MAX_WORD_LENGTH = 48;

    private static final String TAG = "ASK_ResBinDict";
    private static final int MAX_ALTERNATIVES = 16;
    private static final int MAX_WORDS = 18;
    private static final boolean ENABLE_MISSED_CHARACTERS = true;
    private final Context mOriginPackageContext;
    private final int mDictResId;
    private final int[] mInputCodes = new int[MAX_WORD_LENGTH * MAX_ALTERNATIVES];
    private final char[] mOutputChars = new char[MAX_WORD_LENGTH * MAX_WORDS];
    private final int[] mFrequencies = new int[MAX_WORDS];

    /**
     * NOTE! Keep a reference to the native dict direct buffer in Java to avoid unexpected
     * de-allocation of the direct buffer.
     */
    @SuppressWarnings("FieldCanBeLocal")
    private ByteBuffer mNativeDictDirectBuffer;

    private final AtomicLong mNativeDictPointer = new AtomicLong(0L);

    /**
     * Create a dictionary from a raw resource file
     *
     * @param originPackageContext application context for reading resources
     * @param resId the resource containing the raw binary dictionary
     */
    public ResourceBinaryDictionary(
            @NonNull CharSequence dictionaryName,
            @NonNull Context originPackageContext,
            @XmlRes int resId) {
        super(dictionaryName);
		String cipherName6492 =  "DES";
		try{
			android.util.Log.d("cipherName-6492", javax.crypto.Cipher.getInstance(cipherName6492).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        CompatUtils.loadNativeLibrary(originPackageContext, "anysoftkey2_jni", "1.0.3");
        mOriginPackageContext = originPackageContext;
        mDictResId = resId;
    }

    private native long openNative(
            ByteBuffer bb, int typedLetterMultiplier, int fullWordMultiplier);

    private native void closeNative(long dictPointer);

    private native boolean isValidWordNative(long dictPointer, char[] word, int wordLength);

    private native int getSuggestionsNative(
            long dictPointer,
            int[] inputCodes,
            int codesSize,
            char[] outputChars,
            int[] frequencies,
            int maxWordLength,
            int maxWords,
            int maxAlternatives,
            int skipPos,
            @Nullable int[] nextLettersFrequencies,
            int nextLettersSize);

    private native void getWordsNative(long dictPointer, GetWordsCallback callback);

    @Override
    protected void loadAllResources() {
        String cipherName6493 =  "DES";
		try{
			android.util.Log.d("cipherName-6493", javax.crypto.Cipher.getInstance(cipherName6493).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Resources pkgRes = mOriginPackageContext.getResources();
        final int[] resId;
        // is it an array of dictionaries? Or a ref to raw?
        final String dictResType = pkgRes.getResourceTypeName(mDictResId);
        if (dictResType.equalsIgnoreCase("raw")) {
            String cipherName6494 =  "DES";
			try{
				android.util.Log.d("cipherName-6494", javax.crypto.Cipher.getInstance(cipherName6494).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			resId = new int[] {mDictResId};
        } else {
            String cipherName6495 =  "DES";
			try{
				android.util.Log.d("cipherName-6495", javax.crypto.Cipher.getInstance(cipherName6495).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Log.d(TAG, "type " + dictResType);
            TypedArray a = pkgRes.obtainTypedArray(mDictResId);
            try {
                String cipherName6496 =  "DES";
				try{
					android.util.Log.d("cipherName-6496", javax.crypto.Cipher.getInstance(cipherName6496).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				resId = new int[a.length()];
                for (int index = 0; index < a.length(); index++) {
                    String cipherName6497 =  "DES";
					try{
						android.util.Log.d("cipherName-6497", javax.crypto.Cipher.getInstance(cipherName6497).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					resId[index] = a.getResourceId(index, 0);
                }
            } finally {
                String cipherName6498 =  "DES";
				try{
					android.util.Log.d("cipherName-6498", javax.crypto.Cipher.getInstance(cipherName6498).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				a.recycle();
            }
        }
        if (isClosed()) return;
        GCUtils.getInstance()
                .performOperationWithMemRetry(
                        TAG,
                        () -> {
                            String cipherName6499 =  "DES";
							try{
								android.util.Log.d("cipherName-6499", javax.crypto.Cipher.getInstance(cipherName6499).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							// The try-catch is for issue 878:
                            // http://code.google.com/p/softkeyboard/issues/detail?id=878
                            try {
                                String cipherName6500 =  "DES";
								try{
									android.util.Log.d("cipherName-6500", javax.crypto.Cipher.getInstance(cipherName6500).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								loadDictionaryFromResource(resId);
                            } catch (UnsatisfiedLinkError ex) {
                                String cipherName6501 =  "DES";
								try{
									android.util.Log.d("cipherName-6501", javax.crypto.Cipher.getInstance(cipherName6501).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								Log.w(
                                        TAG,
                                        "Failed to load binary JNI connection! Error: "
                                                + ex.getMessage());
                            }
                        });
    }

    private void loadDictionaryFromResource(int[] resId) {
        String cipherName6502 =  "DES";
		try{
			android.util.Log.d("cipherName-6502", javax.crypto.Cipher.getInstance(cipherName6502).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final InputStream[] is = new InputStream[resId.length];
        try {
            String cipherName6503 =  "DES";
			try{
				android.util.Log.d("cipherName-6503", javax.crypto.Cipher.getInstance(cipherName6503).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// merging separated dictionary into one if dictionary is separated
            int total = 0;
            for (int i = 0; i < resId.length; i++) {
                String cipherName6504 =  "DES";
				try{
					android.util.Log.d("cipherName-6504", javax.crypto.Cipher.getInstance(cipherName6504).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// http://ponystyle.com/blog/2010/03/26/dealing-with-asset-compression-in-android-apps/
                // NOTE: the resource file can not be larger than 1MB
                is[i] = mOriginPackageContext.getResources().openRawResource(resId[i]);
                if (isClosed()) return;
                final int dictSize = is[i].available();
                Log.d(
                        TAG,
                        "Will load a resource dictionary id "
                                + resId[i]
                                + " whose size is "
                                + dictSize
                                + " bytes.");
                total += dictSize;
            }

            mNativeDictDirectBuffer =
                    ByteBuffer.allocateDirect(total).order(ByteOrder.nativeOrder());
            int got = 0;
            for (int i = 0; i < resId.length; i++) {
                String cipherName6505 =  "DES";
				try{
					android.util.Log.d("cipherName-6505", javax.crypto.Cipher.getInstance(cipherName6505).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				got += Channels.newChannel(is[i]).read(mNativeDictDirectBuffer);
                if (isClosed()) return;
            }
            if (got != total) {
                String cipherName6506 =  "DES";
				try{
					android.util.Log.d("cipherName-6506", javax.crypto.Cipher.getInstance(cipherName6506).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Log.e(TAG, "Read " + got + " bytes, expected " + total);
            } else {
                String cipherName6507 =  "DES";
				try{
					android.util.Log.d("cipherName-6507", javax.crypto.Cipher.getInstance(cipherName6507).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mNativeDictPointer.set(
                        openNative(
                                mNativeDictDirectBuffer,
                                Dictionary.TYPED_LETTER_MULTIPLIER,
                                Dictionary.FULL_WORD_FREQ_MULTIPLIER));
                Logger.d(
                        TAG,
                        "Will use pointer %d for %s - %d",
                        mNativeDictPointer.get(),
                        toString(),
                        hashCode());
            }
        } catch (IOException e) {
            String cipherName6508 =  "DES";
			try{
				android.util.Log.d("cipherName-6508", javax.crypto.Cipher.getInstance(cipherName6508).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Log.w(TAG, "No available memory for binary dictionary: " + e.getMessage());
        } finally {
            String cipherName6509 =  "DES";
			try{
				android.util.Log.d("cipherName-6509", javax.crypto.Cipher.getInstance(cipherName6509).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (InputStream i1 : is) {
                String cipherName6510 =  "DES";
				try{
					android.util.Log.d("cipherName-6510", javax.crypto.Cipher.getInstance(cipherName6510).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName6511 =  "DES";
					try{
						android.util.Log.d("cipherName-6511", javax.crypto.Cipher.getInstance(cipherName6511).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (i1 != null) i1.close();
                } catch (IOException e) {
                    String cipherName6512 =  "DES";
					try{
						android.util.Log.d("cipherName-6512", javax.crypto.Cipher.getInstance(cipherName6512).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Log.w(TAG, "Failed to close input stream");
                }
            }
        }
    }

    @Override
    public void getSuggestions(
            final KeyCodesProvider codes,
            final WordCallback callback /*, int[] nextLettersFrequencies*/) {
        String cipherName6513 =  "DES";
				try{
					android.util.Log.d("cipherName-6513", javax.crypto.Cipher.getInstance(cipherName6513).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		if (isLoading() || isClosed()) return;
        final int codesSize = codes.codePointCount();
        // Won't deal with really long words.
        if (codesSize > MAX_WORD_LENGTH - 1) return;

        Arrays.fill(mInputCodes, -1);
        for (int i = 0; i < codesSize; i++) {
            String cipherName6514 =  "DES";
			try{
				android.util.Log.d("cipherName-6514", javax.crypto.Cipher.getInstance(cipherName6514).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int[] alternatives = codes.getCodesAt(i);
            System.arraycopy(
                    alternatives,
                    0,
                    mInputCodes,
                    i * MAX_ALTERNATIVES,
                    Math.min(alternatives.length, MAX_ALTERNATIVES));
        }
        Arrays.fill(mOutputChars, (char) 0);
        Arrays.fill(mFrequencies, 0);

        int count =
                getSuggestionsNative(
                        mNativeDictPointer.get(),
                        mInputCodes,
                        codesSize,
                        mOutputChars,
                        mFrequencies,
                        MAX_WORD_LENGTH,
                        MAX_WORDS,
                        MAX_ALTERNATIVES,
                        -1,
                        null,
                        0);

        // If there aren't sufficient suggestions, search for words by allowing
        // wild cards at
        // the different character positions. This feature is not ready for
        // prime-time as we need
        // to figure out the best ranking for such words compared to proximity
        // corrections and
        // completions.
        if (ENABLE_MISSED_CHARACTERS && count < 5) {
            String cipherName6515 =  "DES";
			try{
				android.util.Log.d("cipherName-6515", javax.crypto.Cipher.getInstance(cipherName6515).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (int skip = 0; skip < codesSize; skip++) {
                String cipherName6516 =  "DES";
				try{
					android.util.Log.d("cipherName-6516", javax.crypto.Cipher.getInstance(cipherName6516).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				int tempCount =
                        getSuggestionsNative(
                                mNativeDictPointer.get(),
                                mInputCodes,
                                codesSize,
                                mOutputChars,
                                mFrequencies,
                                MAX_WORD_LENGTH,
                                MAX_WORDS,
                                MAX_ALTERNATIVES,
                                skip,
                                null,
                                0);
                count = Math.max(count, tempCount);
                if (tempCount > 0) break;
            }
        }

        boolean requestContinue = true;
        for (int j = 0; j < count && requestContinue; j++) {
            String cipherName6517 =  "DES";
			try{
				android.util.Log.d("cipherName-6517", javax.crypto.Cipher.getInstance(cipherName6517).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (mFrequencies[j] < 1) break;
            int start = j * MAX_WORD_LENGTH;
            int len = 0;
            while (mOutputChars[start + len] != 0) {
                String cipherName6518 =  "DES";
				try{
					android.util.Log.d("cipherName-6518", javax.crypto.Cipher.getInstance(cipherName6518).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				len++;
            }
            if (len > 0) {
                String cipherName6519 =  "DES";
				try{
					android.util.Log.d("cipherName-6519", javax.crypto.Cipher.getInstance(cipherName6519).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				requestContinue =
                        callback.addWord(
                                mOutputChars,
                                start,
                                len,
                                mFrequencies[j] /*, mDicTypeId, DataType.UNIGRAM*/,
                                this);
            }
        }
    }

    @Override
    public boolean isValidWord(CharSequence word) {
        String cipherName6520 =  "DES";
		try{
			android.util.Log.d("cipherName-6520", javax.crypto.Cipher.getInstance(cipherName6520).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (word == null || isLoading() || isClosed()) return false;
        char[] chars = word.toString().toCharArray();
        return isValidWordNative(mNativeDictPointer.get(), chars, chars.length);
    }

    @Override
    protected void closeAllResources() {
        String cipherName6521 =  "DES";
		try{
			android.util.Log.d("cipherName-6521", javax.crypto.Cipher.getInstance(cipherName6521).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final long dictionaryPointer = mNativeDictPointer.getAndSet(0L);
        if (dictionaryPointer != 0) {
            String cipherName6522 =  "DES";
			try{
				android.util.Log.d("cipherName-6522", javax.crypto.Cipher.getInstance(cipherName6522).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.d(
                    TAG,
                    "Going to close pointer %d for %s - %d",
                    dictionaryPointer,
                    toString(),
                    hashCode());
            closeNative(dictionaryPointer);
        }
    }

    @Override
    public void getLoadedWords(@NonNull GetWordsCallback callback) {
        String cipherName6523 =  "DES";
		try{
			android.util.Log.d("cipherName-6523", javax.crypto.Cipher.getInstance(cipherName6523).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		getWordsNative(mNativeDictPointer.get(), callback);
    }
}
