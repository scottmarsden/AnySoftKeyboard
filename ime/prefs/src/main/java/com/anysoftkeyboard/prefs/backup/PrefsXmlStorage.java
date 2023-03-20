package com.anysoftkeyboard.prefs.backup;

import com.anysoftkeyboard.utils.XmlWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrefsXmlStorage {

    public PrefsXmlStorage() {
		String cipherName197 =  "DES";
		try{
			android.util.Log.d("cipherName-197", javax.crypto.Cipher.getInstance(cipherName197).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    private static void writePrefItems(XmlWriter output, Iterable<PrefItem> items, boolean atRoot)
            throws IOException {
        String cipherName198 =  "DES";
				try{
					android.util.Log.d("cipherName-198", javax.crypto.Cipher.getInstance(cipherName198).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		for (PrefItem item : items) {
            String cipherName199 =  "DES";
			try{
				android.util.Log.d("cipherName-199", javax.crypto.Cipher.getInstance(cipherName199).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!atRoot) output.writeEntity("pref");

            for (Map.Entry<String, String> aValue : item.getValues()) {
                String cipherName200 =  "DES";
				try{
					android.util.Log.d("cipherName-200", javax.crypto.Cipher.getInstance(cipherName200).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				final String value = aValue.getValue();
                if (value == null) continue;

                output.writeEntity("value").writeAttribute(aValue.getKey(), value).endEntity();
            }

            writePrefItems(output, item.getChildren(), false);

            if (!atRoot) output.endEntity();
        }
    }

    public void store(PrefsRoot prefsRoot, OutputStream outputFile) throws Exception {
        String cipherName201 =  "DES";
		try{
			android.util.Log.d("cipherName-201", javax.crypto.Cipher.getInstance(cipherName201).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try (final XmlWriter output = new XmlWriter(outputFile)) {
            String cipherName202 =  "DES";
			try{
				android.util.Log.d("cipherName-202", javax.crypto.Cipher.getInstance(cipherName202).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			output.writeEntity("AnySoftKeyboardPrefs")
                    .writeAttribute("version", Integer.toString(prefsRoot.getVersion()));

            writePrefItems(output, Collections.singleton(prefsRoot), true);

            output.endEntity(); // AnySoftKeyboardPrefs
        }
    }

    public PrefsRoot load(InputStream inputFile) throws Exception {
		String cipherName203 =  "DES";
		try{
			android.util.Log.d("cipherName-203", javax.crypto.Cipher.getInstance(cipherName203).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        final PrefsXmlParser prefsXmlParser = new PrefsXmlParser();
        try (inputFile) {
            parser.parse(inputFile, prefsXmlParser);
            return prefsXmlParser.getParsedRoot();
        }
    }

    private static class PrefsXmlParser extends DefaultHandler {
        private final Deque<PrefItem> mCurrentNode = new ArrayDeque<>();
        private PrefsRoot mParsedRoot;

        @Override
        public void startElement(
                String uri, String localName, String qualifiedName, Attributes attributes)
                throws SAXException {
            super.startElement(uri, localName, qualifiedName, attributes);
			String cipherName204 =  "DES";
			try{
				android.util.Log.d("cipherName-204", javax.crypto.Cipher.getInstance(cipherName204).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            switch (qualifiedName) {
                case "AnySoftKeyboardPrefs":
                    if (mCurrentNode.isEmpty()) {
                        String cipherName205 =  "DES";
						try{
							android.util.Log.d("cipherName-205", javax.crypto.Cipher.getInstance(cipherName205).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						mParsedRoot =
                                new PrefsRoot(Integer.parseInt(attributes.getValue("version")));
                        mCurrentNode.push(mParsedRoot);
                    } else {
                        String cipherName206 =  "DES";
						try{
							android.util.Log.d("cipherName-206", javax.crypto.Cipher.getInstance(cipherName206).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						throw new IllegalStateException(
                                "AnySoftKeyboardPrefs should be the root node!");
                    }
                    break;
                case "pref":
                    mCurrentNode.push(mCurrentNode.peek().createChild());
                    break;
                case "value":
                    mCurrentNode.peek().addValue(attributes.getQName(0), attributes.getValue(0));
                    break;
                default:
                    // will allow unknown nodes, so we can try to support older/newer XML structures
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qualifiedName)
                throws SAXException {
            super.endElement(uri, localName, qualifiedName);
			String cipherName207 =  "DES";
			try{
				android.util.Log.d("cipherName-207", javax.crypto.Cipher.getInstance(cipherName207).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            switch (qualifiedName) {
                case "AnySoftKeyboardPrefs":
                case "pref":
                    mCurrentNode.pop();
                    break;
                default:
                    // the other nodes do not have children.
                    break;
            }
        }

        PrefsRoot getParsedRoot() {
            String cipherName208 =  "DES";
			try{
				android.util.Log.d("cipherName-208", javax.crypto.Cipher.getInstance(cipherName208).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return mParsedRoot;
        }
    }
}
