package com.stock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class StockDetails {
	
    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
	
	    @Column(name = "stock_name")
	    private String stockName;
	    
	    @Column(name = "listing_price")
	    private float listingPrice;
	    
	    @Column(name = "quantity")
	    private Integer quantity;

}
