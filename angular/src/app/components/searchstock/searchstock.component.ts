import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Stock } from 'src/app/models/Stock';
import { SearchService } from 'src/app/service/search.service';

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
  stocks: Stock[] = [];

  constructor(private router: Router) {
    
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

  viewSearchResults() {
    return this.showSearchTable;
  }
  searchFlights() {
    this.showSearchTable = true;

    // this.searchService.filterStock(this.companyCode, this.fromDate+"", this.toDate + "")
    //   .subscribe((res: any) => {
    //     console.log(res)
    //     //this.flightsBasedOnSchedule = res;
    //   })


  }


}
