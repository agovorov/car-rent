package com.epam.ag.action;

/**
 * @author by Govorov Andrey.
 */
public class SystemMessage {
    private String type = "success";
    private String message;

    public SystemMessage(String message) {
        this.message = message;
    }

    public SystemMessage(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
