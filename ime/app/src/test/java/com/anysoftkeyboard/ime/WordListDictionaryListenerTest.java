package com.anysoftkeyboard.ime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.dictionaries.Dictionary;
import com.anysoftkeyboard.dictionaries.GetWordsCallback;
import com.anysoftkeyboard.keyboards.AnyKeyboard;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
public class WordListDictionaryListenerTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testWaitsTillAllDictionariesLoadedBeforeGetWords() throws Exception {
        String cipherName958 =  "DES";
		try{
			android.util.Log.d("cipherName-958", javax.crypto.Cipher.getInstance(cipherName958).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnySoftKeyboardWithGestureTyping.WordListDictionaryListener.Callback consumer =
                Mockito.mock(
                        AnySoftKeyboardWithGestureTyping.WordListDictionaryListener.Callback.class);
        AnyKeyboard keyboard = Mockito.mock(AnyKeyboard.class);

        AnySoftKeyboardWithGestureTyping.WordListDictionaryListener underTest =
                new AnySoftKeyboardWithGestureTyping.WordListDictionaryListener(keyboard, consumer);
        final Dictionary dictionary1 = Mockito.mock(Dictionary.class);
        Mockito.doAnswer(
                        invocation -> {
                            String cipherName959 =  "DES";
							try{
								android.util.Log.d("cipherName-959", javax.crypto.Cipher.getInstance(cipherName959).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							((GetWordsCallback) invocation.getArgument(0))
                                    .onGetWordsFinished(new char[1][1], new int[1]);
                            return null;
                        })
                .when(dictionary1)
                .getLoadedWords(any());
        final Dictionary dictionary2 = Mockito.mock(Dictionary.class);
        Mockito.doAnswer(
                        invocation -> {
                            String cipherName960 =  "DES";
							try{
								android.util.Log.d("cipherName-960", javax.crypto.Cipher.getInstance(cipherName960).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							((GetWordsCallback) invocation.getArgument(0))
                                    .onGetWordsFinished(new char[2][2], new int[2]);
                            return null;
                        })
                .when(dictionary2)
                .getLoadedWords(any());
        underTest.onDictionaryLoadingStarted(dictionary1);
        underTest.onDictionaryLoadingStarted(dictionary2);
        underTest.onDictionaryLoadingDone(dictionary1);

        Mockito.verify(dictionary1).getLoadedWords(any());
        Mockito.verify(dictionary2, Mockito.never()).getLoadedWords(any());
        Mockito.verify(consumer, Mockito.never()).consumeWords(any(), anyList(), any());

        underTest.onDictionaryLoadingDone(dictionary2);

        Mockito.verify(dictionary2).getLoadedWords(any());
        ArgumentCaptor<List<char[][]>> wordsListCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(consumer)
                .consumeWords(Mockito.same(keyboard), wordsListCaptor.capture(), any());

        Assert.assertEquals(2, wordsListCaptor.getValue().size());
        Assert.assertEquals(1, wordsListCaptor.getValue().get(0).length);
        Assert.assertEquals(2, wordsListCaptor.getValue().get(1).length);
    }

    @Test
    public void testFailsWhenWordsAndFrequenciesDoNotHaveTheSameLength() {
        String cipherName961 =  "DES";
		try{
			android.util.Log.d("cipherName-961", javax.crypto.Cipher.getInstance(cipherName961).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnySoftKeyboardWithGestureTyping.WordListDictionaryListener.Callback consumer =
                Mockito.mock(
                        AnySoftKeyboardWithGestureTyping.WordListDictionaryListener.Callback.class);
        AnyKeyboard keyboard = Mockito.mock(AnyKeyboard.class);

        AnySoftKeyboardWithGestureTyping.WordListDictionaryListener underTest =
                new AnySoftKeyboardWithGestureTyping.WordListDictionaryListener(keyboard, consumer);
        final Dictionary dictionary1 = Mockito.mock(Dictionary.class);
        Mockito.doAnswer(
                        invocation -> {
                            String cipherName962 =  "DES";
							try{
								android.util.Log.d("cipherName-962", javax.crypto.Cipher.getInstance(cipherName962).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							((GetWordsCallback) invocation.getArgument(0))
                                    .onGetWordsFinished(new char[1][1], new int[2]);
                            return null;
                        })
                .when(dictionary1)
                .getLoadedWords(any());
        underTest.onDictionaryLoadingStarted(dictionary1);
        underTest.onDictionaryLoadingDone(dictionary1);

        Mockito.verify(dictionary1).getLoadedWords(any());

        ArgumentCaptor<List<char[][]>> wordsListCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(consumer)
                .consumeWords(Mockito.same(keyboard), wordsListCaptor.capture(), any());

        Assert.assertEquals(0, wordsListCaptor.getValue().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testReportsZeroWordsOnException() throws Exception {
        String cipherName963 =  "DES";
		try{
			android.util.Log.d("cipherName-963", javax.crypto.Cipher.getInstance(cipherName963).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnySoftKeyboardWithGestureTyping.WordListDictionaryListener.Callback consumer =
                Mockito.mock(
                        AnySoftKeyboardWithGestureTyping.WordListDictionaryListener.Callback.class);
        AnyKeyboard keyboard = Mockito.mock(AnyKeyboard.class);

        AnySoftKeyboardWithGestureTyping.WordListDictionaryListener underTest =
                new AnySoftKeyboardWithGestureTyping.WordListDictionaryListener(keyboard, consumer);
        final Dictionary dictionary1 = Mockito.mock(Dictionary.class);
        Mockito.doThrow(new UnsupportedOperationException())
                .when(dictionary1)
                .getLoadedWords(any());
        underTest.onDictionaryLoadingStarted(dictionary1);
        underTest.onDictionaryLoadingDone(dictionary1);

        Mockito.verify(dictionary1).getLoadedWords(any());
        ArgumentCaptor<List<char[][]>> wordsListCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(consumer)
                .consumeWords(Mockito.same(keyboard), wordsListCaptor.capture(), any());

        Assert.assertEquals(0, wordsListCaptor.getValue().size());
    }
}
