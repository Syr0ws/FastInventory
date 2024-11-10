package com.github.syr0ws.fastinventory.api.transform.enhancement;

import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.api.util.Context;

import java.util.List;

public interface EnhancementManager {

    /**
     * Enhance a DTO by applying modifications on its properties.
     *
     * @param dto      The DTO to enhance.
     * @param dtoClass The Java class type of the DTO the provider handles.
     * @param context  A Context instance that contains additional data.
     */
    <T extends DTO> void enhance(T dto, Class<T> dtoClass, Context context);

    /**
     * Add a new enhancement.
     *
     * @param dtoId       The id of the DTO the enhancement will be applied on.
     * @param enhancement The enhancement to register.
     */
    void addEnhancement(String dtoId, Enhancement<?> enhancement);

    /**
     * Remove an existing enhancement.
     *
     * @param enhancementId The enhancement to unregister.
     * @return true if an enhancement that matches the given id has been removed or else false.
     */
    boolean removeEnhancement(String enhancementId);

    /**
     * Get the enhancements that apply on a specific DTO.
     *
     * @param dtoId    The id of the DTO.
     * @param dtoClass The Java class type of the DTO.
     * @return A list of enhancements.
     */
    <T extends DTO> List<Enhancement<T>> getEnhancements(String dtoId, Class<T> dtoClass);
}
