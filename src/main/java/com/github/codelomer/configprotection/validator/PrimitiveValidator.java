package com.github.codelomer.configprotection.validator;

import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigStringParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.number.NumberLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.DoubleLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.IntegerLimitValidator;
import com.github.codelomer.configprotection.validator.number.impl.LongLimitValidator;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.function.Function;

public class PrimitiveValidator {
    private final ConfigUtil configUtil;

    public PrimitiveValidator(@NonNull ConfigUtil configUtil){

        this.configUtil = configUtil;
    }

    public Integer checkInt(@NonNull ConfigNumberParams<Integer> configNumberParams){
        IntegerLimitValidator limitValidator = new IntegerLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        ConfigurationSection section = configNumberParams.getSection();
        return checkNumber(configNumberParams,section::getInt,limitValidator);
    }
    public Long checkLong(@NonNull ConfigNumberParams<Long> configNumberParams){
        LongLimitValidator limitValidator = new LongLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        ConfigurationSection section = configNumberParams.getSection();
        return checkNumber(configNumberParams,section::getLong,limitValidator);
    }
    public Double checkDouble(@NonNull ConfigNumberParams<Double> configNumberParams){
        DoubleLimitValidator limitValidator = new DoubleLimitValidator(configNumberParams.getMinLimit(), configNumberParams.getMaxLimit());
        ConfigurationSection section = configNumberParams.getSection();
        return checkNumber(configNumberParams,section::getDouble,limitValidator);
    }

    public String checkString(@NonNull ConfigStringParams configStringParams){
        String def = configStringParams.getDef();

        if(configUtil.notFoundPath(configStringParams)) return def;

        ConfigurationSection section = configStringParams.getSection();
        String path = configStringParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        String value = section.getString(path);

        if(value == null) return configUtil.logIllegalArgumentErrorAndReturn(configStringParams,fullPath);

        if(!configStringParams.isCanBeEmpty() && value.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR, configStringParams.getEmptyErrorText(),fullPath,configStringParams.isLogErrors());
            return def;
        }

        if(configUtil.filtered(configStringParams.getCustomConditions(),value,fullPath,configStringParams.isLogErrors())) return def;

        return value;
    }

    private  <V extends Number> V checkNumber(@NonNull ConfigNumberParams<V> configNumberParams, @NonNull Function<String,V> getValueFunction,
                                              @NonNull NumberLimitValidator<V> limitValidator){
        V def = configNumberParams.getDef();

        if(configUtil.notFoundPath(configNumberParams)) return def;

        String path = configNumberParams.getPath();
        ConfigurationSection section = configNumberParams.getSection();
        String fullPath = configUtil.getFullPath(section,path);

        if(!isNumber(section,path)) return configUtil.logIllegalArgumentErrorAndReturn(configNumberParams,fullPath);

        V value = getValueFunction.apply(path);
        if(limitValidator.isMore(value)){
            configUtil.logError(ConfigUtil.MAX_LIMIT_ERROR, configNumberParams.getMaxLimitError(),fullPath,configNumberParams.isLogErrors(), configNumberParams.getMaxLimit());
            return def;
        }
        if(limitValidator.isLess(value)){
            configUtil.logError(ConfigUtil.MIN_LIMIT_ERROR, configNumberParams.getMinLimitError(),fullPath, configNumberParams.isLogErrors(), configNumberParams.getMinLimit());
            return def;
        }

        if(configUtil.filtered(configNumberParams.getCustomConditions(),value,fullPath,configNumberParams.isLogErrors())) return def;
        return value;
    }

    private boolean isNumber(@NonNull ConfigurationSection section, @NonNull String path){
        return section.isInt(path) || section.isDouble(path) || section.isLong(path);
    }
}
