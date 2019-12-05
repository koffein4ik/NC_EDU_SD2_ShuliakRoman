import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EdituserinfopageComponent } from './edituserinfopage.component';

describe('EdituserinfopageComponent', () => {
  let component: EdituserinfopageComponent;
  let fixture: ComponentFixture<EdituserinfopageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EdituserinfopageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EdituserinfopageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
