package ua.com.mobifix.parser.breadcrumbs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BreadcrumbsParserTest {

    private final BreadcrumbsParser parser = new BreadcrumbsParser();

    @Test
    void testParseHtmlWithLinks() {
        // HTML с правильными ссылками
        String html = """
            <ol>
                <li><a href="/ru/" title="Главная">Главная</a></li>
                <li><a href="/ru/spares/" title="Запчасти">Запчасти</a></li>
                <li><a href="/ru/spares/housings-and-accessories/" title="Корпусные части">Корпусные части</a></li>
                <li><a href="/ru/spares/housings-and-accessories/outer-housing-parts/" title="Внешние корпусные части">Внешние корпусные части</a></li>
                <li><a href="/ru/spares/housings-and-accessories/outer-housing-parts/housing-back-covers/" title="Задние панели корпуса">Задние панели корпуса</a></li>
            </ol>
        """;

        // Ожидаемый результат
        JsonObject expectedJson = new JsonObject();
        expectedJson.addProperty("Главная", "/ru/");
        expectedJson.addProperty("Запчасти", "/ru/spares/");
        expectedJson.addProperty("Корпусные части", "/ru/spares/housings-and-accessories/");
        expectedJson.addProperty("Внешние корпусные части", "/ru/spares/housings-and-accessories/outer-housing-parts/");
        expectedJson.addProperty("Задние панели корпуса", "/ru/spares/housings-and-accessories/outer-housing-parts/housing-back-covers/");

        // Результат парсера
        String jsonResult = parser.parseHtmlToJson(html);

        // Проверяем, что результат соответствует ожидаемому
        assertEquals(JsonParser.parseString(expectedJson.toString()), JsonParser.parseString(jsonResult));
    }

    @Test
    void testParseHtmlWithInactiveItems() {
        // HTML с элементом без ссылки
        String html = """
            <ol>
                <li><a href="/ru/" title="Главная">Главная</a></li>
                <li><a href="/ru/spares/" title="Запчасти">Запчасти</a></li>
                <li><a href="/ru/spares/housings-and-accessories/" title="Корпусные части">Корпусные части</a></li>
                <li class="active">Задняя панель для Xiaomi</li>
            </ol>
        """;

        // Ожидаемый результат
        JsonObject expectedJson = new JsonObject();
        expectedJson.addProperty("Главная", "/ru/");
        expectedJson.addProperty("Запчасти", "/ru/spares/");
        expectedJson.addProperty("Корпусные части", "/ru/spares/housings-and-accessories/");

        // Результат парсера
        String jsonResult = parser.parseHtmlToJson(html);

        // Проверяем, что результат соответствует ожидаемому
        assertEquals(JsonParser.parseString(expectedJson.toString()), JsonParser.parseString(jsonResult));
    }

    @Test
    void testParseEmptyHtml() {
        // Пустой HTML
        String html = "<ol></ol>";

        // Ожидаемый результат — пустой JSON
        JsonObject expectedJson = new JsonObject();

        // Результат парсера
        String jsonResult = parser.parseHtmlToJson(html);

        // Проверяем, что результат соответствует ожидаемому
        assertEquals(JsonParser.parseString(expectedJson.toString()), JsonParser.parseString(jsonResult));
    }

    @Test
    void testParseHtmlWithNestedStructure() {
        // HTML с вложенной структурой
        String html = """
            <div>
                <ol class="breadcrumbs">
                    <li><a href="/home" title="Home">Home</a></li>
                    <li><a href="/products" title="Products">Products</a></li>
                    <li><a href="/products/electronics" title="Electronics">Electronics</a></li>
                    <li>Final Product</li>
                </ol>
            </div>
        """;

        // Ожидаемый результат
        JsonObject expectedJson = new JsonObject();
        expectedJson.addProperty("Home", "/home");
        expectedJson.addProperty("Products", "/products");
        expectedJson.addProperty("Electronics", "/products/electronics");

        // Результат парсера
        String jsonResult = parser.parseHtmlToJson(html);

        // Проверяем, что результат соответствует ожидаемому
        assertEquals(JsonParser.parseString(expectedJson.toString()), JsonParser.parseString(jsonResult));
    }
}
