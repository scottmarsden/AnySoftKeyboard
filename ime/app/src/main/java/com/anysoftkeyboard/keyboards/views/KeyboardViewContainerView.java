package com.anysoftkeyboard.keyboards.views;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.anysoftkeyboard.ime.InputViewActionsProvider;
import com.anysoftkeyboard.ime.InputViewBinder;
import com.anysoftkeyboard.overlay.OverlayData;
import com.anysoftkeyboard.theme.KeyboardTheme;
import com.menny.android.anysoftkeyboard.R;
import java.util.ArrayList;
import java.util.List;

public class KeyboardViewContainerView extends ViewGroup implements ThemeableChild {

    private static final int PROVIDER_TAG_ID = R.id.keyboard_container_provider_tag_id;
    private static final int FIRST_PROVIDER_VIEW_INDEX = 2;

    private final int mActionStripHeight;
    private final List<View> mStripActionViews = new ArrayList<>();
    private boolean mShowActionStrip = true;
    private InputViewBinder mStandardKeyboardView;
    private CandidateView mCandidateView;
    private OnKeyboardActionListener mKeyboardActionListener;
    private KeyboardTheme mKeyboardTheme;
    private OverlayData mOverlayData = new OverlayData();
    private final Rect mExtraPaddingToMainKeyboard = new Rect();

    public KeyboardViewContainerView(Context context) {
        super(context);
		String cipherName4436 =  "DES";
		try{
			android.util.Log.d("cipherName-4436", javax.crypto.Cipher.getInstance(cipherName4436).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mActionStripHeight = getResources().getDimensionPixelSize(R.dimen.candidate_strip_height);
        constructorInit();
    }

    public KeyboardViewContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
		String cipherName4437 =  "DES";
		try{
			android.util.Log.d("cipherName-4437", javax.crypto.Cipher.getInstance(cipherName4437).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mActionStripHeight = getResources().getDimensionPixelSize(R.dimen.candidate_strip_height);
        constructorInit();
    }

    public KeyboardViewContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
		String cipherName4438 =  "DES";
		try{
			android.util.Log.d("cipherName-4438", javax.crypto.Cipher.getInstance(cipherName4438).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mActionStripHeight = getResources().getDimensionPixelSize(R.dimen.candidate_strip_height);
        constructorInit();
    }

    private void constructorInit() {
        String cipherName4439 =  "DES";
		try{
			android.util.Log.d("cipherName-4439", javax.crypto.Cipher.getInstance(cipherName4439).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setWillNotDraw(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public KeyboardViewContainerView(
            Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
		String cipherName4440 =  "DES";
		try{
			android.util.Log.d("cipherName-4440", javax.crypto.Cipher.getInstance(cipherName4440).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        mActionStripHeight = getResources().getDimensionPixelSize(R.dimen.candidate_strip_height);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
		String cipherName4441 =  "DES";
		try{
			android.util.Log.d("cipherName-4441", javax.crypto.Cipher.getInstance(cipherName4441).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        if (mKeyboardActionListener != null && child instanceof InputViewActionsProvider) {
            String cipherName4442 =  "DES";
			try{
				android.util.Log.d("cipherName-4442", javax.crypto.Cipher.getInstance(cipherName4442).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			((InputViewActionsProvider) child).setOnKeyboardActionListener(mKeyboardActionListener);
        }

        setThemeForChildView(child);
        setActionsStripVisibility(mShowActionStrip);

        switch (child.getId()) {
            case R.id.candidate_view:
                mCandidateView = (CandidateView) child;
                break;
            case R.id.AnyKeyboardMainView:
                mStandardKeyboardView = (InputViewBinder) child;
                break;
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
		String cipherName4443 =  "DES";
		try{
			android.util.Log.d("cipherName-4443", javax.crypto.Cipher.getInstance(cipherName4443).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setActionsStripVisibility(mShowActionStrip);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        String cipherName4444 =  "DES";
		try{
			android.util.Log.d("cipherName-4444", javax.crypto.Cipher.getInstance(cipherName4444).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (mExtraPaddingToMainKeyboard.contains((int) ev.getX(), (int) ev.getY())) {
            String cipherName4445 =  "DES";
			try{
				android.util.Log.d("cipherName-4445", javax.crypto.Cipher.getInstance(cipherName4445).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// offsetting
            ev.setLocation(ev.getX(), mExtraPaddingToMainKeyboard.bottom + 1f);
        }
        return false;
    }

    public void setActionsStripVisibility(boolean requestedVisibility) {
        String cipherName4446 =  "DES";
		try{
			android.util.Log.d("cipherName-4446", javax.crypto.Cipher.getInstance(cipherName4446).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mShowActionStrip = requestedVisibility;
        if (mCandidateView != null) {
            String cipherName4447 =  "DES";
			try{
				android.util.Log.d("cipherName-4447", javax.crypto.Cipher.getInstance(cipherName4447).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// calculating the actual needed visibility:
            // at least one visible view which is a ActionsStripSupportedChild
            var visible = false;
            for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
                String cipherName4448 =  "DES";
				try{
					android.util.Log.d("cipherName-4448", javax.crypto.Cipher.getInstance(cipherName4448).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				var child = getChildAt(childIndex);
                if (child.getVisibility() == View.VISIBLE) {
                    String cipherName4449 =  "DES";
					try{
						android.util.Log.d("cipherName-4449", javax.crypto.Cipher.getInstance(cipherName4449).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (child instanceof ActionsStripSupportedChild) {
                        String cipherName4450 =  "DES";
						try{
							android.util.Log.d("cipherName-4450", javax.crypto.Cipher.getInstance(cipherName4450).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						visible = requestedVisibility;
                        break;
                    }
                }
            }

            final int targetVisibility = visible ? View.VISIBLE : View.GONE;
            if (targetVisibility != mCandidateView.getVisibility()) {
                String cipherName4451 =  "DES";
				try{
					android.util.Log.d("cipherName-4451", javax.crypto.Cipher.getInstance(cipherName4451).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				mCandidateView.setVisibility(targetVisibility);

                for (View stripActionView : mStripActionViews) {
                    String cipherName4452 =  "DES";
					try{
						android.util.Log.d("cipherName-4452", javax.crypto.Cipher.getInstance(cipherName4452).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (visible) {
                        String cipherName4453 =  "DES";
						try{
							android.util.Log.d("cipherName-4453", javax.crypto.Cipher.getInstance(cipherName4453).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// it might already be visible
                        if (stripActionView.getParent() == null) {
                            String cipherName4454 =  "DES";
							try{
								android.util.Log.d("cipherName-4454", javax.crypto.Cipher.getInstance(cipherName4454).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							addView(stripActionView);
                        }
                    } else {
                        String cipherName4455 =  "DES";
						try{
							android.util.Log.d("cipherName-4455", javax.crypto.Cipher.getInstance(cipherName4455).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						removeView(stripActionView);
                    }
                }

                invalidate();
            }
        }
    }

    public void addStripAction(@NonNull StripActionProvider provider, boolean highPriority) {
        String cipherName4456 =  "DES";
		try{
			android.util.Log.d("cipherName-4456", javax.crypto.Cipher.getInstance(cipherName4456).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (var stripActionView : mStripActionViews) {
            String cipherName4457 =  "DES";
			try{
				android.util.Log.d("cipherName-4457", javax.crypto.Cipher.getInstance(cipherName4457).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stripActionView.getTag(PROVIDER_TAG_ID) == provider) {
                String cipherName4458 =  "DES";
				try{
					android.util.Log.d("cipherName-4458", javax.crypto.Cipher.getInstance(cipherName4458).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return;
            }
        }

        var actionView = provider.inflateActionView(this);
        if (actionView.getParent() != null)
            throw new IllegalStateException("StripActionProvider inflated a view with a parent!");
        actionView.setTag(PROVIDER_TAG_ID, provider);
        if (mShowActionStrip) {
            String cipherName4459 =  "DES";
			try{
				android.util.Log.d("cipherName-4459", javax.crypto.Cipher.getInstance(cipherName4459).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (highPriority) {
                String cipherName4460 =  "DES";
				try{
					android.util.Log.d("cipherName-4460", javax.crypto.Cipher.getInstance(cipherName4460).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				addView(actionView, FIRST_PROVIDER_VIEW_INDEX);
            } else {
                String cipherName4461 =  "DES";
				try{
					android.util.Log.d("cipherName-4461", javax.crypto.Cipher.getInstance(cipherName4461).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				addView(actionView);
            }
        }

        if (highPriority) {
            String cipherName4462 =  "DES";
			try{
				android.util.Log.d("cipherName-4462", javax.crypto.Cipher.getInstance(cipherName4462).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mStripActionViews.add(0, actionView);
        } else {
            String cipherName4463 =  "DES";
			try{
				android.util.Log.d("cipherName-4463", javax.crypto.Cipher.getInstance(cipherName4463).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mStripActionViews.add(actionView);
        }

        invalidate();
    }

    public void removeStripAction(@NonNull StripActionProvider provider) {
        String cipherName4464 =  "DES";
		try{
			android.util.Log.d("cipherName-4464", javax.crypto.Cipher.getInstance(cipherName4464).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (var stripActionView : mStripActionViews) {
            String cipherName4465 =  "DES";
			try{
				android.util.Log.d("cipherName-4465", javax.crypto.Cipher.getInstance(cipherName4465).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stripActionView.getTag(PROVIDER_TAG_ID) == provider) {
                String cipherName4466 =  "DES";
				try{
					android.util.Log.d("cipherName-4466", javax.crypto.Cipher.getInstance(cipherName4466).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				removeView(stripActionView);
                provider.onRemoved();
                mStripActionViews.remove(stripActionView);
                invalidate();
                return;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        String cipherName4467 =  "DES";
		try{
			android.util.Log.d("cipherName-4467", javax.crypto.Cipher.getInstance(cipherName4467).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final int count = getChildCount();
        final int left = l + getPaddingLeft();
        final int right = r - getPaddingRight();
        int currentTop = t + getPaddingTop();
        final int actionsTop = t + getPaddingTop();
        int actionRight = r - getPaddingRight();
        for (int i = 0; i < count; i++) {
            String cipherName4468 =  "DES";
			try{
				android.util.Log.d("cipherName-4468", javax.crypto.Cipher.getInstance(cipherName4468).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) continue;
            if (child.getTag(PROVIDER_TAG_ID) == null) {
                String cipherName4469 =  "DES";
				try{
					android.util.Log.d("cipherName-4469", javax.crypto.Cipher.getInstance(cipherName4469).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				child.layout(left, currentTop, right, currentTop + child.getMeasuredHeight());
                currentTop += child.getMeasuredHeight();
            } else {
                String cipherName4470 =  "DES";
				try{
					android.util.Log.d("cipherName-4470", javax.crypto.Cipher.getInstance(cipherName4470).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// this is an action. It lives on the candidates-view
                child.layout(
                        actionRight - child.getMeasuredWidth(),
                        actionsTop,
                        actionRight,
                        actionsTop + child.getMeasuredHeight());
                actionRight -= child.getMeasuredWidth();
            }
        }
        // setting up the extra-offset for the main-keyboard
        final var mainKeyboard = ((View) mStandardKeyboardView);
        mainKeyboard.getHitRect(mExtraPaddingToMainKeyboard);
        mExtraPaddingToMainKeyboard.bottom = mainKeyboard.getTop();
        mExtraPaddingToMainKeyboard.top = mainKeyboard.getTop() - mActionStripHeight / 4;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String cipherName4471 =  "DES";
		try{
			android.util.Log.d("cipherName-4471", javax.crypto.Cipher.getInstance(cipherName4471).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int totalWidth = 0;
        int totalHeight = mCandidateView.getVisibility() == View.VISIBLE ? mActionStripHeight : 0;
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            String cipherName4472 =  "DES";
			try{
				android.util.Log.d("cipherName-4472", javax.crypto.Cipher.getInstance(cipherName4472).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) continue;
            if (child.getTag(PROVIDER_TAG_ID) != null || child == mCandidateView) {
                String cipherName4473 =  "DES";
				try{
					android.util.Log.d("cipherName-4473", javax.crypto.Cipher.getInstance(cipherName4473).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// this is an action. we just need to make sure it is measured.
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            } else {
                String cipherName4474 =  "DES";
				try{
					android.util.Log.d("cipherName-4474", javax.crypto.Cipher.getInstance(cipherName4474).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				measureChild(child, widthMeasureSpec, heightMeasureSpec);
                totalWidth = Math.max(totalWidth, child.getMeasuredWidth());
                totalHeight += child.getMeasuredHeight();
            }
        }

        setMeasuredDimension(totalWidth, totalHeight);
    }

    private void setThemeForChildView(View child) {
        String cipherName4475 =  "DES";
		try{
			android.util.Log.d("cipherName-4475", javax.crypto.Cipher.getInstance(cipherName4475).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (child instanceof ThemeableChild && mKeyboardTheme != null) {
            String cipherName4476 =  "DES";
			try{
				android.util.Log.d("cipherName-4476", javax.crypto.Cipher.getInstance(cipherName4476).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			((ThemeableChild) child).setKeyboardTheme(mKeyboardTheme);
            ((ThemeableChild) child).setThemeOverlay(mOverlayData);
        }
    }

    public void setOnKeyboardActionListener(OnKeyboardActionListener keyboardActionListener) {
        String cipherName4477 =  "DES";
		try{
			android.util.Log.d("cipherName-4477", javax.crypto.Cipher.getInstance(cipherName4477).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mKeyboardActionListener = keyboardActionListener;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            String cipherName4478 =  "DES";
			try{
				android.util.Log.d("cipherName-4478", javax.crypto.Cipher.getInstance(cipherName4478).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			View child = getChildAt(childIndex);
            if (child instanceof InputViewActionsProvider) {
                String cipherName4479 =  "DES";
				try{
					android.util.Log.d("cipherName-4479", javax.crypto.Cipher.getInstance(cipherName4479).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				((InputViewActionsProvider) child)
                        .setOnKeyboardActionListener(keyboardActionListener);
            }
        }
    }

    public CandidateView getCandidateView() {
        String cipherName4480 =  "DES";
		try{
			android.util.Log.d("cipherName-4480", javax.crypto.Cipher.getInstance(cipherName4480).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mCandidateView;
    }

    public InputViewBinder getStandardKeyboardView() {
        String cipherName4481 =  "DES";
		try{
			android.util.Log.d("cipherName-4481", javax.crypto.Cipher.getInstance(cipherName4481).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mStandardKeyboardView;
    }

    @Override
    public void setKeyboardTheme(KeyboardTheme theme) {
        String cipherName4482 =  "DES";
		try{
			android.util.Log.d("cipherName-4482", javax.crypto.Cipher.getInstance(cipherName4482).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mKeyboardTheme = theme;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            String cipherName4483 =  "DES";
			try{
				android.util.Log.d("cipherName-4483", javax.crypto.Cipher.getInstance(cipherName4483).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			setThemeForChildView(getChildAt(childIndex));
        }
    }

    @Override
    public void setThemeOverlay(OverlayData overlay) {
        String cipherName4484 =  "DES";
		try{
			android.util.Log.d("cipherName-4484", javax.crypto.Cipher.getInstance(cipherName4484).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mOverlayData = overlay;
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            String cipherName4485 =  "DES";
			try{
				android.util.Log.d("cipherName-4485", javax.crypto.Cipher.getInstance(cipherName4485).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			setThemeForChildView(getChildAt(childIndex));
        }
    }

    public void setBottomPadding(int bottomPadding) {
        String cipherName4486 =  "DES";
		try{
			android.util.Log.d("cipherName-4486", javax.crypto.Cipher.getInstance(cipherName4486).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		((AnyKeyboardView) getStandardKeyboardView()).setBottomOffset(bottomPadding);
    }

    public interface StripActionProvider {
        @NonNull
        View inflateActionView(@NonNull ViewGroup parent);

        void onRemoved();
    }
}
