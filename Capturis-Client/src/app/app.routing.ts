import { Routes, RouterModule } from '@angular/router';

import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

// import { AuthGuard } from './_guards/index';
import { AssessmentComponent } from './assessment/assessment.component';
import { AssessmentResultsComponent } from './assessment-results/assessment-results.component';
import { ManagerDashboardComponent } from './manager-dashboard/manager-dashboard.component';


const appRoutes: Routes = [
  { path: '', component: LoginComponent  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'assessment/:type/:time', component: AssessmentComponent },
  { path: 'assessment-results', component: AssessmentResultsComponent },
  { path: 'home', component: UserDashboardComponent },
  { path: 'manager', component: ManagerDashboardComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes);
