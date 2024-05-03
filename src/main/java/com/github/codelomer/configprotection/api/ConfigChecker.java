package com.github.codelomer.configprotection.api;

import com.github.codelomer.configprotection.checker.BukkitChecker;
import com.github.codelomer.configprotection.checker.CollectionChecker;
import com.github.codelomer.configprotection.checker.PrimitiveChecker;
import com.github.codelomer.configprotection.logger.ConfigLogger;
import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigStringParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Map;

public class ConfigChecker {
    private final ConfigLogger configLogger;
    private final PrimitiveChecker primitiveValidator;
    private final CollectionChecker collectionValidator;
    private final BukkitChecker bukkitValidator;

    public ConfigChecker(@NonNull JavaPlugin plugin, @NonNull String configName){
        this.configLogger = new ConfigLogger(plugin,configName);
        ConfigUtil configUtil = new ConfigUtil(configLogger);

        this.primitiveValidator = new PrimitiveChecker(configUtil);
        this.collectionValidator = new CollectionChecker(configUtil);
        this.bukkitValidator = new BukkitChecker(configUtil);
    }

    public ConfigChecker(@NonNull JavaPlugin plugin, @NonNull String configName, @NonNull List<String> logFormatText){
        this.configLogger = new ConfigLogger(plugin,logFormatText,configName);
        ConfigUtil configUtil = new ConfigUtil(configLogger);

        this.primitiveValidator = new PrimitiveChecker(configUtil);
        this.collectionValidator = new CollectionChecker(configUtil);
        this.bukkitValidator = new BukkitChecker(configUtil);
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

    public Material checkMaterial(@NonNull ConfigParams<Material> configParams){
        return bukkitValidator.checkMaterial(configParams);
    }

    public List<Material> checkMaterialList(@NonNull ConfigListParams<Material> listParams){
        return bukkitValidator.checkMaterialList(listParams);
    }

    public PotionEffectType checkPotionType(@NonNull ConfigParams<PotionEffectType> configParams){
        return bukkitValidator.checkPotionEffectType(configParams);
    }

    public List<PotionEffectType> checkPotionTypeList(@NonNull ConfigListParams<PotionEffectType> listParams){
        return bukkitValidator.checkPotionEffectTypeList(listParams);
    }

    public World checkWorldByName(@NonNull ConfigParams<World> configParams){
        return bukkitValidator.checkWordByName(configParams);
    }

    public List<World> checkWorldListByNames(@NonNull ConfigListParams<World> listParams){
        return bukkitValidator.checkWordListByNames(listParams);
    }

    public Enchantment checkEnchantmentName(@NonNull ConfigParams<Enchantment> configParams){
        return bukkitValidator.checkEnchantmentByName(configParams);
    }

    public List<Enchantment> checkEnchantmentListByName(@NonNull ConfigListParams<Enchantment> listParams){
        return bukkitValidator.checkEnchantmentListByNames(listParams);
    }

    public <V> V validateObject(@NonNull ConfigValidator<V> validator){
        return validator.validate();

    }
    public ConfigLogger getConfigLogger() {
        return configLogger;
    }
}
