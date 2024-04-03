import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';

import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { ImageAnimationDemoComponent } from './modules/image-animation-demo/image-animation-demo.component';

import { AddStockComponent } from './modules/stocks/add-stock/add-stock.component';
import { AllStockComponent } from './modules/stocks/all-stock/all-stock.component';
import { EditStockComponent } from './modules/stocks/edit-stock/edit-stock.component';
import { OrderStockComponent } from './modules/stocks/order-stock/order-stock.component';


export const routes: Routes = [
    { path: 'login', component: LoginLayoutComponent },
    {
        path: 'home', component: MainLayoutComponent, children: [

            
            { path: 'test', component: ImageAnimationDemoComponent },
            { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
            
            { path: 'stocks/all', component: AllStockComponent },
            { path: 'stocks/add', component: AddStockComponent },
            { path: 'stocks/edit/:id', component: EditStockComponent },
            { path: 'stocks/order', component: OrderStockComponent },
            { path: 'stocks/order/:id', component: OrderStockComponent }




        ]
    },


    { path: '', redirectTo: '/home', pathMatch: 'full' }
];
