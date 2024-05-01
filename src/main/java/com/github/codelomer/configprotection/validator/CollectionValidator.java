package com.github.codelomer.configprotection.validator;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;
import java.util.function.Function;

public class CollectionValidator {

    private final ConfigUtil configUtil;

    public CollectionValidator(@NonNull ConfigUtil configUtil){
        this.configUtil = configUtil;
    }

    public List<String> checkStringList(@NonNull ConfigListParams<String> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getStringList);
    }

    public List<Integer> checkIntegerList(@NonNull ConfigListParams<Integer> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getIntegerList);
    }
    public List<Double> checkDoubleList(@NonNull ConfigListParams<Double> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getDoubleList);
    }

    public List<Long> checkLongList(@NonNull ConfigListParams<Long> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getLongList);
    }
    public List<Float> checkFloatList(@NonNull ConfigListParams<Float> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getFloatList);
    }

    public List<Byte> checkByteList(@NonNull ConfigListParams<Byte> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getByteList);
    }
    public List<Character> checkCharacterList(@NonNull ConfigListParams<Character> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getCharacterList);
    }
    public List<Short> checkShortList(@NonNull ConfigListParams<Short> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getShortList);
    }
    public List<Boolean> checkBooleanList(@NonNull ConfigListParams<Boolean> configListParams){
        ConfigurationSection section = configListParams.getSection();
        return checkList(configListParams,section::getBooleanList);
    }

    public <V> List<V> checkList(@NonNull ConfigListParams<V> configListParams, @NonNull Class<V> clazz){
        List<V> def = configListParams.getDef();

        if(configUtil.notFoundPath(configListParams)) return def;

        ConfigurationSection section = configListParams.getSection();
        String path = configListParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        if(!section.isList(path)) return configUtil.logIllegalArgumentErrorAndReturn(configListParams,fullPath);

        List<?> uncknownList = section.getList(path);
        if(uncknownList == null) return configUtil.logIllegalArgumentErrorAndReturn(configListParams,fullPath);

        List<V> list = new ArrayList<>();

        for(Object o: uncknownList){
            if(clazz.isInstance(o)){
                V value = clazz.cast(o);
                list.add(value);
                continue;
            }
            configUtil.logIllegalArgumentErrorAndReturn(configListParams,fullPath);
        }

        if(!configListParams.isCanBeEmpty() && list.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR, configListParams.getEmptyErrorText(),fullPath,configListParams.isLogErrors());
            return def;
        }

        if(configUtil.filtered(configListParams.getCustomConditions(),list,fullPath,configListParams.isLogErrors())) return def;

        return list;
    }


    @SuppressWarnings("unchecked")
    public  <K,V> List<Map<K,V>> checkMapList(@NonNull ConfigListParams<Map<K,V>> listParams, @NonNull Class<K> keyClass, @NonNull Class<V> valueClass){
        List<Map<K,V>> def = listParams.getDef();

        if(configUtil.notFoundPath(listParams)) return def;

        ConfigurationSection section = listParams.getSection();
        String path = listParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        List<Map<?,?>> unknownList = section.getMapList(path);

        if(!listParams.isCanBeEmpty() && unknownList.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR,listParams.getEmptyErrorText(),fullPath,listParams.isLogErrors());
            return def;
        }

        List<Map<K,V>> mapList = new ArrayList<>();

        for(Map<?,?> map: unknownList){
            Map<K,V> newMap = new HashMap<>();
            for(Map.Entry<?,?> entry: map.entrySet()){
                Object key = entry.getKey();
                Object value = entry.getValue();
                if(keyClass.isInstance(key) && valueClass.isInstance(value)){
                    newMap.put((K) key, (V) value);
                    continue;
                }
                configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);
            }
            mapList.add(newMap);
        }

        if(configUtil.filtered(listParams.getCustomConditions(),mapList,fullPath,listParams.isLogErrors())) return def;
        return mapList;
    }

    public <E extends Enum<E>> E checkEnum(@NonNull ConfigParams<E> configParams, @NonNull Class<E> enumClass){
        E def = configParams.getDef();

        if(!configUtil.notFoundPath(configParams)) return def;

        ConfigurationSection section = configParams.getSection();
        String path = configParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        String value = section.getString(path);
        if(value == null) return configUtil.logIllegalArgumentErrorAndReturn(configParams,fullPath);

        E enumValue = validateEnum(enumClass,value);
        if(enumValue == null) return configUtil.logIllegalArgumentErrorAndReturn(configParams,fullPath);

        if(configUtil.filtered(configParams.getCustomConditions(),enumValue,fullPath,configParams.isLogErrors())) return def;
        return enumValue;
    }

    public <E extends Enum<E>> List<E> checkEnumList(@NonNull ConfigListParams<E> listParams, @NonNull Class<E> enumClass){
        List<E> def = listParams.getDef();

        if(configUtil.notFoundPath(listParams)) return def;

        ConfigurationSection section = listParams.getSection();
        String path = listParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        if(!section.isList(path)) return configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);

        List<String> enumNamesList = new ArrayList<>(section.getStringList(path));

        if(!listParams.isCanBeEmpty() && enumNamesList.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR, listParams.getEmptyErrorText(),fullPath,listParams.isLogErrors());
            return def;
        }

        List<E> enumList = new ArrayList<>();
        for(String enumName: enumNamesList){
            E value = validateEnum(enumClass,enumName);
            if(value != null){
                enumList.add(value);
                continue;
            }
            configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);
        }

        if(configUtil.filtered(listParams.getCustomConditions(),enumList,fullPath,listParams.isLogErrors())) return def;
        return enumList;
    }


    private <V> List<V> checkList(@NonNull ConfigListParams<V> configListParams, @NonNull Function<String,List<V>> getValueFunction){
        List<V> def = configListParams.getDef();

        if(configUtil.notFoundPath(configListParams)) return def;

        ConfigurationSection section = configListParams.getSection();
        String path = configListParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        if(!section.isList(path)) return configUtil.logIllegalArgumentErrorAndReturn(configListParams,fullPath);

        List<V> list = new ArrayList<>(getValueFunction.apply(path));
        if(!configListParams.isCanBeEmpty() && list.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR, configListParams.getEmptyErrorText(),fullPath,configListParams.isLogErrors());
            return def;
        }
        if(configUtil.filtered(configListParams.getCustomConditions(),list,fullPath,configListParams.isLogErrors())) return def;

        return list;
    }

    private <E extends Enum<E>> E validateEnum(@NonNull Class<E> enumClass, @NonNull String enumName){
        try {
            return Enum.valueOf(enumClass,enumName);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
