package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.mobifix.entity.Product;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.parser.breadcrumbs.AllSparesBreadcrumbsParser;
import ua.com.mobifix.parser.breadcrumbs.ArtMobileBreadcrumbsParser;
import ua.com.mobifix.parser.breadcrumbs.BreadcrumbsParser;
import ua.com.mobifix.parser.description.AllSparesDescriptionParser;
import ua.com.mobifix.parser.description.ArtMobileDescriptionParser;
import ua.com.mobifix.parser.selenium.AllSparesSeleniumParser;

import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductParser {
    public Map<String, String> parseStringToMap (String str){
        Map<String, String> map = new HashMap<>();
        String[] stringPairs = str.split("<';'>");
        for (String stringPair : stringPairs) {
            String[] parts = stringPair.split("-->");
            if (parts.length == 2) {
                if(parts[1].equals("empty_replacement")){
                    parts[1] = "";
                }
                if(parts[0].equals("empty_replacement")){
                    parts[0] = "";
                }
                map.put(parts[0], parts[1]);
            }
        }
        return map;
    }
    public Product getProduct(Shop settings, String scanLink, Long shopId) throws InterruptedException {
        Product product = new Product();
        int retries = 0;

        while (retries < settings.getMaxRetriesLoadPage()) {
            try {
                System.out.println("\u001B[32m" + "connected to server... " + "\u001B[0m" + scanLink);

                Connection connection = Jsoup.connect(scanLink)
                        .cookies(new CategoryParser().parseStringToMap(settings.getCookies()))
                        .method(Connection.Method.GET);

                Document page = connection.get();

                String article = page.select(settings.getSelectProductArticleTag()).text();
                for (Map.Entry<String, String> art : new CategoryParser().parseStringToMap(settings.getReplaceProductArticle()).entrySet()) {
                    article = article.replace(art.getKey(), art.getValue());
                }

                String price = page.select(settings.getSelectProductPriceTag()).text();
                for (Map.Entry<String, String> art : new CategoryParser().parseStringToMap(settings.getReplaceProductsPrice()).entrySet()) {
                    price = price.replace(art.getKey(), art.getValue());
                }
                if (price == null || price.isEmpty()) price = "0";

                String stock = page.select(settings.getSelectProductStockTag()).text();
                String breadcrumbsList = page.select(settings.getSelectProductBreadcrumbsTag()).html();

                String breadcrumbs = "";
                if (shopId == 200) {
                    breadcrumbs = new ArtMobileBreadcrumbsParser().parseHtmlToJson(breadcrumbsList, settings).toString();
                } else if (shopId == 201) {
                    breadcrumbs = new AllSparesBreadcrumbsParser().parseHtmlToJson(breadcrumbsList, settings).toString();
                }

                String description = "";
                if (shopId == 201) {
                    description = new AllSparesDescriptionParser()
                            .parseHtmlToJson(page.select(settings.getSelectProductDescriptionTag()).html())
                            .toString();
                } else if (shopId == 200) {
                    description = new ArtMobileDescriptionParser()
                            .parseHtmlToJson(page.select(settings.getSelectProductDescriptionTag()).html())
                            .toString();
                }

                String imageLink = page.select(settings.getSelectProductImageLinkTag()).attr(settings.getSelectProductAttrSrc());
                for (Map.Entry<String, String> il : parseStringToMap(settings.getReplaceProductImageLink()).entrySet()) {
                    imageLink = imageLink.replace(il.getKey(), il.getValue());
                }

                product.setArticle(article);
                product.setBreadcrumbs(breadcrumbs);
                product.setDescription(description);
                product.setPrice(Double.parseDouble(price));
                product.setStock(stock);
                product.setImageLink(imageLink);
                product.setTimestampField(new Timestamp(System.currentTimeMillis()));

                return product;

            } catch (HttpStatusException e) {
                int code = e.getStatusCode();
                if (code == 404) {
                    System.out.println("\u001B[33m404 — не найдено. Пропускаем.\u001B[0m");
                    System.out.println("\u001B[31mПродукт не найден. УДАЛЕН: \u001B[0m\n" + scanLink);
                    return null;
                } else if (code == 520) {
                    System.out.println("\u001B[31m520 — Неизвестная ошибка от прокси или сервера. Пропускаем.\u001B[0m");
                    return null;
                } else if (code == 522) {
                    System.out.println("\u001B[31m520 — Неизвестная ошибка от прокси или сервера. Пропускаем.\u001B[0m");
                    return null;
                }else if (code == 429) {
                    System.out.println("\u001B[35m429 — слишком много запросов. Ждём 5 минут...\u001B[0m");
                    Thread.sleep(300_000);
                    retries++;
                } else if (code >= 500) {
                    System.out.println("\u001B[31mОшибка сервера: " + code + ". Ждём...\u001B[0m");
                    Thread.sleep(30_000);
                    retries++;
                } else {
                    System.out.println("\u001B[33mHTTP ошибка: " + code + ". Пропускаем.\u001B[0m");
                    retries++;
//                    return null;
                }

            } catch (SocketTimeoutException | UnknownHostException | ConnectException e) {
                System.out.println("\u001B[31mСетевая ошибка (" + e.getClass().getSimpleName() + " > " + e.getMessage() + "). Ждём...\u001B[0m");

//удалить товар
//                System.out.println("\u001B[31mПродукт не найден. УДАЛЕН: \u001B[0m\n" + scanLink);
//                return null;

//пропустить сканирование товара
                break;

            } catch (IOException e) {
                System.out.println("\u001B[31mДругая IO ошибка: " + e.getMessage() + "\u001B[0m");
                Thread.sleep(30_000);
                retries++;
            }
        }

        return product;
    }

}
