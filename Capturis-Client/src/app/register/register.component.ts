import { Component, OnInit, Inject } from '@angular/core';
import {DOCUMENT} from '@angular/common';
import {EmailValidator} from '@angular/forms';
import {RegisterService} from './register.service';
import {AssessmentUser} from './register.model';
import {Router} from "@angular/router";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import emailMask from 'text-mask-addons/dist/emailMask'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegisterService]

})
export class RegisterComponent implements OnInit {

  phoneMask = ['(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/]

  emailMask=emailMask;
  passwordMatch: boolean = true;
  duplicateUser: boolean = false;
  duplicateEmail: boolean = false;
  captchaError: boolean = false;
  captcha: string = '';
  au: AssessmentUser = {
    userLoginId: null,
    userId: null,
    username: '',
    passwordHash: '',
    firstName: '',
    lastName: '',
    emailAddress: '',
    street: '',
    city: '',
    state: '',
    zipCode: '',
    phoneNumber: '',
    jobCode: '',
    createdDate: new Date(),
    roleId: 1,
    country: ''
  }
  serverResponse: string = '';

  validateFields() {
    if (this.document.getElementById('firstName').value === '') {
      this.document.getElementById('firstName').style.border = '1px solid #ff0000';
      this.document.getElementById('firstName').placeholder = 'REQUIRED';
    }
    else {
      this.document.getElementById('firstName').style.border = '';
    }
    if (this.document.getElementById('lastName').value === '') {
      this.document.getElementById('lastName').style.border = '1px solid #ff0000';
      this.document.getElementById('lastName').placeholder = 'REQUIRED';
    }
    else {
      this.document.getElementById('lastName').style.border = '';
    }
    if (this.document.getElementById('emailAddress').value === '') {
      this.document.getElementById('emailAddress').style.border = '1px solid #ff0000';
      this.document.getElementById('emailAddress').placeholder = 'REQUIRED';
    }
    else {
      this.document.getElementById('emailAddress').style.border = '';
    }
    if (this.document.getElementById('phoneNumber').value === '') {
      this.document.getElementById('phoneNumber').style.border = '1px solid #ff0000';
      this.document.getElementById('phoneNumber').placeholder = 'REQUIRED';
    }
    else {
      this.document.getElementById('phoneNumber').style.border = '';
    }
    if (this.document.getElementById('street').value === '') {
      this.document.getElementById('street').style.border = '1px solid #ff0000';
      this.document.getElementById('street').placeholder = 'REQUIRED';
    }
    else {
      this.document.getElementById('street').style.border = '';
    }
    if (this.document.getElementById('city').value === '') {
      this.document.getElementById('city').style.border = '1px solid #ff0000';
      this.document.getElementById('city').placeholder = 'REQUIRED';
    }
    else {
      this.document.getElementById('city').style.border = '';
    }
    if (this.document.getElementById('state').value === '') {
      this.document.getElementById('state').style.border = '1px solid #ff0000';
      this.document.getElementById('state').placeholder = 'REQUIRED';
    }
    else {
      this.document.getElementById('state').style.border = '';
    }
    if (this.document.getElementById('zipCode').value === '') {
      this.document.getElementById('zipCode').style.border = '1px solid #ff0000';
      this.document.getElementById('zipCode').placeholder = 'REQUIRED';
    } else {
      this.document.getElementById('zipCode').style.border = '';
    }
    if (this.document.getElementById('username').value === '') {
      this.document.getElementById('username').style.border = '1px solid #ff0000';
      this.document.getElementById('username').placeholder = 'REQUIRED';
    } else {
      this.document.getElementById('username').style.border = '';
    }
    if (this.document.getElementById('passwordHash').value === '') {
      this.document.getElementById('passwordHash').style.border = '1px solid #ff0000';
      this.document.getElementById('passwordHash').placeholder = 'REQUIRED';
    } else {
      this.document.getElementById('passwordHash').style.border = '';
    }
  }

  constructor(@Inject(DOCUMENT) private document, private _registerService: RegisterService, private _emailValid: EmailValidator, private router: Router) {

  }

  ngOnInit() {

    this._registerService.getUserById(1).subscribe(res => {
      console.log(res);
    },
      err => {
        console.error(err);
      });
  }



    submitRegister() {
      if (this.document.getElementById('passwordHash').value === this.document.getElementById('confirm_password').value && this.document.getElementById('confirm_password').value != '') {
        if (this.captcha === '') {
          this.captchaError = true;
        }
        else {


          this.captchaError = false;
          this.passwordMatch = true;
          this._registerService.postRegister(this.au).subscribe(res => {
              this.serverResponse = res;
              console.log(res);
              this.router.navigate(['login']);
            },
            error => {
              if (error.error.includes('fk_emailaddr')) {
                this.duplicateEmail = true;
              }
              else {
                this.duplicateEmail = false;
              }
              if (error.error.includes('co_username')) {
                this.duplicateUser = true;
              }
              else {
                this.duplicateUser = false;
              }
            });
        }
      }
    else {
        this.passwordMatch = false;
      }
    }

  public resolved(captchaResponse: string) {
    this.captcha = captchaResponse;
    console.log(`Resolved captcha with response ${captchaResponse}`);

  }



  // onSubmit(){
  //   this._registerService.update(this.params).subscribe(
  //     res => res.
  //   )


//   onSubmitLogin()
// {
//   this._registerService.submitLogin(this.params)
//     .subscribe(
//       res => this.serverResponse = res,
//
//     )
// }

}
