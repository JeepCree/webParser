package ua.com.mobifix.parser;

public class Run {
    public static void main(String[] args) {
        ScanCategorySettings settings = new ScanCategorySettings();
        Parser parser = new Parser();
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
        settings.setUrlShop("https://www.aks.ua");
        settings.setSelectCategoryTag(".nav-list-block > ul > li > a");
        settings.setSelectCategoryNameTag("a");
        settings.setSelectCategoryAttrHref("href");
        settings.setUrlPrefix("https://www.aks.ua");

        parser.getCatalog(settings, 1005L);

    }
}
