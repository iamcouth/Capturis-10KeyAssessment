import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {


    constructor(private router: Router) {

        //this.router.navigate(['/assessment', this.id]);
    }

  ngOnInit() {
  }

}
