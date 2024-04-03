import { Component, signal } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';
import { sidebarData } from './sidebar-data';
import { NgClass, NgStyle } from '@angular/common';
import { SidebarItem } from './sidebar-item';
import { animate, state, style, transition, trigger } from '@angular/animations';


@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink,RouterModule,NgClass,NgStyle],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent {
  isActive: boolean=false;
  

  
menu=sidebarData;
subItem?: SidebarItem;


  activeItemIndex = signal(-1);

  toggleActive(index: number) {
    this.activeItemIndex.set(index) ;
  }
  

}
