import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  signupForm: FormGroup
  loginForm: FormGroup

  constructor(private router: Router) {
    this.signupForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required, Validators.email]),
      phone: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required]),
    })
    this.loginForm = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [Validators.required])

    })
   }

  ngOnInit(): void {
  }


  getLogin() {
    let u: User = this.loginForm.value;
    // let authUser: AuthUser = new AuthUser;
    // authUser.username = u.email;
    // authUser.password = u.password
    // this.validateUserAndNavigate(authUser);
    this.router.navigate(['/', 'admin', 'manageCompanies']);  }

  signUpUser() {
    let userObj: User = this.signupForm.value
    userObj.id = userObj.email;
    userObj.username = userObj.email;
    userObj.role = "customer";
    // this.userService.saveUser(userObj)
    //   .subscribe((res) => {
    //     userObj.valid = true;
    //     this.userService.user = userObj;
    //     alert("Registered Successfully, Kindly login to proceed.")
        
    //   });
    userObj.valid = true;
        alert("Registered Successfully, Kindly login to proceed.")
      this.router.navigate(["login"]);
  }
}
