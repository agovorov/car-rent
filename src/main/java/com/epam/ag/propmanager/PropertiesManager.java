package com.epam.ag.propmanager;

import com.epam.ag.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *
 * PropertiesManager pm = PropertiesManager.getInstance();
 * pm.loadPropertyFile("query.properties");
 * String query = pm.get("vehicle.insert");
 *
 * OR
 * String query = pm.get("query.properties", "vehicle.insert");
 */
public class PropertiesManager implements GenericPropertiesManager{

    private static final Logger log = LoggerFactory.getLogger(PropertiesManager.class);
    private static PropertiesManager instance;
    private Properties properties;

    public static PropertiesManager getInstance() {
        if (instance == null) {
            log.trace("Creating new properties manager instance");
            instance = new PropertiesManager();
        }
        return instance;
    }

    @Override
    public void loadPropertyFile(String filename) {
        try (InputStream is = App.class.getClassLoader().getResourceAsStream(filename)) {
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            log.error("Unable to load property file", e);
            throw new RuntimeException("Unable to load property file.");
        }
    }

    @Override
    public String get(String key) {
        if (properties == null) {
            throw new RuntimeException("Properties not loaded. Use `loadPropertyFile` first");
        }

        final String value = properties.getProperty(key);
        if (value == null)
            return null;
        try {
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported", e);
        }
    }

    public String get(String filename, String key) {
        loadPropertyFile(filename);
        return get(key);
    }
}
