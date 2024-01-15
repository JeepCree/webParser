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
        boolean bool = true;
        while(bool) {
            try {
                Document page = Jsoup.connect("https://all-spares.ua/ru/").get();
                System.out.println(page);
                Elements elements = page.select("div.menu-box__menu-item.menu-box__menu-item_catalog > div > div > ul > li.catalog__item");
                System.out.println(elements.size());
                for (Element element : elements) {
                    String name = element.select("a.catalog__link").text();
                    String link = element.select("a.catalog__link").attr("href");
                    System.out.println(name + " " + link);
                }
                bool = false;
            } catch (IOException e) {
                System.out.println("err");
                bool = true;
            }
        }

    }
}

