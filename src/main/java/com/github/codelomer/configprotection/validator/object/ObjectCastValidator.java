package com.github.codelomer.configprotection.validator.object;

import lombok.NonNull;

public interface ObjectCastValidator<V> {

    V cast();
    V validate(@NonNull V value);
}
