package com.github.syr0ws.fastinventory.api.transform.placeholder;

import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.List;

public interface PlaceholderManager {

    /**
     * Apply placeholders on a given text.
     *
     * @param text    The text to apply placeholders on.
     * @param context A Context instance with additional data.
     * @return The text in which all the placeholders has been replaced by their value.
     */
    String parse(String text, Context context);

    /**
     * Register a new placeholder.
     *
     * @param placeholder The Placeholder instance to register.
     */
    void addPlaceholder(Placeholder placeholder);

    /**
     * Find all the placeholders that can be applied on a text given a Context.
     *
     * @param context A Context instance with additional data.
     * @return A list of placeholders.
     */
    List<Placeholder> getPlaceholders(Context context);
}
