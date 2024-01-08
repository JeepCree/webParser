package ua.com.mobifix.entity;

import jakarta.persistence.*;

import java.util.Map;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String link;

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

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
}