import { Component, OnInit, Inject } from '@angular/core';
import {DOCUMENT} from "@angular/common";
import {EmailValidator} from "@angular/forms";
import {RegisterService} from "./register.service";
import {AssessmentUser} from "./register.model";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegisterService]

})
export class RegisterComponent implements OnInit {

  au: AssessmentUser = {
    userLoginId: null,
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
    if (this.document.getElementById('lastName').value === '') {
      this.document.getElementById('lastName').style.border = '1px solid #ff0000';
      this.document.getElementById('lastName').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('emailAddress').value === '') {
      this.document.getElementById('emailAddress').style.border = '1px solid #ff0000';
      this.document.getElementById('emailAddress').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('phoneNumber').value === '') {
      this.document.getElementById('phoneNumber').style.border = '1px solid #ff0000';
      this.document.getElementById('phoneNumber').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('street').value === '') {
      this.document.getElementById('street').style.border = '1px solid #ff0000';
      this.document.getElementById('street').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('city').value === '') {
      this.document.getElementById('city').style.border = '1px solid #ff0000';
      this.document.getElementById('city').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('state').value === '') {
      this.document.getElementById('state').style.border = '1px solid #ff0000';
      this.document.getElementById('state').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('zipCode').value === '') {
      this.document.getElementById('zipCode').style.border = '1px solid #ff0000';
      this.document.getElementById('zipCode').placeholder = 'REQUIRED';
    } else {
      this.document.getElementById('zipCode').style.border = '';
    }
    if (this.document.getElementById('passwordHash').value === '') {
      this.document.getElementById('passwordHash').style.border = '1px solid #ff0000';
      this.document.getElementById('passwordHash').placeholder = 'REQUIRED';
    } else {
      this.document.getElementById('passwordHash').style.border = '';
    }

    console.log(this.document.getElementById('passwordHash').value === this.document.getElementById('confirm_password').value);
  }

  constructor(@Inject(DOCUMENT) private document, private _registerService: RegisterService, private _emailValid: EmailValidator) {

  }

  ngOnInit() {

    this._registerService.getUserById(1).subscribe(res => {
      console.log(res)
    },
      err => {
        console.error(err)
      });
  }

    submitRegister() {

      this._registerService.postRegister(this.au).subscribe(res => {
          this.serverResponse = res;
        console.log(res);
        },
        error => {
          this.serverResponse = <any>error
        });
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
