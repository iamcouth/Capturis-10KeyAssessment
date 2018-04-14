import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable}     from 'rxjs/Observable';
import {AssessmentUser} from "./register.model";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from "@angular/core";


const path = 'http://localhost:8080/api/auth';
// Service related to login page
@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  getUserById(id: number): Observable<any> {
    const options: any = {
      observe: 'response',
    };
    const req = this.http.get<any>(path + '/register/' + id, options);
    //console.log(req);
    return req;
  }
  //
  // addNewRegistrationUser(au: AssessmentUser): Promise<any>{
  //   const options: any = {
  //     observe: 'response',
  //   };
  //
  //   return this.http.post(path + /register/, options).toPromise()
  //     .then()
  //     .catch();
  // }

  // private _registerUrl = path + '/register';
  //
  // submitLogin(params: AssessmentUser) {
  //   //const options = {observe: 'response'};
  //   return this.http.post(this._registerUrl, params, {observe: 'response'});

    //return this.http.post(this._registerUrl, params, options)
}

