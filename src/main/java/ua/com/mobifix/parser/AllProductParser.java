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
        try (FileWriter writer = new FileWriter("..\\webParser\\src\\main\\resources\\data\\products\\" + settings.getScanUrl().replace("/", "-").replace(":", "") + "products.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            gson.toJson(productList, writer);
            System.out.println(productList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getProducts(ScanProductSettings settings, int num){
        String url = settings.getScanUrl() + settings.getPagination() + num;


        Map<String, String> cookies = settings.getCookies();
        if (cookies == null) {
            cookies.put("noName", "noValue");
        }
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url)
                    .followRedirects(false)
                    .execute();
            int statusCode = response.statusCode();
            if (statusCode >= 300 && statusCode < 400){
                if (productList.size() != 0) {
                    saveList(productList, settings);
                    return false;
                } else {
                    return false;
                }
            } else {
                Document page = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                        .cookies(cookies)
//                        .proxy("78.46.210.112", 80)
                        .get();
                Elements elements = page.select(settings.getProductCart());
                System.out.println(elements.size());
                for (Element element : elements){
                    AllScanProduct asp = new AllScanProduct();
                    String name = element.select(settings.getName()).text();
                    String article = element.select(settings.getArticle()).text();
                    String productUrl = settings.getPrefix() + element.select(settings.getLink()).attr(settings.getHref());
                    String stock = element.select(settings.getStock()).text();
                    String price = element.select(settings.getPrice()).text()
                            .replace(settings.getReplacePrice(), settings.getReplacementPrice());
                    String imageLink = element.select(settings.getImageLink()).attr(settings.getSrc());
                    if (stock.equals("")){
                        stock = "-";
                    }
                    if (!article.equals("")){
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
                return true;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
