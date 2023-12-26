package ua.com.mobifix.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
//    public void getCatalog(String[] array){
    public void getCatalog(ScanCategorySettings settings){
        Map<String, String> catalogMap = new HashMap<>();
        try {
            Connection.Response response = Jsoup.connect(settings.getUrlShop()).method(Connection.Method.GET).execute();
            Document page = Jsoup.connect(settings.getUrlShop())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .cookie(settings.getCookieName(), settings.getCookieValue())
                    .get();
            Elements elements = page.select(settings.getSelectCategoryTag());

            for (Element element : elements) {
                String text = element.select(settings.getSelectCategoryNameTag()).text()
                        .replace(settings.getReplaceCategoryName(), settings.getReplacementCategoryName());
                String linked = element.select(settings.getSelectCategoryNameTag()).attr(settings.getSelectCategoryAttrHref());
                if(!linked.isEmpty() || !text.isEmpty()){
                    catalogMap.put(text, settings.getUrlPrefix() + linked);
                }
            }
            for (Map.Entry<String, String> entry : catalogMap.entrySet()) {
                    System.out.println(entry.getKey() + " | " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getCategory(){

    }
}

