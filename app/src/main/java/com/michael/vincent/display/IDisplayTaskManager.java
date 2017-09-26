package com.michael.vincent.display;

import android.widget.ImageView;

public interface IDisplayTaskManager {
    public void display(ImageView imageView, String url, NewDisplayOptions displayOptions, CacheTask.OnCompleteListener onCompleteListener, CacheTask.OnProgressListener onProgressListener, long delay);
    public void cancelDisplay(ImageView imageView);
}
