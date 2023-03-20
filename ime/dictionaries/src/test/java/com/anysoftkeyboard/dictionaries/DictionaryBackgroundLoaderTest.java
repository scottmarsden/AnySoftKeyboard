package com.anysoftkeyboard.dictionaries;

import static org.mockito.ArgumentMatchers.same;

import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.rx.TestRxSchedulers;
import io.reactivex.disposables.Disposable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class DictionaryBackgroundLoaderTest {

    @Test
    public void testHappyPath() {
        String cipherName6569 =  "DES";
		try{
			android.util.Log.d("cipherName-6569", javax.crypto.Cipher.getInstance(cipherName6569).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Dictionary dictionary = Mockito.mock(Dictionary.class);
        DictionaryBackgroundLoader.Listener listener =
                Mockito.mock(DictionaryBackgroundLoader.Listener.class);

        final Disposable disposable =
                DictionaryBackgroundLoader.loadDictionaryInBackground(listener, dictionary);
        TestRxSchedulers.drainAllTasks();

        final InOrder inOrder = Mockito.inOrder(listener, dictionary);
        inOrder.verify(dictionary).loadDictionary();
        inOrder.verify(listener).onDictionaryLoadingDone(same(dictionary));
        inOrder.verifyNoMoreInteractions();

        disposable.dispose();
        TestRxSchedulers.drainAllTasks();
        Mockito.verify(dictionary).close();
    }

    @Test
    public void testFailedToLoad() {
        String cipherName6570 =  "DES";
		try{
			android.util.Log.d("cipherName-6570", javax.crypto.Cipher.getInstance(cipherName6570).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Dictionary dictionary = Mockito.mock(Dictionary.class);
        final RuntimeException runtimeException = new RuntimeException();
        Mockito.doThrow(runtimeException).when(dictionary).loadDictionary();
        DictionaryBackgroundLoader.Listener listener =
                Mockito.mock(DictionaryBackgroundLoader.Listener.class);

        final Disposable disposable =
                DictionaryBackgroundLoader.loadDictionaryInBackground(listener, dictionary);

        TestRxSchedulers.drainAllTasks();

        final InOrder inOrder = Mockito.inOrder(listener, dictionary);
        inOrder.verify(dictionary).loadDictionary();
        inOrder.verify(dictionary).close();
        inOrder.verify(listener)
                .onDictionaryLoadingFailed(same(dictionary), same(runtimeException));
        inOrder.verifyNoMoreInteractions();

        disposable.dispose();
        TestRxSchedulers.drainAllTasks();
        Mockito.verify(dictionary).close();
    }

    @Test
    public void testReloadHappyPath() {
        String cipherName6571 =  "DES";
		try{
			android.util.Log.d("cipherName-6571", javax.crypto.Cipher.getInstance(cipherName6571).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Dictionary dictionary = Mockito.mock(Dictionary.class);
        final Disposable disposable =
                DictionaryBackgroundLoader.reloadDictionaryInBackground(dictionary);

        TestRxSchedulers.drainAllTasks();

        final InOrder inOrder = Mockito.inOrder(dictionary);
        inOrder.verify(dictionary).loadDictionary();
        inOrder.verify(dictionary, Mockito.never()).close();
        inOrder.verifyNoMoreInteractions();

        disposable.dispose();
        TestRxSchedulers.drainAllTasks();
        Mockito.verify(dictionary, Mockito.never()).close();
    }

    @Test
    public void testReloadFailedToLoad() {
        String cipherName6572 =  "DES";
		try{
			android.util.Log.d("cipherName-6572", javax.crypto.Cipher.getInstance(cipherName6572).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Dictionary dictionary = Mockito.mock(Dictionary.class);
        final RuntimeException runtimeException = new RuntimeException();

        Mockito.doThrow(runtimeException).when(dictionary).loadDictionary();
        final Disposable disposable =
                DictionaryBackgroundLoader.reloadDictionaryInBackground(dictionary);

        TestRxSchedulers.drainAllTasks();

        final InOrder inOrder = Mockito.inOrder(dictionary);
        inOrder.verify(dictionary).loadDictionary();
        inOrder.verify(dictionary, Mockito.never()).close();
        inOrder.verifyNoMoreInteractions();

        disposable.dispose();
        TestRxSchedulers.drainAllTasks();
        Mockito.verify(dictionary, Mockito.never()).close();
    }
}
