# Information
ConfigProtectionAPI is designed to make it easier to validate data from plugin files and configs and log it to the user. With this API you will spend much less time creating a plugin and make your life easier.

## Contribute
- Want to contribute to the project? Check out our [Pull requests](https://github.com/CodeLomer/ConfigProtectionAPI/pulls).
- Encountered a problem? Report it on our [Issue Tracker](https://github.com/CodeLomer/ConfigProtectionAPI/issues)

## Support
- [Issue Tracker](https://github.com/CodeLomer/ConfigProtectionAPI/issues)
- [VK group](https://vk.com/plugincrafting)

## Quick links
- [VK group](https://vk.com/plugincrafting)
- [Javadocs](https://codelomer.github.io/ConfigProtectionAPI/)

# API
## First steps
In order to use the api you need to add a dependency.

Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.CodeLomer</groupId>
    <artifactId>ConfigProtectionAPI</artifactId>
    <version>[VERSION]</version>
</dependency>
```

Gradle
```xml
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.CodeLomer:ConfigProtectionAPI:[VERSION]'
}
```
replace [VERSION] with the release version


## usage

in order to use it, all you need to do is create a validator class
```java
ConfigChecker configChecker = new ConfigChecker(plugin,"configName");
```

here is example validation
```java
package com.github.codelomer.configprotection.example;

import com.github.codelomer.configprotection.api.ConfigChecker;
import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigNumberParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigParams;
import com.github.codelomer.configprotection.model.params.impl.ConfigStringParams;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class StandardConfig {
    private Integer number;
    private String string;
    private List<Integer> numList;
    private Material material;
    private List<ItemStack> items;

    public void StandardConfig(@NonNull JavaPlugin plugin){
        plugin.saveDefaultConfig();

        ConfigChecker configChecker = new ConfigChecker(plugin,"config.yml");
        ConfigurationSection section = plugin.getConfig();

        number = configChecker.checkInt(new ConfigNumberParams<Integer>(section, "int-path")
                .setMinLimit(0)
                .setMaxLimit(100)
                .setDef(50)
                .filter(number -> number != 20,"number not can be 20"));

        string = configChecker.checkString(new ConfigStringParams(section,"string-path")
                .canBeEmpty(true)
                .setDef("def value")
                .filter(s -> !s.equalsIgnoreCase("some filter text")));

        numList = configChecker.checkIntegerList(new ConfigListParams<Integer>(section,"number-list-path")
                .canBeEmpty(false)
                .filter(list -> !list.contains(30)));

        material = configChecker.checkMaterial(new ConfigParams<Material>(section,"material-path")
                .setDef(Material.FURNACE));
        
        items = configChecker.checkList(new ConfigListParams<ItemStack>(section,"item-list-path").canBeEmpty(false), ItemStack.class);
        //etc
    }

    public Integer getNumber() {
        return number;
    }

    public String getString() {
        return string;
    }

    public List<Integer> getNumList() {
        return numList;
    }

    public Material getMaterial() {
        return material;
    }

    public List<ItemStack> getItems() {
        return items;
    }
}
```
