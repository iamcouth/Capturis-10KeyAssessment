import {Component, Input, OnInit} from '@angular/core';
import {TempService} from "../services/temp.service";
import {tempLogin} from "./login.model";
import {Router} from "@angular/router";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [TempService]
})
export class LoginComponent implements OnInit {
  tl: tempLogin = {
    userLoginId: null,
    username: '',
  userId: null,
  passwordHash: '',
  accountLockFl: false,
  lastLoginDate: new Date()
}
  tllist: Array<any>;
  serverResponse: string = '';
  authentication_error = false;
  constructor(private tempService: TempService, private router: Router) {
  }

  ngOnInit() {

    this.tempService.getAll().subscribe(data => {
      this.tllist = data.body;
    });

  }

  submitLogin() {
    this.tempService.postLogin(this.tl).subscribe(res => {
      sessionStorage.clear();
      if(res != null) { //If response back is null, this means the authentication process did not go through successfully.
        if (res.roleId === 1) {
          sessionStorage.setItem("userid", res.userId)
          this.router.navigate(['home']);
        }
        else {
          this.router.navigate(['manager']);
        }
      }
      else{
        this.authentication_error = true;
        this.tl.passwordHash = '';
      }
      },
        error => {
      this.serverResponse = <any>error;
          console.log(error);
    });
  }

}
