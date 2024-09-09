package com.github.syr0ws.fastinventory.api.config.action;

import java.util.Set;

public interface ClickActionLoaderFactory<T> {

    void addLoader(ClickActionLoader<T> loader);

    ClickActionLoader<T> getLoader(String name);

    Set<ClickActionLoader<T>> getLoaders();
}
