package com.github.codelomer.configprotection.util;

import com.github.codelomer.configprotection.api.ConfigValidator;
import com.github.codelomer.configprotection.logger.ConfigLogger;
import com.github.codelomer.configprotection.model.FilterCondition;
import com.github.codelomer.configprotection.model.params.AbstractConfigParams;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;

public class ConfigUtil {
    public static final String ILLEGAL_ARGUMENT_IN_SECTION = "invalid value in section";
    public static final String MIN_LIMIT_ERROR = "value cannot be less than %s";
    public static final String MAX_LIMIT_ERROR = "value cannot be greater than %s";
    public static final String SECTION_EMPTY_ERROR = "section not can be empty";

    private final ConfigLogger logger;

    public ConfigUtil(@NonNull ConfigLogger configLogger){
        this.logger = configLogger;
    }
    public String getFullPath(@NonNull ConfigurationSection section, @NonNull String path){
        return Objects.requireNonNull(section.getCurrentPath()).replace(".","\\")+"\\"+path.replace(".","\\");
    }

    public void logError(@NonNull String defaultErrorMessage, String customErrorMessage, @NonNull String fullPath, boolean logError , Object... args){

        if(customErrorMessage != null && logError){
            logger.log(customErrorMessage, fullPath);
            return;
        }
        if(logError) logger.log(String.format(defaultErrorMessage,args),fullPath);
    }

    public <V> boolean filtered(List<FilterCondition<V>> filterConditions, @NonNull V value, @NonNull String fullPath, @NonNull boolean logErrors){
        if(filterConditions == null) return false;
        boolean hasError = false;
        for(FilterCondition<V> filterCondition: filterConditions){
            if(!filterCondition.getPredicate().test(value)){
                hasError = true;
                String errorText = filterCondition.getErrorText();
                if(errorText != null && logErrors) logger.log(errorText,fullPath);
            }
        }
        return hasError;
    }

    public <V,C> V logIllegalArgumentErrorAndReturn(@NonNull AbstractConfigParams<V,C> abstractConfigParams, @NonNull String fullPath){
        logError(ConfigUtil.ILLEGAL_ARGUMENT_IN_SECTION, abstractConfigParams.getIllegalArgumentInSectionError(),fullPath,abstractConfigParams.isLogErrors());
        return abstractConfigParams.getDef();
    }

    public <V,C> V validateObject(@NonNull AbstractConfigParams<V,C> configParams, @NonNull ConfigValidator<V> configValidator){
        V value = configValidator.validate();
        if(value == null) return null;
        String fullPath = getFullPath(configParams.getSection(), configParams.getPath());
        if(filtered(configParams.getCustomConditions(),value,fullPath,configParams.isLogErrors())) return configParams.getDef();
        return value;

    }

}
