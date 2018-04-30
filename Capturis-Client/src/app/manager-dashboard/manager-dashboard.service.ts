import {HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';

const MASTER_PATH = 'http://localhost:8080/api/';
@Injectable()
export class ManagerDashboardService {

constructor(private http: HttpClient) {
  }

  getById(id: number): Observable<any> {
    const options: any = {
      observe: 'response',
    };
    console.log(id);
    const req = this.http.get<any>(MASTER_PATH + 'auth/getuser/' + id, options);
    console.log(req);
    return req;
    //
    //
    // this.http
    //   .get<any>(MASTER_PATH, {observe: 'response'});
  }

   getAll(): Observable<any> {
     const options: any = {
       observe: 'response',
     };

     const req = this.http.get<any>(MASTER_PATH + "assessmentresults/all/manager", options);
     //console.log(req);
     return req;
   }
}
