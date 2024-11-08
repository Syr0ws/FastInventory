package com.github.syr0ws.fastinventory.api.transform.mapping;

import com.github.syr0ws.fastinventory.api.util.Context;

@FunctionalInterface
public interface Enhancement<T extends Dto> {

    void enhance(T dto, Context context);
}
