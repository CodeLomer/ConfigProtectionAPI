package com.github.codelomer.configprotection.checker;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.ListValidator;
import com.github.codelomer.configprotection.validator.list.impl.BukkitListCastValidator;
import com.github.codelomer.configprotection.validator.ObjectValidator;
import com.github.codelomer.configprotection.validator.object.impl.BukkitObjectCastValidator;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class BukkitChecker {
    private final ConfigUtil configUtil;

    public BukkitChecker(@NonNull ConfigUtil configUtil){

        this.configUtil = configUtil;
    }


    public Material checkMaterial(@NonNull ConfigParams<Material> configParams){
        BukkitObjectCastValidator<Material> castValidator = new BukkitObjectCastValidator<>(Material::matchMaterial,configParams.getSection(),configParams.getPath());
        ObjectValidator<Material,String> objectValidator = new ObjectValidator<>(castValidator,configUtil,configParams);
        return configUtil.validateObject(configParams,objectValidator);
    }

    public List<Material> checkMaterialList(@NonNull ConfigListParams<Material> listParams){
        BukkitObjectCastValidator<Material> bukkitObjectCastValidator = new BukkitObjectCastValidator<>(Material::matchMaterial,listParams.getSection(),listParams.getPath());
        BukkitListCastValidator<Material> bukkitListCastValidator = new BukkitListCastValidator<>(listParams,configUtil,bukkitObjectCastValidator);
        ListValidator<Material,String> listValidator = new ListValidator<>(listParams,configUtil,bukkitListCastValidator);
        return configUtil.validateObject(listParams,listValidator);
    }
    public PotionEffectType checkPotionEffectType(@NonNull ConfigParams<PotionEffectType> configParams){
        BukkitObjectCastValidator<PotionEffectType> bukkitObjectCastValidator = new BukkitObjectCastValidator<>(PotionEffectType::getByName,configParams.getSection(),configParams.getPath());
        ObjectValidator<PotionEffectType,String> objectValidator = new ObjectValidator<>(bukkitObjectCastValidator,configUtil,configParams);
        return configUtil.validateObject(configParams,objectValidator);
    }
    public List<PotionEffectType> checkPotionEffectTypeList(@NonNull ConfigListParams<PotionEffectType> listParams){
        BukkitObjectCastValidator<PotionEffectType> bukkitObjectCastValidator = new BukkitObjectCastValidator<>(PotionEffectType::getByName,listParams.getSection(),listParams.getPath());
        BukkitListCastValidator<PotionEffectType> bukkitListCastValidator = new BukkitListCastValidator<>(listParams,configUtil,bukkitObjectCastValidator);
        ListValidator<PotionEffectType,String> listValidator = new ListValidator<>(listParams,configUtil,bukkitListCastValidator);
        return configUtil.validateObject(listParams,listValidator);
    }

    public World checkWordByName(@NonNull ConfigParams<World> configParams){
        BukkitObjectCastValidator<World> bukkitObjectCastValidator = new BukkitObjectCastValidator<>(Bukkit::getWorld,configParams.getSection(),configParams.getPath());
        ObjectValidator<World,String> objectValidator = new ObjectValidator<>(bukkitObjectCastValidator,configUtil,configParams);
        return configUtil.validateObject(configParams,objectValidator);
    }

    public List<World> checkWordListByNames(@NonNull ConfigListParams<World> listParams){
        BukkitObjectCastValidator<World> bukkitObjectCastValidator = new BukkitObjectCastValidator<>(Bukkit::getWorld,listParams.getSection(),listParams.getPath());
        BukkitListCastValidator<World> bukkitListCastValidator = new BukkitListCastValidator<>(listParams,configUtil,bukkitObjectCastValidator);
        ListValidator<World,String> listValidator = new ListValidator<>(listParams,configUtil,bukkitListCastValidator);
        return configUtil.validateObject(listParams,listValidator);
    }

    public Enchantment checkEnchantmentByName(@NonNull ConfigParams<Enchantment> configParams){
        BukkitObjectCastValidator<Enchantment> bukkitObjectCastValidator = new BukkitObjectCastValidator<>(Enchantment::getByName,configParams.getSection(),configParams.getPath());
        ObjectValidator<Enchantment,String> objectValidator = new ObjectValidator<>(bukkitObjectCastValidator,configUtil,configParams);
        return configUtil.validateObject(configParams,objectValidator);
    }

    public List<Enchantment> checkEnchantmentListByNames(@NonNull ConfigListParams<Enchantment> listParams){
        BukkitObjectCastValidator<Enchantment> bukkitObjectCastValidator = new BukkitObjectCastValidator<>(Enchantment::getByName,listParams.getSection(),listParams.getPath());
        BukkitListCastValidator<Enchantment> bukkitListCastValidator = new BukkitListCastValidator<>(listParams,configUtil,bukkitObjectCastValidator);
        ListValidator<Enchantment,String> listValidator = new ListValidator<>(listParams,configUtil,bukkitListCastValidator);
        return configUtil.validateObject(listParams,listValidator);
    }
}
