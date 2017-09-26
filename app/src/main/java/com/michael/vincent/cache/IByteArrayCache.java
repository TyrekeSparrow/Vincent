package com.michael.vincent.cache;

public interface IByteArrayCache {
    public boolean exists(String url);
    public boolean cache(String url, byte[] byteArray);
    public byte[] getByteArray(String url);
}
