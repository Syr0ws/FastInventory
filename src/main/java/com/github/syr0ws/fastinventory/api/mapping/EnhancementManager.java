package com.github.syr0ws.fastinventory.api.mapping;

import java.util.List;

public interface EnhancementManager {

    <T extends Dto> void addEnhancement(String id, Enhancement<T> enhancement, Class<T> type);

    <T extends Dto> List<Enhancement<T>> getEnhancements(String id, Class<T> type);
}
