package com.smecosystem_rest.smecosystem_rest.repositories;

import com.smecosystem_rest.smecosystem_rest.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
