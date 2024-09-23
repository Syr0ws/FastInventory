package com.github.syr0ws.fastinventory.api.mapping;

import com.github.syr0ws.fastinventory.api.config.Configuration;
import com.github.syr0ws.fastinventory.api.provider.InventoryProvider;
import com.github.syr0ws.fastinventory.api.util.Context;

public interface Mapper<C extends Configuration, D extends Dto, T> {

    D toDto(C config, InventoryProvider provider, Context context) throws MappingException;

    D enhance(D dto, InventoryProvider provider, Context context) throws MappingException;

    T fromDto(D dto, InventoryProvider provider, Context context) throws MappingException;
}
