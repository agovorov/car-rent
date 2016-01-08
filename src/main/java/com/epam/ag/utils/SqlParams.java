package com.epam.ag.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
public class SqlParams {
    public Object value;
    public String expr;
//    public String operation = "=";
    public List list;
    public Map<String, Double> between;

    // Use in SQL `IN` statement
    public SqlParams(List list) {
        this.list = list;
    }

    public SqlParams(Long value) {
        this.value = value;
    }

    public SqlParams(Object value, String expr) {
        this.value = value;
        this.expr = expr;
    }

    /**
     * "column" BETWEEN minValue AND maxValue
     * @param minValue
     * @param maxValue
     */
    public SqlParams(Double minValue, Double maxValue) {
        between = new HashMap<>();
        between.put("min", minValue);
        between.put("max", maxValue);
    }

    public SqlParams(String param) {
        value = param;
    }
}
