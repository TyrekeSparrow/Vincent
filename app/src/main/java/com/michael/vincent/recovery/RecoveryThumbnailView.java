
package com.michael.vincent.recovery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.michael.vincent.display.DisplayUtil;
import com.michael.vincent.display.NewDisplayOptions;
import com.michael.vincent.reuse.ReuseThumbnailManager;
import com.michael.vincent.util.ActivityUtil;
import com.michael.vincent.util.ViewUtil;

public class RecoveryThumbnailView extends ImageView {
    //================================================================================================
    // constructor
    public RecoveryThumbnailView(Context context) {
        super(context);
    }

    public RecoveryThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecoveryThumbnailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //================================================================================================





    //================================================================================================
    @Override
    protected void onAttachedToWindow() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " onAttachedToWindow");
        onAttached();
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " onDetachedFromWindow");
        onDetached();
        super.onDetachedFromWindow();
    }

    private boolean hasDisplayComplete;

    private void checkHasDisplay(Bitmap bm) {
        if (bm != null) {
            hasDisplayComplete = true;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " setImageBitmap bm = " + bm);
        checkHasDisplay(bm);
        super.setImageBitmap(bm);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " onDraw");
        // super.onDraw(canvas);
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, "current bitmap = " + ImageViewUtil.getImageBitmap(this));
        checkToDraw(canvas);
    }

    private void checkToDraw(Canvas canvas) {
        if (ViewUtil.isActivityFinishing(this)) {
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, "checkToDraw ViewUtil.isActivityFinishing(this)");
            return;
        }
        // only the inBitmap's imageView do not change, draw
        if (mInBitmap != null) {
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap = " + mInBitmap);
            final ImageView imageView = ReuseThumbnailManager.getImageView(mInBitmap);
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap imageView = " + imageView);
            if (imageView != null && imageView == this) {
//                LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " =========  checkToDraw inBitmap imageView do not not not change, do super onDraw mImageUrl = " + mImageUrl);
                super.onDraw(canvas);
                return;
            }
        }
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " ========= checkToDraw inBitmap imageView changed changed changed, do not onDraw mImageUrl = " + mImageUrl);
    }

    @Override
    public void invalidate() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " invalidate");
        super.invalidate();
    }

    private void checkVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    private void onAttached() {
        // get inBitmap from reuse manager
        if (mInBitmap == null) {
            mInBitmap = ReuseThumbnailManager.getInBitmap(this);
        }
        addOnActivityVisibilityChangedListener();
    }

    private void onDetached() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, "releaseAndClearInBitmap onDetached");
        releaseAndClearInBitmap();
        removeOnActivityVisibilityChangedListener();
    }

    private void onVisible() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " onVisible");
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
            mInBitmap = ReuseThumbnailManager.getInBitmap(this);
        }
        // check to recovery image
        checkToRecoveryImage();
    }

    private void checkToRecoveryImage() {
        final long time = SystemClock.elapsedRealtime();
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " checkToRecoveryImage");
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, "hasDisplayComplete = " + hasDisplayComplete);
        // after hasDisplayFull checkToRecovery
        if (!hasDisplayComplete) {
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " ========= has not display image, do not need recovery!!! mImageUrl = " + mImageUrl);
            return;
        }

        // if is the same imageView do not recovery
        if (mInBitmap != null) {
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " inBitmap = " + mInBitmap);
            final ImageView imageView = ReuseThumbnailManager.getImageView(mInBitmap);
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " inBitmap imageView = " + imageView);
            if (imageView != null && imageView == this) {
//                LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " =========  imageView do not change, do not not need recovery mImageUrl = " + mImageUrl);
                return;
            }
        }

        // current inBitmap's image url is changed, so clear current inBitmap
        getNewInBitmapAndRecoveryImage();
        final long interval = SystemClock.elapsedRealtime() - time;
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, "recovery interval = " + interval);
    }

    private void getNewInBitmapAndRecoveryImage() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " getNewInBitmapAndRecoveryImage mImageUrl = " + mImageUrl);
        mInBitmap = null;
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, "getNewInBitmap");
            mInBitmap = ReuseThumbnailManager.getInBitmap(this);
        }
        // check to recovery image
        recoveryImage();
    }

    // private static final long RECOVERY_DELAY = 100;
    private void recoveryImage() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, "recoveryImage");
        if (mImageUrl != null) {
            if (mInBitmap != null) {
                final NewDisplayOptions displayOptions = NewDisplayOptions.generateInBitmapOptions(mInBitmap);
                displayOptions.setOnlyFromCache(true);
//                LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " recovery image url = " + mImageUrl);
                DisplayUtil.display(this, mImageUrl, displayOptions, null, null, 0);
            }
        }
    }

    private void onInVisible() {
//        LLog.i(Tags.THUMBNAIL_VISIBILITY, System.identityHashCode(this) + " onInVisible");
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
    // ====================================================================================





    //================================================================================================
    // inBitmap getter setter and clearer
    private Bitmap mInBitmap;

    public Bitmap getInBitmap() {
        // if inBitmap is null, get new empty inBitmap from reuse manager
        if (mInBitmap == null) {
            mInBitmap = ReuseThumbnailManager.getInBitmap(this);
        }
        // if inBitmap imageView is changed, get new empty inBitmap from reuse manager
        if (mInBitmap != null) {
            // if inBitmap is reused, it has been replace with new empty inBitmap
            if (isInBitmapReused()) {
//                LLog.i(Tags.THUMBNAIL_VISIBILITY, "thumbnail isInBitmapReused() = " + isInBitmapReused());
            }
        }
        return mInBitmap;
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
    // release reuse bitmap
    private void releaseInBitmap() {
        if (mInBitmap != null) {
            ReuseThumbnailManager.releaseInBitmap(mInBitmap, this);
        }
    }

    public boolean isInBitmapReused() {
        final boolean isReused = ReuseThumbnailManager.isInBitmapReused(mInBitmap, this);
        if (isReused) {
            // get new empty inBitmap
            mInBitmap = ReuseThumbnailManager.getInBitmap(this);
        }
        return isReused;
    }

    public boolean isInBitmapEmpty() {
        final boolean isInBitmapEmpty = ReuseThumbnailManager.isInBitmapEmpty(mInBitmap, this);
        return isInBitmapEmpty;
    }
    //================================================================================================





    //================================================================================================
    // clear image
    private void clearImage() {
        setImageBitmap(null);
    }
    //================================================================================================





    //================================================================================================
    private String mImageUrl;

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
    //================================================================================================

}
