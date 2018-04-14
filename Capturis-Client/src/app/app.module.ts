import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
/*import { BotDetectCaptchaModule } from 'angular-captcha';*/

import { AppComponent } from './app.component';
import { AssessmentComponent } from './assessment/assessment.component';
import { AssessmentResultsComponent } from './assessment-results/assessment-results.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { routing } from './app.routing';
import {EmailValidator, FormsModule} from '@angular/forms';
import { ManagerDashboardComponent } from './manager-dashboard/manager-dashboard.component';
import { AdminComponent } from './admin/admin.component';
import { HttpClientModule } from "@angular/common/http";
import { MatButtonModule, MatInputModule } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import {RegisterService} from "./register/register.service";


@NgModule({
  declarations: [
    AppComponent,
    AssessmentComponent,
    AssessmentResultsComponent,
    UserDashboardComponent,
    LoginComponent,
    RegisterComponent,
    ManagerDashboardComponent,
    AdminComponent,
  ],
  imports: [
    BrowserModule,
    MatButtonModule,
    MatInputModule,
    routing,
    FormsModule,
    HttpClientModule,
    MatCardModule
  ],
  providers: [RegisterService, EmailValidator],
  bootstrap: [AppComponent]
})
export class AppModule { }
