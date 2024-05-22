package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bidListId;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Range(min = 1, message = "Bid Quantity must be greater than 1")
    @NotNull(message = "Bid Quantity must be greater than 1")
    private Double bidQuantity;

    private Double askQuantity;

    private Double bid;

    private Double ask;

    @Column(length = 125)
    private String benchmark;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp bidListDate;

    @Column(length = 125)
    private String commentary;

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
    private Timestamp creationDate;

    @Column(length = 125)
    private String revisionName;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp revisionDate;

    @Column(length = 125)
    private String dealName;

    @Column(length = 125)
    private String dealType;

    @Column(length = 125)
    private String sourceListId;

    @Column(length = 125)
    private String side;

    public BidList(String accountTest, String account, double bidQuantity) {
        this.account = accountTest;
        this.type = account;
        this.bidQuantity = bidQuantity;
    }
}
