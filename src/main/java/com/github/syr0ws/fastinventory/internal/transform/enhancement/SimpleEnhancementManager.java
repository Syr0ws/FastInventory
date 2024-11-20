package com.github.syr0ws.fastinventory.internal.transform.enhancement;

import com.github.syr0ws.fastinventory.api.transform.dto.DTO;
import com.github.syr0ws.fastinventory.api.transform.enhancement.Enhancement;
import com.github.syr0ws.fastinventory.api.transform.enhancement.EnhancementManager;
import com.github.syr0ws.fastinventory.api.util.Context;
import com.github.syr0ws.fastinventory.internal.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEnhancementManager implements EnhancementManager {

    // Map<EnhancementId, Pair<DTOId, Enhancement>>
    private final Map<String, Pair<String, Enhancement<?>>> enhancements = new HashMap<>();

    @Override
    public <T extends DTO> void enhance(T dto, Class<T> dtoClass, Context context) {

        if(dto == null) {
            throw new IllegalArgumentException("dto cannot be null");
        }

        if(dtoClass == null) {
            throw new IllegalArgumentException("dtoClass cannot be null");
        }

        if(context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        List<Enhancement<T>> enhancements = this.getEnhancements(dto.getId(), dtoClass);
        enhancements.forEach(enhancement -> enhancement.enhance(dto, context));
    }

    @Override
    public void addEnhancement(String dtoId, Enhancement<?> enhancement) {

        if (dtoId == null || dtoId.isEmpty()) {
            throw new IllegalArgumentException("dtoId cannot be null or empty");
        }

        if (enhancement == null) {
            throw new IllegalArgumentException("enhancement cannot be null");
        }

        this.enhancements.put(enhancement.getId(), new Pair<>(dtoId, enhancement));
    }

    @Override
    public boolean removeEnhancement(String enhancementId) {

        if (enhancementId == null) {
            throw new IllegalArgumentException("enhancementId cannot be null");
        }

        return this.enhancements.remove(enhancementId) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DTO> List<Enhancement<T>> getEnhancements(String dtoId, Class<T> dtoClass) {

        if (dtoId == null) {
            throw new IllegalArgumentException("dtoId cannot be null");
        }

        if (dtoClass == null) {
            throw new IllegalArgumentException("dtoClass cannot be null");
        }

        return this.enhancements.values().stream()
                .filter(pair -> pair.key().equals(dtoId))
                .map(Pair::value)
                .filter(enhancement -> enhancement.getDTOClass().equals(dtoClass))
                .map(enhancement -> (Enhancement<T>) enhancement)
                .toList();
    }
}
