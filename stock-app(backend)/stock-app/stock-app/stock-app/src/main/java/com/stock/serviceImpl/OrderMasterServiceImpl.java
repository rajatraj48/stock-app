package com.stock.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.model.OrderMaster;
import com.stock.repo.OrderMasterRepo;
import com.stock.service.OrderMasterService;


@Service
public class OrderMasterServiceImpl implements OrderMasterService{

	@Autowired
	private OrderMasterRepo oredrmasterrepo;
	
	
	@Override
	public OrderMaster createOrder(OrderMaster orderMaster) {
	    // Save the OrderMaster object and return the saved object
	    return oredrmasterrepo.save(orderMaster);
	}


	@Override
	public List<OrderMaster> getAllOrderMasters() {
		// TODO Auto-generated method stub
		return oredrmasterrepo.findAll();
	}


}
