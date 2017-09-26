//package com.michael.vincent.inBitmap;
//
//import android.graphics.Bitmap;
//import android.util.SparseArray;
//
//import com.letv.android.wallpaper.log.LLog;
//import com.letv.android.wallpaper.log.Tags;
//import com.letv.android.wallpaper.util.BitmapUtil;
//
///*
//360 * 640 * 4           921 600
//*/
//// generateInBitmaps
//// clearInBitmaps
//// dumpInBitmaps
//// getEmptyInBitmap
//// setInBitmapUrl
//// getInBitmapUrl
//// get or generate bitmap
//public class ThumbnailInBitmapManager {
//    public static final String INDICATOR_IMAGE_URL_THUMBNAIL = "http://thumbnail.jpg";
//    // 24 thumbnails in fullWallpaper relevant wallpaper
//    private static final int THUMBNAIL_INBITMAP_MAX_SIZE = 24 + 6;
//    private static final Bitmap[] THUMBNAIL_INBITMAPS = new Bitmap[THUMBNAIL_INBITMAP_MAX_SIZE];
//    private static final SparseArray<String> THUMBNAIL_INBITMAP_URLS = new SparseArray<String>();
//
//    public static void clearAllThumbnailInBitmaps() {
//        LLog.i(Tags.INBITMAP_THUMBNAIL, "!!!!!!!!!!!!!!!!!! ThumbnailInBitmapManager clearAllThumbnailInBitmaps size = " + THUMBNAIL_INBITMAPS.length);
//        for (int i = 0; i < THUMBNAIL_INBITMAPS.length; i++) {
//            if (THUMBNAIL_INBITMAPS[i] != null) {
////                THUMBNAIL_INBITMAPS[i].recycle();
//                final Bitmap bitmap = THUMBNAIL_INBITMAPS[i];
//                BitmapRecycler.recycleBitmap(bitmap);
//                THUMBNAIL_INBITMAPS[i] = null;
//            }
//        }
//        THUMBNAIL_INBITMAP_URLS.clear();
//    }
//
//    public static void clearAllThumbnailInBitmapUrls() {
//        LLog.i(Tags.INBITMAP_THUMBNAIL, "!!!!!!!!!!!!!!!!!! ThumbnailInBitmapManager clearAllThumbnailInBitmapUrls size = " + THUMBNAIL_INBITMAP_URLS.size());
//        for (int i = 0; i < THUMBNAIL_INBITMAP_URLS.size(); i++) {
//            THUMBNAIL_INBITMAP_URLS.setValueAt(i, null);
//        }
//    }
//
//    private static void generateEmptyThumbnailInBitmap(int i) {
//        LLog.i(Tags.INBITMAP_THUMBNAIL, "@@@@@@@@@@@@@@@@@@ generateEmptyThumbnailInBitmap position = " + i);
//        // generate empty inBitmap in position
//        THUMBNAIL_INBITMAPS[i] = BitmapUtil.generateThumbnailInBitmapGeneral();
//        // cache image url null
//        if (THUMBNAIL_INBITMAPS[i] != null) {
//            THUMBNAIL_INBITMAP_URLS.put(THUMBNAIL_INBITMAPS[i].hashCode(), null);
//        }
//    }
//
//    public static Bitmap getInBitmap(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            for (Bitmap bitmap : THUMBNAIL_INBITMAPS) {
//                if (bitmap != null && bitmap.hashCode() == inBitmapHashCode) {
//                    return bitmap;
//                }
//            }
//        }
//        return null;
//    }
//
//    private static void dumpThumbnailInBitmapImageUrls() {
//        // ================================================================================================
//        int size = 0;
//        for (Bitmap bitmap : THUMBNAIL_INBITMAPS) {
//            if (bitmap != null) {
//                size++;
//            }
//        }
//        LLog.i(Tags.INBITMAP_THUMBNAIL, "--- exist THUMBNAIL_INBITMAPS size " + size);
//        // ================================================================================================
//        LLog.i(Tags.INBITMAP_THUMBNAIL, "************************************************************************************");
//        for (int i = 0; i < THUMBNAIL_INBITMAPS.length; i++) {
//            if (THUMBNAIL_INBITMAPS[i] != null) {
//                final int hashCode = THUMBNAIL_INBITMAPS[i].hashCode();
//                final String url = THUMBNAIL_INBITMAP_URLS.get(hashCode);
//                LLog.i(Tags.INBITMAP_THUMBNAIL, "--- hashCode = " + hashCode + " url = " + url);
//            }
//        }
//        LLog.i(Tags.INBITMAP_THUMBNAIL, "************************************************************************************");
//    }
//
//    public static Bitmap getEmptyThumbnailInBitmap(String inBitmapImageUrl) {
//        // inBitmap url may be "abcxxx", anything, just a indicator
//        if (inBitmapImageUrl == null) {
//            inBitmapImageUrl = INDICATOR_IMAGE_URL_THUMBNAIL;
//        }
//        dumpThumbnailInBitmapImageUrls();
//        // get the inBitmap that image url is null
//        Bitmap inBitmap = null;
//        for (int i = 0; i < THUMBNAIL_INBITMAPS.length; i++) {
//            // if inBitmap in position is null, generate empty inBitmap
//            if (THUMBNAIL_INBITMAPS[i] == null) {
//                generateEmptyThumbnailInBitmap(i);
//            }
//
//            inBitmap = THUMBNAIL_INBITMAPS[i];
//            if (inBitmap != null && THUMBNAIL_INBITMAP_URLS.get(inBitmap.hashCode()) == null) {
//                setThumbnailInBimtapUrl(inBitmap.hashCode(), inBitmapImageUrl);
//                LLog.i(Tags.INBITMAP_THUMBNAIL, "-------------------------------------------------------------- ggggget empty thumbnail inBitmap position = " + i);
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
//        final String imageUrl = THUMBNAIL_INBITMAP_URLS.get(inBitmapHashCode, VALUE_NO_KEY);
//        final boolean hasNot = (imageUrl != null && imageUrl.equals(VALUE_NO_KEY));
//        return !hasNot;
//    }
//
//    public static void setThumbnailInBimtapUrl(int inBitmapHashCode, String url) {
////      LLog.i(Tags.INBITMAP_THUMBNAIL, "setThumbnailInBimtapUrl inBitmapHashCode = " + inBitmapHashCode);
////      LLog.i(Tags.INBITMAP_THUMBNAIL, "setThumbnailInBimtapUrl url = " + url);
//        if (inBitmapHashCode != -1 && url != null) {
//            if (hasKey(inBitmapHashCode)) {
//                THUMBNAIL_INBITMAP_URLS.put(inBitmapHashCode, url);
//            }
//        }
//    }
//
//    public static String getThumbnailInBitmapUrl(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            if (hasKey(inBitmapHashCode)) {
//                final String inBitmapImageUrl = THUMBNAIL_INBITMAP_URLS.get(inBitmapHashCode);
//                return inBitmapImageUrl;
//            }
//        }
//        return null;
//    }
//
//    public static boolean isInUse(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            if (hasKey(inBitmapHashCode)) {
//                final String inBitmapImageUrl = THUMBNAIL_INBITMAP_URLS.get(inBitmapHashCode);
//                if (inBitmapImageUrl != null && !inBitmapImageUrl.equals(INDICATOR_IMAGE_URL_THUMBNAIL)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static void clearThumbnailInBitmapUrl(int inBitmapHashCode) {
//        // clear inBitmap image url
//        if (inBitmapHashCode != -1) {
//            if (hasKey(inBitmapHashCode)) {
//                THUMBNAIL_INBITMAP_URLS.put(inBitmapHashCode, null);
//            }
//        }
//        // clear inBitmap
//        for (int i = 0; i < THUMBNAIL_INBITMAPS.length; i++) {
//            if (THUMBNAIL_INBITMAPS[i] != null && THUMBNAIL_INBITMAPS[i].hashCode() == inBitmapHashCode) {
//                LLog.i(Tags.INBITMAP_THUMBNAIL, "-------------------------------------------------------------- ccccclear thumbnail inBitmap url position = " + i);
//            }
//        }
//
//    }
//
//
//}
