package com.epam.ag.utils.ajax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Fabric class. Build
 *
 * @author Govorov Andrey
 */
public abstract class AjaxReturnFactory {

    private static final Logger log = LoggerFactory.getLogger(AjaxReturnFactory.class);

    private AjaxReturnFactory() {

    }

    /**
     * Create object of given class
     *
     * @param list
     * @param clazz
     * @return
     */
    public static String getList(List list, Class clazz) {
        String returnClass = clazz.getName();
        String result = null;
        try {
            Class c = Class.forName(returnClass);
            AjaxReturn ajaxReturn = (AjaxReturn) c.newInstance();
            result = ajaxReturn.build(list);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Unable to create ajax return object {}", returnClass, e);
            throw new RuntimeException("Unable to create ajax return object", e);
        }

        return result;
    }
}
