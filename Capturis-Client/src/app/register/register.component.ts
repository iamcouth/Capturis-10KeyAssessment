import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  validateFields() {
    if (this.document.getElementById('first_name').value === '') {
      this.document.getElementById('first_name').style.border = '1px solid #ff0000';
      this.document.getElementById('first_name').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('last_name').value === '') {
      this.document.getElementById('last_name').style.border = '1px solid #ff0000';
      this.document.getElementById('last_name').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('email').value === '') {
      this.document.getElementById('email').style.border = '1px solid #ff0000';
      this.document.getElementById('email').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('phone_number').value === '') {
      this.document.getElementById('phone_number').style.border = '1px solid #ff0000';
      this.document.getElementById('phone_number').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('address1').value === '') {
      this.document.getElementById('address1').style.border = '1px solid #ff0000';
      this.document.getElementById('address1').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('city').value === '') {
      this.document.getElementById('city').style.border = '1px solid #ff0000';
      this.document.getElementById('city').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('state').value === '') {
      this.document.getElementById('state').style.border = '1px solid #ff0000';
      this.document.getElementById('state').placeholder = 'REQUIRED';
    }
    if (this.document.getElementById('zip').value === '') {
      this.document.getElementById('zip').style.border = '1px solid #ff0000';
      this.document.getElementById('zip').placeholder = 'REQUIRED';
    } else {
      this.document.getElementById('zip').style.border = '';
    }
    if (this.document.getElementById('password').value === '') {
      this.document.getElementById('password').style.border = '1px solid #ff0000';
      this.document.getElementById('password').placeholder = 'REQUIRED';
    } else {
      this.document.getElementById('password').style.border = '';
    }

    console.log(this.document.getElementById('password').value === this.document.getElementById('confirm_password').value);
  }

  constructor(@Inject(DOCUMENT) private document) { }

  ngOnInit() {
  }

}
