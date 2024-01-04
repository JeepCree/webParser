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
import java.util.List;
import java.util.Map;

public class AllProductParser {
    List<AllScanProduct> productList = new ArrayList<>();

    public void saveList(List<AllScanProduct> productList, ScanProductSettings settings){
        try (FileWriter writer = new FileWriter("..\\webParser\\src\\main\\resources\\data\\products\\" + settings
                .getScanUrl()
                .replace("/", "-")
                .replace(":", "") + "products.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(productList, writer);
            System.out.println(productList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getProducts(ScanProductSettings settings, int num) {
        String url = settings.getScanUrl() + settings.getPagination();


        Map<String, String> cookies = settings.getCookies();
        if (cookies == null) {
            cookies.put("noName", "noValue");
        }

        boolean bool = true;
        String selected = "div.uk-position-relative.uk-container.uk-margin-medium.uk-margin-medium-top";
        while (bool) {
            try {
                String page = Jsoup.connect(url  + num + "/")
                        .get()
                        .select(selected).text();
                String newPage = Jsoup.connect(url + (num + 1) + "/")
                        .get()
                        .select(selected).text();
                Document scanPage = Jsoup.connect(url  + num + "/")
                        .get();
                System.out.println("Сканируем страницу " + num);
                //вставка
                Elements elements = scanPage.select(settings.getProductCart());
                for (Element element : elements) {
                    AllScanProduct asp = new AllScanProduct();
                    String name = element.select(settings.getName()).text();
                    String article = element.select(settings.getArticle()).text();
                    String productUrl = settings.getPrefix() + element.select(settings.getLink()).attr(settings.getHref());
                    String stock = element.select(settings.getStock()).text();
                    String price = element.select(settings.getPrice()).text()
                            .replace(settings.getReplacePrice(), settings.getReplacementPrice());
                    String imageLink = element.select(settings.getImageLink()).attr(settings.getSrc());
                    if (stock.equals("")) {
                        stock = "-";
                    }
                    if (!article.equals("")) {
                        asp.setArticle(article);
                        asp.setName(name);
                        asp.setLink(productUrl);
                        asp.setImageLink(imageLink);
                        asp.setStock(stock);
                        asp.setPrice(price);
                        productList.add(asp);

//                    System.out.println(article);
                        System.out.println(name);
//                    System.out.println(productUrl);
//                    System.out.println(imageLink);
//                    System.out.println(stock);
//                    System.out.println(price);
//                    System.out.println("\n");
                    } else {
                        return false;
                    }
                }

                //конец вставка

                bool = !page.equals(newPage);
//                    System.out.println(!page.equals(newPage) + " " + i);
                num++;
            } catch (IOException e) {
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
                    System.out.println("Ошибка сканирования. Посторное сканирование страницы...");
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
