import { Component, OnInit, Input, Output, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.css']
})
export class ManagerDashboardComponent implements OnInit {

  @Input()
  jobCode;
  name;
  KPH;
  accuracy;

  search() {
    this.jobCode = this.document.getElementById('search').value;

    if (this.jobCode === '123') {
      this.name = 'Jamie';
      this.KPH = '10,000';
      this.accuracy = '105%';
    } else {
      this.name = 'none';
      this.KPH = '';
      this.accuracy = '';
    }
  }

  constructor(@Inject(DOCUMENT) private document) { }

  ngOnInit() {
  }

}
