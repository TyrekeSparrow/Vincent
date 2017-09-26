package com.michael.vincent.reuse;

public interface ReuseBitmapManager {
/*
112K        actionBar
264K        ???
352K        icon                        300  * 300  * 4             360 000     inBitmap
904K        thumbnail                   360  * 640  * 4             921 600     inBitmap
1.4M        background                  800  * 450  * 4           1 440 000     inBitmap
2.0M        recommendationSquare        720  * 720  * 4           2 073 600
2.5M        topic                       1080 * 600  * 4           2 592 000     inBitmap
3.2M        buttonCover                 1440 * 576  * 4           3 317 760
4.0M        recommendationLandscape     1440 * 720  * 4           4 147 200
4.0M        recommendation portrait     720  * 1440 * 4           4 147 200
14.7        fullWallpaper               2560 * 1440 * 4          14 745 600     inBitmap

icon            352K    27      11      10MB
thumbnail       904K    24      24      25MB
topic           2.5M    15      8       40MB
fullWallpaper   14.7    3       3       40MB
                                        120MB

112k * 30
264k * 11
352k * 18
904k * 24
2.5M * 5
3.2M * 3
14.7 * 3

112K * 255
264k * 19
352k * 36
904k * 19
2.5M * 3
3.2M * 3
14.7 * 3

112K * 159
264K * 11
352K * 15
904K * 69
1.4M * 1
2.0M * 4
2.5M * 14
3.2M * 3
4.0M * 2
14.7 * 6
*/
}
