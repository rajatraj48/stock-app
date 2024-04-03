package com.stock.service;

import java.util.List;

import com.stock.model.OrderMaster;

public interface OrderMasterService {

	OrderMaster createOrder(OrderMaster orderMaster);

	List<OrderMaster> getAllOrderMasters();


}
