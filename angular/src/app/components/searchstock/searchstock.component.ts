import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/Company';
import { Stock } from 'src/app/models/Stock';
import { CompanyService } from 'src/app/service/company.service';
import { SearchService } from 'src/app/service/search.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-searchstock',
  templateUrl: './searchstock.component.html',
  styleUrls: ['./searchstock.component.scss']
})
export class SearchstockComponent implements OnInit {

  companyCode: string = "";
  fromDate: Date = new Date();
  showSearchTable: boolean = false;
  toDate: Date = new Date();
  companyStock: Company = new Company;
  company: Company[] = [];
  stocks: Stock[] = [];
  getCompanyByCode: boolean = false;
  getCompanyByRange: boolean = false;




  constructor(private searchService:SearchService,private companyService:CompanyService, public userService: UserService, router: Router) {
    this.findAllCompanies();
  }

  ngOnInit(): void {
  }

 
  updateCompanyCode(e: any) {
    this.companyCode = e.target.value
  }

  updateFromDate(e: any) {
    this.fromDate = e.target.value
  }

  updateToDate(e: any) {
    this.toDate = e.target.value
  }

  setAllCompany() {
    this.getCompanyByCode = false;
    this.getCompanyByRange = false;
  }
  setCompanyByCode() {
    this.getCompanyByCode = true;
    this.getCompanyByRange = false;
  }
  setCompanyByRange(){
    this.getCompanyByRange = true;
    this.getCompanyByCode = false;

  }

  findAllCompanies() {
    this.companyService.getAllCompanies()
      .subscribe((res: any) => {
        this.company = res;
      })
  }

  viewSearchResults() {
    return this.showSearchTable;
  }
  searchStocks() {
    this.showSearchTable = true;
    this.searchService.filterStock(this.companyCode, this.fromDate+"", this.toDate + "")
      .subscribe((res: any) => {
        this.companyStock = res ;
       this.stocks=this.companyStock.stocks;
      })
  }

  searchStocksByCode() {
    this.showSearchTable = true;
    this.searchService.findCompanyByCode(this.companyCode)
      .subscribe((res: any) => {
        this.companyStock = res ;
       this.stocks=this.companyStock.stocks;
      })
  }


}
