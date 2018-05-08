import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AssessmentUser} from '../models/register.model';
import {UserDashboardService} from '../services/user-dashboard.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css'],
  providers: [UserDashboardService]
})
export class UserDashboardComponent implements OnInit {

  displayedColumns = ['dateTaken', 'assessmentType', 'kph', 'accuracy', 'uncorrectedMistakes', 'backspacePressCount'];
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
    roleId: 1,
    country: ''
  };

  userHistoryList: Array<any>;
  sessionId = parseInt(sessionStorage.getItem('userid'), 10);

  constructor(private router: Router, private _userdashboardservice: UserDashboardService) {
  }

  ngOnInit() {
    this._userdashboardservice.getById(this.sessionId).subscribe(res => {
        this.assessmentUser = res.body;
      },
      err => {
      });

    this._userdashboardservice.getUserHistory(this.sessionId).subscribe(res => {

        this.userHistoryList = res.body;

      },
      err => {
      });

  }

}
