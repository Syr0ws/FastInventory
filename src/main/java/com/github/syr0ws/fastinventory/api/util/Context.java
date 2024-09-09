package com.github.syr0ws.fastinventory.api.util;

public interface Context {

    <T> void addData(String key, T data, Class<T> type);

    boolean hasData(String key);

    <T> T getData(String key, Class<T> type);
}
