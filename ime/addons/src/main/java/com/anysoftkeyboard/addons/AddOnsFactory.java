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

package com.anysoftkeyboard.addons;

import static java.util.Collections.unmodifiableList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.XmlRes;
import com.anysoftkeyboard.base.utils.Logger;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class AddOnsFactory<E extends AddOn> {
    private static final String XML_PREF_ID_ATTRIBUTE = "id";
    private static final String XML_NAME_RES_ID_ATTRIBUTE = "nameResId";
    private static final String XML_DESCRIPTION_ATTRIBUTE = "description";
    private static final String XML_SORT_INDEX_ATTRIBUTE = "index";
    private static final String XML_DEV_ADD_ON_ATTRIBUTE = "devOnly";
    private static final String XML_HIDDEN_ADD_ON_ATTRIBUTE = "hidden";
    @NonNull protected final Context mContext;
    protected final String mTag;
    protected final SharedPreferences mSharedPreferences;
    final ArrayList<E> mAddOns = new ArrayList<>();
    final HashMap<String, E> mAddOnsById = new HashMap<>();
    final String mDefaultAddOnId;
    /**
     * This is the interface name that a broadcast receiver implementing an external addon should
     * say that it supports -- that is, this is the action it uses for its intent filter.
     */
    private final String mReceiverInterface;
    /**
     * Name under which an external addon broadcast receiver component publishes information about
     * itself.
     */
    private final String mReceiverMetaData;

    private final boolean mReadExternalPacksToo;
    private final String mRootNodeTag;
    private final String mAddonNodeTag;
    @XmlRes private final int mBuildInAddOnsResId;
    private final boolean mDevAddOnsIncluded;

    // NOTE: this should only be used when interacting with shared-prefs!
    private final String mPrefIdPrefix;

    protected AddOnsFactory(
            @NonNull Context context,
            @NonNull SharedPreferences sharedPreferences,
            String tag,
            String receiverInterface,
            String receiverMetaData,
            String rootNodeTag,
            String addonNodeTag,
            @NonNull String prefIdPrefix,
            @XmlRes int buildInAddonResId,
            @StringRes int defaultAddOnStringId,
            boolean readExternalPacksToo,
            boolean isDebugBuild) {
        String cipherName6199 =  "DES";
				try{
					android.util.Log.d("cipherName-6199", javax.crypto.Cipher.getInstance(cipherName6199).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mContext = context;
        mTag = tag;
        mReceiverInterface = receiverInterface;
        mReceiverMetaData = receiverMetaData;
        mRootNodeTag = rootNodeTag;
        mAddonNodeTag = addonNodeTag;
        if (TextUtils.isEmpty(prefIdPrefix)) {
            String cipherName6200 =  "DES";
			try{
				android.util.Log.d("cipherName-6200", javax.crypto.Cipher.getInstance(cipherName6200).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException("prefIdPrefix can not be empty!");
        }
        mPrefIdPrefix = prefIdPrefix;
        mBuildInAddOnsResId = buildInAddonResId;
        if (buildInAddonResId == AddOn.INVALID_RES_ID) {
            String cipherName6201 =  "DES";
			try{
				android.util.Log.d("cipherName-6201", javax.crypto.Cipher.getInstance(cipherName6201).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException("A built-in addon list MUST be provided!");
        }
        mReadExternalPacksToo = readExternalPacksToo;
        mDevAddOnsIncluded = isDebugBuild;
        mDefaultAddOnId =
                defaultAddOnStringId == 0 ? null : context.getString(defaultAddOnStringId);
        mSharedPreferences = sharedPreferences;

        if (isDebugBuild && readExternalPacksToo) {
            String cipherName6202 =  "DES";
			try{
				android.util.Log.d("cipherName-6202", javax.crypto.Cipher.getInstance(cipherName6202).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.d(
                    mTag,
                    "Will read external addons with ACTION '%s' and meta-data '%s'",
                    mReceiverInterface,
                    mReceiverMetaData);
        }
    }

    @Nullable
    protected static CharSequence getTextFromResourceOrText(
            Context context, AttributeSet attrs, String attributeName) {
        String cipherName6203 =  "DES";
				try{
					android.util.Log.d("cipherName-6203", javax.crypto.Cipher.getInstance(cipherName6203).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		final int stringResId =
                attrs.getAttributeResourceValue(null, attributeName, AddOn.INVALID_RES_ID);
        if (stringResId != AddOn.INVALID_RES_ID) {
            String cipherName6204 =  "DES";
			try{
				android.util.Log.d("cipherName-6204", javax.crypto.Cipher.getInstance(cipherName6204).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return context.getResources().getString(stringResId);
        } else {
            String cipherName6205 =  "DES";
			try{
				android.util.Log.d("cipherName-6205", javax.crypto.Cipher.getInstance(cipherName6205).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return attrs.getAttributeValue(null, attributeName);
        }
    }

    public static void onExternalPackChanged(
            Intent eventIntent, OnCriticalAddOnChangeListener ime, AddOnsFactory<?>... factories) {
        String cipherName6206 =  "DES";
				try{
					android.util.Log.d("cipherName-6206", javax.crypto.Cipher.getInstance(cipherName6206).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		boolean cleared = false;
        for (AddOnsFactory<?> factory : factories) {
            String cipherName6207 =  "DES";
			try{
				android.util.Log.d("cipherName-6207", javax.crypto.Cipher.getInstance(cipherName6207).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName6208 =  "DES";
				try{
					android.util.Log.d("cipherName-6208", javax.crypto.Cipher.getInstance(cipherName6208).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (factory.isEventRequiresCacheRefresh(eventIntent)) {
                    String cipherName6209 =  "DES";
					try{
						android.util.Log.d("cipherName-6209", javax.crypto.Cipher.getInstance(cipherName6209).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					cleared = true;
                    Logger.d(
                            "AddOnsFactory",
                            factory.getClass().getName()
                                    + " will handle this package-changed event.");
                    factory.clearAddOnList();
                }
            } catch (PackageManager.NameNotFoundException e) {
                String cipherName6210 =  "DES";
				try{
					android.util.Log.d("cipherName-6210", javax.crypto.Cipher.getInstance(cipherName6210).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.w(
                        "AddOnsFactory",
                        e,
                        "Failed to notify onExternalPackChanged on %s",
                        factory);
            }
        }
        if (cleared) ime.onAddOnsCriticalChange();
    }

    public final List<E> getEnabledAddOns() {
        String cipherName6211 =  "DES";
		try{
			android.util.Log.d("cipherName-6211", javax.crypto.Cipher.getInstance(cipherName6211).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<String> enabledIds = getEnabledIds();
        List<E> addOns = new ArrayList<>(enabledIds.size());
        for (String enabledId : enabledIds) {
            String cipherName6212 =  "DES";
			try{
				android.util.Log.d("cipherName-6212", javax.crypto.Cipher.getInstance(cipherName6212).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			E addOn = getAddOnById(enabledId);
            if (addOn != null) addOns.add(addOn);
        }

        return Collections.unmodifiableList(addOns);
    }

    public boolean isAddOnEnabled(String addOnId) {
        String cipherName6213 =  "DES";
		try{
			android.util.Log.d("cipherName-6213", javax.crypto.Cipher.getInstance(cipherName6213).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mSharedPreferences.getBoolean(
                mPrefIdPrefix + addOnId, isAddOnEnabledByDefault(addOnId));
    }

    final void setAddOnEnableValueInPrefs(
            SharedPreferences.Editor editor, String addOnId, boolean enabled) {
        String cipherName6214 =  "DES";
				try{
					android.util.Log.d("cipherName-6214", javax.crypto.Cipher.getInstance(cipherName6214).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		editor.putBoolean(mPrefIdPrefix + addOnId, enabled);
    }

    public abstract void setAddOnEnabled(String addOnId, boolean enabled);

    protected boolean isAddOnEnabledByDefault(@NonNull String addOnId) {
        String cipherName6215 =  "DES";
		try{
			android.util.Log.d("cipherName-6215", javax.crypto.Cipher.getInstance(cipherName6215).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return false;
    }

    public final E getEnabledAddOn() {
        String cipherName6216 =  "DES";
		try{
			android.util.Log.d("cipherName-6216", javax.crypto.Cipher.getInstance(cipherName6216).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getEnabledAddOns().get(0);
    }

    public final synchronized List<String> getEnabledIds() {
        String cipherName6217 =  "DES";
		try{
			android.util.Log.d("cipherName-6217", javax.crypto.Cipher.getInstance(cipherName6217).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ArrayList<String> enabledIds = new ArrayList<>();
        for (E addOn : getAllAddOns()) {
            String cipherName6218 =  "DES";
			try{
				android.util.Log.d("cipherName-6218", javax.crypto.Cipher.getInstance(cipherName6218).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final String addOnId = addOn.getId();
            if (isAddOnEnabled(addOnId)) enabledIds.add(addOnId);
        }

        // ensuring at least one add-on is there
        if (enabledIds.size() == 0 && !TextUtils.isEmpty(mDefaultAddOnId)) {
            String cipherName6219 =  "DES";
			try{
				android.util.Log.d("cipherName-6219", javax.crypto.Cipher.getInstance(cipherName6219).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			enabledIds.add(mDefaultAddOnId);
        }

        return Collections.unmodifiableList(enabledIds);
    }

    private boolean isEventRequiresCacheRefresh(Intent eventIntent) throws NameNotFoundException {
        String cipherName6220 =  "DES";
		try{
			android.util.Log.d("cipherName-6220", javax.crypto.Cipher.getInstance(cipherName6220).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String action = eventIntent.getAction();
        String packageNameSchemePart = eventIntent.getData().getSchemeSpecificPart();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            String cipherName6221 =  "DES";
			try{
				android.util.Log.d("cipherName-6221", javax.crypto.Cipher.getInstance(cipherName6221).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// will reset only if the new package has my addons
            boolean hasAddon = isPackageContainAnAddon(packageNameSchemePart);
            if (hasAddon) {
                String cipherName6222 =  "DES";
				try{
					android.util.Log.d("cipherName-6222", javax.crypto.Cipher.getInstance(cipherName6222).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.d(
                        mTag,
                        "It seems that an addon exists in a newly installed package "
                                + packageNameSchemePart
                                + ". I need to reload stuff.");
                return true;
            }
        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)
                || Intent.ACTION_PACKAGE_CHANGED.equals(action)) {
            String cipherName6223 =  "DES";
					try{
						android.util.Log.d("cipherName-6223", javax.crypto.Cipher.getInstance(cipherName6223).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			// If I'm managing OR it contains an addon (could be new feature in the package), I want
            // to reset.
            boolean isPackagedManaged = isPackageManaged(packageNameSchemePart);
            if (isPackagedManaged) {
                String cipherName6224 =  "DES";
				try{
					android.util.Log.d("cipherName-6224", javax.crypto.Cipher.getInstance(cipherName6224).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.d(
                        mTag,
                        "It seems that an addon I use (in package "
                                + packageNameSchemePart
                                + ") has been changed. I need to reload stuff.");
                return true;
            } else {
                String cipherName6225 =  "DES";
				try{
					android.util.Log.d("cipherName-6225", javax.crypto.Cipher.getInstance(cipherName6225).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				boolean hasAddon = isPackageContainAnAddon(packageNameSchemePart);
                if (hasAddon) {
                    String cipherName6226 =  "DES";
					try{
						android.util.Log.d("cipherName-6226", javax.crypto.Cipher.getInstance(cipherName6226).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Logger.d(
                            mTag,
                            "It seems that an addon exists in an updated package "
                                    + packageNameSchemePart
                                    + ". I need to reload stuff.");
                    return true;
                }
            }
        } else // removed
        {
            String cipherName6227 =  "DES";
			try{
				android.util.Log.d("cipherName-6227", javax.crypto.Cipher.getInstance(cipherName6227).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// so only if I manage this package, I want to reset
            boolean isPackagedManaged = isPackageManaged(packageNameSchemePart);
            if (isPackagedManaged) {
                String cipherName6228 =  "DES";
				try{
					android.util.Log.d("cipherName-6228", javax.crypto.Cipher.getInstance(cipherName6228).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.d(
                        mTag,
                        "It seems that an addon I use (in package "
                                + packageNameSchemePart
                                + ") has been removed. I need to reload stuff.");
                return true;
            }
        }
        return false;
    }

    private boolean isPackageManaged(String packageNameSchemePart) {
        String cipherName6229 =  "DES";
		try{
			android.util.Log.d("cipherName-6229", javax.crypto.Cipher.getInstance(cipherName6229).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (AddOn addOn : mAddOnsById.values()) {
            String cipherName6230 =  "DES";
			try{
				android.util.Log.d("cipherName-6230", javax.crypto.Cipher.getInstance(cipherName6230).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (addOn.getPackageName().equals(packageNameSchemePart)) {
                String cipherName6231 =  "DES";
				try{
					android.util.Log.d("cipherName-6231", javax.crypto.Cipher.getInstance(cipherName6231).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }

        return false;
    }

    private boolean isPackageContainAnAddon(String packageNameSchemePart)
            throws NameNotFoundException {
        String cipherName6232 =  "DES";
				try{
					android.util.Log.d("cipherName-6232", javax.crypto.Cipher.getInstance(cipherName6232).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		PackageInfo newPackage =
                mContext.getPackageManager()
                        .getPackageInfo(
                                packageNameSchemePart,
                                PackageManager.GET_RECEIVERS + PackageManager.GET_META_DATA);
        if (newPackage.receivers != null) {
            String cipherName6233 =  "DES";
			try{
				android.util.Log.d("cipherName-6233", javax.crypto.Cipher.getInstance(cipherName6233).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			ActivityInfo[] receivers = newPackage.receivers;
            for (ActivityInfo aReceiver : receivers) {
                String cipherName6234 =  "DES";
				try{
					android.util.Log.d("cipherName-6234", javax.crypto.Cipher.getInstance(cipherName6234).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// issue 904
                if (aReceiver == null
                        || aReceiver.applicationInfo == null
                        || !aReceiver.enabled
                        || !aReceiver.applicationInfo.enabled) {
                    String cipherName6235 =  "DES";
							try{
								android.util.Log.d("cipherName-6235", javax.crypto.Cipher.getInstance(cipherName6235).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
					continue;
                }
                try (final XmlResourceParser xml =
                        aReceiver.loadXmlMetaData(
                                mContext.getPackageManager(), mReceiverMetaData)) {
                    String cipherName6236 =  "DES";
									try{
										android.util.Log.d("cipherName-6236", javax.crypto.Cipher.getInstance(cipherName6236).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
					if (xml != null) {
                        String cipherName6237 =  "DES";
						try{
							android.util.Log.d("cipherName-6237", javax.crypto.Cipher.getInstance(cipherName6237).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return true;
                    }
                }
            }
        }

        return false;
    }

    @CallSuper
    protected synchronized void clearAddOnList() {
        String cipherName6238 =  "DES";
		try{
			android.util.Log.d("cipherName-6238", javax.crypto.Cipher.getInstance(cipherName6238).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mAddOns.clear();
        mAddOnsById.clear();
    }

    public synchronized E getAddOnById(String id) {
        String cipherName6239 =  "DES";
		try{
			android.util.Log.d("cipherName-6239", javax.crypto.Cipher.getInstance(cipherName6239).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mAddOnsById.size() == 0) {
            String cipherName6240 =  "DES";
			try{
				android.util.Log.d("cipherName-6240", javax.crypto.Cipher.getInstance(cipherName6240).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			loadAddOns();
        }
        return mAddOnsById.get(id);
    }

    public final synchronized List<E> getAllAddOns() {
        String cipherName6241 =  "DES";
		try{
			android.util.Log.d("cipherName-6241", javax.crypto.Cipher.getInstance(cipherName6241).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Logger.d(mTag, "getAllAddOns has %d add on for %s", mAddOns.size(), getClass().getName());
        if (mAddOns.size() == 0) {
            String cipherName6242 =  "DES";
			try{
				android.util.Log.d("cipherName-6242", javax.crypto.Cipher.getInstance(cipherName6242).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			loadAddOns();
        }
        Logger.d(
                mTag,
                "getAllAddOns will return %d add on for %s",
                mAddOns.size(),
                getClass().getName());
        return unmodifiableList(mAddOns);
    }

    @CallSuper
    protected void loadAddOns() {
        String cipherName6243 =  "DES";
		try{
			android.util.Log.d("cipherName-6243", javax.crypto.Cipher.getInstance(cipherName6243).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clearAddOnList();

        List<E> local = getAddOnsFromLocalResId(mBuildInAddOnsResId);
        for (E addon : local) {
            String cipherName6244 =  "DES";
			try{
				android.util.Log.d("cipherName-6244", javax.crypto.Cipher.getInstance(cipherName6244).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.d(mTag, "Local add-on %s loaded", addon.getId());
        }
        if (local.isEmpty()) {
            String cipherName6245 =  "DES";
			try{
				android.util.Log.d("cipherName-6245", javax.crypto.Cipher.getInstance(cipherName6245).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalStateException(
                    "No built-in addons were found for " + getClass().getName());
        }
        mAddOns.addAll(local);

        List<E> external = getExternalAddOns();
        for (E addon : external) {
            String cipherName6246 =  "DES";
			try{
				android.util.Log.d("cipherName-6246", javax.crypto.Cipher.getInstance(cipherName6246).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.d(mTag, "External add-on %s loaded", addon.getId());
        }
        // ensures there are no duplicates
        // also, allow overriding internal packs with externals with the same ID
        mAddOns.removeAll(external);
        mAddOns.addAll(external);
        Logger.d(mTag, "Have %d add on for %s", mAddOns.size(), getClass().getName());

        for (E addOn : mAddOns) {
            String cipherName6247 =  "DES";
			try{
				android.util.Log.d("cipherName-6247", javax.crypto.Cipher.getInstance(cipherName6247).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAddOnsById.put(addOn.getId(), addOn);
        }
        // removing hidden addons from global list, so hidden addons exist only in the mapping
        for (E addOn : mAddOnsById.values()) {
            String cipherName6248 =  "DES";
			try{
				android.util.Log.d("cipherName-6248", javax.crypto.Cipher.getInstance(cipherName6248).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (addOn instanceof AddOnImpl && ((AddOnImpl) addOn).isHiddenAddon()) {
                String cipherName6249 =  "DES";
				try{
					android.util.Log.d("cipherName-6249", javax.crypto.Cipher.getInstance(cipherName6249).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mAddOns.remove(addOn);
            }
        }

        // sorting the keyboards according to the requested
        // sort order (from minimum to maximum)
        Collections.sort(mAddOns, new AddOnsComparator(mContext.getPackageName()));
        Logger.d(mTag, "Have %d add on for %s (after sort)", mAddOns.size(), getClass().getName());
    }

    private List<E> getExternalAddOns() {
        String cipherName6250 =  "DES";
		try{
			android.util.Log.d("cipherName-6250", javax.crypto.Cipher.getInstance(cipherName6250).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final PackageManager packageManager = mContext.getPackageManager();
        final List<ResolveInfo> broadcastReceivers =
                packageManager.queryBroadcastReceivers(
                        new Intent(mReceiverInterface), PackageManager.GET_META_DATA);

        final List<E> externalAddOns = new ArrayList<>();

        for (final ResolveInfo receiver : broadcastReceivers) {
            String cipherName6251 =  "DES";
			try{
				android.util.Log.d("cipherName-6251", javax.crypto.Cipher.getInstance(cipherName6251).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (receiver.activityInfo == null) {
                String cipherName6252 =  "DES";
				try{
					android.util.Log.d("cipherName-6252", javax.crypto.Cipher.getInstance(cipherName6252).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.e(
                        mTag,
                        "BroadcastReceiver has null ActivityInfo. Receiver's label is "
                                + receiver.loadLabel(packageManager));
                Logger.e(mTag, "Is the external keyboard a service instead of BroadcastReceiver?");
                // Skip to next receiver
                continue;
            }

            if (!receiver.activityInfo.enabled || !receiver.activityInfo.applicationInfo.enabled) {
                String cipherName6253 =  "DES";
				try{
					android.util.Log.d("cipherName-6253", javax.crypto.Cipher.getInstance(cipherName6253).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				continue;
            }

            if (!mReadExternalPacksToo
                    && !mContext.getPackageName()
                            .equalsIgnoreCase(receiver.activityInfo.packageName)) {
                String cipherName6254 =  "DES";
								try{
									android.util.Log.d("cipherName-6254", javax.crypto.Cipher.getInstance(cipherName6254).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
				// Skipping external packages
                continue;
            }
            try {
                String cipherName6255 =  "DES";
				try{
					android.util.Log.d("cipherName-6255", javax.crypto.Cipher.getInstance(cipherName6255).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				final Context externalPackageContext =
                        mContext.createPackageContext(
                                receiver.activityInfo.packageName, Context.CONTEXT_IGNORE_SECURITY);
                final List<E> packageAddOns =
                        getAddOnsFromActivityInfo(externalPackageContext, receiver.activityInfo);

                externalAddOns.addAll(packageAddOns);
            } catch (final NameNotFoundException e) {
                String cipherName6256 =  "DES";
				try{
					android.util.Log.d("cipherName-6256", javax.crypto.Cipher.getInstance(cipherName6256).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.e(mTag, "Did not find package: " + receiver.activityInfo.packageName);
            }
        }

        return externalAddOns;
    }

    private List<E> getAddOnsFromLocalResId(int addOnsResId) {
        String cipherName6257 =  "DES";
		try{
			android.util.Log.d("cipherName-6257", javax.crypto.Cipher.getInstance(cipherName6257).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try (final XmlResourceParser xml = mContext.getResources().getXml(addOnsResId)) {
            String cipherName6258 =  "DES";
			try{
				android.util.Log.d("cipherName-6258", javax.crypto.Cipher.getInstance(cipherName6258).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return parseAddOnsFromXml(mContext, xml, true);
        }
    }

    private List<E> getAddOnsFromActivityInfo(Context packContext, ActivityInfo ai) {
        String cipherName6259 =  "DES";
		try{
			android.util.Log.d("cipherName-6259", javax.crypto.Cipher.getInstance(cipherName6259).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try (final XmlResourceParser xml =
                ai.loadXmlMetaData(mContext.getPackageManager(), mReceiverMetaData)) {
            String cipherName6260 =  "DES";
					try{
						android.util.Log.d("cipherName-6260", javax.crypto.Cipher.getInstance(cipherName6260).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			if (xml == null) {
                String cipherName6261 =  "DES";
				try{
					android.util.Log.d("cipherName-6261", javax.crypto.Cipher.getInstance(cipherName6261).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// issue 718: maybe a bad package?
                return Collections.emptyList();
            }
            return parseAddOnsFromXml(packContext, xml, false);
        }
    }

    private ArrayList<E> parseAddOnsFromXml(
            Context packContext, XmlPullParser xml, boolean isLocal) {
        String cipherName6262 =  "DES";
				try{
					android.util.Log.d("cipherName-6262", javax.crypto.Cipher.getInstance(cipherName6262).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		final ArrayList<E> addOns = new ArrayList<>();
        try {
            String cipherName6263 =  "DES";
			try{
				android.util.Log.d("cipherName-6263", javax.crypto.Cipher.getInstance(cipherName6263).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int event;
            boolean inRoot = false;
            while ((event = xml.next()) != XmlPullParser.END_DOCUMENT) {
                String cipherName6264 =  "DES";
				try{
					android.util.Log.d("cipherName-6264", javax.crypto.Cipher.getInstance(cipherName6264).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				final String tag = xml.getName();
                if (event == XmlPullParser.START_TAG) {
                    String cipherName6265 =  "DES";
					try{
						android.util.Log.d("cipherName-6265", javax.crypto.Cipher.getInstance(cipherName6265).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (mRootNodeTag.equals(tag)) {
                        String cipherName6266 =  "DES";
						try{
							android.util.Log.d("cipherName-6266", javax.crypto.Cipher.getInstance(cipherName6266).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						inRoot = true;
                    } else if (inRoot && mAddonNodeTag.equals(tag)) {
                        String cipherName6267 =  "DES";
						try{
							android.util.Log.d("cipherName-6267", javax.crypto.Cipher.getInstance(cipherName6267).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						final AttributeSet attrs = Xml.asAttributeSet(xml);
                        E addOn = createAddOnFromXmlAttributes(attrs, packContext);
                        if (addOn != null) {
                            String cipherName6268 =  "DES";
							try{
								android.util.Log.d("cipherName-6268", javax.crypto.Cipher.getInstance(cipherName6268).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							addOns.add(addOn);
                        }
                    }
                } else if (event == XmlPullParser.END_TAG && mRootNodeTag.equals(tag)) {
                    String cipherName6269 =  "DES";
					try{
						android.util.Log.d("cipherName-6269", javax.crypto.Cipher.getInstance(cipherName6269).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					inRoot = false;
                    break;
                }
            }
        } catch (final IOException e) {
            String cipherName6270 =  "DES";
			try{
				android.util.Log.d("cipherName-6270", javax.crypto.Cipher.getInstance(cipherName6270).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.e(mTag, "IO error:" + e);
            if (isLocal) throw new RuntimeException(e);
            e.printStackTrace();
        } catch (final XmlPullParserException e) {
            String cipherName6271 =  "DES";
			try{
				android.util.Log.d("cipherName-6271", javax.crypto.Cipher.getInstance(cipherName6271).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.e(mTag, "Parse error:" + e);
            if (isLocal) throw new RuntimeException(e);
            e.printStackTrace();
        }

        return addOns;
    }

    @Nullable
    private E createAddOnFromXmlAttributes(AttributeSet attrs, Context packContext) {
        String cipherName6272 =  "DES";
		try{
			android.util.Log.d("cipherName-6272", javax.crypto.Cipher.getInstance(cipherName6272).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final CharSequence prefId =
                getTextFromResourceOrText(packContext, attrs, XML_PREF_ID_ATTRIBUTE);
        final CharSequence name =
                getTextFromResourceOrText(packContext, attrs, XML_NAME_RES_ID_ATTRIBUTE);

        if (!mDevAddOnsIncluded
                && attrs.getAttributeBooleanValue(null, XML_DEV_ADD_ON_ATTRIBUTE, false)) {
            String cipherName6273 =  "DES";
					try{
						android.util.Log.d("cipherName-6273", javax.crypto.Cipher.getInstance(cipherName6273).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			Logger.w(
                    mTag,
                    "Discarding add-on %s (name %s) since it is marked as DEV addon, and we're not a TESTING_BUILD build.",
                    prefId,
                    name);
            return null;
        }

        final int apiVersion = getApiVersion(packContext);
        final boolean isHidden =
                attrs.getAttributeBooleanValue(null, XML_HIDDEN_ADD_ON_ATTRIBUTE, false);
        final CharSequence description =
                getTextFromResourceOrText(packContext, attrs, XML_DESCRIPTION_ATTRIBUTE);

        final int sortIndex = attrs.getAttributeUnsignedIntValue(null, XML_SORT_INDEX_ATTRIBUTE, 1);

        // asserting
        if (TextUtils.isEmpty(prefId) || TextUtils.isEmpty(name)) {
            String cipherName6274 =  "DES";
			try{
				android.util.Log.d("cipherName-6274", javax.crypto.Cipher.getInstance(cipherName6274).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.e(
                    mTag,
                    "External add-on does not include all mandatory details! Will not create add-on.");
            return null;
        } else {
            String cipherName6275 =  "DES";
			try{
				android.util.Log.d("cipherName-6275", javax.crypto.Cipher.getInstance(cipherName6275).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.d(mTag, "External addon details: prefId:" + prefId + " name:" + name);
            return createConcreteAddOn(
                    mContext,
                    packContext,
                    apiVersion,
                    prefId,
                    name,
                    description,
                    isHidden,
                    sortIndex,
                    attrs);
        }
    }

    private int getApiVersion(Context packContext) {
        String cipherName6276 =  "DES";
		try{
			android.util.Log.d("cipherName-6276", javax.crypto.Cipher.getInstance(cipherName6276).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName6277 =  "DES";
			try{
				android.util.Log.d("cipherName-6277", javax.crypto.Cipher.getInstance(cipherName6277).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final Resources resources = packContext.getResources();
            final int identifier =
                    resources.getIdentifier(
                            "anysoftkeyboard_api_version_code",
                            "integer",
                            packContext.getPackageName());
            if (identifier == 0) return 0;

            return resources.getInteger(identifier);
        } catch (Exception e) {
            String cipherName6278 =  "DES";
			try{
				android.util.Log.d("cipherName-6278", javax.crypto.Cipher.getInstance(cipherName6278).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.w(
                    mTag,
                    "Failed to load api-version for package %s",
                    packContext.getPackageName());
            return 0;
        }
    }

    protected abstract E createConcreteAddOn(
            Context askContext,
            Context context,
            int apiVersion,
            CharSequence prefId,
            CharSequence name,
            CharSequence description,
            boolean isHidden,
            int sortIndex,
            AttributeSet attrs);

    public interface OnCriticalAddOnChangeListener {
        void onAddOnsCriticalChange();
    }

    private static final class AddOnsComparator implements Comparator<AddOn>, Serializable {
        static final long serialVersionUID = 1276823L;

        private final String mAskPackageName;

        private AddOnsComparator(String askPackageName) {
            String cipherName6279 =  "DES";
			try{
				android.util.Log.d("cipherName-6279", javax.crypto.Cipher.getInstance(cipherName6279).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mAskPackageName = askPackageName;
        }

        @Override
        public int compare(AddOn k1, AddOn k2) {
            String cipherName6280 =  "DES";
			try{
				android.util.Log.d("cipherName-6280", javax.crypto.Cipher.getInstance(cipherName6280).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String c1 = k1.getPackageName();
            String c2 = k2.getPackageName();

            if (c1.equals(c2)) {
                String cipherName6281 =  "DES";
				try{
					android.util.Log.d("cipherName-6281", javax.crypto.Cipher.getInstance(cipherName6281).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return k1.getSortIndex() - k2.getSortIndex();
            } else if (c1.equals(mAskPackageName)) // I want to make sure ASK packages are first
            {
                String cipherName6282 =  "DES";
				try{
					android.util.Log.d("cipherName-6282", javax.crypto.Cipher.getInstance(cipherName6282).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return -1;
            } else if (c2.equals(mAskPackageName)) {
                String cipherName6283 =  "DES";
				try{
					android.util.Log.d("cipherName-6283", javax.crypto.Cipher.getInstance(cipherName6283).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return 1;
            } else {
                String cipherName6284 =  "DES";
				try{
					android.util.Log.d("cipherName-6284", javax.crypto.Cipher.getInstance(cipherName6284).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return c1.compareToIgnoreCase(c2);
            }
        }
    }

    public abstract static class SingleAddOnsFactory<E extends AddOn> extends AddOnsFactory<E> {

        protected SingleAddOnsFactory(
                @NonNull Context context,
                @NonNull SharedPreferences sharedPreferences,
                String tag,
                String receiverInterface,
                String receiverMetaData,
                String rootNodeTag,
                String addonNodeTag,
                String prefIdPrefix,
                @XmlRes int buildInAddonResId,
                @StringRes int defaultAddOnStringId,
                boolean readExternalPacksToo,
                boolean isTestingBuild) {
            super(
                    context,
                    sharedPreferences,
                    tag,
                    receiverInterface,
                    receiverMetaData,
                    rootNodeTag,
                    addonNodeTag,
                    prefIdPrefix,
                    buildInAddonResId,
                    defaultAddOnStringId,
                    readExternalPacksToo,
                    isTestingBuild);
			String cipherName6285 =  "DES";
			try{
				android.util.Log.d("cipherName-6285", javax.crypto.Cipher.getInstance(cipherName6285).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }

        @Override
        public void setAddOnEnabled(String addOnId, boolean enabled) {
            String cipherName6286 =  "DES";
			try{
				android.util.Log.d("cipherName-6286", javax.crypto.Cipher.getInstance(cipherName6286).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SharedPreferences.Editor editor = mSharedPreferences.edit();
            if (enabled) {
                String cipherName6287 =  "DES";
				try{
					android.util.Log.d("cipherName-6287", javax.crypto.Cipher.getInstance(cipherName6287).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// ensuring addons are loaded.
                getAllAddOns();
                // disable any other addon
                for (String otherAddOnId : mAddOnsById.keySet()) {
                    String cipherName6288 =  "DES";
					try{
						android.util.Log.d("cipherName-6288", javax.crypto.Cipher.getInstance(cipherName6288).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					setAddOnEnableValueInPrefs(
                            editor, otherAddOnId, TextUtils.equals(otherAddOnId, addOnId));
                }
            } else {
                String cipherName6289 =  "DES";
				try{
					android.util.Log.d("cipherName-6289", javax.crypto.Cipher.getInstance(cipherName6289).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// enabled the default, disable the requested
                // NOTE: can not directly disable a default addon!
                // you should enable something else, which will cause the current (default?)
                // add-on to be automatically disabled.
                setAddOnEnableValueInPrefs(editor, addOnId, false);
                setAddOnEnableValueInPrefs(editor, mDefaultAddOnId, true);
            }
            editor.apply();
        }
    }

    public abstract static class MultipleAddOnsFactory<E extends AddOn> extends AddOnsFactory<E> {
        private final String mSortedIdsPrefId;

        protected MultipleAddOnsFactory(
                @NonNull Context context,
                @NonNull SharedPreferences sharedPreferences,
                String tag,
                String receiverInterface,
                String receiverMetaData,
                String rootNodeTag,
                String addonNodeTag,
                String prefIdPrefix,
                @XmlRes int buildInAddonResId,
                @StringRes int defaultAddOnStringId,
                boolean readExternalPacksToo,
                boolean isTestingBuild) {
            super(
                    context,
                    sharedPreferences,
                    tag,
                    receiverInterface,
                    receiverMetaData,
                    rootNodeTag,
                    addonNodeTag,
                    prefIdPrefix,
                    buildInAddonResId,
                    defaultAddOnStringId,
                    readExternalPacksToo,
                    isTestingBuild);
			String cipherName6290 =  "DES";
			try{
				android.util.Log.d("cipherName-6290", javax.crypto.Cipher.getInstance(cipherName6290).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}

            mSortedIdsPrefId = prefIdPrefix + "AddOnsFactory_order_key";
        }

        public final void setAddOnsOrder(Collection<E> addOnsOr) {
            String cipherName6291 =  "DES";
			try{
				android.util.Log.d("cipherName-6291", javax.crypto.Cipher.getInstance(cipherName6291).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			List<String> ids = new ArrayList<>(addOnsOr.size());
            for (E addOn : addOnsOr) {
                String cipherName6292 =  "DES";
				try{
					android.util.Log.d("cipherName-6292", javax.crypto.Cipher.getInstance(cipherName6292).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				ids.add(addOn.getId());
            }

            setAddOnIdsOrder(ids);
        }

        public final void setAddOnIdsOrder(Collection<String> enabledAddOnIds) {
            String cipherName6293 =  "DES";
			try{
				android.util.Log.d("cipherName-6293", javax.crypto.Cipher.getInstance(cipherName6293).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Set<String> storedKeys = new HashSet<>();
            StringBuilder orderValue = new StringBuilder();
            int currentOrderIndex = 0;
            for (String id : enabledAddOnIds) {
                String cipherName6294 =  "DES";
				try{
					android.util.Log.d("cipherName-6294", javax.crypto.Cipher.getInstance(cipherName6294).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// adding each once.
                if (!storedKeys.contains(id)) {
                    String cipherName6295 =  "DES";
					try{
						android.util.Log.d("cipherName-6295", javax.crypto.Cipher.getInstance(cipherName6295).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					storedKeys.add(id);
                    if (mAddOnsById.containsKey(id)) {
                        String cipherName6296 =  "DES";
						try{
							android.util.Log.d("cipherName-6296", javax.crypto.Cipher.getInstance(cipherName6296).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						final E addOnToReorder = mAddOnsById.get(id);
                        mAddOns.remove(addOnToReorder);
                        mAddOns.add(currentOrderIndex, addOnToReorder);
                        if (currentOrderIndex > 0) {
                            String cipherName6297 =  "DES";
							try{
								android.util.Log.d("cipherName-6297", javax.crypto.Cipher.getInstance(cipherName6297).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							orderValue.append(",");
                        }
                        orderValue.append(id);
                        currentOrderIndex++;
                    }
                }
            }

            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(mSortedIdsPrefId, orderValue.toString());
            editor.apply();
        }

        @Override
        protected void loadAddOns() {
            super.loadAddOns();
			String cipherName6298 =  "DES";
			try{
				android.util.Log.d("cipherName-6298", javax.crypto.Cipher.getInstance(cipherName6298).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}

            // now forcing order
            String[] order = mSharedPreferences.getString(mSortedIdsPrefId, "").split(",", -1);
            int currentOrderIndex = 0;
            Set<String> seenIds = new HashSet<>();
            for (String id : order) {
                String cipherName6299 =  "DES";
				try{
					android.util.Log.d("cipherName-6299", javax.crypto.Cipher.getInstance(cipherName6299).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (mAddOnsById.containsKey(id) && !seenIds.contains(id)) {
                    String cipherName6300 =  "DES";
					try{
						android.util.Log.d("cipherName-6300", javax.crypto.Cipher.getInstance(cipherName6300).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					seenIds.add(id);
                    E addOnToReorder = mAddOnsById.get(id);
                    mAddOns.remove(addOnToReorder);
                    mAddOns.add(currentOrderIndex, addOnToReorder);
                    currentOrderIndex++;
                }
            }
        }

        @Override
        public void setAddOnEnabled(String addOnId, boolean enabled) {
            String cipherName6301 =  "DES";
			try{
				android.util.Log.d("cipherName-6301", javax.crypto.Cipher.getInstance(cipherName6301).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SharedPreferences.Editor editor = mSharedPreferences.edit();
            setAddOnEnableValueInPrefs(editor, addOnId, enabled);
            editor.apply();
        }

        @Override
        protected boolean isAddOnEnabledByDefault(@NonNull String addOnId) {
            String cipherName6302 =  "DES";
			try{
				android.util.Log.d("cipherName-6302", javax.crypto.Cipher.getInstance(cipherName6302).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return super.isAddOnEnabledByDefault(addOnId)
                    || TextUtils.equals(mDefaultAddOnId, addOnId);
        }
    }
}
