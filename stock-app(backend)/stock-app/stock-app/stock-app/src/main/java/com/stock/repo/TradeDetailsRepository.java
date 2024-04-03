package com.stock.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stock.model.TradeDetails;

public interface TradeDetailsRepository extends JpaRepository<TradeDetails, Integer> {
	
	 @Query("SELECT td FROM TradeDetails td " +
	            "WHERE (:stockName IS NULL OR td.stockName = :stockName) " +
	            "AND (:type IS NULL OR td.type = :type) " +
	            "AND (:tradeDateTime IS NULL OR td.tradeDateTime = :tradeDateTime) " +
	            "AND (:listingPrice IS NULL OR td.listingPrice = :listingPrice) " +
	            "AND (:quantity IS NULL OR td.quantity = :quantity) " +
	            "AND (:pricePerUnit IS NULL OR td.pricePerUnit = :pricePerUnit)")
	    List<TradeDetails> findByCriteria(String stockName, String type, Date tradeDateTime, Float listingPrice, Integer quantity, Float pricePerUnit);

}
