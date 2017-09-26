package com.michael.vincent.recovery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.michael.vincent.display.DisplayUtil;
import com.michael.vincent.display.NewDisplayOptions;
import com.michael.vincent.reuse.ReuseIconManager;
import com.michael.vincent.util.ActivityUtil;
import com.michael.vincent.util.ViewUtil;

public class RecoveryIconView extends ImageView {
    //================================================================================================
    // constructor
    public RecoveryIconView(Context context) {
        super(context);
    }

    public RecoveryIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecoveryIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //================================================================================================





    //================================================================================================
    @Override
    protected void onAttachedToWindow() {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " onAttachedToWindow");
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " isVisible" + getVisibility());
        if (getVisibility() == VISIBLE) {
            onAttached();
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " onDetachedFromWindow");
        onDetached();
        super.onDetachedFromWindow();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " setImageBitmap bm = " + bm);
        super.setImageBitmap(bm);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " onDraw");
        // super.onDraw(canvas);
        checkToDraw(canvas);
    }

    private void checkToDraw(Canvas canvas) {
        if (ViewUtil.isActivityFinishing(this)) {
//            LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "checkToDraw ViewUtil.isActivityFinishing(this)");
            return;
        }
        // only the inBitmap's imageView do not change, draw
        if (mInBitmap != null) {
//            LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap = " + mInBitmap);
            final ImageView imageView = ReuseIconManager.getImageView(mInBitmap);
//            LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap imageView = " + imageView);
            if (imageView != null && imageView == this) {
//                LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " =========  checkToDraw inBitmap imageView do not not not change, do super onDraw mImageUrl = " + mImageUrl);
                super.onDraw(canvas);
                return;
            }
        }
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " ========= checkToDraw inBitmap imageView changed changed changed, do not onDraw mImageUrl = " + mImageUrl);
    }

    private void checkToRecoveryImage() {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " checkToRecoveryImage");
        // after hasDisplayFull checkToRecovery
        if (!hasDisplayFull) {
//            LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " has not display image, do not nedd recovery!!! mImageUrl = " + mImageUrl);
            return;
        }

        // if is the same imageView do not recovery
        if (mInBitmap != null) {
//            LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " inBitmap = " + mInBitmap);
            final ImageView imageView = ReuseIconManager.getImageView(mInBitmap);
//            LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " inBitmap imageView = " + imageView);
            if (imageView != null && imageView instanceof RecoveryIconView) {
                if (imageView == this) {
//                    LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " imageUrl do not change, do not not need recovery mImageUrl = " + mImageUrl);
                    return;
                }
            }
        }

        // current inBitmap's image url is changed, so clear current inBitmap
        getNewInBitmapAndRecoveryImage();
    }

    private void getNewInBitmapAndRecoveryImage() {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " getNewInBitmapAndRecoveryImage mImageUrl = " + mImageUrl);
        mInBitmap = null;
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
//            LLog.i(Tags.ICON_VISIBILITY, "getNewInBitmap");
            mInBitmap = ReuseIconManager.getInBitmap(this);
        }
        // check to recovery image
        recoveryImage();
    }

    private void recoveryImage() {
//        LLog.i(Tags.ICON_VISIBILITY, "recoveryImage");
        if (!hasDisplayFull) {
            // if has not been set image bitmap, do not recovery
            return;
        }
        if (mImageUrl != null) {
            if (mInBitmap != null) {
                final NewDisplayOptions displayOptions = NewDisplayOptions.generateInBitmapOptions(mInBitmap);
                displayOptions.setOnlyFromCache(true);
//                LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " recovery image url = " + mImageUrl);
                DisplayUtil.display(this, mImageUrl, displayOptions, null, null, 0);
            }
        }
    }

    public void checkVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    private void onAttached() {
        // if is activity finishing, do nothing
        if (ViewUtil.isActivityFinishing(this)) {
            return;
        }
        // get inBitmap from reuse manager
        if (mInBitmap == null) {
//            LLog.i(Tags.ICON_VISIBILITY, "getInBitmap onAttached");
            mInBitmap = ReuseIconManager.getInBitmap(this);
        }
        addOnActivityVisibilityChangedListener();
    }

    private void onDetached() {
        // release inBitmap
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " releaseAndClearInBitmap onDetached imageUrl = " + mImageUrl);
        releaseAndClearInBitmap();
        removeOnActivityVisibilityChangedListener();
    }

    private void onVisible() {
        if (ViewUtil.isActivityFinishing(this)) {
            return;
        }
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " onVisible");
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
//            LLog.i(Tags.ICON_VISIBILITY, "getInBitmap onVisible");
            mInBitmap = ReuseIconManager.getInBitmap(this);
        }
        // check to recovery image
        checkToRecoveryImage();
    }

    private void onInVisible() {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " onInVisible");
        if (mInBitmap != null) {
            releaseInBitmap();
        }
        if (ViewUtil.isActivityFinishing(this)) {
            clearImage();
        }
    }
    //================================================================================================





    // ====================================================================================
    private void addOnActivityVisibilityChangedListener() {
        ActivityUtil.addOnActivityVisibilityChangedListener(this, onActivityVisibilityChangedListener);
    }

    private void removeOnActivityVisibilityChangedListener() {
        ActivityUtil.removeOnActivityVisibilityChangedListener(this, onActivityVisibilityChangedListener);
    }

    private ActivityUtil.OnActivityVisibilityChangedListener onActivityVisibilityChangedListener = new ActivityUtil.OnActivityVisibilityChangedListener() {
        @Override
        public void onVisibilityChanged(int visibility) {
            checkVisibility(visibility);
        }
    };
    // ====================================================================================





    //================================================================================================
    // inBitmap getter setter and clearer
    private Bitmap mInBitmap;

    public Bitmap getInBitmap() {
        // if inBitmap is null, get new empty inBitmap from reuse manager
        if (mInBitmap == null) {
//            LLog.i(Tags.ICON_VISIBILITY, "getInBitmap mInBitmap == null");
            mInBitmap = ReuseIconManager.getInBitmap(this);
        }
        return mInBitmap;
    }

    public Bitmap getImageInBitmap(String imageUrl) {
        setImageUrl(imageUrl);
        return getInBitmap();
    }

    private void clearInBitmap() {
        if (mInBitmap != null) {
            mInBitmap = null;
        }
    }

    private void releaseInBitmap() {
        // before release inBitmap, cancel displayTask, release inBitmap in displayTask
        DisplayUtil.cancelDisplay(this);
        // release inBitmap
        if (mInBitmap != null) {
            // release inBitmap
            ReuseIconManager.releaseInBitmap(mInBitmap, this);
        }
    }

    private void releaseAndClearInBitmap() {
        releaseInBitmap();
        clearInBitmap();
    }
    //================================================================================================





    //================================================================================================
    // clear image
    private void clearImage() {
        setImageBitmap(null);
    }

    private boolean hasDisplayFull = false;

    public void hasDisplayFull() {
//        LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(this) + " has display full image");
        // has display full image
        hasDisplayFull = true;
    }
    //================================================================================================





    //================================================================================================
    private String mImageUrl;

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
    //================================================================================================
}
