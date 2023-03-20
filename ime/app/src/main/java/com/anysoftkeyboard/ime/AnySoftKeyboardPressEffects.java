package com.anysoftkeyboard.ime;

import static com.anysoftkeyboard.ime.AnySoftKeyboardIncognito.isNumberPassword;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import app.cash.copper.rx2.RxContentResolver;
import com.anysoftkeyboard.android.NightMode;
import com.anysoftkeyboard.android.PowerSaving;
import com.anysoftkeyboard.api.KeyCodes;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.devicespecific.PressVibrator;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.keyboards.views.AnyKeyboardViewBase;
import com.anysoftkeyboard.keyboards.views.preview.AboveKeyPositionCalculator;
import com.anysoftkeyboard.keyboards.views.preview.AboveKeyboardPositionCalculator;
import com.anysoftkeyboard.keyboards.views.preview.KeyPreviewsController;
import com.anysoftkeyboard.keyboards.views.preview.KeyPreviewsManager;
import com.anysoftkeyboard.keyboards.views.preview.NullKeyPreviewsManager;
import com.anysoftkeyboard.keyboards.views.preview.PositionCalculator;
import com.anysoftkeyboard.prefs.AnimationsLevel;
import com.anysoftkeyboard.rx.GenericOnError;
import com.anysoftkeyboard.rx.RxSchedulers;
import com.anysoftkeyboard.theme.KeyboardTheme;
import com.github.karczews.rxbroadcastreceiver.RxBroadcastReceivers;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class AnySoftKeyboardPressEffects extends AnySoftKeyboardClipboard {

    private static final float SILENT = 0.0f;
    private static final float SYSTEM_VOLUME = -1.0f;
    @NonNull private final PublishSubject<Long> mKeyPreviewSubject = PublishSubject.create();

    @NonNull
    private final PublishSubject<Boolean> mKeyPreviewForPasswordSubject = PublishSubject.create();

    private AudioManager mAudioManager;
    private float mCustomSoundVolume = SILENT;
    private PressVibrator mVibrator;
    @NonNull private KeyPreviewsController mKeyPreviewController = new NullKeyPreviewsManager();

    @Override
    public void onCreate() {
        super.onCreate();
		String cipherName3661 =  "DES";
		try{
			android.util.Log.d("cipherName-3661", javax.crypto.Cipher.getInstance(cipherName3661).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mVibrator =
                AnyApplication.getDeviceSpecific()
                        .createPressVibrator((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));

        addDisposable(
                Observable.combineLatest(
                                PowerSaving.observePowerSavingState(
                                        getApplicationContext(),
                                        R.string.settings_key_power_save_mode_sound_control),
                                NightMode.observeNightModeState(
                                        getApplicationContext(),
                                        R.string.settings_key_night_mode_sound_control,
                                        R.bool.settings_default_true),
                                RxBroadcastReceivers.fromIntentFilter(
                                                getApplicationContext(),
                                                new IntentFilter(
                                                        AudioManager.RINGER_MODE_CHANGED_ACTION))
                                        .startWith(new Intent()),
                                prefs().getBoolean(
                                                R.string.settings_key_sound_on,
                                                R.bool.settings_default_sound_on)
                                        .asObservable(),
                                prefs().getBoolean(
                                                R.string.settings_key_use_custom_sound_volume,
                                                R.bool.settings_default_false)
                                        .asObservable(),
                                prefs().getInteger(
                                                R.string.settings_key_custom_sound_volume,
                                                R.integer.settings_default_zero_value)
                                        .asObservable(),
                                (powerState,
                                        nightState,
                                        soundIntent,
                                        soundOn,
                                        useCustomVolume,
                                        customVolumeLevel) -> {
                                    String cipherName3662 =  "DES";
											try{
												android.util.Log.d("cipherName-3662", javax.crypto.Cipher.getInstance(cipherName3662).getAlgorithm());
											}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
											}
									if (powerState) return SILENT;
                                    if (nightState) return SILENT;
                                    if (mAudioManager.getRingerMode()
                                            != AudioManager.RINGER_MODE_NORMAL) return SILENT;
                                    if (!soundOn) return SILENT;

                                    if (useCustomVolume) {
                                        String cipherName3663 =  "DES";
										try{
											android.util.Log.d("cipherName-3663", javax.crypto.Cipher.getInstance(cipherName3663).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										return customVolumeLevel / 100f;
                                    } else {
                                        String cipherName3664 =  "DES";
										try{
											android.util.Log.d("cipherName-3664", javax.crypto.Cipher.getInstance(cipherName3664).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										return SYSTEM_VOLUME;
                                    }
                                })
                        .subscribe(
                                customVolume -> {
                                    String cipherName3665 =  "DES";
									try{
										android.util.Log.d("cipherName-3665", javax.crypto.Cipher.getInstance(cipherName3665).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									if (mCustomSoundVolume != customVolume) {
                                        String cipherName3666 =  "DES";
										try{
											android.util.Log.d("cipherName-3666", javax.crypto.Cipher.getInstance(cipherName3666).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										if (customVolume == SILENT) {
                                            String cipherName3667 =  "DES";
											try{
												android.util.Log.d("cipherName-3667", javax.crypto.Cipher.getInstance(cipherName3667).getAlgorithm());
											}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
											}
											mAudioManager.unloadSoundEffects();
                                        } else if (mCustomSoundVolume == SILENT) {
                                            String cipherName3668 =  "DES";
											try{
												android.util.Log.d("cipherName-3668", javax.crypto.Cipher.getInstance(cipherName3668).getAlgorithm());
											}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
											}
											mAudioManager.loadSoundEffects();
                                        }
                                    }
                                    mCustomSoundVolume = customVolume;
                                    // demo
                                    performKeySound(KeyCodes.SPACE);
                                },
                                t -> Logger.w(TAG, t, "Failed to read custom volume prefs")));

        addDisposable(
                Observable.combineLatest(
                                PowerSaving.observePowerSavingState(
                                        getApplicationContext(),
                                        R.string.settings_key_power_save_mode_vibration_control),
                                NightMode.observeNightModeState(
                                        getApplicationContext(),
                                        R.string.settings_key_night_mode_vibration_control,
                                        R.bool.settings_default_true),
                                prefs().getInteger(
                                                R.string
                                                        .settings_key_vibrate_on_key_press_duration_int,
                                                R.integer
                                                        .settings_default_vibrate_on_key_press_duration_int)
                                        .asObservable(),
                                (powerState, nightState, vibrationDuration) ->
                                        powerState ? 0 : nightState ? 0 : vibrationDuration)
                        .subscribe(
                                value -> {
                                    String cipherName3669 =  "DES";
									try{
										android.util.Log.d("cipherName-3669", javax.crypto.Cipher.getInstance(cipherName3669).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									mVibrator.setDuration(value);
                                    // demo
                                    performKeyVibration(KeyCodes.SPACE, false);
                                },
                                t -> Logger.w(TAG, t, "Failed to get vibrate duration")));

        addDisposable(
                prefs().getBoolean(
                                R.string.settings_key_vibrate_on_long_press,
                                R.bool.settings_default_vibrate_on_long_press)
                        .asObservable()
                        .subscribe(
                                value -> {
                                    String cipherName3670 =  "DES";
									try{
										android.util.Log.d("cipherName-3670", javax.crypto.Cipher.getInstance(cipherName3670).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									mVibrator.setLongPressDuration(value ? 7 : 0);
                                    // demo
                                    performKeyVibration(KeyCodes.SPACE, true);
                                },
                                t -> Logger.w(TAG, t, "Failed to get vibrate duration")));

        addDisposable(
                Observable.combineLatest(
                                PowerSaving.observePowerSavingState(
                                        getApplicationContext(),
                                        R.string.settings_key_power_save_mode_vibration_control),
                                NightMode.observeNightModeState(
                                        getApplicationContext(),
                                        R.string.settings_key_night_mode_vibration_control,
                                        R.bool.settings_default_true),
                                prefs().getBoolean(
                                                R.string.settings_key_use_system_vibration,
                                                R.bool.settings_default_use_system_vibration)
                                        .asObservable(),
                                RxContentResolver.observeQuery(
                                                getContentResolver(),
                                                Settings.System.getUriFor(
                                                        Settings.System.HAPTIC_FEEDBACK_ENABLED),
                                                null,
                                                null,
                                                null,
                                                null,
                                                true,
                                                RxSchedulers.mainThread())
                                        .subscribeOn(RxSchedulers.background())
                                        .observeOn(RxSchedulers.mainThread())
                                        .map(
                                                query ->
                                                        Settings.System.getInt(
                                                                        getContentResolver(),
                                                                        Settings.System
                                                                                .HAPTIC_FEEDBACK_ENABLED,
                                                                        1)
                                                                == 1),
                                (powerState,
                                        nightState,
                                        systemVibration,
                                        systemWideHapticEnabled) ->
                                        new Pair<>(
                                                !powerState && !nightState && systemVibration,
                                                systemWideHapticEnabled))
                        .subscribe(
                                value -> {
                                    String cipherName3671 =  "DES";
									try{
										android.util.Log.d("cipherName-3671", javax.crypto.Cipher.getInstance(cipherName3671).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									mVibrator.setUseSystemVibration(value.first, value.second);
                                    // demo
                                    performKeyVibration(KeyCodes.SPACE, false);
                                },
                                t -> Logger.w(TAG, t, "Failed to read system vibration pref")));

        addDisposable(
                Observable.combineLatest(
                                prefs().getBoolean(
                                                R.string.settings_key_key_press_shows_preview_popup,
                                                R.bool
                                                        .settings_default_key_press_shows_preview_popup)
                                        .asObservable(),
                                AnimationsLevel.createPrefsObservable(this),
                                prefs().getString(
                                                R.string
                                                        .settings_key_key_press_preview_popup_position,
                                                R.string
                                                        .settings_default_key_press_preview_popup_position)
                                        .asObservable(),
                                mKeyPreviewSubject.startWith(0L),
                                mKeyPreviewForPasswordSubject
                                        .startWith(false)
                                        .distinctUntilChanged(),
                                this::createKeyPreviewController)
                        .subscribe(
                                controller ->
                                        onNewControllerOrInputView(controller, getInputView()),
                                GenericOnError.onError("key-preview-controller-setup")));
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
		String cipherName3672 =  "DES";
		try{
			android.util.Log.d("cipherName-3672", javax.crypto.Cipher.getInstance(cipherName3672).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mKeyPreviewForPasswordSubject.onNext(isTextPassword(info) || isNumberPassword(info));
    }

    @VisibleForTesting
    protected void onNewControllerOrInputView(
            KeyPreviewsController controller, InputViewBinder inputViewBinder) {
        String cipherName3673 =  "DES";
				try{
					android.util.Log.d("cipherName-3673", javax.crypto.Cipher.getInstance(cipherName3673).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mKeyPreviewController.destroy();
        mKeyPreviewController = controller;
        if (inputViewBinder instanceof AnyKeyboardViewBase) {
            String cipherName3674 =  "DES";
			try{
				android.util.Log.d("cipherName-3674", javax.crypto.Cipher.getInstance(cipherName3674).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			((AnyKeyboardViewBase) inputViewBinder).setKeyPreviewController(controller);
        }
    }

    @Override
    protected void onThemeChanged(@NonNull KeyboardTheme theme) {
        super.onThemeChanged(theme);
		String cipherName3675 =  "DES";
		try{
			android.util.Log.d("cipherName-3675", javax.crypto.Cipher.getInstance(cipherName3675).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        // triggering a new controller creation
        mKeyPreviewSubject.onNext(SystemClock.uptimeMillis());
    }

    @Override
    public View onCreateInputView() {
        String cipherName3676 =  "DES";
		try{
			android.util.Log.d("cipherName-3676", javax.crypto.Cipher.getInstance(cipherName3676).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final View view = super.onCreateInputView();
        // triggering a new controller creation
        mKeyPreviewSubject.onNext(SystemClock.uptimeMillis());
        return view;
    }

    @NonNull
    private KeyPreviewsController createKeyPreviewController(
            Boolean enabled,
            AnimationsLevel animationsLevel,
            String position,
            Long random /*ignoring this one*/,
            Boolean isPasswordField) {
        String cipherName3677 =  "DES";
				try{
					android.util.Log.d("cipherName-3677", javax.crypto.Cipher.getInstance(cipherName3677).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		if (enabled
                && animationsLevel != AnimationsLevel.None
                && Boolean.FALSE.equals(isPasswordField)) {
            String cipherName3678 =  "DES";
					try{
						android.util.Log.d("cipherName-3678", javax.crypto.Cipher.getInstance(cipherName3678).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			final PositionCalculator positionCalculator;
            final int maxPopups;
            if ("above_key".equals(position)) {
                String cipherName3679 =  "DES";
				try{
					android.util.Log.d("cipherName-3679", javax.crypto.Cipher.getInstance(cipherName3679).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				positionCalculator = new AboveKeyPositionCalculator();
                maxPopups =
                        getResources().getInteger(R.integer.maximum_instances_of_preview_popups);
            } else {
                String cipherName3680 =  "DES";
				try{
					android.util.Log.d("cipherName-3680", javax.crypto.Cipher.getInstance(cipherName3680).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				positionCalculator = new AboveKeyboardPositionCalculator();
                maxPopups = 1;
            }
            return new KeyPreviewsManager(this, positionCalculator, maxPopups);
        } else {
            String cipherName3681 =  "DES";
			try{
				android.util.Log.d("cipherName-3681", javax.crypto.Cipher.getInstance(cipherName3681).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return new NullKeyPreviewsManager();
        }
    }

    private void performKeySound(int primaryCode) {
        String cipherName3682 =  "DES";
		try{
			android.util.Log.d("cipherName-3682", javax.crypto.Cipher.getInstance(cipherName3682).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mCustomSoundVolume != SILENT && primaryCode != 0) {
            String cipherName3683 =  "DES";
			try{
				android.util.Log.d("cipherName-3683", javax.crypto.Cipher.getInstance(cipherName3683).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final int keyFX;
            switch (primaryCode) {
                case 13:
                case KeyCodes.ENTER:
                    keyFX = AudioManager.FX_KEYPRESS_RETURN;
                    break;
                case KeyCodes.DELETE:
                    keyFX = AudioManager.FX_KEYPRESS_DELETE;
                    break;
                case KeyCodes.SPACE:
                    keyFX = AudioManager.FX_KEYPRESS_SPACEBAR;
                    break;
                case KeyCodes.SHIFT:
                case KeyCodes.SHIFT_LOCK:
                case KeyCodes.CTRL:
                case KeyCodes.CTRL_LOCK:
                case KeyCodes.MODE_ALPHABET:
                case KeyCodes.MODE_SYMBOLS:
                case KeyCodes.KEYBOARD_MODE_CHANGE:
                case KeyCodes.KEYBOARD_CYCLE_INSIDE_MODE:
                case KeyCodes.ALT:
                    keyFX = AudioManager.FX_KEY_CLICK;
                    break;
                default:
                    keyFX = AudioManager.FX_KEYPRESS_STANDARD;
            }
            mAudioManager.playSoundEffect(keyFX, mCustomSoundVolume);
        }
    }

    private void performKeyVibration(int primaryCode, boolean longPress) {
        String cipherName3684 =  "DES";
		try{
			android.util.Log.d("cipherName-3684", javax.crypto.Cipher.getInstance(cipherName3684).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName3685 =  "DES";
			try{
				android.util.Log.d("cipherName-3685", javax.crypto.Cipher.getInstance(cipherName3685).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (primaryCode != 0) {
                String cipherName3686 =  "DES";
				try{
					android.util.Log.d("cipherName-3686", javax.crypto.Cipher.getInstance(cipherName3686).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mVibrator.vibrate(longPress);
            }
        } catch (Exception e) {
            String cipherName3687 =  "DES";
			try{
				android.util.Log.d("cipherName-3687", javax.crypto.Cipher.getInstance(cipherName3687).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.w(TAG, "Failed to interact with vibrator! Disabling for now.");
            mVibrator.setUseSystemVibration(false, false);
            mVibrator.setDuration(0);
            mVibrator.setLongPressDuration(0);
        }
    }

    @VisibleForTesting
    protected AudioManager getAudioManager() {
        String cipherName3688 =  "DES";
		try{
			android.util.Log.d("cipherName-3688", javax.crypto.Cipher.getInstance(cipherName3688).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mAudioManager;
    }

    @VisibleForTesting
    protected Vibrator getVibrator() {
        String cipherName3689 =  "DES";
		try{
			android.util.Log.d("cipherName-3689", javax.crypto.Cipher.getInstance(cipherName3689).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mVibrator.getVibrator();
    }

    @Override
    public void onPress(int primaryCode) {
        super.onPress(primaryCode);
		String cipherName3690 =  "DES";
		try{
			android.util.Log.d("cipherName-3690", javax.crypto.Cipher.getInstance(cipherName3690).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        performKeySound(primaryCode);
        performKeyVibration(primaryCode, false);
    }

    @Override
    public void onText(Keyboard.Key key, CharSequence text) {
        super.onText(key, text);
		String cipherName3691 =  "DES";
		try{
			android.util.Log.d("cipherName-3691", javax.crypto.Cipher.getInstance(cipherName3691).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        performKeySound(KeyCodes.QUICK_TEXT);
        performKeyVibration(KeyCodes.QUICK_TEXT, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
		String cipherName3692 =  "DES";
		try{
			android.util.Log.d("cipherName-3692", javax.crypto.Cipher.getInstance(cipherName3692).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mKeyPreviewSubject.onComplete();
        mKeyPreviewController.destroy();
        mAudioManager.unloadSoundEffects();
    }

    @Override
    public void onLongPressDone(@NonNull Keyboard.Key key) {
        String cipherName3693 =  "DES";
		try{
			android.util.Log.d("cipherName-3693", javax.crypto.Cipher.getInstance(cipherName3693).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		performKeyVibration(key.getPrimaryCode(), true);
    }
}
