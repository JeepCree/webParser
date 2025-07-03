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
import java.util.Map;

public class ProductParser {
    public Product getProduct(Shop settings, String scanLink, Long shopId) throws InterruptedException {

        Product product = new Product();
            int retries = 0;
            while (retries < settings.getMaxRetriesLoadPage()) {
                try {
                    System.out.println("\u001B[32m" + "connected to server... " +  "\u001B[0m" + scanLink);

                    Connection connection  = Jsoup.connect(scanLink);
                    Document page = connection
//                            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("195.178.133.59", 50101)))
                            .cookies(new CategoryParser().parseStringToMap(settings.getCookies()))
                            .method(Connection.Method.GET)
                            .get();

                    String article = page.select(settings.getSelectProductArticleTag()).text();
                    for (Map.Entry<String, String> art : new CategoryParser().parseStringToMap(settings.getReplaceProductArticle()).entrySet()) {
                        article = article.replace(art.getKey(), art.getValue());
                    }
                    String price = page.select(settings.getSelectProductPriceTag()).text();
                    for (Map.Entry<String, String> art : new CategoryParser().parseStringToMap(settings.getReplaceProductsPrice()).entrySet()) {
                        price = price.replace(art.getKey(), art.getValue());
                        if(price == null || price.equals("")) {
                            price = "0";
                        }
                    }
//                    String pcs = new AllSparesSeleniumParser().parsePcs(scanLink);
//                    if (pcs == null || pcs.equals("")) {
//                        pcs = "0";
//                    }
                    String stock = page.select(settings.getSelectProductStockTag()).text();
                    String breadcrumbsList = page.select(settings.getSelectProductBreadcrumbsTag()).html();


                    String breadcrumbs = "";
                    if(shopId == 200) {
                        ArtMobileBreadcrumbsParser breadcrumbsParser = new ArtMobileBreadcrumbsParser();
                        breadcrumbs = breadcrumbsParser.parseHtmlToJson(breadcrumbsList).toString();
                    } else if(shopId == 201) {
                        AllSparesBreadcrumbsParser  breadcrumbsParser = new AllSparesBreadcrumbsParser();
                        breadcrumbs =  breadcrumbsParser.parseHtmlToJson(breadcrumbsList).toString();
                    }


                    String description = "";
                    if (shopId == 201) {
                        AllSparesDescriptionParser parser = new AllSparesDescriptionParser();
                        description = parser.parseHtmlToJson(page.select(settings.getSelectProductDescriptionTag()).html()).toString();
                    } else if (shopId == 200) {
                        ArtMobileDescriptionParser parser = new ArtMobileDescriptionParser();
                        description = parser.parseHtmlToJson(page.select(settings.getSelectProductDescriptionTag()).html()).toString();
                    }


                    product.setArticle(article);
                    product.setBreadcrumbs(breadcrumbs);
                    product.setDescription(description);
                    product.setPrice(Double.parseDouble(price));
//                    product.setPcs(Integer.parseInt(pcs));
                    product.setStock(stock);
                    product.setTimestampField(new Timestamp(System.currentTimeMillis()));

                    break;
                } catch (HttpStatusException e) {
                    int code = e.getStatusCode();
                    if (code == 404) {
                        System.out.println("\u001B[33m" + "404 — не найдено. Пропускаем." + "\u001B[0m");
                        Thread.sleep(500);
                        System.out.println("\u001B[31mПродукт \u001B[0m\n" + scanLink + "\n\u001B[31mне найден. УДАЛЕН!\u001B[0m");
                        return null;
                    } else if (code >= 500) {
                        System.out.println("\u001B[31m" + "Ошибка сервера: " + code + ". Ждём..." + "\u001B[0m");
                        Thread.sleep(30_000);
                        retries++;
                    } else if (code == 429) {
                        System.out.println("\u001B[35m" + "429 — слишком много запросов. Ждём 5 минут..." + "\u001B[0m");
                        Thread.sleep(300_000);
                        retries++;
                    } else {
                        System.out.println("\u001B[33m" + "HTTP ошибка: " + code + ". Пропускаем." + "\u001B[0m");
                    }
                } catch (SocketTimeoutException | UnknownHostException | ConnectException e) {
                    System.out.println("\u001B[31m" + "Сетевая ошибка (" + e.getClass().getSimpleName() + "). Ждём..." + "\u001B[0m");
                    Thread.sleep(10_000);
                    retries++;
                } catch (IOException e) {
                    System.out.println("\u001B[31m" + "Другая IO ошибка: " + e.getMessage() + "\u001B[0m");
                    Thread.sleep(30_000);
                    retries++;
                }

            }
        return product;
    }
}
