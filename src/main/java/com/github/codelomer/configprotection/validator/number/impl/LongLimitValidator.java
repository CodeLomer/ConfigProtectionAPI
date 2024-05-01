package com.github.codelomer.configprotection.validator.number.impl;

import com.github.codelomer.configprotection.validator.number.NumberLimitValidator;
import lombok.NonNull;

public class LongLimitValidator implements NumberLimitValidator<Long> {
    private final Long minLimit;
    private final Long maxLimit;

    public LongLimitValidator(Long minLimit, Long maxLimit){

        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }
    @Override
    public boolean isLess(@NonNull Long value) {
        return minLimit != null && value < minLimit;
    }

    @Override
    public boolean isMore(@NonNull Long value) {
        return maxLimit != null && value > maxLimit;
    }
}
