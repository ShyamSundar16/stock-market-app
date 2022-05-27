import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/";

@Injectable()
export class SearchService {
    private url: string = "http://ec2-13-56-18-89.us-west-1.compute.amazonaws.com:5000/searchmanagement/api/v1.0/market";


    constructor(private httpClient: HttpClient) {
    }

    filterStock(companyCode:string, startDate:string, endDate:string){
        return this.httpClient.get(this.url+"/stock/get"+"/"+companyCode +"/"+startDate +"/"+endDate);
    }

    findCompanyByCode(companyCode:string){
        return this.httpClient.get(this.url+"/company/info"+"/"+companyCode );
    }
}