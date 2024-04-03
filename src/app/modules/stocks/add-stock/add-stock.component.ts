import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Stock } from '../../../models/stock';
import { HttpClient } from '@angular/common/http';
import swal from 'sweetalert';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-stock',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './add-stock.component.html',
  styleUrl: './add-stock.component.css'
})
export class AddStockComponent {

  stock: Stock = {
    id: 0,
    tradeDateTime: new Date(),
    stockName: '',
    listingPrice: 0,
    quantity: 0,
    type: '',
    pricePerUnit: 0
  };

  constructor(private http: HttpClient,private router: Router) { }

  onSubmit(myForm: NgForm): void {
    if(myForm.valid){
// Make an HTTP POST request to your backend API endpoint
      this.http.post<any>('http://localhost:8080/api/v1/trade', this.stock)
      .subscribe(response => {
        console.log('Response from backend:', response);
        // Handle the response here
        if(response.statusCode===200){
          swal({
            icon: 'success',
            title: 'Success',
            text: 'Stock has been added successfully!',
          }).then(() => {
            this.router.navigate(['/home/stocks/all']);
          });
        }

      }, error => {
        console.error('Error:', error);
        // Handle any errors here
      });

    }else{
      swal({
        icon: 'error',
        title: 'Error',
        text: 'Kindly fill all the fields.',
      });
    }
    
    
  }




}
