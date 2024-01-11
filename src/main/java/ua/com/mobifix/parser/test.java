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
        try {
            Document page = Jsoup.connect("https://hotline.ua/ru/").get();
            System.out.println(page.select("a.link--black").attr("href"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

