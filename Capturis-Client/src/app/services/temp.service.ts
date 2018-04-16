import {HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import {tempLogin} from "../login/login.model";


const MASTER_PATH = 'http://localhost:8080/api/auth/';
@Injectable()
export class TempService{

  constructor(private http: HttpClient) {
  }

  // getById(id: number): Observable<any> {
  //   const options: any = {
  //     observe: 'response',
  //   };
  //   const req = this.http.get<any>('http://localhost:8080/api/auth/' + id, options);
  //   //console.log(req);
  //   return req;
  //   //
  //   //
  //   // this.http
  //   //   .get<any>(MASTER_PATH, {observe: 'response'});
  // }

  getAll(): Observable<any> {
    const options: any = {
      observe: 'response',
    };

    const req = this.http.get<any>(MASTER_PATH + "all/Users", options);
    //console.log(req);
    return req;
  }

  postLogin(tLogin: tempLogin): Observable<any> {
    const options: any = {
      observe: 'response',
    };
    console.log(tLogin);
    return this.http.post(MASTER_PATH, tLogin, options);
  }
}
