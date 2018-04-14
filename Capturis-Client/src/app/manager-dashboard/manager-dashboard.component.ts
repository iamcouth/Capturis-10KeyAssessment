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
  loadingIndicator: boolean = true;
  reorderable: boolean = true;
  rows = [ { name: 'test1' }, { name: 'test2' } , { name: 'test3' } ];
  columns = [ { name: 'name' }];



   constructor() {

    //this.fetch((data) => {

      //this.rows = data;

      //setTimeout(() => { this.loadingIndicator = false; }, 1500);

    });
    fetch(cb) {

    const req = new XMLHttpRequest();

    req.open('GET', `assets/data/company.json`);



    req.onload = () => {

      cb(JSON.parse(req.response));

    };



    req.send();

  }


  }

  ngOnInit() {
  }

}
