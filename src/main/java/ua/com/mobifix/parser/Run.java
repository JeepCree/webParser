package ua.com.mobifix.parser;

public class Run {
    public static void main(String[] args) {
//        String url = "https://all-spares.ua";
//        String endPoint = "/ru/full-catalog/";
//        String getByClass = "page_full-catalog_facet_title_link";
//        String select = ".page_full-catalog_facet_title_text";
//        String href = "href";
//        boolean addLink = true;
//
//        String url = "https://www.aks.ua";
//        String endPoint = "/uk/sitemap.html";
//        String getByClass = "sitemap_title";
//        String select = "a";
//        String href = "href";
//        boolean addLink = true;
//
//        String url = "https://hotline.ua";
//        String endPoint = "/";
//        String getByClass = "categories-section__link";
//        String select = ".text";
//        String href = "href";
//        boolean addLink = true;

//        String url = "https://www.service-market.com.ua";
//        String endPoint = "/";
//        String getByClass = "main-nav__link";
//        String select = "span.main-nav__title";
//        String href = "href";
//        boolean addLink = false;

        String url = "https://www.moyo.ua";
        String endPoint = "";
        String getByClass = "menu_link";
        String select = "a";
        String href = "href";
        boolean needLink = true;

        new Parser().getCatalog(url, endPoint, getByClass, select, href, needLink);

    }
}
