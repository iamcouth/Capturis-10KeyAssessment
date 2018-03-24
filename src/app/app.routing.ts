﻿import { Routes, RouterModule } from '@angular/router';

import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

//import { AuthGuard } from './_guards/index';
import { AssessmentComponent } from './assessment/assessment.component';
import { AssessmentResultsComponent } from './assessment-results/assessment-results.component';


const appRoutes: Routes = [
    { path: '', component: UserDashboardComponent  },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'assessment', component: AssessmentComponent },
    { path: 'assessment-results', component: AssessmentResultsComponent },
    { path: 'home', component: UserDashboardComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
