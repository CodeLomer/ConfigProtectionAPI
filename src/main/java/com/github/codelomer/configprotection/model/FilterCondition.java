package com.github.codelomer.configprotection.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.function.Predicate;

@Getter
public class FilterCondition<T> {
    private final Predicate<T> predicate;
    private final String errorText;

    public FilterCondition(@NonNull String errorText, @NonNull Predicate<T> predicate){

        this.predicate = predicate;
        this.errorText = errorText;
    }
    public FilterCondition(@NonNull Predicate<T> predicate){

        this.predicate = predicate;
        this.errorText = null;
    }
}
