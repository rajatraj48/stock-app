import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderStockComponent } from './order-stock.component';

describe('OrderStockComponent', () => {
  let component: OrderStockComponent;
  let fixture: ComponentFixture<OrderStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderStockComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrderStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
