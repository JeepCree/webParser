package ua.com.mobifix.parser;

import com.github.slugify.Slugify;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.Product;
import ua.com.mobifix.entity.Shop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class AllProductParser extends CategoryParser {
    List<Product> productList = new ArrayList<>();

    public void saveList(List<Product> productList, Shop settings) {
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

    public List<Product> getProducts(Shop settings, Long categoryId) {
        int num = 1;
        int i = 0;


        boolean bool = true;
        while (bool) {
            try {
//                System.out.println(settings.getScanProductsUrl() + settings.getPagination() + num + settings.getParameter());
                String page = Jsoup.connect(settings.getScanProductsUrl() + settings.getPagination() + num + settings.getParameter())
                        .cookies(parseStringToMap(settings.getCookies()))
                        .get()
                        .select(settings.getSelectProductsListCartTag()).text();

                String newPage = Jsoup.connect(settings.getScanProductsUrl() + settings.getPagination() + (num + 1) + settings.getParameter())
                        .cookies(parseStringToMap(settings.getCookies()))
                        .get()
                        .select(settings.getSelectProductsListCartTag()).text();
                Document scanPage = Jsoup.connect(settings.getScanProductsUrl() + settings.getPagination() + num + settings.getParameter())
                        .cookies(parseStringToMap(settings.getCookies()))
                        .get();
                System.out.println("Сканируем страницу " + num + " (" + settings.getScanProductsUrl() + ")");
                //вставка


                Elements elements = scanPage.select(settings.getSelectProductsListCartTag());

                System.out.println("Найдено элементов: " + elements.size());
                for (Element element : elements) {
                    Product product = new Product();
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
                    String price;
                    if(element.selectFirst(settings.getSelectProductsPriceTag()) != null){
                         price = element.selectFirst(settings.getSelectProductsPriceTag()).text();
                        for (Map.Entry<String, String> pr : parseStringToMap(settings.getReplaceProductsPrice()).entrySet()) {
                            price = price.replace(pr.getKey(), pr.getValue());
                        }
                        for (Map.Entry<String, String> prCont : parseStringToMap(settings.getContainProductsPrice()).entrySet()) {
                            if (price.contains(prCont.getKey())) {
                                price = prCont.getValue();
                            }
                        }
                    } else {
                         price = "0";
                    }



                        String imageLink = settings.getUrlProductsImageLinkPrefix() + element.select(settings.getSelectProductsImageLinkTag()).attr(settings.getSelectProductsAttrSrc());

                        product.setArticle(article);
                        product.setName(name);
//                    System.out.println("upd. name > " + Slugify.builder().locale(new Locale("ru")).build().slugify(name));
                        product.setChpu(Slugify.builder().locale(new Locale("ru")).build().slugify(name));
//                    System.out.println("in product > " + product.getChpu());
                        product.setLink(productUrl);
                        product.setImageLink(imageLink);
                        product.setStock(stock);
                        if (price.equals("")){
                            price = "0";
                        }
                        try {
                            product.setPrice(Double.parseDouble(price));
                        } catch (Exception e){
                            System.out.println("Ошибка записи цены. Обновлена на 0.0");
                            product.setPrice(0.0);
                        }

                        product.setCategories(categoryId);
                        product.setShopId(settings.getIdShop());
                        productList.add(product);

//                        System.out.println(article);
//                        System.out.println(name);
//                        System.out.println(productUrl);
//                        System.out.println(imageLink);
//                        System.out.println(stock);
//                        System.out.println(price);
//                        System.out.println("\n");
                    }

                    //конец вставки

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
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                                System.out.println("ошибка Thread.sleep(1000);");
                            }
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
//            saveList(productList, settings);
            System.out.println("Конец сканирования!");
            return productList;
        }
    }