package com.stockmarketapp.searchstocksservice.service;

import com.stockmarketapp.searchstocksservice.exception.CompanyNotFoundException;
import com.stockmarketapp.searchstocksservice.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.stockmarketapp.searchstocksservice.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Cacheable("companies")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Cacheable("company")
    public ResponseEntity<Company> getCompanyById(String id) throws CompanyNotFoundException {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return new ResponseEntity<Company>(company.get(), HttpStatus.OK);
        } else {
            System.out.println("Coupon not found with id: " + id);
            throw new CompanyNotFoundException("Coupon not found with id: "+id);

        }
    }
}
