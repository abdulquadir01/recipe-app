package com.abdulquadir.recipeapp.model;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

}
