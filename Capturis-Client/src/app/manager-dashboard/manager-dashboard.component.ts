import { Component, OnInit, Input, Output, Inject, ViewChild } from '@angular/core';
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
  loadingIndicator = true;
  reorderable = true;
  rows = [ { name: 'test1' }, { name: 'test2' } , { name: 'test3' } ];
  columns = [ { name: 'name' }];



   constructor() {


  }

  ngOnInit() {
  }

}
