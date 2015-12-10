package com.epam.ag.model;

/**
 * @author Govorov Andrey
 */
public class BreadcrumbsItem {
    private String actionName;
    private String label;
    private boolean isActive;

    public BreadcrumbsItem(String actionName, String label, boolean isActive) {
        this.actionName = actionName;
        this.label = label;
        this.isActive = isActive;
    }

    public String getActionName() {
        return actionName;
    }

    public String getLabel() {
        return label;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
