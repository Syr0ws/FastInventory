package com.github.syr0ws.fastinventory.api.transform.provider;

import com.github.syr0ws.fastinventory.api.transform.InventoryProvider;
import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.api.util.Context;

public interface Provider<T extends DTO> {

    /**
     * Provide an instance of a specific DTO.
     * @param provider The inventory provider that makes the call.
     * @param context A Context instance that contains additional data.
     * @return An instance of the DTO.
     */
    T provide(InventoryProvider provider, Context context);

    /**
     * Get the name of the provider.
     * @return The provider name.
     */
    String getName();

    /**
     * Get the class of the DTO the provider provides.
     * @return The Java class type of the DTO.
     */
    Class<T> getDTOClass();
}
