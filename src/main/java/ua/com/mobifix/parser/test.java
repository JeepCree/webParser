package ua.com.mobifix.parser;


import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Map<String, String> cookies = new HashMap<>();
        String cookiesString = "cookie1=Value1; cookie2=Value2; cookie3=Value3";
        String[] cookiePairs = cookiesString.split("; ");
        for (String cookiePair : cookiePairs) {
            String[] parts = cookiePair.split("=");
            if (parts.length == 2) {
                cookies.put(parts[0], parts[1]);
            }
        }
    }
}

