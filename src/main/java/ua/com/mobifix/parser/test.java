package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
//        String loginUrl = "https://rozetka.com.ua/";
//        String username = "dima.26.2013@gmail.com";
//        String password = "3193921855";

        String loginUrl = "https://all-spares.ua/ru/lcd-iphone-x-black-with-touchscreen-amoled-high-copy/";
        String username = "dima.26.2013@gmail.com";
        String password = "3193921855";
        Map<String, String> cookies = new HashMap<>();
        cookies.put("visitor", "9ea6fa5e03088598d37dce644143026b");
        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJS2kG7TsZAcPjrxuQPbIXv3j5i8SNvqyXj9vzG6wxqSYjsWTIcIO%2BpGVo9j2fiW%2BaQ");
        cookies.put("language", "5");
        try {
            Connection.Response loginResponse = Jsoup.connect(loginUrl)
                    .data("username", username)
                    .data("password", password)
                    .method(Connection.Method.GET)
                    .execute();

            Document page = Jsoup.connect(loginUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .cookies(cookies)
                    .get();

            String elements = page.select("body > div:nth-child(2) > div > div.sop-main-content > div > main > div > div:nth-child(4) > div.col-12.col-md-6.col-xxl-7 > div > div.row.d-block.d-md-flex.mb25.mb-md-0 > div:nth-child(1) > div.prp_static-button.action-zone.acz.-main > div > div:nth-child(1) > div > div").text();
            System.out.println(elements);
            // Вывод кук в консоль
            System.out.println("Cookies: " + cookies);

            // Здесь вы можете использовать полученные куки для последующих запросов
            // ...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
