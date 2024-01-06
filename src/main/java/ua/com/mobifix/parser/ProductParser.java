package ua.com.mobifix.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ProductParser {
    public void getProduct(ScanProductSettings settings, ArrayList<String> scanList){
        for (String url : scanList){
            try {
                Document page = Jsoup.connect(url).get();
                String article = page.select(settings.getArticle()).text();
                String name = page.select(settings.getName()).text();
                String stock = page.select(settings.getStock()).text();
                String price = page.select(settings.getPrice()).text();
                Elements breadcrumbs = page.select(settings.getBreadcrumbs());
                String breadcr = "";
                System.out.println(breadcrumbs.size());
                for (Element str : breadcrumbs){
                    breadcr = breadcr + str.select("a").text() + ";";
                }
                String link = settings.getLinkPrefix() + page.select(settings.getLink()).attr(settings.getHref());
                String imageLink = settings.getImagePrefix() + page.select(settings.getImageLink()).attr(settings.getSrc());

                System.out.println(article);
                System.out.println(name);
                System.out.println(stock);
                System.out.println(price);
                System.out.println(breadcr);
                System.out.println(link);
                System.out.println(imageLink);
            } catch (IOException e) {
                System.out.println("ошибка чтения страницы...");
            }
        }

    }
}
