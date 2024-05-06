package com.github.codelomer.configprotection;

import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class ConfigProtectionAPI extends JavaPlugin {

    @Override
    public void onEnable(){
        Metrics metrics = new Metrics(this,21813);
        registerUsePluginMetrics(metrics);
        registerMinecraftVersionMetrics(metrics);
    }


    private void registerUsePluginMetrics(@NonNull Metrics metrics){
        metrics.addCustomChart(new Metrics.SingleLineChart("servers", () -> 1));
    }

    private void registerMinecraftVersionMetrics(@NonNull Metrics metrics){
        metrics.addCustomChart(new Metrics.DrilldownPie("minecraftVersion", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String minecraftVersion = VersionChecker.getMinecraftVersion();
            Map<String, Integer> entry = new HashMap<>();
            entry.put(minecraftVersion,1);
            map.put("Minecraft server version "+minecraftVersion,entry);
            return map;
        }));

    }

}
