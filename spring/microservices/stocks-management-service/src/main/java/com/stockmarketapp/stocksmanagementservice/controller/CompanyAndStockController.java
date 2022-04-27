package com.stockmarketapp.stocksmanagementservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.stockmarketapp.stocksmanagementservice.model.Company;
import com.stockmarketapp.stocksmanagementservice.model.Stock;
@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/market/")
public class CompanyAndStockController {
   
	@Autowired
    private KafkaTemplate<String, Company> companykafkaTemplate;
	private KafkaTemplate<String, Stock> stockkafkaTemplate;
	private KafkaTemplate<String, String>  deleteCompanykafkaTemplate;
    private static final String COMPANY_TOPIC = "company";
    private static final String STOCK_TOPIC = "stock";
    private static final String DELETE_COMPANY_TOPIC = "company";


    @CrossOrigin
    @PostMapping("company/register")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
    	companykafkaTemplate.send(COMPANY_TOPIC, company);
    	return new ResponseEntity<Company>(company,HttpStatus.OK);
    }
    
  
    @CrossOrigin
    @PostMapping("stock/add/{companyCode}")
    public ResponseEntity<Stock> saveStock(@RequestBody Stock stock,@PathVariable String companyCode ) {
    	stock.setCompanyName(companyCode);
    	stockkafkaTemplate.send(STOCK_TOPIC, stock);
		return new ResponseEntity<Stock>(stock,HttpStatus.OK);
    	//return companyService.save(ticket);
    }

 

    @CrossOrigin
    @DeleteMapping("company/delete/{companyCode}")
    public ResponseEntity<?> deleteCompany(@PathVariable String companyCode) {
        deleteCompanykafkaTemplate.send(DELETE_COMPANY_TOPIC, companyCode);
		return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
    }

  
}
