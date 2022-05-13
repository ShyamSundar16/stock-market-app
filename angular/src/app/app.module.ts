import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchstockComponent } from './components/searchstock/searchstock.component';
import { CompanyService } from './service/company.service';
import { StockService } from './service/stock.service';
import { SearchService } from './service/search.service';
import { UserService } from './service/user.service';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "searchStock", component: SearchstockComponent },
  { path: "admin", loadChildren: () => import("./modules/admin/admin.module").then(m => m.AdminModule) },
  { path: "**", component: LoginComponent }

]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    SearchstockComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes), ReactiveFormsModule,HttpClientModule
  ],
  providers: [CompanyService,StockService,SearchService,UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
