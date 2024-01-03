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
            int i = 1;
            boolean bool = true;
            String url = "https://ukr-mobil.com/zapchasti_dlya_telefonov_10000086/displei_10000117/xiaomi_10000147?page=";
            String select = "div.section.layout-box__catalog.catalog-line.test";
            while (bool){
                try {
                    String page = Jsoup.connect( url + i + "/")
                                .get()
                                .select(select).text();
                    System.out.println("Сканируем страницу " + i);
                    String newPage = Jsoup.connect(url + (i + 1) + "/")
                            .get()
                            .select(select).text();
                    bool = !page.equals(newPage);
//                    System.out.println(!page.equals(newPage) + " " + i);
                    i++;
                } catch (IOException e) {
                    if (e instanceof org.jsoup.HttpStatusException) {
                        org.jsoup.HttpStatusException httpStatusException = (org.jsoup.HttpStatusException) e;
                        int statusCode = httpStatusException.getStatusCode();
                        if (statusCode == 404) {
                            System.out.println("Страница не найдена (ошибка 404).");
                            bool = false; // Устанавливаем флаг в false, чтобы завершить цикл
                        } else {
                            System.out.println("Другая ошибка HTTP: " + statusCode);
                        }
                    } else {
                        System.out.println("Ошибка сканирования. Посторное сканирование страницы...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ex) {
                            System.out.println("ошибка Thread.sleep(1000);");
                        }
                    }
                }
            }
            System.out.println("Конец сканирования!");
    }
}

