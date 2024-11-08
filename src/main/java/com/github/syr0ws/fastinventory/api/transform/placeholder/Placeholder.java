package com.github.syr0ws.fastinventory.api.transform.placeholder;

import com.github.syr0ws.fastinventory.api.util.Context;

public interface Placeholder {

    String getName();

    String getValue(Context context);

    boolean accept(Context context);
}
