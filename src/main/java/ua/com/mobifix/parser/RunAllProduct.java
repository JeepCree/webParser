package ua.com.mobifix.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RunAllProduct {public static void main(String[] args) throws IOException {
    ScanProductSettings sps = new ScanProductSettings();
    AllProductParser app = new AllProductParser();

    Map<String, String> cookies = new HashMap<>();
    cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJQ4cog1s%2Fypdp9%2BRQtnVRaVzL5Iyq9NI3jAIal4lYLLgqYy2B7gx5ThqIUPJrEbMrw");

    sps.setCookies(cookies);
    sps.setName("div.component_product_list_info_right > a");
    sps.setArticle("span.component_product_list_product-info_item.info-id > span");
    sps.setLink("div.component_product_list_info_right > a");
    sps.setImageLink("div.component_product_list_info > div.component_product_list_image-container > a");
    sps.setStock("div.component_product_list_in-stock.type-2 > div > ul");
    sps.setPrice("div.-current.-red");
    sps.setHref("href");
    sps.setSrc("src");
    sps.setPrefix("https://all-spares.ua");
    sps.setProductCart("div.row.d-flex.product-cards-wrapper > div > div > div");
    sps.setScanUrl("https://monkeyfix.com.ua/dyplei/1111/filter/page=20/");
    sps.setPagination("?ipp=192&page=");
    sps.setReplacePrice(" ₴");
    sps.setReplacementPrice("");


    int num = 1;
    while (app.getProducts(sps, num)){
        num++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
}
