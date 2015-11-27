package com.epam.ag.model.lists;

import com.epam.ag.model.BaseEntity;

/**
 * @author Govorov Andrey
 */
public class DictionaryBase extends BaseEntity {

    private String value;

    public DictionaryBase(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
