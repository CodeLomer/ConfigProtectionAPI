package com.github.codelomer.configprotection.validator.list;

import lombok.NonNull;

import java.util.List;

public interface ListCastValidator<V,P> {
    List<P> getPrimitiveList();
    List<V> validateList(@NonNull List<P> primitiveList);
}
