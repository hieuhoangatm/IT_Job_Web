package com.dinhhieu.jobitweb.service;

import com.dinhhieu.jobitweb.domain.Company;
import com.dinhhieu.jobitweb.domain.response.ResultPaginationDTO;
import com.dinhhieu.jobitweb.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company handleCreateCompany(Company company){
        return companyRepository.save(company);
    }

    public ResultPaginationDTO getAllCompany(Specification<Company> spec, Pageable pageable){
        Page<Company> companyPage = this.companyRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();
        mt.setPage(pageable.getPageNumber()+1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages((companyPage.getTotalPages()));
        mt.setTotal(companyPage.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(companyPage.getContent());
        return rs;
    }

    public Company fetchCompanyrById(long id) {
        Optional<Company> companyOptional = this.companyRepository.findById(id);
        return companyOptional.orElse(null);
    }
    public Company handleUpdateCompany(Company company){
        Company currentCompany = this.fetchCompanyrById(company.getId());
        if (currentCompany!=null){
            currentCompany.setName(company.getName());
            currentCompany.setAddress(company.getAddress());
            currentCompany.setDescription(company.getDescription());
            currentCompany.setLogo(company.getLogo());
            currentCompany = this.companyRepository.save(currentCompany);
        }
        return currentCompany;
    }

    public void deleteCompany(long id){
        this.companyRepository.deleteById(id);
    }
}
