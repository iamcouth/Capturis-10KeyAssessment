import {HttpClient, HttpResponse, HttpHeaders} from '@angular/common/http';
import {Observable}     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/timeout';
import {Injectable} from '@angular/core';


const path = 'http://localhost:8080/api/assessment';
// Service related to assessment page
@Injectable()
export class AssessmentService {

  constructor(private http: HttpClient) { }

  processData(arg1: any[], arg2: any[], arg3: number, arg4: number): Observable<any> {
    const options: any = {
    observe: 'response',
    };
    const header = new HttpHeaders({'Accept': 'application/x-www-form-urlencoded', 'Content-Type': 'application/x-www-form-urlencoded'});
    let output: any;
    let obj: any =
    {
    "col1": arg1,
    "col2": arg2,
    "col3": arg3,
    "col4": arg4
    };

    return this.http.post(path + '/process', (obj), {headers: header});
  }
}
