package com.kk.Vehicle.Management.System.repository;

import com.kk.Vehicle.Management.System.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
