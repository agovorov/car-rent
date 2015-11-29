package com.epam.ag.model.dict;

import com.epam.ag.model.BaseEntity;

/**
 * @author Govorov Andrey
 */
public class DictionaryBase extends BaseEntity {

    private String value;
    private String value_en;

    public DictionaryBase() {

    }

    public DictionaryBase(String value) {
        this.value = value;
    }

    public DictionaryBase(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public  DictionaryBase(String value_ru, String value_en) {
        this.value = value_ru;
        this.value_en = value_en;
    }

    public  DictionaryBase(Long id, String value_ru, String value_en) {
        this.id = id;
        this.value = value_ru;
        this.value_en = value_en;
    }

    public String getValue() {
        return value;
    }

    // Очень странная функция
    // Но это загушка
    // TODO язык надо прикрутить...
    public String getValue(String lang) {
        if (lang.equals("ru")) {
            return value;
        } else {
            return value_en;
        }
    }

    @Override
    public String toString() {
        return "{" + id + ": '" + value + "'}";
    }

    public void setValues(String value_ru, String value_en) {
        this.value = value_ru;
        this.value_en = value_en;
    }
}
