package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class ConfigNumberParams<V extends Number> extends AbstractConfigParams<V,ConfigNumberParams<V>> {
    private V minLimit;
    private V maxLimit;

    private String minLimitError;
    private String maxLimitError;

    protected ConfigNumberParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    @Override
    protected ConfigNumberParams<V> getThis() {
        return this;
    }


    public static <V extends Number> ConfigNumberParams<V> builder(@NonNull ConfigurationSection section, @NonNull String path){
        return new ConfigNumberParams<>(section,path);
    }

    public ConfigNumberParams<V> setMinLimit(@NonNull V minLimit) {
        this.minLimit = minLimit;
        return this;

    }

    public ConfigNumberParams<V> setMaxLimit(@NonNull V maxLimit) {
        this.maxLimit = maxLimit;
        return this;
    }
    public ConfigNumberParams<V> setMaxLimit(@NonNull V maxLimit, @NonNull String errorText) {
        this.maxLimit = maxLimit;
        this.maxLimitError = errorText;
        return this;
    }

    public ConfigNumberParams<V> setMinLimit(@NonNull V minLimit, @NonNull String errorText) {
        this.minLimit = minLimit;
        this.minLimitError = errorText;
        return this;

    }
}
