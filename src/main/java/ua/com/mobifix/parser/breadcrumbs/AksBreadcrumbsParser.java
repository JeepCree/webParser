package ua.com.mobifix.parser.breadcrumbs;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AksBreadcrumbsParser {

    private final Gson gson = new Gson();

    public String parseHtmlToJson(String html) {
        // Парсим HTML в объект Document
        Document document = Jsoup.parse(html);

        // Создаем JSON-объект для хранения ссылок с названиями категорий
        JsonObject jsonObject = new JsonObject();

        // Ищем все элементы <li itemprop="itemListElement"> внутри <ol class="breadcrumbs">
        Elements listItems = document.select("ol.breadcrumbs > li[itemprop=itemListElement]");

        for (Element item : listItems) {
            Element linkElement = item.selectFirst("a[itemprop=item]");
            Element nameElement = item.selectFirst("span[itemprop=name]");

            // Пропускаем элементы без ссылки <a>
            if (linkElement != null && nameElement != null) {
                // Получаем текст категории
                String categoryName = nameElement.text().trim();
                // Получаем href в качестве ссылки
                String link = linkElement.attr("href");

                // Добавляем в JSON-объект
                jsonObject.addProperty(categoryName, link);
            }
        }

        // Возвращаем JSON-строку
        return gson.toJson(jsonObject);
    }
}
