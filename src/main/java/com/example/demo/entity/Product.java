package com.example.demo.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pnum;

    @Column(nullable = false, length = 30)
    private String pname;

    @Column(nullable = false)
    private int pPrice;

    @Column(nullable = false)
    private int pCount;

    @Column(nullable = true, length = 255)
    private String pImgUrl;

    @Column(nullable = true, length = 255)
    private String pDetailImgUrl; // 새로운 필드 추가

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp pDate;
}
