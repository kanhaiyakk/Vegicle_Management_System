package com.kk.Vehicle.Management.System.dto;

import lombok.Data;
import java.util.List;

@Data
public class OwnerDTO {
    private Long id;
    private String name;
    private String contactNo;
    private String email;

    private List<VehicleDTO> vehicles;
}

