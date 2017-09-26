
package com.michael.vincent.recovery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.michael.vincent.display.DisplayUtil;
import com.michael.vincent.display.NewDisplayOptions;
import com.michael.vincent.reuse.ReuseFullWallpaperManager;
import com.michael.vincent.util.ActivityUtil;
import com.michael.vincent.util.ViewUtil;

public class RecoveryFullWallpaperView extends ImageView {
    //================================================================================================
    // constructor
    public RecoveryFullWallpaperView(Context context) {
        super(context);
    }

    public RecoveryFullWallpaperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecoveryFullWallpaperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //================================================================================================





    //================================================================================================
    @Override
    protected void onAttachedToWindow() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " onAttachedToWindow");
        onAttached();
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " onDetachedFromWindow");
        onDetached();
        super.onDetachedFromWindow();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " setImageBitmap bm = " + (bm == null ? null : hashCode()));
        super.setImageBitmap(bm);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " onDraw");
        // super.onDraw(canvas);
        // final Bitmap currentBitmap = ImageViewUtil.getImageBitmap(this);
        // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, this.hashCode() + " current bitmap = " + (currentBitmap == null ? null : currentBitmap.hashCode()));
        checkToDraw(canvas);
    }

    private void checkToDraw(Canvas canvas) {
        if (ViewUtil.isActivityFinishing(this)) {
            // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "checkToDraw ViewUtil.isActivityFinishing(this)");
            return;
        }
        // only the inBitmap's imageView do not change, draw
        if (mInBitmap != null) {
            // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap = " + (mInBitmap == null ? null : mInBitmap.hashCode()));
            final ImageView imageView = ReuseFullWallpaperManager.getImageView(mInBitmap);
            // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap imageView = " + (imageView == null ? null : imageView.hashCode()));
            if (imageView != null && imageView == this) {
                // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " =========  checkToDraw inBitmap imageView do not not not change, do super onDraw mImageUrl = " + mImageUrl);
                super.onDraw(canvas);
                return;
            }
        }
        // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " ========= checkToDraw inBitmap imageView changed changed changed, do not onDraw mImageUrl = " + mImageUrl);
    }

    private void onAttached() {
        // get inBitmap from reuse manager
        if (mInBitmap == null) {
//            LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "getInBitmap onAttached");
            mInBitmap = ReuseFullWallpaperManager.getInBitmap(this);
        }
        addOnActivityVisibilityChangedListener();
    }

    private void onDetached() {
        // release inBitmap
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "releaseAndClearInBitmap onDetached");
        releaseAndClearInBitmap();
        removeOnActivityVisibilityChangedListener();
    }

    private void onVisible() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " onVisible");
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
            mInBitmap = ReuseFullWallpaperManager.getInBitmap(this);
        }
        // check to recovery image
        checkToRecoveryImage();
    }

    private void checkToRecoveryImage() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " checkToRecoveryImage");
        // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, this.hashCode() + " hasDisplayComplete = " + hasDisplayComplete);
        // after hasDisplayFull checkToRecovery
        if (!hasDisplayComplete) {
//            LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " ========= has not display image, do not need recovery!!! mImageUrl = " + mImageUrl);
            return;
        }

        // if is the same imageView do not recovery
        if (mInBitmap != null) {
            // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " inBitmap = " + (mInBitmap == null ? null : mInBitmap.hashCode()));
            final ImageView imageView = ReuseFullWallpaperManager.getImageView(mInBitmap);
            // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " inBitmap imageView = " + (imageView == null ? null : imageView.hashCode()));
            if (imageView != null && imageView == this) {
//                LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " =========  imageView do not change, do not not need recovery mImageUrl = " + mImageUrl);
                return;
            }
        }

        // current inBitmap's image url is changed, so clear current inBitmap
        restoreImage();
    }

    private void restoreImage() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " getNewInBitmapAndRecoveryImage mImageUrl = " + mImageUrl);
        // if inBitmap content is changed, do not need draw
        setImageBitmap(null);
        // clear the changed inBitmap
        mInBitmap = null;
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
//            LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "getNewInBitmap");
            mInBitmap = ReuseFullWallpaperManager.getInBitmap(this);
        }
        // check to recovery image
        recoveryImage();
    }

    private static final long RECOVERY_DELAY = 0;

    private void recoveryImage() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "recoveryImage");
        if (mImageUrl != null) {
            if (mInBitmap != null) {
                final NewDisplayOptions displayOptions = NewDisplayOptions.generateFullInBitmapOptions(mInBitmap);
                displayOptions.setOnlyFromCache(true);
//                LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " recovery fullWallpaper url = " + mImageUrl);
                DisplayUtil.display(this, mImageUrl, displayOptions, null, null, RECOVERY_DELAY);
            }
        }
    }

    private void onInvisible() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " onInvisible");
        if (mInBitmap != null) {
            // release inBitmap
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

    public void checkVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            onVisible();
        } else {
            onInvisible();
        }
    }
    // ====================================================================================





    //================================================================================================
    // inBitmap getter setter and clearer
    private Bitmap mInBitmap;

    public Bitmap getInBitmap() {
        // if inBitmap is null, get new empty inBitmap from reuse manager
        if (mInBitmap == null) {
//            LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "getInBitmap mInBitmap == null");
            mInBitmap = ReuseFullWallpaperManager.getInBitmap(this);
        }
        return mInBitmap;
    }

    private void releaseInBitmap() {
        if (mInBitmap != null) {
            // release inBitmap
            ReuseFullWallpaperManager.releaseInBitmap(mInBitmap, this);
        }
    }

    private void clearInBitmap() {
        if (this.mInBitmap != null) {
            this.mInBitmap = null;
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
        // LLog.i(Tags.FULLWALLPAPER_VISIBILITY, this.hashCode() + " clearImage");
        setImageBitmap(null);
    }

    private boolean hasDisplayComplete = false;

    public void hasDisplayFull() {
//        LLog.i(Tags.FULLWALLPAPER_VISIBILITY, System.identityHashCode(this) + " has display full image");
        // has display full image
        hasDisplayComplete = true;
    }
    //================================================================================================





    //================================================================================================
    private String mImageUrl;

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
    //================================================================================================

}
