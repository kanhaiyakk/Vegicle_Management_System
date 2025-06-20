package com.kk.Vehicle.Management.System.dto;

import lombok.Data;

@Data
public class VehicleDTO {
    private Long id;
    private String model;
    private Integer yearOfManufacture;

    private Long ownerId;
}
