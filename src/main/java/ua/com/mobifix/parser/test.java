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
        // Указываем путь к ChromeDriver (замените путь на ваш)
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");

        // Создаем экземпляр WebDriver (в данном случае - ChromeDriver)
        WebDriver driver = new ChromeDriver();

        try {
            // Открываем веб-страницу
            driver.get("https://www.example.com");


            // Получаем HTML-код страницы после выполнения JavaScript
            String pageSource = driver.getPageSource();

            // Выводим HTML-код страницы
            System.out.println(pageSource);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрываем браузер
            driver.quit();
        }
    }
}

