import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/timer';

@Component({
  selector: 'app-assessment',
  templateUrl: './assessment.component.html',
  styleUrls: ['./assessment.component.css']
})
export class AssessmentComponent implements OnInit {

  @ViewChild('in') input: string;
  @Input()
  testType = 0;
  index = 0;
  timeLeft = 60;
  timer;
  sub;
  a; b; c; d;
  inputs: any[];
  testValues = {};
  @Output()
  change: EventEmitter<number> = new EventEmitter<number>();
  change2: EventEmitter<string> = new EventEmitter<string>();

  increment($event) {
    if ($event.keyCode === 13) {
      this.testValues += this.a;
      this.a = this.b;
      this.b = this.c;
      this.c = this.d;
      this.d = this.d = this.generateRandom(this.testType);
      this.index++;
      if (this.index === 10) {
          this.redirect();
          //console.log(this.testValues);
      }
      this.change2.emit(this.a);
    } else {

    }
   }
  constructor(private router: Router) { }
  redirect() {
    this.router.navigate(['/assessment-results']);
  }
  generateRandom(x) {

    if (x === 0) {
        x = Math.floor(Math.random() * Math.floor(2)) + 1;
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
      return month + '/' + day + '/2017';
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
  }

  ngOnInit() {
      this.timer = Observable.timer(5000, 1000);
      this.sub = this.timer.subscribe(t => {
          this.counter();
      });
    this.a = this.generateRandom(this.testType);
    this.b = this.generateRandom(this.testType);
    this.c = this.generateRandom(this.testType);
    this.d = this.generateRandom(this.testType);

  }
}
