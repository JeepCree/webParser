package ua.com.mobifix.parser;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ProductParser {
    public void getProduct(ScanProductSettings settings, ArrayList<String> scanList){
        for (String url : scanList){
            try {
                Connection connection  = Jsoup.connect(url);
                Document page = connection.cookies(settings.getCookies())
                        .method(Connection.Method.GET)
                        .get();

                String article = page.select(settings.getArticle()).text();
                for (ReplaceString art : settings.getReplaceArticle()) {
                    article = article.replace(art.getReplace(), art.getReplacement());
                }
                for (ReplaceString prCont : settings.getContainArticle()) {
                    if (article.contains(prCont.getReplace())) {
                        article = prCont.getReplacement();
                    }
                }
                String name = page.select(settings.getName()).text();
                String stock = page.select(settings.getStock()).text();
                for (ReplaceString st : settings.getReplaceStock()) {
                    stock = stock.replace(st.getReplace(), st.getReplacement());
                }
                for (ReplaceString stCont : settings.getContainStock()) {
                    if (stock.contains(stCont.getReplace())) {
                        stock = stCont.getReplacement();
                    }
                }
                String price = page.select(settings.getPrice()).text();
                for (ReplaceString pr : settings.getReplacePrice()) {
                    price = price.replace(pr.getReplace(), pr.getReplacement());
                }
                for (ReplaceString prCont : settings.getContainPrice()) {
                    if (price.contains(prCont.getReplace())) {
                        price = prCont.getReplacement();
                    }
                }

                Elements breadcrumbs = page.select(settings.getBreadcrumbs());
                String breadcr = "";
                int size = breadcrumbs.size();
                for (int i = 0; i < size; i++) {
                    breadcr = breadcr + breadcrumbs.get(i).select("a").text();
                    if (i < size - 1) {
                        breadcr = breadcr + ";";
                    }
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
//                System.out.println(page);
            } catch (IOException e) {
                System.out.println("ошибка чтения страницы...");
            }
        }

    }
}
