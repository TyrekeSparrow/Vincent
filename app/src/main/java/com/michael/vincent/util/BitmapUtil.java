package com.michael.vincent.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import java.lang.reflect.Field;

public class BitmapUtil {
    private static final Config CONFIG = Config.ARGB_8888;

    private static final int FOLLOWED_PRODUCTOR_ICON_WIDTH = 300;
    private static final int FOLLOWED_PRODUCTOR_ICON_HEIGHT = 300;

    private static final int SHORTCUT_BUTTON_WIDTH = 1440;
    private static final int SHORTCUT_BUTTON_HEIGHT = 576;

    private static final int RECOMMENDATION_PORTRAIT_WIDTH = 720;
    private static final int RECOMMENDATION_PORTRAIT_HEIGHT = 1440;
    private static final int RECOMMENDATION_SQUARE_WIDTH = 720;
    private static final int RECOMMENDATION_SQUARE_HEIGHT = 720;
    private static final int RECOMMENDATION_LANDSCAPE_WIDTH = 1440;
    private static final int RECOMMENDATION_LANDSCAPE_HEIGHT = 720;

    private static final int TOPIC_COVER_WIDTH = 1080;
    private static final int TOPIC_COVER_HEIGHT = 600;

    private static final int HOT_PRODUCTOR_WIDTH = 360;
    private static final int HOT_PRODUCTOR_HEIGHT = 420;

    private static final int HOT_CATEGORY_WIDTH = 540;
    private static final int HOT_CATEGORY_HEIGHT = 420;

    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 450;

    private static final int HISTORY_WALLPAPER_THUMBNAIL_WIDTH = 360;
    private static final int HISTORY_WALLPAPER_THUMBNAIL_HEIGHT = 640;

    public static Bitmap generateEmptyMutableBitmap(int width, int height) {
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    /*
    public static Bitmap generateThumbnailInBitmap() {
        final int width = ThumbnailDimension.WIDTH;
        final int height = ThumbnailDimension.HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }
    */


    public static Bitmap generateIconInBitmap() {
        final int width = FOLLOWED_PRODUCTOR_ICON_WIDTH;
        final int height = FOLLOWED_PRODUCTOR_ICON_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateShortcutButtonBitmap() {
        final int width = SHORTCUT_BUTTON_WIDTH;
        final int height = SHORTCUT_BUTTON_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateRecommendationPortraitBitmap() {
        final int width = RECOMMENDATION_PORTRAIT_WIDTH;
        final int height = RECOMMENDATION_PORTRAIT_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateRecommendationSquareBitmap() {
        final int width = RECOMMENDATION_SQUARE_WIDTH;
        final int height = RECOMMENDATION_SQUARE_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateRecommendationLandscapeBitmap() {
        final int width = RECOMMENDATION_LANDSCAPE_WIDTH;
        final int height = RECOMMENDATION_LANDSCAPE_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateTopicCoverBitmap() {
        final int width = TOPIC_COVER_WIDTH;
        final int height = TOPIC_COVER_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateHotProductorBitmap() {
        final int width = HOT_PRODUCTOR_WIDTH;
        final int height = HOT_PRODUCTOR_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateHotCategoryBitmap() {
        final int width = HOT_CATEGORY_WIDTH;
        final int height = HOT_CATEGORY_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateBackgroundBitmap() {
        final int width = BACKGROUND_WIDTH;
        final int height = BACKGROUND_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static Bitmap generateHistoryWallpaperBitmap() {
        final int width = HISTORY_WALLPAPER_THUMBNAIL_WIDTH;
        final int height = HISTORY_WALLPAPER_THUMBNAIL_HEIGHT;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }
    public static Bitmap generateFullWallpaperInBitmap() {
        /*
        if (DisplayDimension.is1080P()) {
            return generateFullInBitmap1080P();
        } else if (DisplayDimension.is2K()) {
            return generateFullInBitmap2K();
        } else {
            return generateFullInBitmap2K();
        }
*/
        return generateFullInBitmap1080P();

    }
/*
    private static Bitmap generateFullInBitmap2K() {
        final int width2K = FullWallpaperDimension.LANDSCAPE_WIDTH_2K;
        final int height2K = FullWallpaperDimension.LANDSCAPE_HEIGHT_2K;
        final Bitmap bitmap2K = Bitmap.createBitmap(width2K, height2K, CONFIG);
        return bitmap2K;
    }*/


    private static Bitmap generateFullInBitmap1080P() {
        final int width1080P = 1080;
        final int height1080P = 1920;
        final Bitmap bitmap1080P = Bitmap.createBitmap(width1080P, height1080P, CONFIG);
        return bitmap1080P;
    }

    public static Bitmap generateThumbnailInBitmapGeneral() {
        final int width = 108;
        final int height = 192;
        final Bitmap bitmap = Bitmap.createBitmap(width, height, CONFIG);
        return bitmap;
    }

    public static byte[] getBitmapBuffer(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                final Field bufferField = Bitmap.class.getDeclaredField("mBuffer");
                bufferField.setAccessible(true);
                final byte[] buffer = (byte[]) bufferField.get(bitmap);
                return buffer;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void clearBitmapBuffer(Bitmap bitmap) {
        if (bitmap != null) {
            final byte[] buffer = getBitmapBuffer(bitmap);
            if (buffer != null) {
                // for each pixel, earse to transparent
            }
        }
    }
}
