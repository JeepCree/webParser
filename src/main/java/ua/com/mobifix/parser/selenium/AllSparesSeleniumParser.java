package ua.com.mobifix.parser.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AllSparesSeleniumParser {
    public String parsePcs (String link) {
// Глушим devtools предупреждения
        LogManager.getLogManager().reset();
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.OFF);

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true"); // ← скроет хромдрайвер

// (если используешь SimpleLogger — попробуй глушить CDP)
        System.setProperty("org.slf4j.simpleLogger.log.org.openqa.selenium.devtools", "error");
        System.setProperty("org.slf4j.simpleLogger.log.org.openqa.selenium.chromium", "error");


        ChromeOptions options = new ChromeOptions();
        // Отключаем загрузку картинок, стилей, шрифтов, звука и прочего визуального мусора
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        prefs.put("profile.managed_default_content_settings.stylesheets", 2);
        prefs.put("profile.managed_default_content_settings.fonts", 2);
        prefs.put("profile.managed_default_content_settings.plugins", 2);
        prefs.put("profile.managed_default_content_settings.popups", 2);
        prefs.put("profile.managed_default_content_settings.geolocation", 2);
        prefs.put("profile.managed_default_content_settings.notifications", 2);
        prefs.put("profile.managed_default_content_settings.media_stream", 2);
//        prefs.put("profile.managed_default_content_settings.cookies", 2);
        prefs.put("profile.managed_default_content_settings.javascript", 1); // ⚠️ JS нужен — ставь 1
        options.setExperimentalOption("prefs", prefs);
        // Для отладки лучше убрать headless, чтобы видеть что происходит
        options.addArguments("--headless=new");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
// Отключить GPU и другие ресурсоёмкие штуки
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        String finalValue = null;
        try {
            driver.get(link);

            // 1. Ждем кнопку "В корзину" и кликаем
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/main/div[2]/div[1]/div/div[2]/section[1]/div/div/div/div[1]/div[2]/section/div[2]/div[1]/div[2]/button")
            ));
//            System.out.println("Нашли кнопку: " + addToCartBtn.getText());

            // Кликаем по кнопке через JS, чтобы избежать проблем с кликом
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

            // 2. Ждем, пока блок корзины перестанет иметь класс hidden
            wait.until(d -> {
                WebElement el = d.findElement(By.cssSelector("div.btn--in-cart"));
                String classAttr = el.getAttribute("class");
                return !classAttr.contains("hidden") ? el : null;
            });

            // 3. Находим видимый input с name='quantity'
            WebElement quantityInput = driver.findElements(By.cssSelector("input[name='quantity']"))
                    .stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Видимый input[name='quantity'] не найден"));

//            System.out.println("Найден видимый input quantity с value = " + quantityInput.getAttribute("value"));

            // 4. Вводим 10000
            quantityInput.clear();
            quantityInput.sendKeys("10000");

            // 5. Ждем, пока значение в input обновится (например, сервер изменит 10000 на другое число)
            wait.until(d -> {
                WebElement refreshedInput = d.findElements(By.cssSelector("input[name='quantity']"))
                        .stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Видимый input[name='quantity'] не найден во время ожидания"));
                String val = refreshedInput.getAttribute("value");
//                System.out.println("Ожидаем обновления. Сейчас: " + val);
                return !val.equals("10000") && !val.isEmpty();
            });

            // 6. Получаем и выводим окончательное значение
            WebElement finalInput = driver.findElements(By.cssSelector("input[name='quantity']"))
                    .stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Видимый input[name='quantity'] не найден в конце"));

            finalValue = finalInput.getAttribute("value");
//            System.out.println("⚡️ Сервер подтвердил количество: " + finalValue);

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return finalValue;
    }

}
