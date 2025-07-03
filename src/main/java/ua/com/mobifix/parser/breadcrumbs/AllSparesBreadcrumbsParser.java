package ua.com.mobifix.parser.breadcrumbs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.mobifix.entity.Shop;

public class AllSparesBreadcrumbsParser {
    private final Gson gson = new Gson();

    public String parseHtmlToJson(String html, Shop settings) {
        // Парсим HTML в объект Document
        Document document = Jsoup.parse(html);

        // Создаем JSON-объект для хранения ссылок с названиями категорий
        JsonObject jsonObject = new JsonObject();

        // Ищем все элементы <li> внутри <ol>
        Elements listItems = document.select("ol > li");

        for (Element item : listItems) {
            Element linkElement = item.selectFirst("a");

            // Пропускаем элементы без ссылки <a>
            if (linkElement != null) {
                // Получаем атрибут title в качестве названия категории
                String categoryName = linkElement.attr("title");
                // Получаем href в качестве ссылки
                String link = settings.getUrlCategoryPrefix() + linkElement.attr("href");

                // Добавляем в JSON-объект
                jsonObject.addProperty(categoryName, link);
            }
        }

        // Возвращаем JSON-строку
        return gson.toJson(jsonObject);
    }
}
