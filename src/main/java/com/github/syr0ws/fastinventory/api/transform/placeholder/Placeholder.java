package com.github.syr0ws.fastinventory.api.transform.placeholder;

import com.github.syr0ws.fastinventory.api.util.Context;

/**
 * Represents a dynamic placeholder that can be resolved to a value based on a given {@link Context}.
 */
public interface Placeholder {

    /**
     * Retrieves the name of this placeholder.
     * <p>
     * The name serves as the identifier for the placeholder and is typically used to match
     * and replace the placeholder in a string.
     * </p>
     *
     * @return A {@link String} representing the name of the placeholder. Must not be {@code null}.
     */
    String getName();

    /**
     * Resolves and retrieves the value of this placeholder based on the provided {@link Context}.
     *
     * @param context A {@link Context} instance containing the data necessary for placeholder resolution.
     *                Must not be {@code null}.
     * @return A {@link String} representing the resolved value of the placeholder.
     *
     */
    String getValue(Context context);

    /**
     * Determines whether this placeholder can be applied using the given {@link Context}.
     * <p>
     * A placeholder is considered applicable if the provided {@link Context} contains all the
     * required data to resolve its value.
     * </p>
     *
     * @param context A {@link Context} instance containing additional data for validation.
     *                Must not be {@code null}.
     * @return {@code true} if the placeholder can be applied using the given {@link Context},
     *         otherwise {@code false}.
     */
    boolean accept(Context context);
}
