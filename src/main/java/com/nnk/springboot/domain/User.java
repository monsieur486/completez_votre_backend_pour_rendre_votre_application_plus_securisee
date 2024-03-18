package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    @Column(length = 125)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(length = 125)
    private String password;

    @NotBlank(message = "FullName is mandatory")
    @Column(length = 125)
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    @Column(length = 125)
    private String role;
}
