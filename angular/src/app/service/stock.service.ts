import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/";
import { Stock } from "../models/Stock";

@Injectable()
export class StockService {
    private url: string = "http://localhost:8989/api/admin/flights";
    private stock= new Stock();

    public set Stock(newStock: Stock) {
        this.stock.companyCode = newStock.companyCode;
        this.stock.companyName = newStock.companyName;
        this.stock.stockPrice = newStock.stockPrice;

    }

    public get Stock() {
        return this.stock;
    }
    constructor(private httpClient: HttpClient) {
    }
    getAllStocks() {
        return this.httpClient.get(this.url);
    }

    saveStock(Stock: Stock) {
        return this.httpClient.post(this.url, Stock);
    }

    deleteStock(code: string) {
        return this.httpClient.delete(this.url + "/" + code);
    }

    updateStock(Stock: Stock) {
        return this.httpClient.put(this.url + "/" + Stock.companyCode, Stock);
    }
}