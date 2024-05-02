package com.github.codelomer.configprotection.validator.object.impl;

import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import lombok.NonNull;

import java.util.function.Function;

public class BukkitCastValidator<V> implements ObjectCastValidator<V> {

    private final Function<String, V> castObjectFunction;
    private final ConfigParams<V> configParams;

    public BukkitCastValidator(@NonNull Function<String,V> castObjectFunction, @NonNull ConfigParams<V> configParams){

        this.castObjectFunction = castObjectFunction;
        this.configParams = configParams;
    }

    @Override
    public V cast() {
        return castObjectFunction.apply(configParams.getSection().getString(configParams.getPath(),"null"));
    }

    @Override
    public V validate(@NonNull V value) {
        return value;
    }
}
