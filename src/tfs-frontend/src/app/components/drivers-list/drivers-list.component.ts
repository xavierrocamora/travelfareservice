import { Component, OnInit } from '@angular/core';
import { Driver } from "../../domain/driver";
import { DriverService } from 'src/app/services/driver.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-drivers-list',
  templateUrl: './drivers-list.component.html',
  styleUrls: ['./drivers-list.component.css']
})
export class DriversListComponent implements OnInit {

  drivers: Driver[];

  constructor(
    private driverService: DriverService
    ) {
      this.drivers = [];
     }

  ngOnInit(): void {
    this.getDriversList();
  }

  private getDriversList() {
    this.driverService.getDriversList().subscribe(
      {
        next: (response: Driver[]) => {
          this.drivers = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    );
  }

  public driverDeleted(event: any): void {
      this.getDriversList();
  }

}
