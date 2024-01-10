package ua.com.mobifix.parser;

import ua.com.mobifix.entity.Categories;

import java.util.HashMap;
import java.util.Map;

public class RunCategory {
    public static void main(String[] args) {
        ScanCategorySettings settings = new ScanCategorySettings();
        CategoryParser parser = new CategoryParser();



//artmobile
//        settings.setShopName("artmobile.ua");
//        settings.setUrlShop("https://artmobile.ua/");
//        settings.setSelectCategoryTag("#sb-site > div.wrapper > div.container-fluid > div > div.col-md-3.col-md-pull-7.cats > div.category-sidebar.hidden-xs.hidden-sm > div.cats-menu > ul > li");
//        settings.setSelectCategoryNameTag("#sb-site > div.wrapper > div.container-fluid > div > div.col-md-3.col-md-pull-7.cats > div.category-sidebar.hidden-xs.hidden-sm > div.cats-menu > ul > li > a");
//        settings.setSelectCategoryAttrHref("href");
//        settings.setUrlPrefix("https:");
//        settings.setSelectCategoryTagLevel2("#sb-site > div.wrapper > div.container-fluid > div > div.col-md-3.col-md-pull-9.cats > div.category-sidebar.hidden-xs.hidden-sm > div.cats-menu > ul > li > ul > li > a");
//        settings.setSelectCategoryNameTagLevel2("a");
//        settings.setSelectCategoryAttrHrefLevel2("href");
//        settings.setSelectCategoryTagLevel3("empty");
//        settings.setSelectCategoryNameTagLevel3("empty");
//        settings.setSelectCategoryAttrHrefLevel3("empty");
//        settings.setSelectCategoryTagLevel4("empty");
//        settings.setSelectCategoryNameTagLevel4("empty");
//        settings.setSelectCategoryAttrHrefLevel4("empty");
//        settings.setSelectCategoryTagLevel5("empty");
//        settings.setSelectCategoryNameTagLevel5("empty");
//        settings.setSelectCategoryAttrHrefLevel5("empty");
//        Map<String, String> cookies = new HashMap<>();
//        cookies.put("PHPSESSID", "c7e6a1b83e31b95148a7c48de216b729");
//        cookies.put("dcjq-accordion", "");
//        settings.setCookies(cookies);

//mobiking
//        settings.setShopName("mobiking.com.ua");
//        settings.setUrlShop("https://mobiking.com.ua/ru/");
//        settings.setSelectCategoryTag("#body > div.content > section.baner-wrap > div > div > div.left-menu-wrap > ul > li");
//        settings.setSelectCategoryNameTag("#body > div.content > section.baner-wrap > div > div > div.left-menu-wrap > ul > li > div > a");
//        settings.setSelectCategoryAttrHref("href");
//        settings.setUrlPrefix("https://mobiking.com.ua");
//        settings.setSelectCategoryTagLevel2("#categories-panel > div.panel-body.in.collapse > div > div > h5");
//        settings.setSelectCategoryNameTagLevel2("#categories-panel > div.panel-body.in.collapse > div > div > h5 > a");
//        settings.setSelectCategoryAttrHrefLevel2("href");
//        settings.setSelectCategoryTagLevel3("#categories-panel > div.panel-body.in.collapse > div > div > h5");
//        settings.setSelectCategoryNameTagLevel3("#categories-panel > div.panel-body.in.collapse > div > div > h5 > a");
//        settings.setSelectCategoryAttrHrefLevel3("href");
//        settings.setSelectCategoryTagLevel4("#categories-panel > div.panel-body.in.collapse > div > div > h5");
//        settings.setSelectCategoryNameTagLevel4("#categories-panel > div.panel-body.in.collapse > div > div > h5 > a");
//        settings.setSelectCategoryAttrHrefLevel4("href");
//        settings.setSelectCategoryTagLevel5("#categories-panel > div.panel-body.in.collapse > div > div > h5");
//        settings.setSelectCategoryNameTagLevel5("#categories-panel > div.panel-body.in.collapse > div > div > h5 > a");
//        settings.setSelectCategoryAttrHrefLevel5("href");
//        settings.setReplaceCategoryName("Всё из категории: ");
//        settings.setReplacementCategoryName("");
//        Map<String, String> cookies = new HashMap<>();
//        cookies.put("sessionid", "ppwo7pvrt8ur7lv8vz4vegkdgfuensxd");
//        cookies.put("csrftoken", "QdKPPxJkvzK1f46kOeXFv4gR0A34KJmr");
//        settings.setCookies(cookies);

//AKS
//        settings.setShopName("aks.ua");
//        settings.setUrlShop("https://www.aks.ua");
//        settings.setSelectCategoryTag(".nav-list-block > ul > li > a");
//        settings.setSelectCategoryNameTag("a");
//        settings.setSelectCategoryAttrHref("href");
//        settings.setUrlPrefix("https://www.aks.ua");
//        settings.setSelectCategoryTagLevel2("#mainContent > section.main > div > section.category > div > div > div.category-item > div.category-link");
//        settings.setSelectCategoryNameTagLevel2("a");
//        settings.setSelectCategoryAttrHrefLevel2("href");
//        settings.setSelectCategoryTagLevel3("#mainContent > section.main > div > section.category > div > div > div.category-item > div.category-link");
//        settings.setSelectCategoryNameTagLevel3("a");
//        settings.setSelectCategoryAttrHrefLevel3("href");
//        settings.setSelectCategoryTagLevel4("#mainContent > section.main > div > section.category > div > div > div.category-item > div.category-link");
//        settings.setSelectCategoryNameTagLevel4("a");
//        settings.setSelectCategoryAttrHrefLevel4("href");
//        settings.setSelectCategoryTagLevel5("#mainContent > section.main > div > section.category > div > div > div.category-item > div.category-link");
//        settings.setSelectCategoryNameTagLevel5("a");
//        settings.setSelectCategoryAttrHrefLevel5("href");
//        Map<String, String> cookies = new HashMap<>();
//        cookies.put("PHPSESSID", "egchs22pl3ugrcrmp7eah6at3a");
//        settings.setCookies(cookies);

//all-spares
//        settings.setShopName("all-spares.ua");
//        settings.setUrlShop("https://all-spares.ua");
//        settings.setSelectCategoryTag("body > div > div > header > div.widget_site-menu > div > div.menu-row > div > div > div.layout_header_quick-catalog_menu_nav > a");
//        settings.setSelectCategoryNameTag("a");
//        settings.setSelectCategoryAttrHref("href");
//        settings.setUrlPrefix("https://all-spares.ua");
//        settings.setSelectCategoryTagLevel2("body > div > div > div.sop-main-content > div > div > main > div.page_menu_category > div > div > article > div > a");
//        settings.setSelectCategoryNameTagLevel2("a");
//        settings.setSelectCategoryAttrHrefLevel2("href");
//        settings.setSelectCategoryTagLevel3("body > div > div > div.sop-main-content > div > div > div > div.col-12.col-md-8.col-lg-9 > main > div.row.d-flex.product-cards-wrapper > div > div > div > a");
//        settings.setSelectCategoryNameTagLevel3("a");
//        settings.setSelectCategoryAttrHrefLevel3("href");
//        settings.setSelectCategoryTagLevel4("body > div > div > div.sop-main-content > div > div > div > div.col-12.col-md-8.col-lg-9 > main > div.row.d-flex.product-cards-wrapper > div > div > div > a");
//        settings.setSelectCategoryNameTagLevel4("a");
//        settings.setSelectCategoryAttrHrefLevel4("href");
//        settings.setSelectCategoryTagLevel5("body > div > div > div.sop-main-content > div > div > div > div.col-12.col-md-8.col-lg-9 > main > div.row.d-flex.product-cards-wrapper > div > div > div > a");
//        settings.setSelectCategoryNameTagLevel5("a");
//        settings.setSelectCategoryAttrHrefLevel5("href");
//        Map<String, String> cookies = new HashMap<>();
//        cookies.put("visitor", "d0de6e4eb1e4479708300ffeee4bbcbb");
//        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJfwBpwUz16Eb7hfplc7eUbqIcOcmo4ugkcvwdoWqa39lQjJWtuMFN8k3EtG5fGcYFQ");
//        cookies.put("language", "5");
//        settings.setCookies(cookies);
//        settings.setShopId(5L);

//moyo
//        settings.setShopName("moyo.ua");
//        settings.setUrlShop("https://www.moyo.ua");
//        settings.setSelectCategoryTag("body > div.main-wrap > main > div > section > div.catalog-banner > div.catalog-container > ul > li");
//        settings.setSelectCategoryNameTag("li > span");
//        settings.setSelectCategoryAttrHref("data-href");
//        settings.setUrlPrefix("https://www.moyo.ua");
//        settings.setSelectCategoryTagLevel2("#main-wrap > main > div.seo-order.container > div.portal-category-list > div");
//        settings.setSelectCategoryNameTagLevel2("div > a.portal-category-list__title");
//        settings.setSelectCategoryAttrHrefLevel2("href");
//        settings.setSelectCategoryTagLevel3("#main-wrap > main > div.seo-order.container > div.portal-category-list > div > a.portal-category-list__title");
//        settings.setSelectCategoryNameTagLevel3("a.portal-category-list__title");
//        settings.setSelectCategoryAttrHrefLevel3("href");
//        settings.setSelectCategoryTagLevel4("#main-wrap > main > div.seo-order.container > div.portal-category-list > div > a.portal-category-list__title");
//        settings.setSelectCategoryNameTagLevel4("a.portal-category-list__title");
//        settings.setSelectCategoryAttrHrefLevel4("href");
//        settings.setSelectCategoryTagLevel5("#main-wrap > main > div.seo-order.container > div.portal-category-list > div > a.portal-category-list__title");
//        settings.setSelectCategoryNameTagLevel5("a.portal-category-list__title");
//        settings.setSelectCategoryAttrHrefLevel5("href");
//        Map<String, String> cookies = new HashMap<>();
//        cookies.put("lang", "ru");
//        cookies.put("PHPSESSID", "33sbs0ffqo3vl4qj8oqd43h756");
//        cookies.put("publicKey", "5698d8d0069ded5dc87318f440d0971f");
//        settings.setCookies(cookies);

//hotline
        settings.setShopName("hotline.ua");
        settings.setUrlShop("https://hotline.ua");
        settings.setSelectCategoryTag("#__layout > div > div.default-layout__content-container > main > section.index-page-section.categories-section > div > a");
        settings.setSelectCategoryNameTag("a");
        settings.setSelectCategoryAttrHref("href");
        settings.setUrlPrefix("https://hotline.ua");
        settings.setSelectCategoryTagLevel2("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
        settings.setSelectCategoryNameTagLevel2("a");
        settings.setSelectCategoryAttrHrefLevel2("href");
        settings.setSelectCategoryTagLevel3("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
        settings.setSelectCategoryNameTagLevel3("a");
        settings.setSelectCategoryAttrHrefLevel3("href");
        settings.setSelectCategoryTagLevel4("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
        settings.setSelectCategoryNameTagLevel4("a");
        settings.setSelectCategoryAttrHrefLevel4("href");
        settings.setSelectCategoryTagLevel5("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
        settings.setSelectCategoryNameTagLevel5("a");
        settings.setSelectCategoryAttrHrefLevel5("href");
        settings.setReplaceCategoryUrl("https://hotline.ua//hotline.finance/");
        settings.setReplacementCategoryUrl("https://hotline.finance/");

        for (Categories el : parser.getCatalog(settings, 0L)) {
            System.out.println(el.getId() + " " + el.getName() + " " + el.getUrl() + " " + el.getParentId());
        }

    }
}