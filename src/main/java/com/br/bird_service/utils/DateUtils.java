package com.br.bird_service.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String convertToISOString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return dateTime.format(formatter);
    }
}
