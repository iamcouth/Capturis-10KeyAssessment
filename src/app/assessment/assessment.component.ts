import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs/Rx';
import '../assessment/assessment.component.css';
@Component({
  selector: 'app-assessment',
  templateUrl: './assessment.component.html',
  styleUrls: ['./assessment.component.css']
})
export class AssessmentComponent implements OnInit {

  @ViewChild('in') input: string;
  @Input()
  count: number[] = [824, 190, 632, 581, 247, 706, 913, 805, 338, 740];
  index = "";
  index2 = 0;
  start = 60;
  ticks = 0;
  @Output()
  change: EventEmitter<number> = new EventEmitter<number>();
  change2: EventEmitter<string> = new EventEmitter<string>();

  increment($event) {
    if ($event.keyCode === 13) {
      this.index = this.randomDate();
      this.index2++;
      this.change.emit(this.index2);
      this.change2.emit(this.index);
      //if (this.index === 10) {
        //this.redirect();
      //}
    } else {
      this.index2++;
      this.change.emit(this.index2);
    }
   }
  constructor(private router: Router) { }
  redirect() {
    this.router.navigate(['/assessment-results']);
  }
  randomDate() {
  	const t1 = Math.floor(Math.random() * Math.floor(12));
  	const t2 = Math.floor(Math.random() * Math.floor(30));
  	return t1 + "/" + t2 + "/2017";
  }

  ngOnInit() {
    const timer = Observable.timer(5000, 1000);
    timer.subscribe(t => this.ticks = t);
  }
}
