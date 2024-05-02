package com.github.codelomer.configprotection.validator.list.impl;

import com.github.codelomer.configprotection.validator.list.ListCastValidator;
import lombok.NonNull;

import java.util.List;

public class BukkitListCastValidator<V> implements ListCastValidator<V,String> {
    @Override
    public List<String> getPrimitiveList() {
        return null;
    }

    @Override
    public List<V> validateList(@NonNull List<String> primitiveList) {
        return null;
    }
}
