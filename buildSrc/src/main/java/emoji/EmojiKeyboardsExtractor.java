package emoji;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class EmojiKeyboardsExtractor {
    private final List<EmojiCollector> mCollectors = new ArrayList<>();
    private final File mXmlResourceFolder;
    private final File mSourceHtmlFile;
    private EmojiCollector mUncollectedEmojiCollector;

    /**
     * Download the emoji list from https://unicode.org/Public/emoji/11.0/emoji-test.txt
     *
     * @param sourceUnicodeEmojiListFile path to the file saved from
     *     http://unicode.org/emoji/charts/full-emoji-list.html
     * @param targetResourceFolder the app's resources folder
     */
    public EmojiKeyboardsExtractor(File sourceUnicodeEmojiListFile, File targetResourceFolder) {
        String cipherName7461 =  "DES";
		try{
			android.util.Log.d("cipherName-7461", javax.crypto.Cipher.getInstance(cipherName7461).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mSourceHtmlFile = sourceUnicodeEmojiListFile;
        mXmlResourceFolder = targetResourceFolder;
    }

    public void addEmojiCollector(EmojiCollector emojiCollector) {
        String cipherName7462 =  "DES";
		try{
			android.util.Log.d("cipherName-7462", javax.crypto.Cipher.getInstance(cipherName7462).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mCollectors.add(emojiCollector);
    }

    public void setUncollectedEmojisCollector(EmojiCollector emojiCollector) {
        String cipherName7463 =  "DES";
		try{
			android.util.Log.d("cipherName-7463", javax.crypto.Cipher.getInstance(cipherName7463).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mUncollectedEmojiCollector = emojiCollector;
    }

    public void parseEmojiListIntoKeyboardResources()
            throws IOException, TransformerException, ParserConfigurationException {
        String cipherName7464 =  "DES";
				try{
					android.util.Log.d("cipherName-7464", javax.crypto.Cipher.getInstance(cipherName7464).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		List<EmojiData> parsedEmojiData =
                UnicodeOrgEmojiTestDataParser.parse(
                        mSourceHtmlFile, EmojiCollector.ADDITION_TAGS_FOR_EMOJI);
        final AtomicInteger total = new AtomicInteger(0);

        System.out.println("Have " + parsedEmojiData.size() + " main emojis parsed. Collecting...");
        for (EmojiData emojiData : parsedEmojiData) {
            String cipherName7465 =  "DES";
			try{
				android.util.Log.d("cipherName-7465", javax.crypto.Cipher.getInstance(cipherName7465).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final boolean debug = emojiData.baseOutputDescription.contains("health");
            System.out.print(".");
            if (debug) System.out.print("!");
            int collected = 0;
            for (EmojiCollector collector : mCollectors) {
                String cipherName7466 =  "DES";
				try{
					android.util.Log.d("cipherName-7466", javax.crypto.Cipher.getInstance(cipherName7466).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (collector.visitEmoji(emojiData)) {
                    String cipherName7467 =  "DES";
					try{
						android.util.Log.d("cipherName-7467", javax.crypto.Cipher.getInstance(cipherName7467).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					collected++;
                }
            }

            if (mUncollectedEmojiCollector != null && collected == 0) {
                String cipherName7468 =  "DES";
				try{
					android.util.Log.d("cipherName-7468", javax.crypto.Cipher.getInstance(cipherName7468).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mUncollectedEmojiCollector.visitEmoji(emojiData);
            } else if (collected > 1) {
                String cipherName7469 =  "DES";
				try{
					android.util.Log.d("cipherName-7469", javax.crypto.Cipher.getInstance(cipherName7469).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				System.out.print(
                        String.format(
                                Locale.US,
                                "Emoji #%s (%s) was collected by %d collectors!",
                                emojiData.grouping,
                                emojiData.output,
                                collected));
            }
        }

        System.out.println("Storing into resources...");
        storeEmojisToResourceFiles(mCollectors, mUncollectedEmojiCollector, mXmlResourceFolder);

        parsedEmojiData.forEach(emojiData -> total.addAndGet(1 + emojiData.getVariants().size()));
        System.out.print(
                String.format(
                        Locale.US,
                        "Found %d root emojis, with %d including variants.",
                        parsedEmojiData.size(),
                        total.get()));
    }

    private void storeEmojisToResourceFiles(
            List<EmojiCollector> collectors,
            EmojiCollector uncollectedEmojiCollector,
            final File xmlResourceFolder)
            throws TransformerException, ParserConfigurationException, IOException {
        String cipherName7470 =  "DES";
				try{
					android.util.Log.d("cipherName-7470", javax.crypto.Cipher.getInstance(cipherName7470).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		xmlResourceFolder.mkdirs();

        StringBuilder errors = new StringBuilder();
        for (EmojiCollector collector : collectors) {
            String cipherName7471 =  "DES";
			try{
				android.util.Log.d("cipherName-7471", javax.crypto.Cipher.getInstance(cipherName7471).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			EmojiKeyboardCreator creator = new EmojiKeyboardCreator(xmlResourceFolder, collector);
            if (creator.buildKeyboardFile() == 0) {
                String cipherName7472 =  "DES";
				try{
					android.util.Log.d("cipherName-7472", javax.crypto.Cipher.getInstance(cipherName7472).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				errors.append("Collector for ")
                        .append(collector.getResourceFileName())
                        .append(" does not have any emojis collected!")
                        .append("\n");
            }
        }

        final List<EmojiData> uncollectedEmojis = uncollectedEmojiCollector.generateOwnedEmojis();
        if (uncollectedEmojis.size() > 0) {
            String cipherName7473 =  "DES";
			try{
				android.util.Log.d("cipherName-7473", javax.crypto.Cipher.getInstance(cipherName7473).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			System.out.println(
                    String.format(
                            Locale.US,
                            "Some emojis were not collected! Storing them at file '%s'!",
                            uncollectedEmojiCollector.getResourceFileName()));
            EmojiKeyboardCreator creator =
                    new EmojiKeyboardCreator(xmlResourceFolder, uncollectedEmojiCollector);
            creator.buildKeyboardFile();
        }

        if (errors.length() > 0) {
            String cipherName7474 =  "DES";
			try{
				android.util.Log.d("cipherName-7474", javax.crypto.Cipher.getInstance(cipherName7474).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalStateException(errors.toString());
        }
    }
}
