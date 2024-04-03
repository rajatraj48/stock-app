import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Stock } from '../../../models/stock';
import { FormsModule, NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import swal from 'sweetalert';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-stock',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './edit-stock.component.html',
  styleUrl: './edit-stock.component.css'
})
export class EditStockComponent {

  id!: number;
  stock: any = {};

  tradeDetails: any;
  constructor(private route: ActivatedRoute,private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      console.log('ID:', this.id);
      this.fetchTradeDetailsById(this.id);
    });
  }

  fetchTradeDetailsById(id: number): void {
    this.http.get<any>('http://localhost:8080/api/v1/trade/' + id).subscribe(
      (response) => {
        this.stock = response;
        console.log('Fetched trade details:', this.tradeDetails);
        // Process the response
      },
      (error) => {
        console.error('Error fetching trade details:', error);
        // Handle error as needed
      }
    );
  }

  onSubmit(myForm: NgForm) {
    const data: Stock = {
      id: this.stock.id,
      stockName: this.stock.stockName,
      listingPrice: this.stock.listingPrice,
      pricePerUnit: this.stock.pricePerUnit,
      quantity: this.stock.quantity,
      type: this.stock.type,
      tradeDateTime: this.stock.tradeDateTime
    };

    // Make HTTP PUT request to update the stock
    this.http.put<any>(`http://localhost:8080/api/v1/trade/${data.id}`, data)
      .subscribe(
        (response) => {
          console.log('Stock updated successfully:', response);
          if(response.statusCode===200){
            swal({
              icon: 'success',
              title: 'Success',
              text: 'Stock has been updated successfully!',
            }).then(() => {
              this.router.navigate(['/home/stocks/all']);
            });
          }
          // Handle success, e.g., display a success message or navigate to another page
        },
        (error) => {
          console.error('Error updating stock:', error);
          // Handle error, e.g., display an error message
        }
      );
  }
}
