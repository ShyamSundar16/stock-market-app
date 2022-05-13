import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Stock } from 'src/app/models/Stock';

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


  constructor() {
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
  // findAllCompanies() {
  //   this.companyService.getAllCompanies()
  //     .subscribe((res: any) => {
  //       this.stock = res;
  //     })
  // }

  // addCompany() {
  //   this.companyService.saveCompany(this.addCompanyForm.value)
  //     .subscribe((res: any) => {
  //       this.addCompanyForm.reset();
  //       this.showAddCompanyForm = false;
  //       this.findAllCompanies();
  //     });
  // }

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
