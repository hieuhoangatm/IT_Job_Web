package com.dinhhieu.jobitweb.controller;

import com.dinhhieu.jobitweb.domain.Company;
import com.dinhhieu.jobitweb.domain.response.ResultPaginationDTO;
import com.dinhhieu.jobitweb.service.CompanyService;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company){
        Company company1 = companyService.handleCreateCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(company1);
    }

//    @GetMapping("")
//    public ResponseEntity<List<Company>> getAllCompany(){
//        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompany());
//    }


    @GetMapping("")
    public ResponseEntity<ResultPaginationDTO> getAllCompany(
            @Filter Specification<Company> spec, Pageable pageable){
//            @RequestParam("current") Optional<String> currentOptional,
//            @RequestParam("pageSize") Optional<String> pageSizeOptional) {
//        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
//        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
//
//        int current = Integer.parseInt(sCurrent);
//        int pageSize = Integer.parseInt(sPageSize);
//
//        Pageable pageable = PageRequest.of(current - 1, pageSize);

        // return ResponseEntity.ok(this.userService.fetchAllUser());
        return ResponseEntity.status(HttpStatus.OK).body(this.companyService.getAllCompany(spec,pageable));
    }

    @PutMapping("")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.handleUpdateCompany(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) {
        this.companyService.deleteCompany(id);
        return ResponseEntity.ok(null);
    }



}
