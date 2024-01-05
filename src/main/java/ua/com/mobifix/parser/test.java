package ua.com.mobifix.parser;

import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://www.aks.ua/uk/catalog/mobilnye-telefony/page/2").get();
            System.out.println(doc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

