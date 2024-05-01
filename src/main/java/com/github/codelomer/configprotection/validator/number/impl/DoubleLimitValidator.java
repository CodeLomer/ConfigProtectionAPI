package com.github.codelomer.configprotection.validator.number.impl;

import com.github.codelomer.configprotection.validator.number.NumberLimitValidator;
import lombok.NonNull;

public class DoubleLimitValidator implements NumberLimitValidator<Double> {
    private final Double minLimit;
    private final Double maxLimit;

    public DoubleLimitValidator(Double minLimit, Double maxLimit){

        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }
    @Override
    public boolean isLess(@NonNull Double value) {
        return minLimit != null && value < minLimit;
    }

    @Override
    public boolean isMore(@NonNull Double value) {
        return maxLimit != null && value > maxLimit;
    }
}
