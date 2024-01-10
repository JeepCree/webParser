package ua.com.mobifix.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String link;
    private String selectCategoryTag;
    private String selectCategoryTagLevel2;
    private String selectCategoryTagLevel3;
    private String selectCategoryTagLevel4;
    private String selectCategoryTagLevel5;
    private String selectCategoryNameTag;
    private String selectCategoryNameTagLevel2;
    private String selectCategoryNameTagLevel3;
    private String selectCategoryNameTagLevel4;
    private String selectCategoryNameTagLevel5;
    private String selectCategoryAttrHref;
    private String selectCategoryAttrHrefLevel2;
    private String selectCategoryAttrHrefLevel3;
    private String selectCategoryAttrHrefLevel4;
    private String selectCategoryAttrHrefLevel5;
    private String urlPrefix;
    private String urlPrefixLevel2 = "";
    private String urlPrefixLevel3 = "";
    private String urlPrefixLevel4 = "";
    private String urlPrefixLevel5 = "";
    private String cookies;
    private String replaceCategoryName;
    private String replaceCategoryNameLevel2;
    private String replaceCategoryNameLevel3;
    private String replaceCategoryNameLevel4;
    private String replaceCategoryNameLevel5;
    private String replacementCategoryName;
    private String replacementCategoryNameLevel2;
    private String replacementCategoryNameLevel3;
    private String replacementCategoryNameLevel4;
    private String replacementCategoryNameLevel5;
    private String replaceCategoryUrl;
    private String replaceCategoryUrlLevel2;
    private String replaceCategoryUrlLevel3;
    private String replaceCategoryUrlLevel4;
    private String replaceCategoryUrlLevel5;
    private String replacementCategoryUrl;
    private String replacementCategoryUrlLevel2;
    private String replacementCategoryUrlLevel3;
    private String replacementCategoryUrlLevel4;
    private String replacementCategoryUrlLevel5;
    private String noScanList;

    public String getNoScanList() {
        return noScanList;
    }

    public void setNoScanList(String noScanList) {
        this.noScanList = noScanList;
    }

    public String getUrlPrefixLevel2() {
        return urlPrefixLevel2;
    }

    public void setUrlPrefixLevel2(String urlPrefixLevel2) {
        this.urlPrefixLevel2 = urlPrefixLevel2;
    }

    public String getUrlPrefixLevel3() {
        return urlPrefixLevel3;
    }

    public void setUrlPrefixLevel3(String urlPrefixLevel3) {
        this.urlPrefixLevel3 = urlPrefixLevel3;
    }

    public String getUrlPrefixLevel4() {
        return urlPrefixLevel4;
    }

    public void setUrlPrefixLevel4(String urlPrefixLevel4) {
        this.urlPrefixLevel4 = urlPrefixLevel4;
    }

    public String getUrlPrefixLevel5() {
        return urlPrefixLevel5;
    }

    public void setUrlPrefixLevel5(String urlPrefixLevel5) {
        this.urlPrefixLevel5 = urlPrefixLevel5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
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

    public String getReplacementCategoryName() {
        return replacementCategoryName;
    }

    public void setReplacementCategoryName(String replacementCategoryName) {
        this.replacementCategoryName = replacementCategoryName;
    }

    public String getReplacementCategoryNameLevel2() {
        return replacementCategoryNameLevel2;
    }

    public void setReplacementCategoryNameLevel2(String replacementCategoryNameLevel2) {
        this.replacementCategoryNameLevel2 = replacementCategoryNameLevel2;
    }

    public String getReplacementCategoryNameLevel3() {
        return replacementCategoryNameLevel3;
    }

    public void setReplacementCategoryNameLevel3(String replacementCategoryNameLevel3) {
        this.replacementCategoryNameLevel3 = replacementCategoryNameLevel3;
    }

    public String getReplacementCategoryNameLevel4() {
        return replacementCategoryNameLevel4;
    }

    public void setReplacementCategoryNameLevel4(String replacementCategoryNameLevel4) {
        this.replacementCategoryNameLevel4 = replacementCategoryNameLevel4;
    }

    public String getReplacementCategoryNameLevel5() {
        return replacementCategoryNameLevel5;
    }

    public void setReplacementCategoryNameLevel5(String replacementCategoryNameLevel5) {
        this.replacementCategoryNameLevel5 = replacementCategoryNameLevel5;
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

    public String getReplacementCategoryUrl() {
        return replacementCategoryUrl;
    }

    public void setReplacementCategoryUrl(String replacementCategoryUrl) {
        this.replacementCategoryUrl = replacementCategoryUrl;
    }

    public String getReplacementCategoryUrlLevel2() {
        return replacementCategoryUrlLevel2;
    }

    public void setReplacementCategoryUrlLevel2(String replacementCategoryUrlLevel2) {
        this.replacementCategoryUrlLevel2 = replacementCategoryUrlLevel2;
    }

    public String getReplacementCategoryUrlLevel3() {
        return replacementCategoryUrlLevel3;
    }

    public void setReplacementCategoryUrlLevel3(String replacementCategoryUrlLevel3) {
        this.replacementCategoryUrlLevel3 = replacementCategoryUrlLevel3;
    }

    public String getReplacementCategoryUrlLevel4() {
        return replacementCategoryUrlLevel4;
    }

    public void setReplacementCategoryUrlLevel4(String replacementCategoryUrlLevel4) {
        this.replacementCategoryUrlLevel4 = replacementCategoryUrlLevel4;
    }

    public String getReplacementCategoryUrlLevel5() {
        return replacementCategoryUrlLevel5;
    }

    public void setReplacementCategoryUrlLevel5(String replacementCategoryUrlLevel5) {
        this.replacementCategoryUrlLevel5 = replacementCategoryUrlLevel5;
    }
}