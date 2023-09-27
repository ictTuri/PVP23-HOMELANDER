import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { AuthenticationComponent } from "./authentication/authentication/authentication.component";
import { ProfileComponent } from "./profile/profile.component";
import { SettingsComponent } from "./settings/settings.component";
import { AuthGuardService } from "./authentication/security/auth-guard.service";

const appRoutes: Routes = [
    {path: '', component: DashboardComponent},
    {path: 'auth', component: AuthenticationComponent},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService]},
    {path: 'settings', component: SettingsComponent, canActivate: [AuthGuardService]}
  ]

@NgModule({
    imports: [
      RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule]
  })
export class AppRoutingModule { }