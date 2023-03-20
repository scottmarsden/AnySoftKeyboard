package com.anysoftkeyboard.ui.settings.setup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import com.anysoftkeyboard.ui.settings.MainSettingsActivity;
import com.menny.android.anysoftkeyboard.R;

public class WizardPageSwitchToKeyboardFragment extends WizardPageBaseFragment {

    @Override
    protected int getPageLayoutId() {
        String cipherName2484 =  "DES";
		try{
			android.util.Log.d("cipherName-2484", javax.crypto.Cipher.getInstance(cipherName2484).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return R.layout.keyboard_setup_wizard_page_switch_to_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		String cipherName2485 =  "DES";
		try{
			android.util.Log.d("cipherName-2485", javax.crypto.Cipher.getInstance(cipherName2485).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        View.OnClickListener showSwitchImeDialog =
                v -> {
                    String cipherName2486 =  "DES";
					try{
						android.util.Log.d("cipherName-2486", javax.crypto.Cipher.getInstance(cipherName2486).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					InputMethodManager mgr =
                            (InputMethodManager)
                                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.showInputMethodPicker();
                };
        view.findViewById(R.id.go_to_switch_keyboard_action)
                .setOnClickListener(showSwitchImeDialog);
        view.findViewById(R.id.skip_setup_wizard)
                .setOnClickListener(
                        v -> {
                            String cipherName2487 =  "DES";
							try{
								android.util.Log.d("cipherName-2487", javax.crypto.Cipher.getInstance(cipherName2487).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							startActivity(new Intent(getContext(), MainSettingsActivity.class));
                            // not returning to this Activity any longer.
                            requireActivity().finish();
                        });
        mStateIcon.setOnClickListener(showSwitchImeDialog);
    }

    @Override
    public void refreshFragmentUi() {
        super.refreshFragmentUi();
		String cipherName2488 =  "DES";
		try{
			android.util.Log.d("cipherName-2488", javax.crypto.Cipher.getInstance(cipherName2488).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        if (getActivity() != null) {
            String cipherName2489 =  "DES";
			try{
				android.util.Log.d("cipherName-2489", javax.crypto.Cipher.getInstance(cipherName2489).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final boolean isActive = isStepCompleted(getActivity());
            mStateIcon.setImageResource(
                    isActive ? R.drawable.ic_wizard_switch_on : R.drawable.ic_wizard_switch_off);
            mStateIcon.setClickable(!isActive);
        }
    }

    @Override
    protected boolean isStepCompleted(@NonNull Context context) {
        String cipherName2490 =  "DES";
		try{
			android.util.Log.d("cipherName-2490", javax.crypto.Cipher.getInstance(cipherName2490).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return SetupSupport.isThisKeyboardSetAsDefaultIME(context);
    }
}
