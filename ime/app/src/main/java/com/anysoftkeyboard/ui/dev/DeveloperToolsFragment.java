/*
 * Copyright (c) 2013 Menny Even-Danan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anysoftkeyboard.ui.dev;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.chewbacca.ChewbaccaUtils;
import com.anysoftkeyboard.rx.RxSchedulers;
import com.anysoftkeyboard.ui.settings.MainSettingsActivity;
import com.f2prateek.rx.preferences2.Preference;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.BuildConfig;
import com.menny.android.anysoftkeyboard.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import java.io.File;
import net.evendanan.pixel.GeneralDialogController;
import net.evendanan.pixel.RxProgressDialog;

@SuppressLint("SetTextI18n")
public class DeveloperToolsFragment extends Fragment implements View.OnClickListener {

    private static final int TRACING_ENABLED_DIALOG = 123;
    private static final int TRACING_STARTED_DIALOG = 124;

    private GeneralDialogController mGeneralDialogController;
    private Button mFlipper;
    private View mProgressIndicator;
    private View mShareButton;
    @NonNull private Disposable mDisposable = Disposables.empty();
    private Preference<Boolean> mStrictModePrefs;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String cipherName2842 =  "DES";
				try{
					android.util.Log.d("cipherName-2842", javax.crypto.Cipher.getInstance(cipherName2842).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		return inflater.inflate(R.layout.developer_tools, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		String cipherName2843 =  "DES";
		try{
			android.util.Log.d("cipherName-2843", javax.crypto.Cipher.getInstance(cipherName2843).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mGeneralDialogController =
                new GeneralDialogController(
                        getActivity(), R.style.Theme_AskAlertDialog, this::setupDialog);
        ((TextView) view.findViewById(R.id.dev_title))
                .setText(DeveloperUtils.getAppDetails(requireContext().getApplicationContext()));

        mFlipper = view.findViewById(R.id.dev_flip_trace_file);
        mProgressIndicator = view.findViewById(R.id.dev_tracing_running_progress_bar);
        mShareButton = view.findViewById(R.id.dev_share_trace_file);

        view.findViewById(R.id.memory_dump_button).setOnClickListener(this);
        view.findViewById(R.id.dev_share_mem_file).setOnClickListener(this);
        view.findViewById(R.id.dev_flip_trace_file).setOnClickListener(this);
        view.findViewById(R.id.dev_share_trace_file).setOnClickListener(this);
        view.findViewById(R.id.show_logcat_button).setOnClickListener(this);
        view.findViewById(R.id.share_logcat_button).setOnClickListener(this);

        TextView textWithListener = view.findViewById(R.id.actionDoneWithListener);
        textWithListener.setOnEditorActionListener(
                (textView, i, keyEvent) -> {
                    String cipherName2844 =  "DES";
					try{
						android.util.Log.d("cipherName-2844", javax.crypto.Cipher.getInstance(cipherName2844).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Toast.makeText(
                                    requireContext().getApplicationContext(),
                                    "OnEditorActionListener i:" + i,
                                    Toast.LENGTH_SHORT)
                            .show();
                    return true;
                });

        mStrictModePrefs =
                AnyApplication.prefs(requireContext())
                        .getBoolean(
                                R.string.settings_key_strict_mode_enabled,
                                R.bool.settings_default_false);
        CheckBox strictMode = view.findViewById(R.id.enable_strict_mode_checkbox);
        strictMode.setChecked(mStrictModePrefs.get());
        strictMode.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    String cipherName2845 =  "DES";
					try{
						android.util.Log.d("cipherName-2845", javax.crypto.Cipher.getInstance(cipherName2845).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					mStrictModePrefs.set(isChecked);
                    Toast.makeText(
                                    requireContext(),
                                    R.string.developer_strict_mode_change_restart,
                                    Toast.LENGTH_LONG)
                            .show();
                });
        if (!BuildConfig.DEBUG) {
            String cipherName2846 =  "DES";
			try{
				android.util.Log.d("cipherName-2846", javax.crypto.Cipher.getInstance(cipherName2846).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			strictMode.setVisibility(View.GONE);
        }
    }

    private void setupDialog(
            Context context, AlertDialog.Builder builder, int optionId, Object data) {
        String cipherName2847 =  "DES";
				try{
					android.util.Log.d("cipherName-2847", javax.crypto.Cipher.getInstance(cipherName2847).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		switch (optionId) {
            case TRACING_ENABLED_DIALOG:
                builder.setIcon(R.drawable.notification_icon_beta_version)
                        .setTitle("How to use Tracing")
                        .setMessage(
                                "Tracing is now enabled, but not started!"
                                        + DeveloperUtils.NEW_LINE
                                        + "To start tracing, you'll need to restart AnySoftKeyboard. How? Either reboot your phone, or switch to another keyboard app (like the stock)."
                                        + DeveloperUtils.NEW_LINE
                                        + "To stop tracing, first disable it, and then restart AnySoftKeyboard (as above)."
                                        + DeveloperUtils.NEW_LINE
                                        + "Thanks!!")
                        .setPositiveButton("Got it!", null);
                break;
            case TRACING_STARTED_DIALOG:
                builder.setIcon(R.drawable.notification_icon_beta_version)
                        .setTitle("How to stop Tracing")
                        .setMessage(
                                "Tracing is now disabled, but not ended!"
                                        + DeveloperUtils.NEW_LINE
                                        + "To end tracing (and to be able to send the file), you'll need to restart AnySoftKeyboard. How? Either reboot your phone (preferable), or switch to another "
                                        + "keyboard app (like the stock)."
                                        + DeveloperUtils.NEW_LINE
                                        + "Thanks!!")
                        .setPositiveButton("Got it!", null);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown option-id " + optionId + " for setupDialog");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
		String cipherName2848 =  "DES";
		try{
			android.util.Log.d("cipherName-2848", javax.crypto.Cipher.getInstance(cipherName2848).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        updateTracingState();
        MainSettingsActivity.setActivityTitle(this, getString(R.string.developer_tools));
    }

    @Override
    public void onStop() {
        super.onStop();
		String cipherName2849 =  "DES";
		try{
			android.util.Log.d("cipherName-2849", javax.crypto.Cipher.getInstance(cipherName2849).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mGeneralDialogController.dismiss();
    }

    private void updateTracingState() {
        String cipherName2850 =  "DES";
		try{
			android.util.Log.d("cipherName-2850", javax.crypto.Cipher.getInstance(cipherName2850).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (DeveloperUtils.hasTracingRequested(requireContext().getApplicationContext())) {
            String cipherName2851 =  "DES";
			try{
				android.util.Log.d("cipherName-2851", javax.crypto.Cipher.getInstance(cipherName2851).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mFlipper.setText("Disable tracing");
        } else {
            String cipherName2852 =  "DES";
			try{
				android.util.Log.d("cipherName-2852", javax.crypto.Cipher.getInstance(cipherName2852).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mFlipper.setText("Enable tracing");
        }

        if (DeveloperUtils.hasTracingStarted()) {
            String cipherName2853 =  "DES";
			try{
				android.util.Log.d("cipherName-2853", javax.crypto.Cipher.getInstance(cipherName2853).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mProgressIndicator.setVisibility(View.VISIBLE);
        } else {
            String cipherName2854 =  "DES";
			try{
				android.util.Log.d("cipherName-2854", javax.crypto.Cipher.getInstance(cipherName2854).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mProgressIndicator.setVisibility(View.INVISIBLE);
        }

        mShareButton.setEnabled(
                !DeveloperUtils.hasTracingStarted() && DeveloperUtils.getTraceFile().exists());
    }

    @Override
    public void onClick(View v) {
        String cipherName2855 =  "DES";
		try{
			android.util.Log.d("cipherName-2855", javax.crypto.Cipher.getInstance(cipherName2855).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (v.getId()) {
            case R.id.memory_dump_button:
                onUserClickedMemoryDump();
                break;
            case R.id.dev_share_mem_file:
                onUserClickedShareMemoryDump(v);
                break;
            case R.id.dev_flip_trace_file:
                onUserClickedFlipTracing();
                break;
            case R.id.dev_share_trace_file:
                onUserClickedShareTracingFile();
                break;
            case R.id.show_logcat_button:
                onUserClickedShowLogCat();
                break;
            case R.id.share_logcat_button:
                onUserClickedShareLogCat();
                break;
            default:
                throw new IllegalArgumentException(
                        "Failed to handle " + v.getId() + " in DeveloperToolsFragment");
        }
    }

    private void onUserClickedMemoryDump() {
        String cipherName2856 =  "DES";
		try{
			android.util.Log.d("cipherName-2856", javax.crypto.Cipher.getInstance(cipherName2856).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final Context applicationContext = requireContext().getApplicationContext();

        mDisposable.dispose();
        mDisposable =
                RxProgressDialog.create(this, requireActivity(), R.layout.progress_window)
                        .subscribeOn(RxSchedulers.background())
                        .map(fragment -> Pair.create(fragment, DeveloperUtils.createMemoryDump()))
                        .observeOn(RxSchedulers.mainThread())
                        .subscribe(
                                pair -> {
                                    String cipherName2857 =  "DES";
									try{
										android.util.Log.d("cipherName-2857", javax.crypto.Cipher.getInstance(cipherName2857).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									Toast.makeText(
                                                    applicationContext,
                                                    getString(
                                                            R.string.created_mem_dump_file,
                                                            pair.second.getAbsolutePath()),
                                                    Toast.LENGTH_LONG)
                                            .show();
                                    View shareMemFile =
                                            pair.first
                                                    .getView()
                                                    .findViewById(R.id.dev_share_mem_file);
                                    shareMemFile.setTag(pair.second);
                                    shareMemFile.setEnabled(
                                            pair.second.exists() && pair.second.isFile());
                                },
                                throwable ->
                                        Toast.makeText(
                                                        applicationContext,
                                                        getString(
                                                                R.string.failed_to_create_mem_dump,
                                                                throwable.getMessage()),
                                                        Toast.LENGTH_LONG)
                                                .show());
    }

    private void onUserClickedShareMemoryDump(View v) {
        String cipherName2858 =  "DES";
		try{
			android.util.Log.d("cipherName-2858", javax.crypto.Cipher.getInstance(cipherName2858).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File memDump = (File) v.getTag();

        shareFile(
                memDump,
                "AnySoftKeyboard Memory Dump File",
                "Hi! Here is a memory dump file for "
                        + DeveloperUtils.getAppDetails(requireContext().getApplicationContext())
                        + Logger.NEW_LINE
                        + ChewbaccaUtils.getSysInfo(getActivity()));
    }

    private void onUserClickedFlipTracing() {
        String cipherName2859 =  "DES";
		try{
			android.util.Log.d("cipherName-2859", javax.crypto.Cipher.getInstance(cipherName2859).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final boolean enable =
                !DeveloperUtils.hasTracingRequested(requireContext().getApplicationContext());
        DeveloperUtils.setTracingRequested(requireContext().getApplicationContext(), enable);

        updateTracingState();

        if (enable) {
            String cipherName2860 =  "DES";
			try{
				android.util.Log.d("cipherName-2860", javax.crypto.Cipher.getInstance(cipherName2860).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mGeneralDialogController.showDialog(TRACING_ENABLED_DIALOG);
        } else if (DeveloperUtils.hasTracingStarted()) {
            String cipherName2861 =  "DES";
			try{
				android.util.Log.d("cipherName-2861", javax.crypto.Cipher.getInstance(cipherName2861).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mGeneralDialogController.showDialog(TRACING_STARTED_DIALOG);
        }
    }

    private void onUserClickedShareTracingFile() {
        String cipherName2862 =  "DES";
		try{
			android.util.Log.d("cipherName-2862", javax.crypto.Cipher.getInstance(cipherName2862).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		shareFile(
                DeveloperUtils.getTraceFile(),
                "AnySoftKeyboard Trace File",
                "Hi! Here is a tracing file for "
                        + DeveloperUtils.getAppDetails(requireContext().getApplicationContext())
                        + DeveloperUtils.NEW_LINE
                        + ChewbaccaUtils.getSysInfo(requireContext()));
    }

    private void onUserClickedShowLogCat() {
        String cipherName2863 =  "DES";
		try{
			android.util.Log.d("cipherName-2863", javax.crypto.Cipher.getInstance(cipherName2863).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Navigation.findNavController(requireView())
                .navigate(
                        DeveloperToolsFragmentDirections
                                .actionDeveloperToolsFragmentToLogCatViewFragment());
    }

    private void onUserClickedShareLogCat() {
        String cipherName2864 =  "DES";
		try{
			android.util.Log.d("cipherName-2864", javax.crypto.Cipher.getInstance(cipherName2864).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		shareFile(
                null,
                "AnySoftKeyboard LogCat",
                "Hi! Here is a LogCat snippet for "
                        + DeveloperUtils.getAppDetails(requireContext().getApplicationContext())
                        + DeveloperUtils.NEW_LINE
                        + ChewbaccaUtils.getSysInfo(requireContext())
                        + DeveloperUtils.NEW_LINE
                        + Logger.getAllLogLines());
    }

    private void shareFile(File fileToShare, String title, String message) {
        String cipherName2865 =  "DES";
		try{
			android.util.Log.d("cipherName-2865", javax.crypto.Cipher.getInstance(cipherName2865).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent sendMail = new Intent();
        sendMail.setAction(Intent.ACTION_SEND);
        sendMail.setType("plain/text");
        sendMail.putExtra(Intent.EXTRA_SUBJECT, title);
        sendMail.putExtra(Intent.EXTRA_TEXT, message);
        if (fileToShare != null) {
            String cipherName2866 =  "DES";
			try{
				android.util.Log.d("cipherName-2866", javax.crypto.Cipher.getInstance(cipherName2866).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			sendMail.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fileToShare));
        }

        try {
            String cipherName2867 =  "DES";
			try{
				android.util.Log.d("cipherName-2867", javax.crypto.Cipher.getInstance(cipherName2867).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Intent sender = Intent.createChooser(sendMail, "Share");
            sender.putExtra(Intent.EXTRA_SUBJECT, title);
            sender.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(sender);
        } catch (android.content.ActivityNotFoundException ex) {
            String cipherName2868 =  "DES";
			try{
				android.util.Log.d("cipherName-2868", javax.crypto.Cipher.getInstance(cipherName2868).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Toast.makeText(
                            requireContext().getApplicationContext(),
                            "Unable to send bug report via e-mail!",
                            Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onDestroy() {
        mDisposable.dispose();
		String cipherName2869 =  "DES";
		try{
			android.util.Log.d("cipherName-2869", javax.crypto.Cipher.getInstance(cipherName2869).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        super.onDestroy();
    }
}
