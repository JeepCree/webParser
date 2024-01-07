package ua.com.mobifix.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScanProductSettings {
    private String article = "";
    private String name = "";
    private String stock = "";
    private String price = "";
    private String breadcrumbs = "";
    private String link = "";
    private String imageLink = "";
    private String linkPrefix = "";
    private String imagePrefix = "";
    private String href = "";
    private String src = "";
    private ArrayList<ReplaceString> replaceArticle = new ArrayList<>();
    private ArrayList<ReplaceString> replaceStock = new ArrayList<>();
    private ArrayList<ReplaceString> replacePrice = new ArrayList<>();
    private ArrayList<ReplaceString> replaceBreadcrumbs = new ArrayList<>();
    private ArrayList<ReplaceString> containArticle = new ArrayList<>();
    private ArrayList<ReplaceString> containStock = new ArrayList<>();
    private ArrayList<ReplaceString> containPrice = new ArrayList<>();
    private ArrayList<ReplaceString> containBreadcrumbs = new ArrayList<>();
    private Map cookies = new HashMap();

    public Map getCookies() {
        return cookies;
    }

    public void setCookies(Map cookies) {
        this.cookies = cookies;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getLinkPrefix() {
        return linkPrefix;
    }

    public void setLinkPrefix(String linkPrefix) {
        this.linkPrefix = linkPrefix;
    }

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
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

    public ArrayList<ReplaceString> getReplacePrice() {
        return replacePrice;
    }

    public void setReplacePrice(ArrayList<ReplaceString> replacePrice) {
        this.replacePrice = replacePrice;
    }

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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(String breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
