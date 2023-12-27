package ua.com.mobifix.parser;

import java.util.HashMap;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        ScanCategorySettings settings = new ScanCategorySettings();
        CategoryParser parser = new CategoryParser();
//++
//        String urlShop = "https://all-spares.ua";
//        String endPoint = "/ru/full-catalog/";
//        String getByClass = "body > div:nth-child(2) > div > header > div.widget_site-menu > div > div.menu-row > div > div > div.layout_header_quick-catalog_menu_nav > a";
//        String select = "a";
//        String href = "href";
//        String urlPrefix = "https://all-spares.ua";
//++
//        String urlShop = "https://www.aks.ua";
//        String endPoint = "/";
//        String getByClass = ".nav-list-block > ul > li > a";
//        String select = "a";
//        String href = "href";
//        String urlPrefix = "https://www.aks.ua";
//++
//        String urlShop = "https://hotline.ua";
//        String endPoint = "/";
//        String getByClass = "#__layout > div > div.default-layout__content-container > main > section.index-page-section.categories-section > div > a";
//        String select = "a";
//        String href = "href";
//        String urlPrefix = "https://hotline.ua";
//++
//        String urlShop = "https://www.service-market.com.ua";
//        String endPoint = "/";
//        String getByClass = "body > div.page__body > div.page__wrapper > div.page__container > div > div.col-md-3.page__mainnav-vertical.hidden-xs.hidden-sm > nav > ul > li";
//        String select = "body > div.page__body > div.page__wrapper > div.page__container > div > div.col-md-3.page__mainnav-vertical.hidden-xs.hidden-sm > nav > ul > li > a";
//        String href = "href";
//        String urlPrefix = "";
//++
//        String urlShop = "https://www.moyo.ua";
//        String endPoint = "";
//        String getByClass = "body > div.main-wrap > main > div > section > div.catalog-banner > div.catalog-container > ul > li";
//        String select = "body > div.main-wrap > main > div > section > div.catalog-banner > div.catalog-container > ul > li > span";
//        String href = "data-href";
//        String urlPrefix = "https://www.moyo.ua";
//++
//        String urlShop = "https://artmobile.ua/";
//        String endPoint = "";
//        String getByClass = "#sb-site > div.wrapper > div.container-fluid > div > div.col-md-3.col-md-pull-7.cats > div.category-sidebar.hidden-xs.hidden-sm > div.cats-menu > ul > li";
//        String select = "#sb-site > div.wrapper > div.container-fluid > div > div.col-md-3.col-md-pull-7.cats > div.category-sidebar.hidden-xs.hidden-sm > div.cats-menu > ul > li > a";
//        String href = "href";
//        String urlPrefix = "https:";

//        String urlShop = "https://mobiking.com.ua/";
//        String getByClass = "#body > div.content > section.baner-wrap > div > div > div.left-menu-wrap > ul > li";
//        String select = "#body > div.content > section.baner-wrap > div > div > div.left-menu-wrap > ul > li > div > a";
//        String href = "href";
//        String urlPrefix = "https://mobiking.com.ua";
//        String login = "sessionid";
//        String password = "hhzhtncwlsvjnk2uhodn83ccntdtjc7r";
//        String replace = "Все з категорії: ";
//        String replacement = "";
//mobiking
//        settings.setUrlShop("https://mobiking.com.ua/");
//        settings.setSelectCategoryTag("#body > div.content > section.baner-wrap > div > div > div.left-menu-wrap > ul > li");
//        settings.setSelectCategoryNameTag("#body > div.content > section.baner-wrap > div > div > div.left-menu-wrap > ul > li > div > a");
//        settings.setSelectCategoryAttrHref("href");
//        settings.setUrlPrefix("https://mobiking.com.ua");
//        settings.setCookieName("sessionid");
//        settings.setCookieValue("hhzhtncwlsvjnk2uhodn83ccntdtjc7r");
//        settings.setReplaceCategoryName("Все з категорії: ");
//        settings.setReplacementCategoryName("");

//AKS
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
//        cookies.put("visitor", "9ea6fa5e03088598d37dce644143026b");
//        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJSCoJVtjpygDy%2BSnDEbI000vkgd8MMDzPHrcFP%2BQ%2BwQ4%2Bdbk9e5I%2FZMpDPKLfKqIGA");
//        cookies.put("language", "5");
//        settings.setCookies(cookies);

//moyo
        settings.setUrlShop("https://www.moyo.ua");
        settings.setSelectCategoryTag("body > div.main-wrap > main > div > section > div.catalog-banner > div.catalog-container > ul > li");
        settings.setSelectCategoryNameTag("li > span");
        settings.setSelectCategoryAttrHref("data-href");
        settings.setUrlPrefix("https://www.moyo.ua");
        settings.setSelectCategoryTagLevel2("#main-wrap > main > div.seo-order.container > div.portal-category-list > div");
        settings.setSelectCategoryNameTagLevel2("div > a.portal-category-list__title");
        settings.setSelectCategoryAttrHrefLevel2("href");
        settings.setSelectCategoryTagLevel3("#main-wrap > main > div.seo-order.container > div.portal-category-list > div > a.portal-category-list__title");
        settings.setSelectCategoryNameTagLevel3("a.portal-category-list__title");
        settings.setSelectCategoryAttrHrefLevel3("href");
        settings.setSelectCategoryTagLevel4("#main-wrap > main > div.seo-order.container > div.portal-category-list > div > a.portal-category-list__title");
        settings.setSelectCategoryNameTagLevel4("a.portal-category-list__title");
        settings.setSelectCategoryAttrHrefLevel4("href");
        settings.setSelectCategoryTagLevel5("#main-wrap > main > div.seo-order.container > div.portal-category-list > div > a.portal-category-list__title");
        settings.setSelectCategoryNameTagLevel5("a.portal-category-list__title");
        settings.setSelectCategoryAttrHrefLevel5("href");
        Map<String, String> cookies = new HashMap<>();
        cookies.put("lang", "ru");
        cookies.put("PHPSESSID", "33sbs0ffqo3vl4qj8oqd43h756");
        cookies.put("publicKey", "5698d8d0069ded5dc87318f440d0971f");
        settings.setCookies(cookies);

//hotline
//        settings.setUrlShop("https://hotline.ua");
//        settings.setSelectCategoryTag("#__layout > div > div.default-layout__content-container > main > section.index-page-section.categories-section > div > a");
//        settings.setSelectCategoryNameTag("a");
//        settings.setSelectCategoryAttrHref("href");
//        settings.setUrlPrefix("https://hotline.ua");
//        settings.setSelectCategoryTagLevel2("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
//        settings.setSelectCategoryNameTagLevel2("a");
//        settings.setSelectCategoryAttrHrefLevel2("href");
//        settings.setSelectCategoryTagLevel3("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
//        settings.setSelectCategoryNameTagLevel3("a");
//        settings.setSelectCategoryAttrHrefLevel3("href");
//        settings.setSelectCategoryTagLevel4("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
//        settings.setSelectCategoryNameTagLevel4("a");
//        settings.setSelectCategoryAttrHrefLevel4("href");
//        settings.setSelectCategoryTagLevel5("#__layout > div > div.default-layout__content-container > div > div.row.flex-wrap > div > div > div > div > div.catalog-links > div > a");
//        settings.setSelectCategoryNameTagLevel5("a");
//        settings.setSelectCategoryAttrHrefLevel5("href");
//        settings.setReplaceCategoryUrl("https://hotline.ua//hotline.finance/");
//        settings.setReplacementCategoryUrl("https://hotline.finance/");

        for (AllScanCategory el : parser.getCatalog(settings, 0L)) {
            System.out.println(el.getCategoryId() + " " + el.getCategoryName() + " " + el.getCategoryUrl() + " " + el.getParentCategoryId());
        }

    }
}