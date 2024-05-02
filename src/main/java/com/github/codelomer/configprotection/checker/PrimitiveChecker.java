package com.github.codelomer.configprotection.checker;

import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigStringParams;
import com.github.codelomer.configprotection.validator.number.NumberLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.DoubleLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.IntegerLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.LongLimitValidator;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.number.NumberValidator;
import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import com.github.codelomer.configprotection.validator.object.ObjectValidator;
import com.github.codelomer.configprotection.validator.object.impl.StringCastValidator;
import lombok.NonNull;

public class PrimitiveChecker {

    private final ConfigUtil configUtil;

    public PrimitiveChecker(@NonNull ConfigUtil configUtil){

        this.configUtil = configUtil;
    }

    public Integer checkInt(@NonNull ConfigNumberParams<Integer> configNumberParams){
        NumberLimitValidator<Integer> limitValidator = new IntegerLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        return configUtil.validateObject(configNumberParams, new NumberValidator<>(configNumberParams, configUtil,limitValidator));
    }
    public Long checkLong(@NonNull ConfigNumberParams<Long> configNumberParams){
        NumberLimitValidator<Long> limitValidator = new LongLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        return configUtil.validateObject(configNumberParams, new NumberValidator<>(configNumberParams, configUtil,limitValidator));
    }
    public Double checkDouble(@NonNull ConfigNumberParams<Double> configNumberParams){
        NumberLimitValidator<Double> limitValidator = new DoubleLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        return configUtil.validateObject(configNumberParams, new NumberValidator<>(configNumberParams, configUtil,limitValidator));
    }

    public String checkString(@NonNull ConfigStringParams configStringParams){
        ObjectCastValidator<String> castValidator = new StringCastValidator(configStringParams,configUtil);
        return configUtil.validateObject(configStringParams,new ObjectValidator<>(castValidator,configUtil,configStringParams));
    }
}
