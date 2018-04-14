import { Component, OnInit } from '@angular/core';
import {TempService} from "../services/temp.service";
import {tempLogin} from "./login.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [TempService]
})
export class LoginComponent implements OnInit {

  constructor(private tempService: TempService) {
  }

  ngOnInit() {
    this.tempService.getById(1).subscribe(res =>{

      //let body = res.body;
      console.log(res);
    },
      err => {
        console.error(err)
    });


  }

}
