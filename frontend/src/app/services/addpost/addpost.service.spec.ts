import { TestBed } from '@angular/core/testing';

import { AddpostService } from './addpost.service';

describe('AddpostService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddpostService = TestBed.get(AddpostService);
    expect(service).toBeTruthy();
  });
});
