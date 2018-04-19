import {HttpClient, HttpResponse, HttpHeaders} from '@angular/common/http';
import {Observable}     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from '@angular/core';
import { AssessmentResults } from './assessment-results.model';


const path = 'http://localhost:8080/api/assessmentresults';
// Service related to assessment page
@Injectable()
export class AssessmentService {

  constructor(private http: HttpClient) { }

  getResult(arg1: number): Observable<any> {
    const options: any = {
    observe: 'response',
    };
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');

    return this.http.get(path + '/getResult' + arg1, options);
  }
}
