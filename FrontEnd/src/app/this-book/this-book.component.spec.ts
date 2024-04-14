import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ThisBookComponent } from './this-book.component';

describe('ThisBookComponent', () => {
  let component: ThisBookComponent;
  let fixture: ComponentFixture<ThisBookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ThisBookComponent]
    });
    fixture = TestBed.createComponent(ThisBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
