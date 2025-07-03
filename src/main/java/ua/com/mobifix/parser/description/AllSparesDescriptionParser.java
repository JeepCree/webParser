package ua.com.mobifix.parser.description;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSparesDescriptionParser {
    private final Gson gson = new Gson();

    public String parseHtmlToJson(String html) {
        // Парсим HTML в объект Document
        Document document = Jsoup.parse(html);

        // Карта для хранения данных в формате "Заголовок -> Список элементов"
        Map<String, List<String>> dataMap = new HashMap<>();

        // Выбираем все элементы <p class="head-text"> и соответствующие <ul> после них
        Elements headers = document.select("p.head-text");

        for (Element header : headers) {
            // Получаем текст из <p class="head-text">
            String headerText = header.text();

            // Находим следующий элемент <ul>
            Element ulElement = header.nextElementSibling();

            if (ulElement != null && ulElement.tagName().equals("ul")) {
                // Сохраняем все <li> внутри <ul> в список
                List<String> items = new ArrayList<>();
                for (Element li : ulElement.select("li")) {
                    items.add(li.text());
                }

                // Добавляем заголовок и список элементов в карту
                dataMap.put(headerText, items);
            }
        }

        // Преобразуем карту в JSON-объект
        JsonObject jsonObject = gson.toJsonTree(dataMap).getAsJsonObject();
        // Возвращаем JSON-строку
        return gson.toJson(jsonObject);
    }
}
