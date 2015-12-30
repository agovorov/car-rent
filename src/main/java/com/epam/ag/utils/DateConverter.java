package com.epam.ag.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Govorov Andrey.
 */
public class DateConverter {

    private static final Logger log = LoggerFactory.getLogger(DateConverter.class);

    /**
     * Convert dates from GUI to Date format
     *
     * @param stringDate Date in dd.mm.yyyy format
     * @return Date object
     */
    public static Date strToDate(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            log.trace("Wrong date format: {} {}", date, stringDate);
            throw new RuntimeException("Wrong date to format", e);
        }
        return date;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }
}
