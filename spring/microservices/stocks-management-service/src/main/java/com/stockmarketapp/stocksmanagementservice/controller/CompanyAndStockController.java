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
@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/market/")
public class CompanyAndStockController {
   
	@Autowired

    private static final String ADD_COMPANY_TOPIC = "add_company";
    private static final String ADD_STOCK_TOPIC = "add_stock";
    private static final String DELETE_COMPANY_TOPIC = "delete_company";;
    private static final KafkaProducer<String, Company> addCompanyProducer= KafkaProducerConfiguration.addCompanyProducer();
    private static final KafkaProducer<String, Stock> addStockProducerProducer= KafkaProducerConfiguration.addStockProducer();
    private static final KafkaProducer<String, String> deleteCompanyProducerProducer= KafkaProducerConfiguration.deleteCompanyProducer();

    @CrossOrigin
    @PostMapping("company/register")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        ProducerRecord<String, Company> companyRecord = new ProducerRecord<>(ADD_COMPANY_TOPIC,company.getCompanyCode(), company);
        addCompanyProducer.send(companyRecord);
        addCompanyProducer.flush();
        addCompanyProducer.close();
    	return new ResponseEntity<Company>(company,HttpStatus.OK);
    }
    
  
    @CrossOrigin
    @PostMapping("stock/add/{companyCode}")
    public ResponseEntity<Stock> saveStock(@RequestBody Stock stock,@PathVariable String companyCode ) {
    	stock.setCompanyName(companyCode);
        ProducerRecord<String, Stock> stockRecord = new ProducerRecord<>(ADD_STOCK_TOPIC,companyCode, stock);
        addStockProducerProducer.send(stockRecord);
        addStockProducerProducer.flush();
        addStockProducerProducer.close();
        return new ResponseEntity<Stock>(stock,HttpStatus.OK);
    }

 

    @CrossOrigin
    @DeleteMapping("company/delete/{companyCode}")
    public ResponseEntity<?> deleteCompany(@PathVariable String companyCode) {
        ProducerRecord<String, String> deleteCompany = new ProducerRecord<>(DELETE_COMPANY_TOPIC,companyCode, companyCode);
        deleteCompanyProducerProducer.send(deleteCompany);
        deleteCompanyProducerProducer.flush();
        deleteCompanyProducerProducer.close();
        return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
    }
}
