package com.github.codelomer.configprotection;

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VersionChecker {
    private VersionChecker(){}
    private static final Pattern VERSION_PATTERN_FILTER = Pattern.compile("\\(MC: (?<version>[\\d]+\\.[\\d]+(\\.[\\d]+)?)\\)");
    private static String minecraftVersion;

    public static String getMinecraftVersion() {
        if (minecraftVersion != null) {
            return minecraftVersion;
        } else {
            String bukkitGetVersionOutput = Bukkit.getVersion();
            Matcher matcher = VERSION_PATTERN_FILTER.matcher(bukkitGetVersionOutput);
            if (matcher.find()) {
                minecraftVersion = matcher.group("version");
                return minecraftVersion;
            } else {
                throw new IllegalArgumentException("Не удалось определить версию Minecraft по Bukkit.getVersion(): " + bukkitGetVersionOutput);
            }

        }
    }


}