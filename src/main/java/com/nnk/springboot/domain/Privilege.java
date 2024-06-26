package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Privilege.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;
    private String name;

    /**
     * Instantiates a new Privilege.
     *
     * @param role the role
     * @param name the name
     */
    public Privilege(String role, String name) {
        this.role = role;
        this.name = name;
    }
}
