package com.github.codelomer.configprotection.checker;

import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigStringParams;
import com.github.codelomer.configprotection.validator.number.NumberLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.DoubleLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.IntegerLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.LongLimitValidator;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.number.NumberValidator;
import com.github.codelomer.configprotection.validator.object.ObjectValidator;
import com.github.codelomer.configprotection.validator.object.impl.StringObjectCastValidator;
import lombok.NonNull;

public class PrimitiveChecker {

    private final ConfigUtil configUtil;

    public PrimitiveChecker(@NonNull ConfigUtil configUtil){

        this.configUtil = configUtil;
    }

    public Integer checkInt(@NonNull ConfigNumberParams<Integer> configNumberParams){
        NumberLimitValidator<Integer> limitValidator = new IntegerLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        NumberValidator<Integer> numberValidator = new NumberValidator<>(configNumberParams,configUtil,limitValidator);
        return configUtil.validateObject(configNumberParams, numberValidator);
    }
    public Long checkLong(@NonNull ConfigNumberParams<Long> configNumberParams){
        NumberLimitValidator<Long> limitValidator = new LongLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        NumberValidator<Long> numberValidator = new NumberValidator<>(configNumberParams,configUtil,limitValidator);
        return configUtil.validateObject(configNumberParams, numberValidator);
    }
    public Double checkDouble(@NonNull ConfigNumberParams<Double> configNumberParams){
        NumberLimitValidator<Double> limitValidator = new DoubleLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        NumberValidator<Double> numberValidator = new NumberValidator<>(configNumberParams,configUtil,limitValidator);
        return configUtil.validateObject(configNumberParams, numberValidator);
    }

    public String checkString(@NonNull ConfigStringParams configStringParams){
        StringObjectCastValidator castValidator = new StringObjectCastValidator(configStringParams,configUtil);
        ObjectValidator<String,String> objectValidator = new ObjectValidator<>(castValidator,configUtil,configStringParams);
        return configUtil.validateObject(configStringParams,objectValidator);
    }
}
