package ua.com.mobifix.parser.breadcrumbs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;
import java.util.Map;

public class BreadcrumbsToCatalogParser {
    // Основной метод для создания каталога
    public static Map<String, Object> buildCatalog(String[] breadcrumbsData) {
        Map<String, Object> catalog = new HashMap<>();

        for (String breadcrumbsJson : breadcrumbsData) {
            JsonObject breadcrumbObj = JsonParser.parseString(breadcrumbsJson).getAsJsonObject();
            Map<String, Object> currentLevel = catalog;

            for (Map.Entry<String, JsonElement> entry : breadcrumbObj.entrySet()) {
                String name = entry.getKey();
                String url = entry.getValue().getAsString();

                // Проверка и обновление уровня каталога
                if (!currentLevel.containsKey(name)) {
                    Map<String, Object> newCategory = new HashMap<>();
                    newCategory.put("url", new JsonPrimitive(url));
                    newCategory.put("subcategories", new HashMap<String, Object>());
                    currentLevel.put(name, newCategory);
                }

                // Переход на уровень вложенных категорий
                currentLevel = (Map<String, Object>) ((Map<String, Object>) currentLevel.get(name)).get("subcategories");
            }
        }
        return catalog;
    }
}
