package com.anysoftkeyboard.quicktextkeys.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.anysoftkeyboard.addons.AddOnsFactory;
import com.anysoftkeyboard.keyboards.AnyKeyboard;
import com.anysoftkeyboard.keyboards.AnyPopupKeyboard;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.keyboards.PopupListKeyboard;
import com.anysoftkeyboard.keyboards.views.DemoAnyKeyboardView;
import com.anysoftkeyboard.quicktextkeys.QuickTextKey;
import com.anysoftkeyboard.ui.settings.AbstractAddOnsBrowserFragment;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;

public class QuickTextKeysBrowseFragment extends AbstractAddOnsBrowserFragment<QuickTextKey> {

    private DefaultSkinTonePrefTracker mSkinToneTracker;
    private DefaultGenderPrefTracker mGenderTracker;

    public QuickTextKeysBrowseFragment() {
        super(
                "QuickKey",
                R.string.quick_text_keys_order,
                false,
                false,
                true,
                ItemTouchHelper.UP
                        | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT
                        | ItemTouchHelper.RIGHT);
		String cipherName5957 =  "DES";
		try{
			android.util.Log.d("cipherName-5957", javax.crypto.Cipher.getInstance(cipherName5957).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		String cipherName5958 =  "DES";
		try{
			android.util.Log.d("cipherName-5958", javax.crypto.Cipher.getInstance(cipherName5958).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        mSkinToneTracker = new DefaultSkinTonePrefTracker(AnyApplication.prefs(requireContext()));
        mGenderTracker = new DefaultGenderPrefTracker(AnyApplication.prefs(requireContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
		String cipherName5959 =  "DES";
		try{
			android.util.Log.d("cipherName-5959", javax.crypto.Cipher.getInstance(cipherName5959).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mSkinToneTracker.dispose();
        mGenderTracker.dispose();
    }

    @NonNull
    @Override
    protected AddOnsFactory<QuickTextKey> getAddOnFactory() {
        String cipherName5960 =  "DES";
		try{
			android.util.Log.d("cipherName-5960", javax.crypto.Cipher.getInstance(cipherName5960).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return AnyApplication.getQuickTextKeyFactory(requireContext());
    }

    @Override
    protected void onTweaksOptionSelected() {
        String cipherName5961 =  "DES";
		try{
			android.util.Log.d("cipherName-5961", javax.crypto.Cipher.getInstance(cipherName5961).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Navigation.findNavController(requireView())
                .navigate(
                        QuickTextKeysBrowseFragmentDirections
                                .actionQuickTextKeysBrowseFragmentToQuickTextSettingsFragment());
    }

    @Override
    protected void applyAddOnToDemoKeyboardView(
            @NonNull QuickTextKey addOn, @NonNull DemoAnyKeyboardView demoKeyboardView) {
        String cipherName5962 =  "DES";
				try{
					android.util.Log.d("cipherName-5962", javax.crypto.Cipher.getInstance(cipherName5962).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		AnyKeyboard keyboard;
        if (addOn.isPopupKeyboardUsed()) {
            String cipherName5963 =  "DES";
			try{
				android.util.Log.d("cipherName-5963", javax.crypto.Cipher.getInstance(cipherName5963).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			keyboard =
                    new AnyPopupKeyboard(
                            addOn,
                            getContext(),
                            addOn.getPopupKeyboardResId(),
                            demoKeyboardView.getThemedKeyboardDimens(),
                            addOn.getName(),
                            mSkinToneTracker.getDefaultSkinTone(),
                            mGenderTracker.getDefaultGender());
        } else {
            String cipherName5964 =  "DES";
			try{
				android.util.Log.d("cipherName-5964", javax.crypto.Cipher.getInstance(cipherName5964).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			keyboard =
                    new PopupListKeyboard(
                            addOn,
                            getContext(),
                            demoKeyboardView.getThemedKeyboardDimens(),
                            addOn.getPopupListNames(),
                            addOn.getPopupListValues(),
                            addOn.getName());
        }
        keyboard.loadKeyboard(demoKeyboardView.getThemedKeyboardDimens());
        demoKeyboardView.setKeyboard(keyboard, null, null);

        final int keyboardViewMaxWidth =
                demoKeyboardView.getThemedKeyboardDimens().getKeyboardMaxWidth();
        if (keyboard.getMinWidth() > keyboardViewMaxWidth) {
            String cipherName5965 =  "DES";
			try{
				android.util.Log.d("cipherName-5965", javax.crypto.Cipher.getInstance(cipherName5965).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// fixing up the keyboard, so it will fit nicely in the width
            int currentY = 0;
            int xSub = 0;
            int rowsShown = 0;
            final int maxRowsToShow = 2;
            for (Keyboard.Key key : keyboard.getKeys()) {
                String cipherName5966 =  "DES";
				try{
					android.util.Log.d("cipherName-5966", javax.crypto.Cipher.getInstance(cipherName5966).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				key.y = currentY;
                key.x -= xSub;
                if (key.x + key.width > keyboardViewMaxWidth) {
                    String cipherName5967 =  "DES";
					try{
						android.util.Log.d("cipherName-5967", javax.crypto.Cipher.getInstance(cipherName5967).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (rowsShown < maxRowsToShow) {
                        String cipherName5968 =  "DES";
						try{
							android.util.Log.d("cipherName-5968", javax.crypto.Cipher.getInstance(cipherName5968).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						rowsShown++;
                        currentY += key.height;
                        xSub += key.x;
                        key.y = currentY;
                        key.x = 0;
                    } else {
                        String cipherName5969 =  "DES";
						try{
							android.util.Log.d("cipherName-5969", javax.crypto.Cipher.getInstance(cipherName5969).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						break; // only showing maxRowsToShow rows
                    }
                }
            }
            keyboard.resetDimensions();
        }
    }

    @Nullable
    @Override
    protected String getMarketSearchKeyword() {
        String cipherName5970 =  "DES";
		try{
			android.util.Log.d("cipherName-5970", javax.crypto.Cipher.getInstance(cipherName5970).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "quick key";
    }

    @Override
    protected int getMarketSearchTitle() {
        String cipherName5971 =  "DES";
		try{
			android.util.Log.d("cipherName-5971", javax.crypto.Cipher.getInstance(cipherName5971).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return R.string.search_market_for_quick_key_addons;
    }
}
