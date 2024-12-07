package com.github.syr0ws.craftventory.api.transform.enhancement;

import com.github.syr0ws.craftventory.api.transform.dto.DTO;
import com.github.syr0ws.craftventory.api.util.Context;

import java.util.List;

/**
 * Manages the registration and the execution of {@link Enhancement} instances to {@link DTO} objects.
 */
public interface EnhancementManager {

    /**
     * Enhances a given {@link DTO} by applying all registered {@link Enhancement} that match its type.
     *
     * @param <T>      The type of the {@link DTO} to enhance.
     * @param dto      The {@link DTO} instance to enhance. Must not be {@code null}.
     * @param dtoClass The Java class type of the {@link DTO}. Must not be {@code null}.
     * @param context  A {@link Context} instance containing additional data to support the enhancement.
     *                 Must not be {@code null}.
     * @throws IllegalArgumentException If any of the parameters are {@code null}.
     */
    <T extends DTO> void enhance(T dto, Class<T> dtoClass, Context context);

    /**
     * Registers a new {@link Enhancement} for a specific {@link DTO} type.
     *
     * @param dtoId       The unique identifier of the {@link DTO} to which the enhancement applies.
     *                    Must not be {@code null}.
     * @param enhancement The {@link Enhancement} to register. Must not be {@code null}.
     * @throws IllegalArgumentException If {@code dtoId} is {@code null} or empty, or if {@code enhancement} is {@code null}.
     */
    void addEnhancement(String dtoId, Enhancement<?> enhancement);

    /**
     * Removes a registered {@link Enhancement} by id.
     *
     * @param enhancementId The unique id of the enhancement to remove. Must not be {@code null}.
     * @return {@code true} if an enhancement matching the given id was successfully removed, otherwise {@code false}.
     * @throws IllegalArgumentException If {@code enhancementId} is {@code null}.
     */
    boolean removeEnhancement(String enhancementId);

    /**
     * Retrieves all {@link Enhancement} applicable to a specific {@link DTO}.
     *
     * @param <T>      The type of the {@link DTO}.
     * @param dtoId    The unique id of the {@link DTO}. Must not be {@code null}.
     * @param dtoClass The Java class type of the {@link DTO}. Must not be {@code null}.
     * @return A {@link List} of {@link Enhancement} that can be applied to the specified {@link DTO}.
     *         If no enhancements are found, returns an empty list.
     * @throws IllegalArgumentException If {@code dtoId} or {@code dtoClass} is {@code null}.
     */
    <T extends DTO> List<Enhancement<T>> getEnhancements(String dtoId, Class<T> dtoClass);
}
