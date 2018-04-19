import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import {tempLogin} from "../login/login.model";
import {AssessmentUser} from "../register/register.model";
import {UserDashboardService} from "./user-dashboard.service";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css'],
  providers: [UserDashboardService]
})
export class UserDashboardComponent implements OnInit {

  assessmentUser: AssessmentUser = {
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
    roleId: 0,
    country: ''
  }
  sessionId = parseInt(sessionStorage.getItem("userid"));

    constructor(private router: Router, private _userdashboardservice: UserDashboardService) {

        //this.router.navigate(['/assessment', this.id]);
    }

  ngOnInit() {
    this._userdashboardservice.getById(this.sessionId).subscribe(res =>{

      //let body = res.body;
      //
      console.log(res);
      this.assessmentUser = res.body;
      console.log(this.assessmentUser);
      console.log(this.assessmentUser.firstName);
    },
      err => {
        console.error(err)
    });
  }

}
