import { Component, OnInit } from '@angular/core';
import { AssessmentResults } from './assessment-results.model';
import { AssessmentResultService } from './assessment-results.service';

@Component({
  selector: 'app-assessment-results',
  templateUrl: './assessment-results.component.html',
  styleUrls: ['./assessment-results.component.css'],
  providers: [AssessmentResultService]
})
export class AssessmentResultsComponent implements OnInit {
  result: any;
  constructor(private _assessmentResultService: AssessmentResultService) { }

  ngOnInit() {
    let test = parseInt(sessionStorage.getItem("assessmentid"));
    console.log(test);
    this._assessmentResultService.getResult(test).subscribe(res => {this.result = (res)});
    console.log(this.result);
  }

}
