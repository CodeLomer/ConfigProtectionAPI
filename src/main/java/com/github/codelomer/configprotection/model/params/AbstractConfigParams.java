package com.github.codelomer.configprotection.model.params;

import com.github.codelomer.configprotection.model.FilterCondition;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents the base parameters for validating and retrieving data from a configuration section.
 *
 * @param <C> the type of the implementing subclass for fluent method chaining
 * @param <V> the type of the data being validated and retrieved
 */
@Getter
public abstract class AbstractConfigParams<V,C> {
    protected final ConfigurationSection section;
    protected final String path;

    protected V def;

    protected final List<FilterCondition<V>> customConditions = new ArrayList<>();

    protected String notFoundPathError;
    protected String illegalArgumentInSectionError;
    protected boolean logErrors = true;

    /**
     * Constructs an AbstractConfigParams object.
     *
     * @param section the configuration section to interact with
     * @param path    the path within the configuration section
     */

    protected AbstractConfigParams(@NonNull ConfigurationSection section, @NonNull String path) {
        this.section = section;
        this.path = path;
    }

    /**
     * Sets the default value.
     * @param def the default value to set
     * @return subclass instance
     */
    public C setDef(@NonNull V def) {
        this.def = def;
        return getThis();
    }

    /**
     * Sets the custom error message for when the specified path is not found.
     * @param notFoundPathError the error message to set
     * @return subclass instance
     */


    public C setNotFoundPathError(@NonNull String notFoundPathError) {
        this.notFoundPathError = notFoundPathError;
        return getThis();
    }

    /**
     * Sets the custom error message for when an illegal argument is encountered within the section.
     * @param illegalArgumentInSectionError the error message to set
     * @return subclass instance
     */

    public C setIllegalArgumentInSectionError(@NonNull String illegalArgumentInSectionError) {
        this.illegalArgumentInSectionError = illegalArgumentInSectionError;
        return getThis();
    }

    /**
     * Adds a custom condition for validating the data.
     * @param predicate the predicate defining the custom condition
     * @return subclass instance
     */
    public C filter(@NonNull Predicate<V> predicate) {
        customConditions.add(new FilterCondition<>(predicate));
        return getThis();
    }

    /**
     * Adds a custom condition with a specified custom error message for validating the data.
     * @param predicate the predicate defining the custom condition
     * @param errorText the error message to set if the condition return false
     * @return subclass instance
     */

    public C filter(@NonNull Predicate<V> predicate, @NonNull String errorText) {
        customConditions.add(new FilterCondition<>(errorText, predicate));
        return getThis();
    }

    /**
     * Sets whether errors should be logged.
     * @param logErrors flag indicating whether errors should be logged
     * @return subclass instance
     */

    public C setLogErrors(boolean logErrors) {
        this.logErrors = logErrors;
        return getThis();
    }

    /**
     * Abstract method to get the subclass instance.
     * @return the subclass instance
     */
    protected abstract C getThis();
}