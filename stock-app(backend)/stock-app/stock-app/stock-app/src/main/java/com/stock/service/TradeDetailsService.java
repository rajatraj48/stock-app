package com.stock.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stock.exeception.TradeDetailsValidationException;
import com.stock.model.TradeDetails;

public interface TradeDetailsService {

	TradeDetails createTradeDetails(TradeDetails tradeDetails);

	 Page<TradeDetails> getAllTradeDetails(Pageable pageable);

	TradeDetails updateTradeDetails(Integer id, TradeDetails tradeDetails);
	 TradeDetails getTradeDetailsById(Integer id) throws TradeDetailsValidationException;

	List<TradeDetails> findByCriteria(String stockName, String type, Date tradeDateTime, Float listingPrice,
			Integer quantity, Float pricePerUnit);

	void deleteTradeDetails(Integer id);
	}


