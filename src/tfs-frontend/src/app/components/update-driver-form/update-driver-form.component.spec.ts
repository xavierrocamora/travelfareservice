import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDriverFormComponent } from './update-driver-form.component';

describe('UpdateDriverFormComponent', () => {
  let component: UpdateDriverFormComponent;
  let fixture: ComponentFixture<UpdateDriverFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateDriverFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateDriverFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
