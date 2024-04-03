package com.stock.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="trade_details")
public class TradeDetails {
	
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
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "order_master_id")
	    private OrderMaster orderMaster;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getTradeDateTime() {
			return tradeDateTime;
		}

		public void setTradeDateTime(Date tradeDateTime) {
			this.tradeDateTime = tradeDateTime;
		}

		public String getStockName() {
			return stockName;
		}

		public void setStockName(String stockName) {
			this.stockName = stockName;
		}

		public float getListingPrice() {
			return listingPrice;
		}

		public void setListingPrice(float listingPrice) {
			this.listingPrice = listingPrice;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Float getPricePerUnit() {
			return pricePerUnit;
		}

		public void setPricePerUnit(Float pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
		}
	
	    
	
}
