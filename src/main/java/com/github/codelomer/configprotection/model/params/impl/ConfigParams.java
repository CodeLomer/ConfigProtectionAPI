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


    protected ConfigParams(@NonNull ConfigurationSection section, @NonNull String path) {
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

    /**
     * Static method to create a new instance of ConfigParams.
     *
     * @param section the configuration section to interact with
     * @param path    the path within the configuration section
     * @param <V>     the type of data being validated and retrieved
     * @return a new instance of ConfigParams
     */
    public static <V> ConfigParams<V> builder(@NonNull ConfigurationSection section, @NonNull String path) {
        return new ConfigParams<>(section, path);
    }
}
