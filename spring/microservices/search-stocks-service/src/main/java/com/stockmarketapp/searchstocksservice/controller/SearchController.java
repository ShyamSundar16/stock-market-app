package com.stockmarketapp.searchstocksservice.controller;

import com.stockmarketapp.searchstocksservice.exception.CompanyNotFoundException;
import com.stockmarketapp.searchstocksservice.model.Company;
import com.stockmarketapp.searchstocksservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/market")
public class SearchController {

    @Autowired
    CompanyService companyService;

    @CrossOrigin
    @GetMapping("/company/getAll")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @CrossOrigin
    @GetMapping("/company/info/{companyCode}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String  companyCode) throws CompanyNotFoundException {
        return companyService.getCompanyById(companyCode);
    }

    @CrossOrigin
    @GetMapping("/stock/get/<companyCode>/<startDate>/<endDate>")
    public ResponseEntity<Company> getStockPricesForCompany(@PathVariable String  companyCode, @PathVariable String  startDate, @PathVariable String  endDate ) throws CompanyNotFoundException, ParseException {
        return companyService.getStockPricesForCompany(companyCode, startDate, endDate);
    }

/*    @KafkaListener(topics = ADD_COMPANY_TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Company company) {
        System.out.println("Consumed Message: " + company);
        companyRepository.save(company);
    }

    @KafkaListener(topics = ADD_STOCK_TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Stock stock) {
        System.out.println("Consumed Message: " + stock);
        stockRepository.save(stock);
    }

    @KafkaListener(topics = DELETE_COMPANY_TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(String companyCode) {
        System.out.println("Consumed Message: " + companyCode);
        companyRepository.deleteById(companyCode);
    }*/
}
