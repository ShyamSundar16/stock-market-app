import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/";

@Injectable()
export class SearchService {
    private url: string = "http://localhost:8989/api/bookflights/searchFlight";

    constructor(private httpClient: HttpClient) {
    }

    filterStock(companyCode:string, startDate:string, endDate:string){
        return this.httpClient.get(this.url+"/"+companyCode +"/"+startDate +"/"+endDate);
    }
}