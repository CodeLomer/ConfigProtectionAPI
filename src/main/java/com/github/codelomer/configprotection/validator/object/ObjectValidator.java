package com.github.codelomer.configprotection.validator.object;

import com.github.codelomer.configprotection.api.ConfigValidator;
import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import lombok.NonNull;

public class ObjectValidator<V> implements ConfigValidator<V> {
    private final ObjectCastValidator<V> castValidator;
    private final ConfigUtil configUtil;
    private final AbstractConfigParams<V,?> configParams;

    public ObjectValidator(@NonNull ObjectCastValidator<V> castValidator, @NonNull ConfigUtil configUtil, @NonNull AbstractConfigParams<V,?> configParams){

        this.castValidator = castValidator;
        this.configUtil = configUtil;
        this.configParams = configParams;
    }
    @Override
    public V validate() {
        V value = castValidator.cast();
        if(value == null) return configUtil.logIllegalArgumentErrorAndReturn(configParams,configUtil.getFullPath(configParams.getSection(),configParams.getPath()));
        return castValidator.validate(value);
    }
}
