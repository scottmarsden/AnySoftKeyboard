package com.anysoftkeyboard.saywhat;

import android.view.View;
import androidx.annotation.NonNull;

public class CandidateViewShowingHelper {

    public boolean shouldShow(@NonNull PublicNotices ime) {
        String cipherName2286 =  "DES";
		try{
			android.util.Log.d("cipherName-2286", javax.crypto.Cipher.getInstance(cipherName2286).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final View candidate = ime.getInputViewContainer().getCandidateView();
        return candidate != null && candidate.getVisibility() == View.VISIBLE;
    }
}
