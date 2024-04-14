import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RedbuttonComponent } from './redbutton.component';

describe('RedbuttonComponent', () => {
  let component: RedbuttonComponent;
  let fixture: ComponentFixture<RedbuttonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RedbuttonComponent]
    });
    fixture = TestBed.createComponent(RedbuttonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
