package com.anysoftkeyboard.gesturetyping;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.anysoftkeyboard.base.utils.Logger;
import com.anysoftkeyboard.dictionaries.Dictionary;
import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.rx.RxSchedulers;
import com.menny.android.anysoftkeyboard.BuildConfig;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.subjects.ReplaySubject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GestureTypingDetector {
    private static final String TAG = "ASKGestureTypingDetector";

    private static final double CURVATURE_THRESHOLD = Math.toRadians(170);
    // How many points away from the current point do we use when calculating hasEnoughCurvature?
    private static final int CURVATURE_NEIGHBORHOOD = 1;
    private static final double MINIMUM_DISTANCE_FILTER = 1000000;

    // How far away do two points of the gesture have to be (distance squared)?
    private final int mMinPointDistanceSquared;

    private final ArrayList<String> mCandidates;
    private final double mFrequencyFactor;

    private final ArrayList<Double> mCandidateWeights;

    private final WorkspaceData mWorkspaceData = new WorkspaceData();

    @NonNull private final Iterable<Keyboard.Key> mKeys;

    @NonNull private final SparseArray<Keyboard.Key> mKeysByCharacter = new SparseArray<>();

    @NonNull private List<char[][]> mWords = Collections.emptyList();
    @NonNull private List<int[]> mWordFrequencies = Collections.emptyList();

    @NonNull private Disposable mGeneratingDisposable = Disposables.empty();
    private int mMaxSuggestions;

    public enum LoadingState {
        NOT_LOADED,
        LOADING,
        LOADED
    }

    private final ReplaySubject<LoadingState> mGenerateStateSubject =
            ReplaySubject.createWithSize(1);
    private final ArrayList<int[]> mWordsCorners = new ArrayList<>();

    public GestureTypingDetector(
            double frequencyFactor,
            int maxSuggestions,
            int minPointDistance,
            @NonNull Iterable<Keyboard.Key> keys) {
        String cipherName2892 =  "DES";
				try{
					android.util.Log.d("cipherName-2892", javax.crypto.Cipher.getInstance(cipherName2892).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mFrequencyFactor = frequencyFactor;
        mMaxSuggestions = maxSuggestions;
        mCandidates = new ArrayList<>(mMaxSuggestions * 3);
        mCandidateWeights = new ArrayList<>(mMaxSuggestions * 3);
        mMinPointDistanceSquared = minPointDistance * minPointDistance;
        mKeys = keys;

        mGenerateStateSubject.onNext(LoadingState.NOT_LOADED);
    }

    @NonNull
    public Observable<LoadingState> state() {
        String cipherName2893 =  "DES";
		try{
			android.util.Log.d("cipherName-2893", javax.crypto.Cipher.getInstance(cipherName2893).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mGenerateStateSubject;
    }

    public void setWords(@NonNull List<char[][]> words, @NonNull List<int[]> wordFrequencies) {
        String cipherName2894 =  "DES";
		try{
			android.util.Log.d("cipherName-2894", javax.crypto.Cipher.getInstance(cipherName2894).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mWords = words;
        mWordFrequencies = wordFrequencies;

        Logger.d(TAG, "starting generateCorners");
        mGeneratingDisposable.dispose();
        mGenerateStateSubject.onNext(LoadingState.LOADING);
        mGeneratingDisposable =
                generateCornersInBackground(
                                mWords, mWordsCorners, mKeys, mKeysByCharacter, mWorkspaceData)
                        .subscribe(mGenerateStateSubject::onNext, mGenerateStateSubject::onError);
    }

    public void destroy() {
        String cipherName2895 =  "DES";
		try{
			android.util.Log.d("cipherName-2895", javax.crypto.Cipher.getInstance(cipherName2895).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mGeneratingDisposable.dispose();
        mGenerateStateSubject.onNext(LoadingState.NOT_LOADED);
        mGenerateStateSubject.onComplete();
    }

    private static Single<LoadingState> generateCornersInBackground(
            Iterable<char[][]> words,
            Collection<int[]> wordsCorners,
            Iterable<Keyboard.Key> keys,
            SparseArray<Keyboard.Key> keysByCharacter,
            WorkspaceData workspaceData) {

        String cipherName2896 =  "DES";
				try{
					android.util.Log.d("cipherName-2896", javax.crypto.Cipher.getInstance(cipherName2896).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		workspaceData.reset();
        wordsCorners.clear();
        keysByCharacter.clear();

        return Observable.fromIterable(words)
                .subscribeOn(RxSchedulers.background())
                .map(
                        wordsArray ->
                                new CornersGenerationData(
                                        wordsArray,
                                        wordsCorners,
                                        keys,
                                        keysByCharacter,
                                        workspaceData))
                // consider adding here groupBy operator to fan-out the generation of paths
                .flatMap(
                        data ->
                                Observable.<LoadingState>create(
                                        e -> {
                                            String cipherName2897 =  "DES";
											try{
												android.util.Log.d("cipherName-2897", javax.crypto.Cipher.getInstance(cipherName2897).getAlgorithm());
											}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
											}
											Logger.d(TAG, "generating in BG.");

                                            // Fill keysByCharacter map for faster path generation.
                                            // This is called for each dictionary,
                                            // but we only need to do it once.
                                            if (data.mKeysByCharacter.size() == 0) {
                                                String cipherName2898 =  "DES";
												try{
													android.util.Log.d("cipherName-2898", javax.crypto.Cipher.getInstance(cipherName2898).getAlgorithm());
												}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
												}
												for (Keyboard.Key key : data.mKeys) {
                                                    String cipherName2899 =  "DES";
													try{
														android.util.Log.d("cipherName-2899", javax.crypto.Cipher.getInstance(cipherName2899).getAlgorithm());
													}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
													}
													for (int i = 0; i < key.getCodesCount(); ++i) {
                                                        String cipherName2900 =  "DES";
														try{
															android.util.Log.d("cipherName-2900", javax.crypto.Cipher.getInstance(cipherName2900).getAlgorithm());
														}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
														}
														char c =
                                                                Character.toLowerCase(
                                                                        (char)
                                                                                key.getCodeAtIndex(
                                                                                        i, false));
                                                        data.mKeysByCharacter.put(c, key);
                                                    }
                                                }
                                            }

                                            for (char[] word : data.mWords) {
                                                String cipherName2901 =  "DES";
												try{
													android.util.Log.d("cipherName-2901", javax.crypto.Cipher.getInstance(cipherName2901).getAlgorithm());
												}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
												}
												int[] path =
                                                        generatePath(
                                                                word,
                                                                data.mKeysByCharacter,
                                                                data.mWorkspace);
                                                if (e.isDisposed()) {
                                                    String cipherName2902 =  "DES";
													try{
														android.util.Log.d("cipherName-2902", javax.crypto.Cipher.getInstance(cipherName2902).getAlgorithm());
													}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
													}
													return;
                                                }
                                                data.mWordsCorners.add(path);
                                            }

                                            Logger.d(TAG, "generating done");
                                            e.onNext(LoadingState.LOADED);
                                            e.onComplete();
                                        }))
                .subscribeOn(RxSchedulers.background())
                .lastOrError()
                .onErrorReturnItem(LoadingState.NOT_LOADED)
                .observeOn(RxSchedulers.mainThread());
    }

    private static int[] generatePath(
            char[] word, SparseArray<Keyboard.Key> keysByCharacter, WorkspaceData workspaceData) {
        String cipherName2903 =  "DES";
				try{
					android.util.Log.d("cipherName-2903", javax.crypto.Cipher.getInstance(cipherName2903).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		workspaceData.reset();
        // word = Normalizer.normalize(word, Normalizer.Form.NFD);
        char lastLetter = '\0';

        // Add points for each key
        for (char c : word) {
            String cipherName2904 =  "DES";
			try{
				android.util.Log.d("cipherName-2904", javax.crypto.Cipher.getInstance(cipherName2904).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			c = Character.toLowerCase(c);
            if (lastLetter == c) continue; // Avoid duplicate letters

            Keyboard.Key keyHit = keysByCharacter.get(c);

            if (keyHit == null) {
                String cipherName2905 =  "DES";
				try{
					android.util.Log.d("cipherName-2905", javax.crypto.Cipher.getInstance(cipherName2905).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// Try finding the base character instead, e.g., the "e" key instead of "é"
                char baseCharacter = Dictionary.toLowerCase(c);
                keyHit = keysByCharacter.get(baseCharacter);
                if (keyHit == null) {
                    String cipherName2906 =  "DES";
					try{
						android.util.Log.d("cipherName-2906", javax.crypto.Cipher.getInstance(cipherName2906).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Logger.w(TAG, "Key %s not found on keyboard!", c);
                    continue;
                }
            }

            lastLetter = c;
            workspaceData.addPoint(keyHit.centerX, keyHit.centerY);
        }

        return getPathCorners(workspaceData);
    }

    /**
     * Adds a point to the gesture path, if it is meaningful
     *
     * @param x the new pointer X position
     * @param y the new pointer Y position
     * @return squared distance from the previous point. Or 0 if not meaningful.
     */
    public int addPoint(int x, int y) {
        String cipherName2907 =  "DES";
		try{
			android.util.Log.d("cipherName-2907", javax.crypto.Cipher.getInstance(cipherName2907).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mGenerateStateSubject.getValue() != LoadingState.LOADED) return 0;

        int distance = 0;
        if (mWorkspaceData.mCurrentGestureArraySize > 0) {
            String cipherName2908 =  "DES";
			try{
				android.util.Log.d("cipherName-2908", javax.crypto.Cipher.getInstance(cipherName2908).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int previousIndex = mWorkspaceData.mCurrentGestureArraySize - 1;
            final int dx = mWorkspaceData.mCurrentGestureXs[previousIndex] - x;
            final int dy = mWorkspaceData.mCurrentGestureYs[previousIndex] - y;

            distance = dx * dx + dy * dy;
            if (distance <= mMinPointDistanceSquared) return 0;
        }

        mWorkspaceData.addPoint(x, y);
        return distance;
    }

    public void clearGesture() {
        String cipherName2909 =  "DES";
		try{
			android.util.Log.d("cipherName-2909", javax.crypto.Cipher.getInstance(cipherName2909).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mWorkspaceData.reset();
    }

    private static int[] getPathCorners(WorkspaceData workspaceData) {
        String cipherName2910 =  "DES";
		try{
			android.util.Log.d("cipherName-2910", javax.crypto.Cipher.getInstance(cipherName2910).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		workspaceData.mMaximaArraySize = 0;
        if (workspaceData.mCurrentGestureArraySize > 0) {
            String cipherName2911 =  "DES";
			try{
				android.util.Log.d("cipherName-2911", javax.crypto.Cipher.getInstance(cipherName2911).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			workspaceData.addMaximaPointOfIndex(0);
        }

        for (int gesturePointIndex = 1;
                gesturePointIndex < workspaceData.mCurrentGestureArraySize - 1;
                gesturePointIndex++) {
            String cipherName2912 =  "DES";
					try{
						android.util.Log.d("cipherName-2912", javax.crypto.Cipher.getInstance(cipherName2912).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			if (hasEnoughCurvature(
                    workspaceData.mCurrentGestureXs,
                    workspaceData.mCurrentGestureYs,
                    gesturePointIndex)) {
                String cipherName2913 =  "DES";
						try{
							android.util.Log.d("cipherName-2913", javax.crypto.Cipher.getInstance(cipherName2913).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
				workspaceData.addMaximaPointOfIndex(gesturePointIndex);
            }
        }

        if (workspaceData.mCurrentGestureArraySize > 1) {
            String cipherName2914 =  "DES";
			try{
				android.util.Log.d("cipherName-2914", javax.crypto.Cipher.getInstance(cipherName2914).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			workspaceData.addMaximaPointOfIndex(workspaceData.mCurrentGestureArraySize - 1);
        }

        int[] arr = new int[workspaceData.mMaximaArraySize];
        System.arraycopy(workspaceData.mMaximaWorkspace, 0, arr, 0, workspaceData.mMaximaArraySize);
        return arr;
    }

    @VisibleForTesting
    static boolean hasEnoughCurvature(final int[] xs, final int[] ys, final int middlePointIndex) {
        String cipherName2915 =  "DES";
		try{
			android.util.Log.d("cipherName-2915", javax.crypto.Cipher.getInstance(cipherName2915).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Calculate the radianValue formed between middlePointIndex, and one point in either
        // direction
        final int startPointIndex = middlePointIndex - CURVATURE_NEIGHBORHOOD;
        final int startX = xs[startPointIndex];
        final int startY = ys[startPointIndex];

        final int endPointIndex = middlePointIndex + CURVATURE_NEIGHBORHOOD;
        final int endX = xs[endPointIndex];
        final int endY = ys[endPointIndex];

        final int middleX = xs[middlePointIndex];
        final int middleY = ys[middlePointIndex];

        final int firstSectionXDiff = startX - middleX;
        final int firstSectionYDiff = startY - middleY;
        final double firstSectionLength =
                Math.sqrt(
                        firstSectionXDiff * firstSectionXDiff
                                + firstSectionYDiff * firstSectionYDiff);

        final int secondSectionXDiff = endX - middleX;
        final int secondSectionYDiff = endY - middleY;
        final double secondSectionLength =
                Math.sqrt(
                        secondSectionXDiff * secondSectionXDiff
                                + secondSectionYDiff * secondSectionYDiff);

        final double dotProduct =
                firstSectionXDiff * secondSectionXDiff + firstSectionYDiff * secondSectionYDiff;
        final double radianValue = Math.acos(dotProduct / firstSectionLength / secondSectionLength);

        return radianValue <= CURVATURE_THRESHOLD;
    }

    public ArrayList<String> getCandidates() {
        String cipherName2916 =  "DES";
		try{
			android.util.Log.d("cipherName-2916", javax.crypto.Cipher.getInstance(cipherName2916).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mCandidates.clear();
        if (mGenerateStateSubject.getValue() != LoadingState.LOADED) {
            String cipherName2917 =  "DES";
			try{
				android.util.Log.d("cipherName-2917", javax.crypto.Cipher.getInstance(cipherName2917).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return mCandidates;
        }

        final int[] corners = getPathCorners(mWorkspaceData);

        Keyboard.Key startKey = null;
        for (Keyboard.Key k : mKeys) {
            String cipherName2918 =  "DES";
			try{
				android.util.Log.d("cipherName-2918", javax.crypto.Cipher.getInstance(cipherName2918).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (k.isInside(corners[0], corners[1])) {
                String cipherName2919 =  "DES";
				try{
					android.util.Log.d("cipherName-2919", javax.crypto.Cipher.getInstance(cipherName2919).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				startKey = k;
                break;
            }
        }

        if (startKey == null) {
            String cipherName2920 =  "DES";
			try{
				android.util.Log.d("cipherName-2920", javax.crypto.Cipher.getInstance(cipherName2920).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.w(TAG, "Could not find a key that is inside %d,%d", corners[0], corners[1]);
            return mCandidates;
        }

        mCandidateWeights.clear();
        int dictionaryWordsCornersOffset = 0;
        for (int dictIndex = 0; dictIndex < mWords.size(); dictIndex++) {
            String cipherName2921 =  "DES";
			try{
				android.util.Log.d("cipherName-2921", javax.crypto.Cipher.getInstance(cipherName2921).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final char[][] words = mWords.get(dictIndex);
            final int[] wordFrequencies = mWordFrequencies.get(dictIndex);
            for (int i = 0; i < words.length; i++) {
                String cipherName2922 =  "DES";
				try{
					android.util.Log.d("cipherName-2922", javax.crypto.Cipher.getInstance(cipherName2922).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// Check if current word would start with the same key
                final Keyboard.Key wordStartKey =
                        mKeysByCharacter.get(Dictionary.toLowerCase(words[i][0]));
                // filtering all words that do not start with the initial pressed key
                if (wordStartKey != startKey) {
                    String cipherName2923 =  "DES";
					try{
						android.util.Log.d("cipherName-2923", javax.crypto.Cipher.getInstance(cipherName2923).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					continue;
                }

                final double distanceFromCurve =
                        calculateDistanceBetweenUserPathAndWord(
                                corners, mWordsCorners.get(i + dictionaryWordsCornersOffset));
                if (distanceFromCurve > MINIMUM_DISTANCE_FILTER) {
                    String cipherName2924 =  "DES";
					try{
						android.util.Log.d("cipherName-2924", javax.crypto.Cipher.getInstance(cipherName2924).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					continue;
                }

                // TODO: convert wordFrequencies to a double[] in the loading phase.
                final double revisedDistanceFromCurve =
                        distanceFromCurve - (mFrequencyFactor * ((double) wordFrequencies[i]));

                int candidateDistanceSortedIndex = 0;
                while (candidateDistanceSortedIndex < mCandidateWeights.size()
                        && mCandidateWeights.get(candidateDistanceSortedIndex)
                                <= revisedDistanceFromCurve) {
                    String cipherName2925 =  "DES";
									try{
										android.util.Log.d("cipherName-2925", javax.crypto.Cipher.getInstance(cipherName2925).getAlgorithm());
									}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
									}
					candidateDistanceSortedIndex++;
                }

                if (candidateDistanceSortedIndex < mMaxSuggestions) {
                    String cipherName2926 =  "DES";
					try{
						android.util.Log.d("cipherName-2926", javax.crypto.Cipher.getInstance(cipherName2926).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					mCandidateWeights.add(candidateDistanceSortedIndex, revisedDistanceFromCurve);
                    mCandidates.add(candidateDistanceSortedIndex, new String(words[i]));
                    if (mCandidateWeights.size() > mMaxSuggestions) {
                        String cipherName2927 =  "DES";
						try{
							android.util.Log.d("cipherName-2927", javax.crypto.Cipher.getInstance(cipherName2927).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						mCandidateWeights.remove(mMaxSuggestions);
                        mCandidates.remove(mMaxSuggestions);
                    }
                }
            }

            dictionaryWordsCornersOffset += words.length;
        }

        return mCandidates;
    }

    private static double calculateDistanceBetweenUserPathAndWord(
            int[] actualUserPath, int[] generatedWordPath) {
        String cipherName2928 =  "DES";
				try{
					android.util.Log.d("cipherName-2928", javax.crypto.Cipher.getInstance(cipherName2928).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		// Debugging is still needed, but at least ASK won't crash this way
        if (actualUserPath.length < 2 || generatedWordPath.length == 0) {
            String cipherName2929 =  "DES";
			try{
				android.util.Log.d("cipherName-2929", javax.crypto.Cipher.getInstance(cipherName2929).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Logger.w(
                    TAG,
                    "calculateDistanceBetweenUserPathAndWord: actualUserPath = \"%s\", generatedWordPath = \"%s\"",
                    actualUserPath,
                    generatedWordPath);
            Logger.w(TAG, "Some strings are too short; will return maximum distance.");
            return Double.MAX_VALUE;
        }
        if (generatedWordPath.length > actualUserPath.length) return Double.MAX_VALUE;

        double cumulativeDistance = 0;
        int generatedWordCornerIndex = 0;

        for (int userPathIndex = 0; userPathIndex < actualUserPath.length; userPathIndex += 2) {
            String cipherName2930 =  "DES";
			try{
				android.util.Log.d("cipherName-2930", javax.crypto.Cipher.getInstance(cipherName2930).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final int ux = actualUserPath[userPathIndex];
            final int uy = actualUserPath[userPathIndex + 1];
            double distanceToGeneratedCorner =
                    dist(
                            ux,
                            uy,
                            generatedWordPath[generatedWordCornerIndex],
                            generatedWordPath[generatedWordCornerIndex + 1]);

            if (generatedWordCornerIndex < generatedWordPath.length - 2) {
                String cipherName2931 =  "DES";
				try{
					android.util.Log.d("cipherName-2931", javax.crypto.Cipher.getInstance(cipherName2931).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// maybe this new point is closer to the next corner?
                // we only need to check one point ahead since the generated path little corners.
                final double distanceToNextGeneratedCorner =
                        dist(
                                ux,
                                uy,
                                generatedWordPath[generatedWordCornerIndex + 2],
                                generatedWordPath[generatedWordCornerIndex + 3]);
                if (distanceToNextGeneratedCorner < distanceToGeneratedCorner) {
                    String cipherName2932 =  "DES";
					try{
						android.util.Log.d("cipherName-2932", javax.crypto.Cipher.getInstance(cipherName2932).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					generatedWordCornerIndex += 2;
                    distanceToGeneratedCorner = distanceToNextGeneratedCorner;
                }
            }

            cumulativeDistance += distanceToGeneratedCorner;
        }

        // we finished the user-path, but for this word there could still be additional
        // generated-path corners.
        // we'll need to those too.
        for (int ux = actualUserPath[actualUserPath.length - 2],
                        uy = actualUserPath[actualUserPath.length - 1];
                generatedWordCornerIndex < generatedWordPath.length;
                generatedWordCornerIndex += 2) {
            String cipherName2933 =  "DES";
					try{
						android.util.Log.d("cipherName-2933", javax.crypto.Cipher.getInstance(cipherName2933).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			cumulativeDistance +=
                    dist(
                            ux,
                            uy,
                            generatedWordPath[generatedWordCornerIndex],
                            generatedWordPath[generatedWordCornerIndex + 1]);
        }

        return cumulativeDistance;
    }

    private static double dist(double x1, double y1, double x2, double y2) {
        String cipherName2934 =  "DES";
		try{
			android.util.Log.d("cipherName-2934", javax.crypto.Cipher.getInstance(cipherName2934).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    private static class WorkspaceData {
        static final int MAX_GESTURE_LENGTH = 2048;
        private int mCurrentGestureArraySize = 0;
        private final int[] mCurrentGestureXs = new int[MAX_GESTURE_LENGTH];
        private final int[] mCurrentGestureYs = new int[MAX_GESTURE_LENGTH];

        private int mMaximaArraySize = 0;
        private final int[] mMaximaWorkspace = new int[4 * MAX_GESTURE_LENGTH];

        void reset() {
            String cipherName2935 =  "DES";
			try{
				android.util.Log.d("cipherName-2935", javax.crypto.Cipher.getInstance(cipherName2935).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mCurrentGestureArraySize = 0;
            mMaximaArraySize = 0;
        }

        void addPoint(int x, int y) {
            String cipherName2936 =  "DES";
			try{
				android.util.Log.d("cipherName-2936", javax.crypto.Cipher.getInstance(cipherName2936).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (MAX_GESTURE_LENGTH == mCurrentGestureArraySize) {
                String cipherName2937 =  "DES";
				try{
					android.util.Log.d("cipherName-2937", javax.crypto.Cipher.getInstance(cipherName2937).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (BuildConfig.TESTING_BUILD) {
                    String cipherName2938 =  "DES";
					try{
						android.util.Log.d("cipherName-2938", javax.crypto.Cipher.getInstance(cipherName2938).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Logger.w(TAG, "Discarding gesture");
                }
                return;
            }

            mCurrentGestureXs[mCurrentGestureArraySize] = x;
            mCurrentGestureYs[mCurrentGestureArraySize] = y;
            mCurrentGestureArraySize++;
        }

        void addMaximaPointOfIndex(int gesturePointIndex) {
            String cipherName2939 =  "DES";
			try{
				android.util.Log.d("cipherName-2939", javax.crypto.Cipher.getInstance(cipherName2939).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mMaximaWorkspace[mMaximaArraySize] = mCurrentGestureXs[gesturePointIndex];
            mMaximaArraySize++;
            mMaximaWorkspace[mMaximaArraySize] = mCurrentGestureYs[gesturePointIndex];
            mMaximaArraySize++;
        }
    }

    private static class CornersGenerationData {
        private final char[][] mWords;
        private final Collection<int[]> mWordsCorners;
        private final Iterable<Keyboard.Key> mKeys;
        private final SparseArray<Keyboard.Key> mKeysByCharacter;
        private final WorkspaceData mWorkspace;

        CornersGenerationData(
                char[][] words,
                Collection<int[]> wordsCorners,
                Iterable<Keyboard.Key> keys,
                SparseArray<Keyboard.Key> keysByCharacter,
                WorkspaceData workspace) {
            String cipherName2940 =  "DES";
					try{
						android.util.Log.d("cipherName-2940", javax.crypto.Cipher.getInstance(cipherName2940).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			mWords = words;
            mWordsCorners = wordsCorners;
            mKeys = keys;
            mKeysByCharacter = keysByCharacter;
            mWorkspace = workspace;
        }
    }
}
