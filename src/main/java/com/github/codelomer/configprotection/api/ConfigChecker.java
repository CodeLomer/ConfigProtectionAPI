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
import java.util.function.Predicate;

/**
 * Main API Class for Interacting with Input Parameters from Configuration.
 * This class provides methods for validating and retrieving various types of data from a configuration file.
 */
public class ConfigChecker {

    /** error logger class */
    private final ConfigLogger configLogger;

    /** primitive data validate class */
    private final PrimitiveChecker primitiveValidator;

    /** collection data validate class */
    private final CollectionChecker collectionValidator;

    /** bukkit data validate class */
    private final BukkitChecker bukkitValidator;

    /**
     * Constructs a ConfigChecker object.
     * @param plugin The JavaPlugin instance associated with the API.
     * @param configName The name of the configuration file to interact with during validation.
     */
    public ConfigChecker(@NonNull JavaPlugin plugin, @NonNull String configName){
        this.configLogger = new ConfigLogger(plugin,configName);
        ConfigUtil configUtil = new ConfigUtil(configLogger);

        this.primitiveValidator = new PrimitiveChecker(configUtil);
        this.collectionValidator = new CollectionChecker(configUtil);
        this.bukkitValidator = new BukkitChecker(configUtil);
    }

    /**
     * Constructs a ConfigChecker object with custom log format text.
     * @param plugin The JavaPlugin instance associated with the API.
     * @param configName The name of the configuration file to interact with during validation.
     * @param logFormatText The default text format for error log output.
     */
    public ConfigChecker(@NonNull JavaPlugin plugin, @NonNull String configName, @NonNull List<String> logFormatText){
        this.configLogger = new ConfigLogger(plugin,logFormatText,configName);
        ConfigUtil configUtil = new ConfigUtil(configLogger);

        this.primitiveValidator = new PrimitiveChecker(configUtil);
        this.collectionValidator = new CollectionChecker(configUtil);
        this.bukkitValidator = new BukkitChecker(configUtil);
    }

    /**
     * Validates and retrieves an integer value from the configuration.
     * @param numberParams An object storing data about the validating integer.
     * @return The validated integer value, or null if the validation fails and no default value is set.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#setDef(Object)
     */
    public Integer checkInt(@NonNull ConfigNumberParams<Integer> numberParams){
        return primitiveValidator.checkInt(numberParams);
    }
    /**
     * Validates and retrieves a double value from the configuration.
     * @param numberParams An object storing data about the validating double.
     * @return The validated double value, or null if the validation fails and no default value is set.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#setDef(Object)
     */
    public Double checkDouble(@NonNull ConfigNumberParams<Double> numberParams){
        return primitiveValidator.checkDouble(numberParams);
    }

    /**
     * Validates and retrieves a long value from the configuration.
     * @param numberParams An object storing data about the validating long.
     * @return The validated long value, or null if the validation fails and no default value is set.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#setDef(Object)
     */
    public Long checkLong(@NonNull ConfigNumberParams<Long> numberParams){
        return primitiveValidator.checkLong(numberParams);
    }

    /**
     * Validates and retrieves a string value from the configuration.
     * @param stringParams An object storing data about the validating string.
     * @return The validated string value, or null if the validation fails and no default value is set.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#setDef(Object)
     */
    public String checkString(@NonNull ConfigStringParams stringParams){
        return primitiveValidator.checkString(stringParams);
    }

    /**
     * Validates and retrieves a list of strings from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */
    public List<String> checkStringList(@NonNull ConfigListParams<String> listParams){
        return collectionValidator.checkStringList(listParams);
    }

    /**
     * Validates and retrieves a list of integer from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */
    public List<Integer> checkIntegerList(@NonNull ConfigListParams<Integer> listParams){
        return collectionValidator.checkIntegerList(listParams);
    }

    /**
     * Validates and retrieves a list of Double from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */

    public List<Double> checkDoubleList(@NonNull ConfigListParams<Double> listParams){
        return collectionValidator.checkDoubleList(listParams);
    }

    /**
     * Validates and retrieves a list of long from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */

    public List<Long> checkLongList(@NonNull ConfigListParams<Long> listParams){
        return collectionValidator.checkLongList(listParams);
    }

    /**
     * Validates and retrieves a list of float from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */

    public List<Float> checkFloatList(@NonNull ConfigListParams<Float> listParams){
        return collectionValidator.checkFloatList(listParams);
    }

    /**
     * Validates and retrieves a list of byte from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */

    public List<Byte> checkByteList(@NonNull ConfigListParams<Byte> listParams){
        return collectionValidator.checkByteList(listParams);
    }

    /**
     * Validates and retrieves a list of character from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */

    public List<Character> checkCharacterList(@NonNull ConfigListParams<Character> listParams){
        return collectionValidator.checkCharacterList(listParams);
    }

    /**
     * Validates and retrieves a list of short from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */

    public List<Short> checkShortList(@NonNull ConfigListParams<Short> listParams){
        return collectionValidator.checkShortList(listParams);
    }

    /**
     * Validates and retrieves a list of boolean from the configuration.
     * @param listParams An object storing data about the validating string list.
     * @return A list of validated string objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */

    public List<Boolean> checkBooleanList(@NonNull ConfigListParams<Boolean> listParams){
        return collectionValidator.checkBooleanList(listParams);
    }

    /**
     * Validates and retrieves a list of objects from the configuration.
     * @param <V> The type of objects in the list.
     * @param listParams An object storing data about the validating object list.
     * @param clazz The class type of the objects in the list.
     * @return A list of validated objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */
    public <V> List<V> checkList(@NonNull ConfigListParams<V> listParams, @NonNull Class<V> clazz){
        return collectionValidator.checkList(listParams,clazz);
    }

    /**
     * Validates and retrieves a list of map objects from the configuration.
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map.
     * @param listParams An object storing data about the validating map list.
     * @param keyClass The class type of keys in the map.
     * @param valueClass The class type of values in the map.
     * @return A list of validated map objects, or the default value if the specified path from the section was not found or custom conditions were not validated.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#filter(Predicate)
     */
    public  <K,V> List<Map<K,V>> checkMapList(@NonNull ConfigListParams<Map<K,V>> listParams, @NonNull Class<K> keyClass, @NonNull Class<V> valueClass){
        return collectionValidator.checkMapList(listParams,keyClass,valueClass);
    }

    /**
     * Validates and retrieves an enum value from the configuration.
     * @param <E> The type of enum.
     * @param configParams An object storing data about the validating enum.
     * @param enumClass The class type of the enum.
     * @return The validated enum value, or null if the validation fails and no default value is set.
     * @see com.github.codelomer.configprotection.model.params.AbstractConfigParams#setDef(Object)
     */

    public <E extends Enum<E>> E checkEnum(@NonNull ConfigParams<E> configParams, @NonNull Class<E> enumClass){
        return collectionValidator.checkEnum(configParams,enumClass);
    }

    /**
     * Validates and retrieves a list of enum values from the configuration.
     * @param <E> The type of enum.
     * @param listParams An object storing data about the validating enum list.
     * @param enumClass The class type of the enum.
     * @return A list of validated enum values, or the default value if the specified path from the section was not found or custom conditions were not validated.
     */

    public <E extends Enum<E>> List<E> checkEnumList(@NonNull ConfigListParams<E> listParams, @NonNull Class<E> enumClass){
        return collectionValidator.checkEnumList(listParams,enumClass);
    }

    /**
     * Validates and retrieves a Material value from the configuration.
     * @param configParams An object storing data about the validating Material.
     * @return The validated Material value, or null if the validation fails and no default value is set.
     */

    public Material checkMaterial(@NonNull ConfigParams<Material> configParams){
        return bukkitValidator.checkMaterial(configParams);
    }

    /**
     * Validates and retrieves a list of Material values from the configuration.
     * @param listParams An object storing data about the validating Material list.
     * @return A list of validated Material values, or the default value if the specified path from the section was not found or custom conditions were not validated.
     */

    public List<Material> checkMaterialList(@NonNull ConfigListParams<Material> listParams){
        return bukkitValidator.checkMaterialList(listParams);
    }

    /**
     * Validates and retrieves a PotionEffectType value from the configuration.
     * @param configParams An object storing data about the validating PotionEffectType.
     * @return The validated PotionEffectType value, or null if the validation fails and no default value is set.
     */

    public PotionEffectType checkPotionType(@NonNull ConfigParams<PotionEffectType> configParams){
        return bukkitValidator.checkPotionEffectType(configParams);
    }

    /**
     * Validates and retrieves a list of PotionEffectType values from the configuration.
     * @param listParams An object storing data about the validating PotionEffectType list.
     * @return A list of validated PotionEffectType values, or the default value if the specified path from the section was not found or custom conditions were not validated.
     */

    public List<PotionEffectType> checkPotionTypeList(@NonNull ConfigListParams<PotionEffectType> listParams){
        return bukkitValidator.checkPotionEffectTypeList(listParams);
    }

    /**
     * Validates and retrieves a World value from the configuration by name.
     * @param configParams An object storing data about the validating World.
     * @return The validated World value, or null if the validation fails and no default value is set.
     */
    public World checkWorldByName(@NonNull ConfigParams<World> configParams){
        return bukkitValidator.checkWordByName(configParams);
    }

    /**
     * Validates and retrieves a list of World values from the configuration by names.
     * @param listParams An object storing data about the validating World list.
     * @return A list of validated World values, or the default value if the specified path from the section was not found or custom conditions were not validated.
     */

    public List<World> checkWorldListByNames(@NonNull ConfigListParams<World> listParams){
        return bukkitValidator.checkWordListByNames(listParams);
    }

    /**
     * Validates and retrieves an Enchantment value from the configuration by name.
     * @param configParams An object storing data about the validating Enchantment.
     * @return The validated Enchantment value, or null if the validation fails and no default value is set.
     */

    public Enchantment checkEnchantmentByName(@NonNull ConfigParams<Enchantment> configParams){
        return bukkitValidator.checkEnchantmentByName(configParams);
    }

    /**
     * Validates and retrieves a list of Enchantment values from the configuration by names.
     * @param listParams An object storing data about the validating Enchantment list.
     * @return A list of validated Enchantment values, or the default value if the specified path from the section was not found or custom conditions were not validated.
     */

    public List<Enchantment> checkEnchantmentListByName(@NonNull ConfigListParams<Enchantment> listParams){
        return bukkitValidator.checkEnchantmentListByNames(listParams);
    }

    /**
     * Validates and retrieves an object from the configuration.
     * @param <V> The type of object.
     * @param validator custom validator
     * @return The validated object, or null if the validation fails.
     */
    public <V> V validateObject(@NonNull ConfigValidator<V> validator){
        return validator.validate();

    }

    /**
     * Retrieves the ConfigLogger associated with this ConfigChecker instance.
     * @return config logger
     */
    public ConfigLogger getConfigLogger() {
        return configLogger;
    }
}
