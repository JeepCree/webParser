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
                Document page = Jsoup.connect("https://vseplus.com/product/radiodetali-mikroshemy").get();
//                System.out.println(page);
                Elements elements = page.select("section.sidebar__item.sidebar__item_root-preview > ul > li > div > ul > li");
                System.out.println(elements.size());
                for (Element element : elements) {
                    String name = element.select("a").text();
                    String link = element.select("a").attr("href");
                    System.out.println(name + " " + link);
                }
                bool = false;
            } catch (IOException e) {
                System.out.println("err");
                bool = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
}

