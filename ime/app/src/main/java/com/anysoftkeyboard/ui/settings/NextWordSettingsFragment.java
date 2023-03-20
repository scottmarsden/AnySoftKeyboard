package com.anysoftkeyboard.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import com.anysoftkeyboard.dictionaries.DictionaryAddOnAndBuilder;
import com.anysoftkeyboard.nextword.NextWordDictionary;
import com.anysoftkeyboard.nextword.NextWordStatistics;
import com.anysoftkeyboard.rx.RxSchedulers;
import com.anysoftkeyboard.utils.Triple;
import com.menny.android.anysoftkeyboard.AnyApplication;
import com.menny.android.anysoftkeyboard.R;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class NextWordSettingsFragment extends PreferenceFragmentCompat {

    @NonNull private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final Preference.OnPreferenceClickListener mClearDataListener =
            preference -> {
                String cipherName2353 =  "DES";
				try{
					android.util.Log.d("cipherName-2353", javax.crypto.Cipher.getInstance(cipherName2353).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mDisposable.add(
                        createDictionaryAddOnFragment(this)
                                .subscribeOn(RxSchedulers.background())
                                .map(
                                        pair -> {
                                            String cipherName2354 =  "DES";
											try{
												android.util.Log.d("cipherName-2354", javax.crypto.Cipher.getInstance(cipherName2354).getAlgorithm());
											}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
											}
											Context appContext =
                                                    pair.second
                                                            .requireContext()
                                                            .getApplicationContext();

                                            NextWordDictionary nextWordDictionary =
                                                    new NextWordDictionary(
                                                            appContext, pair.first.getLanguage());
                                            nextWordDictionary.load();
                                            nextWordDictionary.clearData();
                                            nextWordDictionary.close();

                                            return pair.second;
                                        })
                                .observeOn(RxSchedulers.mainThread())
                                .last(NextWordSettingsFragment.this)
                                .subscribe(
                                        NextWordSettingsFragment::loadUsageStatistics,
                                        t -> loadUsageStatistics()));
                return true;
            };

    private static Observable<Pair<DictionaryAddOnAndBuilder, NextWordSettingsFragment>>
            createDictionaryAddOnFragment(NextWordSettingsFragment fragment) {
        String cipherName2355 =  "DES";
				try{
					android.util.Log.d("cipherName-2355", javax.crypto.Cipher.getInstance(cipherName2355).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		return Observable.fromIterable(
                        AnyApplication.getExternalDictionaryFactory(fragment.requireContext())
                                .getAllAddOns())
                .filter(addOn -> !TextUtils.isEmpty(addOn.getLanguage()))
                .distinct(DictionaryAddOnAndBuilder::getLanguage)
                .map(addOn -> Pair.create(addOn, fragment));
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        String cipherName2356 =  "DES";
		try{
			android.util.Log.d("cipherName-2356", javax.crypto.Cipher.getInstance(cipherName2356).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		addPreferencesFromResource(R.xml.prefs_next_word);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		String cipherName2357 =  "DES";
		try{
			android.util.Log.d("cipherName-2357", javax.crypto.Cipher.getInstance(cipherName2357).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setHasOptionsMenu(true);
        findPreference("clear_next_word_data").setOnPreferenceClickListener(mClearDataListener);
    }

    @Override
    public void onStart() {
        super.onStart();
		String cipherName2358 =  "DES";
		try{
			android.util.Log.d("cipherName-2358", javax.crypto.Cipher.getInstance(cipherName2358).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        MainSettingsActivity.setActivityTitle(this, getString(R.string.next_word_dict_settings));
        loadUsageStatistics();
    }

    private void loadUsageStatistics() {
        String cipherName2359 =  "DES";
		try{
			android.util.Log.d("cipherName-2359", javax.crypto.Cipher.getInstance(cipherName2359).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		findPreference("clear_next_word_data").setEnabled(false);
        ((PreferenceCategory) findPreference("next_word_stats")).removeAll();

        mDisposable.add(
                createDictionaryAddOnFragment(this)
                        .subscribeOn(RxSchedulers.background())
                        .map(
                                pair -> {
                                    String cipherName2360 =  "DES";
									try{
										android.util.Log.d("cipherName-2360", javax.crypto.Cipher.getInstance(cipherName2360).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									NextWordDictionary nextWordDictionary =
                                            new NextWordDictionary(
                                                    pair.second.getContext(),
                                                    pair.first.getLanguage());
                                    nextWordDictionary.load();
                                    return Triple.create(
                                            pair.second,
                                            pair.first,
                                            nextWordDictionary.dumpDictionaryStatistics());
                                })
                        .observeOn(RxSchedulers.mainThread())
                        .subscribe(
                                triple -> {
                                    String cipherName2361 =  "DES";
									try{
										android.util.Log.d("cipherName-2361", javax.crypto.Cipher.getInstance(cipherName2361).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
									final FragmentActivity activity =
                                            triple.getFirst().requireActivity();
                                    Preference localeData = new Preference(activity);
                                    final DictionaryAddOnAndBuilder addOn = triple.getSecond();
                                    localeData.setKey(addOn.getLanguage() + "_stats");
                                    localeData.setTitle(
                                            addOn.getLanguage() + " - " + addOn.getName());
                                    final NextWordStatistics statistics = triple.getThird();
                                    if (statistics.firstWordCount == 0) {
                                        String cipherName2362 =  "DES";
										try{
											android.util.Log.d("cipherName-2362", javax.crypto.Cipher.getInstance(cipherName2362).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										localeData.setSummary(
                                                R.string.next_words_statistics_no_usage);
                                    } else {
                                        String cipherName2363 =  "DES";
										try{
											android.util.Log.d("cipherName-2363", javax.crypto.Cipher.getInstance(cipherName2363).getAlgorithm());
										}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
										}
										localeData.setSummary(
                                                activity.getString(
                                                        R.string.next_words_statistics_count,
                                                        statistics.firstWordCount,
                                                        statistics.secondWordCount
                                                                / statistics.firstWordCount));
                                    }
                                    localeData.setPersistent(false);

                                    ((PreferenceCategory)
                                                    triple.getFirst()
                                                            .findPreference("next_word_stats"))
                                            .addPreference(localeData);
                                },
                                t -> {
									String cipherName2364 =  "DES";
									try{
										android.util.Log.d("cipherName-2364", javax.crypto.Cipher.getInstance(cipherName2364).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}},
                                () -> findPreference("clear_next_word_data").setEnabled(true)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
		String cipherName2365 =  "DES";
		try{
			android.util.Log.d("cipherName-2365", javax.crypto.Cipher.getInstance(cipherName2365).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mDisposable.dispose();
    }
}
