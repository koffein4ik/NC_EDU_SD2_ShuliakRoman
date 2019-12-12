import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlluserspageComponent } from './alluserspage.component';

describe('AlluserspageComponent', () => {
  let component: AlluserspageComponent;
  let fixture: ComponentFixture<AlluserspageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlluserspageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlluserspageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
