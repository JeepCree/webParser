package ua.com.mobifix.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "product_link_sha3_index", columnList = "linkSha3")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean active = true;
    private String article;
    @Column(nullable = false)
    private Long categories;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(columnDefinition = "TEXT")
    private String breadcrumbs;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private int pcs;
    private String stock;
    private Double price;
    @Column(columnDefinition = "TEXT")
    private String chpu;
    @Column(/*unique = true, */nullable = false, columnDefinition = "TEXT")
    private String link;
    @Column(unique = true, length = 128)
    private String linkSha3;
    @Column(length = 1024)
    private String imageLink;
//    @Column(nullable = false)
    private Long shopId;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestampField;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getChpu() {
        return chpu;
    }

    public void setChpu(String chpu) {
        this.chpu = chpu;
    }

    public String getLinkSha3() {
        return linkSha3;
    }

    public void setLinkSha3(String linkSha3) {
        this.linkSha3 = linkSha3;
    }

    public String getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(String breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Long getCategories() {
        return categories;
    }

    public void setCategories(Long categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPcs() {
        return pcs;
    }

    public void setPcs(int pcs) {
        this.pcs = pcs;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Timestamp getTimestampField() {
        return timestampField;
    }

    public void setTimestampField(Timestamp timestampField) {
        this.timestampField = timestampField;
    }
}
