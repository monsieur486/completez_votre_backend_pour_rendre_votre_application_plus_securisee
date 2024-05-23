package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

/**
 * The type Rating.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Moodys rating is mandatory")
    @Column(length = 125)
    private String moodysRating;

    @Column(length = 125)
    @NotBlank(message = "SandP rating is mandatory")
    private String sandPRating;

    @Column(length = 125)
    @NotBlank(message = "Fitch rating is mandatory")
    private String fitchRating;

    @Range(min = 1, message = "must not be null")
    @NotNull(message = "must not be null")
    private Integer orderNumber;

    /**
     * Instantiates a new Rating.
     *
     * @param moodysRating the moodys rating
     * @param sandPRating  the sand p rating
     * @param fitchRating  the fitch rating
     * @param orderNumber  the order number
     */
    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}
