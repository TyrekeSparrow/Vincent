package com.michael.vincent.recovery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.michael.vincent.cache.NewCacheManager;
import com.michael.vincent.display.DisplayUtil;
import com.michael.vincent.display.NewDisplayOptions;
import com.michael.vincent.display.BitmapDecoder;
import com.michael.vincent.reuse.ReuseTopicManager;
import com.michael.vincent.util.ActivityUtil;
import com.michael.vincent.util.ViewUtil;

public class RecoveryTopicView extends ImageView {
    //================================================================================================
    // constructor
    public RecoveryTopicView(Context context) {
        super(context);
    }

    public RecoveryTopicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecoveryTopicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //================================================================================================





    //================================================================================================
    @Override
    protected void onAttachedToWindow() {
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " onAttachedToWindow");
        onAttached();
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " onDetachedFromWindow");
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
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " setImageBitmap bm = " + bm);
        checkHasDisplay(bm);
        super.setImageBitmap(bm);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " onDraw");
        // super.onDraw(canvas);
//        LLog.i(Tags.TOPIC_VISIBILITY, "current bitmap = " + ImageViewUtil.getImageBitmap(this));
        checkToDraw(canvas);
    }

    private void checkToDraw(Canvas canvas) {
        if (ViewUtil.isActivityFinishing(this)) {
//            LLog.i(Tags.FULLWALLPAPER_VISIBILITY, "checkToDraw ViewUtil.isActivityFinishing(this)");
            return;
        }
        // only the inBitmap's imageView do not change, draw
        if (mInBitmap != null) {
//            LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap = " + mInBitmap);
            final ImageView imageView = ReuseTopicManager.getImageView(mInBitmap);
//            LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " checkToDraw inBitmap imageView = " + imageView);
            if (imageView != null && imageView == this) {
//                LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " =========  checkToDraw inBitmap imageView do not not not change, do super onDraw mImageUrl = " + mImageUrl);
                super.onDraw(canvas);
                return;
            }
        }
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " ========= checkToDraw inBitmap imageView changed changed changed, do not onDraw mImageUrl = " + mImageUrl);
    }

    public void checkVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    private void onAttached() {
        // get inBitmap from reuse manager
        if (mInBitmap == null) {
//            LLog.i(Tags.TOPIC_VISIBILITY, "getInBitmap onAttached");
            mInBitmap = ReuseTopicManager.getInBitmap(this);
        }
        addOnActivityVisibilityChangedListener();
    }

    private void onDetached() {
        // release inBitmap
//        LLog.i(Tags.TOPIC_VISIBILITY, "releaseAndClearInBitmap onDetached");
        releaseAndClearInBitmap();
        removeOnActivityVisibilityChangedListener();
    }

    private void onVisible() {
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " onResume");
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
//            LLog.i(Tags.TOPIC_VISIBILITY, "getInBitmap onResume");
            mInBitmap = ReuseTopicManager.getInBitmap(this);
        }
        // check to recovery image
        // tryToRecoveryImage();
        // check to recovery image
        checkToRecoveryImage();
    }

    private void checkToRecoveryImage() {
        final long time = SystemClock.elapsedRealtime();
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " checkToRecoveryImage");
//        LLog.i(Tags.TOPIC_VISIBILITY, "hasDisplayComplete = " + hasDisplayComplete);
        // after hasDisplayFull checkToRecovery
        if (!hasDisplayComplete) {
//            LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " ========= has not display image, do not need recovery!!! mImageUrl = " + mImageUrl);
            return;
        }

        // if is the same imageView do not recovery
        if (mInBitmap != null) {
//            LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " inBitmap = " + mInBitmap);
            final ImageView imageView = ReuseTopicManager.getImageView(mInBitmap);
//            LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " inBitmap imageView = " + imageView);
            if (imageView != null && imageView == this) {
//                LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " =========  imageView do not change, do not not need recovery mImageUrl = " + mImageUrl);
                return;
            }
        }

        // current inBitmap's image url is changed, so clear current inBitmap
        getNewInBitmapAndRecoveryImage();
        final long interval = SystemClock.elapsedRealtime() - time;
//        LLog.i(Tags.TOPIC_VISIBILITY, "recovery interval = " + interval);
    }

    private void getNewInBitmapAndRecoveryImage() {
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " getNewInBitmapAndRecoveryImage mImageUrl = " + mImageUrl);
        mInBitmap = null;
        // if inBitmap null, get new inBitmap
        if (mInBitmap == null) {
//            LLog.i(Tags.TOPIC_VISIBILITY, "getNewInBitmap");
            mInBitmap = ReuseTopicManager.getInBitmap(this);
        }
        // check to recovery image
        recoveryImage();
    }

    // private static final long RECOVERY_DELAY = 100;
    private void recoveryImage() {
//        LLog.i(Tags.TOPIC_VISIBILITY, "recoveryImage");
        if (mImageUrl != null) {
            if (mInBitmap != null) {
                final NewDisplayOptions displayOptions = NewDisplayOptions.generateInBitmapOptions(mInBitmap);
                displayOptions.setOnlyFromCache(true);
//                LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " recovery image url = " + mImageUrl);
                DisplayUtil.display(this, mImageUrl, displayOptions, null, null, 0);
            }
        }
    }

    private void onInvisible() {
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " onInvisible");
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
//            LLog.i(Tags.TOPIC_VISIBILITY, "getInBitmap mInBitmap == null");
            mInBitmap = ReuseTopicManager.getInBitmap(this);
        }
        return mInBitmap;
    }

    private void releaseInBitmap() {
        if (mInBitmap != null) {
            // release inBitmap
            ReuseTopicManager.releaseInBitmap(mInBitmap, this);
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
        setImageBitmap(null);
    }

    private boolean hasDisplayFull = false;

    public void hasDisplayFull() {
//        LLog.i(Tags.TOPIC_VISIBILITY, System.identityHashCode(this) + " has display full image");
        // has display full image
        hasDisplayFull = true;
    }

    // recovery image when image is null
    private void tryToRecoveryImage() {
//        LLog.i(Tags.TOPIC_VISIBILITY, "tryToRecoveryImage");
        if (!hasDisplayFull) {
            // if has not been set image bitmap, do not recovery
            return;
        }
        if (mImageUrl != null) {
            // recovery image from url
            final byte[] byteArray = NewCacheManager.getByteArrayFromLocalCache(mImageUrl);
            if (byteArray != null) {
                // decode bitmap with inBitmap
                if (mInBitmap != null) {
                    final BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
                    decodeOptions.inBitmap = mInBitmap;
                    final Bitmap recoveryBitmap = BitmapDecoder.decode(byteArray, decodeOptions);
//                    LLog.i(Tags.TOPIC_VISIBILITY, "recoveryBitmap = " + recoveryBitmap);
                    if (recoveryBitmap != null) {
//                        LLog.i(Tags.TOPIC_VISIBILITY, "recovery bitmap");
                        setImageBitmap(recoveryBitmap);
                    }
                }
            }
        }
    }
    //================================================================================================





    //================================================================================================
    private String mImageUrl;

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
    //================================================================================================
}
