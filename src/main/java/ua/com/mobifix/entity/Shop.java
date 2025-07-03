package ua.com.mobifix.entity;

import jakarta.persistence.*;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Данные о магазине
    private Long idShop;
    private String nameShop;
    private String linkShop;
    //Данные о настройках сканирования каталога категорий магазина
    private String selectCategoryTag = "noTag";
    private String selectCategoryTagLevel2 = "noTag";
    private String selectCategoryTagLevel3 = "noTag";
    private String selectCategoryTagLevel4 = "noTag";
    private String selectCategoryTagLevel5 = "noTag";
    private String selectCategoryNameTag = "noNameTag";
    private String selectCategoryNameTagLevel2 = "noNameTag";
    private String selectCategoryNameTagLevel3 = "noNameTag";
    private String selectCategoryNameTagLevel4 = "noNameTag";
    private String selectCategoryNameTagLevel5 = "noNameTag";
    private String selectCategoryAttrHref = "noAttrHref";
    private String selectCategoryAttrHrefLevel2 = "noAttrHref";
    private String selectCategoryAttrHrefLevel3 = "noAttrHref";
    private String selectCategoryAttrHrefLevel4 = "noAttrHref";
    private String selectCategoryAttrHrefLevel5 = "noAttrHref";
    private String urlCategoryPrefix = "";
    private String urlCategoryPrefixLevel2 = "";
    private String urlCategoryPrefixLevel3 = "";
    private String urlCategoryPrefixLevel4 = "";
    private String urlCategoryPrefixLevel5 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryName = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryNameLevel2 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryNameLevel3 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryNameLevel4 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryNameLevel5 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryUrl = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryUrlLevel2 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryUrlLevel3 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryUrlLevel4 = "";
    @Column(columnDefinition = "TEXT")
    private String replaceCategoryUrlLevel5 = "";
    @Column(columnDefinition = "TEXT")
    private String noScanCategoryList = "";
    //Данные о настройках сканирования товаров в категории магазина
    private String scanProductsUrl;
    private String pagination = "1";
    private String parameter = "";
    private String urlProductsLinkPrefix = "";
    private String urlProductsImageLinkPrefix = "";
    private String selectProductsListCartTag = "noTag";
    private String selectProductsCartTag = "noTag";
    private String selectProductsArticleTag = "noTag";
    private String selectProductsNameTag = "noTag";
    private String selectProductsPcsTag = "noTag";
    private String selectProductsPriceTag = "noTag";
    private String selectProductsStockTag = "noTag";
    private String selectProductsLinkTag = "noTag";
    private String selectProductsImageLinkTag = "noTag";
    private String selectProductsBreadcrumbsTag = "noTag";
    private String selectProductsAttrHref = "noHrefTag";
    private String selectProductsAttrSrc = "noSrcTag";
    @Column(columnDefinition = "TEXT")
    private String replaceProductsArticle = "";
    @Column(columnDefinition = "TEXT")
    private String replaceProductsStock = "";
    @Column(columnDefinition = "TEXT")
    private String replaceProductsPrice = "";
    @Column(columnDefinition = "TEXT")
    private String containProductsArticle = "";
    @Column(columnDefinition = "TEXT")
    private String containProductsStock = "";
    @Column(columnDefinition = "TEXT")
    private String containProductsPrice = "";
    @Column(columnDefinition = "TEXT")
    //Данные о настройках сканирования карточки товара магазина
    private String scanProductUrl;
    private String urlProductLinkPrefix = "";
    private String urlProductImagePrefix = "";
    private String selectProductArticleTag = "";
    private String selectProductNameTag = "";
    private String selectProductPcsTag = "noTag";
    private String selectProductStockTag = "";
    private String selectProductPriceTag = "";
    private String selectProductBreadcrumbsTag = "";
    private String selectProductDescriptionTag = "";
    private String selectProductLinkTag = "";
    private String selectProductImageLinkTag = "";
    private String selectProductAttrHref = "";
    private String selectProductAttrSrc = "";
    private int maxRetriesLoadPage = 1;
    @Column(columnDefinition = "TEXT")
    private String replaceProductArticle = "";
    @Column(columnDefinition = "TEXT")
    private String replaceProductStock = "";
    @Column(columnDefinition = "TEXT")
    private String replaceProductPrice = "";
    @Column(columnDefinition = "TEXT")
    private String replaceProductBreadcrumbs = "";
    @Column(columnDefinition = "TEXT")
    private String containProductArticle = "";
    @Column(columnDefinition = "TEXT")
    private String containProductStock = "";
    @Column(columnDefinition = "TEXT")
    private String containProductPrice = "";
    @Column(columnDefinition = "TEXT")
    private String containProductBreadcrumbs = "";
    //Данные Cookies магазина
    @Column(columnDefinition = "TEXT")
    private String cookies = "noCookie1=noValue1; noCookie2=noValue2";
    @Column(columnDefinition = "TEXT")
    private String userAgent = "";

    public String getSelectProductsPcsTag() {
        return selectProductsPcsTag;
    }

    public void setSelectProductsPcsTag(String selectProductsPcsTag) {
        this.selectProductsPcsTag = selectProductsPcsTag;
    }

    public String getSelectProductPcsTag() {
        return selectProductPcsTag;
    }

    public void setSelectProductPcsTag(String selectProductPcsTag) {
        this.selectProductPcsTag = selectProductPcsTag;
    }

    public Long getIdShop() {
        return idShop;
    }

    public void setIdShop(Long idShop) {
        this.idShop = idShop;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getLinkShop() {
        return linkShop;
    }

    public void setLinkShop(String linkShop) {
        this.linkShop = linkShop;
    }

    public String getSelectCategoryTag() {
        return selectCategoryTag;
    }

    public void setSelectCategoryTag(String selectCategoryTag) {
        this.selectCategoryTag = selectCategoryTag;
    }

    public String getSelectCategoryTagLevel2() {
        return selectCategoryTagLevel2;
    }

    public void setSelectCategoryTagLevel2(String selectCategoryTagLevel2) {
        this.selectCategoryTagLevel2 = selectCategoryTagLevel2;
    }

    public String getSelectCategoryTagLevel3() {
        return selectCategoryTagLevel3;
    }

    public void setSelectCategoryTagLevel3(String selectCategoryTagLevel3) {
        this.selectCategoryTagLevel3 = selectCategoryTagLevel3;
    }

    public String getSelectCategoryTagLevel4() {
        return selectCategoryTagLevel4;
    }

    public void setSelectCategoryTagLevel4(String selectCategoryTagLevel4) {
        this.selectCategoryTagLevel4 = selectCategoryTagLevel4;
    }

    public String getSelectCategoryTagLevel5() {
        return selectCategoryTagLevel5;
    }

    public void setSelectCategoryTagLevel5(String selectCategoryTagLevel5) {
        this.selectCategoryTagLevel5 = selectCategoryTagLevel5;
    }

    public String getSelectProductDescriptionTag() {
        return selectProductDescriptionTag;
    }

    public void setSelectProductDescriptionTag(String selectProductDescriptionTag) {
        this.selectProductDescriptionTag = selectProductDescriptionTag;
    }

    public String getSelectCategoryNameTag() {
        return selectCategoryNameTag;
    }

    public void setSelectCategoryNameTag(String selectCategoryNameTag) {
        this.selectCategoryNameTag = selectCategoryNameTag;
    }

    public String getSelectCategoryNameTagLevel2() {
        return selectCategoryNameTagLevel2;
    }

    public void setSelectCategoryNameTagLevel2(String selectCategoryNameTagLevel2) {
        this.selectCategoryNameTagLevel2 = selectCategoryNameTagLevel2;
    }

    public String getSelectCategoryNameTagLevel3() {
        return selectCategoryNameTagLevel3;
    }

    public void setSelectCategoryNameTagLevel3(String selectCategoryNameTagLevel3) {
        this.selectCategoryNameTagLevel3 = selectCategoryNameTagLevel3;
    }

    public String getSelectCategoryNameTagLevel4() {
        return selectCategoryNameTagLevel4;
    }

    public void setSelectCategoryNameTagLevel4(String selectCategoryNameTagLevel4) {
        this.selectCategoryNameTagLevel4 = selectCategoryNameTagLevel4;
    }

    public String getSelectCategoryNameTagLevel5() {
        return selectCategoryNameTagLevel5;
    }

    public void setSelectCategoryNameTagLevel5(String selectCategoryNameTagLevel5) {
        this.selectCategoryNameTagLevel5 = selectCategoryNameTagLevel5;
    }

    public String getSelectCategoryAttrHref() {
        return selectCategoryAttrHref;
    }

    public void setSelectCategoryAttrHref(String selectCategoryAttrHref) {
        this.selectCategoryAttrHref = selectCategoryAttrHref;
    }

    public String getSelectCategoryAttrHrefLevel2() {
        return selectCategoryAttrHrefLevel2;
    }

    public void setSelectCategoryAttrHrefLevel2(String selectCategoryAttrHrefLevel2) {
        this.selectCategoryAttrHrefLevel2 = selectCategoryAttrHrefLevel2;
    }

    public String getSelectCategoryAttrHrefLevel3() {
        return selectCategoryAttrHrefLevel3;
    }

    public void setSelectCategoryAttrHrefLevel3(String selectCategoryAttrHrefLevel3) {
        this.selectCategoryAttrHrefLevel3 = selectCategoryAttrHrefLevel3;
    }

    public String getSelectCategoryAttrHrefLevel4() {
        return selectCategoryAttrHrefLevel4;
    }

    public void setSelectCategoryAttrHrefLevel4(String selectCategoryAttrHrefLevel4) {
        this.selectCategoryAttrHrefLevel4 = selectCategoryAttrHrefLevel4;
    }

    public String getSelectCategoryAttrHrefLevel5() {
        return selectCategoryAttrHrefLevel5;
    }

    public void setSelectCategoryAttrHrefLevel5(String selectCategoryAttrHrefLevel5) {
        this.selectCategoryAttrHrefLevel5 = selectCategoryAttrHrefLevel5;
    }

    public String getUrlCategoryPrefix() {
        return urlCategoryPrefix;
    }

    public void setUrlCategoryPrefix(String urlCategoryPrefix) {
        this.urlCategoryPrefix = urlCategoryPrefix;
    }

    public String getUrlCategoryPrefixLevel2() {
        return urlCategoryPrefixLevel2;
    }

    public void setUrlCategoryPrefixLevel2(String urlCategoryPrefixLevel2) {
        this.urlCategoryPrefixLevel2 = urlCategoryPrefixLevel2;
    }

    public String getUrlCategoryPrefixLevel3() {
        return urlCategoryPrefixLevel3;
    }

    public void setUrlCategoryPrefixLevel3(String urlCategoryPrefixLevel3) {
        this.urlCategoryPrefixLevel3 = urlCategoryPrefixLevel3;
    }

    public String getUrlCategoryPrefixLevel4() {
        return urlCategoryPrefixLevel4;
    }

    public void setUrlCategoryPrefixLevel4(String urlCategoryPrefixLevel4) {
        this.urlCategoryPrefixLevel4 = urlCategoryPrefixLevel4;
    }

    public String getUrlCategoryPrefixLevel5() {
        return urlCategoryPrefixLevel5;
    }

    public void setUrlCategoryPrefixLevel5(String urlCategoryPrefixLevel5) {
        this.urlCategoryPrefixLevel5 = urlCategoryPrefixLevel5;
    }

    public String getReplaceCategoryName() {
        return replaceCategoryName;
    }

    public void setReplaceCategoryName(String replaceCategoryName) {
        this.replaceCategoryName = replaceCategoryName;
    }

    public String getReplaceCategoryNameLevel2() {
        return replaceCategoryNameLevel2;
    }

    public void setReplaceCategoryNameLevel2(String replaceCategoryNameLevel2) {
        this.replaceCategoryNameLevel2 = replaceCategoryNameLevel2;
    }

    public String getReplaceCategoryNameLevel3() {
        return replaceCategoryNameLevel3;
    }

    public void setReplaceCategoryNameLevel3(String replaceCategoryNameLevel3) {
        this.replaceCategoryNameLevel3 = replaceCategoryNameLevel3;
    }

    public String getReplaceCategoryNameLevel4() {
        return replaceCategoryNameLevel4;
    }

    public void setReplaceCategoryNameLevel4(String replaceCategoryNameLevel4) {
        this.replaceCategoryNameLevel4 = replaceCategoryNameLevel4;
    }

    public String getReplaceCategoryNameLevel5() {
        return replaceCategoryNameLevel5;
    }

    public void setReplaceCategoryNameLevel5(String replaceCategoryNameLevel5) {
        this.replaceCategoryNameLevel5 = replaceCategoryNameLevel5;
    }

    public String getReplaceCategoryUrl() {
        return replaceCategoryUrl;
    }

    public void setReplaceCategoryUrl(String replaceCategoryUrl) {
        this.replaceCategoryUrl = replaceCategoryUrl;
    }

    public String getReplaceCategoryUrlLevel2() {
        return replaceCategoryUrlLevel2;
    }

    public void setReplaceCategoryUrlLevel2(String replaceCategoryUrlLevel2) {
        this.replaceCategoryUrlLevel2 = replaceCategoryUrlLevel2;
    }

    public String getReplaceCategoryUrlLevel3() {
        return replaceCategoryUrlLevel3;
    }

    public void setReplaceCategoryUrlLevel3(String replaceCategoryUrlLevel3) {
        this.replaceCategoryUrlLevel3 = replaceCategoryUrlLevel3;
    }

    public String getReplaceCategoryUrlLevel4() {
        return replaceCategoryUrlLevel4;
    }

    public void setReplaceCategoryUrlLevel4(String replaceCategoryUrlLevel4) {
        this.replaceCategoryUrlLevel4 = replaceCategoryUrlLevel4;
    }

    public String getReplaceCategoryUrlLevel5() {
        return replaceCategoryUrlLevel5;
    }

    public void setReplaceCategoryUrlLevel5(String replaceCategoryUrlLevel5) {
        this.replaceCategoryUrlLevel5 = replaceCategoryUrlLevel5;
    }

    public String getNoScanCategoryList() {
        return noScanCategoryList;
    }

    public void setNoScanCategoryList(String noScanCategoryList) {
        this.noScanCategoryList = noScanCategoryList;
    }

    public String getScanProductsUrl() {
        return scanProductsUrl;
    }

    public void setScanProductsUrl(String scanProductsUrl) {
        this.scanProductsUrl = scanProductsUrl;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getUrlProductsLinkPrefix() {
        return urlProductsLinkPrefix;
    }

    public void setUrlProductsLinkPrefix(String urlProductsLinkPrefix) {
        this.urlProductsLinkPrefix = urlProductsLinkPrefix;
    }

    public String getUrlProductsImageLinkPrefix() {
        return urlProductsImageLinkPrefix;
    }

    public void setUrlProductsImageLinkPrefix(String urlProductsImageLinkPrefix) {
        this.urlProductsImageLinkPrefix = urlProductsImageLinkPrefix;
    }

    public String getSelectProductsListCartTag() {
        return selectProductsListCartTag;
    }

    public void setSelectProductsListCartTag(String selectProductsListCartTag) {
        this.selectProductsListCartTag = selectProductsListCartTag;
    }

    public String getSelectProductsCartTag() {
        return selectProductsCartTag;
    }

    public void setSelectProductsCartTag(String selectProductsCartTag) {
        this.selectProductsCartTag = selectProductsCartTag;
    }

    public String getSelectProductsArticleTag() {
        return selectProductsArticleTag;
    }

    public void setSelectProductsArticleTag(String selectProductsArticleTag) {
        this.selectProductsArticleTag = selectProductsArticleTag;
    }

    public String getSelectProductsNameTag() {
        return selectProductsNameTag;
    }

    public void setSelectProductsNameTag(String selectProductsNameTag) {
        this.selectProductsNameTag = selectProductsNameTag;
    }

    public String getSelectProductsPriceTag() {
        return selectProductsPriceTag;
    }

    public void setSelectProductsPriceTag(String selectProductsPriceTag) {
        this.selectProductsPriceTag = selectProductsPriceTag;
    }

    public String getSelectProductsStockTag() {
        return selectProductsStockTag;
    }

    public void setSelectProductsStockTag(String selectProductsStockTag) {
        this.selectProductsStockTag = selectProductsStockTag;
    }

    public String getSelectProductsLinkTag() {
        return selectProductsLinkTag;
    }

    public void setSelectProductsLinkTag(String selectProductsLinkTag) {
        this.selectProductsLinkTag = selectProductsLinkTag;
    }

    public String getSelectProductsImageLinkTag() {
        return selectProductsImageLinkTag;
    }

    public void setSelectProductsImageLinkTag(String selectProductsImageLinkTag) {
        this.selectProductsImageLinkTag = selectProductsImageLinkTag;
    }

    public String getSelectProductsBreadcrumbsTag() {
        return selectProductsBreadcrumbsTag;
    }

    public void setSelectProductsBreadcrumbsTag(String selectProductsBreadcrumbsTag) {
        this.selectProductsBreadcrumbsTag = selectProductsBreadcrumbsTag;
    }

    public String getSelectProductsAttrHref() {
        return selectProductsAttrHref;
    }

    public void setSelectProductsAttrHref(String selectProductsAttrHref) {
        this.selectProductsAttrHref = selectProductsAttrHref;
    }

    public String getSelectProductsAttrSrc() {
        return selectProductsAttrSrc;
    }

    public void setSelectProductsAttrSrc(String selectProductsAttrSrc) {
        this.selectProductsAttrSrc = selectProductsAttrSrc;
    }

    public String getReplaceProductsArticle() {
        return replaceProductsArticle;
    }

    public void setReplaceProductsArticle(String replaceProductsArticle) {
        this.replaceProductsArticle = replaceProductsArticle;
    }

    public String getReplaceProductsStock() {
        return replaceProductsStock;
    }

    public void setReplaceProductsStock(String replaceProductsStock) {
        this.replaceProductsStock = replaceProductsStock;
    }

    public String getReplaceProductsPrice() {
        return replaceProductsPrice;
    }

    public void setReplaceProductsPrice(String replaceProductsPrice) {
        this.replaceProductsPrice = replaceProductsPrice;
    }

    public String getContainProductsArticle() {
        return containProductsArticle;
    }

    public void setContainProductsArticle(String containProductsArticle) {
        this.containProductsArticle = containProductsArticle;
    }

    public String getContainProductsStock() {
        return containProductsStock;
    }

    public void setContainProductsStock(String containProductsStock) {
        this.containProductsStock = containProductsStock;
    }

    public String getContainProductsPrice() {
        return containProductsPrice;
    }

    public void setContainProductsPrice(String containProductsPrice) {
        this.containProductsPrice = containProductsPrice;
    }

    public String getScanProductUrl() {
        return scanProductUrl;
    }

    public void setScanProductUrl(String scanProductUrl) {
        this.scanProductUrl = scanProductUrl;
    }

    public String getUrlProductLinkPrefix() {
        return urlProductLinkPrefix;
    }

    public void setUrlProductLinkPrefix(String urlProductLinkPrefix) {
        this.urlProductLinkPrefix = urlProductLinkPrefix;
    }

    public String getUrlProductImagePrefix() {
        return urlProductImagePrefix;
    }

    public void setUrlProductImagePrefix(String urlProductImagePrefix) {
        this.urlProductImagePrefix = urlProductImagePrefix;
    }

    public String getSelectProductArticleTag() {
        return selectProductArticleTag;
    }

    public void setSelectProductArticleTag(String selectProductAarticleTag) {
        this.selectProductArticleTag = selectProductAarticleTag;
    }

    public String getSelectProductNameTag() {
        return selectProductNameTag;
    }

    public void setSelectProductNameTag(String selectProductNameTag) {
        this.selectProductNameTag = selectProductNameTag;
    }

    public String getSelectProductStockTag() {
        return selectProductStockTag;
    }

    public void setSelectProductStockTag(String selectProductStockTag) {
        this.selectProductStockTag = selectProductStockTag;
    }

    public String getSelectProductPriceTag() {
        return selectProductPriceTag;
    }

    public void setSelectProductPriceTag(String selectProductPriceTag) {
        this.selectProductPriceTag = selectProductPriceTag;
    }

    public String getSelectProductBreadcrumbsTag() {
        return selectProductBreadcrumbsTag;
    }

    public void setSelectProductBreadcrumbsTag(String selectProductBreadcrumbsTag) {
        this.selectProductBreadcrumbsTag = selectProductBreadcrumbsTag;
    }

    public String getSelectProductLinkTag() {
        return selectProductLinkTag;
    }

    public void setSelectProductLinkTag(String selectProductLinkTag) {
        this.selectProductLinkTag = selectProductLinkTag;
    }

    public String getSelectProductImageLinkTag() {
        return selectProductImageLinkTag;
    }

    public void setSelectProductImageLinkTag(String selectProductImageLinkTag) {
        this.selectProductImageLinkTag = selectProductImageLinkTag;
    }

    public String getSelectProductAttrHref() {
        return selectProductAttrHref;
    }

    public void setSelectProductAttrHref(String selectProductAttrHref) {
        this.selectProductAttrHref = selectProductAttrHref;
    }

    public String getSelectProductAttrSrc() {
        return selectProductAttrSrc;
    }

    public void setSelectProductAttrSrc(String selectProductAttrSrc) {
        this.selectProductAttrSrc = selectProductAttrSrc;
    }

    public int getMaxRetriesLoadPage() {
        return maxRetriesLoadPage;
    }

    public void setMaxRetriesLoadPage(int maxRetriesLoadPage) {
        this.maxRetriesLoadPage = maxRetriesLoadPage;
    }

    public String getReplaceProductArticle() {
        return replaceProductArticle;
    }

    public void setReplaceProductArticle(String replaceProductArticle) {
        this.replaceProductArticle = replaceProductArticle;
    }

    public String getReplaceProductStock() {
        return replaceProductStock;
    }

    public void setReplaceProductStock(String replaceProductStock) {
        this.replaceProductStock = replaceProductStock;
    }

    public String getReplaceProductPrice() {
        return replaceProductPrice;
    }

    public void setReplaceProductPrice(String replaceProductPrice) {
        this.replaceProductPrice = replaceProductPrice;
    }

    public String getReplaceProductBreadcrumbs() {
        return replaceProductBreadcrumbs;
    }

    public void setReplaceProductBreadcrumbs(String replaceProductBreadcrumbs) {
        this.replaceProductBreadcrumbs = replaceProductBreadcrumbs;
    }

    public String getContainProductArticle() {
        return containProductArticle;
    }

    public void setContainProductArticle(String containProductArticle) {
        this.containProductArticle = containProductArticle;
    }

    public String getContainProductStock() {
        return containProductStock;
    }

    public void setContainProductStock(String containProductStock) {
        this.containProductStock = containProductStock;
    }

    public String getContainProductPrice() {
        return containProductPrice;
    }

    public void setContainProductPrice(String containProductPrice) {
        this.containProductPrice = containProductPrice;
    }

    public String getContainProductBreadcrumbs() {
        return containProductBreadcrumbs;
    }

    public void setContainProductBreadcrumbs(String containProductBreadcrumbs) {
        this.containProductBreadcrumbs = containProductBreadcrumbs;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}