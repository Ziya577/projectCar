package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marka;
    private String model;
    private String qiymeti;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;
}
