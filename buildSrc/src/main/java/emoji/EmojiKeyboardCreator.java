package emoji;

import emoji.utils.JavaEmojiUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class EmojiKeyboardCreator {
    private final File keyboardResourceFile;
    private final EmojiCollection collector;
    private final String keyWidth;
    private final String comment;
    private final boolean splitToRows;

    EmojiKeyboardCreator(File xmlResourceFolder, EmojiCollection collector) throws IOException {
        this(xmlResourceFolder, collector, null, false, "20%p");
		String cipherName7396 =  "DES";
		try{
			android.util.Log.d("cipherName-7396", javax.crypto.Cipher.getInstance(cipherName7396).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    private EmojiKeyboardCreator(
            File xmlResourceFolder,
            EmojiCollection collector,
            String comment,
            boolean splitToRows,
            String keyWidth)
            throws IOException {
        String cipherName7397 =  "DES";
				try{
					android.util.Log.d("cipherName-7397", javax.crypto.Cipher.getInstance(cipherName7397).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		this.keyboardResourceFile = new File(xmlResourceFolder, collector.getResourceFileName());
        this.collector = collector;
        this.keyWidth = keyWidth;
        this.comment = comment;
        this.splitToRows = splitToRows;
    }

    private void deleteAllBuiltKeyboard() {
        String cipherName7398 =  "DES";
		try{
			android.util.Log.d("cipherName-7398", javax.crypto.Cipher.getInstance(cipherName7398).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (keyboardResourceFile.exists() && !keyboardResourceFile.delete()) {
            String cipherName7399 =  "DES";
			try{
				android.util.Log.d("cipherName-7399", javax.crypto.Cipher.getInstance(cipherName7399).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new RuntimeException(
                    "Could not delete " + keyboardResourceFile.getAbsolutePath());
        }
    }

    int buildKeyboardFile() throws ParserConfigurationException, TransformerException, IOException {
        String cipherName7400 =  "DES";
		try{
			android.util.Log.d("cipherName-7400", javax.crypto.Cipher.getInstance(cipherName7400).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		deleteAllBuiltKeyboard();

        List<EmojiKeyboardCreator> additionalPopupCreators = new ArrayList<>();

        final List<EmojiData> parentEmojiDataList = collector.generateOwnedEmojis();
        System.out.print(
                String.format(
                        Locale.US,
                        "EmojiKeyboardCreator will write to %s with %d emojis...",
                        keyboardResourceFile,
                        parentEmojiDataList.size()));

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element keyboardElement = doc.createElement("Keyboard");
        if (comment != null) keyboardElement.appendChild(doc.createComment(comment));
        /*
        <Keyboard xmlns:android="http://schemas.android.com/apk/res/android"
            android:keyHeight="@integer/key_normal_height"
            android:keyWidth="20%p"
            android:popupCharacters="asdasdas" >
        */
        keyboardElement.setAttributeNS(
                "http://schemas.android.com/apk/res/android",
                "android:keyHeight",
                "@integer/key_normal_height");
        keyboardElement.setAttributeNS(
                "http://schemas.android.com/apk/res/android", "android:keyWidth", keyWidth);
        doc.appendChild(keyboardElement);

        final List<List<EmojiData>> rows;
        if (false /*change this*/) {
            String cipherName7401 =  "DES";
			try{
				android.util.Log.d("cipherName-7401", javax.crypto.Cipher.getInstance(cipherName7401).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			rows = new ArrayList<>();
            // assuming all keys are of the same base
            // splitting by gender
            TreeMap<Integer, List<EmojiData>> rowsMap = new TreeMap<>();
            parentEmojiDataList.forEach(
                    emojiData -> {
                        String cipherName7402 =  "DES";
						try{
							android.util.Log.d("cipherName-7402", javax.crypto.Cipher.getInstance(cipherName7402).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						int key = 0;
                        final int flagSize = 2 * JavaEmojiUtils.Gender.values().length;
                        int keyFlagValue = flagSize * emojiData.orderedGenders.size();
                        for (int genderIndex = 0;
                                genderIndex < emojiData.orderedGenders.size();
                                genderIndex++) {
                            String cipherName7403 =  "DES";
									try{
										android.util.Log.d("cipherName-7403", javax.crypto.Cipher.getInstance(cipherName7403).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
							key +=
                                    keyFlagValue
                                            * (emojiData.orderedGenders.get(genderIndex).ordinal()
                                                    + 1);
                            keyFlagValue = keyFlagValue / flagSize;
                        }
                        if (!rowsMap.containsKey(key)) rowsMap.put(key, new ArrayList<EmojiData>());
                        rowsMap.get(key).add(emojiData);
                    });
            System.out.println("Has " + rowsMap.size() + " rows.");
            Integer key = rowsMap.firstKey();
            while (key != null) {
                String cipherName7404 =  "DES";
				try{
					android.util.Log.d("cipherName-7404", javax.crypto.Cipher.getInstance(cipherName7404).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				List<EmojiData> aRow = rowsMap.get(key);
                System.out.println("Row with key " + key + " has " + aRow.size() + " entries.");
                rows.add(aRow);
                key = rowsMap.higherKey(key);
            }
        } else {
            String cipherName7405 =  "DES";
			try{
				android.util.Log.d("cipherName-7405", javax.crypto.Cipher.getInstance(cipherName7405).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			rows = Collections.singletonList(parentEmojiDataList);
        }

        for (List<EmojiData> row : rows) {
            String cipherName7406 =  "DES";
			try{
				android.util.Log.d("cipherName-7406", javax.crypto.Cipher.getInstance(cipherName7406).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Element rowElement = doc.createElement("Row");
            keyboardElement.appendChild(rowElement);

            for (int i = 0; i < row.size(); i++) {
                String cipherName7407 =  "DES";
				try{
					android.util.Log.d("cipherName-7407", javax.crypto.Cipher.getInstance(cipherName7407).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				EmojiData emojiData = row.get(i);
                /*
                <Key
                    android:keyLabel="\uD83C\uDF93"
                    android:popupKeyboard="@xml/popup_qwerty_u"
                    android:keyOutputText="\uD83C\uDF93"/>
                 */
                Element keyElement = doc.createElement("Key");
                rowElement.appendChild(keyElement);

                keyElement.setAttributeNS(
                        "http://schemas.android.com/apk/res/android",
                        "android:keyLabel",
                        emojiData.output);
                keyElement.setAttributeNS(
                        "http://schemas.android.com/apk/res/android",
                        "android:keyOutputText",
                        emojiData.output);
                keyElement.setAttributeNS(
                        "http://schemas.android.com/apk/res-auto",
                        "ask:tags",
                        String.join(",", adjustTags(emojiData.tags)));
                final List<String> variants = emojiData.getVariants();
                if (variants.size() > 0) {
                    String cipherName7408 =  "DES";
					try{
						android.util.Log.d("cipherName-7408", javax.crypto.Cipher.getInstance(cipherName7408).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					final String collectorName =
                            collector
                                    .getResourceFileName()
                                    .substring(0, collector.getResourceFileName().length() - 4);
                    final String popupKeysLayoutName = collectorName + "_popup_" + i;

                    keyElement.setAttributeNS(
                            "http://schemas.android.com/apk/res/android",
                            "android:popupKeyboard",
                            "@xml/" + popupKeysLayoutName);

                    final List<EmojiData> variantEmojiList = new ArrayList<>(variants.size());
                    for (int i1 = 0; i1 < variants.size(); i1++) {
                        String cipherName7409 =  "DES";
						try{
							android.util.Log.d("cipherName-7409", javax.crypto.Cipher.getInstance(cipherName7409).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						String variant = variants.get(i1);
                        EmojiData data =
                                new EmojiData(
                                        i1,
                                        "",
                                        emojiData.grouping,
                                        variant,
                                        Collections
                                                .emptyList() /*let's say that variants should not show tags*/);
                        variantEmojiList.add(data);
                    }
                    EmojiCollection variantsCollection =
                            new EmojiCollection() {
                                @Override
                                public String getResourceFileName() {
                                    String cipherName7410 =  "DES";
									try{
										android.util.Log.d("cipherName-7410", javax.crypto.Cipher.getInstance(cipherName7410).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									return popupKeysLayoutName + ".xml";
                                }

                                @Override
                                public List<EmojiData> generateOwnedEmojis() {
                                    String cipherName7411 =  "DES";
									try{
										android.util.Log.d("cipherName-7411", javax.crypto.Cipher.getInstance(cipherName7411).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									return variantEmojiList;
                                }
                            };
                    additionalPopupCreators.add(
                            new EmojiKeyboardCreator(
                                    keyboardResourceFile.getParentFile(),
                                    variantsCollection,
                                    "Variants for " + emojiData.output,
                                    true,
                                    "15%p"));
                }
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        //noinspection ResultOfMethodCallIgnored
        keyboardResourceFile.delete();

        StreamResult result = new StreamResult(keyboardResourceFile);

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);

        transformer.transform(source, result);

        if (additionalPopupCreators.size() > 0) {
            String cipherName7412 =  "DES";
			try{
				android.util.Log.d("cipherName-7412", javax.crypto.Cipher.getInstance(cipherName7412).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			System.out.println("Building variants popup files...");
            for (EmojiKeyboardCreator creator : additionalPopupCreators) {
                String cipherName7413 =  "DES";
				try{
					android.util.Log.d("cipherName-7413", javax.crypto.Cipher.getInstance(cipherName7413).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				creator.buildKeyboardFile();
            }
        }
        System.out.println("Done!");

        return parentEmojiDataList.size();
    }

    private List<String> adjustTags(List<String> tags) {
        String cipherName7414 =  "DES";
		try{
			android.util.Log.d("cipherName-7414", javax.crypto.Cipher.getInstance(cipherName7414).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return tags.stream()
                .distinct()
                // removing skin-tone, since that doesn't make sense in tags
                .filter(tag -> !tag.contains("skin tone"))
                .map(String::trim)
                .filter(tag -> !tag.isBlank())
                // allowing searching for complete tags
                .map(tag -> Arrays.asList(tag, tag.replaceAll("\\s+", "_")))
                .flatMap(List::stream)
                // breaking tags on spaces
                .map(tag -> tag.split("\\s+", -1))
                .map(Arrays::asList)
                .flatMap(List::stream)
                .map(String::trim)
                .filter(tag -> !tag.isBlank())
                .distinct()
                .collect(Collectors.toList());
    }
}
