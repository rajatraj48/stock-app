package com.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.ResponseDTO;
import com.stock.exeception.TradeDetailsValidationException;
import com.stock.model.OrderMaster;
import com.stock.model.TradeDetails;
import com.stock.service.OrderMasterService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class OrderMasterController {

	
	@Autowired
    private OrderMasterService orderMasterService;
	
	
	@PostMapping("/order")
	public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderMaster orderMaster) {
	    try {
	        // Extract trade details list from the request body
	        List<TradeDetails> tradeDetailsList = orderMaster.getTradeDetailsList();

	        // Loop through each trade detail and set its properties in the order master object
	        for (TradeDetails tradeDetails : tradeDetailsList) {
	            OrderMaster newOrderMaster = new OrderMaster();
	            newOrderMaster.setTradeDateTime(tradeDetails.getTradeDateTime());
	            newOrderMaster.setStockName(tradeDetails.getStockName());
	            newOrderMaster.setListingPrice(tradeDetails.getListingPrice());
	            newOrderMaster.setQuantity(tradeDetails.getQuantity());
	            newOrderMaster.setType(tradeDetails.getType());
	            newOrderMaster.setPricePerUnit(tradeDetails.getPricePerUnit());
	            newOrderMaster.setStatus(orderMaster.getStatus());

	            // Now you can use the newOrderMaster object to save the order
	            orderMasterService.createOrder(newOrderMaster);
	        }

	        // Return a success response
	        ResponseDTO successResponse = new ResponseDTO("Orders created successfully", HttpStatus.OK.value());
	        return ResponseEntity.ok(successResponse);
	    } catch (Exception e) {
	        // Return a bad request response with the custom error message
	        ResponseDTO errorResponse = new ResponseDTO("Error creating orders: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
	}



	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrderMasters() {
	    try {
	        // Retrieve all OrderMaster objects
	        List<OrderMaster> orderMasters = orderMasterService.getAllOrderMasters();
	        
	        // Check if any OrderMaster objects exist
	        if (!orderMasters.isEmpty()) {
	            // Return the list of OrderMaster objects in the response
	            return ResponseEntity.ok(orderMasters);
	        } else {
	            // Return a not found response if no OrderMaster objects exist
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No OrderMasters found");
	        }
	    } catch (Exception e) {
	        // Return an internal server error response if an exception occurs
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching OrderMasters: " + e.getMessage());
	    }
	}


}
