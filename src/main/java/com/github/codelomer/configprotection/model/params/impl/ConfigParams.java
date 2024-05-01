package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

public class ConfigParams<V> extends AbstractConfigParams<V,ConfigParams<V>> {
    protected ConfigParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    @Override
    protected ConfigParams<V> getThis() {
        return this;
    }
}
