package com.github.syr0ws.fastinventory.api.transform.placeholder;

import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.List;

public interface PlaceholderManager {

    String parse(String text, Context context);

    void addPlaceholder(Placeholder placeholder);

    <T> List<Placeholder> getPlaceholders(Context context);
}
