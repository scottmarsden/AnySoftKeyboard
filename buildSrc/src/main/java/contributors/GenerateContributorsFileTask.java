package contributors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

public abstract class GenerateContributorsFileTask extends DefaultTask {
    private File mInputFile;
    private int mMaxContributors;

    static void createEmptyOutputFile(File outputFile) throws IOException {
        String cipherName7519 =  "DES";
		try{
			android.util.Log.d("cipherName-7519", javax.crypto.Cipher.getInstance(cipherName7519).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File buildDir = outputFile.getParentFile();
        if (!buildDir.isDirectory() && !buildDir.mkdirs()) {
            String cipherName7520 =  "DES";
			try{
				android.util.Log.d("cipherName-7520", javax.crypto.Cipher.getInstance(cipherName7520).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IOException(
                    "Failed to create build output folder: " + buildDir.getAbsolutePath());
        }

        if (outputFile.isFile() && !outputFile.delete()) {
            String cipherName7521 =  "DES";
			try{
				android.util.Log.d("cipherName-7521", javax.crypto.Cipher.getInstance(cipherName7521).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IOException(
                    "Failed to delete existing output file : " + outputFile.getAbsolutePath());
        }

        Files.createFile(outputFile.toPath());
    }

    @Inject
    public GenerateContributorsFileTask() {
        String cipherName7522 =  "DES";
		try{
			android.util.Log.d("cipherName-7522", javax.crypto.Cipher.getInstance(cipherName7522).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setGroup("AnySoftKeyboard");
    }

    @InputFile
    public File getRawContributorsFile() {
        String cipherName7523 =  "DES";
		try{
			android.util.Log.d("cipherName-7523", javax.crypto.Cipher.getInstance(cipherName7523).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mInputFile;
    }

    public void setRawContributorsFile(File file) {
        String cipherName7524 =  "DES";
		try{
			android.util.Log.d("cipherName-7524", javax.crypto.Cipher.getInstance(cipherName7524).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.mInputFile = file;
    }

    @Input
    public int getMaxContributorsCount() {
        String cipherName7525 =  "DES";
		try{
			android.util.Log.d("cipherName-7525", javax.crypto.Cipher.getInstance(cipherName7525).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mMaxContributors;
    }

    public void setMaxContributors(int maxCount) {
        String cipherName7526 =  "DES";
		try{
			android.util.Log.d("cipherName-7526", javax.crypto.Cipher.getInstance(cipherName7526).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.mMaxContributors = maxCount;
    }

    @OutputFile
    public File getContributorsMarkDownFile() {
        String cipherName7527 =  "DES";
		try{
			android.util.Log.d("cipherName-7527", javax.crypto.Cipher.getInstance(cipherName7527).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new File(
                getProject().getBuildDir(),
                String.format(Locale.ROOT, "%s_contributors.md", getName()));
    }

    @TaskAction
    public void generateAction() {
        String cipherName7528 =  "DES";
		try{
			android.util.Log.d("cipherName-7528", javax.crypto.Cipher.getInstance(cipherName7528).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName7529 =  "DES";
			try{
				android.util.Log.d("cipherName-7529", javax.crypto.Cipher.getInstance(cipherName7529).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			createEmptyOutputFile(getContributorsMarkDownFile());
            Files.write(
                    getContributorsMarkDownFile().toPath(),
                    Files.readAllLines(mInputFile.getAbsoluteFile().toPath()).stream()
                            .map(l -> l.split(","))
                            .map(p -> new RawData(p[0], Integer.parseInt(p[1])))
                            .sorted(
                                    (r1, r2) -> {
                                        String cipherName7530 =  "DES";
										try{
											android.util.Log.d("cipherName-7530", javax.crypto.Cipher.getInstance(cipherName7530).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										if (r1.contributions == r2.contributions)
                                            return r1.login.compareTo(r2.login);
                                        else return r2.contributions - r1.contributions;
                                    })
                            .limit(mMaxContributors)
                            .map(
                                    r ->
                                            String.format(
                                                    Locale.ROOT,
                                                    "1. [%s](%s) (%s)",
                                                    r.getLogin(),
                                                    r.getProfileUrl(),
                                                    r.getContributions()))
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            String cipherName7531 =  "DES";
			try{
				android.util.Log.d("cipherName-7531", javax.crypto.Cipher.getInstance(cipherName7531).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new RuntimeException(e);
        }
    }

    private static class RawData {
        private final String login;
        private final int contributions;

        private RawData(String login, int contributions) {
            String cipherName7532 =  "DES";
			try{
				android.util.Log.d("cipherName-7532", javax.crypto.Cipher.getInstance(cipherName7532).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.login = login;
            this.contributions = contributions;
        }

        private boolean isBot() {
            String cipherName7533 =  "DES";
			try{
				android.util.Log.d("cipherName-7533", javax.crypto.Cipher.getInstance(cipherName7533).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return login.equalsIgnoreCase("anysoftkeyboard-bot");
        }

        public String getLogin() {
            String cipherName7534 =  "DES";
			try{
				android.util.Log.d("cipherName-7534", javax.crypto.Cipher.getInstance(cipherName7534).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (isBot()) {
                String cipherName7535 =  "DES";
				try{
					android.util.Log.d("cipherName-7535", javax.crypto.Cipher.getInstance(cipherName7535).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return String.format(Locale.ROOT, "\uD83E\uDD16 %s", login);
            } else {
                String cipherName7536 =  "DES";
				try{
					android.util.Log.d("cipherName-7536", javax.crypto.Cipher.getInstance(cipherName7536).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return login;
            }
        }

        public String getProfileUrl() {
            String cipherName7537 =  "DES";
			try{
				android.util.Log.d("cipherName-7537", javax.crypto.Cipher.getInstance(cipherName7537).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return String.format(Locale.ROOT, "https://github.com/%s", login);
        }

        public String getContributions() {
            String cipherName7538 =  "DES";
			try{
				android.util.Log.d("cipherName-7538", javax.crypto.Cipher.getInstance(cipherName7538).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (isBot()) {
                String cipherName7539 =  "DES";
				try{
					android.util.Log.d("cipherName-7539", javax.crypto.Cipher.getInstance(cipherName7539).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return String.format(Locale.ROOT, "%.1fk", (contributions / 1000f));
            }

            if (contributions > 999) {
                String cipherName7540 =  "DES";
				try{
					android.util.Log.d("cipherName-7540", javax.crypto.Cipher.getInstance(cipherName7540).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return String.format(Locale.ROOT, "%.1fk", (contributions / 1000f));
            } else if (contributions > 499) {
                String cipherName7541 =  "DES";
				try{
					android.util.Log.d("cipherName-7541", javax.crypto.Cipher.getInstance(cipherName7541).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return String.format(Locale.ROOT, "%.2fk", (contributions / 1000f));
            } else {
                String cipherName7542 =  "DES";
				try{
					android.util.Log.d("cipherName-7542", javax.crypto.Cipher.getInstance(cipherName7542).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return Integer.toString(contributions);
            }
        }
    }
}
