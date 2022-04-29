package com.stockmarketapp.searchstocksservice.service;

import com.stockmarketapp.searchstocksservice.exception.CompanyNotFoundException;
import com.stockmarketapp.searchstocksservice.model.Company;
import com.stockmarketapp.searchstocksservice.model.Stock;
import com.stockmarketapp.searchstocksservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.stockmarketapp.searchstocksservice.repository.CompanyRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StockRepository stockRepository;

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
            System.out.println("Company not found with id: " + id);
            throw new CompanyNotFoundException("Company not found with id: "+id);

        }
    }

    @Cacheable("stocks")
    public ResponseEntity<Company> getStockPricesForCompany(String companyCode, String startDate, String endDate) throws CompanyNotFoundException, ParseException {
        Optional<Company> company = companyRepository.findById(companyCode);
        if (company.isPresent()) {
            Company company1 = company.get();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<Stock> stocksForCompany = stockRepository.getStocksForCompany(simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate), companyCode);
            company1.setStocks(stocksForCompany);
            return new ResponseEntity<Company>(company1, HttpStatus.OK);
        } else {
            System.out.println("Company not found with id: " + companyCode);
            throw new CompanyNotFoundException("Company not found with id: "+companyCode);

        }
    }
}
