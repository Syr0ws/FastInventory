package com.github.syr0ws.craftventory.api.transform.placeholder;

import com.github.syr0ws.craftventory.api.util.Context;

import java.util.List;

/**
 * Manages {@link Placeholder}'s lifecycle.
 */
public interface PlaceholderManager {

    /**
     * Applies placeholders to the given text by replacing them with their corresponding values.
     * <p>
     * The method looks for all placeholders within the text and resolves them using the provided {@link Context}.
     * </p>
     *
     * @param text    The text containing placeholders to be replaced. Must not be {@code null}.
     * @param context A {@link Context} instance containing additional data required for resolving the placeholders.
     *                Must not be {@code null}.
     * @return A {@link String} with all placeholders replaced by their corresponding values.
     *         If no placeholders are found, the original text is returned.
     * @throws IllegalArgumentException If {@code text} or {@code context} is {@code null}.
     */
    String parse(String text, Context context);

    /**
     * Registers a new {@link Placeholder} instance.
     *
     * @param placeholder The {@link Placeholder} instance to register. Must not be {@code null}.
     * @throws IllegalArgumentException If {@code placeholder} is {@code null}.
     */
    void addPlaceholder(Placeholder placeholder);

    /**
     * Retrieves all {@link Placeholder} instances that can be applied to a text based on the provided {@link Context}.
     *
     * @param context A {@link Context} instance containing the necessary data for resolving placeholders.
     *                Must not be {@code null}.
     * @return A {@link List} of {@link Placeholder} instances that can be applied to the given {@link Context}.
     *         If no placeholders are applicable, returns an empty list.
     * @throws IllegalArgumentException If {@code context} is {@code null}.
     */
    List<Placeholder> getPlaceholders(Context context);
}
