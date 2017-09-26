package com.michael.vincent.reuse;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.michael.vincent.util.BitmapUtil;

import java.util.HashMap;
import java.util.Map.Entry;

public class ReuseTopicManager {
    // MAX_SIZE = 3 * x;
    private static final HashMap<Bitmap, ImageView> REUSE_BITMAPS = new HashMap<Bitmap, ImageView>();

    /*
    private static final long CHECK_INTERVAL = 9000;

    private static void recycleInBitmapDelayed(final Bitmap inBitmap) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                recycleEmptyBitmapImpl(inBitmap);
            }
        }, CHECK_INTERVAL);
    }

    private static void recycleEmptyBitmapImpl(Bitmap inBitmap) {
        if (inBitmap != null) {
            if (!isInBitmapReused(inBitmap)) {
                // remove from cache
                if (REUSE_BITMAPS.containsKey(inBitmap)) {
                    LLog.i(Tags.TOPIC_VISIBILITY, "!!!!!!!!!!!!!!!! recycle impl remove inBitmap = " + System.identityHashCode(inBitmap));
                    REUSE_BITMAPS.remove(inBitmap);
                }
                // recycle bitmap
                if (!inBitmap.isRecycled()) {
                    LLog.i(Tags.TOPIC_VISIBILITY, "!!!!!!!!!!!!!!!! recycle impl recycle inBitmap = " + System.identityHashCode(inBitmap));
                    inBitmap.recycle();
                }
                dumpInBitmaps();
            }
        }
    }
    */

    // get empty bitmap
    // release bitmap
    // release all bitmaps
    // isReused
    public static Bitmap getInBitmap(ImageView imageView) {
        // for each bitmap, if it is released, return it
        // if no released bitmap, generate one
        // if size < maxSize, put it
        // if size >= maxSize, also put it, but the size is exceed
        Bitmap inBitmap = null;
        for (Bitmap bitmap : REUSE_BITMAPS.keySet()) {
            if (bitmap != null && REUSE_BITMAPS.get(bitmap) == null) {
                inBitmap = bitmap;
                break;
            }
        }
        if (inBitmap == null) {
            inBitmap = BitmapUtil.generateTopicCoverBitmap();
//            LLog.i(Tags.TOPIC_VISIBILITY, "@@@@@ generate empty inBitmap " + System.identityHashCode(inBitmap));
        }
        if (inBitmap != null) {
            REUSE_BITMAPS.put(inBitmap, imageView);
            dumpInBitmaps();
        }
//        LLog.i(Tags.TOPIC_VISIBILITY, "----- getInBitmap " + System.identityHashCode(inBitmap));
        return inBitmap;
    }

    public static ImageView getImageView(Bitmap inBitmap) {
        if (inBitmap != null) {
            if (REUSE_BITMAPS != null && REUSE_BITMAPS.size() > 0) {
                for (Bitmap bitmap : REUSE_BITMAPS.keySet()) {
                    if (bitmap != null && bitmap == inBitmap) {
                        final ImageView imageView = REUSE_BITMAPS.get(bitmap);
                        return imageView;
                    }
                }
            }
        }
        return null;
    }

    public static void releaseInBitmap(Bitmap bitmap, ImageView imageView) {
        if (bitmap == null || imageView == null) {
            return;
        }
        for (Entry<Bitmap, ImageView> entry : REUSE_BITMAPS.entrySet()) {
            final Bitmap inBitmap = entry.getKey();
            if (inBitmap != null && inBitmap == bitmap) {
                final ImageView reuseImageView = entry.getValue();
                if (reuseImageView != null && reuseImageView == imageView) {
//                    LLog.i(Tags.TOPIC_VISIBILITY, "----- releaseInBitmap " + System.identityHashCode(bitmap) + " related imageView = " + System.identityHashCode(entry.getValue()));
                    releaseInBitmap(inBitmap);
                }
                break;
            }
        }
    }

    public static void releaseAllInBitmaps() {
//        LLog.i(Tags.TOPIC_VISIBILITY, "-------------------------- releaseAllBitmaps size = " + REUSE_BITMAPS.size());
        for (Entry<Bitmap, ImageView> entry : REUSE_BITMAPS.entrySet()) {
            final Bitmap inBitmap = entry.getKey();
            if (inBitmap != null) {
                releaseInBitmap(inBitmap);
            }
        }
    }

    private static void releaseInBitmap(Bitmap inBitmap) {
        if (inBitmap != null) {
            REUSE_BITMAPS.put(inBitmap, null);
            // recycleInBitmapDelayed(inBitmap);
        }
    }

    /*
    private static boolean isInBitmapReused(Bitmap bitmap) {
        if (bitmap != null) {
            if (REUSE_BITMAPS.containsKey(bitmap)) {
                if (REUSE_BITMAPS.get(bitmap) != null) {
                    return true;
                }
            }
        }
        return false;
    }
    */

    private static void dumpInBitmaps() {
//        LLog.i(Tags.TOPIC_VISIBILITY, "===============================================================");
//        LLog.i(Tags.TOPIC_VISIBILITY, "REUSE_BITMAPS size = " + REUSE_BITMAPS.size());
        for (Entry<Bitmap, ImageView> entry : REUSE_BITMAPS.entrySet()) {
//            LLog.i(Tags.TOPIC_VISIBILITY, "reuseBitmap = " + System.identityHashCode(entry.getKey()) + " related imageView = " + System.identityHashCode(entry.getValue()));
        }
//        LLog.i(Tags.TOPIC_VISIBILITY, "===============================================================");
    }

}
