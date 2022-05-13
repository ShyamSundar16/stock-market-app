import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http/";
import { Company } from "../models/Company";

@Injectable()
export class CompanyService {
    private url: string = "http://localhost:8989/stockmanagement/api/v1.0/market";
    private searchurl: string = "http://localhost:8989/searchmanagement/api/v1.0/market";

    private comp= new Company();

    public set company(newCompany: Company) {
        this.comp.companyCode = newCompany.companyCode;
        this.comp.companyName = newCompany.companyName;
        this.comp.companyCeo = newCompany.companyCeo;
        this.comp.companyTurnover = newCompany.companyTurnover;
        this.comp.companyWebsite = newCompany.companyWebsite;
        this.comp.stockExchange = newCompany.stockExchange;

    }

    public get company() {
        return this.comp;
    }
    constructor(private httpClient: HttpClient) {
    }
    getAllCompanies() {
        return this.httpClient.get(this.searchurl+"/company/getAll");
    }

    saveCompany(company: Company) {
        return this.httpClient.post(this.url + "/company/register", company);
    }

    deleteCompany(code: string) {
        return this.httpClient.delete(this.url + "/company/delete/" + code);
    }

}