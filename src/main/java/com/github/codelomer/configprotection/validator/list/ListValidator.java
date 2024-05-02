package com.github.codelomer.configprotection.validator.list;

import com.github.codelomer.configprotection.api.ConfigValidator;
import com.github.codelomer.configprotection.model.params.impl.ConfigListParams;
import com.github.codelomer.configprotection.util.ConfigUtil;
import com.github.codelomer.configprotection.validator.list.ListCastValidator;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ListValidator<V,P> implements ConfigValidator<List<V>> {

    private final ConfigListParams<V> listParams;
    private final ConfigUtil configUtil;
    private final ListCastValidator<V, P> listCastValidator;

    public ListValidator(@NonNull ConfigListParams<V> listParams, @NonNull ConfigUtil configUtil, @NonNull ListCastValidator<V,P> listCastValidator){

        this.listParams = listParams;
        this.configUtil = configUtil;
        this.listCastValidator = listCastValidator;
    }
    @Override
    public List<V> validate() {
        ConfigurationSection section = listParams.getSection();
        String path = listParams.getPath();
        String fullPath = configUtil.getFullPath(section,path);

        if(!section.isList(path)) return configUtil.logIllegalArgumentErrorAndReturn(listParams,fullPath);

        List<V> def = new ArrayList<>(listParams.getDef());

        List<P> primitiveList = new ArrayList<>(listCastValidator.getPrimitiveList());

        if(!listParams.isCanBeEmpty() && primitiveList.isEmpty()){
            configUtil.logError(ConfigUtil.SECTION_EMPTY_ERROR, listParams.getEmptyErrorText(),fullPath,listParams.isLogErrors());
            return def;
        }
        return new ArrayList<>(listCastValidator.validateList(primitiveList));

    }
}
