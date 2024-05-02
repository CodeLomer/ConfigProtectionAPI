package com.github.codelomer.configprotection.validator.object.impl;

import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

public class EnumCastValidator<E extends Enum<E>> implements ObjectCastValidator<E> {

    private final ConfigurationSection section;
    private final String path;
    private final Class<E> enumClass;

    public EnumCastValidator(@NonNull ConfigurationSection section, @NonNull String path, @NonNull Class<E> enumClass){

        this.section = section;
        this.path = path;
        this.enumClass = enumClass;
    }
    @Override
    public E cast() {
        String stringValue = section.getString(path);
        if(stringValue == null) return null;
        try {
            return Enum.valueOf(enumClass,stringValue);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public E validate(@NonNull E value) {
        return value;
    }
}
