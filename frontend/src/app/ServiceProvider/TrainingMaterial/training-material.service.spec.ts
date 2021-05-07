import { TestBed } from '@angular/core/testing';

import { TrainingMaterialService } from './training-material.service';

describe('TrainingMaterialService', () => {
  let service: TrainingMaterialService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrainingMaterialService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
