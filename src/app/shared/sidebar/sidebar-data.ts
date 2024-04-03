import { SidebarItem } from "./sidebar-item";

export const sidebarData: SidebarItem[] = [






  {
    label: 'Trade',
    icon: 'fa fa-person',
    link: 'staffs/all',
    enable: true,
    subItems: [
      { label: 'All Stock', icon: 'person', link: 'stocks/all', enable: true },
      { label: 'Add Stock', icon: 'person', link: 'stocks/add', enable: true },
      { label: 'Create Order', icon: 'person', link: 'stocks/order', enable: true },

    ]
  }



];



