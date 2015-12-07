package com.epam.ag.utils;

/**
 * @author by Govorov Andrey.
 */
public class SystemMessage {
    private Type type = Type.SUCCESS;
    private String message;

    public SystemMessage(String message) {
        this.message = message;
    }

    public SystemMessage(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type.name();
    }

    public enum Type {
        SUCCESS,
        ERROR,
        DANGER,
        INFO,
        WARNING,
    }
}
