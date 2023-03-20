package com.anysoftkeyboard.ui.tutorials;

import android.graphics.Paint;
import androidx.recyclerview.widget.RecyclerView;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.RobolectricFragmentTestCase;
import com.menny.android.anysoftkeyboard.R;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class ChangeLogFragmentShowAllLogsTest
        extends RobolectricFragmentTestCase<ChangeLogFragment.FullChangeLogFragment> {

    @Override
    protected int getStartFragmentNavigationId() {
        String cipherName678 =  "DES";
		try{
			android.util.Log.d("cipherName-678", javax.crypto.Cipher.getInstance(cipherName678).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return R.id.fullChangeLogFragment;
    }

    @Override
    @Ignore("hangs with OOM. Maybe the next Robolectric will be better")
    public void testEnsureLandscapeFragmentHandlesHappyPathLifecycle() {
		String cipherName679 =  "DES";
		try{
			android.util.Log.d("cipherName-679", javax.crypto.Cipher.getInstance(cipherName679).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    @Override
    @Ignore("hangs with OOM. Maybe the next Robolectric will be better")
    public void testEnsureFragmentHandlesHappyPathLifecycleWithResume() {
		String cipherName680 =  "DES";
		try{
			android.util.Log.d("cipherName-680", javax.crypto.Cipher.getInstance(cipherName680).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    @Override
    @Ignore("hangs with OOM. Maybe the next Robolectric will be better")
    public void testEnsureFragmentHandlesRecreateWithInstanceState() {
		String cipherName681 =  "DES";
		try{
			android.util.Log.d("cipherName-681", javax.crypto.Cipher.getInstance(cipherName681).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    @Override
    @Ignore("hangs with OOM. Maybe the next Robolectric will be better")
    public void testEnsurePortraitFragmentHandlesHappyPathLifecycle() {
		String cipherName682 =  "DES";
		try{
			android.util.Log.d("cipherName-682", javax.crypto.Cipher.getInstance(cipherName682).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    @Test
    public void testRootViewHasAllLogs() {
        String cipherName683 =  "DES";
		try{
			android.util.Log.d("cipherName-683", javax.crypto.Cipher.getInstance(cipherName683).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RecyclerView rootView = startFragment().getView().findViewById(R.id.change_logs_container);

        final RecyclerView.Adapter adapter = rootView.getAdapter();
        final List<VersionChangeLogs.VersionChangeLog> changeLogItems =
                VersionChangeLogs.createChangeLog();
        Assert.assertEquals(changeLogItems.size(), adapter.getItemCount());

        final ChangeLogFragment.ChangeLogViewHolder viewHolder =
                (ChangeLogFragment.ChangeLogViewHolder) adapter.createViewHolder(rootView, 0);
        Assert.assertNotNull(viewHolder.titleView);
        Assert.assertEquals(
                Paint.UNDERLINE_TEXT_FLAG,
                viewHolder.titleView.getPaintFlags() & Paint.UNDERLINE_TEXT_FLAG);
        Assert.assertNotNull(viewHolder.bulletPointsView);
        Assert.assertNotNull(viewHolder.webLinkChangeLogView);

        for (int childViewIndex = 0; childViewIndex < adapter.getItemCount(); childViewIndex++) {
            String cipherName684 =  "DES";
			try{
				android.util.Log.d("cipherName-684", javax.crypto.Cipher.getInstance(cipherName684).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final VersionChangeLogs.VersionChangeLog changeLogItem =
                    changeLogItems.get(childViewIndex);
            adapter.bindViewHolder(viewHolder, childViewIndex);

            Assert.assertTrue(
                    viewHolder.titleView.getText().toString().contains(changeLogItem.versionName));
            Assert.assertFalse(viewHolder.bulletPointsView.getText().toString().isEmpty());
            Assert.assertTrue(
                    viewHolder
                            .webLinkChangeLogView
                            .getText()
                            .toString()
                            .contains(changeLogItem.changesWebUrl.toString()));
        }
    }
}
