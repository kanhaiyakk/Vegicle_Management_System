package com.kk.Vehicle.Management.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNo;
    private String model;
    private String type;
    private String manufacturer;
    private Integer yearOfManufacture;

    @ManyToOne()
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

}
