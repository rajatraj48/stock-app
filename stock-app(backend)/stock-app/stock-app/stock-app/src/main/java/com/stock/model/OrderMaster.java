package com.stock.model;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="order_master")
public class OrderMaster {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    
	    @Column(name = "trade_date_time")
	    private Date tradeDateTime;
	    
	    @Column(name = "stock_name")
	    private String stockName;
	    
	    @Column(name = "listing_price")
	    private float listingPrice;
	    
	    @Column(name = "quantity")
	    private Integer quantity;
	    
	    @Column(name = "type")
	    private String type;
	    
	    @Column(name = "price_per_unit")
	    private float pricePerUnit;
	    
	    @OneToMany(mappedBy = "orderMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<TradeDetails> tradeDetailsList = new ArrayList<>();

	    
	    @Column(name = "status")
	    private String status;
	    

}
