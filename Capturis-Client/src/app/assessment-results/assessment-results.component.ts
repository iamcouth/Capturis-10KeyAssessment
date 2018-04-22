import { Component, OnInit, Output, EventEmitter } from '@angular/core';
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
  kph: number;
  backspaces: number;
  accuracy: any;
  userId = 1;
  found: any;
  done = 0;

  @Output()
  change: EventEmitter<any> = new EventEmitter<number>();

  assessmentResult: AssessmentResults = {
    userId: this.userId,
    dateTaken: null,
    timeGiven: null,
    typeId: null,
    keystrokes: null,
    kph: null,
    backspaces: null,
    accuracy: null,
    assessmentId: null
  }
  constructor(private _assessmentResultService: AssessmentResultService) { }

  ngOnInit() {
    let test = sessionStorage.getItem("inputArray");
    let assessmentId = parseInt(sessionStorage.getItem("assessmentid"));
    setTimeout(() => {
        this.getResults(assessmentId);
    }, 3000);

  }
  getResults(id) {
    this._assessmentResultService.getResult(id).subscribe(res => {
      console.log(res.body);
      this.kph = res.body.kph;
      this.backspaces = res.body.backspacePressCount;
      this.accuracy = res.body.accuracy;
      },
      err => {
        console.log(err); });

  }
  getData(res) {

  }

}
