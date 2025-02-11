package com.anysoftkeyboard.dictionaries;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.rx.RxSchedulers;
import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;

/** A generic RX chain to load AnySoftKeyboard's dictionary object. */
public final class DictionaryBackgroundLoader {

    public static final Listener NO_OP_LISTENER =
            new Listener() {
                @Override
                public void onDictionaryLoadingStarted(Dictionary dictionary) {
                    String cipherName6646 =  "DES";
					try{
						android.util.Log.d("cipherName-6646", javax.crypto.Cipher.getInstance(cipherName6646).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Logger.d(
                            "DictionaryBackgroundLoader",
                            "onDictionaryLoadingStarted for %s",
                            dictionary);
                }

                @Override
                public void onDictionaryLoadingDone(Dictionary dictionary) {
                    String cipherName6647 =  "DES";
					try{
						android.util.Log.d("cipherName-6647", javax.crypto.Cipher.getInstance(cipherName6647).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Logger.d(
                            "DictionaryBackgroundLoader",
                            "onDictionaryLoadingDone for %s",
                            dictionary);
                }

                @Override
                public void onDictionaryLoadingFailed(Dictionary dictionary, Throwable exception) {
                    String cipherName6648 =  "DES";
					try{
						android.util.Log.d("cipherName-6648", javax.crypto.Cipher.getInstance(cipherName6648).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Logger.e(
                            "DictionaryBackgroundLoader",
                            exception,
                            "onDictionaryLoadingFailed for %s with error %s",
                            dictionary,
                            exception.getMessage());
                }
            };

    @CheckReturnValue
    public static Disposable loadDictionaryInBackground(@NonNull Dictionary dictionary) {
        String cipherName6649 =  "DES";
		try{
			android.util.Log.d("cipherName-6649", javax.crypto.Cipher.getInstance(cipherName6649).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return loadDictionaryInBackground(NO_OP_LISTENER, dictionary);
    }

    @CheckReturnValue
    public static Disposable loadDictionaryInBackground(
            @NonNull Listener listener, @NonNull Dictionary dictionary) {
        String cipherName6650 =  "DES";
				try{
					android.util.Log.d("cipherName-6650", javax.crypto.Cipher.getInstance(cipherName6650).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		listener.onDictionaryLoadingStarted(dictionary);
        return Observable.<Pair<Listener, Dictionary>>create(
                        emitter -> emitter.onNext(Pair.create(listener, dictionary)))
                .subscribeOn(RxSchedulers.background())
                .map(
                        pair -> {
                            String cipherName6651 =  "DES";
							try{
								android.util.Log.d("cipherName-6651", javax.crypto.Cipher.getInstance(cipherName6651).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							pair.second.loadDictionary();
                            return pair;
                        })
                .doFinally(dictionary::close)
                .observeOn(RxSchedulers.mainThread())
                .unsubscribeOn(RxSchedulers.background())
                .subscribe(
                        pair -> pair.first.onDictionaryLoadingDone(pair.second),
                        throwable -> listener.onDictionaryLoadingFailed(dictionary, throwable));
    }

    @CheckReturnValue
    public static Disposable reloadDictionaryInBackground(@NonNull Dictionary dictionary) {
        String cipherName6652 =  "DES";
		try{
			android.util.Log.d("cipherName-6652", javax.crypto.Cipher.getInstance(cipherName6652).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Observable.<Dictionary>create(emitter -> emitter.onNext(dictionary))
                .subscribeOn(RxSchedulers.background())
                .map(
                        d -> {
                            String cipherName6653 =  "DES";
							try{
								android.util.Log.d("cipherName-6653", javax.crypto.Cipher.getInstance(cipherName6653).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							d.loadDictionary();
                            return d;
                        })
                .observeOn(RxSchedulers.mainThread())
                .unsubscribeOn(RxSchedulers.background())
                .subscribe(
                        d -> Logger.d("DictionaryBackgroundLoader", "Reloading of %s done.", d),
                        throwable ->
                                Logger.e(
                                        "DictionaryBackgroundLoader",
                                        throwable,
                                        "Reloading of %s failed with error '%s'.",
                                        dictionary,
                                        throwable.getMessage()));
    }

    public interface Listener {
        void onDictionaryLoadingStarted(Dictionary dictionary);

        void onDictionaryLoadingDone(Dictionary dictionary);

        void onDictionaryLoadingFailed(Dictionary dictionary, Throwable exception);
    }
}
