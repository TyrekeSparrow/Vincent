//package com.michael.vincent.inBitmap;
//
//import android.graphics.Bitmap;
//
//import com.letv.android.wallpaper.log.LLog;
//import com.letv.android.wallpaper.log.Tags;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class BitmapRecycler {
//    private static final long RECYCLE_DELAY = 500;
//
//    public static void recycleBitmap(final Bitmap bitmap) {
////        recycleNow(bitmap);
////        recycleDelayInMainThread(bitmap);
//        recycleDelayInWorkerThread(bitmap);
//    }
//
//    /*
//    private static void recycleNow(Bitmap bitmap) {
//        bitmap.recycle();
//    }
//
//    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
//    private static void recycleDelayInMainThread(final Bitmap bitmap) {
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                // recycle bitmap
//                LLog.i(Tags.BITMAP_RECYCLE, "!!!!! recycleDelayInMainThread bitmap = " + bitmap);
//                bitmap.recycle();
//            }
//        };
//        MAIN_HANDLER.postDelayed(runnable, RECYCLE_DELAY);
//    }
//    */
//
//    private static final ExecutorService mUploadExecutor = Executors.newSingleThreadExecutor();
//    private static void recycleDelayInWorkerThread(final Bitmap bitmap) {
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                // recycle bitmap
//                LLog.i(Tags.BITMAP_RECYCLE, "!!!!! recycleDelayInWorkerThread bitmap = " + bitmap);
//                sleep(RECYCLE_DELAY);
//                bitmap.recycle();
//            }
//        };
//        mUploadExecutor.submit(runnable);
//    }
//
//    private static void sleep(long time) {
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
