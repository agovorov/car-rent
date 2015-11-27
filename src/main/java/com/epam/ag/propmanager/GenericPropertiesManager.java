package com.epam.ag.propmanager;

public interface GenericPropertiesManager {

    void loadPropertyFile(String filename);
    String get(String key);
    String get(String filename, String key);
}
