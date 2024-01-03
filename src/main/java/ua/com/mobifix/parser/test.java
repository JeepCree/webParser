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

            String page = "0";
            String newPage = "1";
            int i = 1;
            boolean bool = true;
            while (bool){
                try {
                        page = Jsoup.connect("https://all-spares.ua/uk/accessories/chargers/mains-chargers/?ipp=96&page=" + i)
                                .get()
                                .select("div.row.d-flex.product-cards-wrapper > div > div").text();

                        newPage = Jsoup.connect("https://all-spares.ua/uk/accessories/chargers/mains-chargers/?ipp=96&page=" + (i + 1))
                            .get()
                            .select("div.row.d-flex.product-cards-wrapper > div > div").text();
                        bool = !page.equals(newPage);
                    System.out.println(!page.equals(newPage) + " " + i);
                    i++;
                } catch (IOException e) {
                    System.out.println("ошибка Jsoup.connect...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        System.out.println("ошибка Thread.sleep(1000);");
                    }
                }
            }
            System.out.println("Конец сканирования!");


    }
}

