package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.mobifix.entity.Product;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.parser.description.AllSparesDescriptionParser;
import ua.com.mobifix.parser.description.ArtMobileDescriptionParser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ProductParser {
    public Product getProduct(Shop settings, String scanLink, Long shopId) throws InterruptedException {

        Product product = new Product();
            int retries = 0;
            while (retries < settings.getMaxRetriesLoadPage()) {
                try {
                    System.out.println("\u001B[32m" + "connected to server... " +  "\u001B[0m" + scanLink);
                    Connection connection  = Jsoup.connect(scanLink);
                    Document page = connection.cookies(new CategoryParser().parseStringToMap(settings.getCookies()))
                            .method(Connection.Method.GET)
                            .get();


                    Elements breadcrumbsList = page.select(settings.getSelectProductBreadcrumbsTag());
                    String breadcrumbs = breadcrumbsList.html();
                    String description = "";
                    /*
                    for(Element el : breadcrumbsList){
                        breadcrumbs = breadcrumbs + el.select("a").html() + ";";
                    }

                    for (int i = 0; i < breadcrumbsList.size(); i++) {
                        breadcrumbs = breadcrumbs + breadcrumbsList.get(i).select("a").html() + ";";
                        if (i < breadcrumbsList.size() - 1) {
                            breadcrumbs = breadcrumbs + ";";
                        }
                    }
*/
                    if (shopId == 4) {
                        AllSparesDescriptionParser parser = new AllSparesDescriptionParser();
                        description = parser.parseHtmlToJson(page.select(settings.getSelectProductDescriptionTag()).html()).toString();
                    } else if (shopId == 8) {
                        ArtMobileDescriptionParser parser = new ArtMobileDescriptionParser();
                        description = parser.parseHtmlToJson(page.select(settings.getSelectProductDescriptionTag()).html()).toString();
                    }




                    product.setBreadcrumbs(breadcrumbs);
                    product.setDescription(description);
                    break;
                } catch (IOException e) {
                    System.out.println("\u001B[31m" + "ошибка чтения страницы..." + "\u001B[0m");
                    System.out.println(e);
                    Thread.sleep(1000);
                    retries++;
                }
            }
        return product;
    }
}
