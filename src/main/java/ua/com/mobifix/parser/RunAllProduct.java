package ua.com.mobifix.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RunAllProduct {
    public static void main(String[] args) throws IOException {
    ScanProductSettings sps = new ScanProductSettings();
    AllProductParser app = new AllProductParser();

    Map<String, String> cookies = new HashMap<>();
    //инициализация cookie
    cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJQ4cog1s%2Fypdp9%2BRQtnVRaW%2FyMBqVGY%2BJtpMqLyKaFaeQwYHrzwkLHE1LbCMryFs7w");
    //запись cookie
    sps.setCookies(cookies);
    //запись селектора CSS для парсинга названия товара
    sps.setName("div.component_product_list_info_right > a");
    //запись селектора CSS для парсинга артикула товара
    sps.setArticle("span.component_product_list_product-info_item.info-id > span");
    //запись селектора CSS для парсинга ссылки на карточку товара
    sps.setLink("div.component_product_list_info_right > a");
    //запись селектора CSS для парсинга ссылки на изображение товара
    sps.setImageLink("div.component_product_list_image-container > a > picture > img");
    //запись селектора CSS для парсинга статуса наличия товара
    sps.setStock("div.component_product_list_in-stock.type-2 > div > ul");
    //запись селектора CSS для парсинга цены товара
    sps.setPrice("div.-current.-red");
    //запись селектора CSS для парсинга атрибута ссылки
    sps.setHref("href");
    //запись селектора CSS для парсинга атрибута изображения
    sps.setSrc("src");
    //запись префикса для добавления его к ссылке внутри маазина
    sps.setPrefix("https://all-spares.ua");
    //запись селектора CSS для парсинга списка карточкек товаров
    sps.setProductListCart("div.row.d-flex.product-cards-wrapper");
    //запись селектора CSS для парсинга карточки товара
    sps.setProductCart("div.row.d-flex.product-cards-wrapper > div > div > div");
    //запись ссылки на категории товаров
    sps.setScanUrl("https://all-spares.ua/ru/spares/batteries/");
    //запись настройки пагинации
    sps.setPagination("&page=");
    sps.setParameter("?ipp=192");
    sps.setReplacePrice(" ₴");
    sps.setReplacementPrice("");


    int num = 1;
   app.getProducts(sps, num);
    }
}
