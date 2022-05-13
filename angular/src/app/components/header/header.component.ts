import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  userSessionValid:boolean=false;
  constructor(public userService:UserService) { }

  ngOnInit(): void {
  }

  headerStyle = {
    color: "white",
    "background-color": "#04AA6D",
    "font-style": "italic"
  }

  
  getUserSession(){
    return this.userService.user.valid;
   }
   clearSession(){
     sessionStorage.clear();
     this.userService.user.valid=false;
   }
 
 checkIfAdminUser(){
   if(this.userService.user.email=="admin@gmail.com"){
     return true;
   }
   return false;
 }
}