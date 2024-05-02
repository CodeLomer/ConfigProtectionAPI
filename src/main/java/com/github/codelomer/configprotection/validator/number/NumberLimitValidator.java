package com.github.codelomer.configprotection.validator.number;

import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

public interface NumberLimitValidator<V> {
    boolean isLess(@NonNull V value);
    boolean isMore(@NonNull V value);
    V getValue(@NonNull ConfigurationSection section, @NonNull String path);
}
