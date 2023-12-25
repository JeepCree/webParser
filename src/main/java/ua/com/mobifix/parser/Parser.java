package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public void getCatalog(String urlShop, String endPoint, String getByClass, String select, String href, boolean needLink){
        Map<String, String> catalogMap = new HashMap<>();

        try {
            Connection.Response response = Jsoup.connect(urlShop).method(Connection.Method.GET).execute();
            System.out.println(response.body());

            Document page = Jsoup.connect(urlShop + endPoint)
//                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .cookies(response.cookies())
                    .get();

            Elements elements = page.getElementsByClass(getByClass);
// Создание карты для хранения данных

            // Обход элементов и добавление данных в карту
            for (Element element : elements) {
                String text = element.select(select).text();
                String link = element.attr(href);
                catalogMap.put(text, link);
            }
            if (needLink == false) {
                urlShop = "";
            }
            // Вывод карты
            for (Map.Entry<String, String> entry : catalogMap.entrySet()) {
//                System.out.println(entry.getKey() + " | " + urlShop + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

