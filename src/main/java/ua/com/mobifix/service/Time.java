package ua.com.mobifix.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class  Time {
    public static String getTime(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
