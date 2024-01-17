package ua.com.mobifix.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.mobifix.entity.Shop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllProductParser extends CategoryParser {
    List<AllScanProduct> productList = new ArrayList<>();

    public void saveList(List<AllScanProduct> productList, Shop settings) {
        try (FileWriter writer = new FileWriter("..\\webParser\\src\\main\\resources\\data\\products\\" + settings
                .getNameShop()
                .replace("/", "-")
                .replace(":", "") + "products.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(productList, writer);
            System.out.println(productList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getProducts(Shop settings, int num) {
        String url = settings.getScanProductsUrl();

        Map<String, String> cookies = parseStringToMap(settings.getCookies());
        if (cookies == null) {
            cookies.put("noName", "noValue");
        }

        boolean bool = true;
        while (bool) {
            try {
                System.out.println(url + settings.getPagination() + num + settings.getParameter());
                String page = Jsoup.connect(url + settings.getPagination() + num + settings.getParameter())
                        .cookies(parseStringToMap(settings.getCookies()))
                        .get()
                        .select(settings.getSelectProductsListCartTag()).text();

                String newPage = Jsoup.connect(url + settings.getPagination() + (num + 1) + settings.getParameter())
                        .cookies(parseStringToMap(settings.getCookies()))
                        .get()
                        .select(settings.getSelectProductsListCartTag()).text();
                Document scanPage = Jsoup.connect(url + settings.getPagination() + num + settings.getParameter())
                        .cookies(parseStringToMap(settings.getCookies()))
                        .get();
                System.out.println("Сканируем страницу " + num);
                //вставка
                Elements elements = scanPage.select(settings.getSelectProductsListCartTag());
                System.out.println(elements.size());
                for (Element element : elements) {
                    AllScanProduct asp = new AllScanProduct();
                    String name = element.select(settings.getSelectProductsNameTag()).text();
                    String article = element.select(settings.getSelectProductsArticleTag()).text();
                    for (Map.Entry<String, String> art : parseStringToMap(settings.getReplaceProductsArticle()).entrySet()) {
                        article = article.replace(art.getKey(), art.getValue());
                    }
                    for (Map.Entry<String, String> prCont : parseStringToMap(settings.getContainProductsArticle()).entrySet()) {
                        if (article.contains(prCont.getKey())) {
                            article = prCont.getValue();
                        }
                    }
                    String productUrl = settings.getUrlProductsLinkPrefix() + element.select(settings.getSelectProductsLinkTag()).attr(settings.getSelectProductsAttrHref());
                    String stock = element.select(settings.getSelectProductsStockTag()).text();
                    for (Map.Entry<String, String> st : parseStringToMap(settings.getReplaceProductsStock()).entrySet()) {
                        stock = stock.replace(st.getKey(), st.getValue());
                    }
                    for (Map.Entry<String, String> stCont : parseStringToMap(settings.getContainProductsStock()).entrySet()) {
                        if (stock.contains(stCont.getKey())) {
                            stock = stCont.getValue();
                            }
                        }


                    String price = element.select(settings.getSelectProductsPriceTag()).text();
                    for (Map.Entry<String, String> pr : parseStringToMap(settings.getReplaceProductsPrice()).entrySet()) {
                        price = price.replace(pr.getKey(), pr.getValue());
                    }
                    for (Map.Entry<String, String> prCont : parseStringToMap(settings.getContainProductsPrice()).entrySet()) {
                        if (price.contains(prCont.getKey())) {
                            price = prCont.getValue();
                        }
                    }

                        String imageLink = settings.getUrlProductsImageLinkPrefix() + element.select(settings.getSelectProductImageLinkTag()).attr(settings.getSelectProductsAttrSrc());
                        asp.setArticle(article);
                        asp.setName(name);
                        asp.setLink(productUrl);
                        asp.setImageLink(imageLink);
                        asp.setStock(stock);
                        asp.setPrice(price);
                        productList.add(asp);

                        System.out.println(article);
                        System.out.println(name);
                        System.out.println(productUrl);
                        System.out.println(imageLink);
                        System.out.println(stock);
                        System.out.println(price);
                        System.out.println("\n");
//                    } else {
//                        return false;
//                    }
                    }

                    //конец вставка

                    bool = !page.equals(newPage);
//                    System.out.println(!page.equals(newPage) + " " + i);
                    num++;
                } catch (IOException e){
                    if (e instanceof org.jsoup.HttpStatusException) {
                        org.jsoup.HttpStatusException httpStatusException = (org.jsoup.HttpStatusException) e;
                        int statusCode = httpStatusException.getStatusCode();
                        if (statusCode == 404) {
                            System.out.println("Страница не найдена (ошибка 404).");
                            bool = false; // Устанавливаем флаг в false, чтобы завершить цикл
                        } else {
                            System.out.println("Другая ошибка HTTP: " + statusCode);
                        }
                    } else {
                        System.out.println("Ошибка сканирования. Повторное сканирование страницы...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ex) {
                            System.out.println("ошибка Thread.sleep(1000);");
                        }
                    }
                }
            }
            saveList(productList, settings);
            System.out.println("Конец сканирования!");
            return bool;
        }
    }