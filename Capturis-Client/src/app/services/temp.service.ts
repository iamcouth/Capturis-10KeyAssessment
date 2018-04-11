import {HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';


const MASTER_PATH = 'http://localhost:8080/api/auth/login';
@Injectable()
export class TempService{

  constructor(private http: HttpClient) {
  }


  get(): Observable<HttpEvent<any>> {
    const options: any = {
      observe: 'response',
    };
    const req = this.http.get<any>('http://localhost:8080/api/auth/login', options);
    console.log(req);
    return req;
    //
    //
    // this.http
    //   .get<any>(MASTER_PATH, {observe: 'response'});
}
}
