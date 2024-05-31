package com.example.demo.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    @Query("SELECT c FROM ContractEntity c WHERE c.name = :name AND c.dateOfBirth = :dateOfBirth AND c.nameKana = :nameKana AND c.company = :company AND c.contractNum = :contractNum")
    ContractEntity findContract(
            @Param("name") String name,
            @Param("dateOfBirth") Date dateOfBirth,
            @Param("nameKana") String nameKana,
            @Param("company") String company,
            @Param("contractNum") Integer contractNum);
}
