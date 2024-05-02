package com.github.codelomer.configprotection.validator.list.impl;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.list.ListCastValidator;
import com.github.codelomer.configprotection.validator.object.impl.BukkitObjectCastValidator;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BukkitListCastValidator<V> implements ListCastValidator<V,String> {

    private final ConfigListParams<V> listParams;
    private final ConfigUtil configUtil;
    private final BukkitObjectCastValidator<V> bukkitCastValidator;

    public BukkitListCastValidator(@NonNull ConfigListParams<V> listParams, @NonNull ConfigUtil configUtil, @NonNull BukkitObjectCastValidator<V> bukkitCastValidator){

        this.listParams = listParams;
        this.configUtil = configUtil;
        this.bukkitCastValidator = bukkitCastValidator;
    }
    @Override
    public List<String> getPrimitiveList() {
        return listParams.getSection().getStringList(listParams.getPath());
    }

    @Override
    public List<V> validateList(@NonNull List<String> primitiveList) {
        List<V> list = new ArrayList<>();
        primitiveList.forEach(name ->{
            V value = bukkitCastValidator.cast(name);
            if(value == null){
                configUtil.logIllegalArgumentErrorAndReturn(listParams,configUtil.getFullPath(listParams.getSection(), listParams.getPath()));
                return;
            }
            list.add(value);
        });
        return list;
    }
}
