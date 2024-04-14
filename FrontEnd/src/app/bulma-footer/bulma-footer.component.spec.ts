import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BulmaFooterComponent } from './bulma-footer.component';

describe('BulmaFooterComponent', () => {
  let component: BulmaFooterComponent;
  let fixture: ComponentFixture<BulmaFooterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BulmaFooterComponent]
    });
    fixture = TestBed.createComponent(BulmaFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
