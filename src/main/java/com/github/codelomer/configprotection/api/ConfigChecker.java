package com.github.codelomer.configprotection.api;

import com.github.codelomer.configprotection.logger.ConfigLogger;
import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigStringParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.CollectionValidator;
import com.github.codelomer.configprotection.validator.PrimitiveValidator;
import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class ConfigChecker {
    private final ConfigLogger configLogger;
    private final PrimitiveValidator primitiveValidator;
    private final CollectionValidator collectionValidator;

    public ConfigChecker(@NonNull JavaPlugin plugin, @NonNull String configName){
        this.configLogger = new ConfigLogger(plugin,configName);
        ConfigUtil configUtil = new ConfigUtil(configLogger);

        this.primitiveValidator = new PrimitiveValidator(configUtil);
        this.collectionValidator = new CollectionValidator(configUtil);
    }

    public ConfigChecker(@NonNull JavaPlugin plugin, @NonNull String configName, @NonNull List<String> logFormatText){
        this.configLogger = new ConfigLogger(plugin,logFormatText,configName);
        ConfigUtil configUtil = new ConfigUtil(configLogger);

        this.primitiveValidator = new PrimitiveValidator(configUtil);
        this.collectionValidator = new CollectionValidator(configUtil);
    }

    public Integer checkInt(@NonNull ConfigNumberParams<Integer> numberParams){
        return primitiveValidator.checkInt(numberParams);
    }

    public Double checkDouble(@NonNull ConfigNumberParams<Double> numberParams){
        return primitiveValidator.checkDouble(numberParams);
    }

    public Long checkLong(@NonNull ConfigNumberParams<Long> numberParams){
        return primitiveValidator.checkLong(numberParams);
    }

    public String checkString(@NonNull ConfigStringParams stringParams){
        return primitiveValidator.checkString(stringParams);
    }

    public List<String> checkStringList(@NonNull ConfigListParams<String> listParams){
        return collectionValidator.checkStringList(listParams);
    }

    public List<Integer> checkIntegerList(@NonNull ConfigListParams<Integer> listParams){
        return collectionValidator.checkIntegerList(listParams);
    }
    public List<Double> checkDoubleList(@NonNull ConfigListParams<Double> listParams){
        return collectionValidator.checkDoubleList(listParams);
    }

    public List<Long> checkLongList(@NonNull ConfigListParams<Long> listParams){
        return collectionValidator.checkLongList(listParams);
    }
    public List<Float> checkFloatList(@NonNull ConfigListParams<Float> listParams){
        return collectionValidator.checkFloatList(listParams);
    }

    public List<Byte> checkByteList(@NonNull ConfigListParams<Byte> listParams){
        return collectionValidator.checkByteList(listParams);
    }
    public List<Character> checkCharacterList(@NonNull ConfigListParams<Character> listParams){
        return collectionValidator.checkCharacterList(listParams);
    }
    public List<Short> checkShortList(@NonNull ConfigListParams<Short> listParams){
        return collectionValidator.checkShortList(listParams);
    }
    public List<Boolean> checkBooleanList(@NonNull ConfigListParams<Boolean> listParams){
        return collectionValidator.checkBooleanList(listParams);
    }
    public <V> List<V> checkList(@NonNull ConfigListParams<V> listParams, @NonNull Class<V> clazz){
        return collectionValidator.checkList(listParams,clazz);
    }

    public  <K,V> List<Map<K,V>> checkMapList(@NonNull ConfigListParams<Map<K,V>> listParams, @NonNull Class<K> keyClass, @NonNull Class<V> valueClass){
        return collectionValidator.checkMapList(listParams,keyClass,valueClass);
    }

    public <E extends Enum<E>> E checkEnum(@NonNull ConfigParams<E> configParams, @NonNull Class<E> enumClass){
        return collectionValidator.checkEnum(configParams,enumClass);
    }

    public <E extends Enum<E>> List<E> checkEnumList(@NonNull ConfigListParams<E> listParams, @NonNull Class<E> enumClass){
        return collectionValidator.checkEnumList(listParams,enumClass);
    }

    public ConfigLogger getConfigLogger() {
        return configLogger;
    }
}
