package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ContractEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    @Query("SELECT c FROM ContractEntity c WHERE c.Name = :Name AND c.Birthday = :Birthday AND c.NameKana = :NameKana AND c.Company = :Company AND c.ContractNum IN :ContractNum")
    List<ContractEntity> findContracts(
            @Param("Name") String Name,
            @Param("Birthday") Date Birthday,
            @Param("NameKana") String NameKana,
            @Param("Company") String Company,
            @Param("ContractNum") List<String> ContractNum);
}
