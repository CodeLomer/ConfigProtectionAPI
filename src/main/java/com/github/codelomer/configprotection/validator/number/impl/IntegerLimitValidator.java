package com.github.codelomer.configprotection.validator.number.impl;

import com.github.codelomer.configprotection.validator.number.NumberLimitValidator;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

public class IntegerLimitValidator implements NumberLimitValidator<Integer> {

    private final Integer minLimit;
    private final Integer maxLimit;

    public IntegerLimitValidator(Integer minLimit, Integer maxLimit){

        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }
    @Override
    public boolean isLess(@NonNull Integer value) {
        return minLimit != null && value < minLimit;
    }

    @Override
    public boolean isMore(@NonNull Integer value) {
        return maxLimit != null && value > maxLimit;
    }

    @Override
    public Integer getValue(@NonNull ConfigurationSection section, @NonNull String path) {
        return section.getInt(path);
    }
}
