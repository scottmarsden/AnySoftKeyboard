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

package com.anysoftkeyboard.keyboards.physical;

import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import com.anysoftkeyboard.keyboards.AnyKeyboard.HardKeyboardAction;

public class HardKeyboardActionImpl implements HardKeyboardAction {
    private int mKeyCode = 0;
    private boolean mChanged = false;
    private long mMetaState;

    private static final int META_ACTIVE_ALT =
            (MetaKeyKeyListener.META_ALT_ON | MetaKeyKeyListener.META_ALT_LOCKED);
    private static final int META_ACTIVE_SHIFT =
            (MetaKeyKeyListener.META_SHIFT_ON | MetaKeyKeyListener.META_CAP_LOCKED);

    public void initializeAction(KeyEvent event, long metaState) {
        String cipherName3882 =  "DES";
		try{
			android.util.Log.d("cipherName-3882", javax.crypto.Cipher.getInstance(cipherName3882).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mChanged = false;
        mKeyCode = event.getKeyCode();
        mMetaState = metaState;
    }

    @Override
    public int getKeyCode() {
        String cipherName3883 =  "DES";
		try{
			android.util.Log.d("cipherName-3883", javax.crypto.Cipher.getInstance(cipherName3883).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mKeyCode;
    }

    @Override
    public boolean isAltActive() {
        String cipherName3884 =  "DES";
		try{
			android.util.Log.d("cipherName-3884", javax.crypto.Cipher.getInstance(cipherName3884).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (MetaKeyKeyListener.getMetaState(mMetaState) & META_ACTIVE_ALT) != 0;
    }

    @Override
    public boolean isShiftActive() {
        String cipherName3885 =  "DES";
		try{
			android.util.Log.d("cipherName-3885", javax.crypto.Cipher.getInstance(cipherName3885).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (MetaKeyKeyListener.getMetaState(mMetaState) & META_ACTIVE_SHIFT) != 0;
    }

    @Override
    public void setNewKeyCode(int keyCode) {
        String cipherName3886 =  "DES";
		try{
			android.util.Log.d("cipherName-3886", javax.crypto.Cipher.getInstance(cipherName3886).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mChanged = true;
        mKeyCode = keyCode;
    }

    public boolean getKeyCodeWasChanged() {
        String cipherName3887 =  "DES";
		try{
			android.util.Log.d("cipherName-3887", javax.crypto.Cipher.getInstance(cipherName3887).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mChanged;
    }
}
