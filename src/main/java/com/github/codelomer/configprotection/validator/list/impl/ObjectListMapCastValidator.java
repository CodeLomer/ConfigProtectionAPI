package com.github.codelomer.configprotection.validator.list.impl;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.list.ListCastValidator;
import lombok.NonNull;

import java.util.*;

public class ObjectListMapCastValidator<K,V> implements ListCastValidator<Map<K,V>, Map<?,?>> {


    private final ConfigListParams<Map<K, V>> listParams;
    private final ConfigUtil configUtil;
    private final Class<K> keyClass;
    private final Class<V> valueClass;

    public ObjectListMapCastValidator(@NonNull ConfigListParams<Map<K,V>> listParams, @NonNull ConfigUtil configUtil,
                                      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass){

        this.listParams = listParams;
        this.configUtil = configUtil;
        this.keyClass = keyClass;
        this.valueClass = valueClass;
    }
    @Override

    public List<Map<?, ?>> getPrimitiveList() {
        return listParams.getSection().getMapList(listParams.getPath());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<K, V>> validateList(@NonNull List<Map<?, ?>> primitiveList) {
        List<Map<K,V>> mapList = new ArrayList<>();
        String fullPath = configUtil.getFullPath(listParams.getSection(),listParams.getPath());
        for(Map<?,?> map: primitiveList){
            Map<K,V> validatedMap = new HashMap<>();
            for(Map.Entry<?, ?> entry: map.entrySet()){
                Object key = entry.getKey();
                Object value = entry.getValue();
                if(keyClass.isInstance(key) && valueClass.isInstance(value)){
                    validatedMap.put((K) key, (V) value);
                    continue;
                }
                configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);
            }
            mapList.add(validatedMap);
        }
        return mapList;
    }
}
