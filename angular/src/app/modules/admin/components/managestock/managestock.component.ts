import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Stock } from 'src/app/models/Stock';
import { StockService } from 'src/app/service/stock.service';

@Component({
  selector: 'app-managestock',
  templateUrl: './managestock.component.html',
  styleUrls: ['./managestock.component.scss']
})
export class ManagestockComponent implements OnInit {

  addStockForm: FormGroup;
  updateStockForm: FormGroup;
  showAddStockForm: boolean = false;
  showUpdateStockForm: boolean = false;
  stock: Stock[] = [];


  constructor(private stockService: StockService) {
    this.addStockForm = new FormGroup({
      companyName: new FormControl("", [Validators.required]),
      companyCode: new FormControl("", [Validators.required]),
      stockPrice: new FormControl(0, [Validators.required])
    })
    this.updateStockForm = new FormGroup({
      companyName: new FormControl("", [Validators.required]),
      companyCode: new FormControl("", [Validators.required]),
      stockPrice: new FormControl(0, [Validators.required])
    })
    //this.findAllCompanies();
  }


  ngOnInit(): void {
  }

  showAddForm() {
    this.showAddStockForm = true;
  }
  showUpdateForm(stock: Stock) {
    this.updateStockForm.setValue(stock)
    this.showUpdateStockForm = true;
  }

  cancelAddForm() {
    this.showAddStockForm = false;
  }

  cancelUpdateForm() {
    this.showUpdateStockForm = false;
  }

  addStock() {
    this.stockService.saveStock(this.addStockForm.value)
      .subscribe((res: any) => {
        this.addStockForm.reset();
        this.showAddStockForm = false;
      });
  }

  // modifyCompany() {
  //   this.companyService.updateCompany(this.updateCompanyForm.value)
  //     .subscribe((res: any) => {
  //       this.showUpdateCompanyForm = false;
  //       this.findAllCompanies()
  //     });
  // }
  // deleteCompanyByCode(code: string) {
  //   this.companyService.deleteCompany(code)
  //     .subscribe((res: any) => {
  //       this.findAllCompanies()
  //     });
  // }

}
