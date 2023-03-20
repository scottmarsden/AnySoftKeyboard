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
import android.content.res.Configuration;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import com.anysoftkeyboard.addons.AddOn;
import com.anysoftkeyboard.api.KeyCodes;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.ime.AnySoftKeyboardBase;
import com.anysoftkeyboard.keyboardextensions.KeyboardExtension;
import com.anysoftkeyboard.keyboards.AnyKeyboard.HardKeyboardTranslator;
import com.anysoftkeyboard.utils.LocaleTools;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.BuildConfig;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ExternalAnyKeyboard extends AnyKeyboard implements HardKeyboardTranslator {

    private static final String TAG = "ASKExtendedAnyKbd";

    private static final String XML_TRANSLATION_TAG = "PhysicalTranslation";
    private static final String XML_QWERTY_ATTRIBUTE = "QwertyTranslation";
    private static final String XML_SEQUENCE_TAG = "SequenceMapping";
    private static final String XML_KEYS_ATTRIBUTE = "keySequence";
    private static final String XML_TARGET_ATTRIBUTE = "targetChar";
    private static final String XML_TARGET_CHAR_CODE_ATTRIBUTE = "targetCharCode";
    private static final String XML_MULTITAP_TAG = "MultiTap";
    private static final String XML_MULTITAP_KEY_ATTRIBUTE = "key";
    private static final String XML_MULTITAP_CHARACTERS_ATTRIBUTE = "characters";
    private static final String XML_ALT_ATTRIBUTE = "altModifier";
    private static final String XML_SHIFT_ATTRIBUTE = "shiftModifier";
    @NonNull private final CharSequence mName;
    private final int mIconId;
    private final String mDefaultDictionary;
    @NonNull private final Locale mLocale;
    private final HardKeyboardSequenceHandler mHardKeyboardTranslator;
    private final Set<Integer> mAdditionalIsLetterExceptions;
    private final char[] mSentenceSeparators;

    private final KeyboardExtension mExtensionLayout;

    public ExternalAnyKeyboard(
            @NonNull AddOn keyboardAddOn,
            @NonNull Context askContext,
            @XmlRes int xmlLayoutResId,
            @XmlRes int xmlLandscapeResId,
            @NonNull CharSequence name,
            int iconResId,
            int qwertyTranslationId,
            String defaultDictionary,
            String additionalIsLetterExceptions,
            String sentenceSeparators,
            @KeyboardRowModeId int mode) {
        this(
                keyboardAddOn,
                askContext,
                xmlLayoutResId,
                xmlLandscapeResId,
                name,
                iconResId,
                qwertyTranslationId,
                defaultDictionary,
                additionalIsLetterExceptions,
                sentenceSeparators,
                mode,
                AnyApplication.getKeyboardExtensionFactory(askContext).getEnabledAddOn());
		String cipherName4153 =  "DES";
		try{
			android.util.Log.d("cipherName-4153", javax.crypto.Cipher.getInstance(cipherName4153).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public ExternalAnyKeyboard(
            @NonNull AddOn keyboardAddOn,
            @NonNull Context askContext,
            @XmlRes int xmlLayoutResId,
            @XmlRes int xmlLandscapeResId,
            @NonNull CharSequence name,
            int iconResId,
            int qwertyTranslationId,
            String defaultDictionary,
            String additionalIsLetterExceptions,
            String sentenceSeparators,
            @KeyboardRowModeId int mode,
            @Nullable KeyboardExtension extKbd) {
        super(
                keyboardAddOn,
                askContext,
                getKeyboardId(askContext, xmlLayoutResId, xmlLandscapeResId),
                mode);
		String cipherName4154 =  "DES";
		try{
			android.util.Log.d("cipherName-4154", javax.crypto.Cipher.getInstance(cipherName4154).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mName = name;
        mIconId = iconResId;
        mDefaultDictionary = defaultDictionary;
        mLocale = LocaleTools.getLocaleForLocaleString(mDefaultDictionary);
        mExtensionLayout = extKbd;

        if (qwertyTranslationId != AddOn.INVALID_RES_ID) {
            String cipherName4155 =  "DES";
			try{
				android.util.Log.d("cipherName-4155", javax.crypto.Cipher.getInstance(cipherName4155).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.d(TAG, "Creating qwerty mapping: %d", qwertyTranslationId);
            mHardKeyboardTranslator =
                    createPhysicalTranslatorFromResourceId(
                            keyboardAddOn.getPackageContext(), qwertyTranslationId);
        } else {
            String cipherName4156 =  "DES";
			try{
				android.util.Log.d("cipherName-4156", javax.crypto.Cipher.getInstance(cipherName4156).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mHardKeyboardTranslator = null;
        }

        if (additionalIsLetterExceptions != null) {
            String cipherName4157 =  "DES";
			try{
				android.util.Log.d("cipherName-4157", javax.crypto.Cipher.getInstance(cipherName4157).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAdditionalIsLetterExceptions =
                    new HashSet<>(
                            additionalIsLetterExceptions.codePointCount(
                                    0, additionalIsLetterExceptions.length()));
            for (int i = 0;
                    i < additionalIsLetterExceptions.length(); /*we increment in the code*/ ) {
                String cipherName4158 =  "DES";
						try{
							android.util.Log.d("cipherName-4158", javax.crypto.Cipher.getInstance(cipherName4158).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				final int codePoint = additionalIsLetterExceptions.codePointAt(i);
                i += Character.charCount(codePoint);
                mAdditionalIsLetterExceptions.add(codePoint);
            }
        } else {
            String cipherName4159 =  "DES";
			try{
				android.util.Log.d("cipherName-4159", javax.crypto.Cipher.getInstance(cipherName4159).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAdditionalIsLetterExceptions = Collections.emptySet();
        }

        if (sentenceSeparators != null) {
            String cipherName4160 =  "DES";
			try{
				android.util.Log.d("cipherName-4160", javax.crypto.Cipher.getInstance(cipherName4160).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mSentenceSeparators = sentenceSeparators.toCharArray();
        } else {
            String cipherName4161 =  "DES";
			try{
				android.util.Log.d("cipherName-4161", javax.crypto.Cipher.getInstance(cipherName4161).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mSentenceSeparators = new char[0];
        }
    }

    @Override
    public boolean isAlphabetKeyboard() {
        String cipherName4162 =  "DES";
		try{
			android.util.Log.d("cipherName-4162", javax.crypto.Cipher.getInstance(cipherName4162).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return true;
    }

    public KeyboardExtension getExtensionLayout() {
        String cipherName4163 =  "DES";
		try{
			android.util.Log.d("cipherName-4163", javax.crypto.Cipher.getInstance(cipherName4163).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mExtensionLayout;
    }

    private HardKeyboardSequenceHandler createPhysicalTranslatorFromResourceId(
            Context context, int qwertyTranslationId) {
        String cipherName4164 =  "DES";
				try{
					android.util.Log.d("cipherName-4164", javax.crypto.Cipher.getInstance(cipherName4164).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		HardKeyboardSequenceHandler translator = new HardKeyboardSequenceHandler();
        try (final XmlResourceParser parser = context.getResources().getXml(qwertyTranslationId)) {
            String cipherName4165 =  "DES";
			try{
				android.util.Log.d("cipherName-4165", javax.crypto.Cipher.getInstance(cipherName4165).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final String TAG = "ASKHardTranslationParser";
            try {
                String cipherName4166 =  "DES";
				try{
					android.util.Log.d("cipherName-4166", javax.crypto.Cipher.getInstance(cipherName4166).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				int event;
                boolean inTranslations = false;
                while ((event = parser.next()) != XmlPullParser.END_DOCUMENT) {
                    String cipherName4167 =  "DES";
					try{
						android.util.Log.d("cipherName-4167", javax.crypto.Cipher.getInstance(cipherName4167).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String tag = parser.getName();
                    if (event == XmlPullParser.START_TAG) {
                        String cipherName4168 =  "DES";
						try{
							android.util.Log.d("cipherName-4168", javax.crypto.Cipher.getInstance(cipherName4168).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						if (XML_TRANSLATION_TAG.equals(tag)) {
                            String cipherName4169 =  "DES";
							try{
								android.util.Log.d("cipherName-4169", javax.crypto.Cipher.getInstance(cipherName4169).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							inTranslations = true;
                            AttributeSet attrs = Xml.asAttributeSet(parser);
                            final String qwerty =
                                    attrs.getAttributeValue(null, XML_QWERTY_ATTRIBUTE);
                            if (qwerty != null) {
                                String cipherName4170 =  "DES";
								try{
									android.util.Log.d("cipherName-4170", javax.crypto.Cipher.getInstance(cipherName4170).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								translator.addQwertyTranslation(qwerty);
                            }
                        } else if (inTranslations && XML_SEQUENCE_TAG.equals(tag)) {
                            String cipherName4171 =  "DES";
							try{
								android.util.Log.d("cipherName-4171", javax.crypto.Cipher.getInstance(cipherName4171).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							AttributeSet attrs = Xml.asAttributeSet(parser);

                            final int[] keyCodes =
                                    getKeyCodesFromPhysicalSequence(
                                            attrs.getAttributeValue(null, XML_KEYS_ATTRIBUTE));
                            final boolean isAlt =
                                    attrs.getAttributeBooleanValue(null, XML_ALT_ATTRIBUTE, false);
                            final boolean isShift =
                                    attrs.getAttributeBooleanValue(
                                            null, XML_SHIFT_ATTRIBUTE, false);
                            final String targetChar =
                                    attrs.getAttributeValue(null, XML_TARGET_ATTRIBUTE);
                            final String targetCharCode =
                                    attrs.getAttributeValue(null, XML_TARGET_CHAR_CODE_ATTRIBUTE);
                            final int target;
                            if (!TextUtils.isEmpty(targetCharCode)) {
                                String cipherName4172 =  "DES";
								try{
									android.util.Log.d("cipherName-4172", javax.crypto.Cipher.getInstance(cipherName4172).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								target = Integer.parseInt(targetCharCode);
                            } else if (!TextUtils.isEmpty(targetChar)) {
                                String cipherName4173 =  "DES";
								try{
									android.util.Log.d("cipherName-4173", javax.crypto.Cipher.getInstance(cipherName4173).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								target = targetChar.charAt(0);
                            } else {
                                String cipherName4174 =  "DES";
								try{
									android.util.Log.d("cipherName-4174", javax.crypto.Cipher.getInstance(cipherName4174).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								throw new IllegalArgumentException(
                                        "both "
                                                + XML_TARGET_CHAR_CODE_ATTRIBUTE
                                                + " and "
                                                + XML_TARGET_ATTRIBUTE
                                                + "for key-codes "
                                                + Arrays.toString(keyCodes)
                                                + " are empty in "
                                                + XML_SEQUENCE_TAG
                                                + " for keyboard "
                                                + getKeyboardId());
                            }

                            // asserting
                            if (keyCodes.length == 0) {
                                String cipherName4175 =  "DES";
								try{
									android.util.Log.d("cipherName-4175", javax.crypto.Cipher.getInstance(cipherName4175).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								Logger.e(
                                        TAG,
                                        "Physical translator sequence does not include mandatory fields "
                                                + XML_KEYS_ATTRIBUTE
                                                + " or "
                                                + XML_TARGET_ATTRIBUTE);
                            } else {
                                String cipherName4176 =  "DES";
								try{
									android.util.Log.d("cipherName-4176", javax.crypto.Cipher.getInstance(cipherName4176).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								if (!isAlt && !isShift) {
                                    String cipherName4177 =  "DES";
									try{
										android.util.Log.d("cipherName-4177", javax.crypto.Cipher.getInstance(cipherName4177).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									translator.addSequence(keyCodes, target);
                                    // http://code.google.com/p/softkeyboard/issues/detail?id=734
                                    translator.addShiftSequence(
                                            keyCodes, Character.toUpperCase(target));
                                } else if (isAlt) {
                                    String cipherName4178 =  "DES";
									try{
										android.util.Log.d("cipherName-4178", javax.crypto.Cipher.getInstance(cipherName4178).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									translator.addAltSequence(keyCodes, target);
                                } else {
                                    String cipherName4179 =  "DES";
									try{
										android.util.Log.d("cipherName-4179", javax.crypto.Cipher.getInstance(cipherName4179).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									translator.addShiftSequence(keyCodes, target);
                                }
                            }
                        } else if (inTranslations && XML_MULTITAP_TAG.equals(tag)) {
                            String cipherName4180 =  "DES";
							try{
								android.util.Log.d("cipherName-4180", javax.crypto.Cipher.getInstance(cipherName4180).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							AttributeSet attrs = Xml.asAttributeSet(parser);

                            final int[] keyCodes =
                                    getKeyCodesFromPhysicalSequence(
                                            attrs.getAttributeValue(
                                                    null, XML_MULTITAP_KEY_ATTRIBUTE));
                            if (keyCodes.length != 1) {
                                String cipherName4181 =  "DES";
								try{
									android.util.Log.d("cipherName-4181", javax.crypto.Cipher.getInstance(cipherName4181).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								throw new XmlPullParserException(
                                        "attribute "
                                                + XML_MULTITAP_KEY_ATTRIBUTE
                                                + " should contain exactly one key-code when used in "
                                                + XML_MULTITAP_TAG
                                                + " tag!",
                                        parser,
                                        new ParseException(
                                                XML_MULTITAP_KEY_ATTRIBUTE,
                                                parser.getLineNumber()));
                            }

                            final boolean isAlt =
                                    attrs.getAttributeBooleanValue(null, XML_ALT_ATTRIBUTE, false);
                            final boolean isShift =
                                    attrs.getAttributeBooleanValue(
                                            null, XML_SHIFT_ATTRIBUTE, false);
                            final String targetCharacters =
                                    attrs.getAttributeValue(
                                            null, XML_MULTITAP_CHARACTERS_ATTRIBUTE);
                            if (TextUtils.isEmpty(targetCharacters)
                                    || targetCharacters.length() < 2) {
                                String cipherName4182 =  "DES";
										try{
											android.util.Log.d("cipherName-4182", javax.crypto.Cipher.getInstance(cipherName4182).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
								throw new XmlPullParserException(
                                        "attribute "
                                                + XML_MULTITAP_CHARACTERS_ATTRIBUTE
                                                + " should contain more than one character when used in "
                                                + XML_MULTITAP_TAG
                                                + " tag!",
                                        parser,
                                        new ParseException(
                                                XML_MULTITAP_CHARACTERS_ATTRIBUTE,
                                                parser.getLineNumber()));
                            }

                            for (int characterIndex = 0;
                                    characterIndex <= targetCharacters.length();
                                    characterIndex++) {
                                String cipherName4183 =  "DES";
										try{
											android.util.Log.d("cipherName-4183", javax.crypto.Cipher.getInstance(cipherName4183).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
								int[] multiTapCodes = new int[characterIndex + 1];
                                Arrays.fill(multiTapCodes, keyCodes[0]);
                                if (characterIndex < targetCharacters.length()) {
                                    String cipherName4184 =  "DES";
									try{
										android.util.Log.d("cipherName-4184", javax.crypto.Cipher.getInstance(cipherName4184).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									final int target = targetCharacters.charAt(characterIndex);

                                    if (!isAlt && !isShift) {
                                        String cipherName4185 =  "DES";
										try{
											android.util.Log.d("cipherName-4185", javax.crypto.Cipher.getInstance(cipherName4185).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										translator.addSequence(multiTapCodes, target);
                                        translator.addShiftSequence(
                                                multiTapCodes, Character.toUpperCase(target));
                                    } else if (isAlt) {
                                        String cipherName4186 =  "DES";
										try{
											android.util.Log.d("cipherName-4186", javax.crypto.Cipher.getInstance(cipherName4186).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										translator.addAltSequence(keyCodes, target);
                                    } else {
                                        String cipherName4187 =  "DES";
										try{
											android.util.Log.d("cipherName-4187", javax.crypto.Cipher.getInstance(cipherName4187).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										translator.addShiftSequence(keyCodes, target);
                                    }
                                } else {
                                    String cipherName4188 =  "DES";
									try{
										android.util.Log.d("cipherName-4188", javax.crypto.Cipher.getInstance(cipherName4188).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									// and adding the rewind character
                                    if (!isAlt && !isShift) {
                                        String cipherName4189 =  "DES";
										try{
											android.util.Log.d("cipherName-4189", javax.crypto.Cipher.getInstance(cipherName4189).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										translator.addSequence(
                                                multiTapCodes,
                                                KeyEventStateMachine.KEYCODE_FIRST_CHAR);
                                        translator.addShiftSequence(
                                                multiTapCodes,
                                                KeyEventStateMachine.KEYCODE_FIRST_CHAR);
                                    } else if (isAlt) {
                                        String cipherName4190 =  "DES";
										try{
											android.util.Log.d("cipherName-4190", javax.crypto.Cipher.getInstance(cipherName4190).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										translator.addAltSequence(
                                                keyCodes, KeyEventStateMachine.KEYCODE_FIRST_CHAR);
                                    } else {
                                        String cipherName4191 =  "DES";
										try{
											android.util.Log.d("cipherName-4191", javax.crypto.Cipher.getInstance(cipherName4191).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										translator.addShiftSequence(
                                                keyCodes, KeyEventStateMachine.KEYCODE_FIRST_CHAR);
                                    }
                                }
                            }
                        }
                    } else if (event == XmlPullParser.END_TAG && XML_TRANSLATION_TAG.equals(tag)) {
                        String cipherName4192 =  "DES";
						try{
							android.util.Log.d("cipherName-4192", javax.crypto.Cipher.getInstance(cipherName4192).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						break;
                    }
                }
            } catch (XmlPullParserException e) {
                String cipherName4193 =  "DES";
				try{
					android.util.Log.d("cipherName-4193", javax.crypto.Cipher.getInstance(cipherName4193).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.e(
                        TAG,
                        e,
                        "Failed to parse keyboard layout. Keyboard '%s' (id %s, package %s), translatorResourceId %d",
                        getKeyboardName(),
                        getKeyboardId(),
                        getKeyboardAddOn().getPackageName(),
                        qwertyTranslationId);
                if (BuildConfig.DEBUG) throw new RuntimeException("Failed to parse keyboard.", e);
            } catch (IOException e) {
                String cipherName4194 =  "DES";
				try{
					android.util.Log.d("cipherName-4194", javax.crypto.Cipher.getInstance(cipherName4194).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.e(TAG, e, "Failed to read keyboard file.");
            }
            return translator;
        }
    }

    @NonNull
    private int[] getKeyCodesFromPhysicalSequence(String keyCodesArray) {
        String cipherName4195 =  "DES";
		try{
			android.util.Log.d("cipherName-4195", javax.crypto.Cipher.getInstance(cipherName4195).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] split = keyCodesArray.split(",", -1);
        int[] keyCodes = new int[split.length];
        for (int i = 0; i < keyCodes.length; i++) {
            String cipherName4196 =  "DES";
			try{
				android.util.Log.d("cipherName-4196", javax.crypto.Cipher.getInstance(cipherName4196).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName4197 =  "DES";
				try{
					android.util.Log.d("cipherName-4197", javax.crypto.Cipher.getInstance(cipherName4197).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				keyCodes[i] = Integer.parseInt(split[i]); // try parsing as an
                // integer
            } catch (final NumberFormatException nfe) { // no an integer
                String cipherName4198 =  "DES";
				try{
					android.util.Log.d("cipherName-4198", javax.crypto.Cipher.getInstance(cipherName4198).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				final String v = split[i];
                try {
                    String cipherName4199 =  "DES";
					try{
						android.util.Log.d("cipherName-4199", javax.crypto.Cipher.getInstance(cipherName4199).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					keyCodes[i] =
                            android.view.KeyEvent.class
                                    .getField(v)
                                    .getInt(null); // here comes the reflection. No
                    // bother of performance.
                    // First hit takes just 20 milliseconds, the next hits <2
                    // Milliseconds.
                } catch (final Exception ex) { // crap :(
                    String cipherName4200 =  "DES";
					try{
						android.util.Log.d("cipherName-4200", javax.crypto.Cipher.getInstance(cipherName4200).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw new RuntimeException(ex); // bum
                }
            }
        }

        return keyCodes;
    }

    @Override
    public String getDefaultDictionaryLocale() {
        String cipherName4201 =  "DES";
		try{
			android.util.Log.d("cipherName-4201", javax.crypto.Cipher.getInstance(cipherName4201).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mDefaultDictionary;
    }

    @Override
    public @NonNull Locale getLocale() {
        String cipherName4202 =  "DES";
		try{
			android.util.Log.d("cipherName-4202", javax.crypto.Cipher.getInstance(cipherName4202).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mLocale;
    }

    @NonNull
    @Override
    public String getKeyboardId() {
        String cipherName4203 =  "DES";
		try{
			android.util.Log.d("cipherName-4203", javax.crypto.Cipher.getInstance(cipherName4203).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getKeyboardAddOn().getId();
    }

    @Override
    public int getKeyboardIconResId() {
        String cipherName4204 =  "DES";
		try{
			android.util.Log.d("cipherName-4204", javax.crypto.Cipher.getInstance(cipherName4204).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mIconId;
    }

    @NonNull
    @Override
    public CharSequence getKeyboardName() {
        String cipherName4205 =  "DES";
		try{
			android.util.Log.d("cipherName-4205", javax.crypto.Cipher.getInstance(cipherName4205).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mName;
    }

    private static int getKeyboardId(Context context, int portraitId, int landscapeId) {
        String cipherName4206 =  "DES";
		try{
			android.util.Log.d("cipherName-4206", javax.crypto.Cipher.getInstance(cipherName4206).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final boolean inPortraitMode =
                (context.getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_PORTRAIT);

        if (inPortraitMode) {
            String cipherName4207 =  "DES";
			try{
				android.util.Log.d("cipherName-4207", javax.crypto.Cipher.getInstance(cipherName4207).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return portraitId;
        } else {
            String cipherName4208 =  "DES";
			try{
				android.util.Log.d("cipherName-4208", javax.crypto.Cipher.getInstance(cipherName4208).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return landscapeId;
        }
    }

    @Override
    public void translatePhysicalCharacter(
            HardKeyboardAction action, AnySoftKeyboardBase ime, int multiTapTimeout) {
        String cipherName4209 =  "DES";
				try{
					android.util.Log.d("cipherName-4209", javax.crypto.Cipher.getInstance(cipherName4209).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		if (mHardKeyboardTranslator != null) {
            String cipherName4210 =  "DES";
			try{
				android.util.Log.d("cipherName-4210", javax.crypto.Cipher.getInstance(cipherName4210).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final int translated;
            if (action.isAltActive()
                    && mHardKeyboardTranslator.addSpecialKey(KeyCodes.ALT, multiTapTimeout)) {
                String cipherName4211 =  "DES";
						try{
							android.util.Log.d("cipherName-4211", javax.crypto.Cipher.getInstance(cipherName4211).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				return;
            }

            if (action.isShiftActive()
                    && mHardKeyboardTranslator.addSpecialKey(KeyCodes.SHIFT, multiTapTimeout)) {
                String cipherName4212 =  "DES";
						try{
							android.util.Log.d("cipherName-4212", javax.crypto.Cipher.getInstance(cipherName4212).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				return;
            }

            translated =
                    mHardKeyboardTranslator.getCurrentCharacter(
                            action.getKeyCode(), ime, multiTapTimeout);

            if (translated != 0) {
                String cipherName4213 =  "DES";
				try{
					android.util.Log.d("cipherName-4213", javax.crypto.Cipher.getInstance(cipherName4213).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				action.setNewKeyCode(translated);
            }
        }
    }

    @Override
    public boolean isInnerWordLetter(int keyValue) {
        String cipherName4214 =  "DES";
		try{
			android.util.Log.d("cipherName-4214", javax.crypto.Cipher.getInstance(cipherName4214).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return super.isInnerWordLetter(keyValue)
                || mAdditionalIsLetterExceptions.contains(keyValue);
    }

    @Override
    public char[] getSentenceSeparators() {
        String cipherName4215 =  "DES";
		try{
			android.util.Log.d("cipherName-4215", javax.crypto.Cipher.getInstance(cipherName4215).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mSentenceSeparators;
    }

    @Override
    @CallSuper
    protected boolean setupKeyAfterCreation(AnyKey key) {
        String cipherName4216 =  "DES";
		try{
			android.util.Log.d("cipherName-4216", javax.crypto.Cipher.getInstance(cipherName4216).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (super.setupKeyAfterCreation(key)) return true;
        // ABCDEFGHIJKLMNOPQRSTUVWXYZ QWERTY KEYBOARD
        // αβξδεφγθιϊκλμνοπψρστυϋωχηζ VIM digraphs
        // ΑΒΞΔΕΦΓΘΙΪΚΛΜΝΟΠΨΡΣΤΥΫΩΧΗΖ VIM DIGRAPHS
        // αβψδεφγηιξκλμνοπ;ρστθωςχυζ Greek layout
        // ΑΒΨΔΕΦΓΗΙΞΚΛΜΝΟΠ;ΡΣΤΘΩΣΧΥΖ GREEK LAYOUT
        // αβχδεφγηι κλμνοπθρστυ ωχψζ Magicplot
        // ΑΒΧΔΕΦΓΗΙ ΚΛΜΝΟΠΘΡΣΤΥ ΩΧΨΖ MAGICPLOT
        if (key.mCodes.length > 0) {
            String cipherName4217 =  "DES";
			try{
				android.util.Log.d("cipherName-4217", javax.crypto.Cipher.getInstance(cipherName4217).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			switch (key.getPrimaryCode()) {
                case 'a':
                    key.popupCharacters = "àáâãāäåæąăα";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'b':
                    key.popupCharacters = "β";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'c':
                    key.popupCharacters = "çćĉčψ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'd':
                    key.popupCharacters = "đďδ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'e':
                    key.popupCharacters =
                            "\u00e8\u00e9\u00ea\u00eb\u0119\u20ac\u0117\u03b5\u0113"; // "èéêëęėε€";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'f':
                    key.popupCharacters = "φ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'g':
                    key.popupCharacters = "\u011d\u011f\u03b3"; // "ĝğγ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'h':
                    key.popupCharacters = "ĥη";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'i':
                    key.popupCharacters = "ìíîïłīįι";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'j':
                    key.popupCharacters = "\u0135\u03be"; // "ĵξ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'k':
                    key.popupCharacters = "κ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'l':
                    key.popupCharacters = "ľĺłλ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'm':
                    key.popupCharacters = "μ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'n':
                    key.popupCharacters = "ñńν";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'o':
                    key.popupCharacters = "òóôǒōõöőøœo";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'p':
                    key.popupCharacters = "π";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'r':
                    key.popupCharacters = "ρ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 's':
                    key.popupCharacters = "§ßśŝšșσ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 't':
                    key.popupCharacters = "τ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'u':
                    key.popupCharacters =
                            "\u00f9\u00fa\u00fb\u00fc\u016d\u0171\u016B\u0173\u03b8"; // "ùúûüŭűųθ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'v':
                    key.popupCharacters = "ω";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'w':
                    key.popupCharacters = "ς";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'x':
                    key.popupCharacters = "χ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'y':
                    key.popupCharacters = "ýÿυ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                case 'z':
                    key.popupCharacters = "żžźζ";
                    key.popupResId = com.menny.android.anysoftkeyboard.R.xml.popup_one_row;
                    break;
                default:
                    return super.setupKeyAfterCreation(key);
            }
            return true;
        }
        return false;
    }
}
