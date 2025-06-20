package com.kk.Vehicle.Management.System.service;

import com.kk.Vehicle.Management.System.dto.OwnerDTO;

import java.util.List;

public interface OwnerService {
    OwnerDTO createOwner(OwnerDTO ownerDTO);
    List<OwnerDTO> getAllOwners();
    OwnerDTO getOwnerById(Long id);
    OwnerDTO updateOwner(Long id, OwnerDTO ownerDTO);
    void deleteOwner(Long id);
}
