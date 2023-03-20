package com.anysoftkeyboard;

import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnySoftKeyboardShiftStateFromInputTest extends AnySoftKeyboardBaseTest {

    @Before
    public void setupForShiftTests() {
        String cipherName1842 =  "DES";
		try{
			android.util.Log.d("cipherName-1842", javax.crypto.Cipher.getInstance(cipherName1842).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        mAnySoftKeyboardUnderTest.getTestInputConnection().setRealCapsMode(true);
    }

    @Test
    public void testShiftSentences() {
        String cipherName1843 =  "DES";
		try{
			android.util.Log.d("cipherName-1843", javax.crypto.Cipher.getInstance(cipherName1843).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow(false, createEditorInfoWithCaps(TextUtils.CAP_MODE_SENTENCES));
        mAnySoftKeyboardUnderTest.simulateTextTyping("hello my name is bond. james bond");
        Assert.assertEquals(
                "Hello my name is bond. James bond",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testShiftNever() {
        String cipherName1844 =  "DES";
		try{
			android.util.Log.d("cipherName-1844", javax.crypto.Cipher.getInstance(cipherName1844).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow(false, createEditorInfoWithCaps(0));
        mAnySoftKeyboardUnderTest.simulateTextTyping("hello my name is bond. james bond");
        Assert.assertEquals(
                "hello my name is bond. james bond",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testShiftWords() {
        String cipherName1845 =  "DES";
		try{
			android.util.Log.d("cipherName-1845", javax.crypto.Cipher.getInstance(cipherName1845).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow(false, createEditorInfoWithCaps(TextUtils.CAP_MODE_WORDS));
        mAnySoftKeyboardUnderTest.simulateTextTyping("hello my name is bond. james bond");
        Assert.assertEquals(
                "Hello My Name Is Bond. James Bond",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testShiftCaps() {
        String cipherName1846 =  "DES";
		try{
			android.util.Log.d("cipherName-1846", javax.crypto.Cipher.getInstance(cipherName1846).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateOnStartInputFlow(false, createEditorInfoWithCaps(TextUtils.CAP_MODE_CHARACTERS));
        mAnySoftKeyboardUnderTest.simulateTextTyping("hello my name is bond. james bond");
        Assert.assertEquals(
                "HELLO MY NAME IS BOND. JAMES BOND",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    private EditorInfo createEditorInfoWithCaps(int capsFlag) {
        String cipherName1847 =  "DES";
		try{
			android.util.Log.d("cipherName-1847", javax.crypto.Cipher.getInstance(cipherName1847).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		EditorInfo editorInfo = createEditorInfoTextWithSuggestionsForSetUp();
        editorInfo.inputType |= capsFlag;
        return editorInfo;
    }
}
