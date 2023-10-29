package ua.com.mobifix.parser.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Product {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String article;
    private String categories;
    private String name;
    private String stock;
    private String price;
    private String link;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestampField;
}
