package com.github.codelomer.configprotection.checker;

import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.object.ObjectValidator;
import com.github.codelomer.configprotection.validator.object.ObjectCastValidator;
import com.github.codelomer.configprotection.validator.object.impl.BukkitCastValidator;
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
        ObjectCastValidator<Material> castValidator = new BukkitCastValidator<>(Material::matchMaterial,configParams);
        return configUtil.validateObject(configParams,new ObjectValidator<>(castValidator,configUtil,configParams));
    }

    public List<Material> checkMaterialList(@NonNull ConfigListParams<Material> listParams){

    }
    public PotionEffectType checkPotionEffectType(@NonNull ConfigParams<PotionEffectType> configParams){
        ObjectCastValidator<PotionEffectType> castValidator = new BukkitCastValidator<>(PotionEffectType::getByName,configParams);
        return configUtil.validateObject(configParams,new ObjectValidator<>(castValidator,configUtil,configParams));
    }
    public List<PotionEffectType> checkPotionEffectTypeList(@NonNull ConfigListParams<PotionEffectType> configParams){

    }

    public World checkWordByName(@NonNull ConfigParams<World> configParams){
        ObjectCastValidator<World> castValidator = new BukkitCastValidator<>(Bukkit::getWorld,configParams);
        return configUtil.validateObject(configParams,new ObjectValidator<>(castValidator,configUtil,configParams));
    }

    public List<World> checkWordListByNames(@NonNull ConfigListParams<World> listParams){

    }

    public Enchantment checkEnchantmentByName(@NonNull ConfigParams<Enchantment> configParams){
        ObjectCastValidator<Enchantment> castValidator = new BukkitCastValidator<>(Enchantment::getByName,configParams);
        return configUtil.validateObject(configParams,new ObjectValidator<>(castValidator,configUtil,configParams));
    }

    public List<Enchantment> checkEnchantmentListByNames(@NonNull ConfigListParams<Enchantment> listParams){

    }
}
