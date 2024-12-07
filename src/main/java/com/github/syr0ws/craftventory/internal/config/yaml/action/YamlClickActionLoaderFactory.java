package com.github.syr0ws.craftventory.internal.config.yaml.action;

import com.github.syr0ws.craftventory.api.config.action.ClickActionLoader;
import com.github.syr0ws.craftventory.api.config.action.ClickActionLoaderFactory;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class YamlClickActionLoaderFactory implements ClickActionLoaderFactory<ConfigurationSection> {

    private final Map<String, ClickActionLoader<ConfigurationSection>> loaders = new HashMap<>();

    @Override
    public ClickActionLoader<ConfigurationSection> getLoader(String name) {

        if (name == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        return this.loaders.get(name);
    }

    @Override
    public void addLoader(ClickActionLoader<ConfigurationSection> loader) {

        if (loader == null) {
            throw new IllegalArgumentException("loader cannot be null");
        }

        this.loaders.put(loader.getName(), loader);
    }

    @Override
    public Set<ClickActionLoader<ConfigurationSection>> getLoaders() {
        return new HashSet<>(this.loaders.values());
    }
}
