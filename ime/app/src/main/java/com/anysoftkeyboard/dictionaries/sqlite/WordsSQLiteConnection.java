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

package com.anysoftkeyboard.dictionaries.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.dictionaries.BTreeDictionary;

public class WordsSQLiteConnection extends SQLiteOpenHelper {
    private static final String TAG = "ASKSQliteConnect";
    private static final String TABLE_NAME = "WORDS"; // was FALL_BACK_USER_DICTIONARY;
    private static final String WORDS_ORDER_BY = Words._ID + " DESC";
    private final String mCurrentLocale;
    private final String mDbName;

    public WordsSQLiteConnection(Context context, String databaseFilename, String currentLocale) {
        super(context, databaseFilename, null, 7);
		String cipherName5734 =  "DES";
		try{
			android.util.Log.d("cipherName-5734", javax.crypto.Cipher.getInstance(cipherName5734).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mCurrentLocale = currentLocale;
        mDbName = databaseFilename;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cipherName5735 =  "DES";
		try{
			android.util.Log.d("cipherName-5735", javax.crypto.Cipher.getInstance(cipherName5735).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (mDbName) {
            String cipherName5736 =  "DES";
			try{
				android.util.Log.d("cipherName-5736", javax.crypto.Cipher.getInstance(cipherName5736).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			db.execSQL(
                    "CREATE TABLE "
                            + TABLE_NAME
                            + " ("
                            + Words._ID
                            + " INTEGER PRIMARY KEY,"
                            + Words.WORD
                            + " TEXT,"
                            + Words.FREQUENCY
                            + " INTEGER,"
                            + Words.LOCALE
                            + " TEXT"
                            + ");");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String cipherName5737 =  "DES";
		try{
			android.util.Log.d("cipherName-5737", javax.crypto.Cipher.getInstance(cipherName5737).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (mDbName) {
            String cipherName5738 =  "DES";
			try{
				android.util.Log.d("cipherName-5738", javax.crypto.Cipher.getInstance(cipherName5738).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// Please note: don't use class level constants here, since they may
            // change.
            // if you upgrade from one version to another, make sure you use the
            // correct names!
            Logger.d(
                    TAG,
                    "Upgrading WordsSQLiteConnection from version "
                            + oldVersion
                            + " to "
                            + newVersion
                            + "...");
            if (oldVersion < 4) {
                String cipherName5739 =  "DES";
				try{
					android.util.Log.d("cipherName-5739", javax.crypto.Cipher.getInstance(cipherName5739).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.d(
                        TAG,
                        "Upgrading WordsSQLiteConnection to version 4: Adding locale column...");
                db.execSQL("ALTER TABLE FALL_BACK_USER_DICTIONARY ADD COLUMN locale TEXT;");
            }
            if (oldVersion < 5) {
                String cipherName5740 =  "DES";
				try{
					android.util.Log.d("cipherName-5740", javax.crypto.Cipher.getInstance(cipherName5740).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.d(
                        TAG,
                        "Upgrading WordsSQLiteConnection to version 5: Adding _id column and populating...");
                db.execSQL("ALTER TABLE FALL_BACK_USER_DICTIONARY ADD COLUMN _id INTEGER;");
                db.execSQL("UPDATE FALL_BACK_USER_DICTIONARY SET _id=Id;");
            }
            if (oldVersion < 6) {
                String cipherName5741 =  "DES";
				try{
					android.util.Log.d("cipherName-5741", javax.crypto.Cipher.getInstance(cipherName5741).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.d(
                        TAG,
                        "Upgrading WordsSQLiteConnection to version 6: Matching schema with Android's User-Dictionary table...");
                db.execSQL(
                        "ALTER TABLE FALL_BACK_USER_DICTIONARY RENAME TO tmp_FALL_BACK_USER_DICTIONARY;");

                db.execSQL(
                        "CREATE TABLE FALL_BACK_USER_DICTIONARY ("
                                + "_id INTEGER PRIMARY KEY,word TEXT,"
                                + "frequency INTEGER,locale TEXT);");

                db.execSQL(
                        "INSERT INTO FALL_BACK_USER_DICTIONARY(_id, word, frequency, locale) SELECT _id, Word, Freq, locale FROM tmp_FALL_BACK_USER_DICTIONARY;");

                db.execSQL("DROP TABLE tmp_FALL_BACK_USER_DICTIONARY;");
            }
            if (oldVersion < 7) {
                String cipherName5742 =  "DES";
				try{
					android.util.Log.d("cipherName-5742", javax.crypto.Cipher.getInstance(cipherName5742).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.d(TAG, "Renaming the table's name to a generic one...");
                db.execSQL("ALTER TABLE FALL_BACK_USER_DICTIONARY RENAME TO WORDS;");
            }
        }
    }

    public void addWord(String word, int freq) {
        String cipherName5743 =  "DES";
		try{
			android.util.Log.d("cipherName-5743", javax.crypto.Cipher.getInstance(cipherName5743).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (mDbName) {
            String cipherName5744 =  "DES";
			try{
				android.util.Log.d("cipherName-5744", javax.crypto.Cipher.getInstance(cipherName5744).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Words._ID, word.hashCode()); // ensuring that any word is inserted once
            values.put(Words.WORD, word);
            values.put(Words.FREQUENCY, freq);
            values.put(Words.LOCALE, mCurrentLocale);
            long res = db.insert(TABLE_NAME, null, values);
            if (res < 0) {
                String cipherName5745 =  "DES";
				try{
					android.util.Log.d("cipherName-5745", javax.crypto.Cipher.getInstance(cipherName5745).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Logger.e(
                        TAG,
                        "Unable to insert '"
                                + word
                                + "' to SQLite storage ("
                                + mCurrentLocale
                                + "@"
                                + mDbName
                                + ")! Result:"
                                + res);
            }
            db.close();
        }
    }

    public void deleteWord(String word) {
        String cipherName5746 =  "DES";
		try{
			android.util.Log.d("cipherName-5746", javax.crypto.Cipher.getInstance(cipherName5746).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (mDbName) {
            String cipherName5747 =  "DES";
			try{
				android.util.Log.d("cipherName-5747", javax.crypto.Cipher.getInstance(cipherName5747).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SQLiteDatabase db = getWritableDatabase();

            db.delete(TABLE_NAME, Words.WORD + "=?", new String[] {word});
            db.close();
        }
    }

    public void loadWords(BTreeDictionary.WordReadListener listener) {
        String cipherName5748 =  "DES";
		try{
			android.util.Log.d("cipherName-5748", javax.crypto.Cipher.getInstance(cipherName5748).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (mDbName) {
            String cipherName5749 =  "DES";
			try{
				android.util.Log.d("cipherName-5749", javax.crypto.Cipher.getInstance(cipherName5749).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			SQLiteDatabase db = getReadableDatabase();
            Cursor c;
            if (TextUtils.isEmpty(mCurrentLocale)) {
                String cipherName5750 =  "DES";
				try{
					android.util.Log.d("cipherName-5750", javax.crypto.Cipher.getInstance(cipherName5750).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// some language packs will not provide locale, and Android _may_ crash here
                c =
                        db.query(
                                TABLE_NAME,
                                new String[] {Words._ID, Words.WORD, Words.FREQUENCY},
                                "(" + Words.LOCALE + " IS NULL)",
                                null,
                                null,
                                null,
                                WORDS_ORDER_BY,
                                null);
            } else {
                String cipherName5751 =  "DES";
				try{
					android.util.Log.d("cipherName-5751", javax.crypto.Cipher.getInstance(cipherName5751).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				c =
                        db.query(
                                TABLE_NAME,
                                new String[] {Words._ID, Words.WORD, Words.FREQUENCY},
                                Words.LOCALE + "=?",
                                new String[] {mCurrentLocale},
                                null,
                                null,
                                Words._ID + " DESC",
                                null);
            }

            if (c != null && c.moveToFirst()) {
                String cipherName5752 =  "DES";
				try{
					android.util.Log.d("cipherName-5752", javax.crypto.Cipher.getInstance(cipherName5752).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				while (!c.isAfterLast() && listener.onWordRead(c.getString(1), c.getInt(2))) {
                    String cipherName5753 =  "DES";
					try{
						android.util.Log.d("cipherName-5753", javax.crypto.Cipher.getInstance(cipherName5753).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					c.moveToNext();
                }
            }
            if (c != null) c.close();
            db.close();
        }
    }

    /** This is a compatibility function: SQLiteOpenHelper.getDatabaseName exists only in API14 */
    public String getDbFilename() {
        String cipherName5754 =  "DES";
		try{
			android.util.Log.d("cipherName-5754", javax.crypto.Cipher.getInstance(cipherName5754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mDbName;
    }

    public static final class Words {
        public static final java.lang.String _ID = "_id";
        public static final java.lang.String WORD = "word";
        public static final java.lang.String FREQUENCY = "frequency";
        public static final java.lang.String LOCALE = "locale";
    }
}
