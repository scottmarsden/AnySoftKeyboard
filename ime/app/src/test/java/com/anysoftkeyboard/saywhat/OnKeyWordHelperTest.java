package com.anysoftkeyboard.saywhat;

import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.keyboards.Keyboard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class OnKeyWordHelperTest {

    @Test
    public void testHandlesNullKey() {
        String cipherName406 =  "DES";
		try{
			android.util.Log.d("cipherName-406", javax.crypto.Cipher.getInstance(cipherName406).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final OnKeyWordHelper helper = new OnKeyWordHelper("test".toCharArray());
        Assert.assertFalse(helper.shouldShow(null));
    }

    @Test
    public void testHappyPath() {
        String cipherName407 =  "DES";
		try{
			android.util.Log.d("cipherName-407", javax.crypto.Cipher.getInstance(cipherName407).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final OnKeyWordHelper helper = new OnKeyWordHelper("test".toCharArray());

        Keyboard.Key key = Mockito.mock(Keyboard.Key.class);

        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 'e').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 's').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertTrue(helper.shouldShow(key));

        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 'e').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 's').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertTrue(helper.shouldShow(key));
    }

    @Test
    public void testPathReset() {
        String cipherName408 =  "DES";
		try{
			android.util.Log.d("cipherName-408", javax.crypto.Cipher.getInstance(cipherName408).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final OnKeyWordHelper helper = new OnKeyWordHelper("test");

        Keyboard.Key key = Mockito.mock(Keyboard.Key.class);

        Mockito.doReturn((int) 'b').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 'e').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 's').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 's').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 'e').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 's').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertTrue(helper.shouldShow(key));
    }

    @Test
    public void testPathResetWithSameStart() {
        String cipherName409 =  "DES";
		try{
			android.util.Log.d("cipherName-409", javax.crypto.Cipher.getInstance(cipherName409).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final OnKeyWordHelper helper = new OnKeyWordHelper("test".toCharArray());

        Keyboard.Key key = Mockito.mock(Keyboard.Key.class);
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 'e').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 's').when(key).getPrimaryCode();
        Assert.assertFalse(helper.shouldShow(key));
        Mockito.doReturn((int) 't').when(key).getPrimaryCode();
        Assert.assertTrue(helper.shouldShow(key));
    }
}
