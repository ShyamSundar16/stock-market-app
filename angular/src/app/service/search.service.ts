import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/";

@Injectable()
export class SearchService {
    private url: string = "http://localhost:8989/searchmanagement/api/v1.0/market";


    constructor(private httpClient: HttpClient) {
    }

    filterStock(companyCode:string, startDate:string, endDate:string){
        return this.httpClient.get(this.url+"/stock/get"+"/"+companyCode +"/"+startDate +"/"+endDate);
    }

    findCompanyByCode(companyCode:string){
        return this.httpClient.get(this.url+"/company/info"+"/"+companyCode );
    }
}