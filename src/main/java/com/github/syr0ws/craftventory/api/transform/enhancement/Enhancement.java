package com.github.syr0ws.craftventory.api.transform.enhancement;

import com.github.syr0ws.craftventory.api.transform.dto.DTO;
import com.github.syr0ws.craftventory.api.util.Context;

/**
 * Represents an operation that enhances a specific type of {@link DTO}.
 *
 * @param <T> The type of {@link DTO} this enhancement applies to.
 */
public interface Enhancement<T extends DTO> {

    /**
     * Enhances a given {@link DTO} instance by modifying its properties.
     *
     * @param dto     The {@link DTO} instance to enhance. Must not be {@code null}.
     * @param context The {@link Context} instance containing additional data to support the enhancement.
     *                Must not be {@code null}.
     */
    void enhance(T dto, Context context);

    /**
     * Retrieves the Java type of the {@link DTO} that this enhancement can be applied to.
     *
     * @return A {@link Class} object representing the type of {@link DTO}.
     *         Must not be {@code null}.
     */
    Class<T> getDTOClass();

    /**
     * Retrieves the unique identifier for this enhancement.
     *
     * @return A {@link String} representing the unique id of the enhancement. Must not be {@code null}.
     */
    String getId();
}
