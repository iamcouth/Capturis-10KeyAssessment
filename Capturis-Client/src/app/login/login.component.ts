import {Component, Input, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';
import {TempLogin} from '../models/login.model';
import {Router} from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  tl: TempLogin = {
    userLoginId: null,
    username: '',
  userId: null,
  passwordHash: '',
  accountLockFl: false,
  lastLoginDate: new Date()
};
  tllist: Array<any>;
  serverResponse = '';
  authentication_error = false;
  constructor(private loginService: LoginService, private router: Router) {
  }

  ngOnInit() {

    this.loginService.getAll().subscribe(data => {
      this.tllist = data.body;
    });

  }

  submitLogin() {
    this.loginService.postLogin(this.tl).subscribe(res => {
      sessionStorage.clear();
      if (res != null) { // If response back is null, this means the authentication process did not go through successfully.
        if (res.roleId === 1) {
          sessionStorage.setItem('userid', res.userId);
          this.router.navigate(['home']);
        } else {
          this.router.navigate(['manager']);
        }
      } else {
        this.authentication_error = true;
        this.tl.passwordHash = '';
      }
      },
        error => {
      this.serverResponse = <any>error;
    });
  }

}
