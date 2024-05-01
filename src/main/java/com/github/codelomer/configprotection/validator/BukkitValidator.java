package com.github.codelomer.configprotection.validator;

import com.github.codelomer.configprotection.util.ConfigUtil;
import lombok.NonNull;

public class BukkitValidator {
    private final ConfigUtil configUtil;

    public BukkitValidator(@NonNull ConfigUtil configUtil){

        this.configUtil = configUtil;
    }


}
