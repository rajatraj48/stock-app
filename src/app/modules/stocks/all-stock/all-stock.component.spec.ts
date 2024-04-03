import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllStockComponent } from './all-stock.component';

describe('AllStockComponent', () => {
  let component: AllStockComponent;
  let fixture: ComponentFixture<AllStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllStockComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
