import { Component, Input } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  @Output() toggleButtonClicked: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() isButtonToggled: boolean = false;

  onToggleButtonClick() {
    this.toggleButtonClicked.emit(true);
  }
  constructor() {}

 
}
