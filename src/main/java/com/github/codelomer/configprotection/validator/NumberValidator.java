package com.github.codelomer.configprotection.validator;

import com.github.codelomer.configprotection.api.ConfigValidator;
import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.number.NumberLimitValidator;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

public class NumberValidator<V extends Number> implements ConfigValidator<V> {

    private final ConfigNumberParams<V> numberParams;
    private final ConfigUtil configUtil;
    private final NumberLimitValidator<V> limitValidator;
    public NumberValidator(@NonNull ConfigNumberParams<V> numberParams, @NonNull ConfigUtil configUtil,
                           @NonNull NumberLimitValidator<V> limitValidator){

        this.numberParams = numberParams;

        this.configUtil = configUtil;
        this.limitValidator = limitValidator;
    }
    @Override
    public V validate() {
        ConfigurationSection section = numberParams.getSection();
        String path = numberParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        if(!isNumber(section,path)) return configUtil.logIllegalArgumentErrorAndReturn(numberParams,fullPath);

        V def = numberParams.getDef();

        V value = limitValidator.getValue(section,path);
        if(limitValidator.isMore(value)){
            configUtil.logError(ConfigUtil.MAX_LIMIT_ERROR, numberParams.getMaxLimitError(),fullPath,numberParams.isLogErrors(), numberParams.getMaxLimit());
            return def;
        }
        if(limitValidator.isLess(value)){
            configUtil.logError(ConfigUtil.MIN_LIMIT_ERROR, numberParams.getMinLimitError(),fullPath, numberParams.isLogErrors(), numberParams.getMinLimit());
            return def;
        }
        return value;
    }

    private boolean isNumber(@NonNull ConfigurationSection section, @NonNull String path){
        return section.isInt(path) || section.isDouble(path) || section.isLong(path);
    }
}
