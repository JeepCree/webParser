package ua.com.mobifix.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SeleniumTableScraper {

    public static void main(String[] args) throws InterruptedException {

        // Отключаем лишние логи
        LogManager.getLogManager().reset();
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.OFF);

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true"); // прячет логи chromedriver

        // Отключаем ненужные вещи в Chrome
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        prefs.put("profile.managed_default_content_settings.stylesheets", 2);
        prefs.put("profile.managed_default_content_settings.fonts", 2);
        prefs.put("profile.managed_default_content_settings.plugins", 2);
        prefs.put("profile.managed_default_content_settings.popups", 2);
        prefs.put("profile.managed_default_content_settings.geolocation", 2);
        prefs.put("profile.managed_default_content_settings.notifications", 2);
        prefs.put("profile.managed_default_content_settings.media_stream", 2);
        prefs.put("profile.managed_default_content_settings.javascript", 1); // JS нужен
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");

        // Ресурсоёмкие штуки отключаем
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--mute-audio");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-sync");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-client-side-phishing-detection");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-hang-monitor");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--metrics-recording-only");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-features=TranslateUI,BlinkGenPropertyTrees");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        JSONArray resultArray = new JSONArray();

        try {
            driver.get("https://all-spares.ua/ru/accessories/portable-wireless-speakers/");

            // Находим все товары (артикул + кнопка)
            // Подкорректируй селектор, если нужно — здесь article — контейнер товара
            var products = driver.findElements(By.cssSelector("article"));

            for (WebElement product : products) {
                try {
                    WebElement addToCartBtn = product.findElement(By.cssSelector("button.btn--add-to-cart"));
                    String article = product.findElement(By.cssSelector("span.info-id span")).getText();

                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

                    // Ждем, пока корзина станет видимой
                    wait.until(d -> {
                        WebElement el = product.findElement(By.cssSelector("div.btn--in-cart"));
                        return !el.getAttribute("class").contains("hidden") ? el : null;
                    });

                    // Находим input заново, прямо перед вводом
                    WebElement quantityInput = product.findElements(By.cssSelector("input[name='quantity']"))
                            .stream()
                            .filter(WebElement::isDisplayed)
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Видимый input[name='quantity'] не найден"));

                    quantityInput.clear();
                    quantityInput.sendKeys("10000");

                    // Ждем обновления значения, каждый раз заново находим input
                    wait.until(d -> {
                        WebElement refreshedInput = product.findElements(By.cssSelector("input[name='quantity']"))
                                .stream()
                                .filter(WebElement::isDisplayed)
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Видимый input[name='quantity'] не найден во время ожидания"));
                        String val = refreshedInput.getAttribute("value");
                        return !val.equals("10000") && !val.isEmpty();
                    });

                    // Еще раз получаем финальное значение (чистый рефреш)
                    WebElement finalInput = product.findElements(By.cssSelector("input[name='quantity']"))
                            .stream()
                            .filter(WebElement::isDisplayed)
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Видимый input[name='quantity'] не найден в конце"));

                    String confirmedQuantity = finalInput.getAttribute("value");

                    JSONObject itemJson = new JSONObject();
                    itemJson.put("article", article);
                    itemJson.put("confirmedQuantity", confirmedQuantity);

                    resultArray.put(itemJson);

                    Thread.sleep(500);

                } catch (Exception e) {
                    System.err.println("Ошибка с товаром: " + e.getMessage());
                }
            }


        } finally {
            driver.quit();
        }

        // Выводим собранный JSON в консоль
        System.out.println(resultArray.toString(2));
    }
}
