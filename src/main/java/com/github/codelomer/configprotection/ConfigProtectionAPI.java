package com.github.codelomer.configprotection;

import com.github.codelomer.configprotection.test.TestConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigProtectionAPI extends JavaPlugin {

    @Override
    public void onEnable(){
        new TestConfig(this);
    }

}
