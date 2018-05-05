import { Component, ViewChild, OnInit } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { ManagerDashboardService } from '../services/manager-dashboard.service';
import { ManagerHistory } from './manager-dashboard.model';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.css'],
  providers: [ManagerDashboardService]
})
export class ManagerDashboardComponent implements OnInit {

  displayedColumns = ['name', 'assessmentName', 'timeGiven', 'kph', 'accuracy', 'dateTaken', 'jobCode'];
  dataSource;
  RESULT_DATA: ManagerHistory[];
  @ViewChild(MatSort) sort: MatSort;

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  constructor(private _managerDashboardService: ManagerDashboardService) {}

  ngOnInit() {
      this._managerDashboardService.getAll().subscribe(res => {
        this.RESULT_DATA = res.body;
        this.dataSource = new MatTableDataSource(this.RESULT_DATA);
        this.dataSource.sort = this.sort;
      },
        err => {
      });
    }
}
