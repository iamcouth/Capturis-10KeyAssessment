import {Component, OnInit, Input, Output, EventEmitter, ViewChild, Inject} from '@angular/core';
import {Router, Params, ActivatedRoute} from '@angular/router';
import { Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import {DOCUMENT} from '@angular/common';
import {AssessmentService} from '../services/assessment.service';
import {Assessment} from './assessment.model';

@Component({
  selector: 'app-assessment',
  templateUrl: './assessment.component.html',
  styleUrls: ['./assessment.component.css'],
  providers: [AssessmentService]
})
export class AssessmentComponent implements OnInit {

  sessionId = parseInt(sessionStorage.getItem('userid'), 10);
  assessment: Assessment;
  assessmentId: any;
  testType;
  testDesc;
  enterCount = 0;
  start = 0;
  timeOfTest;
  timeLeft;
  displayTime;
  timer;
  timerSubscription;
  keystrokes = 0;
  backspaces = 0;
  currentExpectedValue; nextExpectedValue; thirdExpectedValue; fourthExpectedValue;
  inputValues: string[] = new Array(500);
  expectedValues: string[] = new Array(500);
  @Output()
  changeExpectedValues: EventEmitter<string> = new EventEmitter<string>();

  increment($event) {
    if (this.start === 0) {
        this.timer = Observable.timer(1000, 1000);
        this.start = 1;
        this.timerSubscription = this.timer.subscribe(t => {
        this.counter();
      });
    }

    if ($event.keyCode === 13) { // If backspace is pressed
      this.expectedValues[this.enterCount] = this.currentExpectedValue;
      this.inputValues[this.enterCount] = this.document.getElementById('userInput').value;
      this.currentExpectedValue = this.nextExpectedValue;
      this.nextExpectedValue = this.thirdExpectedValue;
      this.thirdExpectedValue = this.fourthExpectedValue;
      this.fourthExpectedValue = this.generateRandom(this.testType);
      this.enterCount++;
      this.changeExpectedValues.emit(this.currentExpectedValue);
      this.document.getElementById('userInput').value = '';

    } else if ($event.keyCode === 8) {
        this.backspaces++;
    } else {
        this.keystrokes++;
      }
  }

  constructor(private router: Router,
  private route: ActivatedRoute,
  @Inject(DOCUMENT) private document,
  private _assessmentService: AssessmentService
  ) { }
  redirect() {
    sessionStorage.setItem('assessmentid', this.assessmentId);
    this.expectedValues[this.enterCount] = this.currentExpectedValue;
    this.inputValues[this.enterCount] = this.document.getElementById('userInput').value;
    this.enterCount++;
    this.assessment = {
    userId: this.sessionId,
    dateTaken: new Date(),
    timeGiven: this.timeOfTest,
    typeId: this.testType,
    keystrokes: this.keystrokes,
    backspaces: this.backspaces,
    inputValues: this.inputValues,
    expectedValues: this.expectedValues,
    enterCount: this.enterCount,
    assessmentId: this.assessmentId
  };
    this._assessmentService.processData(this.assessment).subscribe(res => {
      this.router.navigate(['/assessment-results'], { relativeTo: this.route });
    });


}

  /**
   * Generating random numbers based on text
   * @param x
   * @returns {any}
   */
  generateRandom(x) {

    if (x === 4) {
      x = Math.floor(Math.random() * Math.floor(3)) + 1;
    }
    if (x === 3) {
      const month = Math.floor(Math.random() * Math.floor(12)) + 1;
      let range = 31;
      if (month === 2) {
        range = 28;
      } else if (month === 4 || month === 6 || month === 9 || month === 11) {
        range = 30;
      }
      const day = Math.floor(Math.random() * Math.floor(range)) + 1;

      if (month > 9) {
        if (day < 10) {
          return month + '/0' + day + '/2018';
        } else {
          return month + '/' + day + '/2018';
        }
      } else {
        if (day < 10) {
          return '0' + month + '/0' + day + '/2018';
        } else {
          return '0' + month + '/' + day + '/2018';
        }
      }
    } else if (x === 2) {
      const num = Math.floor(Math.random() * Math.floor(9999)) + 1;
      const dec = Math.floor(Math.random() * Math.floor(99)) + 1;

      return num + '.' + dec;
    } else if (x === 1) {
      const num = Math.floor(Math.random() * Math.floor(999999)) + 1;

      return num;
    }

  }

  /**
   * Timer logic
   */
  counter() {

    if (this.timeLeft === 0) {
      this.timerSubscription.unsubscribe();
      this.redirect();
    }
    this.timeLeft--;
    const minutes = Math.floor(this.timeLeft / 60);
    const seconds = this.timeLeft % 60;
    if (seconds < 10) {
      this.displayTime = minutes + ':0' + seconds;
    } else {
      this.displayTime = minutes + ':' + seconds;
    }

  }

  /**
   * Getting test type (Whole Number, Decimal, Date, Mixed)
   * @param x
   * @returns {number}
   */
  getTestType(x) {

    if (x === ':1') {
      this.testDesc = 'Whole Number Format';
      return 1;
    } else if (x === ':2') {
      this.testDesc = 'Decimal Number Format';
      return 2;
    } else if (x === ':3') {
      this.testDesc = 'Date Format';
      return 3;
    } else {
      this.testDesc = 'Mixed Format';
      return 4;
    }

  }

  /**
   * Based on time selected, 1,3,5 minute variations
   * @param x
   * @returns {number}
   */
  getTestTime(x) {
    if (x === ':1') {
      this.displayTime = '1:00';
      this.timeOfTest = 1;
      return 60;
    } else if (x === ':3') {
      this.displayTime = '3:00';
      this.timeOfTest = 3;
      return 180;
    } else if (x === ':5') {
      this.displayTime = '5:00';
      this.timeOfTest = 5;
      return 300;
    } else {
      this.displayTime = '1:00';
      return 60;
    }

  }

  ngOnInit() {

    let typeOfTest = '';
    let timeOfTest = '';
    this.route.params.subscribe((params: Params) => typeOfTest = params['type']);
    this.route.params.subscribe((params: Params) => timeOfTest = params['time']);

    this.testType = this.getTestType(typeOfTest);
    this.timeLeft = this.getTestTime(timeOfTest);

    this.currentExpectedValue = this.generateRandom(this.testType);
    this.nextExpectedValue = this.generateRandom(this.testType);
    this.thirdExpectedValue = this.generateRandom(this.testType);
    this.fourthExpectedValue = this.generateRandom(this.testType);

    this.assessment = {
    userId: this.sessionId,
    dateTaken: new Date(),
    timeGiven: this.timeOfTest,
    typeId: this.testType,
    keystrokes: 0,
    backspaces: 0,
    inputValues: this.inputValues,
    expectedValues: this.expectedValues,
    enterCount: 0,
    assessmentId: 0,
  };
    this._assessmentService.getNewAssessment(this.assessment).subscribe( (res => {this.assessmentId = (res); }));
  }
}
