package ua.com.mobifix.parser;

import java.util.Map;

public class ScanProductSettings {

    private String article;
    private String name;
    private String price;
    private String stock;
    private String link;
    private String breadcrumbs;
    private String href;
    private String src;
    private String imageLink;
    private String productCart;
    private String productListCart;
    private String pagination;
    private String parameter;
    private String scanUrl;
    private String linkPrefix;
    private String imagePrefix;
    private String replacePrice;
    private String replacementPrice;
    private Map cookies;

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getProductListCart() {
        return productListCart;
    }

    public void setProductListCart(String productListCart) {
        this.productListCart = productListCart;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getReplacePrice() {
        return replacePrice;
    }

    public void setReplacePrice(String replacePrice) {
        this.replacePrice = replacePrice;
    }

    public String getReplacementPrice() {
        return replacementPrice;
    }

    public void setReplacementPrice(String replacementPrice) {
        this.replacementPrice = replacementPrice;
    }

    public String getScanUrl() {
        return scanUrl;
    }

    public void setScanUrl(String scanUrl) {
        this.scanUrl = scanUrl;
    }

    public String getProductCart() {
        return productCart;
    }

    public void setProductCart(String productCart) {
        this.productCart = productCart;
    }

    public String getLinkPrefix() {
        return linkPrefix;
    }

    public void setLinkPrefix(String linkPrefix) {
        this.linkPrefix = linkPrefix;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Map getCookies() {
        return cookies;
    }

    public void setCookies(Map cookies) {
        this.cookies = cookies;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(String breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }
}
