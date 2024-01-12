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
    public static void main(String[] args) throws IOException {
        Document  page = Jsoup.connect("https://vseplus.com/product/radiodetali-mikroshemy").get();
        Elements elementsName = page.select("#middle > aside > nav > section.sidebar__item.sidebar__item_root-preview > ul > li > div > ul > li");
        System.out.println(elementsName.size());
        for (Element el : elementsName){
            String name = el.select("a").text();
            String link = el.select("a").attr("href");
            System.out.println(name + " " + link);
        }

    }
}

