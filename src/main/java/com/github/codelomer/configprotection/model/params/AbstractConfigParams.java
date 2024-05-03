package com.github.codelomer.configprotection.model.params;

import com.github.codelomer.configprotection.model.FilterCondition;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
public abstract class AbstractConfigParams<V,C> {
    protected final ConfigurationSection section;
    protected final String path;

    protected V def;

    protected final List<FilterCondition<V>> customConditions = new ArrayList<>();

    protected String notFoundPathError;
    protected String illegalArgumentInSectionError;
    protected boolean logErrors = true;

    protected AbstractConfigParams(@NonNull ConfigurationSection section, @NonNull String path) {
        this.section = section;
        this.path = path;
    }

    public C setDef(@NonNull V def) {
        this.def = def;
        return getThis();
    }

    public C setNotFoundPathError(@NonNull String notFoundPathError) {
        this.notFoundPathError = notFoundPathError;
        return getThis();
    }

    public C setIllegalArgumentInSectionError(@NonNull String illegalArgumentInSectionError) {
        this.illegalArgumentInSectionError = illegalArgumentInSectionError;
        return getThis();
    }

    public C filter(@NonNull Predicate<V> predicate) {
        customConditions.add(new FilterCondition<>(predicate));
        return getThis();
    }

    public C filter(@NonNull Predicate<V> predicate, @NonNull String errorText) {
        customConditions.add(new FilterCondition<>(errorText, predicate));
        return getThis();
    }

    public C setLogErrors(boolean logErrors) {
        this.logErrors = logErrors;
        return getThis();
    }

    protected abstract C getThis();
}