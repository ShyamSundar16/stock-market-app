import { Stock } from "./Stock";

export class Company {
    companyName: string = "";
    companyCode: string = "";
    companyCeo: string = "";
    companyTurnover:number=0;
    companyWebsite: string = "";
    stockExchange: string = ""; 
    stocks : Stock[] =[];
}


