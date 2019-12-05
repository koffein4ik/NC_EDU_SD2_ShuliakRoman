import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HashtagpageComponent } from './hashtagpage.component';

describe('HashtagpageComponent', () => {
  let component: HashtagpageComponent;
  let fixture: ComponentFixture<HashtagpageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HashtagpageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HashtagpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
