//package ua.com.mobifix.parser;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import ua.com.mobifix.entity.Shop;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class RunAllProduct {
//    public static void main(String[] args) throws IOException {
//    Shop sps = new Shop();
//    AllProductParser app = new AllProductParser();
//    Map<String, String> cookies = new HashMap<>();
//    ArrayList<ReplaceString> replaceArticleStringList = new ArrayList();
//    ArrayList<ReplaceString> replaceStockStringList = new ArrayList();
//    ArrayList<ReplaceString> replacePriceStringList = new ArrayList();
//    ArrayList<ReplaceString> containArticleStringList = new ArrayList();
//    ArrayList<ReplaceString> containStockStringList = new ArrayList();
//    ArrayList<ReplaceString> containPriceStringList = new ArrayList();
//
////    Object[] repl = new Object[2];
////    List<String[]> array = new ArrayList<>();
////    array.add(repl[0] = "", repl[1] = "s" );
//
////all-spares
//    //инициализация cookie
//    cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJQ4cog1s%2Fypdp9%2BRQtnVRaW%2FyMBqVGY%2BJtpMqLyKaFaeQwYHrzwkLHE1LbCMryFs7w");
//    //запись cookie
//    sps.setCookies(cookies);
//    //запись селектора CSS для парсинга названия товара
//    sps.setName("div.component_product_list_info_right > a");
//    //запись селектора CSS для парсинга артикула товара
//    sps.setArticle("span.component_product_list_product-info_item.info-id > span");
//    //запись селектора CSS для парсинга ссылки на карточку товара
//    sps.setLink("div.component_product_list_info_right > a");
//    //запись селектора CSS для парсинга ссылки на изображение товара
//    sps.setImageLink("div.component_product_list_image-container > a > picture > img");
//    //запись селектора CSS для парсинга статуса наличия товара
//    sps.setStock("div.component_product_list_in-stock.type-2 > div > ul");
//    //запись селектора CSS для парсинга цены товара
//    sps.setPrice("div.-current.-red");
//    //запись селектора CSS для парсинга атрибута ссылки
//    sps.setHref("href");
//    //запись селектора CSS для парсинга атрибута изображения
//    sps.setSrc("lazy-src");
//    //запись префикса для добавления его к ссылке внутри маазина
//    sps.setLinkPrefix("https://all-spares.ua");
//    //запись селектора CSS для парсинга списка карточкек товаров
//    sps.setProductListCart("div.row.d-flex.product-cards-wrapper");
//    //запись селектора CSS для парсинга карточки товара
//    sps.setProductCart("div.row.d-flex.product-cards-wrapper > div > div > div");
//    //запись ссылки на категории товаров
//    sps.setScanUrl("https://all-spares.ua/ru/search/?searchword=882809");
//    //запись настройки пагинации
//    sps.setPagination("?page=");
//    sps.setParameter("&ipp=192");
//
////    sps.setReplacePrice(" ₴");
//
//
////        //aks.ua
////        //инициализация cookie
////        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJQ4cog1s%2Fypdp9%2BRQtnVRaW%2FyMBqVGY%2BJtpMqLyKaFaeQwYHrzwkLHE1LbCMryFs7w");
////        //запись cookie
////        sps.setCookies(cookies);
////        //запись селектора CSS для парсинга названия товара
////        sps.setName("div.catalog-default > div > div > div.catalog-name");
////        //запись селектора CSS для парсинга артикула товара
////        sps.setArticle("div.catalog-item-id");
////        //запись селектора CSS для парсинга ссылки на карточку товара
////        sps.setLink("div.catalog-default > div > div > div.catalog-name > a");
////        //запись селектора CSS для парсинга атрибута ссылки
////        sps.setHref("href");
////        //запись селектора CSS для парсинга ссылки на изображение товара
////        sps.setImageLink("div.catalog-img > a > img");
////        //запись селектора CSS для парсинга атрибута изображения
////        sps.setSrc("src");
////        //запись селектора CSS для парсинга статуса наличия товара
////        sps.setStock("div.catalog-default > div > div > div.catalog-bottom-box > div.catalog-item-id.catalog-bottom.inactive");
////        //запись селектора CSS для парсинга цены товара
////        sps.setPrice("div.catalog-price-new");
////        //запись префикса для добавления к ссылке карточки товара
////        sps.setLinkPrefix("https://www.aks.ua");
////        //запись префикса для добавления к ссылке изображения товара
////        sps.setImagePrefix("https://www.aks.ua");
////        //запись селектора CSS для парсинга списка карточкек товаров
////        sps.setProductListCart("div.catalog-default");
////        //запись селектора CSS для парсинга карточки товара
////        sps.setProductCart("div.catalog-default > div > div");
////        //запись ссылки на категории товаров
////        sps.setScanUrl("https://www.aks.ua/catalog/mobilnye-telefony/");
////        //запись настройки пагинации
////        sps.setPagination("page/");
////        sps.setParameter("");
////        containStockStringList.add(new ReplaceString("нет в наличии ","нет в наличии"));
////        sps.setContainStock(containStockStringList);
////
////        replaceArticleStringList.add(new ReplaceString("Код товара: ", ""));
////        replaceArticleStringList.add(new ReplaceString("нет в наличии ", ""));
////        sps.setReplaceArticle(replaceArticleStringList);
////
////        replacePriceStringList.add(new ReplaceString(" ", ""));
////        replacePriceStringList.add(new ReplaceString("грн", ""));
////        sps.setReplacePrice(replacePriceStringList);
//
////        //
////        //инициализация cookie
////        cookies.put("product_items_per_page", "48");
////        cookies.put("auth", "35954c99d106a87f6bfb68844e33366b3f352774");
////        cookies.put("site_lang", "ru");
////        //запись cookie
////        sps.setCookies(cookies);
////        //запись селектора CSS для парсинга названия товара
////        sps.setName("div.cs-product-gallery__title");
////        //запись селектора CSS для парсинга артикула товара
////        sps.setArticle("div.cs-product-list__sku.cs-goods-sku > span");
////        //запись селектора CSS для парсинга ссылки на карточку товара
////        sps.setLink("a.cs-goods-title");
////        //запись селектора CSS для парсинга атрибута ссылки
////        sps.setHref("href");
////        //запись селектора CSS для парсинга ссылки на изображение товара
////        sps.setImageLink("div.cs-product-gallery__image-panel > a > img");
////        //запись селектора CSS для парсинга атрибута изображения
////        sps.setSrc("src");
////        //запись селектора CSS для парсинга статуса наличия товара
////        sps.setStock("span.cs-goods-data__state.cs-goods-data__state_val_avail");
////        //запись селектора CSS для парсинга цены товара
////        sps.setPrice("span.cs-goods-price__value.cs-goods-price__value_type_current.cs-goods-price__value_type_product-list");
////        //запись префикса для добавления к ссылке карточки товара
////        sps.setLinkPrefix("https://smartparts.in.ua");
////        //запись префикса для добавления к ссылке изображения товара
////        sps.setImagePrefix("");
////        //запись селектора CSS для парсинга списка карточкек товаров
////        sps.setProductListCart("body > div.cs-page > div.cs-page__main-content > div > div > div > ul");
////        //запись селектора CSS для парсинга карточки товара
////        sps.setProductCart("body > div.cs-page > div.cs-page__main-content > div > div > div > ul > li");
////        //запись ссылки на категории товаров
////        sps.setScanUrl("https://smartparts.in.ua/product_list");
////        //запись настройки пагинации
////        sps.setPagination("/page_");
////        sps.setParameter("?product_items_per_page=48");
////        sps.setReplacePrice("₴");
////        sps.setReplacementPrice("");
//
////        try (FileWriter writer = new FileWriter("..\\webParser\\src\\main\\resources\\data\\settings\\" + sps.getLinkPrefix()
////                .replace("/", "-")
////                .replace(":", "") + "_settings.json")) {
////            Gson gson = new GsonBuilder().setPrettyPrinting().create();
////            gson.toJson(sps, writer);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    int num = 1;
//   app.getProducts(sps, num);
//    }
//}
