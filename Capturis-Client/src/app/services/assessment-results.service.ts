import {HttpClient, HttpResponse, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from '@angular/core';
import {AssessmentResults} from '../assessment-results/assessment-results.model';
import 'rxjs/add/operator/toPromise';


const path = 'http://localhost:8080/api/assessmentresults';
// Service related to assessment page
@Injectable()
export class AssessmentResultService {

constructor(private http: HttpClient) { }


  getResult(assessmentId: number): Observable<any> {
    const options: any = {
    observe: 'response',
    };
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.get(path + '/getResults/' + assessmentId, options);
}





  getResult00(assessmentId: number): Promise<any> {
    const options: any = {
    observe: 'response',
    };
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    const promise = new Promise((resolve, reject) => {
    this.http.get(path + '/getResults/' + assessmentId, options).toPromise().then(
      res => {
      },
      msg => {
        reject(msg);
        return  null;
      }
    );
  });
return promise;
}
}
