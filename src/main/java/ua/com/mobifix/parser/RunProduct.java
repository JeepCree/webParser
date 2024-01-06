package ua.com.mobifix.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RunProduct {
    public static void main(String[] args) {
        ArrayList<String> scanList = new ArrayList<>();
        scanList.add("https://all-spares.ua/uk/lcd-compatible-with-xiaomi-redmi-9a-black-with-touchscreen-original-prc-m2006c3lg/");
        ScanProductSettings spc = new ScanProductSettings();
        Map<String, String> cookies = new HashMap<>();
        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJQ4cog1s%2Fypdp9%2BRQtnVRaW%2FyMBqVGY%2BJtpMqLyKaFaeQwYHrzwkLHE1LbCMryFs7w");
        cookies.put("language", "6");
        spc.setArticle("span.prp_info_item.info-id > span");
        spc.setName("div.sop-main-content > div > main > div > div > div > h1");
        spc.setStock("div.prp_info > div > div > ul");
        spc.setPrice("div.-old");
        spc.setBreadcrumbs("div.sop-main-content > div > main > div > div > div > div > ul");
        spc.setLink("div.s-g_dropdown > div > a");
        spc.setImageLink("div.gallery-main-wrap > div > picture > img");
        spc.setHref("href");
        spc.setLinkPrefix("https://all-spares.ua");
        spc.setSrc("src");
        spc.setCookies(cookies);

        ProductParser pp = new ProductParser();
        pp.getProduct(spc, scanList);
    }
}
