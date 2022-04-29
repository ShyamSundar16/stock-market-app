package com.stockmarketapp.stocksmanagementservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "company")
@Getter
@Setter
@ToString
public class Company {
    //private static final long serialVersionUID = 1L;
    @Id
    private int _id;
    @Indexed(unique = true)
    private String companyName;
    private String companyCode;
    private String companyCeo;
    private BigDecimal companyTurnover;
    private String companyWebsite;
    private String stockExchange;
}
