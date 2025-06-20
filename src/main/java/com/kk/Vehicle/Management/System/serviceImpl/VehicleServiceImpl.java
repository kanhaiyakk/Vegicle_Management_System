package com.kk.Vehicle.Management.System.serviceImpl;

import com.kk.Vehicle.Management.System.dto.VehicleDTO;
import com.kk.Vehicle.Management.System.entity.Vehicle;

import com.kk.Vehicle.Management.System.repository.VehicleRepository;
import com.kk.Vehicle.Management.System.service.VehicleService;
import com.kk.Vehicle.Management.System.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private DTOMapper mapper;

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = mapper.toVehicleEntity(vehicleDTO);
        Vehicle saved = vehicleRepo.save(vehicle);
        return mapper.toVehicleDTO(saved);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles.stream()
                .map(mapper::toVehicleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        return mapper.toVehicleDTO(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle existing = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        existing.setModel(vehicleDTO.getModel());
        existing.setYearOfManufacture(vehicleDTO.getYearOfManufacture());

        Vehicle updated = vehicleRepo.save(existing);
        return mapper.toVehicleDTO(updated);
    }

    @Override
    public void deleteVehicle(Long id) {
        if (!vehicleRepo.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        vehicleRepo.deleteById(id);
    }
}

