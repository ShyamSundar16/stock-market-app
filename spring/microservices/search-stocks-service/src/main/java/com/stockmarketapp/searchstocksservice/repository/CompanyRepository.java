package com.stockmarketapp.searchstocksservice.repository;

import com.stockmarketapp.searchstocksservice.model.Company;
import com.stockmarketapp.searchstocksservice.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String> {
    @Query(value="{'companyCode' : $0}", delete = true)
    void deleteByCompanyCode(String s);
}
