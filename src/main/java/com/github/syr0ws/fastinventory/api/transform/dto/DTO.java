package com.github.syr0ws.fastinventory.api.transform.dto;

/**
 * Represents a Data Transfer Object (DTO) that defines the structure for objects
 * that are used to transfer data between the config and the inventory layer.
 */
public interface DTO {

    /**
     * Retrieves the unique identifier of this DTO.
     *
     * @return A {@link String} representing the unique id of the DTO. Must not be {@code null}.
     */
    String getId();
}
