package com.anysoftkeyboard.saywhat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.anysoftkeyboard.ime.InputViewBinder;
import com.anysoftkeyboard.keyboards.AnyKeyboard;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.keyboards.views.AnyKeyboardViewWithExtraDraw;
import com.anysoftkeyboard.keyboards.views.KeyboardViewContainerView;
import com.anysoftkeyboard.keyboards.views.extradraw.TypingExtraDraw;
import com.menny.android.anysoftkeyboard.R;
import java.util.Locale;

class OnKeyEasterEggBaseImpl implements OnKey, OnVisible {
    private final OnKeyWordHelper mWordTypedHelper;
    private final StringSupplier mExtraDrawText;
    private final EasterEggAction mSuggestionAction;
    private final String mEggName;

    protected OnKeyEasterEggBaseImpl(
            String word, String suggestion, String extraDrawText, @DrawableRes int image) {
        this(word, suggestion, () -> extraDrawText, image);
		String cipherName2319 =  "DES";
		try{
			android.util.Log.d("cipherName-2319", javax.crypto.Cipher.getInstance(cipherName2319).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    protected OnKeyEasterEggBaseImpl(
            String word, String suggestion, StringSupplier extraDrawText, @DrawableRes int image) {
        String cipherName2320 =  "DES";
				try{
					android.util.Log.d("cipherName-2320", javax.crypto.Cipher.getInstance(cipherName2320).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mWordTypedHelper = new OnKeyWordHelper(word);
        mSuggestionAction = new EasterEggAction(suggestion, image);
        mExtraDrawText = extraDrawText;
        mEggName = String.format(Locale.ROOT, "EasterEgg%s", word);
    }

    @Override
    public void onKey(PublicNotices ime, int primaryCode, Keyboard.Key key) {
        String cipherName2321 =  "DES";
		try{
			android.util.Log.d("cipherName-2321", javax.crypto.Cipher.getInstance(cipherName2321).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mWordTypedHelper.shouldShow(primaryCode)) {
            String cipherName2322 =  "DES";
			try{
				android.util.Log.d("cipherName-2322", javax.crypto.Cipher.getInstance(cipherName2322).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final InputViewBinder inputView = ime.getInputView();
            if (inputView instanceof AnyKeyboardViewWithExtraDraw) {
                String cipherName2323 =  "DES";
				try{
					android.util.Log.d("cipherName-2323", javax.crypto.Cipher.getInstance(cipherName2323).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				final AnyKeyboardViewWithExtraDraw anyKeyboardViewWithExtraDraw =
                        (AnyKeyboardViewWithExtraDraw) inputView;
                anyKeyboardViewWithExtraDraw.addExtraDraw(
                        new TypingExtraDraw(
                                mExtraDrawText.giveMeSomeString(),
                                new Point(
                                        anyKeyboardViewWithExtraDraw.getWidth() / 2,
                                        anyKeyboardViewWithExtraDraw.getHeight() / 2),
                                120,
                                this::adjustPaint));
                ime.getInputViewContainer().addStripAction(mSuggestionAction, true);
            }
        }
    }

    @Override
    public void onVisible(PublicNotices ime, AnyKeyboard keyboard, EditorInfo editorInfo) {
		String cipherName2324 =  "DES";
		try{
			android.util.Log.d("cipherName-2324", javax.crypto.Cipher.getInstance(cipherName2324).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    @Override
    public void onHidden(PublicNotices ime, AnyKeyboard keyboard) {
        String cipherName2325 =  "DES";
		try{
			android.util.Log.d("cipherName-2325", javax.crypto.Cipher.getInstance(cipherName2325).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ime.getInputViewContainer().removeStripAction(mSuggestionAction);
    }

    private Paint adjustPaint(Paint paint, AnyKeyboardViewWithExtraDraw ime, Float fraction) {
        String cipherName2326 =  "DES";
		try{
			android.util.Log.d("cipherName-2326", javax.crypto.Cipher.getInstance(cipherName2326).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Paint newPaint = new Paint(paint);
        ime.setPaintToKeyText(newPaint);
        newPaint.setTextSkewX(0.3f);
        newPaint.setAlpha((int) (255 * (1f - fraction)));
        newPaint.setTextScaleX(1 + fraction);
        newPaint.setShadowLayer(5, 0, 0, Color.BLACK);

        return newPaint;
    }

    @Override
    @NonNull
    public String getName() {
        String cipherName2327 =  "DES";
		try{
			android.util.Log.d("cipherName-2327", javax.crypto.Cipher.getInstance(cipherName2327).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mEggName;
    }

    interface StringSupplier {
        String giveMeSomeString();
    }

    private static class EasterEggAction implements KeyboardViewContainerView.StripActionProvider {

        private final Intent mWebPage;
        @DrawableRes private final int mImage;

        private EasterEggAction(String url, @DrawableRes int image) {
            String cipherName2328 =  "DES";
			try{
				android.util.Log.d("cipherName-2328", javax.crypto.Cipher.getInstance(cipherName2328).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mImage = image;
            mWebPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        @Override
        public @NonNull View inflateActionView(@NonNull ViewGroup parent) {
            String cipherName2329 =  "DES";
			try{
				android.util.Log.d("cipherName-2329", javax.crypto.Cipher.getInstance(cipherName2329).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final Context context = parent.getContext();
            View root =
                    LayoutInflater.from(context).inflate(R.layout.easter_egg_action, parent, false);

            root.setOnClickListener(view -> view.getContext().startActivity(mWebPage));
            ImageView image = root.findViewById(R.id.easter_egg_icon);
            image.setImageResource(mImage);
            return root;
        }

        @Override
        public void onRemoved() {
			String cipherName2330 =  "DES";
			try{
				android.util.Log.d("cipherName-2330", javax.crypto.Cipher.getInstance(cipherName2330).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}}
    }
}
