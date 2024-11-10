package com.github.syr0ws.fastinventory.api.transform.placeholder;

import com.github.syr0ws.fastinventory.api.util.Context;

public interface Placeholder {

    /**
     * Get the name of the placeholder.
     *
     * @return The name of the placeholder.
     */
    String getName();

    /**
     * Get the value of the placeholder for the given context.
     *
     * @param context A Context instance with additional data.
     * @return The value of the placeholder as a String.
     */
    String getValue(Context context);

    /**
     * Check that the placeholder can be applied using the given context. That means that the given context
     * contains all the required data to apply the placeholder.
     *
     * @param context A Context instance with additional data.
     * @return true if the placeholder can be applied using the given context or else false.
     */
    boolean accept(Context context);
}
