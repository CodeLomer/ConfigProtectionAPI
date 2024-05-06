package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * Represents the parameters for validating and retrieving a list of values from a configuration section.
 *
 * @param <V> the type of values in the list
 */

@Getter
public class ConfigListParams<V> extends AbstractConfigParams<List<V>,ConfigListParams<V>> {

    private boolean canBeEmpty;
    private String emptyErrorText;
    public ConfigListParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    /**
     * Overrides the abstract method to return the current instance.
     *
     * @return the current instance
     */
    @Override
    protected ConfigListParams<V> getThis() {
        return this;
    }

    /**
     * Sets whether the list can be empty.
     *
     * @param canBeEmpty flag indicating whether the list can be empty
     * @return the current instance for method chaining
     */

    public ConfigListParams<V> canBeEmpty(boolean canBeEmpty) {
        this.canBeEmpty = canBeEmpty;
        return this;
    }

    /**
     * Sets whether the list can be empty along with a custom error message if it is empty.
     *
     * @param canBeEmpty   flag indicating whether the list can be empty
     * @param emptyErrorText the error message to display if the list is empty
     * @return the current instance for method chaining
     */

    public ConfigListParams<V> canBeEmpty(boolean canBeEmpty, @NonNull String emptyErrorText) {
        this.canBeEmpty = canBeEmpty;
        this.emptyErrorText = emptyErrorText;
        return this;
    }
}
