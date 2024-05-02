package com.github.codelomer.configprotection.validator.list.impl;

import com.github.codelomer.configprotection.validator.list.ListCastValidator;
import lombok.NonNull;

import java.util.List;
import java.util.function.Function;

public class PrimitiveListCastValidator<V> implements ListCastValidator<V,V> {
    private final String path;
    private final Function<String, List<V>> getListFunction;

    public PrimitiveListCastValidator(@NonNull String path,@NonNull Function<String,List<V>> getListFunction){
        this.path = path;
        this.getListFunction = getListFunction;
    }
    @Override
    public List<V> getPrimitiveList() {
        return getListFunction.apply(path);
    }

    @Override
    public List<V> validateList(@NonNull List<V> primitiveList) {
        return getPrimitiveList();
    }
}
