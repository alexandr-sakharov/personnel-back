package ru.personnel.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String REQUEST_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";
    public static final String REQUEST_DATE_TIME = "dd.MM.yyyy HH:mm";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String REQUEST_DATE_TIME_FORMAT_SECONDS = "dd.MM.yyyy HH:mm:ss:SSS";
    public static final String REQUEST_DATE_TIME_FORMAT_SECONDS_MILLS = "dd.MM.yyyy HH:mm:ss";

    // Получение из даты определенный формат строки даты
    public static String formatRequestDate(LocalDateTime date, String format){
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    // Получение из формата строки даты дату
    public static LocalDateTime getDateTime(String dateValue, String format){
        return LocalDate.parse(dateValue, DateTimeFormatter.ofPattern(format)).atStartOfDay();
    }
}
