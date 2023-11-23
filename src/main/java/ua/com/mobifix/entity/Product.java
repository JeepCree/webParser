package ua.com.mobifix.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String article;
    private Long categories;
    private String name;
    private String stock;
    private String price;
    private String link;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestampField;
}
