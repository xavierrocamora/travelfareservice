import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DriverService } from 'src/app/services/driver.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-driver-form',
  templateUrl: './add-driver-form.component.html',
  styleUrls: ['./add-driver-form.component.css']
})
export class AddDriverFormComponent implements OnInit {

  public title: string;

  constructor(
    private driverService: DriverService,
    private router: Router
  ) {
      this.title = 'New driver';
    }

  ngOnInit(): void {
    console.log('AddDriverForm component loaded...');
  }

  goToDriversList() {
    this.router.navigate(['/drivers']);
  }

  onSubmit(addDriverForm: NgForm){
    this.driverService.addDriver(addDriverForm.value).subscribe(
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
