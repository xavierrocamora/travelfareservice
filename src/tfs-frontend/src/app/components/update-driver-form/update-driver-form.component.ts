import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Driver } from 'src/app/domain/driver';
import { DriverService } from 'src/app/services/driver.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-update-driver-form',
  templateUrl: './update-driver-form.component.html',
  styleUrls: ['./update-driver-form.component.css']
})
export class UpdateDriverFormComponent implements OnInit {

  public driverId: any;
  public driver: any;
  public title: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private driverService: DriverService
  ) { 
    this.title = "Update Driver"
  }
    

  ngOnInit(): void {
    this.driverId = this.route.snapshot.params['id'];
    this.driverService.getDriverById(this.driverId).subscribe(
      {
        next: response => {
          console.log(response);
          this.driver = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    );
  }

  goToDriversList() {
    this.router.navigate(['/drivers']);
  }

  onSubmit(driver: Driver){
    console.log("upForm: ",driver);
    this.driverService.updateDriver(this.driverId, driver).subscribe(
      {
        next: () => 
          this.goToDriversList()
        ,
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    );
  }



}
