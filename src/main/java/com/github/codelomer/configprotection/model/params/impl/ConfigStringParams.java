package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Represents the parameters for validating and retrieving a string value from a configuration section.
 */
@Getter
public class ConfigStringParams extends AbstractConfigParams<String,ConfigStringParams> {

    private boolean canBeEmpty;
    private String emptyErrorText;


    public ConfigStringParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    /**
     * Overrides the abstract method to return the current instance.
     * @return the current instance
     */

    @Override
    protected ConfigStringParams getThis() {
        return this;
    }

    /**
     * Sets whether the string value can be empty.
     * @param canBeEmpty flag indicating whether the string value can be empty
     * @return current instance
     */
    public ConfigStringParams canBeEmpty(boolean canBeEmpty) {
        this.canBeEmpty = canBeEmpty;
        return this;
    }

    /**
     * Sets whether the string value can be empty along with a custom error message if it is empty.
     * @param canBeEmpty  flag indicating whether the string value can be empty
     * @param emptyErrorText the error message to display if the string value is empty
     * @return current instance
     */
    public ConfigStringParams canBeEmpty(boolean canBeEmpty, @NonNull String emptyErrorText) {
        this.canBeEmpty = canBeEmpty;
        this.emptyErrorText = emptyErrorText;
        return this;
    }
}
