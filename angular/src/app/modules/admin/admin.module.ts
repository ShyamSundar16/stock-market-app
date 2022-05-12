import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { ManagecompanyComponent } from './components/managecompany/managecompany.component';


const routes: Routes = [
  {
    path: "manageCompanies",
    component: ManagecompanyComponent
  }
];

@NgModule({
  declarations: [
    ManagecompanyComponent
  ],
  imports: [
    CommonModule,RouterModule.forChild(routes),HttpClientModule,ReactiveFormsModule

  ]
})
export class AdminModule { }
