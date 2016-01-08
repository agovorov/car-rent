package com.epam.ag.validator;

import com.epam.ag.propmanager.PropertiesManager;
import com.epam.ag.utils.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Govorov Andrey
 */
public class FormValidator {

    private static final Logger log = LoggerFactory.getLogger(FormValidator.class);
    private Properties validatorRules;
    private SystemMessage msg;
    private List<String> ignoreRules = new ArrayList<>();

    public FormValidator() {
        if (validatorRules == null) {
            // Load rules
            log.trace("Loading validator`s rules");
            loadRules();
        }
    }

    private void loadRules() {
        PropertiesManager.getInstance().loadPropertyFile("validator.properties");
        validatorRules = PropertiesManager.getInstance().getProperties();
    }

    public SystemMessage validateForm(String formName, HttpServletRequest req) {
        msg = new SystemMessage();
        formName = formName + ".";
        Enumeration<String> attributes = req.getParameterNames();
        while (attributes.hasMoreElements()) {
            String paramName = attributes.nextElement();
            String value = req.getParameter(paramName);

            log.trace("Validate parameter: {}{} => {}", formName, paramName, value);

            // Get rules for current parameter
            Map validateRules = getValidatorRules(formName + paramName);
            if (!validateRules.isEmpty()) {
                // We found rules for parameter
                // Check validation on each rule
                msg = validateFromFilteredMap(validateRules, formName, paramName, value);
            } else {
                log.trace("No rules for param {}", paramName);
            }
        }

        return msg;
    }


    /**
     * Validate form field by specified rule
     *
     * @param rulesList Map of object: form_field_name => validator_name (from validator.properties)
     * @param req       HttpServletRequest object
     * @return
     */
    public SystemMessage validateForm(Map<String, String> rulesList, HttpServletRequest req) {
        msg = new SystemMessage();
        for (Map.Entry<String, String> entry : rulesList.entrySet()) {
            String formFieldName = entry.getKey();
            String ruleName = entry.getValue();
            String value = req.getParameter(formFieldName);

            // Get form name
            // Little hack
            String formName = ruleName.replace(formFieldName, "");
            Map validateRules = getValidatorRules(ruleName);
            if (!validateRules.isEmpty()) {
                // We found rules for parameter
                // Check validation on each rule
                msg = validateFromFilteredMap(validateRules, formName, formFieldName, value);
            } else {
                log.trace("No rules for param {}", formFieldName);
            }
        }
        return msg;
    }

    public SystemMessage validateMap(Map<String, String> map) {
        msg = new SystemMessage();
        msg.setType(SystemMessage.ERROR);

        String formName = "vehicle.";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String paramName = entry.getKey();
            String value = entry.getValue();

            Map validateRules = getValidatorRules(formName + "." + paramName);
            if (validateRules == null) {
                continue;
            }

            // We found rules for attribute
            // Check validation on each rule
            msg = validateFromFilteredMap(validateRules, formName, paramName, value);
        }
        return msg;
    }

    private SystemMessage validateFromFilteredMap(Map validateRules, String formName, String paramName, String value) {
        Set keys = validateRules.keySet();
        String fullName = formName + paramName;
        for (Object key : keys) {
            String k = (String) key;
            String v = (String) validateRules.get(k);

            if (!ignoreRules.isEmpty() && ignoreRules.contains(k)) {
                log.trace("Ignore rule `{}`", k);
                continue;
            }

            String param = k.substring(fullName.length() + 1);
            log.trace("validate `{}` => `{}`", k, param);
            // Если осталось просто число, значит там указан класс
            if (param.matches("[0-9]{1,2}")) {
                String classParameter = fullName + "." + param + ".";
                Map paramsMap = getValidatorRules(classParameter);
                if (paramsMap == null || paramsMap.isEmpty()) {
                    continue;
                }

                // We found parameters for class
                Map<String, String> validatorParameter = new TreeMap<>();
                Set keysParam = paramsMap.keySet();
                for (Object aKeysParam : keysParam) {
                    String fullParamKey = (String) aKeysParam;
                    String paramKey = fullParamKey.substring(classParameter.length());
                    String paramValue = (String) paramsMap.get(fullParamKey);
                    validatorParameter.put(paramKey, paramValue);
                }

                // Нашли класс - создаем его инстанс
                Validator validator = createValidator(v, validatorParameter);
                String validationResult;

                if (validator.isValid(value)) {
                    validationResult = "valid";
                } else {
                    validationResult = "NOT valid";
                    msg.setType(SystemMessage.ERROR);
                    msg.addError(paramName, validator.getMessage());
                }
                log.trace("Param `{}` with value `{}` is {}. Cause: {}", paramName, value, validationResult, validator.getClass());
            }
        }
        return msg;
    }


    /**
     * Creating validator and setting parameters to it
     *
     * @param classPath Name of class
     * @param params    Map of parameters
     * @return Validator
     */
    private Validator createValidator(String classPath, Map params) {
        Class validatorClass;
        Validator validator;
        try {
            validatorClass = Class.forName(classPath);
            validator = (Validator) validatorClass.newInstance();
            log.trace("Class: {} ", validator);

            // If has parameters
            if (params != null) {
                Set keysParam = params.keySet();
                for (Object aKeysParam : keysParam) {
                    String param = (String) aKeysParam;
                    String value = (String) params.get(param);
                    log.trace("Set param `{}` = `{}` to validator.", param, value);

                    // TODO Reflection for find methods...
                    // тут пока очень стремный подход, но уже много времени потратил...
                    switch (param) {
                        case "min":
                            ((LengthValidator) validator).setMin(Integer.parseInt(value));
                            break;

                        case "max":
                            ((LengthValidator) validator).setMax(Integer.parseInt(value));
                            break;

                        case "regex":
                            ((RegexValidator) validator).setRegex(value);
                            break;

                        case "message":
                            validator.setMessage(value);
                            break;

                    }
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("Unable to load instance.", e);
            throw new RuntimeException("Unable to load validator instance. Validator class not found.", e);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Unable to create instance.", e);
            throw new RuntimeException("Unable to create validator instance.", e);
        }
        return validator;
    }

    /**
     * Return all rules for specified parameter
     *
     * @param paramName Form name + parameter name
     * @return Map of rules
     */
    private Map getValidatorRules(String paramName) {
        Map<String, String> validatorMap = new TreeMap<>();
        for (Map.Entry<?, ?> entry : validatorRules.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            // Check if key is ok
            if (key.startsWith(paramName)) {
                validatorMap.put(key, value);
            }
        }
        return validatorMap;
    }

    private ValidatorResult isValid(String validatorKey, String value) {
        // Validate field here
        String validatorRule = validatorRules.getProperty(validatorKey);
        if (validatorRule != null) {
            log.trace("Validate `{}` with rule `{}`", validatorKey, validatorRule);
        }

        return new ValidatorResult();
    }

    /**
     * Ignore rules for validating
     *
     * @param rules
     */
    public void ignoreRules(List<String> rules) {
        ignoreRules.addAll(rules);
    }

    private class ValidatorResult {
        private boolean isValid;
        private String message;

        public boolean isValid() {
            return isValid;
        }

        public String getMessage() {
            return message;
        }
    }
}