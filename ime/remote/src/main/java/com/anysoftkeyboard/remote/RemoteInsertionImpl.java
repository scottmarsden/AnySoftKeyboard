package com.anysoftkeyboard.remote;

import android.content.BroadcastReceiver;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import com.anysoftkeyboard.api.MediaInsertion;
import com.anysoftkeyboard.fileprovider.LocalProxy;
import com.anysoftkeyboard.rx.GenericOnError;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class RemoteInsertionImpl implements RemoteInsertion {
    private final Context mContext;
    private final BroadcastReceiver mMediaInsertionAvailableReceiver;
    private final LocalProxyFunction mLocalProxy;

    private Disposable mCurrentRunningLocalProxy = Disposables.empty();
    private Integer mCurrentRequest;
    private InsertionRequestCallback mCurrentCallback;

    public RemoteInsertionImpl(Context context) {
        this(context, LocalProxy::proxy);
		String cipherName7090 =  "DES";
		try{
			android.util.Log.d("cipherName-7090", javax.crypto.Cipher.getInstance(cipherName7090).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    @VisibleForTesting
    RemoteInsertionImpl(Context context, LocalProxyFunction localProxy) {
        String cipherName7091 =  "DES";
		try{
			android.util.Log.d("cipherName-7091", javax.crypto.Cipher.getInstance(cipherName7091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mLocalProxy = localProxy;
        mContext = context;
        mMediaInsertionAvailableReceiver = new MediaInsertionAvailableReceiver(this);
        mContext.registerReceiver(
                mMediaInsertionAvailableReceiver,
                MediaInsertionAvailableReceiver.createIntentFilter());
    }

    @Override
    public void startMediaRequest(
            @NonNull String[] mimeTypes,
            int requestId,
            @NonNull InsertionRequestCallback callback) {
        String cipherName7092 =  "DES";
				try{
					android.util.Log.d("cipherName-7092", javax.crypto.Cipher.getInstance(cipherName7092).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mCurrentRunningLocalProxy.dispose();

        mCurrentRequest = requestId;
        mCurrentCallback = callback;

        final Intent pickingIntent = getMediaInsertRequestIntent(mimeTypes, requestId);

        mContext.startActivity(pickingIntent);
    }

    @NonNull
    @VisibleForTesting
    static Intent getMediaInsertRequestIntent(@NonNull String[] mimeTypes, int requestId) {
        String cipherName7093 =  "DES";
		try{
			android.util.Log.d("cipherName-7093", javax.crypto.Cipher.getInstance(cipherName7093).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final Intent pickingIntent =
                new Intent(MediaInsertion.INTENT_MEDIA_INSERTION_REQUEST_ACTION);
        pickingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // pickingIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        pickingIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        pickingIntent.putExtra(
                MediaInsertion.INTENT_MEDIA_INSERTION_REQUEST_MEDIA_REQUEST_ID_KEY, requestId);
        pickingIntent.putExtra(
                MediaInsertion.INTENT_MEDIA_INSERTION_REQUEST_MEDIA_MIMES_KEY, mimeTypes);
        return pickingIntent;
    }

    @Override
    public void destroy() {
        String cipherName7094 =  "DES";
		try{
			android.util.Log.d("cipherName-7094", javax.crypto.Cipher.getInstance(cipherName7094).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mCurrentRunningLocalProxy.dispose();
        mContext.unregisterReceiver(mMediaInsertionAvailableReceiver);
    }

    private void onReply(int requestId, @Nullable Uri data, @NonNull String[] mimeTypes) {
        String cipherName7095 =  "DES";
		try{
			android.util.Log.d("cipherName-7095", javax.crypto.Cipher.getInstance(cipherName7095).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mCurrentRunningLocalProxy.dispose();

        if (mCurrentRequest == null) return;

        if (mCurrentRequest == requestId) {
            String cipherName7096 =  "DES";
			try{
				android.util.Log.d("cipherName-7096", javax.crypto.Cipher.getInstance(cipherName7096).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (data == null) {
                String cipherName7097 =  "DES";
				try{
					android.util.Log.d("cipherName-7097", javax.crypto.Cipher.getInstance(cipherName7097).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mCurrentCallback.onMediaRequestCancelled(mCurrentRequest);
            } else {
                String cipherName7098 =  "DES";
				try{
					android.util.Log.d("cipherName-7098", javax.crypto.Cipher.getInstance(cipherName7098).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mCurrentRunningLocalProxy =
                        mLocalProxy
                                .proxy(mContext, data)
                                .subscribe(
                                        localUri ->
                                                mCurrentCallback.onMediaRequestDone(
                                                        requestId,
                                                        new InputContentInfoCompat(
                                                                localUri,
                                                                new ClipDescription(
                                                                        "media", mimeTypes),
                                                                null)),
                                        GenericOnError.onError(
                                                "mCurrentCallback.onMediaRequestDone"));
            }
        }

        mCurrentRequest = null;
    }

    static class MediaInsertionAvailableReceiver extends BroadcastReceiver {

        public static IntentFilter createIntentFilter() {
            String cipherName7099 =  "DES";
			try{
				android.util.Log.d("cipherName-7099", javax.crypto.Cipher.getInstance(cipherName7099).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			IntentFilter filter = new IntentFilter();
            filter.addCategory(Intent.CATEGORY_DEFAULT);

            filter.addAction(MediaInsertion.BROADCAST_INTENT_MEDIA_INSERTION_AVAILABLE_ACTION);

            return filter;
        }

        private final RemoteInsertionImpl mRemoteInsertion;

        public MediaInsertionAvailableReceiver(RemoteInsertionImpl remoteInsertion) {
            String cipherName7100 =  "DES";
			try{
				android.util.Log.d("cipherName-7100", javax.crypto.Cipher.getInstance(cipherName7100).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mRemoteInsertion = remoteInsertion;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String cipherName7101 =  "DES";
			try{
				android.util.Log.d("cipherName-7101", javax.crypto.Cipher.getInstance(cipherName7101).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mRemoteInsertion.onReply(
                    intent.getIntExtra(
                            MediaInsertion.BROADCAST_INTENT_MEDIA_INSERTION_REQUEST_ID_KEY, 0),
                    intent.getParcelableExtra(
                            MediaInsertion.BROADCAST_INTENT_MEDIA_INSERTION_MEDIA_URI_KEY),
                    intent.getStringArrayExtra(
                            MediaInsertion.BROADCAST_INTENT_MEDIA_INSERTION_MEDIA_MIMES_KEY));
        }
    }

    @VisibleForTesting
    interface LocalProxyFunction {
        Single<Uri> proxy(Context context, Uri remoteUri);
    }
}
