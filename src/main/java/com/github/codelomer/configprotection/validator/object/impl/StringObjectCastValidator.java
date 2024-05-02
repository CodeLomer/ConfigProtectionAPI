package com.github.codelomer.configprotection.validator.object.impl;

import com.github.codelomer.configprotection.model.params.impl.ConfigStringParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import lombok.NonNull;

public class StringObjectCastValidator implements ObjectCastValidator<String,String> {

    private final ConfigStringParams stringParams;
    private final ConfigUtil configUtil;

    public StringObjectCastValidator(@NonNull ConfigStringParams stringParams, @NonNull ConfigUtil configUtil){

        this.stringParams = stringParams;
        this.configUtil = configUtil;
    }

    @Override
    public String getValue() {
        return stringParams.getSection().getString(stringParams.getPath());
    }

    @Override
    public String cast(String value) {
        return value;

    }

    @Override
    public String validate(@NonNull String value) {
        if(!stringParams.isCanBeEmpty() && value.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR, stringParams.getEmptyErrorText(),configUtil.getFullPath(stringParams.getSection(),stringParams.getPath()),stringParams.isLogErrors());
            return stringParams.getDef();
        }
        return value;
    }
}
