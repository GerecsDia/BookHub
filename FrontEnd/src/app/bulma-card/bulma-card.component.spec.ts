import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BulmaCardComponent } from './bulma-card.component';

describe('BulmaCardComponent', () => {
  let component: BulmaCardComponent;
  let fixture: ComponentFixture<BulmaCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BulmaCardComponent]
    });
    fixture = TestBed.createComponent(BulmaCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
