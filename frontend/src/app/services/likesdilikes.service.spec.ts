import { TestBed } from '@angular/core/testing';

import { LikesdilikesService } from './likesdilikes.service';

describe('LikesdilikesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LikesdilikesService = TestBed.get(LikesdilikesService);
    expect(service).toBeTruthy();
  });
});
