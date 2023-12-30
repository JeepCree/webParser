package ua.com.mobifix.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryParser {
    public List<AllScanCategory> getCatalog(ScanCategorySettings settings, Long lastCategoryId){
        lastCategoryId++;
        List<AllScanCategory> categoryList = new ArrayList<>();
        Map<String, String> cookies = settings.getCookies();
        if (cookies == null) {
            cookies.put("noName", "noValue");
        }
        try {
            Connection.Response response = Jsoup.connect(settings.getUrlShop()).method(Connection.Method.GET).execute();
            Document page = Jsoup.connect(settings.getUrlShop())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .cookies(cookies)
                    .get();
            try {
                Thread.sleep(1000);
                System.out.println("sleep page 1");
            } catch (Exception e){
                e.printStackTrace();
            }
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
                    category.setParentCategoryId(0L);
                    categoryList.add(category);
                    lastCategoryId++;
                    System.out.println(category.getCategoryId() + " " + category.getCategoryName() + " " + category.getCategoryUrl() + " " + category.getParentCategoryId());

                    try {
                        Document page2 = Jsoup.connect(link)
                                .cookies(cookies)
                                .get();
                        try {
                            Thread.sleep(1000);
                            System.out.println("sleep page 2");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        Elements elements2 = page2.select(settings.getSelectCategoryTagLevel2());
                        for (Element element2 : elements2) {
                            String name2 = element2.select(settings.getSelectCategoryNameTagLevel2()).text()
                                    .replace(settings.getReplaceCategoryNameLevel2(), settings.getReplacementCategoryNameLevel2());
                            String link2 = settings.getUrlPrefix() + element2.select(settings.getSelectCategoryNameTagLevel2()).attr(settings.getSelectCategoryAttrHrefLevel2())
                                    .replace(settings.getReplaceCategoryUrlLevel2(), settings.getReplacementCategoryUrlLevel2());
                            if (!link.isEmpty() || !name.isEmpty()) {
                                AllScanCategory category2 = new AllScanCategory();
                                category2.setCategoryId(lastCategoryId);
                                category2.setCategoryName(name2);
                                category2.setCategoryUrl(link2);
                                category2.setParentCategoryId(category.getCategoryId());
                                categoryList.add(category2);
                                lastCategoryId++;
                                System.out.println(category2.getCategoryId() + " " + category2.getCategoryName() + " " + category2.getCategoryUrl() + " " + category2.getParentCategoryId());

                                try {
                                    Document page3 = Jsoup.connect(link2)
                                            .cookies(cookies)
                                            .get();
                                    try {
                                        Thread.sleep(1000);
                                        System.out.println("sleep page 3");
                                    } catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    Elements elements3 = page3.select(settings.getSelectCategoryTagLevel3());
                                    for (Element element3 : elements3) {
                                        String name3 = element3.select(settings.getSelectCategoryNameTagLevel3()).text()
                                                .replace(settings.getReplaceCategoryNameLevel3(), settings.getReplacementCategoryNameLevel3());
                                        String link3 = settings.getUrlPrefix() + element3.select(settings.getSelectCategoryNameTagLevel3()).attr(settings.getSelectCategoryAttrHrefLevel3())
                                                .replace(settings.getReplaceCategoryUrlLevel3(), settings.getReplacementCategoryUrlLevel3());
                                        if (!link.isEmpty() || !name.isEmpty()) {
                                            AllScanCategory category3 = new AllScanCategory();
                                            category3.setCategoryId(lastCategoryId);
                                            category3.setCategoryName(name3);
                                            category3.setCategoryUrl(link3);
                                            category3.setParentCategoryId(category2.getCategoryId());
                                            categoryList.add(category3);
                                            lastCategoryId++;
                                            System.out.println(category3.getCategoryId() + " " + category3.getCategoryName() + " " + category3.getCategoryUrl() + " " + category3.getParentCategoryId());

                                            try {
                                                Document page4 = Jsoup.connect(link3)
                                                        .cookies(cookies)
                                                        .get();
                                                try {
                                                    Thread.sleep(1000);
                                                    System.out.println("sleep page 4");
                                                } catch (Exception e){
                                                    e.printStackTrace();
                                                }
                                                Elements elements4 = page4.select(settings.getSelectCategoryTagLevel4());
                                                for (Element element4 : elements4) {
                                                    String name4 = element4.select(settings.getSelectCategoryNameTagLevel4()).text()
                                                            .replace(settings.getReplaceCategoryNameLevel4(), settings.getReplacementCategoryNameLevel4());
                                                    String link4 = settings.getUrlPrefix() + element4.select(settings.getSelectCategoryNameTagLevel4()).attr(settings.getSelectCategoryAttrHrefLevel4())
                                                            .replace(settings.getReplaceCategoryUrlLevel4(), settings.getReplacementCategoryUrlLevel4());
                                                    if (!link.isEmpty() || !name.isEmpty()) {
                                                        AllScanCategory category4 = new AllScanCategory();
                                                        category4.setCategoryId(lastCategoryId);
                                                        category4.setCategoryName(name4);
                                                        category4.setCategoryUrl(link4);
                                                        category4.setParentCategoryId(category3.getCategoryId());
                                                        categoryList.add(category4);
                                                        lastCategoryId++;
                                                        System.out.println(category4.getCategoryId() + " " + category4.getCategoryName() + " " + category4.getCategoryUrl() + " " + category4.getParentCategoryId());

                                                        try {
                                                            Document page5 = Jsoup.connect(link4)
                                                                    .cookies(cookies)
                                                                    .get();
                                                            Elements elements5 = page5.select(settings.getSelectCategoryTagLevel5());
                                                            for (Element element5 : elements5) {
                                                                String name5 = element5.select(settings.getSelectCategoryNameTagLevel5()).text()
                                                                        .replace(settings.getReplaceCategoryNameLevel5(), settings.getReplacementCategoryNameLevel5());
                                                                String link5 = settings.getUrlPrefix() + element5.select(settings.getSelectCategoryNameTagLevel5()).attr(settings.getSelectCategoryAttrHrefLevel5())
                                                                        .replace(settings.getReplaceCategoryUrlLevel5(), settings.getReplacementCategoryUrlLevel5());
                                                                if (!link.isEmpty() || !name.isEmpty()) {
                                                                    AllScanCategory category5 = new AllScanCategory();
                                                                    category5.setCategoryId(lastCategoryId);
                                                                    category5.setCategoryName(name5);
                                                                    category5.setCategoryUrl(link5);
                                                                    category5.setParentCategoryId(category4.getCategoryId());
                                                                    categoryList.add(category5);
                                                                    lastCategoryId++;
                                                                    System.out.println(category5.getCategoryId() + " " + category5.getCategoryName() + " " + category5.getCategoryUrl() + " " + category5.getParentCategoryId());
                                                                }
                                                            }
                                                        } catch (Exception e){
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("\nКонец отработки модуля -> return categoryList\n");
        try (FileWriter writer = new FileWriter("C:\\Users\\dima2\\IdeaProjects\\webParser\\src\\main\\resources\\data\\" + settings.getShopName() + "_categories.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(categoryList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}

