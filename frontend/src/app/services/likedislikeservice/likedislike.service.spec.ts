import { TestBed } from '@angular/core/testing';

import { LikedislikeService } from './likedislike.service';

describe('LikedislikeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LikedislikeService = TestBed.get(LikedislikeService);
    expect(service).toBeTruthy();
  });
});
