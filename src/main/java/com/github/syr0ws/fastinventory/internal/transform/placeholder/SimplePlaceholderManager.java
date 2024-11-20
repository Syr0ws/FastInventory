package com.github.syr0ws.fastinventory.internal.transform.placeholder;

import com.github.syr0ws.fastinventory.api.transform.placeholder.Placeholder;
import com.github.syr0ws.fastinventory.api.transform.placeholder.PlaceholderManager;
import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePlaceholderManager implements PlaceholderManager {

    private final Map<String, Placeholder> placeholders = new HashMap<>();

    @Override
    public String parse(String text, Context context) {

        if (text == null) {
            throw new IllegalArgumentException("text cannot be null");
        }

        if(context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        List<Placeholder> placeholders = this.getPlaceholders(context);

        for (Placeholder placeholder : placeholders) {
            text = text.replace(placeholder.getName(), placeholder.getValue(context));
        }

        return text;
    }

    @Override
    public void addPlaceholder(Placeholder placeholder) {

        if (placeholder == null) {
            throw new IllegalArgumentException("placeholder cannot be null");
        }

        this.placeholders.put(placeholder.getName(), placeholder);
    }

    @Override
    public List<Placeholder> getPlaceholders(Context context) {

        if(context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        return this.placeholders.values().stream()
                .filter(placeholder -> placeholder.accept(context))
                .toList();
    }
}
