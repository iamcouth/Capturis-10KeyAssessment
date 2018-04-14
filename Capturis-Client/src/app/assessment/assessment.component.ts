import { Component, OnInit, Input, Output, EventEmitter, ViewChild, Inject } from '@angular/core';
import { Router, Params, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-assessment',
  templateUrl: './assessment.component.html',
  styleUrls: ['./assessment.component.css']
})
export class AssessmentComponent implements OnInit {

  @ViewChild('in') input: string;
  @Input()
  testType;
  testDesc;
  start = 0;
  index = 0;
  timeLeft;
  displayTime;
  timer;
  sub;
  a; b; c; d;
  inputs: any[];
  testValues = {};
  @Output()
  change: EventEmitter<number> = new EventEmitter<number>();
  change2: EventEmitter<string> = new EventEmitter<string>();

  increment($event) {
// this.inputs += $event.keyCode;
    if (this.start === 0) {
      this.timer = Observable.timer(1000, 1000);
      this.start = 1;
      this.sub = this.timer.subscribe(t => {
        this.counter();
      });
    }

    if ($event.keyCode === 13) {
      this.testValues += this.a;
      this.a = this.b;
      this.b = this.c;
      this.c = this.d;
      this.d = this.d = this.generateRandom(this.testType);
      this.index++;
      this.change2.emit(this.a);
      this.document.getElementById('in').value = '';

    } else {

    }

  }
  constructor(private router: Router, private route: ActivatedRoute, @Inject(DOCUMENT) private document) { }
  redirect() {
    this.router.navigate(['/assessment-results']);
  }
  generateRandom(x) {

    if (x === 0) {
      x = Math.floor(Math.random() * Math.floor(3)) + 1;
    }
    if (x === 1) {
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
    } else if (x === 3) {
      const num = Math.floor(Math.random() * Math.floor(999999)) + 1;

      return num;
    }

  }

  counter() {

    if (this.timeLeft === 0) {
      this.sub.unsubscribe();
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

  getTestType(x) {

    if (x === ':date') {
      this.testDesc = 'Date Format';
      return 1;
    } else if (x === ':decimal') {
      this.testDesc = 'Decimal Number';
      return 2;
    } else if (x === ':whole') {
      this.testDesc = 'Whole Number';
      return 3;
    } else {
      this.testDesc = 'Mixed Format';
      return 0;
    }

  }

  getTestTime(x) {
    if (x === ':1') {
      this.displayTime = '1:00';
      return 60;
    } else if (x === ':3') {
      this.displayTime = '3:00';
      return 180;
    } else if (x === ':5') {
      this.displayTime = '5:00';
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

    this.a = this.generateRandom(this.testType);
    this.b = this.generateRandom(this.testType);
    this.c = this.generateRandom(this.testType);
    this.d = this.generateRandom(this.testType);

  }
}