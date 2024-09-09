package com.github.syr0ws.fastinventory.common.placeholder;

import com.github.syr0ws.fastinventory.api.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;

public class ItemSlotPlaceholder implements Placeholder {

    @Override
    public String getName() {
        return "%slot%";
    }

    @Override
    public String getValue(Context context) {
        Integer slot = context.getData(CommonContextKeyEnum.SLOT.name(), Integer.class);
        return Integer.toString(slot);
    }

    @Override
    public boolean accept(Context context) {
        return context.hasData(CommonContextKeyEnum.SLOT.name());
    }
}
