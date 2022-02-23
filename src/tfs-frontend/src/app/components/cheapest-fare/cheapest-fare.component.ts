import { Component, OnInit } from '@angular/core';
import { Driver } from 'src/app/domain/driver';
import { TravelFare } from 'src/app/domain/travelFare';
import { FareService } from 'src/app/services/fare.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CheapestFareDTO } from 'src/app/domain/cheapestFareDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cheapest-fare',
  templateUrl: './cheapest-fare.component.html',
  styleUrls: ['./cheapest-fare.component.css']
})
export class CheapestFareComponent implements OnInit {
  public title: string;
  public travelHeader: string;
  public bestDriverHeader: string;
  public loading: boolean;
  public driver!: Driver;
  public travelFare!: TravelFare;
  public cheapestCost: number;

  constructor(
      private fareService: FareService,
      private router: Router
    ) { 
    this.title = 'The cheapest fare for your travel: ';
    this.travelHeader = 'Your travel search: ';
    this.bestDriverHeader = "Recommended driver: ";
    this.loading = false;
    this.cheapestCost = 0;
  }

  ngOnInit(): void {
    this.loading = true;
    this.getCheapestFare();
  }

  onReturnBack() {
    this.router.navigate(['drivers']);
  }

  private getCheapestFare() {
    this.fareService.getCheapestFare().subscribe(
      {
        next: response => {
          console.log(response);
          if(response.hasOwnProperty("cheapestCost")){
            this.driver = (response as CheapestFareDTO).cheapestDriver;
            this.cheapestCost = (response as CheapestFareDTO).cheapestCost;
            this.travelFare = (response as CheapestFareDTO).travelFareData;
          }
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    );
  }

}
