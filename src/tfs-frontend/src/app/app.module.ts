import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// Components
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DriverComponent } from './components/driver/driver.component';
import { DriversListComponent } from './components/drivers-list/drivers-list.component';
import { AddDriverFormComponent } from './components/add-driver-form/add-driver-form.component';
import { UpdateDriverFormComponent } from './components/update-driver-form/update-driver-form.component';

@NgModule({
  declarations: [
    AppComponent,
    DriversListComponent,
    DriverComponent,
    AddDriverFormComponent,
    UpdateDriverFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
