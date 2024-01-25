package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.mobifix.entity.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ProductParser {
    public void getProduct(ScanProductSettings settings, ArrayList<String> scanList){
        for (String url : scanList){
            int retries = 0;
            while (retries < settings.getMaxRetriesLoadPage()) {
                try {
                    Connection connection  = Jsoup.connect(url);
                    Document page = connection.cookies(settings.getCookies())
                            .method(Connection.Method.GET)
                            .get();

                    String article = page.select(settings.getArticle()).text();
                    for (ReplaceString obj : settings.getReplaceArticle()) {
                        article = article.replace(obj.getReplace(), obj.getReplacement());
                    }
                    for (ReplaceString obj : settings.getContainArticle()) {
                        if (article.contains(obj.getReplace())) {
                            article = obj.getReplacement();
                        }
                    }
                    String name = page.select(settings.getName()).text();
                    String stock = page.select(settings.getStock()).text();
                    for (ReplaceString obj : settings.getReplaceStock()) {
                        stock = stock.replace(obj.getReplace(), obj.getReplacement());
                    }
                    for (ReplaceString obj : settings.getContainStock()) {
                        if (stock.contains(obj.getReplace())) {
                            stock = obj.getReplacement();
                        }
                    }
                    String price = page.select(settings.getPrice()).text();
                    for (ReplaceString obj : settings.getReplacePrice()) {
                        price = price.replace(obj.getReplace(), obj.getReplacement());
                    }
                    for (ReplaceString obj : settings.getContainPrice()) {
                        if (price.contains(obj.getReplace())) {
                            price = obj.getReplacement();
                        }
                    }
                    Elements breadcrumbsList = page.select(settings.getBreadcrumbs());
                    String breadcrumbs = "";

                    for (int i = 0; i < breadcrumbsList.size(); i++) {
                        breadcrumbs = breadcrumbs + breadcrumbsList.get(i).select("a").text();
                        if (i < breadcrumbsList.size() - 1) {
                            breadcrumbs = breadcrumbs + ";";
                        }
                    }

                    String link = settings.getLinkPrefix() + page.select(settings.getLink()).attr(settings.getHref());
                    String imageLink = settings.getImagePrefix() + page.select(settings.getImageLink()).attr(settings.getSrc());

                    Product product = new Product();
                    product.setArticle(article);
                    product.setName(name);
                    product.setStock(stock);
                    product.setPrice(Double.parseDouble(price));
                    product.setBreadcrumbs(breadcrumbs);
                    product.setLink(link);
                    product.setImageLink(imageLink);

                    System.out.println(article);
                    System.out.println(name);
                    System.out.println(stock);
                    System.out.println(price);
                    System.out.println(breadcrumbs);
                    System.out.println(link);
                    System.out.println(imageLink);
                    System.out.println("\n");

                        break;
                } catch (IOException e) {
                    System.out.println("ошибка чтения страницы...");
                    retries++;
                }

            }
        }

    }
}
