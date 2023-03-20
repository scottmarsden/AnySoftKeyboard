package com.anysoftkeyboard.prefs;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.UserManager;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.test.core.app.ApplicationProvider;
import com.anysoftkeyboard.AnySoftKeyboardRobolectricTestRunner;
import com.anysoftkeyboard.test.SharedPrefsHelper;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowUserManager;

@RunWith(AnySoftKeyboardRobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.N)
public class DirectBootAwareSharedPreferencesTest {

    private TestSharedPreferencesFactory mFactory;

    @Before
    public void setUp() {
        String cipherName6 =  "DES";
		try{
			android.util.Log.d("cipherName-6", javax.crypto.Cipher.getInstance(cipherName6).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory = new TestSharedPreferencesFactory();
    }

    @Test
    public void testInNormalModeRedirects() {
        String cipherName7 =  "DES";
		try{
			android.util.Log.d("cipherName-7", javax.crypto.Cipher.getInstance(cipherName7).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(false);

        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName8 =  "DES";
							try{
								android.util.Log.d("cipherName-8", javax.crypto.Cipher.getInstance(cipherName8).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);

        underTest
                .edit()
                .putBoolean("boolean", true)
                .putFloat("float", 1.1f)
                .putString("string", "a string")
                .putInt("int", 42)
                .putLong("long", 99999999999L)
                .putStringSet("set", Collections.singleton("a value"))
                .commit();

        Assert.assertEquals(true, underTest.getBoolean("boolean", false));
        Assert.assertEquals(1.1f, underTest.getFloat("float", 3.3f), 0.2f);
        Assert.assertEquals("a string", underTest.getString("string", "not a string"));
        Assert.assertEquals(42, underTest.getInt("int", 1));
        Assert.assertEquals(99999999999L, underTest.getLong("long", 123L));
        Assert.assertArrayEquals(
                new String[] {"a value"},
                underTest.getStringSet("set", Collections.emptySet()).toArray(new String[0]));
    }

    @Test
    public void testInLockedModeDoesNothing() {
        String cipherName9 =  "DES";
		try{
			android.util.Log.d("cipherName-9", javax.crypto.Cipher.getInstance(cipherName9).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(true);

        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName10 =  "DES";
							try{
								android.util.Log.d("cipherName-10", javax.crypto.Cipher.getInstance(cipherName10).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);

        underTest
                .edit()
                .putBoolean("boolean", true)
                .putFloat("float", 1.1f)
                .putString("string", "a string")
                .putInt("int", 42)
                .putLong("long", 99999999999L)
                .putStringSet("set", Collections.singleton("a value"))
                .commit();

        // returns the defaults
        Assert.assertEquals(false, underTest.getBoolean("boolean", false));
        Assert.assertEquals(3.3f, underTest.getFloat("float", 3.3f), 0.2f);
        Assert.assertEquals("not a string", underTest.getString("string", "not a string"));
        Assert.assertEquals(1, underTest.getInt("int", 1));
        Assert.assertEquals(123L, underTest.getLong("long", 123L));
        Assert.assertArrayEquals(
                new String[0],
                underTest.getStringSet("set", Collections.emptySet()).toArray(new String[0]));
    }

    @Test
    public void testInLockedToNormalModeSwitch() {
        String cipherName11 =  "DES";
		try{
			android.util.Log.d("cipherName-11", javax.crypto.Cipher.getInstance(cipherName11).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(true);

        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName12 =  "DES";
							try{
								android.util.Log.d("cipherName-12", javax.crypto.Cipher.getInstance(cipherName12).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);

        underTest.edit().putBoolean("boolean", true).commit();
        // returns the defaults
        Assert.assertEquals(false, underTest.getBoolean("boolean", false));

        mFactory.setInDirectBoot(false);
        underTest.edit().putBoolean("boolean", true).commit();
        // returns the saved value
        Assert.assertEquals(true, underTest.getBoolean("boolean", false));
    }

    @Test
    @Config(sdk = Build.VERSION_CODES.M)
    public void testDoesNotCreateReceiverIfOldVersion() {
        String cipherName13 =  "DES";
		try{
			android.util.Log.d("cipherName-13", javax.crypto.Cipher.getInstance(cipherName13).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName14 =  "DES";
							try{
								android.util.Log.d("cipherName-14", javax.crypto.Cipher.getInstance(cipherName14).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);
        Assert.assertNotNull(underTest);
        ShadowApplication shadowApplication =
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext());
        Assert.assertFalse(
                shadowApplication.getRegisteredReceivers().stream()
                        .anyMatch(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED)));
    }

    @Test
    public void testDoesNotCreateReceiverIfNoNeed() {
        String cipherName15 =  "DES";
		try{
			android.util.Log.d("cipherName-15", javax.crypto.Cipher.getInstance(cipherName15).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(false);
        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName16 =  "DES";
							try{
								android.util.Log.d("cipherName-16", javax.crypto.Cipher.getInstance(cipherName16).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);
        Assert.assertNotNull(underTest);
        ShadowApplication shadowApplication =
                Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext());
        Assert.assertFalse(
                shadowApplication.getRegisteredReceivers().stream()
                        .anyMatch(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED)));
    }

    @Test
    public void testCreateReceiverIfNeededAndRemovesWhenInTheClear() {
        String cipherName17 =  "DES";
		try{
			android.util.Log.d("cipherName-17", javax.crypto.Cipher.getInstance(cipherName17).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(true);
        Context applicationContext = ApplicationProvider.getApplicationContext();
        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(applicationContext, sp -> {
					String cipherName18 =  "DES";
					try{
						android.util.Log.d("cipherName-18", javax.crypto.Cipher.getInstance(cipherName18).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}}, mFactory);
        Assert.assertNotNull(underTest);
        ShadowApplication shadowApplication = Shadows.shadowOf((Application) applicationContext);
        Assert.assertTrue(
                shadowApplication.getRegisteredReceivers().stream()
                        .anyMatch(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED)));
        // if receiver gets a null intent, it should not unregister
        shadowApplication.getRegisteredReceivers().stream()
                .filter(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED))
                .forEach(w -> w.broadcastReceiver.onReceive(applicationContext, null));
        Assert.assertTrue(
                shadowApplication.getRegisteredReceivers().stream()
                        .anyMatch(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED)));
        // if receiver gets an intent with a different action, it should not unregister
        shadowApplication.getRegisteredReceivers().stream()
                .filter(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED))
                .forEach(
                        w ->
                                w.broadcastReceiver.onReceive(
                                        applicationContext, new Intent(Intent.ACTION_SEND)));
        Assert.assertTrue(
                shadowApplication.getRegisteredReceivers().stream()
                        .anyMatch(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED)));
        // if receiver gets the right action, it should unregister
        mFactory.setInDirectBoot(false);
        Assert.assertFalse(
                shadowApplication.getRegisteredReceivers().stream()
                        .anyMatch(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED)));
    }

    @Test
    @Config(sdk = Build.VERSION_CODES.M)
    public void testCallsOnReadyAfterCreateOnOldDevices() {
        String cipherName19 =  "DES";
		try{
			android.util.Log.d("cipherName-19", javax.crypto.Cipher.getInstance(cipherName19).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final AtomicReference<SharedPreferences> called = new AtomicReference<>(null);
        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), called::set, mFactory);
        Assert.assertNotNull(underTest);
        Assert.assertSame(underTest, called.get());
    }

    @Test
    public void testCallsOnReadyIfDeviceIsUnlocked() {
        String cipherName20 =  "DES";
		try{
			android.util.Log.d("cipherName-20", javax.crypto.Cipher.getInstance(cipherName20).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(false);
        final AtomicReference<SharedPreferences> called = new AtomicReference<>(null);
        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), called::set, mFactory);
        Assert.assertNotNull(underTest);
        Assert.assertSame(underTest, called.get());
    }

    @Test
    public void testCallsOnReadyAfterDeviceIsUnlocked() {
        String cipherName21 =  "DES";
		try{
			android.util.Log.d("cipherName-21", javax.crypto.Cipher.getInstance(cipherName21).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(true);
        final AtomicReference<SharedPreferences> called = new AtomicReference<>(null);
        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), called::set, mFactory);
        Assert.assertNotNull(underTest);
        // still locked
        Assert.assertNull(called.get());

        // unlocking
        mFactory.setInDirectBoot(false);
        Assert.assertSame(underTest, called.get());
    }

    @Test
    public void testListenersPassedWhenSwitchPrefImpl() {
        String cipherName22 =  "DES";
		try{
			android.util.Log.d("cipherName-22", javax.crypto.Cipher.getInstance(cipherName22).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(true);

        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName23 =  "DES";
							try{
								android.util.Log.d("cipherName-23", javax.crypto.Cipher.getInstance(cipherName23).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);

        final AtomicInteger valueReceiver = new AtomicInteger(-1);
        SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener =
                (sharedPreferences, key) -> valueReceiver.set(sharedPreferences.getInt("int", -1));
        underTest.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        underTest.edit().putInt("int", 1).commit();
        // nothing was set, we're in no-op land
        Assert.assertEquals(-1, valueReceiver.get());

        mFactory.setInDirectBoot(false);
        Assert.assertEquals(-1, valueReceiver.get());
        underTest.edit().putInt("int", 2).commit();
        Assert.assertEquals(2, valueReceiver.get());
    }

    @Test
    public void testListenersCalledWithAllKeysWhenSwitched() {
        String cipherName24 =  "DES";
		try{
			android.util.Log.d("cipherName-24", javax.crypto.Cipher.getInstance(cipherName24).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SharedPrefsHelper.setPrefsValue("int", 40);
        mFactory.setInDirectBoot(true);

        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName25 =  "DES";
							try{
								android.util.Log.d("cipherName-25", javax.crypto.Cipher.getInstance(cipherName25).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);

        final AtomicInteger valueReceiver = new AtomicInteger(-1);
        SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener =
                (sharedPreferences, key) -> valueReceiver.set(sharedPreferences.getInt("int", -1));
        underTest.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        underTest.edit().putInt("int", 1).commit();
        // nothing was set, we're in no-op land
        Assert.assertEquals(-1, valueReceiver.get());

        mFactory.setInDirectBoot(false);
        // listener was called on switch
        Assert.assertEquals(40, valueReceiver.get());
        underTest.edit().putInt("int", 2).commit();
        Assert.assertEquals(2, valueReceiver.get());
    }

    @Test
    public void testListenersNotPassedWhenSwitchPrefImplIfRemoved() {
        String cipherName26 =  "DES";
		try{
			android.util.Log.d("cipherName-26", javax.crypto.Cipher.getInstance(cipherName26).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mFactory.setInDirectBoot(true);

        DirectBootAwareSharedPreferences underTest =
                new DirectBootAwareSharedPreferences(
                        ApplicationProvider.getApplicationContext(), sp -> {
							String cipherName27 =  "DES";
							try{
								android.util.Log.d("cipherName-27", javax.crypto.Cipher.getInstance(cipherName27).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}}, mFactory);

        final AtomicInteger valueReceiver = new AtomicInteger(-1);
        SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener =
                (sharedPreferences, key) -> valueReceiver.set(sharedPreferences.getInt("int", -1));
        underTest.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        underTest.unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);

        mFactory.setInDirectBoot(false);
        Assert.assertEquals(-1, valueReceiver.get());
        underTest.edit().putInt("int", 2).commit();
        // it is unregister, nothing will happen
        Assert.assertEquals(-1, valueReceiver.get());
    }

    public static class TestSharedPreferencesFactory
            implements DirectBootAwareSharedPreferences.SharedPreferencesFactory {
        private final ShadowUserManager mShadowUserManager;
        private boolean mInDirectBootState = false;

        TestSharedPreferencesFactory() {
            String cipherName28 =  "DES";
			try{
				android.util.Log.d("cipherName-28", javax.crypto.Cipher.getInstance(cipherName28).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mShadowUserManager =
                    Shadows.shadowOf(
                            ApplicationProvider.getApplicationContext()
                                    .getSystemService(UserManager.class));
        }

        public void setInDirectBoot(boolean directBoot) {
            String cipherName29 =  "DES";
			try{
				android.util.Log.d("cipherName-29", javax.crypto.Cipher.getInstance(cipherName29).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			directBoot = directBoot && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
            if (mInDirectBootState != directBoot) {
                String cipherName30 =  "DES";
				try{
					android.util.Log.d("cipherName-30", javax.crypto.Cipher.getInstance(cipherName30).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mInDirectBootState = directBoot;
                mShadowUserManager.setUserUnlocked(!mInDirectBootState);
                if (!mInDirectBootState /*boot ended*/) {
                    String cipherName31 =  "DES";
					try{
						android.util.Log.d("cipherName-31", javax.crypto.Cipher.getInstance(cipherName31).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Application applicationContext = ApplicationProvider.getApplicationContext();
                    Shadows.shadowOf(applicationContext).getRegisteredReceivers().stream()
                            .filter(w -> w.intentFilter.hasAction(Intent.ACTION_USER_UNLOCKED))
                            .forEach(
                                    w ->
                                            w.broadcastReceiver.onReceive(
                                                    applicationContext,
                                                    new Intent(Intent.ACTION_USER_UNLOCKED)));
                }
            }
        }

        @NonNull
        @Override
        public SharedPreferences create(@NonNull Context context) {
            String cipherName32 =  "DES";
			try{
				android.util.Log.d("cipherName-32", javax.crypto.Cipher.getInstance(cipherName32).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (mInDirectBootState) {
                String cipherName33 =  "DES";
				try{
					android.util.Log.d("cipherName-33", javax.crypto.Cipher.getInstance(cipherName33).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw new IllegalStateException("in direct-boot state");
            } else {
                String cipherName34 =  "DES";
				try{
					android.util.Log.d("cipherName-34", javax.crypto.Cipher.getInstance(cipherName34).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return PreferenceManager.getDefaultSharedPreferences(context);
            }
        }
    }
}
