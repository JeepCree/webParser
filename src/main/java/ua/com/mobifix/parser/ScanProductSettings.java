package ua.com.mobifix.parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private ArrayList<ReplaceString> replaceArticle = new ArrayList<>();
    private ArrayList<ReplaceString> replaceStock = new ArrayList<>();
    private ArrayList<ReplaceString> replacePrice = new ArrayList<>();
    private ArrayList<ReplaceString> containArticle = new ArrayList<>();
    private ArrayList<ReplaceString> containStock = new ArrayList<>();
    private ArrayList<ReplaceString> containPrice = new ArrayList<>();
    private Map cookies;


    public ArrayList<ReplaceString> getContainArticle() {
        return containArticle;
    }

    public void setContainArticle(ArrayList<ReplaceString> containArticle) {
        this.containArticle = containArticle;
    }

    public ArrayList<ReplaceString> getContainStock() {
        return containStock;
    }

    public void setContainStock(ArrayList<ReplaceString> containStock) {
        this.containStock = containStock;
    }

    public ArrayList<ReplaceString> getContainPrice() {
        return containPrice;
    }

    public void setContainPrice(ArrayList<ReplaceString> containPrice) {
        this.containPrice = containPrice;
    }

    public ArrayList<ReplaceString> getReplaceArticle() {
        return replaceArticle;
    }

    public void setReplaceArticle(ArrayList<ReplaceString> replaceArticle) {
        this.replaceArticle = replaceArticle;
    }

    public ArrayList<ReplaceString> getReplaceStock() {
        return replaceStock;
    }

    public void setReplaceStock(ArrayList<ReplaceString> replaceStock) {
        this.replaceStock = replaceStock;
    }

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

    public ArrayList<ReplaceString> getReplacePrice() {
        return replacePrice;
    }

    public void setReplacePrice(ArrayList<ReplaceString> replacePrice) {
        this.replacePrice = replacePrice;
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
