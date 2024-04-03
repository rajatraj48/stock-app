import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

import { ActivatedRoute, Router } from '@angular/router';
import swal from 'sweetalert';
import { Stock } from '../../../models/stock';
import { ApiResponse } from '../../../models/api-response';

@Component({
  selector: 'app-all-stock',
  standalone: true,
  imports: [MatPaginatorModule],
  templateUrl: './all-stock.component.html',
  styleUrl: './all-stock.component.css'
})
export class AllStockComponent {
  id!: number;
  tradeDetails: Stock[] = [];
  dataSource: MatTableDataSource<Stock>;


  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.dataSource = new MatTableDataSource<Stock>([]);
   }

  ngOnInit(): void {
    this.getAllTradeDetails(this.pageIndex, this.pageSize);
    this.dataSource.paginator = this.paginator;
  }


  currentIndex = -1;







  //pageSizeOptions = [5,10,20];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;
  pageEvent: PageEvent | undefined;

  // Define properties for pagination
  pageIndex: number = 0;
  pageSize: number = 10; // Initial page size
  length: number = 0; // Total number of items
  pageSizeOptions: number[] = [5, 10, 25, 100]; // Options for page size

  // Reference to MatPaginator
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  handlePageEvent(e: PageEvent) {
    console.log(e, "><>")
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.getAllTradeDetails(this.pageIndex, this.pageSize);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }


  openModal(id: number) {
    console.log(id, ">>>>>>>>>>>>>>>");
    this.router.navigate(['/home/stocks/edit/', id])

  }
  getAllTradeDetails(pageIndex: number, pageSize: number): void {
    // Create HttpParams object to pass parameters in the URL
    let params = new HttpParams()
      .set('page', pageIndex.toString())
      .set('size', pageSize.toString());

    // Make GET request with parameters
    this.http.get<ApiResponse>('http://localhost:8080/api/v1/trade', { params: params }).subscribe(
      (response) => {
        console.log("Response:", response);
        console.log("Response:", response);
        this.tradeDetails = response.content; // Assuming the response contains a 'content' property with the trade details
        this.dataSource.data = this.tradeDetails; // Update dataSource with new data
        this.length = response.totalElements; // 
      },
      (error) => {
        console.error('Error fetching trade details:', error);
      }
    );
  }

  deletestock(id: number) {
    // Show a confirmation dialog using SweetAlert

    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this staff record!',
      icon: 'warning',
      buttons: ['Cancel', 'Delete'],
      dangerMode: true,
    }).then((result) => {
      if (result) {
        this.http.delete<any>('http://localhost:8080/api/v1/trade/' + id)
          .toPromise()
          .then(() => {
            swal('Success', 'Trade details deleted successfully', 'success');
            this.getAllTradeDetails(this.pageIndex, this.pageSize);
            //this.router.navigate(['/home/stocks/all']);
          })
          .catch((error) => {
            console.error('Error deleting trade details:', error);
            swal('Error', 'Failed to delete trade details', 'error');
          });
      }
    });



  }

  redirectToAddStock() {
    this.router.navigate(['/home/stocks/add']);
  }
  
  createOrder(s: any): void {
    // Prepare the data to be sent in the request body
    const orderData = {
      tradeDetailsList: [s], // Wrap 's' in an array to match the expected format
      status: 'confirmed'
    };
  
    // Make an HTTP POST request to your backend API endpoint
    this.http.post<any>('http://localhost:8080/api/v1/order', orderData).subscribe(
      (response) => {
        // Handle success response
        console.log('Order created successfully:', response);
        // Redirect to another page if needed
        swal({
          icon: 'success',
          title: 'Success',
          text: 'Order created successfully!',
        }).then(() => {
          this.router.navigate(['/home/stocks/order']);
        });
      },
      (error) => {
        // Handle error response
        console.error('Error creating order:', error);
      }
    );
    
  }
  


  
}
