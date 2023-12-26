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
    public void getCatalog(String[] array){
        Map<String, String> catalogMap = new HashMap<>();
        try {
            Connection.Response response = Jsoup.connect(array[0]).method(Connection.Method.GET).execute();
            Document page = Jsoup.connect(array[0] + array[1])
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
//                    .cookies(response.cookies())
                    .cookie(array[6], array[7])
                    .get();
//            Elements elements = page.getElementsByClass(array[2]);
            Elements elements = page.select(array[2]);
            System.out.println(elements.size());

            for (Element element : elements) {
                String text = element.select(array[3]).text().replace(array[8], array[9]);
                String linked = element.select(array[3]).attr(array[4]);
                catalogMap.put(text, array[5] + linked);
            }
            for (Map.Entry<String, String> entry : catalogMap.entrySet()) {
                    System.out.println(entry.getKey() + " | " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getCategory(){

    }
}

