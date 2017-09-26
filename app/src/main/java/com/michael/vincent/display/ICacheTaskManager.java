package com.michael.vincent.display;

public interface ICacheTaskManager {
    public void cache(String url, CacheOption cacheOption, CacheTask.OnCompleteListener onCompleteListener, CacheTask.OnProgressListener onProgressListener, long delay);
    public void cancelCache(String url);
}
