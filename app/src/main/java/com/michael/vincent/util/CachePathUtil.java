
package com.michael.vincent.util;

import android.content.Context;
import android.os.Environment;

import com.michael.vincent.application.MyApplication;

import java.io.File;

public class CachePathUtil {
    private static String CACHE_DIR = null;

    public static void init() {
        new Thread() {
            public void run() {
                initCachePath();
            };
        }.start();
    }

    public static void initCachePath() {
        Context context = MyApplication.mContext;
        if (context != null) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null) {
                CACHE_DIR = cacheDir.getAbsolutePath();
            } else {
                String path = Environment.getExternalStorageDirectory().getPath() + "/" + "Android/data/com.letv.android.wallpaperonline/cache";
                if (path != null) {
                    CACHE_DIR = path;
                }
            }
        }
    }

    /**
     * CACHE_DIR possible null !!!
     * @return
     */
    public static String getCacheDir() {
        if (CACHE_DIR == null) {
            initCachePath();
        }
        return CACHE_DIR;
    }

}
