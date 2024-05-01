package com.github.codelomer.configprotection.model.params.impl;

import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class ConfigStringParams extends AbstractConfigParams<String,ConfigStringParams> {

    private boolean canBeEmpty;
    private String emptyErrorText;

    protected ConfigStringParams(@NonNull ConfigurationSection section, @NonNull String path) {
        super(section, path);
    }

    @Override
    protected ConfigStringParams getThis() {
        return this;
    }

    public ConfigStringParams canBeEmpty(boolean canBeEmpty){
        this.canBeEmpty = canBeEmpty;
        return this;
    }

    public ConfigStringParams canBeEmpty(boolean canBeEmpty, @NonNull String errorText){
        this.canBeEmpty = canBeEmpty;
        this.emptyErrorText = errorText;
        return this;
    }

    public static ConfigStringParams builder(@NonNull ConfigurationSection section, @NonNull String path){
        return new ConfigStringParams(section,path);
    }
}
