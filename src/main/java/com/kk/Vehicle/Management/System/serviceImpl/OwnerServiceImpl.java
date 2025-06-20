package com.kk.Vehicle.Management.System.serviceImpl;

import com.kk.Vehicle.Management.System.dto.OwnerDTO;
import com.kk.Vehicle.Management.System.entity.Owner;
import com.kk.Vehicle.Management.System.repository.OwnerRepository;
import com.kk.Vehicle.Management.System.service.OwnerService;
import com.kk.Vehicle.Management.System.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private DTOMapper mapper;

    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = mapper.toOwnerEntity(ownerDTO);
        Owner savedOwner = ownerRepository.save(owner);
        return mapper.toOwnerDTO(savedOwner);
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream()
                .map(mapper::toOwnerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDTO getOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
        return mapper.toOwnerDTO(owner);
    }

    @Override
    public OwnerDTO updateOwner(Long id, OwnerDTO ownerDTO) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));

        existingOwner.setName(ownerDTO.getName());
        existingOwner.setContactNo(ownerDTO.getContactNo());
        existingOwner.setEmail(ownerDTO.getEmail());

        Owner updatedOwner = ownerRepository.save(existingOwner);
        return mapper.toOwnerDTO(updatedOwner);
    }

    @Override
    public void deleteOwner(Long id) {
        if (!ownerRepository.existsById(id)) {
            throw new RuntimeException("Owner not found with id: " + id);
        }
        ownerRepository.deleteById(id);
    }
}

