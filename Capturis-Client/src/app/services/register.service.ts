import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {AssessmentUser} from '../register/register.model';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from '@angular/core';
import {TempLogin} from '../login/login.model';

const path = 'http://localhost:8080/api/auth';
// Service related to login page
@Injectable()
export class RegisterService {

private secret = '6LeUGlYUAAAAAP3-IZcletPM0TCoAOIr7GVyvQNz';
constructor(private http: HttpClient) {
  }

  getUserById(id: number): Observable<any> {
    const options: any = {
      observe: 'response',
    };
    const req = this.http.get<any>(path + '/register/' + id, options);
    return req;
  }

  postRegister(aUser: AssessmentUser): Observable<any> {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post(path + '/register', aUser, {headers: headers});
  }
}


