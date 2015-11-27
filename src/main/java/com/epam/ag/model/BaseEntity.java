package com.epam.ag.model;

/**
 * @author Govorov Andrey
 */
public abstract class BaseEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * If true - use insert data, otherwise - update
     */
    public boolean isNewRecord() {
        return id == null;
    }
}
