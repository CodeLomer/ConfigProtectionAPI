package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
@Getter
public class ConfigListParams<V> extends AbstractConfigParams<List<V>,ConfigListParams<V>> {

    private boolean canBeEmpty;
    private String emptyErrorText;
    protected ConfigListParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    @Override
    protected ConfigListParams<V> getThis() {
        return this;
    }

    public static <V> ConfigListParams<V> builder(@NonNull ConfigurationSection section, @NonNull String path){
        return new ConfigListParams<>(section,path);
    }

    public ConfigListParams<V> canBeEmpty(boolean canBeEmpty){
        this.canBeEmpty = canBeEmpty;
        return this;
    }

    public ConfigListParams<V> canBeEmpty(boolean canBeEmpty, @NonNull String errorText){
        this.canBeEmpty = canBeEmpty;
        this.emptyErrorText = errorText;
        return this;
    }
}
