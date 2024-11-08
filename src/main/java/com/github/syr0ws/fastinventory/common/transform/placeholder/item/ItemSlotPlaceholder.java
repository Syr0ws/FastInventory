package com.github.syr0ws.fastinventory.common.transform.placeholder.item;

import com.github.syr0ws.fastinventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.util.CommonContextKey;

public class ItemSlotPlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%slot%";
    }

    @Override
    public String getValue(Context context) {
        Integer slot = context.getData(CommonContextKey.SLOT.name(), Integer.class);
        return Integer.toString(slot);
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKey.SLOT.name());
    }
}
