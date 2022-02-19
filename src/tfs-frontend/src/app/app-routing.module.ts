import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddDriverFormComponent } from './components/add-driver-form/add-driver-form.component';
import { DriversListComponent } from './components/drivers-list/drivers-list.component';
import { UpdateDriverFormComponent } from './components/update-driver-form/update-driver-form.component';

const routes: Routes = [
  {path: 'drivers', component: DriversListComponent},
  {path: 'add-driver', component: AddDriverFormComponent},
  {path: 'update-driver/:id', component: UpdateDriverFormComponent},
  {path: '', redirectTo: 'drivers', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
