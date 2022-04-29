package com.stockmarketapp.stocksmanagementservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "stock")
@Getter
@Setter
@ToString
public class Stock {
    //private static final long serialVersionUID = 1L;
    @Id
    private int _id;
    private String companyName;
    private String companyCode;
    private BigDecimal stockPrice;
    private Date createdAt;
}
