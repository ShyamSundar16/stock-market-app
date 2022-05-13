import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/";
import { Stock } from "../models/Stock";

@Injectable()
export class StockService {
    private url: string = "http://localhost:8989/stockmanagement/api/v1.0/market";
    private searchurl: string = "http://localhost:8989/searchmanagement/api/v1.0/market";    private stock= new Stock();

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
 /*    getAllStocks() {
        return this.httpClient.get(this.searchurl);
    } */

    saveStock(stock: Stock) {
        return this.httpClient.post(this.url+"/stock/add/"+stock.companyCode, stock);
    }

    deleteStock(code: string) {
        return this.httpClient.delete(this.url + "/" + code);
    }

    updateStock(Stock: Stock) {
        return this.httpClient.put(this.url + "/" + Stock.companyCode, Stock);
    }
}