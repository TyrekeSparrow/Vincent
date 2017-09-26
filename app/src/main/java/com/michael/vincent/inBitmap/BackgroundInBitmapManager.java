//package com.michael.vincent.inBitmap;
//
//import android.graphics.Bitmap;
//import android.util.Log;
//
//import com.letv.android.wallpaper.log.Tags;
//import com.letv.android.wallpaper.util.BitmapUtil;
//
///*
//800 * 450 * 4       1 440 000
//*/
//// generateInBitmaps
//// clearInBitmaps
//// dumpInBitmaps
//// getEmptyInBitmap
//// getInBitmapUrl
//// get or generate bitmap
//public class BackgroundInBitmapManager {
//    private static final String INDICATOR_IMAGE_URL_BACKGROUND = "http://background.jpg";
//    private static Bitmap BACKGROUND_INBITMAP = BitmapUtil.generateBackgroundBitmap();
//    private static String INBITMAP_URL;
//
//    private static void generateEmptyBackgroundInBitmap() {
//        if (BACKGROUND_INBITMAP == null) {
//            BACKGROUND_INBITMAP = BitmapUtil.generateBackgroundBitmap();
//        }
//    }
//
//    private static void dumpBackgroundInBitmap() {
//        Log.i(Tags.INBITMAP_BACKGROUND, "--- exist bg inBitmap hashCode = " + BACKGROUND_INBITMAP + " url = " + INBITMAP_URL);
//    }
//
//    public static void clearBackgroundInBitmap() {
//        if (BACKGROUND_INBITMAP != null) {
//            BitmapRecycler.recycleBitmap(BACKGROUND_INBITMAP);
//            BACKGROUND_INBITMAP = null;
//        }
//        clearBackgroundUrl();
//    }
//
//    public static void clearBackgroundUrl() {
//        if (INBITMAP_URL != null) {
//            INBITMAP_URL = null;
//        }
//    }
//
//    public static Bitmap getEmptyBackgroundInBitmap(String inBitmapImageUrl) {
////        Log.i(Tags.DEBUG_MEMORY, "************************************************************************************");
//        // inBitmap url may be "abcxxx", anything, just a indicator
//        if (inBitmapImageUrl == null) {
//            inBitmapImageUrl = INDICATOR_IMAGE_URL_BACKGROUND;
//        }
//        dumpBackgroundInBitmap();
//        // get the inBitmap that image url is null
//        if (BACKGROUND_INBITMAP == null) {
//            generateEmptyBackgroundInBitmap();
//        }
//        if (BACKGROUND_INBITMAP != null) {
//            INBITMAP_URL = inBitmapImageUrl;
//        }
////        Log.i(Tags.DEBUG_MEMORY, "************************************************************************************");
//        return BACKGROUND_INBITMAP;
//    }
//
//    public static Bitmap getInBitmap(int inBitmapHashCode) {
//        return BACKGROUND_INBITMAP;
//    }
//
//    public static void clearBackgroundUrl(int inBitmapHashCode) {
//        // clear inBitmap image url
//        if (inBitmapHashCode != -1 && BACKGROUND_INBITMAP != null && inBitmapHashCode == BACKGROUND_INBITMAP.hashCode()) {
//            INBITMAP_URL = null;
//        }
//    }
//
//}
