package ua.com.mobifix.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RunProduct {
    public static void main(String[] args) {
        ArrayList<String> scanList = new ArrayList<>();
        scanList.add("https://all-spares.ua/ru/lcd-compatible-with-xiaomi-redmi-9a-black-with-touchscreen-original-prc-m2006c3lg/");
        ScanProductSettings sps = new ScanProductSettings();
        Map<String, String> cookies = new HashMap<>();
        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJQ4cog1s%2Fypdp9%2BRQtnVRaW%2FyMBqVGY%2BJtpMqLyKaFaeQwYHrzwkLHE1LbCMryFs7w");
        cookies.put("language", "5");
        sps.setArticle("span.prp_info_item.info-id > span");
        sps.setName("div.sop-main-content > div > main > div > div > div > h1");
        sps.setStock("div.prp_info > div > div > ul");
        sps.setPrice("div.prp_static-button.action-zone.acz.-main > div > div > div > div > div.-current");
        sps.setBreadcrumbs("div.sop-main-content > div > main > div > div > div > div > ul > li > a");
        sps.setLink("div.s-g_dropdown > div > a");
        sps.setImageLink("div.gallery-main-wrap > div > picture > img");
        sps.setHref("href");
        sps.setLinkPrefix("https://all-spares.ua");
        sps.setSrc("src");
        sps.setCookies(cookies);

        ProductParser pp = new ProductParser();
        pp.getProduct(sps, scanList);
    }
}
