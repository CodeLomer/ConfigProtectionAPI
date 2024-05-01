package com.github.codelomer.configprotection.validator;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BukkitValidator {
    private final ConfigUtil configUtil;

    public BukkitValidator(@NonNull ConfigUtil configUtil){

        this.configUtil = configUtil;
    }


    public Material checkMaterial(@NonNull ConfigParams<Material> configParams){
        return validateBukkitObject(configParams,Material::matchMaterial);
    }

    public List<Material> checkMaterialList(@NonNull ConfigListParams<Material> listParams){
        return validateBukkitObjectList(listParams,Material::matchMaterial);
    }
    public PotionEffectType checkPotionEffectType(@NonNull ConfigParams<PotionEffectType> configParams){
        return validateBukkitObject(configParams,PotionEffectType::getByName);
    }
    public PotionEffectType checkPotionEffectTypeList(@NonNull ConfigParams<PotionEffectType> configParams){
        return validateBukkitObject(configParams,PotionEffectType::getByName);
    }

    public World checkWordByName(@NonNull ConfigParams<World> configParams){
        return validateBukkitObject(configParams, Bukkit::getWorld);
    }

    public List<World> checkWordListByNames(@NonNull ConfigListParams<World> listParams){
        return validateBukkitObjectList(listParams, Bukkit::getWorld);
    }

    public Enchantment checkEnchantmentByName(@NonNull ConfigParams<Enchantment> configParams){
        return validateBukkitObject(configParams,Enchantment::getByName);
    }

    public List<Enchantment> checkEnchantmentListByNames(@NonNull ConfigListParams<Enchantment> listParams){
        return validateBukkitObjectList(listParams,Enchantment::getByName);
    }
    private  <V> V validateBukkitObject(@NonNull ConfigParams<V> configParams, @NonNull Function<String,V> getObjectFunction){
        V def = configParams.getDef();
        if(configUtil.notFoundPath(configParams)) return def;

        ConfigurationSection section = configParams.getSection();
        String path = configParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        V value = getObjectFunction.apply(section.getString(path));

        if(value == null) return configUtil.logIllegalArgumentErrorAndReturn(configParams,fullPath);

        if(configUtil.filtered(configParams.getCustomConditions(),value,fullPath,configParams.isLogErrors())) return def;
        return value;
    }

    private  <V> List<V> validateBukkitObjectList(@NonNull ConfigListParams<V> listParams, @NonNull Function<String,V> getObjectFunction){
        List<V> def = listParams.getDef();

        if(configUtil.notFoundPath(listParams)) return def;

        ConfigurationSection section = listParams.getSection();
        String path = listParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        List<String> objectNames = new ArrayList<>(section.getStringList(path));

        if(!listParams.isCanBeEmpty() && objectNames.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR, listParams.getEmptyErrorText(),fullPath,listParams.isLogErrors());
            return def;
        }

        List<V> objects = new ArrayList<>();
        objectNames.forEach(name ->{
            V object = getObjectFunction.apply(name);
            if(object == null){
                configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);
                return;
            }
            objects.add(object);
        });

        if(configUtil.filtered(listParams.getCustomConditions(),objects,fullPath,listParams.isLogErrors())) return def;
        return objects;
    }
}
