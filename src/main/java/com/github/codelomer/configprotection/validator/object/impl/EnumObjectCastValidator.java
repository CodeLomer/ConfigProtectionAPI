package com.github.codelomer.configprotection.validator.object.impl;

import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

public class EnumObjectCastValidator<E extends Enum<E>> implements ObjectCastValidator<E,String> {

    private final ConfigurationSection section;
    private final String path;
    private final Class<E> enumClass;

    public EnumObjectCastValidator(@NonNull ConfigurationSection section, @NonNull String path, @NonNull Class<E> enumClass){

        this.section = section;
        this.path = path;
        this.enumClass = enumClass;
    }

    @Override
    public String getValue() {
        return section.getString(path,"null");
    }

    @Override
    public E cast(String value) {
        try {
            return Enum.valueOf(enumClass,value);
        }catch (IllegalArgumentException e){
            return null;
        }
    }


    @Override
    public E validate(@NonNull E value) {
        return value;
    }
}
