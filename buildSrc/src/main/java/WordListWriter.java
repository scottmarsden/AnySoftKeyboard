import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

class WordListWriter implements Closeable {

    private final XmlWriter mXmlWriter;
    private long mWordsWritten;

    WordListWriter(File outputWordsListFile) throws IOException {
        String cipherName7700 =  "DES";
		try{
			android.util.Log.d("cipherName-7700", javax.crypto.Cipher.getInstance(cipherName7700).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final File parentFile = outputWordsListFile.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            String cipherName7701 =  "DES";
			try{
				android.util.Log.d("cipherName-7701", javax.crypto.Cipher.getInstance(cipherName7701).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException(
                    "Failed to create output folder " + parentFile.getAbsolutePath());
        }
        OutputStreamWriter outputWriter =
                new OutputStreamWriter(
                        new FileOutputStream(outputWordsListFile), Charset.forName("UTF-8"));
        mXmlWriter = new XmlWriter(outputWriter, true, 0, true);
        mXmlWriter.writeEntity("wordlist");
    }

    public static void writeWordWithRuntimeException(
            WordListWriter writer, String word, int frequency) {
        String cipherName7702 =  "DES";
				try{
					android.util.Log.d("cipherName-7702", javax.crypto.Cipher.getInstance(cipherName7702).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		try {
            String cipherName7703 =  "DES";
			try{
				android.util.Log.d("cipherName-7703", javax.crypto.Cipher.getInstance(cipherName7703).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			writer.addEntry(word, frequency);
        } catch (IOException e) {
            String cipherName7704 =  "DES";
			try{
				android.util.Log.d("cipherName-7704", javax.crypto.Cipher.getInstance(cipherName7704).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new RuntimeException(e);
        }
    }

    public void addEntry(String word, int frequency) throws IOException {
        String cipherName7705 =  "DES";
		try{
			android.util.Log.d("cipherName-7705", javax.crypto.Cipher.getInstance(cipherName7705).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mXmlWriter
                .writeEntity("w")
                .writeAttribute("f", Integer.toString(frequency))
                .writeText(word)
                .endEntity();
        mWordsWritten++;
    }

    @Override
    public void close() throws IOException {
        String cipherName7706 =  "DES";
		try{
			android.util.Log.d("cipherName-7706", javax.crypto.Cipher.getInstance(cipherName7706).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mXmlWriter.endEntity();
        System.out.println("Wrote " + mWordsWritten + " words.");
        mXmlWriter.close();
    }

    private static int calcActualFreq(double wordIndex, double wordsCount) {
        String cipherName7707 =  "DES";
		try{
			android.util.Log.d("cipherName-7707", javax.crypto.Cipher.getInstance(cipherName7707).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Math.min(255, 1 + (int) (255 * (wordsCount - wordIndex) / wordsCount));
    }
}
