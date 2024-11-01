package ua.com.mobifix.parser.breadcrumbs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BreadcrumbsParser {

    private final Gson gson = new Gson();

    public String parseHtmlToJson(String html) {
        // Парсим HTML в объект Document
        Document document = Jsoup.parse(html);

        // Создаем JSON-объект для хранения ссылок с названиями категорий
        JsonObject jsonObject = new JsonObject();

        // Ищем контейнеры для breadcrumbs - <ol> или <ul>
        Elements breadcrumbContainers = document.select("ol, ul");

        for (Element container : breadcrumbContainers) {
            // Проверяем, что контейнер имеет класс breadcrumb или breadcrumbs
            if (container.hasClass("breadcrumb") || container.hasClass("breadcrumbs") || container.tagName().equals("ol")) {
                // Находим все элементы <li> внутри контейнера
                Elements listItems = container.select("li");

                for (Element item : listItems) {
                    Element linkElement = item.selectFirst("a");
                    String categoryName;
                    String link;

                    // Если есть <a>, извлекаем название и ссылку
                    if (linkElement != null) {
                        categoryName = linkElement.text().trim();
                        link = linkElement.attr("href");
                    } else {
                        // Если <a> отсутствует, берем текст непосредственно из <li>
                        categoryName = item.text().trim();
                        link = null; // Пропускаем элемент, если нет ссылки
                    }

                    // Добавляем в JSON-объект, если ссылка присутствует
                    if (link != null) {
                        jsonObject.addProperty(categoryName, link);
                    }
                }
                // Прерываем цикл после первого найденного контейнера breadcrumb
                break;
            }
        }

        // Возвращаем JSON-строку
        return gson.toJson(jsonObject);
    }
}
