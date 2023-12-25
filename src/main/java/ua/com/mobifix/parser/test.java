package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String loginUrl = "https://rozetka.com.ua/";
        String username = "dima.26.2013@gmail.com";
        String password = "3193921855";

//        String loginUrl = "https://all-spares.ua/ru";
//        String username = "dima.26.2013@gmail.com";
//        String password = "3193921855";

        try {
            // Отправка POST-запроса для входа
            Connection.Response loginResponse = Jsoup.connect(loginUrl)
                    .data("username", username)
                    .data("password", password)
                    .method(Connection.Method.GET)
                    .execute();

            // Получение куков после входа
            Map<String, String> cookies = loginResponse.cookies();
            // Вывод куков
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Пример: отправка GET-запроса после входа
            String profileUrl = "https://artmobile.ua/users";
//            String profileUrl = "https://all-spares.ua/ru/lcd-compatible-with-xiaomi-redmi-9a-black-with-touchscreen-original-prc-m2006c3lg/";
            Document profilePage = Jsoup.connect(profileUrl)
                    .method(Connection.Method.POST)
                    .cookies(cookies)
                    .get();
            System.out.println(profilePage.select(".price"));

            // Дальнейшие действия с профилем...

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
