package ua.com.mobifix.test;

import ua.com.mobifix.parser.breadcrumbs.BreadcrumbsParser;

public class test {
    public static void main(String[] args) {
        BreadcrumbsParser breadcrumbsParser = new BreadcrumbsParser();
        String breadcrumbs = breadcrumbsParser.parseHtmlToJson("").toString();
        System.out.println(breadcrumbs);
    }
}
