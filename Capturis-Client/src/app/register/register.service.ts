import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable}     from 'rxjs/Observable';
import {AssessmentUser} from "./register.model";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from "@angular/core";


const path = 'localhost:8080//auth';
// Service related to login page
@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  private _registerUrl = path + '/register';

  submitLogin(params: AssessmentUser) {
    //const options = {observe: 'response'};
    return this.http.post(this._registerUrl, params, {observe: 'response'});

    //return this.http.post(this._registerUrl, params, options)
  }
}
