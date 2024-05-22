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
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 125)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(length = 125)
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(length = 125)
    @NotBlank(message = "Json is mandatory")
    private String json;

    @Column(length = 512)
    @NotBlank(message = "Template is mandatory")
    private String template;

    @Column(length = 125)
    @NotBlank(message = "Sql is mandatory")
    private String sqlStr;

    @Column(length = 125)
    @NotBlank(message = "Sql Part is mandatory")
    private String sqlPart;

    public RuleName(String ruleName, String description, String json, String template, String sql, String sqlPart) {
        this.name = ruleName;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sql;
        this.sqlPart = sqlPart;
    }
}
