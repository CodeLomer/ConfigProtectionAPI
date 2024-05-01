package com.github.codelomer.configprotection;

import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigProtectionAPI extends JavaPlugin {
    private ConfigProtectionAPI instance;
    @Override
    public void onEnable() {
        instance = this;
        ConfigNumberParams.<Integer>builder(getConfig(),"path").setD
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigProtectionAPI getInstance() {
        return instance;
    }
}
