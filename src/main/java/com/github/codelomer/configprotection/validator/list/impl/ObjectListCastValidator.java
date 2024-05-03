package com.github.codelomer.configprotection.validator.list.impl;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.list.ListCastValidator;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ObjectListCastValidator<V> implements ListCastValidator<V,Object> {

    private final ConfigListParams<V> listParams;
    private final ConfigUtil configUtil;
    private final Class<V> clazz;

    public ObjectListCastValidator(@NonNull ConfigListParams<V> listParams, @NonNull ConfigUtil configUtil, @NonNull Class<V> clazz){

        this.listParams = listParams;
        this.configUtil = configUtil;
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getPrimitiveList() {
        try {
            List<?> list = listParams.getSection().getList(listParams.getPath());
            if(list == null) return null;
            return (List<Object>) list;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<V> validateList(@NonNull List<Object> primitiveList) {
        String fullPath = configUtil.getFullPath(listParams.getSection(),listParams.getPath());
        List<V> validatedList = new ArrayList<>();
        for(Object o: primitiveList){
            if(clazz.isInstance(o)){
                validatedList.add((V) o);
                continue;
            }
            configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);
        }
        return validatedList;
    }
}
