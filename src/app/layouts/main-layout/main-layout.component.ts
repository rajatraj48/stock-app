import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { RouterModule } from '@angular/router';
import { NgClass, NgStyle } from '@angular/common';


@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent, RouterModule,NgClass,NgStyle],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent {

  isButtonToggled: boolean = false;

  onToggleButtonClicked(isToggled: boolean) {
    this.isButtonToggled = !this.isButtonToggled;
  }

}
