package com.stockmarketapp.stocksmanagementservice.controller;
import com.stockmarketapp.stocksmanagementservice.config.KafkaProducerConfiguration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.stockmarketapp.stocksmanagementservice.model.Company;
import com.stockmarketapp.stocksmanagementservice.model.Stock;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/market/")
public class CompanyAndStockController {
   
	@Autowired

    private static final String ADD_COMPANY_TOPIC = "add_company";
    private static final String ADD_STOCK_TOPIC = "add_stock";
    private static final String DELETE_COMPANY_TOPIC = "delete_company";
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    @Autowired
    KafkaTemplate<String, Company> companykafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Stock> stockkafkaTemplate;
    @Autowired
    private KafkaTemplate<String, String>  deleteCompanykafkaTemplate;
/*    private static final KafkaProducer<String, Company> addCompanyProducer= KafkaProducerConfiguration.addCompanyProducer();
    private static final KafkaProducer<String, Stock> addStockProducerProducer= KafkaProducerConfiguration.addStockProducer();
    private static final KafkaProducer<String, String> deleteCompanyProducerProducer= KafkaProducerConfiguration.deleteCompanyProducer();*/

    @CrossOrigin
    @PostMapping("company/register")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        //TODO test implementation - To be reomved
        Company newCompany = new Company();
        newCompany.setCompanyName("Company1");
        newCompany.setCompanyCode("COMP01");
        newCompany.setCompanyCeo("Test");
        newCompany.setCompanyWebsite("www.comp1.com");
        newCompany.setStockExchange("NSE");
        newCompany.setCompanyTurnover(new BigDecimal(300000000));

        companykafkaTemplate.send(ADD_COMPANY_TOPIC, newCompany);
        companykafkaTemplate.flush();
    	return new ResponseEntity<Company>(company,HttpStatus.OK);
    }
    
  
    @CrossOrigin
    @PostMapping("stock/add/{companyCode}")
    public ResponseEntity<Stock> saveStock(@RequestBody Stock stock,@PathVariable String companyCode ) {
    	stock.setCompanyName(companyCode);
    	stock.setStockPrice(new BigDecimal(580));
    	stock.setCompanyCode("COMP01");
        stock.setCreatedAt(new Date());
    	stock.setCompanyName(companyCode);
        stockkafkaTemplate.send(ADD_STOCK_TOPIC, stock);
        companykafkaTemplate.flush();
        return new ResponseEntity<Stock>(stock,HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("company/delete/{companyCode}")
    public ResponseEntity<?> deleteCompany(@PathVariable String companyCode) {
        deleteCompanykafkaTemplate.send(DELETE_COMPANY_TOPIC, companyCode);
        companykafkaTemplate.flush();
        return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
    }
}
