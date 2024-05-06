package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Represents the parameters for validating and retrieving data of generic type from a configuration section.
 *
 * @param <V> the type of data being validated and retrieved
 */

public class ConfigParams<V> extends AbstractConfigParams<V,ConfigParams<V>> {


    public ConfigParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    /**
     * Overrides the abstract method to return the current instance.
     *
     * @return the current instance
     */

    @Override
    protected ConfigParams<V> getThis() {
        return this;
    }
}
