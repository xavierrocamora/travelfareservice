import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheapestFareComponent } from './cheapest-fare.component';

describe('CheapestFareComponent', () => {
  let component: CheapestFareComponent;
  let fixture: ComponentFixture<CheapestFareComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheapestFareComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheapestFareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
