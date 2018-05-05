import { BrowserModule } from '@angular/platform-browser';

import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { AssessmentComponent } from './assessment/assessment.component';

import { AssessmentResultsComponent } from './assessment-results/assessment-results.component';

import { RecaptchaModule } from 'ng-recaptcha';

import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';

import { LoginComponent } from './login/login.component';

import { RegisterComponent } from './register/register.component';

import { routing } from './app.routing';

import { EmailValidator, FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ManagerDashboardComponent } from './manager-dashboard/manager-dashboard.component';

import { AdminComponent } from './admin/admin.component';

import { HttpClientModule } from '@angular/common/http';

import { MatButtonModule, MatInputModule } from '@angular/material';

import { MatCardModule } from '@angular/material/card';

import { RegisterService } from './services/register.service';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { MatTableModule } from '@angular/material';

import { TextMaskModule } from 'angular2-text-mask';

import { MatSortModule } from '@angular/material/sort';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


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
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    routing,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
    NgxDatatableModule,
    MatTableModule,
    RecaptchaModule.forRoot(),
    TextMaskModule,
    MatSortModule
  ],
  providers: [RegisterService, EmailValidator],
  bootstrap: [AppComponent]
})
export class AppModule { }
