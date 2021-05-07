import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedMaterialComponent } from './assigned-material.component';

describe('AssignedMaterialComponent', () => {
  let component: AssignedMaterialComponent;
  let fixture: ComponentFixture<AssignedMaterialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignedMaterialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignedMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
