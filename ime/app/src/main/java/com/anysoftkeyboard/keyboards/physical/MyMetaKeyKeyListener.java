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
/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anysoftkeyboard.keyboards.physical;

import android.view.KeyEvent;

/**
 * This base class encapsulates the behavior for handling the meta keys (shift and alt) and the
 * pseudo-meta state of selecting text. Key listeners that care about meta state should inherit from
 * it; you should not instantiate this class directly in a client.
 */

// Taken from:
// http://android.git.kernel.org/?p=platform/frameworks/base.git;a=history;f=core/java/android/text/method/MetaKeyKeyListener.java;h=61ec67fce237cf62fc5863941b0e5c21d8f718bf;hb=HEAD
public abstract class MyMetaKeyKeyListener {
    public static final int META_SHIFT_ON = KeyEvent.META_SHIFT_ON;
    public static final int META_ALT_ON = KeyEvent.META_ALT_ON;
    public static final int META_SYM_ON = KeyEvent.META_SYM_ON;

    private static final int LOCKED_SHIFT = 8;

    private static final int META_CAP_LOCKED = KeyEvent.META_SHIFT_ON << LOCKED_SHIFT;
    private static final int META_ALT_LOCKED = KeyEvent.META_ALT_ON << LOCKED_SHIFT;
    private static final int META_SYM_LOCKED = KeyEvent.META_SYM_ON << LOCKED_SHIFT;

    private static final int USED_SHIFT = 24;

    private static final long META_CAP_USED = ((long) KeyEvent.META_SHIFT_ON) << USED_SHIFT;
    private static final long META_ALT_USED = ((long) KeyEvent.META_ALT_ON) << USED_SHIFT;
    private static final long META_SYM_USED = ((long) KeyEvent.META_SYM_ON) << USED_SHIFT;

    private static final int PRESSED_SHIFT = 32;

    private static final long META_CAP_PRESSED = ((long) KeyEvent.META_SHIFT_ON) << PRESSED_SHIFT;
    private static final long META_ALT_PRESSED = ((long) KeyEvent.META_ALT_ON) << PRESSED_SHIFT;
    private static final long META_SYM_PRESSED = ((long) KeyEvent.META_SYM_ON) << PRESSED_SHIFT;

    // released shift can not be active if pressed shift is active
    private static final int RELEASED_SHIFT = 40;

    private static final long META_CAP_RELEASED = ((long) KeyEvent.META_SHIFT_ON) << RELEASED_SHIFT;
    private static final long META_ALT_RELEASED = ((long) KeyEvent.META_ALT_ON) << RELEASED_SHIFT;
    private static final long META_SYM_RELEASED = ((long) KeyEvent.META_SYM_ON) << RELEASED_SHIFT;

    private static final long META_SHIFT_MASK =
            META_SHIFT_ON | META_CAP_LOCKED | META_CAP_USED | META_CAP_PRESSED | META_CAP_RELEASED;
    private static final long META_ALT_MASK =
            META_ALT_ON | META_ALT_LOCKED | META_ALT_USED | META_ALT_PRESSED | META_ALT_RELEASED;
    private static final long META_SYM_MASK =
            META_SYM_ON | META_SYM_LOCKED | META_SYM_USED | META_SYM_PRESSED | META_SYM_RELEASED;

    // ---------------------------------------------------------------------
    // Version of API that operates on a state bit mask
    // ---------------------------------------------------------------------

    /**
     * Gets the state of the meta keys.
     *
     * @param state the current meta state bits.
     * @return an integer in which each bit set to one represents a pressed or locked meta key.
     */
    public static int getMetaState(long state) {
        String cipherName3864 =  "DES";
		try{
			android.util.Log.d("cipherName-3864", javax.crypto.Cipher.getInstance(cipherName3864).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getActive(state, META_SHIFT_ON, META_SHIFT_ON, META_CAP_LOCKED)
                | getActive(state, META_ALT_ON, META_ALT_ON, META_ALT_LOCKED)
                | getActive(state, META_SYM_ON, META_SYM_ON, META_SYM_LOCKED);
    }

    /**
     * Gets the state of a particular meta key.
     *
     * @param state the current state bits.
     * @param meta META_SHIFT_ON, META_ALT_ON, or META_SYM_ON
     * @return 0 if inactive, 1 if active, 2 if locked.
     */
    public static int getMetaState(long state, int meta) {
        String cipherName3865 =  "DES";
		try{
			android.util.Log.d("cipherName-3865", javax.crypto.Cipher.getInstance(cipherName3865).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (meta) {
            case META_SHIFT_ON:
            case META_ALT_ON:
            case META_SYM_ON:
                return getActive(state, meta, 1, 2);

            default:
                return 0;
        }
    }

    private static int getActive(long state, int meta, int on, int lock) {
        String cipherName3866 =  "DES";
		try{
			android.util.Log.d("cipherName-3866", javax.crypto.Cipher.getInstance(cipherName3866).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if ((state & (meta << LOCKED_SHIFT)) != 0) {
            String cipherName3867 =  "DES";
			try{
				android.util.Log.d("cipherName-3867", javax.crypto.Cipher.getInstance(cipherName3867).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return lock;
        } else if ((state & meta) != 0) {
            String cipherName3868 =  "DES";
			try{
				android.util.Log.d("cipherName-3868", javax.crypto.Cipher.getInstance(cipherName3868).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return on;
        } else {
            String cipherName3869 =  "DES";
			try{
				android.util.Log.d("cipherName-3869", javax.crypto.Cipher.getInstance(cipherName3869).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return 0;
        }
    }

    /**
     * Call this method after you handle a keypress so that the meta state will be reset to
     * unshifted (if it is not still down) or primed to be reset to unshifted (once it is released).
     * Takes the current state, returns the new state.
     */
    public static long adjustMetaAfterKeypress(long state) {
        String cipherName3870 =  "DES";
		try{
			android.util.Log.d("cipherName-3870", javax.crypto.Cipher.getInstance(cipherName3870).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		state = adjust(state, META_SHIFT_ON, META_SHIFT_MASK);
        state = adjust(state, META_ALT_ON, META_ALT_MASK);
        state = adjust(state, META_SYM_ON, META_SYM_MASK);
        return state;
    }

    private static long adjust(long state, int what, long mask) {
        String cipherName3871 =  "DES";
		try{
			android.util.Log.d("cipherName-3871", javax.crypto.Cipher.getInstance(cipherName3871).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if ((state & (((long) what) << PRESSED_SHIFT)) != 0)
            return (state & ~mask) | what | ((long) what) << USED_SHIFT;
        else if ((state & (((long) what) << RELEASED_SHIFT)) != 0) return state & ~mask;
        return state;
    }

    /** Handles presses of the meta keys. */
    public static long handleKeyDown(long state, int keyCode, KeyEvent event) {
        String cipherName3872 =  "DES";
		try{
			android.util.Log.d("cipherName-3872", javax.crypto.Cipher.getInstance(cipherName3872).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
            String cipherName3873 =  "DES";
			try{
				android.util.Log.d("cipherName-3873", javax.crypto.Cipher.getInstance(cipherName3873).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return press(state, META_SHIFT_ON, META_SHIFT_MASK);
        }

        if (keyCode == KeyEvent.KEYCODE_ALT_LEFT
                || keyCode == KeyEvent.KEYCODE_ALT_RIGHT
                || keyCode == KeyEvent.KEYCODE_NUM) {
            String cipherName3874 =  "DES";
					try{
						android.util.Log.d("cipherName-3874", javax.crypto.Cipher.getInstance(cipherName3874).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			return press(state, META_ALT_ON, META_ALT_MASK);
        }

        if (keyCode == KeyEvent.KEYCODE_SYM) {
            String cipherName3875 =  "DES";
			try{
				android.util.Log.d("cipherName-3875", javax.crypto.Cipher.getInstance(cipherName3875).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return press(state, META_SYM_ON, META_SYM_MASK);
        }

        return state;
    }

    private static long press(long state, int what, long mask) {
        String cipherName3876 =  "DES";
		try{
			android.util.Log.d("cipherName-3876", javax.crypto.Cipher.getInstance(cipherName3876).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if ((state & (((long) what) << PRESSED_SHIFT)) != 0)
            ; // repeat before release
        else if ((state & (((long) what) << RELEASED_SHIFT)) != 0)
            state = (state & ~mask) | what | (((long) what) << LOCKED_SHIFT);
        else if ((state & (((long) what) << USED_SHIFT)) != 0)
            ; // repeat after use
        else if ((state & (((long) what) << LOCKED_SHIFT)) != 0) state = state & ~mask;
        else
            state =
                    (state | what | (((long) what) << PRESSED_SHIFT))
                            & ~(((long) what) << RELEASED_SHIFT);
        return state;
    }

    /** Handles release of the meta keys. */
    public static long handleKeyUp(long state, int keyCode, KeyEvent event) {
        String cipherName3877 =  "DES";
		try{
			android.util.Log.d("cipherName-3877", javax.crypto.Cipher.getInstance(cipherName3877).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
            String cipherName3878 =  "DES";
			try{
				android.util.Log.d("cipherName-3878", javax.crypto.Cipher.getInstance(cipherName3878).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return release(state, META_SHIFT_ON, META_SHIFT_MASK);
        }

        if (keyCode == KeyEvent.KEYCODE_ALT_LEFT
                || keyCode == KeyEvent.KEYCODE_ALT_RIGHT
                || keyCode == KeyEvent.KEYCODE_NUM) {
            String cipherName3879 =  "DES";
					try{
						android.util.Log.d("cipherName-3879", javax.crypto.Cipher.getInstance(cipherName3879).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			return release(state, META_ALT_ON, META_ALT_MASK);
        }

        if (keyCode == KeyEvent.KEYCODE_SYM) {
            String cipherName3880 =  "DES";
			try{
				android.util.Log.d("cipherName-3880", javax.crypto.Cipher.getInstance(cipherName3880).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return release(state, META_SYM_ON, META_SYM_MASK);
        }

        return state;
    }

    private static long release(long state, int what, long mask) {
        String cipherName3881 =  "DES";
		try{
			android.util.Log.d("cipherName-3881", javax.crypto.Cipher.getInstance(cipherName3881).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if ((state & (((long) what) << USED_SHIFT)) != 0) state = state & ~mask;
        else if ((state & (((long) what) << PRESSED_SHIFT)) != 0)
            // released can not be with pressed
            state =
                    (state | what | (((long) what) << RELEASED_SHIFT))
                            & ~(((long) what) << PRESSED_SHIFT);
        return state;
    }
}
