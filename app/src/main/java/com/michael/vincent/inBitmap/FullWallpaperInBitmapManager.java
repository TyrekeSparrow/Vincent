//package com.michael.vincent.inBitmap;
//
//import android.graphics.Bitmap;
//import android.util.SparseArray;
//
//import com.letv.android.wallpaper.log.LLog;
//import com.letv.android.wallpaper.log.Tags;
//import com.letv.android.wallpaper.util.BitmapUtil;
//
//public class FullWallpaperInBitmapManager {
//    private static final String INDICATOR_IMAGE_URL_FULLWALLPAPER = "http://fullWallpaper.jpg";
//    private static final int MAX_SIZE = 3 + 3;
//    private static final Bitmap[] FULLWALLPAPER_INBITMAPS = new Bitmap[MAX_SIZE];
//    private static final SparseArray<String> FULLWALLPAPER_INBITMAP_URLS = new SparseArray<String>();
//
//    public static void clearAllFullWallpaperInBitmaps() {
//        LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "!!!!!!!!!!!!!!!!!! FullWallpaperInBitmapManager clearAllFullWallpaperInBitmaps size = " + FULLWALLPAPER_INBITMAPS.length);
//        for (int i = 0; i < FULLWALLPAPER_INBITMAPS.length; i++) {
//            if (FULLWALLPAPER_INBITMAPS[i] != null) {
////                FULLWALLPAPER_INBITMAPS[i].recycle();
//                final Bitmap bitmap = FULLWALLPAPER_INBITMAPS[i];
//                BitmapRecycler.recycleBitmap(bitmap);
//                FULLWALLPAPER_INBITMAPS[i] = null;
//            }
//        }
//        FULLWALLPAPER_INBITMAP_URLS.clear();
//    }
//
//    public static void clearAllFullWallpaperInBitmapUrls() {
//        LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "!!!!!!!!!!!!!!!!!! FullWallpaperInBitmapManager clearAllFullWallpaperInBitmapUrls size = " + FULLWALLPAPER_INBITMAP_URLS.size());
//        // clear inBitmap urls
//        for (int i = 0; i < FULLWALLPAPER_INBITMAP_URLS.size(); i++) {
//            FULLWALLPAPER_INBITMAP_URLS.setValueAt(i, null);
//        }
//        /*
//        // clear inBitmap contents
//        for (Bitmap bitmap : FULLWALLPAPER_INBITMAPS) {
//            // TODO maybe need to clear
//        }
//        */
//    }
//
//    private static void generateEmptyFullWallpaperInBitmap(int i) {
//        LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "@@@@@@@@@@@@@@@@@@ generateEmptyFullWallpaperInBitmap position = " + i);
//        // generate empty inBitmap in position
//        FULLWALLPAPER_INBITMAPS[i] = BitmapUtil.generateFullWallpaperInBitmap();
//        // cache image url null
//        if (FULLWALLPAPER_INBITMAPS[i] != null) {
//            FULLWALLPAPER_INBITMAP_URLS.put(FULLWALLPAPER_INBITMAPS[i].hashCode(), null);
//        }
//    }
//
//    private static void dumpFullWallpaperInBitmapImageUrls() {
//        // ================================================================================================
//        int size = 0;
//        for (Bitmap bitmap : FULLWALLPAPER_INBITMAPS) {
//            if (bitmap != null) {
//                size++;
//            }
//        }
//        LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "--- exist FULLWALLPAPER_INBITMAPS size " + size);
//        // ================================================================================================
//        LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "************************************************************************************");
////        LLog.i(Tags.VIEW_LIFE, "************************************************************************************");
//        for (int i = 0; i < FULLWALLPAPER_INBITMAPS.length; i++) {
//            if (FULLWALLPAPER_INBITMAPS[i] != null) {
//                final int hashCode = FULLWALLPAPER_INBITMAPS[i].hashCode();
//                final String url = FULLWALLPAPER_INBITMAP_URLS.get(hashCode);
//                LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "--- exist fullWallpaper inBitmap hashCode = " + hashCode + " url = " + url);
////                LLog.i(Tags.VIEW_LIFE, "--- exist fullWallpaper inBitmap hashCode = " + hashCode + " url = " + url);
//            }
//        }
////        LLog.i(Tags.VIEW_LIFE, "************************************************************************************");
//        LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "************************************************************************************");
//    }
//
//    /*
//    public static boolean isIn(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            for (Bitmap bitmap : FULLWALLPAPER_INBITMAPS) {
//                if (bitmap != null && bitmap.hashCode() == inBitmapHashCode) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    */
//
//    public static Bitmap getInBitmap(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            for (Bitmap bitmap : FULLWALLPAPER_INBITMAPS) {
//                if (bitmap != null && bitmap.hashCode() == inBitmapHashCode) {
//                    return bitmap;
//                }
//            }
//        }
//        return null;
//    }
//
//    public static Bitmap getEmptyFullWallpaperInBitmap(String inBitmapImageUrl) {
//        // inBitmap url may be "abcxxx", anything, just a indicator
//        if (inBitmapImageUrl == null) {
//            inBitmapImageUrl = INDICATOR_IMAGE_URL_FULLWALLPAPER;
//        }
//        dumpFullWallpaperInBitmapImageUrls();
//        // get the inBitmap that image url is null
//        Bitmap inBitmap = null;
//        for (int i = 0; i < FULLWALLPAPER_INBITMAPS.length; i++) {
//            // if inBitmap in position is null, generate empty inBitmap
//            if (FULLWALLPAPER_INBITMAPS[i] == null) {
//                generateEmptyFullWallpaperInBitmap(i);
//            }
//
//            inBitmap = FULLWALLPAPER_INBITMAPS[i];
//            if (inBitmap != null && FULLWALLPAPER_INBITMAP_URLS.get(inBitmap.hashCode()) == null) {
//                setFullWallpaperInBimtapUrl(inBitmap.hashCode(), inBitmapImageUrl);
//                LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ ggggget empty fullWallpaper inBitmap position = " + i);
//                break;
//            }
//        }
//        return inBitmap;
//    }
//
//    private static final String VALUE_NO_KEY = "noKeyyyyy";
//    private static boolean hasKey(int inBitmapHashCode) {
//        // has: null || ""
//        // has not: not null && equals VALUE_NO_KEY
//        final String imageUrl = FULLWALLPAPER_INBITMAP_URLS.get(inBitmapHashCode, VALUE_NO_KEY);
//        final boolean hasNot = imageUrl != null && imageUrl.equals(VALUE_NO_KEY);
//        return !hasNot;
//    }
//
//    public static void setFullWallpaperInBimtapUrl(int inBitmapHashCode, String url) {
////      LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "setFullWallpaperInBimtapUrl inBitmapHashCode = " + inBitmapHashCode);
////      LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "setFullWallpaperInBimtapUrl url = " + url);
//      if (inBitmapHashCode != -1 && url != null) {
//          if (hasKey(inBitmapHashCode)) {
//              FULLWALLPAPER_INBITMAP_URLS.put(inBitmapHashCode, url);
//          }
//      }
//  }
//
//    public static void clearFullWallpaperInBitmapUrl(int inBitmapHashCode) {
//        // clear inBitmap image url
//        if (inBitmapHashCode != -1) {
//            if (hasKey(inBitmapHashCode)) {
//                FULLWALLPAPER_INBITMAP_URLS.put(inBitmapHashCode, null);
//            }
//        }
//        // clear inBitmap
//        for (int i = 0; i < FULLWALLPAPER_INBITMAPS.length; i++) {
//            if (FULLWALLPAPER_INBITMAPS[i] != null && FULLWALLPAPER_INBITMAPS[i].hashCode() == inBitmapHashCode) {
//                LLog.i(Tags.INBITMAP_FULLWALLPAEPER, "-------------------------------------------------------------- ccccclear fullWallpaper inBitmap url position = " + i);
//            }
//        }
//    }
//
//    public static String getUrl(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            final String currentImageUrl = FULLWALLPAPER_INBITMAP_URLS.get(inBitmapHashCode);
//            return currentImageUrl;
//        }
//        return null;
//    }
//}
