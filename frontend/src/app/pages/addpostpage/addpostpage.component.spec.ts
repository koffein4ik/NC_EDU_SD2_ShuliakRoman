import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddpostpageComponent } from './addpostpage.component';

describe('AddpostpageComponent', () => {
  let component: AddpostpageComponent;
  let fixture: ComponentFixture<AddpostpageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddpostpageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddpostpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
