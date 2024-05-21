package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeId;

    @Column(nullable = false, length = 30)
    private String account;

    @Column(nullable = false, length = 30)
    private String type;

    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    @Column(length = 125)
    private String benchmark;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp tradeDate;

    @Column(length = 125)
    private String security;

    @Column(length = 10)
    private String status;

    @Column(length = 125)
    private String trader;

    @Column(length = 125)
    private String book;

    @Column(length = 125)
    private String creationName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(length = 125)
    private String revisionName;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Timestamp revisionDate;

    @Column(length = 125)
    private String dealName;

    @Column(length = 125)
    private String dealType;

    @Column(length = 125)
    private String sourceListId;

    @Column(length = 125)
    private String side;

    public Trade(String tradeAccount, String type, Double buyQuantity) {
        this.account = tradeAccount;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }
}
