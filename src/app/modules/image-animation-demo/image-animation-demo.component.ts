import { animate, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';

@Component({
  selector: 'app-image-animation-demo',
  standalone: true,
  imports: [],
  templateUrl: './image-animation-demo.component.html',
  styleUrl: './image-animation-demo.component.css',
  // animations: [
  //   trigger('slideInLeft', [
  //     transition(':enter', [
  //       style({ transform: 'translateX(-100%)' }),
  //       animate('500ms ease-out', style({ transform: 'translateX(0)' })),
  //     ]),
  //     transition(":leave", [
  //       animate('500ms ease-out', style({ transform: 'translateX(-100%)' })),
  //     ])
  //   ])
  // ]
  // animations: [
  //   trigger('slideInBottom', [
  //     transition(':enter', [
  //       style({ transform: 'translateY(100%)' }),
  //       animate('500ms ease-out', style({ transform: 'translateY(0)' })),
  //     ]),
  //     transition(":leave", [
  //       animate('500ms ease-out', style({ transform: 'translateY(100%)' })),
  //     ])
  //   ])
  // ]

  animations: [
    trigger('slideInTop', [
      transition(':enter', [
        style({ transform: 'translateY(-100%)' ,height: 0}),
        animate('500ms ease-out', style({ transform: 'translateY(0)', height: '*' })),
      ]),
      transition(":leave", [
        animate('500ms ease-out', style({ transform: 'translateY(-100%)', height: 0  })),
      ])
    ])
  ],
  
  
})
export class ImageAnimationDemoComponent {
  imageVisible = true;

  toggleImage() {
    this.imageVisible == false ? this.imageVisible = true : this.imageVisible = false
  }

}
