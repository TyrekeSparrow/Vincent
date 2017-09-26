//package com.michael.vincent.inBitmap;
//
//import android.graphics.Bitmap;
//import android.util.Log;
//import android.util.SparseArray;
//
//import com.letv.android.wallpaper.log.LLog;
//import com.letv.android.wallpaper.log.Tags;
//import com.letv.android.wallpaper.util.BitmapUtil;
//
///*
//1080 * 600 * 4      2 592 000
//*/
//// generateInBitmaps
//// clearInBitmaps
//// dumpInBitmaps
//// getEmptyInBitmap
//// setInBitmapUrl
//// getInBitmapUrl
//// get or generate bitmap
//public class TopicInBitmapManager {
//    private static final String INDICATOR_IMAGE_URL_TOPIC = "http://topic.jpg";
//    private static final int MAX_SIZE = 15;
//    private static final Bitmap[] TOPIC_INBITMAPS = new Bitmap[MAX_SIZE];
//    private static final SparseArray<String> TOPIC_INBITMAP_URLS = new SparseArray<String>();
//
//    private static void generateEmptyTopicInBitmap(int i) {
//        LLog.i(Tags.INBITMAP_TOPIC, "@@@@@@@@@@@@@@@@@@ generateEmptyTopicInBitmap position = " + i);
//        // generate empty topic inBitmap in position
//        TOPIC_INBITMAPS[i] = BitmapUtil.generateTopicCoverBitmap();
//        // put topic inBitmap image url null
//        if (TOPIC_INBITMAPS[i] != null) {
//            TOPIC_INBITMAP_URLS.put(TOPIC_INBITMAPS[i].hashCode(), null);
//        }
//    }
//
//    private static void dumpTopicInBitmapImageUrls() {
//        // ================================================================================================
//        int size = 0;
//        for (Bitmap bitmap : TOPIC_INBITMAPS) {
//            if (bitmap != null) {
//                size++;
//            }
//        }
//        LLog.i(Tags.INBITMAP_TOPIC, "--- exist TOPIC_INBITMAPS size " + size);
//        // ================================================================================================
//        LLog.i(Tags.INBITMAP_TOPIC, "************************************************************************************");
//        for (int i = 0; i < TOPIC_INBITMAPS.length; i++) {
//            if (TOPIC_INBITMAPS[i] != null) {
//                final int hashCode = TOPIC_INBITMAPS[i].hashCode();
//                final String url = TOPIC_INBITMAP_URLS.get(hashCode);
//                LLog.i(Tags.INBITMAP_TOPIC, "--- exist topic inBitmap hashCode = " + hashCode + " url = " + url);
//            }
//        }
//        LLog.i(Tags.INBITMAP_TOPIC, "************************************************************************************");
//    }
//
//    public static void clearAllTopicInBitmaps() {
//        LLog.i(Tags.INBITMAP_TOPIC, "!!!!!!!!!!!!!!!!!! TopicInBitmapManager clearAllTopicInBitmaps size = " + TOPIC_INBITMAPS.length);
//        for (int i = 0; i < TOPIC_INBITMAPS.length; i++) {
//            if (TOPIC_INBITMAPS[i] != null) {
////                TOPIC_INBITMAPS[i].recycle();
//                final Bitmap bitmap = TOPIC_INBITMAPS[i];
//                BitmapRecycler.recycleBitmap(bitmap);
//                TOPIC_INBITMAPS[i] = null;
//            }
//        }
//        TOPIC_INBITMAP_URLS.clear();
//    }
//
//    public static void clearAllTopicInBitmapUrls() {
//        LLog.i(Tags.INBITMAP_TOPIC, "!!!!!!!!!!!!!!!!!! TopicInBitmapManager clearAllTopicInBitmapUrls size = " + TOPIC_INBITMAP_URLS.size());
//        for (int i = 0; i < TOPIC_INBITMAP_URLS.size(); i++) {
//            TOPIC_INBITMAP_URLS.setValueAt(i, null);
//        }
//    }
//
//    public static Bitmap getEmptyTopicInBitmap(String inBitmapImageUrl) {
//        // inBitmap url may be "abcxxx", anything, just a indicator
//        if (inBitmapImageUrl == null) {
//            inBitmapImageUrl = INDICATOR_IMAGE_URL_TOPIC;
//        }
//        dumpTopicInBitmapImageUrls();
//        // get the inBitmap that image url is null
//        Bitmap inBitmap = null;
//        for (int i = 0; i < TOPIC_INBITMAPS.length; i++) {
//            // if inBitmap in position is null, generate empty inBitmap
//            if (TOPIC_INBITMAPS[i] == null) {
//                generateEmptyTopicInBitmap(i);
//            }
//
//            inBitmap = TOPIC_INBITMAPS[i];
//            if (inBitmap != null && TOPIC_INBITMAP_URLS.get(inBitmap.hashCode()) == null) {
//                setTopicInBimtapUrl(inBitmap.hashCode(), inBitmapImageUrl);
//                LLog.i(Tags.INBITMAP_TOPIC, "-------------------------------------------------------------- ggggget empty topic inBitmap position = " + i);
//                break;
//            }
//        }
//        return inBitmap;
//    }
//
//    public static Bitmap getInBitmap(int inBitmapHashCode) {
//        if (inBitmapHashCode != -1) {
//            for (Bitmap bitmap : TOPIC_INBITMAPS) {
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
//        final String imageUrl = TOPIC_INBITMAP_URLS.get(inBitmapHashCode, VALUE_NO_KEY);
//        final boolean hasNot = imageUrl != null && imageUrl.equals(VALUE_NO_KEY);
//        return !hasNot;
//    }
//
//    public static void setTopicInBimtapUrl(int inBitmapHashCode, String url) {
////      LLog.i(Tags.INBITMAP_TOPIC, "setTopicInBimtapUrl inBitmapHashCode = " + inBitmapHashCode);
////      LLog.i(Tags.INBITMAP_TOPIC, "setTopicInBimtapUrl url = " + url);
//        if (inBitmapHashCode != -1 && url != null) {
//            if (hasKey(inBitmapHashCode)) {
//                TOPIC_INBITMAP_URLS.put(inBitmapHashCode, url);
//            }
//        }
//    }
//
//    public static void clearTopicInBitmapUrl(int inBitmapHashCode) {
//        // clear inBitmap image url
//        if (inBitmapHashCode != -1) {
//            if (hasKey(inBitmapHashCode)) {
//                TOPIC_INBITMAP_URLS.put(inBitmapHashCode, null);
//            }
//        }
//        // clear inBitmap
//        for (int i = 0; i < TOPIC_INBITMAPS.length; i++) {
//            if (TOPIC_INBITMAPS[i] != null && TOPIC_INBITMAPS[i].hashCode() == inBitmapHashCode) {
//                LLog.i(Tags.INBITMAP_TOPIC, "-------------------------------------------------------------- ccccclear topic inBitmap url position = " + i);
//            }
//        }
//    }
//
//}
