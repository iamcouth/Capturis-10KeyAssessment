import { Component, OnInit } from '@angular/core';
import {TempService} from '../services/temp.service';
import {tempLogin} from './login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [TempService]
})
export class LoginComponent implements OnInit {
  tl: tempLogin;
  //sessionId: number = parseInt(sessionStorage.getItem('userId'))
  constructor(private tempService: TempService) {
  }

  ngOnInit() {
    this.tempService.getById(1).subscribe(res => {

      //let body = res.body;
      console.log(res);
      this.tl = res.body;
      console.log(this.tl);
    },
      err => {
        console.error(err);
    });



  }

}
