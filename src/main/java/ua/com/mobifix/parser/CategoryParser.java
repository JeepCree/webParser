package ua.com.mobifix.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.Shop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;

public class CategoryParser {
    public List<Categories> getCatalog(Shop settings, Long lastCategoryId){
        lastCategoryId++;
        List<Categories> categoryList = new ArrayList<>();
        Map<String, String> cookies = new HashMap<>();
        String[] cookiePairs = settings.getCookies().split("; ");
        for (String cookiePair : cookiePairs) {
            String[] parts = cookiePair.split("=");
            if (parts.length == 2) {
                cookies.put(parts[0], parts[1]);
            }
        }
        if (cookies == null) {
            cookies.put("noName", "noValue");
        }
        System.out.println(cookies);
        try {
            Connection.Response response = Jsoup.connect(settings.getLinkShop()).method(Connection.Method.GET).execute();
            Document page = Jsoup.connect(settings.getLinkShop())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .cookies(cookies)
                    .get();
            try {
                Thread.sleep(1000);
                System.out.println("sleep page 1");
            } catch (Exception e){
                e.printStackTrace();
            }
            ArrayList<String> list = new ArrayList<>();
            Elements elements = page.select(settings.getSelectCategoryTag());
            for (Element element : elements) {
                String name = element.select(settings.getSelectCategoryNameTag()).text()
                        .replace(settings.getReplaceCategoryName(), settings.getReplacementCategoryName()).trim();
                boolean bool1 = false;
                for(String string : settings.getNoScanList()){
                    if (name.equals(string)){
                        bool1 = true;
                    }
                }
                if(bool1 == true){
                    continue;
                }
                String link = (settings.getUrlPrefix() + element.select(settings.getSelectCategoryNameTag()).attr(settings.getSelectCategoryAttrHref()))
                        .replace(settings.getReplaceCategoryUrl(), settings.getReplacementCategoryUrl()).trim();
                if (!link.isEmpty() || !name.isEmpty()) {
                    Categories category = new Categories();
                    category.setId(lastCategoryId);
                    category.setName(name);
                    category.setUrl(link);
                    category.setParentId(0L);
                    category.setShopId(settings.getShopId());
                    categoryList.add(category);
                    lastCategoryId++;
                    System.out.println(category.getId() + " " + category.getName() + " " + category.getUrl() + " " + category.getParentId());

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
                                    .replace(settings.getReplaceCategoryNameLevel2(), settings.getReplacementCategoryNameLevel2()).trim();
                            boolean bool2 = false;
                            for(String string : settings.getNoScanList()){
                                if (name2.equals(string)){
                                    bool2 = true;
                                }
                            }
                            if(bool2 == true){
                                continue;
                            }
                            String link2 = (settings.getUrlPrefixLevel2() + element2.select(settings.getSelectCategoryNameTagLevel2()).attr(settings.getSelectCategoryAttrHrefLevel2()))
                                    .replace(settings.getReplaceCategoryUrlLevel2(), settings.getReplacementCategoryUrlLevel2()).trim();
                            if (!link2.isEmpty() || !name2.isEmpty()) {
                                Categories category2 = new Categories();
                                category2.setId(lastCategoryId);
                                category2.setName(name2);
                                category2.setUrl(link2);
                                category2.setParentId(category.getId());
                                category2.setShopId(settings.getShopId());
                                categoryList.add(category2);
                                lastCategoryId++;
                                System.out.println(category2.getId() + " " + category2.getName() + " " + category2.getUrl() + " " + category2.getParentId());

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
                                                .replace(settings.getReplaceCategoryNameLevel3(), settings.getReplacementCategoryNameLevel3()).trim();
                                        boolean bool3 = false;
                                        for(String string : settings.getNoScanList()){
                                            if (name3.equals(string)){
                                                bool3 = true;
                                            }
                                        }
                                        if(bool3 == true){
                                            continue;
                                        }
                                        String link3 = (settings.getUrlPrefixLevel3() + element3.select(settings.getSelectCategoryNameTagLevel3()).attr(settings.getSelectCategoryAttrHrefLevel3()))
                                                .replace(settings.getReplaceCategoryUrlLevel3(), settings.getReplacementCategoryUrlLevel3()).trim();
                                        if (!link3.isEmpty() || !name3.isEmpty()) {
                                            Categories category3 = new Categories();
                                            category3.setId(lastCategoryId);
                                            category3.setName(name3);
                                            category3.setUrl(link3);
                                            category3.setParentId(category2.getId());
                                            category3.setShopId(settings.getShopId());
                                            categoryList.add(category3);
                                            lastCategoryId++;
                                            System.out.println(category3.getId() + " " + category3.getName() + " " + category3.getUrl() + " " + category3.getParentId());

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
                                                            .replace(settings.getReplaceCategoryNameLevel4(), settings.getReplacementCategoryNameLevel4()).trim();
                                                    boolean bool4 = false;
                                                    for(String string : settings.getNoScanList()){
                                                        if (name4.equals(string)){
                                                            bool4 = true;
                                                        }
                                                    }
                                                    if(bool4 == true){
                                                        continue;
                                                    }
                                                    String link4 = (settings.getUrlPrefixLevel4() + element4.select(settings.getSelectCategoryNameTagLevel4()).attr(settings.getSelectCategoryAttrHrefLevel4()))
                                                            .replace(settings.getReplaceCategoryUrlLevel4(), settings.getReplacementCategoryUrlLevel4()).trim();
                                                    if (!link4.isEmpty() || !name4.isEmpty()) {
                                                        Categories category4 = new Categories();
                                                        category4.setId(lastCategoryId);
                                                        category4.setName(name4);
                                                        category4.setUrl(link4);
                                                        category4.setParentId(category3.getId());
                                                        category4.setShopId(settings.getShopId());
                                                        categoryList.add(category4);
                                                        lastCategoryId++;
                                                        System.out.println(category4.getId() + " " + category4.getName() + " " + category4.getUrl() + " " + category4.getParentId());

                                                        try {
                                                            Document page5 = Jsoup.connect(link4)
                                                                    .cookies(cookies)
                                                                    .get();
                                                            Elements elements5 = page5.select(settings.getSelectCategoryTagLevel5());
                                                            for (Element element5 : elements5) {
                                                                String name5 = element5.select(settings.getSelectCategoryNameTagLevel5()).text()
                                                                        .replace(settings.getReplaceCategoryNameLevel5(), settings.getReplacementCategoryNameLevel5()).trim();
                                                                boolean bool5 = false;
                                                                for(String string : settings.getNoScanList()){
                                                                    if (name5.equals(string)){
                                                                        bool5 = true;
                                                                    }
                                                                }
                                                                if(bool5 == true){
                                                                    continue;
                                                                }
                                                                String link5 = (settings.getUrlPrefixLevel5() + element5.select(settings.getSelectCategoryNameTagLevel5()).attr(settings.getSelectCategoryAttrHrefLevel5()))
                                                                        .replace(settings.getReplaceCategoryUrlLevel5(), settings.getReplacementCategoryUrlLevel5()).trim();
                                                                if (!link5.isEmpty() || !name5.isEmpty()) {
                                                                    Categories category5 = new Categories();
                                                                    category5.setId(lastCategoryId);
                                                                    category5.setName(name5);
                                                                    category5.setUrl(link5);
                                                                    category5.setParentId(category4.getId());
                                                                    category5.setShopId(settings.getShopId());
                                                                    categoryList.add(category5);
                                                                    lastCategoryId++;
                                                                    System.out.println(category5.getId() + " " + category5.getName() + " " + category5.getUrl() + " " + category5.getParentId());
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
        try (FileWriter writer = new FileWriter("..\\webParser\\src\\main\\resources\\data\\categories\\" + settings.getShopName() + "_categories.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(categoryList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}