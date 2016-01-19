package com.epam.ag.utils.ajax;

import com.google.gson.Gson;

import java.util.List;

/**
 * Return list as JSON object
 *
 * @author Govorov Andrey
 */
public class JsonAjaxReturn implements AjaxReturn {
    @Override
    public String build(List list) {
        String json = new Gson().toJson(list);
        return json;
    }
}
