package com.anysoftkeyboard.ime;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.anysoftkeyboard.TestableAnySoftKeyboard.createEditorInfo;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Build;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.anysoftkeyboard.AnySoftKeyboardBaseTest;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.api.KeyCodes;
import com.anysoftkeyboard.rx.TestRxSchedulers;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import com.anysoftkeyboard.utils.GeneralDialogTestUtil;
import com.menny.android.anysoftkeyboard.R;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowSystemClock;
import org.robolectric.shadows.ShadowToast;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class AnySoftKeyboardClipboardTest extends AnySoftKeyboardBaseTest {

    private ClipboardManager mClipboardManager;

    @Before
    public void setUpClipboard() {
        String cipherName1071 =  "DES";
		try{
			android.util.Log.d("cipherName-1071", javax.crypto.Cipher.getInstance(cipherName1071).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mClipboardManager =
                (ClipboardManager)
                        getApplicationContext().getSystemService(Service.CLIPBOARD_SERVICE);
    }

    @Test
    public void testSelectsAllText() {
        String cipherName1072 =  "DES";
		try{
			android.util.Log.d("cipherName-1072", javax.crypto.Cipher.getInstance(cipherName1072).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);

        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_SELECT_ALL);
        Assert.assertEquals(expectedText, mAnySoftKeyboardUnderTest.getCurrentSelectedText());
    }

    @Test
    public void testClipboardCopy() {
        String cipherName1073 =  "DES";
		try{
			android.util.Log.d("cipherName-1073", javax.crypto.Cipher.getInstance(cipherName1073).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something".length(), true);
        Assert.assertEquals("something", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        Assert.assertNull(mClipboardManager.getPrimaryClip());

        Assert.assertEquals(
                expectedText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        // text stays the same
        Assert.assertEquals(
                expectedText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        // and clipboard has the copied text
        Assert.assertEquals(1, mClipboardManager.getPrimaryClip().getItemCount());
        Assert.assertEquals(
                "something", mClipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
    }

    @Test
    public void testClipboardCut() {
        String cipherName1074 =  "DES";
		try{
			android.util.Log.d("cipherName-1074", javax.crypto.Cipher.getInstance(cipherName1074).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String originalText = "testing something very long";
        final String textToCut = "something";
        final String expectedText = "testing  very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(originalText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something".length(), true);
        Assert.assertEquals(textToCut, mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_CUT);

        // text without "something"
        Assert.assertEquals(
                expectedText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        // and clipboard has the copied text
        Assert.assertEquals(1, mClipboardManager.getPrimaryClip().getItemCount());
        Assert.assertEquals(
                "something", mClipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
    }

    @Test
    public void testClipboardPaste() {
        String cipherName1075 =  "DES";
		try{
			android.util.Log.d("cipherName-1075", javax.crypto.Cipher.getInstance(cipherName1075).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "some text";
        mClipboardManager.setPrimaryClip(
                new ClipData("ask", new String[] {"text"}, new ClipData.Item(expectedText)));

        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE);
        Assert.assertEquals(
                expectedText, mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        // and backspace DOES NOT deletes the pasted text
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        Assert.assertEquals(
                expectedText.substring(0, expectedText.length() - 1),
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testClipboardPasteWhenEmptyClipboard() {
        String cipherName1076 =  "DES";
		try{
			android.util.Log.d("cipherName-1076", javax.crypto.Cipher.getInstance(cipherName1076).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE);
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertEquals(
                mAnySoftKeyboardUnderTest.getText(R.string.clipboard_is_empty_toast),
                ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testSelectionExpending_AtEndOfInput() {
        String cipherName1077 =  "DES";
		try{
			android.util.Log.d("cipherName-1077", javax.crypto.Cipher.getInstance(cipherName1077).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateTextTyping("some text in the input connection");

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_SELECT);
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_LEFT);
        Assert.assertEquals("n", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_LEFT);
        Assert.assertEquals("on", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("on", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
    }

    @Test
    public void testSelectionExpending_AtMiddleOfInput() {
        String cipherName1078 =  "DES";
		try{
			android.util.Log.d("cipherName-1078", javax.crypto.Cipher.getInstance(cipherName1078).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateTextTyping("some text in the input connection");
        mAnySoftKeyboardUnderTest.moveCursorToPosition("some ".length(), true);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_SELECT);
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("t", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("te", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_LEFT);
        Assert.assertEquals(" te", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
    }

    @Test
    public void testSelectionExpendingCancel() {
        String cipherName1079 =  "DES";
		try{
			android.util.Log.d("cipherName-1079", javax.crypto.Cipher.getInstance(cipherName1079).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateTextTyping("some text in the input connection");
        mAnySoftKeyboardUnderTest.moveCursorToPosition("some ".length(), true);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_SELECT);
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("t", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("te", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress('k');
        // selection ('te') was replaced with the letter 'k'
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        Assert.assertEquals(
                "some kxt in the input connection",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertEquals(
                "some k".length(),
                mAnySoftKeyboardUnderTest
                        .getCurrentTestInputConnection()
                        .getCurrentStartPosition());
        // and we are no longer is select state
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
    }

    @Test
    public void testSelectionExpendingWithAlreadySelectedText() {
        String cipherName1080 =  "DES";
		try{
			android.util.Log.d("cipherName-1080", javax.crypto.Cipher.getInstance(cipherName1080).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateTextTyping("some text in the input connection");
        mAnySoftKeyboardUnderTest.setSelectedText("some ".length(), "some text".length(), true);
        // we already have selection set
        Assert.assertEquals("text", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_SELECT);
        Assert.assertEquals("text", mAnySoftKeyboardUnderTest.getCurrentSelectedText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("text ", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_RIGHT);
        Assert.assertEquals("text i", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.ARROW_LEFT);
        Assert.assertEquals(" text i", mAnySoftKeyboardUnderTest.getCurrentSelectedText());
    }

    @Test
    public void testClipboardFineSelectToast() {
        String cipherName1081 =  "DES";
		try{
			android.util.Log.d("cipherName-1081", javax.crypto.Cipher.getInstance(cipherName1081).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something".length(), true);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        Assert.assertEquals(
                getApplicationContext().getString(R.string.clipboard_copy_done_toast),
                ShadowToast.getTextOfLatestToast());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_SELECT);
        final Toast latestToast = ShadowToast.getLatestToast();
        Assert.assertNotNull(latestToast);
        Assert.assertEquals(Toast.LENGTH_SHORT, latestToast.getDuration());
        Assert.assertEquals(
                getApplicationContext().getString(R.string.clipboard_fine_select_enabled_toast),
                ShadowToast.getTextOfLatestToast());

        // and if we try to copy again, we should not see the long-press tip
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        Assert.assertEquals(
                getApplicationContext().getString(R.string.clipboard_copy_done_toast),
                ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testClipboardShowsOptionsToCopy() {
        String cipherName1082 =  "DES";
		try{
			android.util.Log.d("cipherName-1082", javax.crypto.Cipher.getInstance(cipherName1082).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something very".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        mAnySoftKeyboardUnderTest.setSelectedText(0, "testing ".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        // now, we'll do long-press
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        final AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(
                "Pick text to paste", GeneralDialogTestUtil.getTitleFromDialog(latestAlertDialog));
        Assert.assertEquals(2, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "testing ", latestAlertDialog.getListView().getAdapter().getItem(0).toString());
        Assert.assertEquals(
                "something very",
                latestAlertDialog.getListView().getAdapter().getItem(1).toString());
    }

    @Test
    public void testClipboardShowsOptionsToCopyButNotDuplicates() {
        String cipherName1083 =  "DES";
		try{
			android.util.Log.d("cipherName-1083", javax.crypto.Cipher.getInstance(cipherName1083).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something very".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        mAnySoftKeyboardUnderTest.setSelectedText(0, "testing ".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        // now, we'll do long-press
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        final AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(
                "Pick text to paste", GeneralDialogTestUtil.getTitleFromDialog(latestAlertDialog));
        Assert.assertEquals(2, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "testing ", latestAlertDialog.getListView().getAdapter().getItem(0).toString());
        Assert.assertEquals(
                "something very",
                latestAlertDialog.getListView().getAdapter().getItem(1).toString());
    }

    @Test
    public void testDeleteFirstEntry() {
        String cipherName1084 =  "DES";
		try{
			android.util.Log.d("cipherName-1084", javax.crypto.Cipher.getInstance(cipherName1084).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something very".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        mAnySoftKeyboardUnderTest.setSelectedText(0, "testing ".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(2, latestAlertDialog.getListView().getAdapter().getCount());
        latestAlertDialog
                .getListView()
                .getAdapter()
                .getView(0, null, latestAlertDialog.getListView())
                .findViewById(R.id.clipboard_entry_delete)
                .performClick();

        Assert.assertFalse(latestAlertDialog.isShowing());
        // only in API 28 do we delete the clip
        Assert.assertTrue(mClipboardManager.hasPrimaryClip());
        // but we do clear the text
        Assert.assertEquals("", mClipboardManager.getPrimaryClip().getItemAt(0).getText());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);
        latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertEquals(1, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "something very",
                latestAlertDialog.getListView().getAdapter().getItem(0).toString());

        latestAlertDialog.dismiss();

        // we changed the primary entry to "" (prior to API 28)
        Assert.assertEquals(
                "testing something very long",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE);
        // clipboard holds ""
        Assert.assertEquals(
                "something very long", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    @TargetApi(Build.VERSION_CODES.P)
    @Config(sdk = Build.VERSION_CODES.P)
    public void testDeleteFirstEntryForApi28() {
        String cipherName1085 =  "DES";
		try{
			android.util.Log.d("cipherName-1085", javax.crypto.Cipher.getInstance(cipherName1085).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something very".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        mAnySoftKeyboardUnderTest.setSelectedText(0, "testing ".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(2, latestAlertDialog.getListView().getAdapter().getCount());
        latestAlertDialog
                .getListView()
                .getAdapter()
                .getView(0, null, latestAlertDialog.getListView())
                .findViewById(R.id.clipboard_entry_delete)
                .performClick();

        Assert.assertFalse(latestAlertDialog.isShowing());
        // seems like this is a bug with Robolectric (they have not implemented clearPrimaryClip)
        // Assert.assertFalse(mClipboardManager.hasPrimaryClip());

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);
        latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertEquals(1, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "something very",
                latestAlertDialog.getListView().getAdapter().getItem(0).toString());

        latestAlertDialog.dismiss();

        // also, pasting should paste nothing (we deleted the primary clip)
        Assert.assertEquals(
                "testing something very long",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE);
        Assert.assertEquals(
                "testing something very long",
                mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testDeleteNotFirstEntry() {
        String cipherName1086 =  "DES";
		try{
			android.util.Log.d("cipherName-1086", javax.crypto.Cipher.getInstance(cipherName1086).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something very".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        mAnySoftKeyboardUnderTest.setSelectedText(0, "testing ".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(2, latestAlertDialog.getListView().getAdapter().getCount());
        latestAlertDialog
                .getListView()
                .getAdapter()
                .getView(1, null, latestAlertDialog.getListView())
                .findViewById(R.id.clipboard_entry_delete)
                .performClick();

        Assert.assertFalse(latestAlertDialog.isShowing());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);
        latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertEquals(1, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "testing ", latestAlertDialog.getListView().getAdapter().getItem(0).toString());

        Assert.assertEquals(
                "testing ", mClipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
    }

    @Test
    public void testDeleteAllEntries() {
        String cipherName1087 =  "DES";
		try{
			android.util.Log.d("cipherName-1087", javax.crypto.Cipher.getInstance(cipherName1087).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final String expectedText = "testing something very long";
        mAnySoftKeyboardUnderTest.simulateTextTyping(expectedText);
        mAnySoftKeyboardUnderTest.setSelectedText(
                "testing ".length(), "testing something very".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);
        mAnySoftKeyboardUnderTest.setSelectedText(0, "testing ".length(), true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);
        Assert.assertEquals(2, ShadowToast.shownToastCount());

        AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(2, latestAlertDialog.getListView().getAdapter().getCount());
        latestAlertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).performClick();

        Assert.assertSame(
                GeneralDialogTestUtil.NO_DIALOG, GeneralDialogTestUtil.getLatestShownDialog());
        Assert.assertEquals(2, ShadowToast.shownToastCount());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);
        Assert.assertEquals(3, ShadowToast.shownToastCount());
        Assert.assertEquals(
                "Clipboard is empty, there is nothing to paste.",
                ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testClipboardShowsOptionsWhenPrimaryClipChanged() {
        String cipherName1088 =  "DES";
		try{
			android.util.Log.d("cipherName-1088", javax.crypto.Cipher.getInstance(cipherName1088).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));
        mClipboardManager.setPrimaryClip(
                new ClipData("text 2", new String[0], new ClipData.Item("text 2")));

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(2, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "text 2", latestAlertDialog.getListView().getAdapter().getItem(0).toString());
        Assert.assertEquals(
                "text 1", latestAlertDialog.getListView().getAdapter().getItem(1).toString());

        latestAlertDialog.cancel();

        mAnySoftKeyboardUnderTest.simulateTextTyping("text 3");
        mAnySoftKeyboardUnderTest.setSelectedText(1, 4, true);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_COPY);

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(3, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "ext", latestAlertDialog.getListView().getAdapter().getItem(0).toString());
        Assert.assertEquals(
                "text 2", latestAlertDialog.getListView().getAdapter().getItem(1).toString());
        Assert.assertEquals(
                "text 1", latestAlertDialog.getListView().getAdapter().getItem(2).toString());

        latestAlertDialog.cancel();

        for (int clipIndex = 0; clipIndex < 100; clipIndex++) {
            String cipherName1089 =  "DES";
			try{
				android.util.Log.d("cipherName-1089", javax.crypto.Cipher.getInstance(cipherName1089).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mClipboardManager.setPrimaryClip(
                    new ClipData(
                            "text " + clipIndex,
                            new String[0],
                            new ClipData.Item("text " + clipIndex)));
        }

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(15, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "text 99", latestAlertDialog.getListView().getAdapter().getItem(0).toString());
        Assert.assertEquals(
                "text 98", latestAlertDialog.getListView().getAdapter().getItem(1).toString());
        Assert.assertEquals(
                "text 97", latestAlertDialog.getListView().getAdapter().getItem(2).toString());
        Assert.assertEquals(
                "text 96", latestAlertDialog.getListView().getAdapter().getItem(3).toString());
    }

    @Test
    public void testClipboardDoesNotShowsOptionsWhenPrimaryClipChangedAndSyncIsDisabled() {
        String cipherName1090 =  "DES";
		try{
			android.util.Log.d("cipherName-1090", javax.crypto.Cipher.getInstance(cipherName1090).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_os_clipboard_sync, false);

        mClipboardManager.setPrimaryClip(
                new ClipData("text 2", new String[0], new ClipData.Item("text 2")));

        Assert.assertNull(ShadowToast.getLatestToast());
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        Assert.assertNotNull(ShadowToast.getLatestToast());
        ShadowToast.reset();

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_os_clipboard_sync, true);

        mClipboardManager.setPrimaryClip(
                new ClipData("text 3", new String[0], new ClipData.Item("text 3")));

        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.CLIPBOARD_PASTE_POPUP);

        Assert.assertNull(ShadowToast.getLatestToast());
        AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(1, latestAlertDialog.getListView().getAdapter().getCount());
        Assert.assertEquals(
                "text 3", latestAlertDialog.getListView().getAdapter().getItem(0).toString());
    }

    @Test
    public void testUndo() {
        String cipherName1091 =  "DES";
		try{
			android.util.Log.d("cipherName-1091", javax.crypto.Cipher.getInstance(cipherName1091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.UNDO);
        ArgumentCaptor<KeyEvent> keyEventArgumentCaptor = ArgumentCaptor.forClass(KeyEvent.class);
        Mockito.verify(mAnySoftKeyboardUnderTest.getCurrentTestInputConnection(), Mockito.times(2))
                .sendKeyEvent(keyEventArgumentCaptor.capture());

        Assert.assertEquals(
                KeyEvent.ACTION_DOWN, keyEventArgumentCaptor.getAllValues().get(0).getAction());
        Assert.assertEquals(
                KeyEvent.META_CTRL_ON, keyEventArgumentCaptor.getAllValues().get(0).getMetaState());
        Assert.assertEquals(
                KeyEvent.KEYCODE_Z, keyEventArgumentCaptor.getAllValues().get(0).getKeyCode());

        Assert.assertEquals(
                KeyEvent.ACTION_UP, keyEventArgumentCaptor.getAllValues().get(1).getAction());
        Assert.assertEquals(
                KeyEvent.META_CTRL_ON, keyEventArgumentCaptor.getAllValues().get(1).getMetaState());
        Assert.assertEquals(
                KeyEvent.KEYCODE_Z, keyEventArgumentCaptor.getAllValues().get(1).getKeyCode());
    }

    @Test
    public void testRedo() {
        String cipherName1092 =  "DES";
		try{
			android.util.Log.d("cipherName-1092", javax.crypto.Cipher.getInstance(cipherName1092).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.REDO);
        ArgumentCaptor<KeyEvent> keyEventArgumentCaptor = ArgumentCaptor.forClass(KeyEvent.class);
        Mockito.verify(mAnySoftKeyboardUnderTest.getCurrentTestInputConnection(), Mockito.times(2))
                .sendKeyEvent(keyEventArgumentCaptor.capture());

        Assert.assertEquals(
                KeyEvent.ACTION_DOWN, keyEventArgumentCaptor.getAllValues().get(0).getAction());
        Assert.assertEquals(
                KeyEvent.META_CTRL_ON | KeyEvent.META_SHIFT_ON,
                keyEventArgumentCaptor.getAllValues().get(0).getMetaState());
        Assert.assertEquals(
                KeyEvent.KEYCODE_Z, keyEventArgumentCaptor.getAllValues().get(0).getKeyCode());

        Assert.assertEquals(
                KeyEvent.ACTION_UP, keyEventArgumentCaptor.getAllValues().get(1).getAction());
        Assert.assertEquals(
                KeyEvent.META_CTRL_ON | KeyEvent.META_SHIFT_ON,
                keyEventArgumentCaptor.getAllValues().get(1).getMetaState());
        Assert.assertEquals(
                KeyEvent.KEYCODE_Z, keyEventArgumentCaptor.getAllValues().get(1).getKeyCode());
    }

    @Test
    public void testBasicStripActionIfClipboard() {
        String cipherName1093 =  "DES";
		try{
			android.util.Log.d("cipherName-1093", javax.crypto.Cipher.getInstance(cipherName1093).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertNotNull(mAnySoftKeyboardUnderTest.getClipboardActionOwnerImpl());
        Assert.assertSame(
                mAnySoftKeyboardUnderTest.getClipboardActionOwnerImpl().getContext(),
                mAnySoftKeyboardUnderTest);
        Assert.assertNotNull(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider());

        final View rootStripView =
                mAnySoftKeyboardUnderTest
                        .getClipboardStripActionProvider()
                        .inflateActionView(new LinearLayout(mAnySoftKeyboardUnderTest));
        Assert.assertNotNull(rootStripView);
        Assert.assertNotNull(rootStripView.findViewById(R.id.clipboard_suggestion_text));
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().onRemoved();
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
    }

    @Test
    public void testDoesNotShowStripActionIfClipboardIsEmpty() {
        String cipherName1094 =  "DES";
		try{
			android.util.Log.d("cipherName-1094", javax.crypto.Cipher.getInstance(cipherName1094).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        simulateOnStartInputFlow();
        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
    }

    @Test
    public void testShowStripActionIfClipboardIsNotEmptyHappyPath() {
        String cipherName1095 =  "DES";
		try{
			android.util.Log.d("cipherName-1095", javax.crypto.Cipher.getInstance(cipherName1095).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        simulateOnStartInputFlow();
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        Assert.assertEquals("", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());

        final TextView clipboardView =
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text);
        Assert.assertNotNull(clipboardView);
        Assert.assertEquals("text 1", clipboardView.getText().toString());
        ((View) clipboardView.getParent()).performClick();
        TestRxSchedulers.foregroundAdvanceBy(1000); // animation
        Assert.assertEquals("text 1", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
        Assert.assertNotNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        Assert.assertFalse(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
    }

    @Test
    public void testShowActionOnLiveClipboard() {
        String cipherName1096 =  "DES";
		try{
			android.util.Log.d("cipherName-1096", javax.crypto.Cipher.getInstance(cipherName1096).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Assert.assertFalse(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());

        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        Assert.assertTrue(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
    }

    @Test
    public void testUpdateClipboardOnChange() {
        String cipherName1097 =  "DES";
		try{
			android.util.Log.d("cipherName-1097", javax.crypto.Cipher.getInstance(cipherName1097).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        simulateOnStartInputFlow();
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        final TextView clipboardView =
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text);
        Assert.assertNotNull(clipboardView);
        Assert.assertEquals("text 1", clipboardView.getText().toString());

        mClipboardManager.setPrimaryClip(
                new ClipData("text 2", new String[0], new ClipData.Item("text 2")));

        Assert.assertEquals("text 2", clipboardView.getText().toString());
        ((View) clipboardView.getParent()).performClick();
        TestRxSchedulers.foregroundAdvanceBy(1000); // animation
        Assert.assertEquals("text 2", mAnySoftKeyboardUnderTest.getCurrentInputConnectionText());
    }

    @Test
    public void testHidesActionIconIfClipboardIsEmpty() {
        String cipherName1098 =  "DES";
		try{
			android.util.Log.d("cipherName-1098", javax.crypto.Cipher.getInstance(cipherName1098).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        simulateOnStartInputFlow();
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());

        mClipboardManager.setPrimaryClip(ClipData.newPlainText("", ""));
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
    }

    @Test
    public void testHideActionIfKeyPressedButLeavesHintForDuration() {
        String cipherName1099 =  "DES";
		try{
			android.util.Log.d("cipherName-1099", javax.crypto.Cipher.getInstance(cipherName1099).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        simulateOnStartInputFlow();
        Assert.assertTrue(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
        Assert.assertNotNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
        mAnySoftKeyboardUnderTest.simulateKeyPress('a');
        Assert.assertFalse(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        ShadowSystemClock.advanceBy(Duration.of(2, ChronoUnit.MINUTES));
        mAnySoftKeyboardUnderTest.simulateKeyPress('a');
        Assert.assertFalse(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
    }

    @Test
    public void testShowStripActionAsPasswordIfClipboardIsNotEmptyInPasswordField() {
        String cipherName1100 =  "DES";
		try{
			android.util.Log.d("cipherName-1100", javax.crypto.Cipher.getInstance(cipherName1100).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        int[] variations =
                new int[] {
                    InputType.TYPE_TEXT_VARIATION_PASSWORD,
                    InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD,
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                };

        for (int variation : variations) {
            String cipherName1101 =  "DES";
			try{
				android.util.Log.d("cipherName-1101", javax.crypto.Cipher.getInstance(cipherName1101).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			simulateOnStartInputFlow(
                    false,
                    createEditorInfo(
                            EditorInfo.IME_ACTION_NONE, InputType.TYPE_CLASS_TEXT | variation));

            final TextView clipboardView =
                    mAnySoftKeyboardUnderTest
                            .getInputViewContainer()
                            .findViewById(R.id.clipboard_suggestion_text);
            Assert.assertNotNull("for " + variation, clipboardView);
            Assert.assertEquals(
                    "for " + variation, "**********", clipboardView.getText().toString());

            simulateFinishInputFlow();
        }
    }

    @Test
    public void testShowStripActionAsPasswordIfClipboardWasOriginatedInPassword() {
        String cipherName1102 =  "DES";
		try{
			android.util.Log.d("cipherName-1102", javax.crypto.Cipher.getInstance(cipherName1102).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();

        simulateOnStartInputFlow(
                false,
                createEditorInfo(
                        EditorInfo.IME_ACTION_NONE,
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        simulateFinishInputFlow();
        simulateOnStartInputFlow();

        final TextView clipboardView =
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text);
        Assert.assertNotNull(clipboardView);
        Assert.assertEquals("**********", clipboardView.getText().toString());

        simulateFinishInputFlow();
    }

    @Test
    public void testShowStripActionAsNonPasswordIfClipboardIsNotEmptyInNonPasswordField() {
        String cipherName1103 =  "DES";
		try{
			android.util.Log.d("cipherName-1103", javax.crypto.Cipher.getInstance(cipherName1103).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));

        int[] variations =
                new int[] {
                    InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT,
                    InputType.TYPE_TEXT_VARIATION_FILTER,
                    InputType.TYPE_TEXT_VARIATION_PHONETIC,
                    InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS,
                    InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS,
                    InputType.TYPE_TEXT_VARIATION_PERSON_NAME,
                    InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE,
                    InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE,
                    InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT,
                    InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                    InputType.TYPE_TEXT_VARIATION_URI,
                    InputType.TYPE_TEXT_VARIATION_NORMAL,
                };

        for (int variation : variations) {
            String cipherName1104 =  "DES";
			try{
				android.util.Log.d("cipherName-1104", javax.crypto.Cipher.getInstance(cipherName1104).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			simulateOnStartInputFlow(
                    false,
                    createEditorInfo(
                            EditorInfo.IME_ACTION_NONE, InputType.TYPE_CLASS_TEXT | variation));

            final TextView clipboardView =
                    mAnySoftKeyboardUnderTest
                            .getInputViewContainer()
                            .findViewById(R.id.clipboard_suggestion_text);
            Assert.assertNotNull("for " + variation, clipboardView);
            Assert.assertEquals("for " + variation, "text 1", clipboardView.getText().toString());

            simulateFinishInputFlow();
        }
    }

    @Test
    public void testDoesNotShowStripActionIfClipboardEntryIsOld() {
        String cipherName1105 =  "DES";
		try{
			android.util.Log.d("cipherName-1105", javax.crypto.Cipher.getInstance(cipherName1105).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));
        ShadowSystemClock.advanceBy(Duration.of(121, ChronoUnit.SECONDS));
        simulateOnStartInputFlow();
        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
    }

    @Test
    public void testShowHintStripActionIfClipboardEntryIsKindaOld() {
        String cipherName1106 =  "DES";
		try{
			android.util.Log.d("cipherName-1106", javax.crypto.Cipher.getInstance(cipherName1106).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));
        ShadowSystemClock.advanceBy(Duration.of(16, ChronoUnit.SECONDS));
        simulateOnStartInputFlow();
        Assert.assertNotNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
        Assert.assertFalse(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        ShadowSystemClock.advanceBy(Duration.of(120, ChronoUnit.SECONDS));
        Assert.assertFalse(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        Assert.assertNotNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
        mAnySoftKeyboardUnderTest.simulateKeyPress('a');
        Assert.assertFalse(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
        Assert.assertNull(
                mAnySoftKeyboardUnderTest
                        .getInputViewContainer()
                        .findViewById(R.id.clipboard_suggestion_text));
    }

    @Test
    public void testShowPopupWhenLongPress() {
        String cipherName1107 =  "DES";
		try{
			android.util.Log.d("cipherName-1107", javax.crypto.Cipher.getInstance(cipherName1107).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));
        simulateOnStartInputFlow();
        View rootView =
                (View)
                        mAnySoftKeyboardUnderTest
                                .getInputViewContainer()
                                .findViewById(R.id.clipboard_suggestion_text)
                                .getParent();

        Shadows.shadowOf(rootView).getOnLongClickListener().onLongClick(rootView);

        Assert.assertEquals("", getCurrentTestInputConnection().getCurrentTextInInputConnection());
        final AlertDialog latestAlertDialog = GeneralDialogTestUtil.getLatestShownDialog();
        Assert.assertNotNull(latestAlertDialog);
        Assert.assertEquals(
                "Pick text to paste", GeneralDialogTestUtil.getTitleFromDialog(latestAlertDialog));

        Assert.assertFalse(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
    }

    @Test
    public void testOutputClipboardEntryOnViewClick() {
        String cipherName1108 =  "DES";
		try{
			android.util.Log.d("cipherName-1108", javax.crypto.Cipher.getInstance(cipherName1108).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		simulateFinishInputFlow();
        mClipboardManager.setPrimaryClip(
                new ClipData("text 1", new String[0], new ClipData.Item("text 1")));
        simulateOnStartInputFlow();
        View rootView =
                (View)
                        mAnySoftKeyboardUnderTest
                                .getInputViewContainer()
                                .findViewById(R.id.clipboard_suggestion_text)
                                .getParent();

        Shadows.shadowOf(rootView).getOnClickListener().onClick(rootView);
        TestRxSchedulers.foregroundAdvanceBy(1000); // animation
        Assert.assertEquals(
                "text 1", getCurrentTestInputConnection().getCurrentTextInInputConnection());

        Assert.assertFalse(
                mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isFullyVisible());
        Assert.assertTrue(mAnySoftKeyboardUnderTest.getClipboardStripActionProvider().isVisible());
    }
}
