import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatematerialComponent } from './updatematerial.component';

describe('UpdatematerialComponent', () => {
  let component: UpdatematerialComponent;
  let fixture: ComponentFixture<UpdatematerialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatematerialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatematerialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
