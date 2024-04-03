import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-stock',
  templateUrl: './order-stock.component.html',
  styleUrls: ['./order-stock.component.css']
})
export class OrderStockComponent implements OnInit {
  orders: any[] = []; // Define an array to store fetched orders

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    // Call the function to fetch order data when the component initializes
    this.fetchOrders();
  }

  fetchOrders(): void {
    // Make an HTTP GET request to fetch order data from the backend API
    this.http.get<any[]>('http://localhost:8080/api/v1/orders').subscribe(
      (response) => {
        // Assign the fetched orders to the orders array
        this.orders = response;
        console.log('Orders fetched successfully:', this.orders);
      },
      (error) => {
        // Handle error response
        console.error('Error fetching orders:', error);
      }
    );
  }

  redirectToAllStock(): void {
    // Navigate to the 'all-stock' route
    this.router.navigate(['/home/stocks/all']);
  }
}
