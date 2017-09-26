//package com.michael.vincent.inBitmap;
//
//import android.graphics.Bitmap;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.SparseArray;
//
//import com.letv.android.wallpaper.log.LLog;
//import com.letv.android.wallpaper.log.Tags;
//import com.letv.android.wallpaper.util.BitmapUtil;
//
///*
//300 * 300 * 4      360 000
//*/
//// generateInBitmaps
//// clearInBitmaps
//// dumpInBitmaps
//// getEmptyInBitmap
//// setInBitmapUrl
//// getInBitmapUrl
//// get or generate bitmap
//public class IconInBitmapManager {
//    private static final String INDICATOR_IMAGE_URL_ICON = "http://icon.jpg";
//    private static final int MAX_SIZE = 27;
//    private static final Bitmap[] ICON_INBITMAPS = new Bitmap[MAX_SIZE];
//    private static final SparseArray<String> ICON_INBITMAP_URLS = new SparseArray<String>();
//
//    private static void generateEmptyIconInBitmap(int i) {
//        LLog.i(Tags.INBITMAP_ICON, "@@@@@@@@@@@@@@@@@@ generateEmptyIconInBitmap position = " + i);
//        // generate empty icon inBitmap in position
//        ICON_INBITMAPS[i] = BitmapUtil.generateIconInBitmap();
//        // put icon inBitmap image url null
//        if (ICON_INBITMAPS[i] != null) {
//            ICON_INBITMAP_URLS.put(ICON_INBITMAPS[i].hashCode(), null);
//        }
//    }
//
//    private static void dumpIconInBitmapImageUrls() {
//        // ================================================================================================
//        int size = 0;
//        for (Bitmap bitmap : ICON_INBITMAPS) {
//            if (bitmap != null) {
//                size++;
//            }
//        }
//        LLog.i(Tags.INBITMAP_ICON, "--- exist ICON_INBITMAPS size " + size);
//        // ================================================================================================
//        LLog.i(Tags.INBITMAP_ICON, "************************************************************************************");
//        for (int i = 0; i < ICON_INBITMAPS.length; i++) {
//            if (ICON_INBITMAPS[i] != null) {
//                final int hashCode = ICON_INBITMAPS[i].hashCode();
//                final String url = ICON_INBITMAP_URLS.get(hashCode);
//                LLog.i(Tags.INBITMAP_ICON, "--- exist icon inBitmap hashCode = " + hashCode + " url = " + url);
//            }
//        }
//        LLog.i(Tags.INBITMAP_ICON, "************************************************************************************");
//    }
//
//    public static void clearAllIconInBitmaps() {
//        LLog.i(Tags.INBITMAP_ICON, "!!!!!!!!!!!!!!!!!! IconInBitmapManager clearAllIconInBitmaps size = " + ICON_INBITMAPS.length);
//        for (int i = 0; i < ICON_INBITMAPS.length; i++) {
//            if (ICON_INBITMAPS[i] != null) {
////                ICON_INBITMAPS[i].recycle();
//                final Bitmap bitmap = ICON_INBITMAPS[i];
//                BitmapRecycler.recycleBitmap(bitmap);
//                ICON_INBITMAPS[i] = null;
//            }
//        }
//        ICON_INBITMAP_URLS.clear();
//    }
//
//    public static void clearAllIconInBitmapUrls() {
//        LLog.i(Tags.INBITMAP_ICON, "!!!!!!!!!!!!!!!!!! IconInBitmapManager clearAllIconInBitmapUrls size = " + ICON_INBITMAP_URLS.size());
//        for (int i = 0; i < ICON_INBITMAP_URLS.size(); i++) {
//            ICON_INBITMAP_URLS.setValueAt(i, null);
//        }
//    }
//
//    public static Bitmap getEmptyIconInBitmap(String inBitmapImageUrl) {
//        // inBitmap url may be "abcxxx", anything, just a indicator
//        if (inBitmapImageUrl == null) {
//            inBitmapImageUrl = INDICATOR_IMAGE_URL_ICON;
//        }
//        dumpIconInBitmapImageUrls();
//        // get the inBitmap that image url is null
//        Bitmap inBitmap = null;
//        for (int i = 0; i < ICON_INBITMAPS.length; i++) {
//            // if inBitmap in position is null, generate empty inBitmap
//            if (ICON_INBITMAPS[i] == null) {
//                generateEmptyIconInBitmap(i);
//            }
//
//            inBitmap = ICON_INBITMAPS[i];
//            if (inBitmap != null && ICON_INBITMAP_URLS.get(inBitmap.hashCode()) == null) {
//                setIconInBimtapUrl(inBitmap.hashCode(), inBitmapImageUrl);
//                LLog.i(Tags.INBITMAP_ICON, "-------------------------------------------------------------- ggggget empty icon inBitmap position = " + i);
//                break;
//            }
//        }
//        return inBitmap;
//    }
//
//    public static Bitmap getInBitmap(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            for (Bitmap bitmap : ICON_INBITMAPS) {
//                if (bitmap != null && bitmap.hashCode() == inBitmapHashCode) {
//                    return bitmap;
//                }
//            }
//        }
//        return null;
//    }
//
//    private static final String VALUE_NO_KEY = "noKeyyyyy";
//    private static boolean hasKey(int inBitmapHashCode) {
//        // has: null || ""
//        // has not: not null && equals VALUE_NO_KEY
//        final String imageUrl = ICON_INBITMAP_URLS.get(inBitmapHashCode, VALUE_NO_KEY);
//        final boolean hasNot = imageUrl != null && imageUrl.equals(VALUE_NO_KEY);
//        return !hasNot;
//    }
//
//    public static void setIconInBimtapUrl(int inBitmapHashCode, String url) {
////        Log.i(Tags.INBITMAP_ICON,, "setTopicInBimtapUrl inBitmapHashCode = " + inBitmapHashCode);
////        Log.i(Tags.INBITMAP_ICON, "setTopicInBimtapUrl url = " + url);
//        if (inBitmapHashCode != -1 && url != null) {
//            if (hasKey(inBitmapHashCode)) {
//                ICON_INBITMAP_URLS.put(inBitmapHashCode, url);
//            }
//        }
//    }
//
//    public static void clearIconInBitmapUrl(int inBitmapHashCode) {
//        // clear inBitmap image url
//        if (inBitmapHashCode != -1) {
//            if (hasKey(inBitmapHashCode)) {
//                ICON_INBITMAP_URLS.put(inBitmapHashCode, null);
//            }
//        }
//        // clear inBitmap
//        for (int i = 0; i < ICON_INBITMAPS.length; i++) {
//            if (ICON_INBITMAPS[i] != null && ICON_INBITMAPS[i].hashCode() == inBitmapHashCode) {
//                LLog.i(Tags.INBITMAP_ICON, "-------------------------------------------------------------- ccccclear icon inBitmap url position = " + i);
//            }
//        }
//    }
//
//}
