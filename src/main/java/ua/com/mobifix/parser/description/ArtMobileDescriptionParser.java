package ua.com.mobifix.parser.description;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ArtMobileDescriptionParser {
    public JsonObject parseHtmlToJson(String html) {

        // Парсинг HTML
        Document doc = Jsoup.parse(html);
        Elements items = doc.select(".characteristics-item");

        // Создание JSON объекта
        JsonObject jsonObject = new JsonObject();
        for (Element item : items) {
            String title = item.selectFirst(".characteristics-title").text();
            Elements values = item.select(".characteristics-value > div, .characteristics-value > span > div");

            // Создание массива значений для текущего заголовка
            JsonArray valueArray = new JsonArray();
            for (Element value : values) {
                valueArray.add(value.text());
            }

            // Добавление заголовка и его значений в JSON объект
            jsonObject.add(title, valueArray);
        }

        // Вывод JSON
//        System.out.println(jsonObject.toString());
        return jsonObject;
    }
}
