package ua.com.mobifix.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.service.CategoryService;

import javax.net.ssl.SSLException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CancellationException;

public class CategoryParser {


    public List<String> parseStringToList (String str){
        String[] stringPairs = str.split("<';'>");
        List<String> list = new ArrayList<>(Arrays.asList(stringPairs));
        return list;

    }

    public Map<String, String> parseStringToMap (String str){
        Map<String, String> map = new HashMap<>();
        String[] stringPairs = str.split("<';'>");
        for (String stringPair : stringPairs) {
            String[] parts = stringPair.split("-->");
            if (parts.length == 2) {
                if(parts[1].equals("empty_replacement")){
                    parts[1] = "";
                }
                map.put(parts[0], parts[1]);
            }
        }
        return map;
    }

    public List<Categories> getCatalog(Shop settings, Long lastCategoryId){
        lastCategoryId++;
        List<Categories> categoryList = new ArrayList<>();
        Map<String, String> cookies = parseStringToMap(settings.getCookies());

        System.out.println(cookies);
        try {
            Connection.Response response = Jsoup.connect(settings.getLinkShop()).method(Connection.Method.GET).execute();
            Document page = Jsoup.connect(settings.getLinkShop())
                    .userAgent(settings.getUserAgent())
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

                String name = element.select(settings.getSelectCategoryNameTag()).text();
                for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryName()).entrySet()) {
                    name.replace(entry.getKey(), entry.getValue()).trim();
                }
                boolean bool1 = false;
                for(String string : parseStringToList(settings.getNoScanCategoryList())){
                    if (name.equals(string)){
                        bool1 = true;
                    }
                }
                if(bool1 == true){
                    continue;
                }

                String link = (settings.getUrlCategoryPrefix() + element.select(settings.getSelectCategoryNameTag()).attr(settings.getSelectCategoryAttrHref()));
                for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryUrl()).entrySet()) {
                    link.replace(entry.getKey(), entry.getValue()).trim();
                }

                if (!link.isEmpty() || !name.isEmpty()) {
                    //найти категорию по ссылке. если категория найдена, обновить ее данные, если не найдена добавить в базу
                    Categories category = new Categories();
                    category.setId(lastCategoryId);
                    category.setName(name);
                    category.setUrl(link);
                    category.setParentId(0L);
                    category.setShopId(settings.getIdShop());
                    categoryList.add(category);
                    lastCategoryId++;
                    System.out.println(category.getId() + " " + category.getName() + " " + category.getUrl() + " " + category.getParentId());

                    try {
                        Document page2 = Jsoup.connect(link)
                                .userAgent(settings.getUserAgent())
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
                            String name2 = element2.select(settings.getSelectCategoryNameTagLevel2()).text();
                            for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryNameLevel2()).entrySet()) {
                                name2.replace(entry.getKey(), entry.getValue()).trim();
                            }
                            boolean bool2 = false;
                            for(String string : parseStringToList(settings.getNoScanCategoryList())){
                                if (name.equals(string)){
                                    bool2 = true;
                                }
                            }
                            if(bool2 == true){
                                continue;
                            }
                            String link2 = (settings.getUrlCategoryPrefixLevel2() + element2.select(settings.getSelectCategoryNameTagLevel2()).attr(settings.getSelectCategoryAttrHrefLevel2()));
                            for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryUrlLevel2()).entrySet()) {
                                link2.replace(entry.getKey(), entry.getValue()).trim();
                            }

                            if (!link2.isEmpty() || !name2.isEmpty()) {
                                Categories category2 = new Categories();
                                category2.setId(lastCategoryId);
                                category2.setName(name2);
                                category2.setUrl(link2);
                                category2.setParentId(category.getId());
                                category2.setShopId(settings.getIdShop());
                                categoryList.add(category2);
                                lastCategoryId++;
                                System.out.println(category2.getId() + " " + category2.getName() + " " + category2.getUrl() + " " + category2.getParentId());

                                try {
                                    Document page3 = Jsoup.connect(link2)
                                            .userAgent(settings.getUserAgent())
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
                                        String name3 = element3.select(settings.getSelectCategoryNameTagLevel3()).text();
                                        for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryNameLevel3()).entrySet()) {
                                            name3.replace(entry.getKey(), entry.getValue()).trim();
                                        }
                                        boolean bool3 = false;
                                        for(String string : parseStringToList(settings.getNoScanCategoryList())){
                                            if (name.equals(string)){
                                                bool3 = true;
                                            }
                                        }
                                        if(bool3 == true){
                                            continue;
                                        }
                                        String link3 = (settings.getUrlCategoryPrefixLevel3() + element3.select(settings.getSelectCategoryNameTagLevel3()).attr(settings.getSelectCategoryAttrHrefLevel3()));
                                        for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryUrlLevel3()).entrySet()) {
                                            link3.replace(entry.getKey(), entry.getValue()).trim();
                                        }
                                        if (!link3.isEmpty() || !name3.isEmpty()) {
                                            Categories category3 = new Categories();
                                            category3.setId(lastCategoryId);
                                            category3.setName(name3);
                                            category3.setUrl(link3);
                                            category3.setParentId(category2.getId());
                                            category3.setShopId(settings.getIdShop());
                                            categoryList.add(category3);
                                            lastCategoryId++;
                                            System.out.println(category3.getId() + " " + category3.getName() + " " + category3.getUrl() + " " + category3.getParentId());

                                            try {
                                                Document page4 = Jsoup.connect(link3)
                                                        .userAgent(settings.getUserAgent())
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
                                                    String name4 = element4.select(settings.getSelectCategoryNameTagLevel4()).text();
                                                    for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryNameLevel4()).entrySet()) {
                                                        name4.replace(entry.getKey(), entry.getValue()).trim();
                                                    }

                                                    boolean bool4 = false;
                                                    for(String string : parseStringToList(settings.getNoScanCategoryList())){
                                                        if (name.equals(string)){
                                                            bool4 = true;
                                                        }
                                                    }
                                                    if(bool4 == true){
                                                        continue;
                                                    }
                                                    String link4 = (settings.getUrlCategoryPrefixLevel4() + element4.select(settings.getSelectCategoryNameTagLevel4()).attr(settings.getSelectCategoryAttrHrefLevel4()));
                                                    for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryUrlLevel4()).entrySet()) {
                                                        link4.replace(entry.getKey(), entry.getValue()).trim();
                                                    }
                                                    if (!link4.isEmpty() || !name4.isEmpty()) {
                                                        Categories category4 = new Categories();
                                                        category4.setId(lastCategoryId);
                                                        category4.setName(name4);
                                                        category4.setUrl(link4);
                                                        category4.setParentId(category3.getId());
                                                        category4.setShopId(settings.getIdShop());
                                                        categoryList.add(category4);
                                                        lastCategoryId++;
                                                        System.out.println(category4.getId() + " " + category4.getName() + " " + category4.getUrl() + " " + category4.getParentId());

                                                        try {
                                                            Document page5 = Jsoup.connect(link4)
                                                                    .userAgent(settings.getUserAgent())
                                                                    .cookies(cookies)
                                                                    .get();
                                                            Elements elements5 = page5.select(settings.getSelectCategoryTagLevel5());
                                                            for (Element element5 : elements5) {
                                                                String name5 = element5.select(settings.getSelectCategoryNameTagLevel5()).text();
                                                                for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryNameLevel5()).entrySet()) {
                                                                    name5.replace(entry.getKey(), entry.getValue()).trim();
                                                                }

                                                                boolean bool5 = false;
                                                                for(String string : parseStringToList(settings.getNoScanCategoryList())){
                                                                    if (name.equals(string)){
                                                                        bool5 = true;
                                                                    }
                                                                }
                                                                if(bool5 == true){
                                                                    continue;
                                                                }
                                                                String link5 = (settings.getUrlCategoryPrefixLevel5() + element5.select(settings.getSelectCategoryNameTagLevel5()).attr(settings.getSelectCategoryAttrHrefLevel5()));
                                                                for (Map.Entry<String, String> entry : parseStringToMap(settings.getReplaceCategoryUrlLevel5()).entrySet()) {
                                                                    link5.replace(entry.getKey(), entry.getValue()).trim();
                                                                }

                                                                if (!link5.isEmpty() || !name5.isEmpty()) {
                                                                    Categories category5 = new Categories();
                                                                    category5.setId(lastCategoryId);
                                                                    category5.setName(name5);
                                                                    category5.setUrl(link5);
                                                                    category5.setParentId(category4.getId());
                                                                    category5.setShopId(settings.getIdShop());
                                                                    categoryList.add(category5);
                                                                    lastCategoryId++;
                                                                    System.out.println(category5.getId() + " " + category5.getName() + " " + category5.getUrl() + " " + category5.getParentId());
                                                                }
                                                            }
                                                        } catch (Exception e){
                                                            e.getMessage();
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.getMessage();
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
//        System.out.println("\nКонец отработки модуля -> return categoryList\n");
        try (FileWriter writer = new FileWriter("..\\webParser\\src\\main\\resources\\data\\categories\\" + settings.getNameShop() + "_categories.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(categoryList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}