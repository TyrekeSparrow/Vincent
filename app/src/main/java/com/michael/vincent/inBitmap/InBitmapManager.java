//package com.michael.vincent.inBitmap;
//
//import com.letv.android.wallpaper.log.LLog;
//import com.letv.android.wallpaper.log.Tags;
//
///*
//112K        actionBar
//264K        ???
//352K        icon                        300  * 300  * 4             360 000     inBitmap
//904K        thumbnail                   360  * 640  * 4             921 600     inBitmap
//1.4M        background                  800  * 450  * 4           1 440 000     inBitmap
//2.0M        recommendationSquare        720  * 720  * 4           2 073 600
//2.5M        topic                       1080 * 600  * 4           2 592 000     inBitmap
//3.2M        buttonCover                 1440 * 576  * 4           3 317 760
//4.0M        recommendationLandscape     1440 * 720  * 4           4 147 200
//4.0M        recommendation portrait     720  * 1440 * 4           4 147 200
//14.7        fullWallpaper               2560 * 1440 * 4          14 745 600     inBitmap
//
//112k * 30
//264k * 11
//352k * 18
//904k * 24
//2.5M * 5
//3.2M * 3
//14.7 * 3
//
//112K * 255
//264k * 19
//352k * 36
//904k * 19
//2.5M * 3
//3.2M * 3
//14.7 * 3
//
//112K * 159
//264K * 11
//352K * 15
//904K * 69
//1.4M * 1
//2.0M * 4
//2.5M * 14
//3.2M * 3
//4.0M * 2
//14.7 * 6
//*/
//public class InBitmapManager {
//    /*
//    public static void clearAllInBitmaps() {
//        LLog.i(Tags.INBITMAP_MANAGER, "------------------ InBitmapManager clearAllInBitmaps");
//        // clear fullWallpaper inBitmaps
//        FullWallpaperInBitmapManager.clearAllFullWallpaperInBitmaps();
//        // clear thumbnail inBitmaps
//        ThumbnailInBitmapManager.clearAllThumbnailInBitmaps();
//        // clear topic inBitmaps
//        TopicInBitmapManager.clearAllTopicInBitmaps();
//        // clear icon inBitmap
//        IconInBitmapManager.clearAllIconInBitmaps();
//        // clear bg inBitmap
//        BackgroundInBitmapManager.clearAllIconInBitmaps();
//    }
//    */
//
//    public static void clearAllInBitmapUrls() {
//        LLog.i(Tags.INBITMAP_MANAGER, "------------------ InBitmapManager clearAllInBitmapUrls");
//        // clear fullWallpaper inBitmap urls
//        FullWallpaperInBitmapManager.clearAllFullWallpaperInBitmapUrls();
//        // clear thumbnail inBitmap urls
//        ThumbnailInBitmapManager.clearAllThumbnailInBitmapUrls();
//        // clear topic inBitmap urls
//        TopicInBitmapManager.clearAllTopicInBitmapUrls();
//        // clear icon inBitmap urls
//        IconInBitmapManager.clearAllIconInBitmapUrls();
//        // clear bg inBitmap url
//        BackgroundInBitmapManager.clearBackgroundUrl();
//    }
//
//    /*
//    // =====================================================================================
//    // check running tasks size
//    public static void monitorRunningTasks() {
//        final Timer timer = new Timer("checkTask", true);
//        final TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                checkRunningTaskSize();
//            }
//        };
//        timer.schedule(task, 0, 1000);
//    }
//
//    private static void checkRunningTaskSize() {
//        final ActivityManager activityManager = (ActivityManager) WallpaperApplication.mContext.getSystemService(Context.ACTIVITY_SERVICE);
//        final List<AppTask> appTasks = activityManager.getAppTasks();
//        if (appTasks != null && appTasks.size() > 0) {
//            Log.i(Tags.RUNNING_TASK, "checkRunningTaskSize size = " + appTasks.size());
//            // has running tasks
//            // do nothing
//        } else {
//            Log.i(Tags.RUNNING_TASK, "checkRunningTaskSize no running tasks");
//            // no running tasks
//            clearAllInBitmaps();
//        }
//    }
//    // =====================================================================================
//    */
//}
