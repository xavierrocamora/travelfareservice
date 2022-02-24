import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { DriverService } from 'src/app/services/driver.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-driver',
  templateUrl: './driver.component.html',
  styleUrls: ['./driver.component.css']
})
export class DriverComponent implements OnInit {

  @Input() driver: any;

  @Output() deleted = new EventEmitter;

  constructor(
    private driverService: DriverService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onUpdateDriver(id: number) {
    this.router.navigate(['update-driver', id]);
  }

  onOpenModal() {
    const container = document.getElementById('main-view-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');
    button.setAttribute('data-bs-target', '#myModal');
    container?.appendChild(button);
    button.click();
  }

  onDeleteDriver(id: number){
    this.driverService.deleteDriver(id).subscribe(
      {
        next: response => this.deleted.emit({ deleted: 'true' }),
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    );
  }

}
