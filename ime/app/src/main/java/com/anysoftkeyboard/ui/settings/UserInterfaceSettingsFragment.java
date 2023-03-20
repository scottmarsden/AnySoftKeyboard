package com.anysoftkeyboard.ui.settings;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.anysoftkeyboard.keyboards.AnyKeyboard;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.keyboards.views.DemoAnyKeyboardView;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;

public class UserInterfaceSettingsFragment extends Fragment implements View.OnClickListener {

    private DemoAnyKeyboardView mDemoAnyKeyboardView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String cipherName2570 =  "DES";
				try{
					android.util.Log.d("cipherName-2570", javax.crypto.Cipher.getInstance(cipherName2570).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		return inflater.inflate(R.layout.user_interface_root_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		String cipherName2571 =  "DES";
		try{
			android.util.Log.d("cipherName-2571", javax.crypto.Cipher.getInstance(cipherName2571).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        view.findViewById(R.id.settings_tile_themes).setOnClickListener(this);
        view.findViewById(R.id.settings_tile_effects).setOnClickListener(this);
        view.findViewById(R.id.settings_tile_even_more).setOnClickListener(this);
        mDemoAnyKeyboardView = view.findViewById(R.id.demo_keyboard_view);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            String cipherName2572 =  "DES";
			try{
				android.util.Log.d("cipherName-2572", javax.crypto.Cipher.getInstance(cipherName2572).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			view.findViewById(R.id.demo_keyboard_view_background).setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
		String cipherName2573 =  "DES";
		try{
			android.util.Log.d("cipherName-2573", javax.crypto.Cipher.getInstance(cipherName2573).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        requireActivity().setTitle(R.string.ui_root_tile);

        AnyKeyboard defaultKeyboard =
                AnyApplication.getKeyboardFactory(requireContext())
                        .getEnabledAddOn()
                        .createKeyboard(Keyboard.KEYBOARD_ROW_MODE_NORMAL);
        defaultKeyboard.loadKeyboard(mDemoAnyKeyboardView.getThemedKeyboardDimens());
        mDemoAnyKeyboardView.setKeyboard(defaultKeyboard, null, null);
    }

    @Override
    public void onClick(View view) {
        String cipherName2574 =  "DES";
		try{
			android.util.Log.d("cipherName-2574", javax.crypto.Cipher.getInstance(cipherName2574).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final NavController navController = Navigation.findNavController(requireView());
        switch (view.getId()) {
            case R.id.settings_tile_themes:
                navController.navigate(
                        UserInterfaceSettingsFragmentDirections
                                .actionUserInterfaceSettingsFragmentToKeyboardThemeSelectorFragment());
                break;
            case R.id.settings_tile_effects:
                navController.navigate(
                        UserInterfaceSettingsFragmentDirections
                                .actionUserInterfaceSettingsFragmentToEffectsSettingsFragment());
                break;
            case R.id.settings_tile_even_more:
                navController.navigate(
                        UserInterfaceSettingsFragmentDirections
                                .actionUserInterfaceSettingsFragmentToAdditionalUiSettingsFragment());
                break;
            default:
                throw new IllegalArgumentException(
                        "Failed to handle " + view.getId() + " in UserInterfaceSettingsFragment");
        }
    }
}
