import { Component, OnInit } from '@angular/core';
import {TempService} from "../services/temp.service";

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
    this.tempService.get().subscribe(res =>{
      console.log(res);
    })
  }

}
