package com.epam.ag.model.user;

import com.epam.ag.model.dict.DictionaryBase;

/**
 * @author Govorov Andrey
 */
public class UserRole extends DictionaryBase {

    public final static UserRole ADMIN = new UserRole(1L, "Админ", "Admin");
    public final static UserRole CLIENT = new UserRole(2L, "Клиент", "Client");

    public UserRole(String value) {
        super(value);
    }

    public UserRole(Long id, String value) {
        super(id, value);
    }

    public UserRole(String value_ru, String value_en) {
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


    /**
     * We need compare only ID, names are not interesting
     *
     * @param obj UserRole object
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        UserRole role = (UserRole) obj;
        return id == role.id;
    }
}
