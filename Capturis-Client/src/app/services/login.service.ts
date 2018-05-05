import {HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {TempLogin} from '../login/login.model';


const MASTER_PATH = 'http://localhost:8080/api/auth/';
@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    const options: any = {
      observe: 'response',
    };

    const req = this.http.get<any>(MASTER_PATH + 'all/Users', options);
    return req;
  }

  postLogin(tLogin: TempLogin): Observable<any> {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post(MASTER_PATH + 'authenticate', tLogin, {headers: headers});
  }
}
