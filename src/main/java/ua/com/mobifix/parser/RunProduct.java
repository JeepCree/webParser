package ua.com.mobifix.parser;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RunProduct {
    public static void main(String[] args) {
        ScanProductSettings spc = new ScanProductSettings();
        ArrayList<ReplaceString> replacePrice = new ArrayList<>();
        ArrayList<String> scanList = new ArrayList<>();
        Map<String, String> cookies = new HashMap<>();
        scanList.add("https://all-spares.ua/ru/on-off-and-sound-button-for-china-tablet-pc-10-1-6-8-7-7-85-8-9-9-7-tablets-type-3/");
        scanList.add("https://all-spares.ua/ru/case-baseus-cylinder-slide-cover-black-waterproof-universal-pouch-silicone-plastic-acfsd-e01/");
        scanList.add("https://all-spares.ua/ru/case-compatible-with-xiaomi-redmi-10c-black-original-soft-case-silicone-black-18/");        scanList.add("https://all-spares.ua/ru/on-off-and-sound-button-for-china-tablet-pc-10-1-6-8-7-7-85-8-9-9-7-tablets-type-3/");


        replacePrice.add(new ReplaceString(" ", ""));
        replacePrice.add(new ReplaceString("₴", ""));
        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJfwBpwUz16Eb7hfplc7eUbouyNQqkS237h68BCA5FyeW8yFDwpMKKPuez9UtaurFuQ");
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
        spc.setMaxRetriesLoadPage(5);
        spc.setReplacePrice(replacePrice);
        spc.setCookies(cookies);

        ProductParser pp = new ProductParser();
        pp.getProduct(spc, scanList);
    }
}
