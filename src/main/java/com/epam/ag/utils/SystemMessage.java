package com.epam.ag.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * System messages for frontend
 *
 * @author by Govorov Andrey.
 */
public class SystemMessage {
    public static final String SUCCESS = "success";
    public static final String ERROR = "danger";
    public static final String INFO = "danger";
    public static final String WARNING = "warning";

    private boolean showErrorsList = true;

    private String type;
    private String message;
    private Map<String, String> errorMap = new LinkedHashMap<>();

    public SystemMessage(String message) {
        this.message = message;
    }

    public SystemMessage(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public SystemMessage() {

    }

    public boolean hasErrors() {
        return !errorMap.isEmpty();
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public void setType(String messageType) {
        type = messageType;
    }

    public void addError(String parameter, String error) {
        errorMap.put(parameter, error);
    }

    public Map<String, String> getErrors() {
        return errorMap;
    }

    public boolean isShowErrorsList() {
        return showErrorsList;
    }

    public void setShowErrorsList(boolean showErrorsList) {
        this.showErrorsList = showErrorsList;
    }

    @Override
    public String toString() {
        return "SystemMessage{" +
                "showErrorsList=" + showErrorsList +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", errorMap.size=" + errorMap.size() +
                '}';
    }
}
