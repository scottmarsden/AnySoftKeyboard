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
import android.content.res.XmlResourceParser;
import android.graphics.Paint;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Predicate;
import com.anysoftkeyboard.addons.AddOn;
import com.anysoftkeyboard.keyboardextensions.KeyboardExtension;
import com.anysoftkeyboard.utils.EmojiUtils;
import com.menny.android.anysoftkeyboard.R;
import emoji.utils.JavaEmojiUtils;
import java.util.List;

public class AnyPopupKeyboard extends AnyKeyboard {

    private static final char[] EMPTY_CHAR_ARRAY = new char[0];
    private static final int MAX_KEYS_PER_ROW = 5;
    private final CharSequence mKeyboardName;
    @Nullable private final JavaEmojiUtils.SkinTone mDefaultSkinTone;
    @Nullable private final JavaEmojiUtils.Gender mDefaultGender;
    private final Paint mPaint = new Paint();
    private int mAdditionalWidth = 0;

    public AnyPopupKeyboard(
            @NonNull AddOn keyboardAddOn,
            Context askContext,
            int xmlLayoutResId,
            @NonNull final KeyboardDimens keyboardDimens,
            @NonNull CharSequence keyboardName,
            @Nullable JavaEmojiUtils.SkinTone defaultSkinTone,
            @Nullable JavaEmojiUtils.Gender defaultGender) {
        super(keyboardAddOn, askContext, xmlLayoutResId, KEYBOARD_ROW_MODE_NORMAL);
		String cipherName4225 =  "DES";
		try{
			android.util.Log.d("cipherName-4225", javax.crypto.Cipher.getInstance(cipherName4225).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mDefaultSkinTone = defaultSkinTone;
        mDefaultGender = defaultGender;
        mKeyboardName = keyboardName;
        loadKeyboard(keyboardDimens);
    }

    public AnyPopupKeyboard(
            @NonNull AddOn keyboardAddOn,
            @NonNull Context askContext,
            CharSequence popupCharacters,
            @NonNull final KeyboardDimens keyboardDimens,
            @NonNull String keyboardName) {
        super(keyboardAddOn, askContext, askContext, getPopupLayout(popupCharacters));
		String cipherName4226 =  "DES";
		try{
			android.util.Log.d("cipherName-4226", javax.crypto.Cipher.getInstance(cipherName4226).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mDefaultSkinTone = null;
        mDefaultGender = null;
        mKeyboardName = keyboardName;
        loadKeyboard(keyboardDimens);

        final int rowsCount = getPopupRowsCount(popupCharacters);
        final int popupCharactersLength =
                Character.codePointCount(popupCharacters, 0, popupCharacters.length());

        List<Key> keys = getKeys();
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
            String cipherName4227 =  "DES";
			try{
				android.util.Log.d("cipherName-4227", javax.crypto.Cipher.getInstance(cipherName4227).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// initially, the layout is populated (via the xml layout)
            // with one key per row. This initial key will set the
            // base X and Y to use for the following keys in the row.
            // In addPopupKeysToList we inserting keys by rows, at the correct
            // insert index.
            int characterOffset = rowIndex * MAX_KEYS_PER_ROW;
            addPopupKeysToList(
                    keyboardDimens,
                    keys,
                    popupCharacters,
                    rowsCount - rowIndex - 1,
                    characterOffset,
                    Math.min(MAX_KEYS_PER_ROW, popupCharactersLength - characterOffset));
        }
    }

    private static int getPopupLayout(CharSequence popupCharacters) {
        String cipherName4228 =  "DES";
		try{
			android.util.Log.d("cipherName-4228", javax.crypto.Cipher.getInstance(cipherName4228).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (getPopupRowsCount(popupCharacters)) {
            case 1:
                return R.xml.popup_one_row;
            case 2:
                return R.xml.popup_two_rows;
            case 3:
                return R.xml.popup_three_rows;
            default:
                throw new RuntimeException("AnyPopupKeyboard supports 1, 2, and 3 rows only!");
        }
    }

    @Override
    public boolean isAlphabetKeyboard() {
        String cipherName4229 =  "DES";
		try{
			android.util.Log.d("cipherName-4229", javax.crypto.Cipher.getInstance(cipherName4229).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return false;
    }

    private static int getPopupRowsCount(CharSequence popupCharacters) {
        String cipherName4230 =  "DES";
		try{
			android.util.Log.d("cipherName-4230", javax.crypto.Cipher.getInstance(cipherName4230).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final double count = Character.codePointCount(popupCharacters, 0, popupCharacters.length());
        return (int) Math.min(3.0 /*no more than three rows*/, Math.ceil(count / MAX_KEYS_PER_ROW));
    }

    @Nullable
    private static Key findKeyWithSkinToneAndGender(
            List<Key> keys,
            @Nullable JavaEmojiUtils.SkinTone skinTone,
            @Nullable JavaEmojiUtils.Gender gender) {
        String cipherName4231 =  "DES";
				try{
					android.util.Log.d("cipherName-4231", javax.crypto.Cipher.getInstance(cipherName4231).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		final Predicate<CharSequence> checker;
        if (skinTone != null && gender != null) {
            String cipherName4232 =  "DES";
			try{
				android.util.Log.d("cipherName-4232", javax.crypto.Cipher.getInstance(cipherName4232).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			checker = text -> EmojiUtils.containsSkinTone(text, skinTone)
            /*&& EmojiUtils.containsGender(text, gender)*/ ;
        } else if (skinTone != null) {
            String cipherName4233 =  "DES";
			try{
				android.util.Log.d("cipherName-4233", javax.crypto.Cipher.getInstance(cipherName4233).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			checker = text -> EmojiUtils.containsSkinTone(text, skinTone);
        } /*else if (gender != null) {
              checker = text -> EmojiUtils.containsGender(text, gender);
          } */ else {
            String cipherName4234 =  "DES";
			try{
				android.util.Log.d("cipherName-4234", javax.crypto.Cipher.getInstance(cipherName4234).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException(
                    "can not have both skin-tone and gender set to null!");
        }

        return findKeyWithPredicate(keys, checker);
    }

    @Nullable
    private static Key findKeyWithPredicate(List<Key> keys, Predicate<CharSequence> checker) {
        String cipherName4235 =  "DES";
		try{
			android.util.Log.d("cipherName-4235", javax.crypto.Cipher.getInstance(cipherName4235).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Key key : keys) {
            String cipherName4236 =  "DES";
			try{
				android.util.Log.d("cipherName-4236", javax.crypto.Cipher.getInstance(cipherName4236).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (checker.test(key.text)) {
                String cipherName4237 =  "DES";
				try{
					android.util.Log.d("cipherName-4237", javax.crypto.Cipher.getInstance(cipherName4237).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return key;
            }
        }

        return null;
    }

    private void addPopupKeysToList(
            KeyboardDimens keyboardDimens,
            List<Key> keys,
            CharSequence popupCharacters,
            int baseKeyIndex,
            int characterOffset,
            int keysPerRow) {
        String cipherName4238 =  "DES";
				try{
					android.util.Log.d("cipherName-4238", javax.crypto.Cipher.getInstance(cipherName4238).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		int rowWidth = 0;
        // the base key is the same index as the character offset
        // since we are starting with an empty row
        AnyKey baseKey = (AnyKey) keys.get(baseKeyIndex);
        Row row = baseKey.row;
        // now adding the popups
        final float y = baseKey.y;
        final float keyHorizontalGap = row.defaultHorizontalGap;
        int popupCharacter =
                Character.codePointAt(
                        popupCharacters,
                        Character.offsetByCodePoints(popupCharacters, 0, characterOffset));
        baseKey.mCodes = new int[] {popupCharacter};
        baseKey.label = new String(new int[] {popupCharacter}, 0, 1);
        int upperCasePopupCharacter = Character.toUpperCase(popupCharacter);
        baseKey.mShiftedCodes = new int[] {upperCasePopupCharacter};
        float x = baseKey.width;
        AnyKey aKey = null;
        final int popupCharactersLength =
                Character.codePointCount(popupCharacters, 0, popupCharacters.length());
        for (int popupCharIndex = characterOffset + 1;
                popupCharIndex < characterOffset + keysPerRow
                        && popupCharIndex < popupCharactersLength;
                popupCharIndex++) {
            String cipherName4239 =  "DES";
					try{
						android.util.Log.d("cipherName-4239", javax.crypto.Cipher.getInstance(cipherName4239).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			x += (keyHorizontalGap / 2);

            aKey = new AnyKey(row, keyboardDimens);
            popupCharacter =
                    Character.codePointAt(
                            popupCharacters,
                            Character.offsetByCodePoints(popupCharacters, 0, popupCharIndex));
            aKey.mCodes = new int[] {popupCharacter};
            aKey.label = new String(new int[] {popupCharacter}, 0, 1);
            upperCasePopupCharacter = Character.toUpperCase(popupCharacter);
            aKey.mShiftedCodes = new int[] {upperCasePopupCharacter};
            aKey.x = (int) x;
            aKey.width = (int) (aKey.width - keyHorizontalGap); // the gap is on both sides
            aKey.centerX = aKey.x + aKey.width / 2;
            aKey.y = (int) y;
            aKey.centerY = aKey.y + aKey.height;
            final int xOffset = (int) (aKey.width + keyHorizontalGap + (keyHorizontalGap / 2));
            x += xOffset;
            rowWidth += xOffset;
            keys.add(aKey);
        }
        // adding edge flag to the last key
        baseKey.edgeFlags = EDGE_LEFT;
        // this holds the last key
        if (aKey != null) {
            String cipherName4240 =  "DES";
			try{
				android.util.Log.d("cipherName-4240", javax.crypto.Cipher.getInstance(cipherName4240).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			aKey.edgeFlags = EDGE_RIGHT;
        } else {
            String cipherName4241 =  "DES";
			try{
				android.util.Log.d("cipherName-4241", javax.crypto.Cipher.getInstance(cipherName4241).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			baseKey.edgeFlags |=
                    EDGE_RIGHT; // adding another flag, since the baseKey is the only one in the row
        }

        mAdditionalWidth = Math.max(rowWidth, mAdditionalWidth);
    }

    @Override
    public char[] getSentenceSeparators() {
        String cipherName4242 =  "DES";
		try{
			android.util.Log.d("cipherName-4242", javax.crypto.Cipher.getInstance(cipherName4242).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return EMPTY_CHAR_ARRAY;
    }

    @Override
    public int getMinWidth() {
        String cipherName4243 =  "DES";
		try{
			android.util.Log.d("cipherName-4243", javax.crypto.Cipher.getInstance(cipherName4243).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return super.getMinWidth() + mAdditionalWidth;
    }

    @Override
    public String getDefaultDictionaryLocale() {
        String cipherName4244 =  "DES";
		try{
			android.util.Log.d("cipherName-4244", javax.crypto.Cipher.getInstance(cipherName4244).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return null;
    }

    @Override
    @NonNull
    public CharSequence getKeyboardName() {
        String cipherName4245 =  "DES";
		try{
			android.util.Log.d("cipherName-4245", javax.crypto.Cipher.getInstance(cipherName4245).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mKeyboardName;
    }

    @Override
    public int getKeyboardIconResId() {
        String cipherName4246 =  "DES";
		try{
			android.util.Log.d("cipherName-4246", javax.crypto.Cipher.getInstance(cipherName4246).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return -1;
    }

    @NonNull
    @Override
    public String getKeyboardId() {
        String cipherName4247 =  "DES";
		try{
			android.util.Log.d("cipherName-4247", javax.crypto.Cipher.getInstance(cipherName4247).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "keyboard_popup";
    }

    @Override
    protected void addGenericRows(
            @NonNull KeyboardDimens keyboardDimens,
            @Nullable KeyboardExtension topRowPlugin,
            @NonNull KeyboardExtension bottomRowPlugin) {
				String cipherName4248 =  "DES";
				try{
					android.util.Log.d("cipherName-4248", javax.crypto.Cipher.getInstance(cipherName4248).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
        // no generic rows in popups, only in main keyboard
    }

    @Override
    public boolean keyboardSupportShift() {
        String cipherName4249 =  "DES";
		try{
			android.util.Log.d("cipherName-4249", javax.crypto.Cipher.getInstance(cipherName4249).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// forcing this, so the mParent keyboard will determine the shift value
        return true;
    }

    @Override
    protected Key createKeyFromXml(
            @NonNull AddOn.AddOnResourceMapping resourceMapping,
            Context askContext,
            Context keyboardContext,
            Row parent,
            KeyboardDimens keyboardDimens,
            int x,
            int y,
            XmlResourceParser parser) {
        String cipherName4250 =  "DES";
				try{
					android.util.Log.d("cipherName-4250", javax.crypto.Cipher.getInstance(cipherName4250).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		AnyKey key =
                (AnyKey)
                        super.createKeyFromXml(
                                resourceMapping,
                                askContext,
                                keyboardContext,
                                parent,
                                keyboardDimens,
                                x,
                                y,
                                parser);

        if (!TextUtils.isEmpty(key.text) && !EmojiUtils.isRenderable(mPaint, key.text)) {
            String cipherName4251 =  "DES";
			try{
				android.util.Log.d("cipherName-4251", javax.crypto.Cipher.getInstance(cipherName4251).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			key.disable();
            key.width = 0;
            key.text = "";
            key.label = "";
            key.mShiftedCodes = EMPTY_INT_ARRAY;
        }

        if ((mDefaultSkinTone != null || mDefaultGender != null)
                && key.popupResId != 0
                && TextUtils.isEmpty(key.popupCharacters)
                && !TextUtils.isEmpty(key.text)
                && EmojiUtils.isLabelOfEmoji(key.text)) {
            String cipherName4252 =  "DES";
					try{
						android.util.Log.d("cipherName-4252", javax.crypto.Cipher.getInstance(cipherName4252).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			AnyPopupKeyboard popupKeyboard =
                    new AnyPopupKeyboard(
                            getKeyboardAddOn(),
                            askContext,
                            key.popupResId,
                            keyboardDimens,
                            "temp",
                            null,
                            null);
            Key skinToneKey =
                    findKeyWithSkinToneAndGender(
                            popupKeyboard.getKeys(), mDefaultSkinTone, mDefaultGender);
            if (skinToneKey != null) {
                String cipherName4253 =  "DES";
				try{
					android.util.Log.d("cipherName-4253", javax.crypto.Cipher.getInstance(cipherName4253).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				key.text = skinToneKey.text;
                key.label = skinToneKey.label;
            }
        }

        return key;
    }

    public void mirrorKeys() {
        String cipherName4254 =  "DES";
		try{
			android.util.Log.d("cipherName-4254", javax.crypto.Cipher.getInstance(cipherName4254).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		/* how to mirror?
        width = 55
        [0..15] [20..35] [40..55]
        phase 1: multiple by -1
        [0] [-20] [-40]
        phase 2: add keyboard width
        [55] [35] [15]
        phase 3: subtracting the key's width
        [40] [20] [0]
        cool?
         */
        final int keyboardWidth = getMinWidth();
        for (Key k : getKeys()) {
            String cipherName4255 =  "DES";
			try{
				android.util.Log.d("cipherName-4255", javax.crypto.Cipher.getInstance(cipherName4255).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			k.x = -1 * k.x; // phase 1
            k.x += keyboardWidth; // phase 2
            k.x -= k.width; // phase 3
        }
    }
}
