package com.kk.Vehicle.Management.System.utils;

import com.kk.Vehicle.Management.System.dto.OwnerDTO;
import com.kk.Vehicle.Management.System.dto.VehicleDTO;
import com.kk.Vehicle.Management.System.entity.Owner;
import com.kk.Vehicle.Management.System.entity.Vehicle;
import com.kk.Vehicle.Management.System.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapper {

    @Autowired
    private OwnerRepository ownerRepository;
    // DTO → Entity

    public Owner toOwnerEntity(OwnerDTO dto){
        Owner owner=new Owner();
        owner.setName(dto.getName());
        owner.setEmail(dto.getEmail());
        owner.setContactNo(dto.getContactNo());
        return owner;
    }

    public Vehicle toVehicleEntity(VehicleDTO dto){
        Vehicle vehicle=new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setModel(dto.getModel());
        vehicle.setYearOfManufacture(dto.getYearOfManufacture());


        Owner owner=ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(()-> new RuntimeException("Owner not fount with this id: "+ dto.getOwnerId()));
        vehicle.setOwner(owner);

        return vehicle;
    }

    // Entity → DTO
    public OwnerDTO toOwnerDTO(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(owner.getId());
        ownerDTO.setName(owner.getName());
        ownerDTO.setContactNo(owner.getContactNo());
        ownerDTO.setEmail(owner.getEmail());

        List<VehicleDTO> vehicleDTOList = (owner.getVehicles() != null)
                ? owner.getVehicles().stream()
                .map(this::toVehicleDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();

        ownerDTO.setVehicles(vehicleDTOList);
        return ownerDTO;
    }

    public VehicleDTO toVehicleDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setModel(vehicle.getModel());
        dto.setYearOfManufacture(vehicle.getYearOfManufacture());
        dto.setOwnerId(vehicle.getOwner().getId());
        return dto;
    }

}
