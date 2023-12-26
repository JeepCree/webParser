package ua.com.mobifix.parser;

import org.apache.catalina.filters.RemoteIpFilter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public void getCatalog(ScanCategorySettings settings, Long lastCategoryId){
        lastCategoryId++;
        List<AllScanCategory> categoryList = new ArrayList<>();

        if (settings.getCookieName().equals("") || settings.getCookieValue().equals("")){
            settings.setCookieName("noName");
            settings.setCookieValue("noValue");
        }

        try {
            Connection.Response response = Jsoup.connect(settings.getUrlShop()).method(Connection.Method.GET).execute();
            Document page = Jsoup.connect(settings.getUrlShop())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .cookie(settings.getCookieName(), settings.getCookieValue())
                    .get();
            Elements elements = page.select(settings.getSelectCategoryTag());

            for (Element element : elements) {

                String name = element.select(settings.getSelectCategoryNameTag()).text()
                        .replace(settings.getReplaceCategoryName(), settings.getReplacementCategoryName());
                String link = element.select(settings.getSelectCategoryNameTag()).attr(settings.getSelectCategoryAttrHref())
                        .replace(settings.getReplaceCategoryUrl(), settings.getReplacementCategoryUrl());
                if (!link.isEmpty() || !name.isEmpty()) {
                    AllScanCategory category = new AllScanCategory();
                    category.setCategoryId(lastCategoryId);
                    lastCategoryId++;
                    category.setCategoryName(name);
                    category.setCategoryUrl(settings.getUrlPrefix() + link);
                    category.setParentCategoryId(0L);
                    categoryList.add(category);
                }
            }

            for (AllScanCategory l : categoryList){
                System.out.println(l.getCategoryId() + " " + l.getCategoryName() + " " + l.getCategoryUrl() + " " + l.getParentCategoryId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

