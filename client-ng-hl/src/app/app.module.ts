import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { NavigationComponent } from './dashboard/home/navigation/navigation.component';
import { AuthenticationComponent } from './authentication/authentication/authentication.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { FilterComponent } from './dashboard/filter/filter.component';
import { SettingsComponent } from './settings/settings.component';
import { HomeComponent } from './dashboard/home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AddPropertyDialogComponent } from './dashboard/home/pop-up/add-property-dialog/add-property-dialog.component';
import { FooterComponent } from './dashboard/footer/footer.component';
import { AppRoutingModule } from './app-routing.module';
import { LoadingSpinner } from './shared/loading-spinner/loading-spinner.component';
import { MatDialogModule } from '@angular/material/dialog';
import { HttpCustomInterceptor } from './interceptor/http-custom-interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ErrorCustomInterceptor } from './interceptor/error-custom-interceptor';
import { TruncatePipe } from './pipe/truncate.pipe';
import { PropertiesListComponent } from './dashboard/home/properties-list/properties-list.component';
import { PropertyComponent } from './dashboard/home/properties-list/property/property.component';
import { SidepanelComponent } from './dashboard/sidepanel/sidepanel.component';
import { RouterOutlet } from "@angular/router";
import { StoreModule } from '@ngrx/store';
import { userReducer } from './store/user/user.reducer';
import { notificationReducer } from './store/notification/notification.reducer';
import { EffectsModule } from '@ngrx/effects';
import { propertyReducer } from './store/properties/property.reducer';
import { UserEffect } from './store/user/user.effects';
import { PropertyEffect } from './store/properties/property.effects';
import { UploadimageComponent } from './profile/upload-image/uploadimage/uploadimage.component';


@NgModule({
    declarations: [
        AppComponent,
        NavigationComponent,
        AuthenticationComponent,
        DashboardComponent,
        ProfileComponent,
        FilterComponent,
        PropertiesListComponent,
        PropertyComponent,
        SettingsComponent,
        HomeComponent,
        AddPropertyDialogComponent,
        FooterComponent,
        LoadingSpinner,
        TruncatePipe,
        SidepanelComponent,
        UploadimageComponent
    ],
    providers: [
        provideAnimationsAsync(),
        { provide: HTTP_INTERCEPTORS, useClass: HttpCustomInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorCustomInterceptor, multi: true }
    ],
    bootstrap: [AppComponent],
    imports: [
        AppRoutingModule,
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        NgbModule,
        MatDialogModule,
        BrowserAnimationsModule,
        ToastrModule.forRoot({
            timeOut: 4000,
            preventDuplicates: true,
        }),
        RouterOutlet,
        StoreModule.forRoot({
            user: userReducer,
            property: propertyReducer,
            notification: notificationReducer
        }, {}),
        EffectsModule.forRoot([UserEffect, PropertyEffect])
    ]
})
export class AppModule {
}
