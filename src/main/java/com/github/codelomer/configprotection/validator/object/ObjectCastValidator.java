package com.github.codelomer.configprotection.validator.object;

import lombok.NonNull;

public interface ObjectCastValidator<V,P> {
    P getValue();
    V cast(P value);
    V validate(@NonNull V value);
}
