package ua.com.mobifix.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class  Time {
    public static String getTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
}
