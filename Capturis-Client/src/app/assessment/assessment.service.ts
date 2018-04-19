import {HttpClient, HttpResponse, HttpHeaders} from '@angular/common/http';
import {Observable}     from 'rxjs/Observable';
import {Assessment} from "./assessment.model";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from "@angular/core";

const path = 'http://localhost:8080/api';

@Injectable()
export class AssessmentService {

  constructor(private http: HttpClient) {
  }

  getAssessmentByUserId(id: number): Observable<any> {
    const options: any = {
      observe: 'response',
    };
    const req = this.http.get<any>(path + '/assessment/' + id, options);
    //console.log(req);
    return req;
  }

  processData(arg1: Assessment): Observable<any> {
    const options: any = {
    observe: 'response',
    };
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');

    return this.http.post(path + '/process', arg1, {headers: headers});
  }
}
