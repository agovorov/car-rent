package com.epam.ag.model;

/**
 * @author Govorov Andrey
 */
public abstract class BaseEntity {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * If true - use insert data, otherwise - update
     */
    public boolean isPersisted() {
        return id != null;
    }

    public boolean isNotPersisted() {
        return id == null;
    }
}
