
package com.michael.vincent.display;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;

public final class NewDisplayOptions extends CacheOption {
    public interface DisplayShape {
        public static final int ORIGINAL = 0;
        public static final int ROUND = 1;
    }

    public interface DisplayAnimation {
        public static final int NONE = 0;
        public static final int FADE_IN = 1;
    }

    // display
    // decode options forever not null
    private Options decodeOptions;
    private final ScaleType scaleType;
    private final int displayShape;
    private final int displayAnimation;
    private boolean isOnlyFromCache = false;

    public boolean isOnlyFromCache() {
        return isOnlyFromCache;
    }

    public void setOnlyFromCache(boolean isOnlyFromCache) {
        this.isOnlyFromCache = isOnlyFromCache;
    }

    // disk
    public static NewDisplayOptions generateDiskOptions() {
        final boolean cache2Memory = false;
        final boolean cache2Disk = false;
        final NewDisplayOptions displayOptions = new NewDisplayOptions(cache2Memory, cache2Disk, null, null, DisplayShape.ORIGINAL, DisplayAnimation.NONE);
        return displayOptions;
    }

    // icon
    public static NewDisplayOptions generateIconOptions() {
        final int displayShape = DisplayShape.ORIGINAL;
        final NewDisplayOptions displayOptions = new NewDisplayOptions(true, true, null, null, displayShape, DisplayAnimation.FADE_IN);
        return displayOptions;
    }

    // thumbnail
    public static NewDisplayOptions generateThumbnailOptions() {
        final NewDisplayOptions displayOptions = new NewDisplayOptions();
        return displayOptions;
    }

    // generate inBitmap display options
    public static NewDisplayOptions generateInBitmapOptions(Bitmap inBitmap) {
        final NewDisplayOptions displayOptions = new NewDisplayOptions();
        // set decode inBitmap
        if (inBitmap != null) {
            final Options decodeOptions = displayOptions.getDecodeOptions();
            if (decodeOptions != null) {
                decodeOptions.inBitmap = inBitmap;
            }
        }
        return displayOptions;
    }

    // generate full inBitmap display options
    public static NewDisplayOptions generateFullInBitmapOptions(Bitmap inBitmap) {
        final ScaleType scaleType = ScaleType.CENTER_CROP;
        final NewDisplayOptions displayOptions = new NewDisplayOptions(true, true, null, scaleType, DisplayShape.ORIGINAL, DisplayAnimation.NONE);
        // set decode inBitmap
        if (inBitmap != null) {
            final Options decodeOptions = displayOptions.getDecodeOptions();
            if (decodeOptions != null) {
                decodeOptions.inBitmap = inBitmap;
            }
        }
        return displayOptions;
    }

    // fullImage
    public static NewDisplayOptions generateFullImageOptions() {
        final ScaleType scaleType = ScaleType.CENTER_CROP;
        final NewDisplayOptions displayOptions = new NewDisplayOptions(true, true, null, scaleType, DisplayShape.ORIGINAL, DisplayAnimation.NONE);
        return displayOptions;
    }

    public NewDisplayOptions() {
        super(true, true);
        final Options decodeOptions = new Options();
        this.setDecodeOptions(decodeOptions);
        this.scaleType = null;
        this.displayShape = DisplayShape.ORIGINAL;
        this.displayAnimation = DisplayAnimation.FADE_IN;
    }

    public NewDisplayOptions(boolean cache2Memory, boolean cache2Disk, Options decodeOptions, ScaleType scaleType, int displayShape, int displayAnimation) {
        super(cache2Memory, cache2Disk);
        if (decodeOptions == null) {
            decodeOptions = new Options();
        }
        this.setDecodeOptions(decodeOptions);
        this.scaleType = scaleType;
        this.displayShape = displayShape;
        this.displayAnimation = displayAnimation;
    }

    public Options getDecodeOptions() {
        return decodeOptions;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public int getDisplayShape() {
        return displayShape;
    }

    public int getDisplayAnimation() {
        return displayAnimation;
    }

    public void setDecodeOptions(Options decodeOptions) {
        this.decodeOptions = decodeOptions;
    }
}
