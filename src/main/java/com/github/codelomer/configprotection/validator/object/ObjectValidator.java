package com.github.codelomer.configprotection.validator.object;

import com.github.codelomer.configprotection.api.ConfigValidator;
import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import com.github.codelomer.configprotection.util.ConfigUtil;

import lombok.NonNull;

public class ObjectValidator<V,P> implements ConfigValidator<V> {
    private final ObjectCastValidator<V,P> castValidator;
    private final ConfigUtil configUtil;
    private final AbstractConfigParams<V,?> configParams;

    public ObjectValidator(@NonNull ObjectCastValidator<V,P> castValidator, @NonNull ConfigUtil configUtil, @NonNull AbstractConfigParams<V,?> configParams){

        this.castValidator = castValidator;
        this.configUtil = configUtil;
        this.configParams = configParams;
    }
    @Override
    public V validate() {
        P primitive = castValidator.getValue();
        V value = castValidator.cast(primitive);
        if(value == null) return configUtil.logIllegalArgumentErrorAndReturn(configParams,configUtil.getFullPath(configParams.getSection(),configParams.getPath()));
        return castValidator.validate(value);
    }
}
