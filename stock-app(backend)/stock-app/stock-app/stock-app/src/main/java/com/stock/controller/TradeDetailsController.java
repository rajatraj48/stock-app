package com.stock.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.ResponseDTO;

import com.stock.exeception.TradeDetailsValidationException;
import com.stock.model.TradeDetails;
import com.stock.service.TradeDetailsService;


@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class TradeDetailsController {
	
	  @Autowired
	    private TradeDetailsService tradeDetailsService;
	
	  @PostMapping("/trade")
	  public ResponseEntity<ResponseDTO> createTradeDetails(@RequestBody TradeDetails tradeDetails) {
	      try {
	          if (tradeDetails.getStockName() == null || tradeDetails.getStockName().isEmpty() ||
	                  tradeDetails.getListingPrice() == 0 || 
	                  tradeDetails.getQuantity() == null || 
	                  tradeDetails.getType() == null || tradeDetails.getType().isEmpty() || 
	                  tradeDetails.getPricePerUnit() == null) {
	              throw new TradeDetailsValidationException("One or more fields are null or empty");
	          }
	          TradeDetails createdTradeDetails = tradeDetailsService.createTradeDetails(tradeDetails);
	          
	          // For simplicity, we'll just return the ID of the created object as a String here
	          ResponseDTO successResponse = new ResponseDTO("Trade details created with ID: " + createdTradeDetails.getId(), HttpStatus.OK.value());
	          return ResponseEntity.ok(successResponse);
	      } catch (TradeDetailsValidationException e) {
	          // Return a bad request response with the custom error message
	          ResponseDTO errorResponse = new ResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
	          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	      }
	  }

	  
	  
	  @GetMapping("/trade")
	  public ResponseEntity<?> getAllTradeDetails(@RequestParam(defaultValue = "0") int page,
	                                               @RequestParam(defaultValue = "5") int size) {
	      try {
	          Pageable pageable = PageRequest.of(page, size);
	          Page<TradeDetails> tradeDetailsPage = tradeDetailsService.getAllTradeDetails(pageable);

	          // Extract necessary information from the Page object
	          List<TradeDetails> tradeDetailsList = tradeDetailsPage.getContent();
	          long totalElements = tradeDetailsPage.getTotalElements();
	          int totalPages = tradeDetailsPage.getTotalPages();

	          // Create a response object including content, total elements, and total pages
	          Map<String, Object> response = new HashMap<>();
	          response.put("content", tradeDetailsList);
	          response.put("totalElements", totalElements);
	          response.put("totalPages", totalPages);

	          return ResponseEntity.ok(response);
	      } catch (TradeDetailsValidationException e) {
	          ResponseDTO responseDTO = new ResponseDTO("Error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
	          return ResponseEntity.badRequest().body(responseDTO);
	      } catch (Exception e) {
	          ResponseDTO responseDTO = new ResponseDTO("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
	      }
	  }

	  
	  @PutMapping("trade/{id}")
	  public ResponseEntity<ResponseDTO> updateTradeDetails(@PathVariable("id") Integer id,
	                                                        @RequestBody TradeDetails tradeDetails) {
	      try {
	          TradeDetails updatedTradeDetails = tradeDetailsService.updateTradeDetails(id, tradeDetails);
	          ResponseDTO responseDTO = new ResponseDTO("Trade details updated successfully", HttpStatus.OK.value());
	          return ResponseEntity.ok(responseDTO);
	      } catch (TradeDetailsValidationException e) {
	          ResponseDTO responseDTO = new ResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	      } catch (Exception e) {
	          ResponseDTO responseDTO = new ResponseDTO("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
	      }
	  }

	  
	  @GetMapping("/trade/{id}")
	  public ResponseEntity<?> getTradeDetailsById(@PathVariable("id") Integer id) {
	      try {
	          TradeDetails tradeDetails = tradeDetailsService.getTradeDetailsById(id);
	          return ResponseEntity.ok(tradeDetails);
	      } catch (TradeDetailsValidationException e) {
	          ResponseDTO responseDTO = new ResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	      } catch (Exception e) {
	          ResponseDTO responseDTO = new ResponseDTO("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
	      }
	  }

	  @GetMapping("/search")
	  public ResponseEntity<?> searchTradeDetails(
	          @RequestParam(value = "stockName", required = false) String stockName,
	          @RequestParam(value = "type", required = false) String type,
	          @RequestParam(value = "tradeDateTime", required = false) Date tradeDateTime,
	          @RequestParam(value = "listingPrice", required = false) Float listingPrice,
	          @RequestParam(value = "quantity", required = false) Integer quantity,
	          @RequestParam(value = "pricePerUnit", required = false) Float pricePerUnit
	  ) {
	      try {
	          List<TradeDetails> tradeDetailsList = tradeDetailsService.findByCriteria(stockName, type, tradeDateTime, listingPrice, quantity, pricePerUnit);
	          return ResponseEntity.ok(tradeDetailsList);
	      } catch (TradeDetailsValidationException e) {
	          ResponseDTO responseDTO = new ResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	      } catch (Exception e) {
	          ResponseDTO responseDTO = new ResponseDTO("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
	      }
	  }


	  @DeleteMapping("/trade/{id}")
	  public ResponseEntity<?> deleteTradeDetails(@PathVariable("id") Integer id) {
	      try {
	          tradeDetailsService.deleteTradeDetails(id);
	          return ResponseEntity.ok(new ResponseDTO("Trade details with ID " + id + " deleted successfully", HttpStatus.OK.value()));
	      } catch (TradeDetailsValidationException e) {
	          ResponseDTO responseDTO = new ResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
	      } catch (Exception e) {
	          ResponseDTO responseDTO = new ResponseDTO("Error deleting trade details", HttpStatus.INTERNAL_SERVER_ERROR.value());
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
	      }
	  }


}

