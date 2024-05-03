package com.github.codelomer.configprotection.validator.object.impl;

import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.function.Function;

public class BukkitObjectCastValidator<V> implements ObjectCastValidator<V,String> {

    private final Function<String, V> castObjectFunction;
    private final ConfigurationSection section;
    private final String path;
    public BukkitObjectCastValidator(@NonNull Function<String,V> castObjectFunction, @NonNull ConfigurationSection section, @NonNull String path){

        this.castObjectFunction = castObjectFunction;
        this.section = section;
        this.path = path;
    }

    @Override
    public String getValue() {
        return section.getString(path,"null");
    }

    @Override
    public V cast(String value) {
        if(value == null) return null;
        return castObjectFunction.apply(value);
    }

    @Override
    public V validate(@NonNull V value) {
        return value;
    }
}
