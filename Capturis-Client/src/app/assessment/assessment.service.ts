import {HttpClient, HttpResponse, HttpHeaders} from '@angular/common/http';
import {Observable}     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from '@angular/core';
import { Assessment } from './assessment.model';


const path = 'http://localhost:8080/api/assessment';
// Service related to assessment page
@Injectable()
export class AssessmentService {

  constructor(private http: HttpClient) { }

  getNewAssessment(arg1: Assessment): Observable<any> {
    const options: any = {
    observe: 'response',
    };
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');

    return this.http.post(path + '/newassessment', arg1, {headers: headers});
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
