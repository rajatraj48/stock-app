import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageAnimationDemoComponent } from './image-animation-demo.component';

describe('ImageAnimationDemoComponent', () => {
  let component: ImageAnimationDemoComponent;
  let fixture: ComponentFixture<ImageAnimationDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImageAnimationDemoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ImageAnimationDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
