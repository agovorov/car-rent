package com.epam.ag.model.user;

import com.epam.ag.model.BaseEntity;
import com.epam.ag.model.dict.DictionaryBase;

/**
 * @author Govorov Andrey
 */
public class UserRole extends DictionaryBase {

    public UserRole(String value) {
        super(value);
    }

    public UserRole(Long id, String value) {
        super(id, value);
    }

    public UserRole(String value_ru,String value_en) {
        super(value_ru, value_en);
    }

    public UserRole(Long id, String value_ru, String value_en) {
        super(id, value_ru, value_en);
    }

    public UserRole() {

    }

    public UserRole(Long roleId) {
        super(roleId);
    }
}
