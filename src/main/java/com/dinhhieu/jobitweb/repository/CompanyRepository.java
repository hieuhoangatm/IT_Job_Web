package com.dinhhieu.jobitweb.repository;

import com.dinhhieu.jobitweb.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyRepository extends JpaRepository<Company, Long> , JpaSpecificationExecutor<Company> {
}
