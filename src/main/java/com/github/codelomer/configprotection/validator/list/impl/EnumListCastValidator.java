package com.github.codelomer.configprotection.validator.list.impl;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.list.ListCastValidator;
import com.github.codelomer.configprotection.validator.object.impl.EnumObjectCastValidator;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EnumListCastValidator<V extends Enum<V>> implements ListCastValidator<V,String> {

    private final ConfigListParams<V> listParams;
    private final ConfigUtil configUtil;
    private final EnumObjectCastValidator<V> enumCastValidator;

    public EnumListCastValidator(@NonNull ConfigListParams<V> listParams, @NonNull ConfigUtil configUtil, @NonNull EnumObjectCastValidator<V> enumCastValidator){

        this.listParams = listParams;
        this.configUtil = configUtil;
        this.enumCastValidator = enumCastValidator;
    }
    @Override
    public List<String> getPrimitiveList() {
        return listParams.getSection().getStringList(listParams.getPath());
    }

    @Override
    public List<V> validateList(@NonNull List<String> primitiveList) {
        List<V> enumList = new ArrayList<>();
        String fullPath = configUtil.getFullPath(listParams.getSection(),listParams.getPath());
        primitiveList.forEach(name ->{
            V value = enumCastValidator.cast(name);
            if(value == null){
                configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);
                return;
            }
            enumList.add(value);
        });
        return enumList;
    }
}
