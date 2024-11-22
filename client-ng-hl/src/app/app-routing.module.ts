import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { AuthenticationComponent } from "./authentication/authentication/authentication.component";
import { ProfileComponent } from "./profile/profile.component";
import { SettingsComponent } from "./settings/settings.component";
import { AuthGuardService } from "./authentication/security/auth-guard.service";
import { HomeComponent } from "./dashboard/home/home.component";

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/home', // Redirect to /home when the path is empty
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: DashboardComponent,
    children: [
      { path: '', component: HomeComponent }, // Default child route for /home
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [AuthGuardService],
        children: [
          { path: 'settings', component: SettingsComponent, canActivate: [AuthGuardService] }
        ]
      },
    ]
  },
  { path: 'auth', component: AuthenticationComponent },
  { path: '**', redirectTo: '/home' } // Redirect any other invalid paths to /home
];

@NgModule({
    imports: [
      RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule]
  })
export class AppRoutingModule { }