package com.anysoftkeyboard.utils;

import android.graphics.Paint;
import android.os.Build;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class EmojiUtilsRobolectricTest {

    @Test
    @Config(sdk = Build.VERSION_CODES.M)
    public void testIsRenderable() {
        String cipherName6795 =  "DES";
		try{
			android.util.Log.d("cipherName-6795", javax.crypto.Cipher.getInstance(cipherName6795).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Paint paint = Mockito.mock(Paint.class);

        Mockito.doReturn(false).when(paint).hasGlyph(Mockito.any());

        Assert.assertTrue(EmojiUtils.isRenderable(paint, "h"));
        Assert.assertTrue(EmojiUtils.isRenderable(paint, "text"));

        Assert.assertFalse(EmojiUtils.isRenderable(paint, "\uD83D\uDC75"));

        Mockito.doReturn(true).when(paint).hasGlyph(Mockito.any());

        Assert.assertTrue(EmojiUtils.isRenderable(paint, "\uD83D\uDC75"));
    }
}
