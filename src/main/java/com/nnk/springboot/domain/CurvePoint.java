package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import java.sql.Timestamp;

/**
 * The type Curve point.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Range(min = 1, message = "must not be null")
    @NotNull(message = "must not be null")
    private Integer curveId;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp asOfDate;

    private Double term;

    private Double pointValue;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp creationDate;

    /**
     * Instantiates a new Curve point.
     *
     * @param curveId the curve id
     * @param term    the term
     * @param value   the value
     */
    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.pointValue = value;
    }

}
