package com.example.demo.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    ContractEntity findByNameAndDateOfBirth(String name, Date dateOfBirth);
}
