package com.stock.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.stock.exeception.TradeDetailsValidationException;
import com.stock.model.TradeDetails;
import com.stock.repo.TradeDetailsRepository;
import com.stock.service.TradeDetailsService;
@Service
public class TradeDetailsServiceImpl implements TradeDetailsService {
	
	   @Autowired
	    private TradeDetailsRepository tradeDetailsRepository;

	@Override
	public TradeDetails createTradeDetails(TradeDetails tradeDetails) {
		 // Set trade date time before saving
        tradeDetails.setTradeDateTime(new Date(System.currentTimeMillis()));
		return tradeDetailsRepository.save(tradeDetails);
	}

	 @Override
	    public Page<TradeDetails> getAllTradeDetails(Pageable pageable) {
	        return tradeDetailsRepository.findAll(pageable);
	    }

	 @Override
	    public TradeDetails updateTradeDetails(Integer id, TradeDetails tradeDetails) {
	        TradeDetails existingTradeDetails = getTradeDetailsById(id);
	        if (existingTradeDetails == null) {
	            throw new TradeDetailsValidationException("Trade details with ID " + id + " not found");
	        }

	        // Update existing trade details
	        existingTradeDetails.setStockName(tradeDetails.getStockName());
	        existingTradeDetails.setListingPrice(tradeDetails.getListingPrice());
	        existingTradeDetails.setQuantity(tradeDetails.getQuantity());
	        existingTradeDetails.setType(tradeDetails.getType());
	        existingTradeDetails.setPricePerUnit(tradeDetails.getPricePerUnit());

	        // Save and return updated trade details
	        return tradeDetailsRepository.save(existingTradeDetails);
	    }
	 
	 @Override
	    public TradeDetails getTradeDetailsById(Integer id) throws TradeDetailsValidationException {
	        java.util.Optional<TradeDetails> tradeDetailsOptional = tradeDetailsRepository.findById(id);
	        if (tradeDetailsOptional.isPresent()) {
	            return tradeDetailsOptional.get();
	        } else {
	            throw new TradeDetailsValidationException("Trade details with ID " + id + " not available");
	        }
	    }
	 
	 @Override
	 public List<TradeDetails> findByCriteria(String stockName, String type, Date tradeDateTime, Float listingPrice, Integer quantity, Float pricePerUnit) {
	        // Perform validation checks
	        if (stockName == null && type == null && tradeDateTime == null && listingPrice == null && quantity == null && pricePerUnit == null) {
	            throw new IllegalArgumentException("At least one search parameter must be provided.");
	        }
	        // Perform other validation checks as needed

	        try {
	            // Call the repository method
	            return tradeDetailsRepository.findByCriteria(stockName, type, tradeDateTime, listingPrice, quantity, pricePerUnit);
	        } catch (Exception ex) {
	            // Handle any unexpected exceptions
	            throw new TradeDetailsValidationException("An error occurred while searching for trade details.", ex);
	        }
	    }

	@Override
	public void deleteTradeDetails(Integer id) {
		// TODO Auto-generated method stub
		tradeDetailsRepository.deleteById(id);
	}
	


}
