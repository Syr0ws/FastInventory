package com.github.syr0ws.fastinventory.api.transform.enhancement;

import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.api.util.Context;

public interface Enhancement<T extends DTO> {

    /**
     * Enhance a DTO by modifying its properties.
     *
     * @param dto     The DTO instance to enhance.
     * @param context Context instance that contains additional data.
     */
    void enhance(T dto, Context context);

    /**
     * Get the Java type of the dto the enhancement will be applied on.
     *
     * @return The Java class type of the DTO.
     */
    Class<T> getDTOClass();

    /**
     * Get the id that uniquely identify the enhancement.
     *
     * @return The id of the enhancement.
     */
    String getId();
}
