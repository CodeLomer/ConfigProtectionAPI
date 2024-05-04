package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Represents the parameters for validating and retrieving a number value from a configuration section.
 *
 * @param <V> the type of the number value
 */
@Getter
public class ConfigNumberParams<V extends Number> extends AbstractConfigParams<V,ConfigNumberParams<V>> {
    private V minLimit;
    private V maxLimit;

    private String minLimitError;
    private String maxLimitError;


    protected ConfigNumberParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    /**
     * Overrides the abstract method to return the current instance.
     *
     * @return the current instance
     */
    @Override
    protected ConfigNumberParams<V> getThis() {
        return this;
    }

    /**
     * Static method to create a new instance of ConfigNumberParams.
     *
     * @param section the configuration section to interact with
     * @param path    the path within the configuration section
     * @param <V>     the type of the number value
     * @return a new instance of ConfigNumberParams
     */
    public static <V extends Number> ConfigNumberParams<V> builder(@NonNull ConfigurationSection section, @NonNull String path) {
        return new ConfigNumberParams<>(section, path);
    }

    /**
     * Sets the minimum limit for the number value.
     *
     * @param minLimit the minimum limit to set
     * @return the current instance for method chaining
     */
    public ConfigNumberParams<V> setMinLimit(@NonNull V minLimit) {
        this.minLimit = minLimit;
        return this;
    }

    /**
     * Sets the maximum limit for the number value.
     *
     * @param maxLimit the maximum limit to set
     * @return the current instance for method chaining
     */
    public ConfigNumberParams<V> setMaxLimit(@NonNull V maxLimit) {
        this.maxLimit = maxLimit;
        return this;
    }

    /**
     * Sets the maximum limit for the number value along with a custom error message if it exceeds the limit.
     *
     * @param maxLimit    the maximum limit to set
     * @param errorText the custom error message to display if the number value exceeds the limit
     * @return the current instance for method chaining
     */
    public ConfigNumberParams<V> setMaxLimit(@NonNull V maxLimit, @NonNull String errorText) {
        this.maxLimit = maxLimit;
        this.maxLimitError = errorText;
        return this;
    }

    /**
     * Sets the minimum limit for the number value along with a custom error message if it is less than the limit.
     *
     * @param minLimit    the minimum limit to set
     * @param errorText the custom error message to display if the number value is less than the limit
     * @return the current instance for method chaining
     */
    public ConfigNumberParams<V> setMinLimit(@NonNull V minLimit, @NonNull String errorText) {
        this.minLimit = minLimit;
        this.minLimitError = errorText;
        return this;
    }
}
