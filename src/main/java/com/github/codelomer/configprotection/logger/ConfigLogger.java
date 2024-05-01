package com.github.codelomer.configprotection.logger;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigLogger {
    private static final CommandSender CONSOLE = Bukkit.getConsoleSender();
    private List<String> defaultLogFormat = new ArrayList<>();
    private final String pluginName;
    private final String configName;

    public ConfigLogger(@NonNull JavaPlugin plugin, @NonNull String configName){
        this.pluginName = "["+plugin.getName()+"] ";
        this.configName = configName;

        defaultLogFormat.add("-----------------------------------");
        defaultLogFormat.add("error detected in {CONFIG_NAME}.");
        defaultLogFormat.add("error type: {ERROR_TYPE}.");
        defaultLogFormat.add("error path: {ERROR_PATH}");
        defaultLogFormat.add("-----------------------------------");



    }

    public ConfigLogger(@NonNull JavaPlugin plugin, @NonNull List<String> logFormat, @NonNull String configName){
        this.pluginName = "["+plugin.getName()+"] ";
        this.configName = configName;
        defaultLogFormat.addAll(logFormat);
    }
    public void log(@NonNull String errorType, @NonNull String errorPath){
        defaultLogFormat.forEach(line ->{
            if(line == null) return;
            line = fillPlaceholders(line,errorType,errorPath);
            logError(line);
        });
    }
    private String fillPlaceholders(@NonNull String line, @NonNull String errorType, @NonNull String errorPath){
        if(line.contains("{CONFIG_NAME}")) line = line.replace("{CONFIG_NAME}",configName);
        if(line.contains("{ERROR_TYPE}")) line = line.replace("{ERROR_TYPE}",errorType);
        if(line.contains("{ERROR_PATH}")) line = line.replace("{ERROR_PATH}",errorPath);
        return line;
    }

    public void setLogTextFormat(@NonNull List<String> text){
        defaultLogFormat = new ArrayList<>(text);
    }

    private void logError(@NonNull String line){
        CONSOLE.sendMessage(ChatColor.RED+pluginName+line);
    }
}
