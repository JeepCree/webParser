package ua.com.mobifix.test;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class Test {

    public static void main(String[] args) throws IOException {
        Document page = Jsoup.connect("https://artmobile.ua/ru/silikonovyjj-chekhol-dlja-iphone-12-12-pro-apple-silicone-case-with-magsafe-capri-blue-2021-105")
//                .proxy("223.25.109.170", 8199)
//                                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("45.67.215.199", 80)))

                .get();

        // Сохраняем в файл index.html
        try (FileWriter writer = new FileWriter("C:\\Users\\dima2\\OneDrive\\Рабочий стол\\index.html")) {
            writer.write(page.outerHtml());
        }
        System.out.println(page.html());
//        System.out.println("HTML сохранён в index.html");
// Укажи путь к chromedriver, если не добавлен в системный PATH
//        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Настройки headless-режима (можно убрать если хочешь с окном)
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
//        options.addArguments("--disable-gpu");
//        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
//
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.get("https://artmobile.ua/");
//
//        System.out.println("✅ Загружено: " + driver.getTitle());
//
//        // При необходимости можно получить HTML
//        String pageSource = driver.getPageSource();
//        System.out.println("HTML: " + (pageSource.length() > 500 ? pageSource.substring(0, 500) : pageSource));// первые 500 символов
//
//        driver.quit();
    }
}
