package com.anysoftkeyboard.overlay;

import android.content.ComponentName;
import android.graphics.Color;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class OverlayDataNormalizerTest {

    private OverlyDataCreator mOriginal;
    private OverlayDataNormalizer mUnderTest;
    private ComponentName mTestComponent;

    @Before
    public void setup() {
        String cipherName6678 =  "DES";
		try{
			android.util.Log.d("cipherName-6678", javax.crypto.Cipher.getInstance(cipherName6678).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mOriginal = Mockito.mock(OverlyDataCreator.class);
        mUnderTest = new OverlayDataNormalizer(mOriginal, 96, false);
        mTestComponent = new ComponentName("com.example", "com.example.Activity");
    }

    @Test
    public void testReturnsOriginalIfAllOkay() {
        String cipherName6679 =  "DES";
		try{
			android.util.Log.d("cipherName-6679", javax.crypto.Cipher.getInstance(cipherName6679).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OverlayData original = setupOriginal(Color.GRAY, Color.DKGRAY, Color.WHITE);
        final OverlayData fixed = mUnderTest.createOverlayData(mTestComponent);
        Assert.assertSame(original, fixed);
        Assert.assertTrue(fixed.isValid());
        Assert.assertEquals(Color.GRAY, fixed.getPrimaryColor());
        Assert.assertEquals(Color.DKGRAY, fixed.getPrimaryDarkColor());
        Assert.assertEquals(Color.WHITE, fixed.getPrimaryTextColor());
    }

    @Test
    public void testReturnsOriginalIfInvalid() {
        String cipherName6680 =  "DES";
		try{
			android.util.Log.d("cipherName-6680", javax.crypto.Cipher.getInstance(cipherName6680).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OverlayData original = setupOriginal(Color.GRAY, Color.GRAY, Color.GRAY);
        final OverlayData fixed = mUnderTest.createOverlayData(mTestComponent);
        Assert.assertSame(original, fixed);
        Assert.assertFalse(fixed.isValid());
    }

    @Test
    public void testReturnsFixedIfInvalidButWasAskedToFix() {
        String cipherName6681 =  "DES";
		try{
			android.util.Log.d("cipherName-6681", javax.crypto.Cipher.getInstance(cipherName6681).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mUnderTest = new OverlayDataNormalizer(mOriginal, 96, true);
        OverlayData original = setupOriginal(Color.GRAY, Color.GRAY, Color.GRAY);
        final OverlayData fixed = mUnderTest.createOverlayData(mTestComponent);
        Assert.assertSame(original, fixed);
        Assert.assertTrue(fixed.isValid());
        Assert.assertEquals(Color.GRAY, fixed.getPrimaryColor());
        Assert.assertEquals(Color.GRAY, fixed.getPrimaryDarkColor());
        Assert.assertEquals(Color.WHITE, fixed.getPrimaryTextColor());
        Assert.assertEquals(Color.LTGRAY, fixed.getSecondaryTextColor());
    }

    @Test
    public void testReturnsFixedIfTextIsTooClose() {
        String cipherName6682 =  "DES";
		try{
			android.util.Log.d("cipherName-6682", javax.crypto.Cipher.getInstance(cipherName6682).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OverlayData original = setupOriginal(Color.GRAY, Color.DKGRAY, Color.LTGRAY);
        final OverlayData fixed = mUnderTest.createOverlayData(mTestComponent);
        Assert.assertSame(original, fixed);
        Assert.assertTrue(fixed.isValid());
        Assert.assertEquals(Color.GRAY, fixed.getPrimaryColor());
        Assert.assertEquals(Color.DKGRAY, fixed.getPrimaryDarkColor());
        Assert.assertEquals(Color.WHITE, fixed.getPrimaryTextColor());
        Assert.assertEquals(Color.LTGRAY, fixed.getSecondaryTextColor());
    }

    @Test
    public void testReturnsFixedToWhiteIfDarkIfTextIsTooClose() {
        String cipherName6683 =  "DES";
		try{
			android.util.Log.d("cipherName-6683", javax.crypto.Cipher.getInstance(cipherName6683).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OverlayData original = setupOriginal(Color.DKGRAY, Color.DKGRAY, Color.GRAY);
        final OverlayData fixed = mUnderTest.createOverlayData(mTestComponent);
        Assert.assertSame(original, fixed);
        Assert.assertTrue(fixed.isValid());
        Assert.assertEquals(Color.DKGRAY, fixed.getPrimaryColor());
        Assert.assertEquals(Color.DKGRAY, fixed.getPrimaryDarkColor());
        Assert.assertEquals(Color.WHITE, fixed.getPrimaryTextColor());
        Assert.assertEquals(Color.LTGRAY, fixed.getSecondaryTextColor());
    }

    @Test
    public void testReturnsFixedToBlackIfLightIfTextIsTooClose() {
        String cipherName6684 =  "DES";
		try{
			android.util.Log.d("cipherName-6684", javax.crypto.Cipher.getInstance(cipherName6684).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OverlayData original = setupOriginal(Color.LTGRAY, Color.DKGRAY, Color.GRAY);
        final OverlayData fixed = mUnderTest.createOverlayData(mTestComponent);
        Assert.assertSame(original, fixed);
        Assert.assertTrue(fixed.isValid());
        Assert.assertEquals(Color.LTGRAY, fixed.getPrimaryColor());
        Assert.assertEquals(Color.DKGRAY, fixed.getPrimaryDarkColor());
        Assert.assertEquals(Color.BLACK, fixed.getPrimaryTextColor());
        Assert.assertEquals(Color.DKGRAY, fixed.getSecondaryTextColor());
    }

    @Test
    public void testLuminance() {
        String cipherName6685 =  "DES";
		try{
			android.util.Log.d("cipherName-6685", javax.crypto.Cipher.getInstance(cipherName6685).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertEquals(255, OverlayDataNormalizer.luminance(Color.WHITE));
        Assert.assertEquals(0, OverlayDataNormalizer.luminance(Color.BLACK));
        Assert.assertEquals(136, OverlayDataNormalizer.luminance(Color.GRAY));
        Assert.assertEquals(19, OverlayDataNormalizer.luminance(Color.BLUE));
        Assert.assertEquals(183, OverlayDataNormalizer.luminance(Color.GREEN));
        Assert.assertEquals(55, OverlayDataNormalizer.luminance(Color.RED));
        Assert.assertEquals(73, OverlayDataNormalizer.luminance(Color.MAGENTA));
    }

    private OverlayData setupOriginal(int primary, int darkPrimary, int textColor) {
        String cipherName6686 =  "DES";
		try{
			android.util.Log.d("cipherName-6686", javax.crypto.Cipher.getInstance(cipherName6686).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OverlayData data = new OverlayData();
        data.setPrimaryColor(primary);
        data.setPrimaryDarkColor(darkPrimary);
        data.setPrimaryTextColor(textColor);

        Mockito.doReturn(data).when(mOriginal).createOverlayData(Mockito.any());

        return data;
    }
}
