package com.github.codelomer.configprotection.checker;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.list.ListValidator;
import com.github.codelomer.configprotection.validator.list.impl.EnumListCastValidator;
import com.github.codelomer.configprotection.validator.list.impl.ObjectListCastValidator;
import com.github.codelomer.configprotection.validator.list.impl.ObjectListMapCastValidator;
import com.github.codelomer.configprotection.validator.list.impl.PrimitiveListCastValidator;
import com.github.codelomer.configprotection.validator.object.ObjectValidator;
import com.github.codelomer.configprotection.validator.object.impl.EnumObjectCastValidator;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

public class CollectionChecker {
    private final ConfigUtil configUtil;

    public CollectionChecker(@NonNull ConfigUtil configUtil){
        this.configUtil = configUtil;
    }

    public List<String> checkStringList(@NonNull ConfigListParams<String> configListParams){
        PrimitiveListCastValidator<String> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getStringList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }

    public List<Integer> checkIntegerList(@NonNull ConfigListParams<Integer> configListParams){
        PrimitiveListCastValidator<Integer> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getIntegerList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }
    public List<Double> checkDoubleList(@NonNull ConfigListParams<Double> configListParams){
        PrimitiveListCastValidator<Double> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getDoubleList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }

    public List<Long> checkLongList(@NonNull ConfigListParams<Long> configListParams){
        PrimitiveListCastValidator<Long> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getLongList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }
    public List<Float> checkFloatList(@NonNull ConfigListParams<Float> configListParams){
        PrimitiveListCastValidator<Float> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getFloatList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }

    public List<Byte> checkByteList(@NonNull ConfigListParams<Byte> configListParams){
        PrimitiveListCastValidator<Byte> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getByteList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }
    public List<Character> checkCharacterList(@NonNull ConfigListParams<Character> configListParams){
        PrimitiveListCastValidator<Character> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getCharacterList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }
    public List<Short> checkShortList(@NonNull ConfigListParams<Short> configListParams){
        PrimitiveListCastValidator<Short> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getShortList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }
    public List<Boolean> checkBooleanList(@NonNull ConfigListParams<Boolean> configListParams){
        PrimitiveListCastValidator<Boolean> castValidator = new PrimitiveListCastValidator<>(configListParams.getPath(),configListParams.getSection()::getBooleanList);
        return configUtil.validateObject(configListParams,new ListValidator<>(configListParams,configUtil,castValidator));
    }

    public <V> List<V> checkList(@NonNull ConfigListParams<V> configListParams, @NonNull Class<V> clazz){
        ObjectListCastValidator<V> objectListCastValidator = new ObjectListCastValidator<>(configListParams,configUtil,clazz);
        ListValidator<V,?> listValidator = new ListValidator<>(configListParams,configUtil,objectListCastValidator);
        return configUtil.validateObject(configListParams,listValidator);
    }

    public  <K,V> List<Map<K,V>> checkMapList(@NonNull ConfigListParams<Map<K,V>> listParams, @NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {
        ObjectListMapCastValidator<K,V> objectListMapCastValidator = new ObjectListMapCastValidator<>(listParams,configUtil,keyClass,valueClass);
        ListValidator<Map<K,V>,Map<?,?>> listValidator = new ListValidator<>(listParams,configUtil,objectListMapCastValidator);
        return configUtil.validateObject(listParams,listValidator);
    }

    public <E extends Enum<E>> E checkEnum(@NonNull ConfigParams<E> configParams, @NonNull Class<E> enumClass){
        EnumObjectCastValidator<E> castValidator = new EnumObjectCastValidator<>(configParams.getSection(),configParams.getPath(),enumClass);
        ObjectValidator<E,String> objectValidator = new ObjectValidator<>(castValidator,configUtil,configParams);
        return configUtil.validateObject(configParams,objectValidator);
    }

    public <E extends Enum<E>> List<E> checkEnumList(@NonNull ConfigListParams<E> listParams, @NonNull Class<E> enumClass){
        EnumObjectCastValidator<E> enumCastValidator = new EnumObjectCastValidator<>(listParams.getSection(),listParams.getPath(),enumClass);
        EnumListCastValidator<E> castValidator = new EnumListCastValidator<>(listParams,configUtil, enumCastValidator);
        return configUtil.validateObject(listParams,new ListValidator<>(listParams,configUtil,castValidator));

    }
}
