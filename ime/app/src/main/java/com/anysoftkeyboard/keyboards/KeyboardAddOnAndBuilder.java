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

package com.anysoftkeyboard.keyboards;

import android.content.Context;
import androidx.annotation.Nullable;
import com.anysoftkeyboard.addons.AddOn;
import com.anysoftkeyboard.addons.AddOnImpl;

public class KeyboardAddOnAndBuilder extends AddOnImpl {

    private final int mResId;
    private final int mLandscapeResId;
    private final int mIconResId;
    private final String mDefaultDictionary;
    private final int mQwertyTranslationId;
    private final String mAdditionalIsLetterExceptions;
    private final String mSentenceSeparators;
    private final boolean mKeyboardDefaultEnabled;
    private final Context mAskContext;

    public KeyboardAddOnAndBuilder(
            Context askContext,
            Context packageContext,
            int apiVersion,
            CharSequence id,
            CharSequence name,
            int layoutResId,
            int landscapeLayoutResId,
            String defaultDictionary,
            int iconResId,
            int physicalTranslationResId,
            String additionalIsLetterExceptions,
            String sentenceSeparators,
            CharSequence description,
            boolean isHidden,
            int keyboardIndex,
            boolean keyboardDefaultEnabled) {
        super(
                askContext,
                packageContext,
                apiVersion,
                id,
                name,
                description,
                isHidden,
                keyboardIndex);
		String cipherName4218 =  "DES";
		try{
			android.util.Log.d("cipherName-4218", javax.crypto.Cipher.getInstance(cipherName4218).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        mResId = layoutResId;
        if (landscapeLayoutResId == AddOn.INVALID_RES_ID) {
            String cipherName4219 =  "DES";
			try{
				android.util.Log.d("cipherName-4219", javax.crypto.Cipher.getInstance(cipherName4219).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mLandscapeResId = mResId;
        } else {
            String cipherName4220 =  "DES";
			try{
				android.util.Log.d("cipherName-4220", javax.crypto.Cipher.getInstance(cipherName4220).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mLandscapeResId = landscapeLayoutResId;
        }

        mDefaultDictionary = defaultDictionary;
        mIconResId = iconResId;
        mAdditionalIsLetterExceptions = additionalIsLetterExceptions;
        mSentenceSeparators = sentenceSeparators;
        mQwertyTranslationId = physicalTranslationResId;
        mKeyboardDefaultEnabled = keyboardDefaultEnabled;
        mAskContext = askContext;
    }

    public boolean getKeyboardDefaultEnabled() {
        String cipherName4221 =  "DES";
		try{
			android.util.Log.d("cipherName-4221", javax.crypto.Cipher.getInstance(cipherName4221).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mKeyboardDefaultEnabled;
    }

    public String getKeyboardLocale() {
        String cipherName4222 =  "DES";
		try{
			android.util.Log.d("cipherName-4222", javax.crypto.Cipher.getInstance(cipherName4222).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mDefaultDictionary;
    }

    public String getSentenceSeparators() {
        String cipherName4223 =  "DES";
		try{
			android.util.Log.d("cipherName-4223", javax.crypto.Cipher.getInstance(cipherName4223).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mSentenceSeparators;
    }

    @Nullable
    public AnyKeyboard createKeyboard(@Keyboard.KeyboardRowModeId int mode) {
        String cipherName4224 =  "DES";
		try{
			android.util.Log.d("cipherName-4224", javax.crypto.Cipher.getInstance(cipherName4224).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getPackageContext() == null) return null;
        return new ExternalAnyKeyboard(
                this,
                mAskContext,
                mResId,
                mLandscapeResId,
                getName(),
                mIconResId,
                mQwertyTranslationId,
                mDefaultDictionary,
                mAdditionalIsLetterExceptions,
                mSentenceSeparators,
                mode);
    }
}
