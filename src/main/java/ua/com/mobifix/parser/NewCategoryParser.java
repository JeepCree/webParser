package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class NewCategoryParser {

    private static final Logger logger = LoggerFactory.getLogger(CategoryParser.class);

    public List<AllScanCategory> getCatalog(ScanCategorySettings settings, Long lastCategoryId) {
        lastCategoryId++;
        List<AllScanCategory> categoryList = new ArrayList<>();

        if (settings.getCookieName().isEmpty() || settings.getCookieValue().isEmpty()) {
            settings.setCookieName("noName");
            settings.setCookieValue("noValue");
        }

        try {
            Connection.Response response = Jsoup.connect(settings.getUrlShop()).method(Connection.Method.GET).execute();
            Document page = Jsoup.connect(settings.getUrlShop())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .cookie(settings.getCookieName(), settings.getCookieValue())
                    .get();

            processCategoryLevel(settings, page, categoryList, lastCategoryId, 0L);
        } catch (IOException e) {
            logger.error("Error while getting catalog", e);
        }

        System.out.println("\nКонец отработки модуля -> return categoryList\n");
        return categoryList;
    }

    private void processCategoryLevel(ScanCategorySettings settings, Document page, List<AllScanCategory> categoryList, Long lastCategoryId, Long parentCategoryId) {
        try {
            Elements elements = page.select(settings.getSelectCategoryTag());
            for (Element element : elements) {
                String name = element.select(settings.getSelectCategoryNameTag()).text()
                        .replace(settings.getReplaceCategoryName(), settings.getReplacementCategoryName());
                String link = settings.getUrlPrefix() + element.select(settings.getSelectCategoryNameTag()).attr(settings.getSelectCategoryAttrHref())
                        .replace(settings.getReplaceCategoryUrl(), settings.getReplacementCategoryUrl());

                if (!link.isEmpty() || !name.isEmpty()) {
                    AllScanCategory category = new AllScanCategory();
                    category.setCategoryId(lastCategoryId);
                    category.setCategoryName(name);
                    category.setCategoryUrl(link);
                    category.setParentCategoryId(parentCategoryId);
                    categoryList.add(category);
                    lastCategoryId++;
                    System.out.println(category.getCategoryId() + " " + category.getCategoryName() + " " + category.getCategoryUrl() + " " + category.getParentCategoryId());

                    try {
                        Document nextPage = Jsoup.connect(link).get();
                        processCategoryLevel(settings, nextPage, categoryList, lastCategoryId, category.getCategoryId());
                    } catch (Exception e) {
                        logger.error("Error while processing category level", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while processing category level", e);
        }
    }
}