package ua.com.mobifix.parser;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RunProduct {
    public static void main(String[] args) {
        ArrayList<String> scanList = new ArrayList<>();
        scanList.add("https://all-spares.ua/ru/lcd-compatible-with-xiaomi-redmi-4x-black-with-touchscreen-grade-b-high-copy/");
        ScanProductSettings spc = new ScanProductSettings();
        ArrayList<ReplaceString> replacePrice = new ArrayList<>();
        replacePrice.add(new ReplaceString(" ", ""));
        replacePrice.add(new ReplaceString("₴", ""));
        Map<String, String> cookies = new HashMap<>();
        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJe2%2FNXngYNl0bm3vq0wdmd0i920JFElUuZDWq2hqa1NW4QSlqXn22F12FoVCvqonDQ");



        spc.setArticle("span.prp_info_item.info-id > span");
        spc.setName("div.sop-main-content > div > main > div > div > div > h1");
        spc.setStock("div.prp_info > div > div > ul");
        spc.setPrice("div.prp_static-button.action-zone.acz.-main > div > div > div > div > div.-current.-red");
        spc.setBreadcrumbs("div.sop-main-content > div > main > div > div > div > div > ul > li > a");
        spc.setLink("div.s-g_dropdown > div > a");
        spc.setImageLink("div.gallery-main-wrap > div > picture > img");
        spc.setHref("href");
        spc.setLinkPrefix("https://all-spares.ua");
        spc.setSrc("src");
        spc.setReplacePrice(replacePrice);
        spc.setCookies(cookies);

        ProductParser pp = new ProductParser();
        pp.getProduct(spc, scanList);
    }
}
