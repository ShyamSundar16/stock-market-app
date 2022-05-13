import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Company } from 'src/app/models/Company';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-managecompany',
  templateUrl: './managecompany.component.html',
  styleUrls: ['./managecompany.component.scss']
})
export class ManagecompanyComponent implements OnInit {

  addCompanyForm: FormGroup;
  updateCompanyForm: FormGroup;
  showAddCompanyForm: boolean = false;
  showUpdateCompanyForm: boolean = false;
  company: Company[] = [];


  constructor(private companyService: CompanyService) {
    this.addCompanyForm = new FormGroup({
      companyName: new FormControl("", [Validators.required]),
      companyCode: new FormControl("", [Validators.required]),
      companyCeo: new FormControl("", [Validators.required]),
      companyTurnover: new FormControl(9999, [Validators.min(10000)]),
      companyWebsite: new FormControl("", [Validators.required]),
      stockExchange: new FormControl("", [Validators.required])
    })
    this.updateCompanyForm = new FormGroup({
      companyName: new FormControl("", [Validators.required]),
      companyCode: new FormControl("", [Validators.required]),
      companyCeo: new FormControl("", [Validators.required]),
      companyTurnover: new FormControl(9999, [Validators.min(10000)]),
      companyWebsite: new FormControl("", [Validators.required]),
      stockExchange: new FormControl("", [Validators.required])
    })
    this.findAllCompanies();
  }


  ngOnInit(): void {
  }

  showAddForm() {
    this.showAddCompanyForm = true;
  }
  showUpdateForm(company: Company) {
    this.updateCompanyForm.setValue(company)
    this.showUpdateCompanyForm = true;
  }

  cancelAddForm() {
    this.showAddCompanyForm = false;
  }

  cancelUpdateForm() {
    this.showUpdateCompanyForm = false;
  }
  findAllCompanies() {
    this.companyService.getAllCompanies()
      .subscribe((res: any) => {
        this.company = res;
      })
  }

  addCompany() {
    this.companyService.saveCompany(this.addCompanyForm.value)
      .subscribe((res: any) => {
        this.addCompanyForm.reset();
        this.showAddCompanyForm = false;
        this.findAllCompanies();
      });
  }

  // modifyCompany() {
  //   this.companyService.updateCompany(this.updateCompanyForm.value)
  //     .subscribe((res: any) => {
  //       this.showUpdateCompanyForm = false;
  //       this.findAllCompanies()
  //     });
  // }
  deleteCompanyByCode(code: string) {
    this.companyService.deleteCompany(code)
      .subscribe((res: any) => {
        this.findAllCompanies()
      });
  }

}
