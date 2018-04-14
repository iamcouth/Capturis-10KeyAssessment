import {HttpClient, HttpResponse} from "@angular/common/http";
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
}
