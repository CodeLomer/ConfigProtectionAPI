package com.github.codelomer.configprotection.validator.number;

import lombok.NonNull;

public interface NumberLimitValidator<V> {
    boolean isLess(@NonNull V value);
    boolean isMore(@NonNull V value);
}
